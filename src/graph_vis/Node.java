/**
 * graph visualisation tool
 * @author lenamiz
 * github.com/lenamiz/
 */
package graph_vis;

import javafx.util.Pair;

/**
 * Class for representing node in the graph
 */
public class Node {
    protected String nodeVal;
    protected int nodeID;
    protected Pair<Integer,Integer> coordinates;

    /**
     * constructor for setting node parameters
     * @param val node value
     * @param id node id
     */
    public Node(String val, int id){
        this.nodeVal = val;
        this.nodeID = id;
    }

    /**
     * sets node coordinates
     * @param p1 node coordinates
     */
    public void setCoordinates(Pair<Integer, Integer> p1){
        coordinates = p1;
    }

    /**
     * gets node coordinates
     * @return node coordinates
     */
    public Pair<Integer,Integer> getCoordinates(){
        return coordinates;
    }
}
