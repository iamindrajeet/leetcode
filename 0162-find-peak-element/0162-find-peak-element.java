class Solution {
    public int findPeakElement(int[] nums) {
        int len = nums.length;

        // If the array has only one element, that element is the peak.
        if (len == 1)
            return 0;

        // Check if the first element is the peak.
        if (nums[0] > nums[1])
            return 0;

        // Check if the last element is the peak.
        if (nums[len - 1] > nums[len - 2])
            return len - 1;

        // Initialize the binary search boundaries.
        int low = 1, high = len - 2;

        // Perform binary search.
        while (low <= high) {
            // Find the middle index.
            int mid = low + (high - low) / 2;

            // If the middle element is greater than its neighbors, it is the peak.
            if (nums[mid] > nums[mid - 1] && nums[mid] > nums[mid + 1])
                return mid;
            
            // If the middle element is greater than the previous element,
            // move to the right half.
            else if (nums[mid] > nums[mid - 1])
                low = mid + 1;
            
            // Otherwise, move to the left half.
            else
                high = mid - 1;
        }

        // Return -1 if no peak is found.
        return -1;
    }
}

/*
 * Time Complexity (TC):
 * The binary search approach divides the search space in half at each step,
 * making the time complexity O(log n), where n is the number of elements
 * in the input array.
 *
 * Space Complexity (SC):
 * The space complexity is O(1) since we are only using a few extra variables
 * for the binary search process and not using any additional space proportional
 * to the input size.
 */
