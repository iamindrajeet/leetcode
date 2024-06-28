/*

Approach:
First, it calculates the effective rotation amount by taking the modulus of k with the length of the array, ensuring that k is within the range of the array length.
Then, it calls the reverse function three times:
First, it reverses the entire array, effectively placing the last k elements at the start of the array.
Second, it reverses the first k elements, moving them to the end of the array.
Finally, it reverses the remaining elements, restoring the original order of the array with the elements rotated to the right by k steps.


Complexity
Time complexity: O(n)
Space complexity: O(1)

*/

class Solution {
    public void rotate(int[] nums, int k) {
        int len = nums.length;
        k = k % len;
        // reversing entire array
        reverseArray(nums, 0, len - 1);
        // reversing array from index 0 to k - 1
        reverseArray(nums, 0, k - 1);
        //reversing array from index k - 1 to len - 1
        reverseArray(nums, k, len- 1);
    }

    public void reverseArray(int[] nums, int left, int right){
        while(left <= right){
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left++;
            right--;
        }
    }
}