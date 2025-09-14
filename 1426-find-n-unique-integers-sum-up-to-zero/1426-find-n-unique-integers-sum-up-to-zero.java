class Solution {
    public int[] sumZero(int n) {
        int[] result = new int[n];
        // Index to insert elements
        int idx = 0;
        // If n is odd, include 0 in the result
        if (n % 2 != 0) {
            result[idx++] = 0;
        }
        // Add pairs of positive and negative numbers
        for (int i = 1; i <= n / 2; i++) {
            result[idx++] = i;    // Add positive number
            result[idx++] = -i;   // Add its negative counterpart
        }
        return result;
    }
}
