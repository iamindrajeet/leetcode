/*

Approach 1 : DP(Memoization - Top Down Approach)

Intuition
The "House Robber" problem is about finding the maximum amount of money you can rob from a series of houses without robbing two consecutive houses. This problem can be approached using dynamic programming to efficiently compute the result by breaking it down into simpler subproblems and using memoization to avoid redundant calculations.

Explanation
Base Cases:

1. If there are no houses (n == 0), the maximum money you can rob is 0.
2. If there is only one house (n == 1), the maximum money you can rob is the amount in that house (nums[0]).
Memoization:

We use an array memo to store the results of subproblems to avoid redundant calculations. Initially, we fill this array with -1 to indicate that a value hasn't been computed yet.

Recursive Helper Function:

1. The helper function helper(n, nums, memo) computes the maximum money that can be robbed from the first n+1 houses.
2. Base Case for Helper Function: If n is less than 0, it means there are no houses to rob, so the result is 0.
3.vMemoization Check: If the result for n houses is already computed (memo[n] != -1), return it.

Recursive Calculation:
1. Take the current house: If you rob the current house (nums[n]), you cannot rob the previous house, so you add nums[n] to the result of the helper function for the houses before the previous one (helper(n - 2, nums, memo)).
2. Skip the current house: If you skip the current house, the result is the same as the helper function for the previous house (helper(n - 1, nums, memo)).
3. Store and Return the Result: Store the maximum of the two options (taking or skipping the current house) in the memo array and return it.

Final Result:

The rob function calls the helper function starting from the last house (n-1) and returns the computed result, which is the maximum money that can be robbed from all the houses.
*/

class Solution {
    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;
        if (n == 1) return nums[0];
        int[] memo = new int[n];
        Arrays.fill(memo, -1);
        return helper(n - 1, nums, memo);
    }

    private int helper(int n, int[] nums, int[] memo) {
        if (n < 0) 
            return 0;
        if (memo[n] != -1) return memo[n];
        
        int take = nums[n] + helper(n - 2, nums, memo);
        int skip = helper(n - 1, nums, memo);

        memo[n] = Math.max(take, skip);

        return memo[n];
    }
}


/*
Approach 2: DP(Tabulation - Bottom Up Appraoch)

Intuition
The problem is to maximize the amount of money you can rob from houses lined up in a row, without robbing two consecutive houses. We use dynamic programming to store the maximum amount robbed up to each house in an array.

Explanation
Edge Cases:

If no houses (n == 0), return 0.
If one house (n == 1), return the money in that house (nums[0]).
Dynamic Programming Array:

Create a memo array to store the maximum money robbed up to each house. memo[i] represents the maximum money that can be robbed from the first i houses.
Initialization:

memo[1] is initialized to nums[0] because if there's only one house, the best you can do is rob that house.
Iterative Calculation:

For each house from the second to the last (i ranges from 2 to n):
Take the current house: Add the money in the current house (nums[i - 1]) to the maximum money robbed up to two houses before (memo[i - 2]).
Skip the current house: The maximum money robbed up to the previous house (memo[i - 1]).
Store the maximum of these two options in memo[i].
Final Result:

The answer is the value stored in memo[n], which represents the maximum money that can be robbed from all n houses.

*/

// class Solution {
//     public int rob(int[] nums) {
//         int n = nums.length;
//         if (n == 0) 
//             return 0;
//         if (n == 1) 
//             return nums[0];

//         int[] memo = new int[n + 1];
//         memo[1] = nums[0];

//         for (int i = 2; i <= n; i++) {
//             int take = nums[i - 1] + memo[i - 2];
//             int skip = memo[i - 1];
//             memo[i] = Math.max(take, skip);
//         }

//         return memo[n];
//     }
// }
