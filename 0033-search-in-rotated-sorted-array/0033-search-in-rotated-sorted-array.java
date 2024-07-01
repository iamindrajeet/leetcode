class Solution {
    public int search(int[] nums, int target) {
        int len = nums.length;
        int left = 0, right = len - 1;

        // Perform binary search
        while (left <= right) {
            // Calculate the middle index to avoid overflow
            int mid = left + (right - left) / 2;

            // If the middle element is the target, return its index
            if (nums[mid] == target)
                return mid;

            // Check if the left half of the array is sorted
            if (nums[left] <= nums[mid]) {
                // If the target is in the sorted left half, adjust the right boundary
                if (nums[left] <= target && target <= nums[mid])
                    right = mid - 1;
                // Otherwise, adjust the left boundary
                else
                    left = mid + 1;
            }
            // If the right half of the array is sorted
            else {
                // If the target is in the sorted right half, adjust the left boundary
                if (nums[mid] <= target && target <= nums[right])
                    left = mid + 1;
                // Otherwise, adjust the right boundary
                else 
                    right = mid - 1;
            }
        }

        // If the target is not found, return -1
        return -1;
    }
}
