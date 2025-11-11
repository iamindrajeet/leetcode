/**
Dynamic Programming without memoization (0/1 Knapsack variation)
Time Complexity	- O(2^len)
Space Complexity - O(len)
*/
// class Solution {
//     public int findMaxForm(String[] strs, int m, int n) {
//         int len = strs.length;

//         // count[i][0] = number of zeros in strs[i]
//         // count[i][1] = number of ones in strs[i]
//         int[][] count = new int[len][2];

//         // Preprocess each string to count zeros and ones
//         for (int i = 0; i < len; i++) {
//             int zeros = 0, ones = 0;

//             // Count zeros and ones in the current string
//             for (char ch : strs[i].toCharArray()) {
//                 if (ch == '0')
//                     zeros++;
//                 else
//                     ones++;
//             }

//             count[i][0] = zeros;
//             count[i][1] = ones;
//         }

//         // Call recursive function starting from last index
//         return solve(count, m, n, len - 1);
//     }

//     private int solve(int[][] count, int m, int n, int i) {
//         // Base case: no strings left or no capacity to take more
//         if (i < 0 || (m == 0 && n == 0))
//             return 0;

//         // Option 1: Include current string (if we have enough zeros and ones left)
//         int include = 0;
//         if (count[i][0] <= m && count[i][1] <= n) {
//             include = 1 + solve(count, m - count[i][0], n - count[i][1], i - 1);
//         }

//         // Option 2: Exclude current string
//         int exclude = solve(count, m, n, i - 1);

//         // Return the maximum of including or excluding the current string
//         return Math.max(include, exclude);
//     }
// }

/**
Approach-1 (Recursion and Memoization)
T.C : O(L * m * n)
    where L = number of strings, m = max number of 0's allowed, n = max number of 1's allowed
    For each index (L), we compute at most m*n states once due to memoization.
S.C : O(L * m * n) for the DP cache + O(L) recursion stack
*/
class Solution {
    public int findMaxForm(String[] strs, int m, int n) {
        int len = strs.length;
        int[][] count = new int[len][2];

        // Step 1: Count zeros and ones for each string
        for (int i = 0; i < len; i++) {
            int zeros = 0, ones = 0;
            for (char ch : strs[i].toCharArray()) {
                if (ch == '0')
                    zeros++;
                else
                    ones++;
            }
            count[i][0] = zeros;
            count[i][1] = ones;
        }

        // Step 2: Initialize memoization table
        // dp[i][m][n] = max number of strings we can form using strings[0...i]
        // with at most m zeros and n ones
        int[][][] dp = new int[len][m + 1][n + 1];

        // Fill dp with -1 to indicate uncomputed states
        for (int i = 0; i < len; i++) {
            for (int j = 0; j <= m; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }

        // Step 3: Solve recursively with memoization
        return solve(count, m, n, len - 1, dp);
    }

    private int solve(int[][] count, int m, int n, int i, int[][][] dp) {
        // Base Case: No strings left or no capacity left
        if (i < 0)
            return 0;

        // If already computed, return from memo
        if (dp[i][m][n] != -1)
            return dp[i][m][n];

        // Option 1: Exclude current string
        int exclude = solve(count, m, n, i - 1, dp);

        // Option 2: Include current string (only if it fits)
        int include = 0;
        if (count[i][0] <= m && count[i][1] <= n) {
            include = 1 + solve(count, m - count[i][0], n - count[i][1], i - 1, dp);
        }

        // Store and return the best result
        return dp[i][m][n] = Math.max(include, exclude);
    }
}
