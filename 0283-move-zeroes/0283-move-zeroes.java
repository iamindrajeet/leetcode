class Solution {
    public void moveZeroes(int[] nums) {
        int left = 0, right = 0, len = nums.length;

        // Use two pointers: left tracks the position to place the next non-zero element,
        // right traverses the array
        while (right < len) {
            if (nums[right] == 0) {
                // If current element is zero, just move the right pointer
                right++;
            } else {
                // If current element is non-zero, swap it with the element at left pointer
                // This effectively moves the non-zero to the front
                swap(nums, left, right);
                // Move both pointers forward
                left++;
                right++;
            }
        }
    }

    // Utility method to swap two elements in the array
    private void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }
}
