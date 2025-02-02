/**
Overview
We need to find whether the given integer array nums could represent a sorted array that has been rotated some number of times. A sorted array is defined as one arranged in non-decreasing order, meaning each element is less than or equal to the next. A rotation involves shifting a contiguous block of elements to the back of the array, preserving the relative order of all elements.

For example, [3, 4, 5, 1, 2] is a rotated version of the sorted array [1, 2, 3, 4, 5]. On the other hand, [3, 4, 2, 1, 5] is not a valid rotation of any sorted array because the order of elements is not preserved.

Approach 1: Brute force
Intuition
A simple logical way to approach this problem is to consider all possible rotations of the array. If any rotated array becomes sorted, we can conclude that it is possible; otherwise, it is not.

Suppose the array has n elements. If we rotate the array by 0 positions, it remains the same. If we rotate it by 1 position, the first element moves to the end, and so on. This process continues until we rotate it by n - 1 positions. Rotating the array by exactly n positions brings it back to its original form, so there’s no need to go beyond n - 1.

To implement this, we define a variable rotationOffset that represents the number of positions the array has been rotated. For each rotationOffset, we simulate the rotation by creating a new array, checkSorted. The new array is constructed in two steps: first, we take all elements from the index rotationOffset to the end of the array and append them to checkSorted. Then, we take the remaining elements from the start of the array up to rotationOffset - 1 and append them to checkSorted.

Once we have the rotated array, the next step is to check if it is sorted in non-decreasing order. If we find a rotation where the array becomes sorted, we immediately return true. If no such rotation exists after trying all possible values of rotationOffset, we return false.

Algorithm
1. Iterate through all possible rotation offsets (rotationOffset) from 0 to n - 1:
    - rotationOffset represents the number of positions the array is rotated.
2. For each rotationOffset, construct a new array checkSorted:
    - Append elements from index rotationOffset to n - 1 of the original array nums to checkSorted.
    - Append elements from index 0 to rotationOffset - 1 of nums to checkSorted.
    - Check if the constructed checkSorted array is sorted:
        - Iterate through checkSorted from index 0 to n - 2:
            - If any element is greater than the next element, mark the array as not sorted and break the loop.
    - If the checkSorted array is sorted, return true.
3. If no rotation offset results in a sorted array after checking all possible offsets, return false.

Complexity Analysis
Let n be the size of the nums array.

Time Complexity: O(n^2)

The algorithm iterates through all possible rotation offsets from 0 to n−1. For each offset, it constructs the checkSorted array by iterating through the entire array, which takes O(n). Additionally, it checks if the checkSorted array is sorted, which also takes O(n). As these steps are repeated for n offsets, the total time complexity is O(n*n) = O(n^2).

Space Complexity: O(n)

The algorithm uses an additional array checkSorted to store the elements of the rotated array for each offset. The size of checkSorted is equal to the size of the input array nums, requiring O(n) space. No other significant data structures are used, so the overall space complexity is O(n).

*/
// class Solution {
//     public boolean check(int[] nums) {
//         int n = nums.length;

//         //Construct the rotated array
//         int[] checkSorted = new int[n];

//         //Iterate through all possible rotation offsets
//         for(int rotationOffset = 0; rotationOffset < n; rotationOffset++) {
//             int currIndex = 0;
//             for(int index = rotationOffset; index < n; index++) {
//                 checkSorted[currIndex++] = nums[index];
//             }

//             for(int index = 0; index < rotationOffset; index++) {
//                 checkSorted[currIndex++] = nums[index];
//             }

//             // Check if the constructed array is sorted
//             boolean isSorted = true;
//             for(int index = 0; index < n - 1; index++) {
//                 if(checkSorted[index] > checkSorted[index + 1]) {
//                     isSorted = false;
//                     break;
//                 }
//             }

//             //If sorted, return true
//             if(isSorted)
//                 return true;
//         }
//         // If no rotation makes the sorted array, return false
//         return false;
//     }
// }

/**
Approach 2: Compare with sorted array
Intuition
In the previous approach, we checked whether each rotation of the array was sorted after computing it. Instead of checking for each rotation, we can create a sorted version of the array and compare each rotation directly with this sorted array.

We iterate through all possible rotationOffset values, similar to the previous approach. For each rotationOffset, we iterate through the elements of nums, starting from rotationOffset and going up to the last index (rotationOffset - 1), cyclically. We compare each element with the corresponding element in the sortedNums array. If all elements match, we return true, as we have found the offset that creates the sortedNums array. Otherwise, we continue checking for the next rotationOffset.

Let's consider an example with the array nums = [3, 4, 5, 1, 2]. The sorted version of the array is sortedNums = [1, 2, 3, 4, 5]. Now, we check each possible rotation offset:
    - For rotationOffset = 0, the array is [3, 4, 5, 1, 2], which doesn’t match the sorted array.
    - For rotationOffset = 1, the array becomes [4, 5, 1, 2, 3], which also doesn’t match.
    - For rotationOffset = 2, the array is [5, 1, 2, 3, 4], still no match.
    - For rotationOffset = 3, the array is [1, 2, 3, 4, 5], which matches the sorted array.
Since the rotation by 3 produces a sorted array, we return true and stop further checking. If no match had been found after checking all offsets, we would have returned false. This process avoids the need to repeatedly build rotated arrays and directly checks the matching elements for each possible rotation.

Algorithm
1. Iterate through all possible rotation offsets (rotationOffset) from 0 to n-1:
    - rotationOffset represents the number of positions the array is rotated.
2. For each rotationOffset, compare the original array with a sorted version of itself:
    - Create a sorted copy of the original array sortedNums.
    - Iterate through the elements of nums starting from rotationOffset and wrapping around cyclically using modulo 
    operation:
        - Compare each element with the corresponding element in sortedNums.
    - Check if all elements at each rotationOffset match the sorted array.
    - If the constructed array matches the sorted array at a specific rotationOffset, return true.
3. If no rotation offset results in a sorted array after checking all possible offsets, return false.

Complexity Analysis
Let n be the size of the nums array.

Time Complexity: O(n^2)

The algorithm creates a sorted version of the array, which takes O(nlogn) time. After sorting, it checks all possible rotations by iterating through the array and comparing elements for each rotation, which takes O(n) for each rotation. Hence, the overall time complexity is O(nlogn)+O(n^2)=O(n^2).

Space Complexity: O(n)

The algorithm uses an additional array sortedNums to store the sorted version of the input array, which requires O(n) space. No other significant data structures are used, so the overall space complexity is O(n).
*/
// class Solution {

//     public boolean check(int[] nums) {
//         int size = nums.length;
//         int[] sortedNums = Arrays.copyOf(nums, size);
//         Arrays.sort(sortedNums);

//         // Compare the original array with the sorted array, considering all possible rotations
//         for(int rotationOffset = 0; rotationOffset < size; rotationOffset++) {
//             boolean isMatch = true;
//             for(int index = 0; index < size; index++) {
//                 if(sortedNums[index] != nums[(rotationOffset + index) % size]) {
//                     isMatch = false;
//                     break;
//                 }
//             }
//             if(isMatch)
//                 return true;
//         }
//         return false;
//     }
// }


/*
Approach 3: finding the number of peaks

T.C - O(n)
S.C - O(1)
*/
class Solution {

    public boolean check(int[] nums) {
        int size = nums.length;
        int peak = 0;
        
        // Check for at most one peak
        for (int i = 0; i < size; i++) {
            if (nums[i] > nums[(i + 1) % size]) {
                peak++;
            }
        }
        
        // If there is at most one peak, return true
        return peak <= 1;
    }
}
