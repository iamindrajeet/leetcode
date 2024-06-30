/*
Approach 1: DP (Memoization - Top Down Approach)

Explanation: The memoization solution improves the recursive solution by introducing memoization, which avoids redundant calculations. We use a memo array to store the already computed results for each step n. Before making a recursive call, we check if the result for the given n exists in the memo. If it does, we return the stored value; otherwise, we compute the result recursively and store it in the memo for future reference.

Complexity : Time: O(n) ; Space: O(n)
*/
class Solution {
    public int climbStairs(int n) {
        int[] memo = new int[n + 1];
        Arrays.fill(memo, -1);
        return climbStairsHelper(n, memo);
    }

    private int climbStairsHelper(int n, int[] memo) {
        if (n == 1) return 1;
        if (n == 2) return 2;

        if (memo[n] != -1) return memo[n];

        memo[n] = climbStairsHelper(n - 1, memo) + climbStairsHelper(n - 2, memo);
        return memo[n];
    }
}

/*
Approach 2: DP(Tabulation - Bottom Up Approach)
Explanation: The tabulation solution eliminates recursion and uses a bottom-up approach to solve the problem iteratively. It creates a DP table (memo) of size n+1 to store the number of ways to reach each step. The base cases (0 and 1 steps) are initialized to 1 since there is only one way to reach them. Then, it iterates from 2 to n, filling in the memo table by summing up the values for the previous two steps. Finally, it returns the value in the last cell of the memo table, which represents the total number of ways to reach the top.

*/

// class Solution {
//     public int climbStairs(int n) {
//         int[] memo = new int[n + 1];
//         memo[0] = memo[1] = 1;

//         for(int i = 2; i <= n; i++){
//             memo[i] = memo[i - 1] + memo[i - 2];
//         }
//         return memo[n];
//     }
// }

/*
Approach 4: Space Optimization
Explanation: The space-optimized solution further reduces the space complexity by using only two variables (prev and curr) instead of an entire DP table. It initializes prev and curr to 1 since there is only one way to reach the base cases (0 and 1 steps). Then, in each iteration, it updates prev and curr by shifting their values. curr becomes the sum of the previous two values, and prev stores the previous value of curr.

*/

// class Solution {
//     public int climbStairs(int n) {
//         if(n == 0 || n == 1)
//             return 1;
//         int prev = 1, curr = 1;
//         for(int i = 2; i <= n; i++){
//             int temp = curr;
//             curr = prev + curr;
//             prev = curr;
//         }
//         return curr;
//     }
// }






