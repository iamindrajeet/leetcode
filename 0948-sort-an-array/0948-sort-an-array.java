/*
Approach - Merge Sort

Time Complexity
The merge sort algorithm has a time complexity of O(nlogn), where n is the number of elements in the array. Here's a breakdown of why:

1. Dividing the Array: The array is recursively divided into two halves until each subarray contains only one element. The number of divisions is 
    log n because each division cuts the array size in half.

2. Merging the Array: Merging two halves takes O(n) time because every element in the array is considered during the merge process.

3. Since these two steps are performed at each level of recursion, the overall time complexity is O(nlogn).

Space Complexity
The space complexity of merge sort is O(n). This is due to the need for additional arrays to hold the left and right halves of the array during the merge process:

Temporary Arrays: For each recursive call, temporary arrays left and right are created to hold the elements of the two halves. The space required is proportional to the size of the array being sorted, which in the worst case, sums up to 
O(n) additional space.

Recursive Call Stack: Although the depth of the recursion is O(logn), the space used by the call stack is usually considered separate from the auxiliary space used by the algorithm. Therefore, the dominant factor in the space complexity here is the space for the temporary arrays, which is O(n).

*/
class Solution {
    public int[] sortArray(int[] nums) {
        mergeSort(nums, nums.length);
        return nums;
    }

    // Merge sort function that recursively sorts the array
    private void mergeSort(int[] nums, int n) {
        if (n < 2) {
            return; // Base case: if the array size is less than 2, it's already sorted
        }

        int mid = n / 2; // Find the midpoint to split the array
        int[] left = new int[mid]; // Create a subarray for the left half
        int[] right = new int[n - mid]; // Create a subarray for the right half

        // Copy data to the left subarray
        for (int i = 0; i < mid; i++) {
            left[i] = nums[i];
        }

        // Copy data to the right subarray
        for (int i = mid; i < n; i++) {
            right[i - mid] = nums[i];
        }

        // Recursively sort both halves
        mergeSort(left, mid);
        mergeSort(right, n - mid);

        // Merge the sorted halves
        merge(nums, left, right, mid, n - mid);
    }

    // Merge function to combine the two sorted subarrays
    private void merge(int[] nums, int[] left, int[] right, int l, int r) {
        int i = 0, j = 0, k = 0; // Pointers for left, right, and merged arrays

        // Merge the left and right arrays into the original array
        while (i < l && j < r) {
            if (left[i] <= right[j]) {
                nums[k++] = left[i++];
            } else {
                nums[k++] = right[j++];
            }
        }

        // Copy remaining elements of left array, if any
        while (i < l) {
            nums[k++] = left[i++];
        }

        // Copy remaining elements of right array, if any
        while (j < r) {
            nums[k++] = right[j++];
        }
    }
}
