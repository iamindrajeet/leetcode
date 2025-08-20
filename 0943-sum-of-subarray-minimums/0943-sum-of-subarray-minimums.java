class Solution {
    public int sumSubarrayMins(int[] arr) {
        int MOD = 1_000_000_007;
        int sum = 0;

        // Get Next Smaller to Left and Right
        int[] nsl = getNsl(arr);
        int[] nsr = getNsr(arr);

        for (int i = 0; i < arr.length; i++) {
            int left = i - nsl[i];         // Number of elements to the left (including current) for which arr[i] is min
            int right = nsr[i] - i;        // Number of elements to the right (including current) for which arr[i] is min

            int totalWays = left * right;  // Total subarrays where arr[i] is the minimum
            long totalSum = (long) totalWays * arr[i];

            sum = (int)((sum + totalSum) % MOD); // Add to sum with modulo
        }

        return sum;
    }

    // Function to compute Next Smaller to Left indices
    private int[] getNsl(int[] arr) {
        int[] nsl = new int[arr.length];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < arr.length; i++) {
            // Maintain monotonic increasing stack
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i])
                stack.pop();

            // If stack is empty, no smaller element to the left
            nsl[i] = stack.isEmpty() ? -1 : stack.peek();

            stack.push(i); // Push current index
        }

        return nsl;
    }

    // Function to compute Next Smaller to Right indices
    private int[] getNsr(int[] arr) {
        int[] nsr = new int[arr.length];
        Stack<Integer> stack = new Stack<>();

        for (int i = arr.length - 1; i >= 0; i--) {
            // Maintain monotonic increasing stack
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i])
                stack.pop();

            // If stack is empty, no smaller element to the right
            nsr[i] = stack.isEmpty() ? arr.length : stack.peek();

            stack.push(i); // Push current index
        }

        return nsr;
    }
}
