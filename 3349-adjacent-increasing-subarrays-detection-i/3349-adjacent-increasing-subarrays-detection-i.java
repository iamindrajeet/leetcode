/**
Approach 1 : Brute force
Time Complexity = O(n * k)
Space Complexity = O(1)
*/
class Solution {
    public boolean hasIncreasingSubarrays(List<Integer> nums, int k) {
        // Iterate up to nums.size() - 2*k because we need TWO subarrays of size k
        for (int idx = 0; idx <= nums.size() - 2 * k; idx++) {
            
            // Check first subarray: nums[idx ... idx + k - 1]
            boolean isSubArray1Increasing = isIncreasing(nums, idx, idx + k - 1);
            
            // Check second subarray: nums[idx + k ... idx + 2*k - 1]
            boolean isSubArray2Increasing = isIncreasing(nums, idx + k, idx + 2 * k - 1);
            
            // If both subarrays are strictly increasing, we found our answer
            if (isSubArray1Increasing && isSubArray2Increasing)
                return true;
        }
        return false;  // No such pair found
    }
    private boolean isIncreasing(List<Integer> nums, int start, int end) {
        // Compare each element with its next one
        for (int i = start; i < end; i++) {
            if (nums.get(i + 1) <= nums.get(i))  // Not strictly increasing
                return false;
        }
        return true;
    }
}
