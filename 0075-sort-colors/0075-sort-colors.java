/**
Approach - Dutch National Flag Algorithm
*/
class Solution {
    public void sortColors(int[] nums) {
        int low = 0, mid = 0, high = nums.length - 1;
        
        // Traverse the array
        while (mid <= high) {
            if (nums[mid] == 0) {
                // If the element is 0, swap it with the element at low index
                swap(nums, low, mid);
                low++;
                mid++;
            } else if (nums[mid] == 1) {
                // If the element is 1, just move mid pointer
                mid++;
            } else {
                // If the element is 2, swap it with the element at high index
                swap(nums, mid, high);
                high--;
            }
        }
    }
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
