class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int n = nums.length;
        int left = 0, right = 0; // Pointers to track the start and end of consecutive 1s
        int maxConsecutiveOnes = 0; // Variable to store the result

        while (left < n && right < n) {
            // Skip all 0s to find the start of a block of 1s
            while (left < n && nums[left] == 0)
                left++;

            // Set right pointer to the beginning of 1s block
            right = left;

            // Move right pointer forward as long as we see 1s
            while (right < n && nums[right] == 1)
                right++;

            // Update the maximum length of consecutive 1s found so far
            maxConsecutiveOnes = Math.max(maxConsecutiveOnes, right - left);

            // Move left to the next index after the last 1 to continue the search
            left = right;
        }

        return maxConsecutiveOnes;
    }
}
