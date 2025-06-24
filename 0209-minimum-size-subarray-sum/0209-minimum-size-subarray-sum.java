/*
T.C - O(n)
S.C - O(1)
*/

class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int left = 0, right = 0, len = nums.length;
        int currSum = 0, minLen = Integer.MAX_VALUE;

        // Use sliding window technique
        while (right < len) {
            // Add current element to the current sum
            currSum += nums[right];

            // While current sum is greater than or equal to target,
            // try to shrink the window from the left to minimize length
            while (currSum >= target) {
                // Update the minimum length of subarray found so far
                minLen = Math.min(minLen, right - left + 1);
                
                // Subtract the element at 'left' and move the window forward
                currSum -= nums[left];
                left++;
            }

            // Move the right boundary of the window forward
            right++;
        }

        // If no valid subarray was found, return 0
        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }
}
