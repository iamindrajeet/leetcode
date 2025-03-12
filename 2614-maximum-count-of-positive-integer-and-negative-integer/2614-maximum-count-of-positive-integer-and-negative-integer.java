/**
T.C - (log n)
S.C - O(1)
*/
class Solution {
    public int maximumCount(int[] nums) {
        // Count the number of positive and negative integers using binary search
        int positiveCount = nums.length - upperBound(nums);
        int negativeCount = lowerBound(nums);

        // Return the maximum of the two counts
        return Math.max(positiveCount, negativeCount);
    }

    /**
     * Finds the index of the first non-negative number (0 or positive).
     * This effectively gives the count of negative numbers in the sorted array.
     *
     * @param nums The sorted integer array
     * @return The index of the first non-negative number
     */
    private int lowerBound(int[] nums) {
        int start = 0, end = nums.length - 1;
        int index = nums.length; // Default index if no non-negative number is found

        while (start <= end) {
            int mid = (start + end) / 2;
            if (nums[mid] < 0) {
                // Move right to find the first non-negative number
                start = mid + 1;
            } else {
                // Mid is non-negative, update index and move left
                end = mid - 1;
                index = mid;
            }
        }
        return index;
    }

    /**
     * Finds the index of the first positive number (>0).
     * This effectively gives the count of zero and negative numbers.
     *
     * @param nums The sorted integer array
     * @return The index of the first positive number
     */
    private int upperBound(int[] nums) {
        int start = 0, end = nums.length - 1;
        int index = nums.length; // Default index if no positive number is found

        while (start <= end) {
            int mid = (start + end) / 2;
            if (nums[mid] <= 0) {
                // Move right to find the first positive number
                start = mid + 1;
            } else {
                // Mid is positive, update index and move left
                end = mid - 1;
                index = mid;
            }
        }
        return index;
    }
}
