class LongJump {
    AVT bst;
    private static class AVT {
        Node root;
        private static class Node {
            int value, height; Node left, right;
            public Node(int value) {this.value = value; this.height = 0; this.right = this.left = null;}
        }

        AVT() {root = null;}
        private int height(Node node) {return node!= null ? node.height : -1;}
        private void updateHeight(Node node) {
            int leftHeight = height(node.left), rightHeight = height(node.right);
            node.height = Math.max(leftHeight, rightHeight) + 1;
        }

        private int balanceFactor(Node node) {
            return height(node.right) - height(node.left);
        }

        private Node rotateRight(Node node) {
            Node left = node.left; node.left = left.right; left.right = node;
            updateHeight(node); updateHeight(left);
            return left;
        }

        private Node rotateLeft(Node node) {
            Node right = node.right; node.right = right.left; right.left = node;
            updateHeight(node); updateHeight(right);
            return right;
        }

        private Node balance(Node node) {
            int balanceFactor = balanceFactor(node);
            if (balanceFactor < -1) {
                if (balanceFactor(node.left) > 0) {
                    node.left = rotateLeft(node.left);
                }
                node = rotateRight(node);
            }
            if (balanceFactor > 1) {
                if (balanceFactor(node.right) < 0) {
                    node.right = rotateRight(node.right);
                }
                node = rotateLeft(node);
            }

            return node;
        }
        public void insert(int value) {root = insert(root, value);}
        private Node insert(Node node, int value) {
            if(node == null) return new Node(value);
            if(node.value > value) node.left = insert(node.left, value);
            else if(node.value < value) node.right = insert(node.right, value);
            updateHeight(node);
            return balance(node);
        }

        public int rangeSum(Node node, int left, int right) {
            if(node == null) return 0;
            if(node.value < left) return rangeSum(node.right, left, right);
            if(node.value > right) return rangeSum(node.left, left, right);
            return node.value + rangeSum(node.left, left, right) + rangeSum(node.right, left, right);
        }
    }
    public LongJump(int[] playerList) {
        bst = new AVT();
        for(int i: playerList) bst.insert(i);
    }

    public void addPlayer(int distance) {
        bst.insert(distance);
    }

    public int winnerDistances(int from, int to) {
        return bst.rangeSum(bst.root, from, to);
    }
}
