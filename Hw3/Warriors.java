import edu.princeton.cs.algs4.Stack;

class Warriors {
    public int[] warriors(int[] strength, int[] range) {
        if (strength.length == 1) return new int[]{0, 0};
        int[] ans = new int[strength.length * 2];
        for (int i = 0; i < strength.length; i++) ans[i * 2 + 1] = strength.length - 1;
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < strength.length; i++) {
            while (stack.size() != 0 && strength[i] >= strength[stack.peek()]) {
                ans[stack.pop() * 2 + 1] = i - 1;
            }
            if(i != strength.length - 1) stack.push(i);
        }

        for (int i = strength.length - 1; i >= 0; i--) {
            while (stack.size() != 0 && strength[i] >= strength[stack.peek()]) {
                ans[stack.pop() * 2] = i + 1;
            }
            if(i != 0) stack.push(i);
        }

        ans[0] = 0; ans[strength.length * 2 - 1] = strength.length - 1;
        for (int i = 0; i < strength.length; i++) {
            if (ans[i * 2] < i - range[i]) ans[i * 2] = i - range[i];
            if (ans[i * 2 + 1] > i + range[i]) ans[i * 2 + 1] = i + range[i];
        }
        return ans;
    }
}
