/**
    * Time Complexity: O(n)
    * - We traverse the array once, making the overall time complexity O(n), 
    *   where n is the number of elements in the array.
    *
    * Space Complexity: O(1)
    * - We are using a constant amount of extra space (only integer variable k), 
    *   so the space complexity is O(1).
*/
class Solution {
    public void moveZeroes(int[] nums) {
        int k = 0; // Pointer to place non-zero elements.
        
        // Traverse the array to move non-zero elements to the front.
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) { // Check if the current element is non-zero.
                nums[k++] = nums[i]; // Place the non-zero element at index k and increment k.
            }
        }

        // Fill the remaining positions with zeros.
        while (k < nums.length) {
            nums[k++] = 0; // Place a zero at index k and increment k.
        }
    }
}
