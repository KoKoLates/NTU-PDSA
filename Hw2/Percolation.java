import edu.princeton.cs.algs4.Quick;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

class Percolation {
    Node[] rootHead;
    Point2D[] region;
    int n, upper, down, count = 0;
    WeightedQuickUnionUF fullCheck;
    WeightedQuickUnionUF percolatedCheck;
    // Create a direction matrix for four direction searching
    int[][] m = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    /**
     * Node class for the linked list to store the percolated region
     */
    private static class Node{
        private Node next;
        private Node tail;
        private int length;
        private final Point2D site;

        /**
         * Initial the node with itself
         * @param site The coordinate of the open site
         */
        public Node(Point2D site){
            this.site = site;
            this.tail = this;
            this.next = null;
            this.length = 1;
        }

        /**
         * Insert the node itself at the head of another node.
         * @param node The head of node to be inserted
         */
        private void insertHead(Node node){
            this.length += node.length;
            this.tail.next = node;
            this.tail = node.tail;
        }
    }

    public Percolation(int N){
        n = N; upper = n * n; down = n * n + 1;
        rootHead = new Node[n * n];
        fullCheck = new WeightedQuickUnionUF(n * n + 1);
        percolatedCheck = new WeightedQuickUnionUF(n * n + 2);
    }

    public void open(int i, int j){
        Point2D site = new Point2D(i, j);
        Node node = new Node(site);
        rootHead[i * n + j] = node;
        for(int k = 0; k < 4; k++){
            int a = i + m[k][0];
            int b = j + m[k][1];
            if(a < 0 || a > n - 1 || b < 0 || b > n - 1 || !isOpen(a, b) ||
                    fullCheck.find(a * n + b) == fullCheck.find(i * n + j))
                continue;

            Node root = rootHead[fullCheck.find(a * n + b)];
            node.insertHead(root);
            fullCheck.union(i * n + j, a * n + b);
            percolatedCheck.union(i * n + j, a * n + b);
            rootHead[fullCheck.find(a * n + b)] = node;
        }

        if(i == 0){
            fullCheck.union(j, upper);
            percolatedCheck.union(j, upper);
        }
        if(i == n - 1)
            percolatedCheck.union(i * n + j, down);

        if(percolates() && count == 0){
            count++;
            region = new Point2D[node.length];
            int index = 0;
            while(node != null) {
                region[index++] = node.site;
                node = node.next;
            }
            Quick.sort(region);
        }
    }

    /**
     * Check the corresponding site is opened or not
     * @param i The col of the site
     * @param j The row of the site
     * @return The boolean value of open status
     */
    public boolean isOpen(int i, int j){
        return !(rootHead[i * n + j] == null);
    }

    /**
     * Check the corresponding site is full or not
     * @param i The col of the site
     * @param j The row of the site
     * @return The boolean value of open status
     */
    public boolean isFull(int i, int j){
        return fullCheck.find(i * n + j) == fullCheck.find(upper);
    }

    /**
     * Check the system is percolated or not
     * @return The boolean status represent percolate
     */
    public boolean percolates(){
        return percolatedCheck.find(upper) == percolatedCheck.find(down);
    }

    /**
     * Get the percolation path once the system is percolated
     * @return The array of the sites of the percolation region
     */
    public Point2D[] PercolatedRegion(){
        return region;
    }
}
