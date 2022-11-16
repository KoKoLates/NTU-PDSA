class LongJump {
    private final SplayTree splayTree;

    public static class SplayTree {
        private static class Node {
            int value; Node parent, left, right;
            public Node(int value) {
                this.value = value; this.parent = this.left = this.right = null;
            }
        }

        private Node root;
        public SplayTree() {root = null;}
        public void insert(int value) {
            Node node = new Node(value);
            Node x = this.root, y = null;
            while (x != null) {
                y = x;
                if(node.value < x.value) x = x.left;
                else x = x.right;
            }
            node.parent = y;
            if(y == null) root = node;
            else if(node.value < y.value) y.left = node;
            else y.right = node;
            splay(node);
        }

        private void splay(Node node){
            while(node.parent != null) {
                if(node.parent.parent == null) {
                    if(node == node.parent.left) rightRotate(node.parent);
                    else leftRotate(node.parent);
                } else if (node == node.parent.left && node.parent == node.parent.parent.left) {
                    rightRotate(node.parent.parent);
                    rightRotate(node.parent);
                } else if (node == node.parent.right && node.parent == node.parent.parent.right) {
                    leftRotate(node.parent.parent);
                    leftRotate(node.parent);
                } else if (node == node.parent.right && node.parent == node.parent.parent.left) {
                    leftRotate(node.parent);
                    rightRotate(node.parent);
                } else {
                  rightRotate(node.parent); leftRotate(node.parent);
                }
            }
        }

        private void rightRotate(Node node) {
            Node temp = node.left; node.left = temp.right;
            if(temp.right != null) temp.right.parent = node;
            temp.parent = node.parent;
            if(node.parent == null) this.root = temp;
            else if(node == node.parent.right) node.parent.right = temp;
            else node.parent.left = temp;
            temp.right = node; node.parent = temp;
        }

        private void leftRotate(Node node) {
            Node temp = node.right; node.right = temp.left;
            if(temp.left != null) temp.left.parent = node;
            temp.parent = node.parent;
            if(node.parent == null) this.root = temp;
            else if(node == node.parent.left) node.parent.left = temp;
            else node.parent.right = temp;
            temp.left = node; node.parent = temp;
        }

        public int rangeSum(Node node, int left, int right) {
            if(node == null) return 0;
            if(node.value < left) return rangeSum(node.right, left, right);
            if(node.value > right) return rangeSum(node.left, left, right);
            return node.value + rangeSum(node.left, left, right) + rangeSum(node.right, left, right);
        }
    }

    public LongJump(int[] playerList) {
        splayTree = new SplayTree();
        for(int i: playerList) splayTree.insert(i);
    }

    public void addPlayer(int distance) {
        splayTree.insert(distance);
    }

    public int winnerDistances(int from, int to) {
        return splayTree.rangeSum(splayTree.root, from, to);
    }
}