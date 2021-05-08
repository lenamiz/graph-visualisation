package graph_vis;

import javafx.util.Pair;

public class Node {
    protected String nodeVal;
    protected int nodeID;
    protected Pair<Integer,Integer> coordinates;

    public Node(String val, int id){
        this.nodeVal = val;
        this.nodeID = id;
    }

    public void setCoordinates(Pair<Integer, Integer> p1){
        coordinates = p1;
    }

    public Pair<Integer,Integer> getCoordinates(){
        return coordinates;
    }
}
