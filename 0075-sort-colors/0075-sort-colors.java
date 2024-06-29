/*
Intuition:

The problem is essentially about sorting an array containing only three distinct values: 0, 1, and 2. The Dutch National Flag problem provides an efficient way to sort such an array using three pointers. The intuition is to partition the array into three sections:

All 0s to the left.
All 1s in the middle.
All 2s to the right.
Approach
We use three pointers: low, mid, and high.

• low keeps track of the boundary for 0s.
• mid traverses through the array and classifies elements into one of the three categories (0, 1, or 2).
• high keeps track of the boundary for 2s.

The algorithm proceeds as follows:

Initialize low and mid to the start of the array, and high to the end of the array.
Traverse the array with the mid pointer until it surpasses high:
• If nums[mid] is 0, swap it with nums[low], then increment both low and mid.
• If nums[mid] is 1, just move mid to the next element.
• If nums[mid] is 2, swap it with nums[high], then decrement high without incrementing mid (as the swapped element at mid needs to be processed).

Complexity
Time complexity:
The algorithm makes a single pass through the array, meaning each element is processed at most once. Therefore, the time complexity is:
O(n)
where n is the number of elements in the array.

Space complexity:
The algorithm uses only a constant amount of extra space for the pointers and temporary variables for swapping. Therefore, the space complexity is:
O(1)

*/

class Solution {
    public void sortColors(int[] nums) {
        int low = 0, mid = 0, high = nums.length - 1;
        while(mid <= high){
            if(nums[mid] == 0){
                swap(nums, low, mid);
                low++;
                mid++;
            }
            else if(nums[mid] == 1)
                mid++;
            else{
                swap(nums, mid, high);
                high--;
            }
        }
    }

    private void swap(int[] nums, int idx1, int idx2){
        int temp = nums[idx1];
        nums[idx1] = nums[idx2];
        nums[idx2] = temp;
    }
}