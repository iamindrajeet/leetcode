class Solution {
    public int singleNonDuplicate(int[] nums) {
        int len = nums.length;

        // If the array has only one element, return that element.
        if (len == 1)
            return nums[0];

        // If the first element is unique (not equal to the second), return it.
        if (nums[0] != nums[1])
            return nums[0];

        // If the last element is unique (not equal to the second last), return it.
        if (nums[len - 1] != nums[len - 2])
            return nums[len - 1];

        // Initialize binary search bounds
        int low = 1, high = len - 2;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            // If nums[mid] is not equal to its neighbors, it's the unique element.
            if (nums[mid] != nums[mid - 1] && nums[mid] != nums[mid + 1])
                return nums[mid];

            // Determine which side to continue the search on.
            // If mid is odd and nums[mid] == nums[mid - 1], or
            // if mid is even and nums[mid] == nums[mid + 1],
            // then the unique element is on the right half.
            if ((mid % 2 == 1 && nums[mid] == nums[mid - 1]) || 
                (mid % 2 == 0 && nums[mid] == nums[mid + 1])) {
                low = mid + 1;
            } else {
                // Otherwise, the unique element is on the left half.
                high = mid - 1;
            }
        }

        // Should not reach here for a valid input.
        return -1;
    }
}
