/**

Approach : Count Inversions

Intuition
To find whether an array can be sorted by rotation, we need to check if, after a certain point, the sequence of elements remains sorted in a cyclic manner. A more efficient way to do this is by finding the smallest element in the array and using its position to identify the potential rotation offset, which would be the point where the original sorted array begins.

Once we identify the smallest element, we treat it as the "starting" point of the sorted array. From this position, we check if the next n elements, wrapping around cyclically, form a sorted sequence.

The key observation here is that, in a sorted array that has been rotated, all elements should be in non-decreasing order, except for one place where the largest element will be followed by the smallest element due to the rotation. This results in at most one "inversion" — a pair where a number is greater than the next one.

If there are more than one such "inversions," meaning multiple instances where a number is greater than its successor, the array cannot be sorted through any rotation. If there’s at most one inversion, then the array can indeed be sorted by a rotation.

Let's consider an example with the array nums = [3, 4, 5, 1, 2]. The smallest element is 1, which we treat as the start of the sorted array. Starting from 1, the sequence [1, 2, 3, 4, 5] is sorted in a cyclic manner, with only one inversion: 5 is followed by 1, which is expected in a rotated sorted array. Since there is only one inversion, the array can be sorted by rotation, and we return true.

Algorithm
Check if the array is empty or contains only one element. If so, return true, as a single element or an empty array is trivially sorted.

Count the number of inversions (pairs where nums[i] > nums[i + 1]) in the array:

Iterate through the array from 1 to n - 1.
For each element, compare it with the previous element. If the current element is smaller, increment the inversion count.
Compare nums[n - 1] with nums[0]. If nums[0] < nums[n - 1], increment the inversion count.

If the total inversion count is less than or equal to 1, return true. Otherwise, return false.

T.C - O(N)
S.C - O(1)

*/
class Solution {

    public boolean check(int[] nums) {
        int inversionCount = 0; // Count the number of places where the order is violated

        // Loop through the array and count the number of times nums[i] > nums[i + 1]
        for(int i = 0; i < nums.length - 1; i++) {
            if(nums[i] > nums[i + 1]) {
                inversionCount++;
                // If more than one inversion is found, array cannot be rotated to become sorted
                if(inversionCount > 1) {
                    return false;
                }
            }
        }

        // Additionally, check the last element with the first (circular rotation)
        if(nums[nums.length - 1] > nums[0]) {
            inversionCount++;
        }

        // If inversion count is more than 1 even after including circular check, return false
        return inversionCount <= 1;
    }
}
