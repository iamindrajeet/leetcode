/*

Intuition and Logic : 
- The goal is to calculate how much water can be trapped between the bars based on their heights.
- Water can only be trapped at a particular index if there are higher bars on both its left and right sides.
- So, To efficiently determine the maximum elevation to the left and right of each bar, we'll use two arrays:
    - left[i]: This array will store the maximum height encountered from the start up to the current index i.
    - right[i]: This array will store the maximum height encountered from the end up to the current index i.

- Filling left[] Array:
    - Initialize left[0] with the height of the first bar.
    - For each subsequent index i, left[i] is computed as the maximum of left[i-1] and the current height height[i]. This ensures that left[i] always holds the maximum height encountered from the start up to i.

- Filling right[] Array:
    - Initialize right[n-1] with the height of the last bar.
    - For each index i from n-2 down to 0, right[i] is computed as the maximum of right[i+1] and the current height height[i]. This ensures that right[i] always holds the maximum height encountered from i to the end.

- Calculating Trapped Water:
    - Once left[] and right[] arrays are populated, iterate through the bars.
    - For each bar at index i, calculate the trapped water above it and store it in trappedWater using:
        trappedWater += (min(left[i], right[i]) - height[i])
    - This formula represents the maximum water that can be trapped above the bar at i, considering the minimum of the highest bars on its left and right minus its own height.

- Final Answer:
    - trappedWater holds the value of trapped after after rain.


T.C - O(n)
S.C - O(n)
*/
class Solution {
    public int trap(int[] height) {
        int n = height.length;
        
        // Edge case: If the array is empty or has less than 3 elements, no water can be trapped.
        if (n < 3) return 0;

        // Arrays to store the maximum height to the left and right of each index
        int[] left = new int[n];
        int[] right = new int[n];

        // Variable to store the total amount of trapped water
        int trappedWater = 0;
        
        // Initialize the left array
        // left[i] will store the maximum height encountered from the left up to index i
        left[0] = height[0];
        for(int i = 1; i < n; i++) {
            left[i] = Math.max(left[i - 1], height[i]);
        }
        
        // Initialize the right array
        // right[i] will store the maximum height encountered from the right up to index i
        right[n - 1] = height[n - 1];
        for(int i = n - 2; i >= 0; i--) {
            right[i] = Math.max(right[i + 1], height[i]);
        }

        // Calculate the trapped water at each index
        // The water above each index is determined by the minimum of the maximum heights to its left and right minus the height at that index
        for(int i = 0; i < n; i++) {
            trappedWater += Math.min(left[i], right[i]) - height[i];
        }

        // Return the total amount of trapped water
        return trappedWater;
    }
}
