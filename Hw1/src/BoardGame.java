import java.util.HashMap;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

class BoardGame {
    // Quick Union is to record the connected status
    // Type hash map record the stone type of each coordinate
    // Bounded hash map told the numbers of left live path.
    WeightedQuickUnionUF uf;
    HashMap<Integer, Character> type;
    HashMap<Integer, Integer> bounded;
    // Create a direction array for four direction searching
    int [][] m = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    int height, width;

    /**
     * Initialize the board with corresponding size
     * @param h The height of the board
     * @param w The width of the board
     */
    public BoardGame(int h, int w){
        uf = new WeightedQuickUnionUF(h * w);
        type = new HashMap<>();
        bounded = new HashMap<>();
        height = h; width = w;
    }

    /**
     * The function to put the stone in corresponding position
     * @param x x direction coordinate
     * @param y y direction coordinate
     * @param stoneType The type of the stone like 'O' or 'X'
     */
    public void putStone(int[] x, int[] y, char stoneType){
        for(int i = 0; i < x.length; i++){
            // store the type of stone in each position
            type.put(x[i] * width + y[i], stoneType);
            // Initialize the live path. If not in the boundary, got four direction
            if(x[i] == 0 || x[i] == height -1 || y[i] == 0 || y[i] == width -1)
                bounded.put(x[i] * width + y[i], width * height);
            else
                bounded.put(x[i] * width + y[i], 4);

            // find up, down, right and left. If there is any same type and not in the same component, then union
            for(int j = 0; j < 4; j++){
                int count = 0;
                int k = x[i] + m[j][0];
                int l = y[i] + m[j][1];
                if (k < 0 || k > height - 1 || l < 0 || l > width - 1 || getStoneType(k, l) == 'N')
                    continue;

                int root1 = uf.find(k * width + l);
                int root2 = uf.find(x[i] * width + y[i]);
                if(getStoneType(k, l) != getStoneType(x[i], y[i])) {
                    bounded.put(root1, bounded.get(root1) - 1);
                    bounded.put(root2, bounded.get(root2) - 1);
                }
                else{
                    if(root1 != root2){
                        uf.union(root1, root2);
                        int root = uf.find(root1);
                        bounded.put(root, bounded.get(root1) + bounded.get(root2) - 2);
                    }
                    else
                        bounded.put(root1, bounded.get(root1) - 2);
                }
            }
        }
    }

    /**
     * Check the position is surrounded or not
     * @param x x coordinate
     * @param y y coordinate
     * @return The status shown surrounded or not
     */
    public boolean surrounded(int x, int y) {
        // If the stone is right on the boundary, it's definitely not surrounded.
        if(x == 0 || x == height - 1 || y == 0 || y == width - 1)
            return false;
        else{
            // And if the coordinate is closed, then it's surrounded.
            return bounded.get(uf.find(x * width + y)) == 0;
        }
    }

    /**
     * Get the type of stone is corresponding coordinate
     * @param x x coordinate
     * @param y y coordinate
     * @return The type of stone in corresponding position 'O' or 'X'
     */
    public char getStoneType(int x, int y){
        // Searching the stone type of corresponding index through the Hash Map
        return type.getOrDefault(x * width + y, 'N');
    }

    /**
     * Get the number of connected regions
     * @return The numbers of the connected region
     */
    public int countConnectedRegions(){
        return uf.count() + type.size() - height * width;
    }
}
