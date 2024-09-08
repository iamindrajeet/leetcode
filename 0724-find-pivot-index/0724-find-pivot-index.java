/*
- Time Complexity (TC):
The time complexity is O(n), where n is the length of the array. This is because we iterate through the array twice: first to calculate the total sum and then to find the pivot index.

- Space Complexity (SC):
The space complexity is O(1) because we are only using a constant amount of extra space (for totalSum and leftSum), regardless of the input size.
*/
class Solution {
    public int pivotIndex(int[] nums) {
        // Initialize totalSum to store the sum of all elements in the array
        int totalSum = 0;
        // Initialize leftSum to store the cumulative sum of elements to the left of the current index
        int leftSum = 0;
        // Calculate the total sum of the array
        for (int num : nums) {
            totalSum += num;
        }
        // Traverse through the array to find the pivot index
        for (int i = 0; i < nums.length; i++) {
            // Update totalSum to represent the sum of elements to the right of index i
            totalSum -= nums[i];
            // If leftSum is equal to totalSum, the current index is the pivot index
            if (leftSum == totalSum) {
                return i;
            }   
            // Update leftSum by adding the current element
            leftSum += nums[i];
        }       
        // If no pivot index is found, return -1
        return -1;
    }
}