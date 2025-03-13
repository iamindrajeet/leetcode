/**
T.C - O(N)
S.C - O(1)
*/
class Solution {
    public int[] applyOperations(int[] nums) {
        // Step 1: Merge adjacent equal elements and replace the second occurrence with 0
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == nums[i + 1] && nums[i] != 0) {
                nums[i] = nums[i] * 2; // Double the value of the first element
                nums[i + 1] = 0; // Set the second element to 0
            }
        }

        // Step 2: Shift all non-zero elements to the left
        int nonZeroIndex = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[nonZeroIndex++] = nums[i]; // Move non-zero element to its correct position
            }
        }

        // Step 3: Fill the remaining positions with zeros
        while (nonZeroIndex < nums.length) {
            nums[nonZeroIndex++] = 0;
        }

        // Return the modified array
        return nums;
    }
}
