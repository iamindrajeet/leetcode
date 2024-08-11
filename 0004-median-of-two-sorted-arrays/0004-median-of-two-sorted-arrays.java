/*

Time Complexity (TC):
O(m + n): Where m and n are the lengths of nums1 and nums2, respectively. This is because we are merging both arrays up to the median index.

Space Complexity (SC):
O(1): No additional space is used aside from a few variables.
*/
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int nums1Length = nums1.length, nums2Length = nums2.length;
        int totalLength = nums1Length + nums2Length;
        boolean isEven = totalLength % 2 == 0;
        int i = 0, j = 0, k = 0; // Pointers for nums1, nums2, and merged array index
        int element1 = 0, element2 = 0; // To store the elements at the median positions
        int idx1 = (totalLength / 2) - 1, idx2 = totalLength / 2; // Median indices

        // Merge the two arrays until we reach the median positions
        while (i < nums1Length && j < nums2Length) {
            if (nums1[i] < nums2[j]) {
                if (k == idx1) element1 = nums1[i];
                if (k == idx2) element2 = nums1[i];
                i++;
            } else {
                if (k == idx1) element1 = nums2[j];
                if (k == idx2) element2 = nums2[j];
                j++;
            }
            k++;
        }

        // If nums1 has remaining elements
        while (i < nums1Length) {
            if (k == idx1) element1 = nums1[i];
            if (k == idx2) element2 = nums1[i];
            i++;
            k++;
        }

        // If nums2 has remaining elements
        while (j < nums2Length) {
            if (k == idx1) element1 = nums2[j];
            if (k == idx2) element2 = nums2[j];
            j++;
            k++;
        }

        // If the total length is even, return the average of the two middle elements
        if (isEven)
            return (element1 + element2) / 2.0;
        else // Otherwise, return the middle element
            return element2;
    }
}
