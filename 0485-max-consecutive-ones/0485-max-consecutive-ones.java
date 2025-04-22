class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        // Variable to store the maximum number of consecutive 1s found so far
        int maxConsecutiveOnes = 0;
        // Variable to keep track of the current streak of 1s
        int countOfOnes = 0;

        // Iterate through the array
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                // If current element is 1, increment the current count
                countOfOnes++;
                // Update maxConsecutiveOnes if current count is greater
                maxConsecutiveOnes = Math.max(maxConsecutiveOnes, countOfOnes);
            } else {
                // If current element is 0, reset the current count
                countOfOnes = 0;
            }
        }

        // Return the maximum number of consecutive 1s found
        return maxConsecutiveOnes;
    }
}
