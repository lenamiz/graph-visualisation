/**
 * @author lenamiz
 * github.com/lenamiz/
 */
package graph_vis;

/**
 * Class representing directed graph
 */
public class DiGraph extends Graph {

    /**
     * adds new edge to the graph
     * @param nodeVal1 value of node 1
     * @param nodeVal2 value of node 2
     * @param lbl edge label
     */
    @Override
    public void addEdge(String nodeVal1, String nodeVal2, String lbl){
        int id1 = getNodeID(nodeVal1);
        int id2 = getNodeID(nodeVal2);
        Edge e = new Edge(lbl);

        adjList.get(getNode(id1)).add(getNode(id2));
        adjacencyMtx[id1][id2] = e;
    }

    /**
     * gets node degree
     * @param nodeID id of the node
     * @return node degreee
     */
    @Override
    public int getDegree(int nodeID){
        return adjList.get(getNode(nodeID)).size();
    }
}
