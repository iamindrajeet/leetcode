/*
Explanation
Logic:
The goal of the problem is to minimize the difference between the maximum and minimum elements of the array after changing at most three elements. By sorting the array, we can strategically decide which elements to modify to achieve the minimum difference.

Steps:
Handle Short Arrays:

If the array length (n) is less than or equal to 4, we can change all elements to be the same with at most 3 changes, so the difference will be 0.
Sort the Array:

Sorting helps in easily accessing the smallest and largest elements, which are potential candidates for modification.
Calculate Possible Minimum Differences:

After sorting, the smallest values will be at the beginning and the largest values will be at the end of the array.

There are four scenarios to consider for minimizing the difference by changing at most three elements:

Change the three largest elements.
Change the two largest elements and the smallest element.
Change the largest element and the two smallest elements.
Change the three smallest elements.
For each scenario, calculate the difference between the remaining largest and smallest values:

nums[n - 4] - nums[0]: Change the three largest elements.
nums[n - 3] - nums[1]: Change the two largest elements and the smallest element.
nums[n - 2] - nums[2]: Change the largest element and the two smallest elements.
nums[n - 1] - nums[3]: Change the three smallest elements.
Return the Minimum Difference:

The minimum difference from the above calculations will be the result.

T.C - O(log n)


*/
class Solution {
    public int minDifference(int[] nums) {
        int n = nums.length;
        
        // If the length of the array is 4 or less, we can change all elements to be the same with 3 or fewer changes.
        if (n <= 4) {
            return 0;
        }

        // Sort the array to find the minimal difference
        Arrays.sort(nums);

        // Initialize the minimum difference variable
        // Check four possible scenarios:
        // 1. Remove the largest 3 elements
        // 2. Remove the smallest element and the largest 2 elements
        // 3. Remove the smallest 2 elements and the largest element
        // 4. Remove the smallest 3 elements
        int minDiff = Math.min(
            Math.min(nums[n - 4] - nums[0], nums[n - 3] - nums[1]),
            Math.min(nums[n - 2] - nums[2], nums[n - 1] - nums[3])
        );

        return minDiff;
    }
}
