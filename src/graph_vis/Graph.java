package graph_vis;

import javax.swing.*;
import java.util.*;

abstract class Graph {

    protected int totalNodeNum;
    protected Map<Node, List<Node>> adjList;    //adjacency list
    protected Edge[][] adjacencyMtx;  //adjacency matrix
    protected List<Node> nodeList;  //list of all graph's nodes


    public Graph() {
        this.totalNodeNum = 0;
        adjacencyMtx = new Edge[20][20];
        nodeList = new ArrayList<>();
        adjList = new HashMap<>();
    }

    public void addNode(String nodeVal) {
        if(totalNodeNum>=20){
            JOptionPane.showMessageDialog(null,"Maximum node number exceeded!");
        }
        else {
            if (!isNodeAlreadyInGraph(nodeVal)) {
                Node node = new Node(nodeVal, totalNodeNum);

                nodeList.add(node);
                adjList.put(node, new LinkedList<Node>());
                totalNodeNum++;
            }
        }
    }

    public abstract void addEdge(String v1, String v2, String lbl);
    public abstract int getDegree(int nodeID);

    public Node getNode(int nodeID) {
        return nodeList.get(nodeID);
    }

    public int getNodeID(String nodeVal) {
        int id = -1;
        for (int i = 0; i < nodeList.size(); i++) {
            if (nodeList.get(i).nodeVal.equals(nodeVal))
                id = i;
        }
        return id;
    }

    public boolean isNodeAlreadyInGraph(String nodeVal) {
        boolean is = false;

        for (Node node : nodeList) {
            if (node.nodeVal.equals(nodeVal)) {
                is = true;
            }
        }

        return is;
    }

    public int getNodeIDWithMaxNeighbours(){
        int biggest = 0;
        int id = -1;
        for (int i=0; i<adjList.size(); i++){
            if(biggest < adjList.get(getNode(i)).size()){
                biggest = adjList.get(getNode(i)).size();
                id = i;
            }
        }
        return id;
    }
}