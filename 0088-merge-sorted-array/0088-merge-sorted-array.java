/**
✅ Time Complexity
O(m + n) → We iterate through both arrays once.

✅ Space Complexity
O(1) → In-place, no extra space used.
*/
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        // Pointers:
        // left  → last valid element in nums1
        // right → last element in nums2
        // k     → last index in nums1 (where we place elements)
        int left = m - 1, right = n - 1;
        int k = m + n - 1;

        // Merge from the back of nums1
        // We only need to place all elements of nums2 (right >= 0)
        while (right >= 0) {
            // If nums1 still has elements and its current element is larger,
            // place nums1[left] at the end
            if (left >= 0 && nums1[left] > nums2[right]) {
                nums1[k--] = nums1[left--];
            } else {
                // Otherwise, place nums2[right]
                nums1[k--] = nums2[right--];
            }
        }
        // No need to copy remaining nums1 elements because they are already in place
    }
}
