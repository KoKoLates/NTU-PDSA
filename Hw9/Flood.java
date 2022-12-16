import java.util.*;
import edu.princeton.cs.algs4.*;

class Flood {
    /**
     * Create an object to store the distance and village relationship
     */
    private static class Pair implements Comparable<Pair> {
        public int key, value;
        public Pair(int key, int value) {
            this.key = key; this.value = value; }

        @Override
        public int compareTo(Pair that) {
            if(this.key == that.key) return Integer.compare(that.value, this.value);
            else return Integer.compare(this.key, that.key);
        }
    }
    /**
     * Find the latest village being attacked by flood.
     * @param villages the villages number.
     * @param road the relationship and weight of road between villages
     * @return the latest village being attacked.
     */
    public int village(int villages, List<int[]> road) {
        MaxPQ<Pair> maxPQ = new MaxPQ<>();
        EdgeWeightedDigraph digraph = new EdgeWeightedDigraph(villages);
        for(int[] i: road)
            digraph.addEdge(new DirectedEdge(i[0], i[1], i[2]));

        // using the dijkstra algorithms to find the shortest path of each village
        DijkstraSP dijkstraSP = new DijkstraSP(digraph, 0);
        for (int i = 1; i < villages; i++) {
            if(dijkstraSP.hasPathTo(i))
                maxPQ.insert(new Pair((int)dijkstraSP.distTo(i), i));
        }
        // using the maximum to obtain the latest one
        return maxPQ.isEmpty() ? 0 : maxPQ.delMax().value;
    }
}
