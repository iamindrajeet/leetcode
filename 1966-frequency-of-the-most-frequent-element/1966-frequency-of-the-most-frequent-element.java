/*
Approach: Sliding Window
We use a sliding window to find the longest subarray (window) where we can make all elements equal 
to the maximum element (nums[r]) using at most `k` operations.

Time Complexity: O(n log n) - due to sorting
Space Complexity: O(1) - no extra space apart from variables
*/
class Solution {
    public int maxFrequency(int[] nums, int k) {
        int n = nums.length;

        // Step 1: Sort the array to bring similar values closer
        Arrays.sort(nums);

        int result = 0;
        int l = 0;             // Left pointer of the window
        long currSum = 0;      // Sum of elements within the window

        // Step 2: Use sliding window technique
        for (int r = 0; r < n; r++) {
            int target = nums[r];   // Try to make all elements in window equal to this
            currSum += nums[r];     // Add the right element to window sum

            // Calculate required sum if all elements were equal to nums[r]
            long requiredWindowSum = (long)(r - l + 1) * target;

            // If operations needed > k, shrink the window from the left
            while (requiredWindowSum - currSum > k) {
                currSum -= nums[l];
                l++;
                requiredWindowSum = (long)(r - l + 1) * target;
            }

            // Update result with the maximum window size found
            result = Math.max(result, r - l + 1);
        }

        return result;
    }
}
