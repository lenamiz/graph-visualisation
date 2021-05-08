package graph_vis;


public class UnDiGraph extends Graph {

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

    @Override
    public int getDegree(int nodeID){
        return adjList.get(getNode(nodeID)).size();
    }

}