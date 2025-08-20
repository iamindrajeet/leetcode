/**
Time Complexity : O(n)
Space Complexity : O(1)
*/
class Solution {
    public int trap(int[] height) {
        int leftMax = 0, rightMax = 0; // To store max height to the left and right of each index
        int totalWater = 0; // Final water trapped
        int left = 0, right = height.length - 1; // Two pointers starting from both ends

        // Loop until the two pointers meet
        while (left <= right) {
            // Process the side with the smaller height
            if (height[left] <= height[right]) {
                // If current height is less than leftMax, water can be trapped
                if (height[left] < leftMax) {
                    totalWater += leftMax - height[left];
                } else {
                    // Update leftMax if current bar is taller
                    leftMax = height[left];
                }
                left++; // Move left pointer inward
            } else {
                // If current height is less than rightMax, water can be trapped
                if (height[right] < rightMax) {
                    totalWater += rightMax - height[right];
                } else {
                    // Update rightMax if current bar is taller
                    rightMax = height[right];
                }
                right--; // Move right pointer inward (fixed from right++)
            }
        }

        return totalWater;
    }
}
