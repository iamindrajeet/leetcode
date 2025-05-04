/**
Time Complexity: O(n)
Where n is the length of the input array nums.

Step 1: Scanning from the end to find the pivot index → O(n)

Step 2: Scanning again from the end to find the element to swap → O(n) in the worst case

Step 3: Reversing the suffix → O(n) in the worst case

These steps are sequential and linear, so the overall complexity is:
 O(n) + O(n) + O(n) = O(n)

Space Complexity: O(1)
The algorithm modifies the input array in place.

It only uses a few integer variables (idx, i, j, temp), which take constant space.
*/
class Solution {
    public void nextPermutation(int[] nums) {
        int n = nums.length;
        int idx = -1;

        // Step 1: Find the first index from the end where nums[i] < nums[i + 1]
        // This identifies the pivot point for the next permutation
        for (int i = n - 2; i >= 0; i--) {
            if (nums[i] < nums[i + 1]) {
                idx = i;
                break;
            }
        }

        // Step 2: If no such index found, the array is in descending order
        // Reverse the whole array to get the smallest permutation
        if (idx == -1) {
            reverse(nums, 0, n - 1);
            return;
        }

        // Step 3: From the end, find the first number greater than nums[idx]
        // This number will be swapped with nums[idx] to increase the permutation
        for (int i = n - 1; i > idx; i--) {
            if (nums[i] > nums[idx]) {
                swap(nums, i, idx);
                break;
            }
        }

        // Step 4: Reverse the subarray after the swapped index
        // This ensures the suffix is the smallest possible order
        reverse(nums, idx + 1, n - 1);
    }

    // Utility method to reverse a subarray from index i to j (inclusive)
    private void reverse(int[] nums, int i, int j) {
        while (i < j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }

    // Utility method to swap two elements in the array
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
