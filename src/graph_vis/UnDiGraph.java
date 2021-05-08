/**
 * graph visualisation tool
 * @author lenamiz
 * github.com/lenamiz/
 */
package graph_vis;

/**
 * Class for representing undirected graph
 */
public class UnDiGraph extends Graph {

    /**
     * adds new edge to the graph
     * @param nodeVal1 node 1 value
     * @param nodeVal2 node 2 value
     * @param lbl edge label
     */
    @Override
    public void addEdge(String nodeVal1, String nodeVal2, String lbl){
        int id1 = getNodeID(nodeVal1);
        int id2 = getNodeID(nodeVal2);
        Edge e = new Edge(lbl);

        adjList.get(getNode(id1)).add(getNode(id2));
        adjList.get(getNode(id2)).add(getNode(id1));
        adjacencyMtx[id1][id2] = e;
        adjacencyMtx[id2][id1] = e;
    }

    /**
     * gets node degree
     * @param nodeID id of the node
     * @return node degree
     */
    @Override
    public int getDegree(int nodeID){
        return adjList.get(getNode(nodeID)).size();
    }

}