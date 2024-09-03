/**
 * Time Complexity: O(n)
 * - We traverse the array once, making the time complexity O(n), where n is the
 * number of elements in the array.
 *
 * Space Complexity: O(1)
 * - We are using a constant amount of extra space (only integer and double
 * variables),
 * so the space complexity is O(1).
 */
class Solution {
    public double findMaxAverage(int[] nums, int k) {
        int left = 0;
        double sum = 0; // Initialize sum to 0 since we will accumulate the first k elements.
        double maxSum = Integer.MIN_VALUE; // Use this to track the maximum sum of any k-length subarray.

        // Traverse the array to find the maximum sum of any subarray of length k.
        for (int right = 0; right < nums.length; right++) {
            sum += nums[right]; // Add the current element to the sum.

            // Check if the current window has exactly k elements.
            if ((right - left + 1) == k) {
                // Update the maxSum if the current sum is greater.
                maxSum = Math.max(maxSum, sum);
                // Slide the window by subtracting the element at the left pointer.
                sum -= nums[left];
                left++; // Move the left pointer to the right to maintain the window size of k.
            }
        }

        // Return the maximum average by dividing maxSum by k.
        return maxSum / k;
    }
}
