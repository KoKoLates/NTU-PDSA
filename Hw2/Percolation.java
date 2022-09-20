import java.util.HashMap;
import edu.princeton.cs.algs4.Merge;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

class Percolation {
    Point2D[] region;
    int n, upper, down, count = 0;
    WeightedQuickUnionUF all;
    WeightedQuickUnionUF full;
    HashMap<Integer, Node> rootHead;
    HashMap<Integer, Boolean> openStatus;
    int[][] m = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    private static class Node{
        private final Point2D site;
        private Node next;
        private int length;

        public Node(Point2D site){
            this.site = site;
            this.next = null;
            this.length = 1;
        }

        private Node insertHead(Node node){
            int length = this.length + node.length;
            if(this.length > node.length){
                Node temp = node;
                while (temp.next != null)
                    temp = temp.next;

                temp.next = this;
                node.length = length;
                return node;
            }
            else{
                Node temp = this;
                while (temp.next != null)
                    temp = temp.next;

                temp.next = node;
                this.length = length;
                return this;
            }
        }
    }

    public Percolation(int N){
        if(N <= 0)
            throw new IllegalArgumentException("Grid size n <= 0.");
        n = N; upper = n * n; down = n * n + 1;
        rootHead = new HashMap<>();
        openStatus = new HashMap<>();
        all = new WeightedQuickUnionUF(n * n + 2);
        full = new WeightedQuickUnionUF(n * n + 1);
    }

    /**
     * opens the site (row, col) if it is not open already
     * @param i The row of the grid
     * @param j The col of the grid
     */
    public void open(int i, int j){
        Point2D sites = new Point2D(i, j);
        Node node = new Node(sites);
        rootHead.put(i * n + j, node);
        openStatus.put(i * n + j, true);

        for(int k = 0; k < 4; k++){
            int a = i + m[k][0];
            int b = j + m[k][1];
            if(a < 0|| a > n - 1 || b < 0 || b > n - 1 || !isOpen(a, b))
                continue;

            if(full.find(a * n + b) != full.find(i * n + j)){
                Node side = rootHead.get(full.find(a * n + b));
                all.union(a * n + b, i * n + j);
                full.union(a * n + b, i * n + j);

                node = node.insertHead(side);
                rootHead.put(full.find(i * n + j), node);

            }
        }

        if(i == 0) {
            all.union(j, upper);
            full.union(j, upper);
        }
        if(i == n - 1)
            all.union(i * n + j, down);

        if(percolates() && count == 0){
            count++;
            region = new Point2D[node.length];
            int index = 0;
            while (node != null){
                region[index] = node.site;
                node = node.next;
                index++;
            }
            Merge.sort(region);
        }
    }

    /**
     * Is the site (row, col) open or not
     * @param i The row of the grid
     * @param j The col of the grid
     * @return The status of open
     */
    public boolean isOpen(int i, int j){
        return openStatus.getOrDefault(i * n + j, false);
    }

    /**
     * Is the site (row, col) Full or not
     * @param i The row of the grid
     * @param j The col of the grid
     * @return The status of full
     */
    public boolean isFull(int i, int j){
        return full.find(i * n + j) == full.find(upper);
    }

    /**
     * Check the system is percolated or not
     * @return The boolean status represent percolate
     */
    public boolean percolates(){
        return all.find(upper) == all.find(down);
    }

    /**
     * @return The array of the sites of the percolation region
     */
    public Point2D[] PercolatedRegion(){
        return region;
    }
}
