import java.util.List;
import java.util.ArrayList;
import edu.princeton.cs.algs4.*;

class Budget {
    public Budget(){};

    /**
     * The function to calculate the shortest path for call island.
     * @param island the number of island to connected.
     * @param bridge the connected relationship between island and cost.
     * @return the total cost for shortest path.
     */
    public int plan(int island, List<int[]> bridge) {
        EdgeWeightedGraph edgeWeightedGraph = new EdgeWeightedGraph(island);
        for(int[] i: bridge) edgeWeightedGraph.addEdge(new Edge(i[0], i[1], i[2]));
        PrimMST primMST = new PrimMST(edgeWeightedGraph);
        return (int)primMST.weight();
    }
}
