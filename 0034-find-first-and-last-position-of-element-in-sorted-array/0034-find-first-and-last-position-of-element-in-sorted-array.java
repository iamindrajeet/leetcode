/*
Modified Binary Search:

1.Initialize two variables, first and last, to -1.
2.Perform a binary search to find the target element.
3.When you find the target element, update first to the current index and continue searching for the first occurrence in the left subarray.
4.Similarly, update last to the current index and continue searching for the last occurrence in the right subarray.
5.Continue until the binary search terminates.
6.Return first and last.

Complexity:
Time Complexity: O(logn) + O(logn) = O(logn).

Space Complexity: O(1) - Constant extra space is used.

*/

class Solution {
    public int[] searchRange(int[] nums, int target) {
        // Find the first and last positions of the target in the array
        int first = findFirst(nums, target);
        int last = findLast(nums, target);
        // Return the result as an array
        return new int[]{first, last};
    }

    private int findFirst(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        int first = -1; // Initialize the first position as -1 (target not found)
        
        // Perform binary search for the first occurrence
        while (left <= right) {
            int mid = left + (right - left) / 2;
            // If the target is found, update first and move left to find the first occurrence
            if (nums[mid] == target) {
                first = mid;
                right = mid - 1;
            } 
            // If the middle element is less than the target, search in the right half
            else if (nums[mid] < target) {
                left = mid + 1;
            } 
            // If the middle element is greater than the target, search in the left half
            else {
                right = mid - 1;
            }
        }
        return first;
    }

    private int findLast(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        int last = -1; // Initialize the last position as -1 (target not found)
        
        // Perform binary search for the last occurrence
        while (left <= right) {
            int mid = left + (right - left) / 2;
            // If the target is found, update last and move right to find the last occurrence
            if (nums[mid] == target) {
                last = mid;
                left = mid + 1;
            } 
            // If the middle element is less than the target, search in the right half
            else if (nums[mid] < target) {
                left = mid + 1;
            } 
            // If the middle element is greater than the target, search in the left half
            else {
                right = mid - 1;
            }
        }
        return last;
    }
}
