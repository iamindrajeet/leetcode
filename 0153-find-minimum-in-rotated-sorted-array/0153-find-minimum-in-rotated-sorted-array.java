/*
Intuition:
The problem asks to find the minimum element in a rotated sorted array. The idea is to use binary search to efficiently find the minimum element. The key observation is that the minimum element will always be on the side where the rotation occurred. By comparing the middle element with the left element, we can determine which side to search.

Approach:
The approach is to use binary search to iteratively narrow down the search space. We maintain two pointers, left and right, representing the current search range. In each iteration, we calculate the middle index (mid) and compare the value at mid with the value at left.

If nums[left] <= nums[mid], it means the left half is sorted, so the minimum element is on the right side of mid, so we update left = mid + 1.
else, it means the right half is sorted, so the minimum element is on the left side of mid or could be mid itself, so we update right = mid - 1.

We continue this process until the search space is exhausted, and at that point, we have found the minimum element.

Complexity:
Time complexity: O(log n) - Binary search is used, which halves the search space in each iteration.
Space complexity: O(1) - Constant space is used as we only have a few variables.
*/
class Solution {
    public int findMin(int[] nums) {
        int left = 0, right = nums.length - 1;
        int minElement = Integer.MAX_VALUE; // Initialize minElement to the largest possible value

        // Perform binary search to find the minimum element
        while (left <= right) {
            int mid = left + (right - left) / 2;

            // If the left half is sorted
            if (nums[left] <= nums[mid]) {
                // Update minElement with the minimum value between current minElement and nums[left]
                minElement = Math.min(minElement, nums[left]);
                // Move the left boundary to mid + 1 to search the unsorted right half
                left = mid + 1;
            }
            // If the right half is sorted
            else {
                // Update minElement with the minimum value between current minElement and nums[mid]
                minElement = Math.min(minElement, nums[mid]);
                // Move the right boundary to mid - 1 to search the unsorted left half
                right = mid - 1;
            }
        }
        return minElement; // Return the minimum element found
    }
}
