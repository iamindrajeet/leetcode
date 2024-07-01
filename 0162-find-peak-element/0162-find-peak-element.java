class Solution {
    public int findPeakElement(int[] nums) {
        int len = nums.length;

        // If the array has only one element, return its index
        if (len == 1)
            return 0;

        // If the first element is greater than the second, return the index 0
        if (nums[0] > nums[1])
            return 0;

        // If the last element is greater than the second last, return the index len - 1
        if (nums[len - 1] > nums[len - 2])
            return len - 1;

        // Initialize the binary search bounds
        int left = 1, right = len - 2;

        // Perform binary search
        while (left <= right) {
            // Find the middle index to avoid overflow
            int mid = left + (right - left) / 2;

            // Check if the mid element is greater than its neighbors
            if (nums[mid] > nums[mid - 1] && nums[mid] > nums[mid + 1])
                return mid;

            // If the mid element is greater than the element to its left,
            // then the peak must be in the right half
            else if (nums[mid] > nums[mid - 1])
                left = mid + 1;

            // If the mid element is not greater than the element to its left,
            // then the peak must be in the left half
            else
                right = mid - 1;
        }

        // If no peak is found, return -1 (although the problem guarantees there will be
        // a peak)
        return -1;
    }
}
