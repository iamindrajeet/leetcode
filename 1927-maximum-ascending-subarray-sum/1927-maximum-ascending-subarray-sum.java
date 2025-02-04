/**
Approach 1: Brute force

Intuition 

A simple logical approach to solving this problem is to check all possible ascending subarrays to find the largest sum. We can start by treating each element as the beginning of a new subarray and calculate its sum. If the element next to it is greater, we can extend the current subarray by adding it to the sum. If the next element is not greater, we stop extending and consider the next element as the start of a new subarray. We repeat this process for each element, keeping track of the largest sum found.

While this is straightforward, it requires checking every possible subarray using nested loops. This can become inefficient because the number of subarrays increases with the size of the input, resulting in a time complexity of O(n^2). Although this method works fine with small inputs due to relatively easy constraints, it is not ideal for larger arrays because it involves checking many possible subarrays.
*/
// class Solution {
//     public int maxAscendingSum(int[] nums) {
//         int maxSum = 0; // Stores the maximum ascending sum found so far

//         for (int i = 0; i < nums.length; i++) {
//             int currSum = nums[i]; // Start a new subarray from the current element

//             for (int j = i + 1; j < nums.length; j++) {
//                 // S: If the current element is greater than the previous one, add it to the sum
//                 if (nums[j] >= nums[j - 1]) {  
//                     currSum += nums[j];
//                 } else {
//                     // If the sequence breaks, stop checking further for this subarray
//                     break;
//                 }
//             }

//             // Update maxSum if the current subarray sum is greater
//             maxSum = Math.max(maxSum, currSum);
//         }

//         return maxSum;
//     }
// }

/** 
Approach 2 : Modified Kadance's Algo(Linear Scan)

Intuition
Instead of using the brute-force method of checking every possible subarray, which is inefficient, we can solve the problem in a single pass through the array.

The key idea is to keep extending the current subarray as long as it stays ascending. If we encounter an element that isn’t greater than the previous one, we stop and compare the current subarray’s sum with the largest sum we’ve found so far and update it if the current element is not greater than the previous one. Then, we reset the current sum to the current element and start a new subarray from there.

This strategy works because, with all numbers being positive, extending a subarray will always increase its sum. Thus, we should never start a new subarray when we can extend the current one. For an added challenge, try solving the similar problem 53. Maximum Subarray, where the numbers are not restricted to be positive.

By following this idea, we only need to go through the array once. At the end of the loop, we perform a final check to ensure we account for the last subarray, just in case it had the largest sum.

T.C - O(n)
S.C - O(1)
*/
class Solution {
    public int maxAscendingSum(int[] nums) {
        int maxSum = nums[0]; // Initialize max sum with the first element
        int currSum = nums[0]; // Initialize current sum with the first element

        for (int i = 1; i < nums.length; i++) {
            // If the sequence remains increasing, add to current sum
            if (nums[i] > nums[i - 1]) {
                currSum += nums[i];
            } else {
                // If sequence breaks, reset current sum to the new subarray
                currSum = nums[i];
            }
            // Update max sum after each step
            maxSum = Math.max(maxSum, currSum);
        }
        return maxSum;
    }
}


