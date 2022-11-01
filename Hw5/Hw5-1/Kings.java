import java.util.Queue;
import java.util.PriorityQueue;
import edu.princeton.cs.algs4.Stack;

class King implements Comparable <King>{
    int Strength; int Range; int Index;
    King(int str, int rng, int i) {
        this.Strength = str; this.Range = rng; this.Index = i;
    }
    /**
     * implementation the compare method for the King object.
     * @param k the object to be compared.
     * @return the compare result.
     */
    public int compareTo(King k) {
        if (Strength == k.Strength) return Integer.compare(k.Index, Index);
        else if (Strength > k.Strength) return 1;
        else return -1;
    }
}

class Kings {
    private final King[] king;
    /**
     * The constructor of the Kings that got the Kings from the given warriors.
     * @param strength the strength of each warrior.
     * @param range the attack range of each warrior.
     */
    public Kings(int[] strength, int[] range) {
        King[] warrior = new King[strength.length];
        for(int i = 0; i < strength.length; i++)
            warrior[i] = new King(strength[i], range[i], i);

        // Using two stack for two direction range and strength filter.
        Stack<King> stack = new Stack<>();
        for(int i = 0; i < warrior.length; i++) {
            while (!stack.isEmpty() && warrior[i].Strength > stack.peek().Strength) {
                if ((i - stack.peek().Index) <= warrior[i].Range) stack.pop();
                else break;
            }
            stack.push(warrior[i]);
        }

        Stack<King> kings = new Stack<>();
        while (!stack.isEmpty()) {
            while (!kings.isEmpty() && kings.peek().Strength < stack.peek().Strength) {
                if (kings.peek().Index - stack.peek().Index <= stack.peek().Range) kings.pop();
                else break;
            }
            kings.push(stack.pop());
        }

        king = new King[kings.size()]; int count = 0;
        while (!kings.isEmpty()) king[count++] = kings.pop();
    }

    /**
     * Using the priority queue for searching top K-th kings
     * @param k the number of k-th.
     * @return the top k-th kings
     */
    public int[] topKKings(int k) {
        if (k > king.length) k = king.length;
        Queue<King> queue = new PriorityQueue<>();
        for (King value : king) {
            if (!queue.isEmpty() && queue.size() == k && queue.peek().Strength < value.Strength) queue.remove();
            if (queue.size() < k) queue.add(value);
        }

        int[] ans = new int[k]; int count = k;
        while (!queue.isEmpty()) {
            ans[--count] = queue.poll().Index;
        }
        return ans;
    }
}
