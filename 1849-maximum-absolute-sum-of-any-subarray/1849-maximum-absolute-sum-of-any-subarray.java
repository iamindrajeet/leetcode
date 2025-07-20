/**
✅ Explanation
Why two passes?

The maximum absolute subarray sum could come from:

A very large positive sum (hence we calculate maxSubSum).

Or a very large negative sum (whose absolute value is large, hence we calculate minSubSum).

Final result
max(|minSubSum|, maxSubSum) covers both cases.

⏳ Time Complexity (TC)
O(n) → We iterate through the array twice (each pass is O(n)).

\U0001f4be Space Complexity (SC)
O(1) → We only use a few extra variables (currSubSum, maxSubSum, minSubSum).

*/
class Solution {
    public int maxAbsoluteSum(int[] nums) {
        int n = nums.length;
        
        // Initialize current subarray sum and track max and min subarray sums
        int currSubSum = nums[0], maxSubSum = nums[0];
        int minSubSum = nums[0];
        
        // First pass: Find the maximum subarray sum (Kadane's algorithm)
        for (int i = 1; i < n; i++) {
            // Either extend the previous subarray or start a new one
            currSubSum = Math.max(nums[i], nums[i] + currSubSum);
            maxSubSum = Math.max(maxSubSum, currSubSum);
        }
        
        // Reset current sum for finding minimum subarray sum
        currSubSum = nums[0];
        
        // Second pass: Find the minimum subarray sum (similar Kadane but with Math.min)
        for (int i = 1; i < n; i++) {
            // Either extend the previous subarray or start a new one
            currSubSum = Math.min(nums[i], nums[i] + currSubSum);
            minSubSum = Math.min(minSubSum, currSubSum);
        }
        
        // The maximum absolute sum is the larger of |minSubSum| and maxSubSum
        return Math.max(Math.abs(minSubSum), maxSubSum);
    }
}
