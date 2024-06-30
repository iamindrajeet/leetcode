/*

Intuition and Logic:
The goal is to find the length of the longest increasing subsequence (LIS) in an array using dynamic programming with memoization.

Steps:
Dynamic Programming with Memoization:

Use a 2D memo array where memo[idx][prevIdx + 1] stores the LIS length starting from index idx with prevIdx as the previous element's index in the sequence.
prevIdx + 1 shifts the index to handle the initial case where prevIdx is -1.
Recursive Function solve:

Base Case: If idx is beyond the array length, return 0.
Memo Check: If memo[idx][prevIdx + 1] is already computed, return it.
Include Current Element: If the current element can be part of the LIS (i.e., it's greater than the previous element), compute the LIS length by including it.
Exclude Current Element: Compute the LIS length by skipping the current element.
Store Result: Store the maximum of including or excluding the current element in the memo array.
Result:

Start the recursion with the first element and no previous element (prevIdx = -1).
Return the result stored in the memo array.

T.C = O(n^2)
S.C = O(n^2)
*/

class Solution {
    public int lengthOfLIS(int[] nums) {
        int len = nums.length;
        // Initialize memoization array with -1
        int[][] memo = new int[len + 1][len + 1];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        // Start the recursion with the first index and prevIdx as -1 (no previous element)
        return solve(0, -1, nums, memo);
    }

    private int solve(int idx, int prevIdx, int[] nums, int[][] memo) {
        if (idx == nums.length) {
            return 0;
        }

        // Use prevIdx + 1 to handle the case when prevIdx is -1
        if (memo[idx][prevIdx + 1] != -1) {
            return memo[idx][prevIdx + 1];
        }

        int taken = 0;
        // Check if we can take the current element
        if (prevIdx == -1 || nums[idx] > nums[prevIdx]) {
            taken = 1 + solve(idx + 1, idx, nums, memo);
        }
        
        // Option to skip the current element
        int notTaken = solve(idx + 1, prevIdx, nums, memo);

        // Store the result in memo array
        memo[idx][prevIdx + 1] = Math.max(taken, notTaken);
        return memo[idx][prevIdx + 1];
    }
}
