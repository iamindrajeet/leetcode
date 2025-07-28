class Solution {
    public boolean search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            // Case 1: Found the target
            if (nums[mid] == target)
                return true;

            // Case 2: Duplicates on both sides – can't decide which half is sorted
            if (nums[left] == nums[mid] && nums[mid] == nums[right]) {
                left++;
                right--;
                continue;
            }

            // Case 3: Left half is sorted
            if (nums[left] <= nums[mid]) {
                // Check if target is in this sorted left half
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } 
            // Case 4: Right half is sorted
            else {
                // Check if target is in this sorted right half
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }

        // Target not found
        return false;
    }
}
