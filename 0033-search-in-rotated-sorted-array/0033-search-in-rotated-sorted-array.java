class Solution {
    public int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            // Case 1: If the mid element is the target, return its index
            if (nums[mid] == target)
                return mid;

            // Case 2: Check if the left half (from left to mid) is sorted
            if (nums[left] <= nums[mid]) {
                // Now check if the target lies within this sorted left half
                if (nums[left] <= target && target < nums[mid]) {
                    // Target is in left half, eliminate right half
                    right = mid - 1;
                } else {
                    // Target is not in left half, eliminate left half
                    left = mid + 1;
                }
            } 
            // Case 3: Otherwise, the right half (from mid to right) must be sorted
            else {
                // Check if the target lies within the sorted right half
                if (nums[mid] < target && target <= nums[right]) {
                    // Target is in right half, eliminate left half
                    left = mid + 1;
                } else {
                    // Target is not in right half, eliminate right half
                    right = mid - 1;
                }
            }
        }

        // If we reach here, target is not found
        return -1;
    }
}
