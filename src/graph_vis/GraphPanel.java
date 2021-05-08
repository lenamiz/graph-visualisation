/**
 * graph visualisation tool
 * @author lenamiz
 * github.com/lenamiz/
 */
package graph_vis;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.LinkedList;
import java.util.*;
import javafx.util.Pair;

/**
 * Class for drawing graph
 */
public class GraphPanel extends JPanel {

    private static final int height = 1000;
    private static final int width = 1000;
    private static final int w = 5; //grid scaling factor, the smaller the factor, the smaller cells
    private static final int rows = height / (2 * w);
    private static final int cols = width / (2 * w);

    private Graph graph;
    private Queue<Node> q;
    private Queue<Node> bufferQ;
    private Cell grid[][] = new Cell[rows][cols];
    private boolean displayedNodes[];
    private ArrayList<Pair<Integer,Integer>> cordsList;

    private int graphTypeDirected;  //says if we need arrows

    private static final Color GRID_COLOR = Color.white;
    private static final Color NODE_COLOR = Color.blue;
    private static final Color LABEL_COLOR = Color.black;
    private static final Color EDGE_COLOR = Color.pink;

    public GraphPanel(Graph graph, int graphType) {
        this.graph = graph;
        this.graphTypeDirected = graphType;
        q = new LinkedList<>();
        bufferQ = new LinkedList<>();
        this.displayedNodes = new boolean[20];
        cordsList = new ArrayList<>();
    }

    /**
     * random number generator
     * @param bound maximum number possible to obtain
     * @return random number
     */
    private int getRandomNumber(int bound) {
        Random rnd = new Random();
        int random = rnd.nextInt(bound + 1);    //number from 0 to bound
        return random;
    }

    /**
     * drawing graph
     * @param g graphics
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        //----------------GRID DRAWING--------------------------


        g2d.setColor(GRID_COLOR);
        for (int j = 0; j < rows; j++) {
            for (int i = 0; i < cols; i++) {
                Cell cell = new Cell(i, j);
                int x = i * w;
                int y = j * w;
                grid[i][j] = cell;

                if (cell.getWall(0)) {
                    g.drawLine(x, y, x + w, y);
                }
                if (cell.getWall(1)) {
                    g.drawLine(x + w, y, x + w, y + w);
                }
                if (cell.getWall(2)) {
                    g.drawLine(x + w, y + w, x, y + w);
                }
                if (cell.getWall(3)) {
                    g.drawLine(x, y, x, y + w);
                }
            }
        }
        g2d.setColor(NODE_COLOR);

        //-------------------ALGORITHM------------------------------

        int maxID = graph.getNodeIDWithMaxNeighbours(); //we start from the node with max neighbours number
        g2d.drawString(graph.nodeList.get(maxID).nodeVal, 245, 255);  //displaying at the center of the grid
        Pair<Integer, Integer> p1 = new Pair<>(245,255);
        cordsList.add(p1);

        graph.getNode(maxID).setCoordinates(p1);   //getting coordinates
        displayedNodes[maxID] = true;   //which node displayed

        //node circle
        Ellipse2D circle2 = new Ellipse2D.Double(240, 240, 20, 20);
        g2d.draw(circle2);

        int currentID = maxID;

        //displaying neighbours one at a time
        while (!areAllNodesDisplayed()) {

            //neighbours to the queue
            for (int i = 0; i < graph.getDegree(currentID); i++) {
                Node currentNode = graph.adjList.get(graph.getNode(currentID)).get(i);
                q.add(currentNode);
                bufferQ.add(currentNode);
            }

            //displaying neighbours and edges
            while(!q.isEmpty()) {

                Pair<Integer, Integer> next;
                String neighbVal = q.remove().nodeVal;

                if (!displayedNodes[graph.getNodeID(neighbVal)]) {//checks if the neighbour isn't already entered

                    do {
                        int random = getRandomNumber(19);
                        next = getNextCoordinates(random, graph.getNode(currentID).coordinates);
                    } while (collisionDetection(next));


                    graph.getNode(graph.getNodeID(neighbVal)).setCoordinates(next); //sets neighbour's coordinates
                    cordsList.add(next);

                    g2d.drawString(neighbVal, next.getKey(), next.getValue()); //draws neighbour

                    //draws circle
                    Ellipse2D circleN = new Ellipse2D.Double(next.getKey() - 5, next.getValue() - 15, 20, 20); //20 = w
                    g2d.draw(circleN);

                    //saves that node is displayed
                    displayedNodes[graph.getNodeID(neighbVal)] = true;
                }
                //draws edge
                drawEdge(g2d, currentID, graph.getNodeID(neighbVal));
            }
            //queue empty => to the next generation
            currentID = bufferQ.remove().nodeID;

        }
    }

    /**
     * checking for collision (if nodes aren't covering each other)
     * @param p1 node coordinates
     * @return true if collision detected
     */
    public boolean collisionDetection(Pair<Integer, Integer> p1) {
        boolean collision = false;
        Pair<Integer, Integer> p2;

        for(int i=0; i<cordsList.size(); i++) {
            p2 = cordsList.get(i);
            if (p1.getKey()+10 == p2.getKey()+10 && p1.getKey()-10 == p2.getKey()-10 && p1.getValue()+10 == p2.getValue()+10 && p1.getValue()-10 == p2.getValue()-10) {
                collision = true;
            }
        }
        return collision;
    }

    /**
     * checking if all nodes displayed
     * @return true if all nodes displayed
     */
    public boolean areAllNodesDisplayed(){
        boolean areThey = true;

        for (int i=0; i<graph.totalNodeNum; i++){
            if (displayedNodes[i] == false){
                areThey = false;
            }
        }
        return areThey;
    }

    /**
     * edge drawing
     * @param g2d graphics
     * @param id1 node 1 id
     * @param id2 node 2 id
     */
    public void drawEdge(Graphics g2d, int id1, int id2){

        g2d.setColor(EDGE_COLOR);

        //edge line
        int x1 = graph.getNode(id1).coordinates.getKey()+5; //+5;
        int y1 = graph.getNode(id1).coordinates.getValue()+5; //-15;
        int x2 = graph.getNode(id2).coordinates.getKey()-5; //+5;
        int y2 = graph.getNode(id2).coordinates.getValue()-5; //-15;
        if(x1<490 && x2<490 && y1<500 && y2<500) {
            g2d.drawLine(x1, y1, x2, y2);

            //arrow, if graph directed
            if (graphTypeDirected == 1) {
                g2d.drawString("X", x2-5, y2);
            }

            //label
            //g2d.setColor(LABEL_COLOR);
            String label = graph.adjacencyMtx[id1][id2].label;
            g2d.drawString(label, (x1+x2)/2, (y1+y2)/2);
        }
        g2d.setColor(NODE_COLOR);
    }

    /**
     * computing next node's coordinates
     * @param direction random number from range [0,19]
     * @param dirs current node's coordinates
     * @return neighbor's coordinates
     */

    public Pair<Integer,Integer> getNextCoordinates(int direction, Pair<Integer,Integer> dirs){
        int x,y;
        int maxDistance = 100;

        switch (direction){
            case 0:
                x = dirs.getKey()-8;
                y = dirs.getValue()-maxDistance;
                break;
            case 1:
                x = dirs.getKey()+maxDistance;
                y = dirs.getValue()-8;
                break;
            case 2:
                x = dirs.getKey()-8;
                y = dirs.getValue()+maxDistance;
                break;
            case 3:
                x = dirs.getKey()-maxDistance;
                y = dirs.getValue()-8;
                break;
            case 4:
                x = dirs.getKey()+maxDistance;
                y = dirs.getValue()-maxDistance;
                break;
            case 5:
                x = dirs.getKey()+maxDistance;
                y = dirs.getValue()+maxDistance;
                break;
            case 6:
                x = dirs.getKey()-maxDistance;
                y = dirs.getValue()+maxDistance;
                break;
            case 7:
                x = dirs.getKey()-maxDistance;
                y = dirs.getValue()-maxDistance;
                break;
            case 8:
                x = dirs.getKey()+24;
                y = dirs.getValue()-maxDistance;
                break;
            case 9:
                x = dirs.getKey()+maxDistance;
                y = dirs.getValue()+24;
                break;
            case 10:
                x = dirs.getKey()-24;
                y = dirs.getValue()+maxDistance;
                break;
            case 11:
                x = dirs.getKey()-maxDistance;
                y = dirs.getValue()-24;
                break;
            case 12:
                x = dirs.getKey()-24;
                y = dirs.getValue()-maxDistance;
                break;
            case 13:
                x = dirs.getKey()+maxDistance;
                y = dirs.getValue()-24;
                break;
            case 14:
                x = dirs.getKey()+24;
                y = dirs.getValue()+maxDistance;
                break;
            case 15:
                x = dirs.getKey()-maxDistance;
                y = dirs.getValue()+24;
                break;
            case 16:
                x = dirs.getKey()+8;
                y = dirs.getValue()-maxDistance;
                break;
            case 17:
                x = dirs.getKey()+maxDistance;
                y = dirs.getValue()+8;
                break;
            case 18:
                x = dirs.getKey()+8;
                y = dirs.getValue()+maxDistance;
                break;
            case 19:
                x = dirs.getKey()-maxDistance;
                y = dirs.getValue()+8;
                break;
            default:
                x = dirs.getKey();
                y = dirs.getValue();
                break;
        }

        if(direction%2 == 0){
            x = x+40;
            y = y+40;
        }

        Pair<Integer,Integer> cords = new Pair(x,y);
        return cords;
    }

}
