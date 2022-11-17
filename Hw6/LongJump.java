class LongJump {
    private final BST tree;

    // Binary Search Tree objects with subtree sum recorded
    private static class BST {
        private static class Node {
            private int subSum, value;
            private Node left, right;
            public Node(int value) {
                this.value = this.subSum = value; this.right = this.left = null; }
        }
        private Node root;
        public BST() {this.root = null;}

        /**
         * Get the total sum of subtree.
         * @param node the root node of the subtree.
         * @return the sum values.
         */
        private int getSubSum(Node node) {
            return node == null ? 0 : node.subSum;
        }

        /**
         * The API function for user to insert the value into BST.
         * @param value the insert value.
         */
        public void insert(int value) {
            root = insert(root, value);
        }

        /**
         * The helper function for recursive use of insert action.
         * @param node the node to be operated.
         * @param value the value to be inserted.
         * @return the root of the BST.
         */
        private Node insert(Node node, int value) {
            if(node == null) return new Node(value);
            if(node.value > value) node.left = insert(node.left, value);
            else if(value > node.value) node.right = insert(node.right, value);
            node.subSum = node.value + getSubSum(node.left) + getSubSum(node.right);
            return node;
        }

        /**
         * Calculate the sum that smaller than to indicate bottom range.
         * @param node the node to be checked.
         * @param value the bottom range value.
         * @return the sum of smaller values.
         */
        public int floor(Node node, int value) {
            if(node == null) return 0;
            if(node.value == value) return getSubSum(node.left);
            else if(node.value > value) return floor(node.left, value);
            else return node.value + getSubSum(node.left) + floor(node.right, value);
        }

        /**
         * Calculate the sum that bigger than to indicate upper range.
         * @param node the node to be checked.
         * @param value the upper range value.
         * @return the sum of bigger values.
         */
        public int ceiling(Node node, int value) {
            if (node == null) return 0;
            if (node.value == value) return getSubSum(node.right);
            else if (node.value < value) return ceiling(node.right, value);
            else return node.value + getSubSum(node.right) + ceiling(node.left, value);
        }

        /**
         * Calculate the range sum of the BST.
         * @param node the node to be checked.
         * @param left the bottom range value.
         * @param right the upper range value.
         * @return the total distance sum of the BST.
         */
        public int rangeSum(Node node, int left, int right) {
            if(node == null) return 0;
            if(node.value < left) return rangeSum(node.right, left, right);
            if(node.value > right) return rangeSum(node.left, left, right);
            return getSubSum(node) - floor(node, left) - ceiling(node, right);
        }
    }

    /**
     * The constructor of the Long Jump.
     * @param playerList the list (distance) of each player to be initialized.
     */
    public LongJump(int[] playerList) {
        tree = new BST();
        for(int i: playerList) tree.insert(i);
    }

    /**
     * The function to add the play to the game (BST).
     * @param distance the player distance to be added.
     */
    public void addPlayer(int distance) {
        tree.insert(distance);
    }

    /**
     * Calculate the range sum of the all player's distances within range.
     * @param from the bottom range value.
     * @param to the upper range value.
     * @return the total distance sum of winner which fall inside the indicated range.
     */
    public int winnerDistances(int from, int to) {
        return tree.rangeSum(tree.root, from, to);
    }
}
