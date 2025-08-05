/**
Time Complexity:
Outer loop: Runs from max(nums) to sum(nums) ⇒ up to O(S) iterations, where S = sum(nums) - max(nums)

getSubArrayCount(): Each call traverses the entire array ⇒ O(N)

Total Time Complexity:
O(S × N),
where S = sum(nums) - max(nums) and N = nums.length

Space Complexity : O(1)
*/
// class Solution {
//     public int splitArray(int[] nums, int k) {
//         // Find the largest number in the array
//         int maxNum = Arrays.stream(nums).max().getAsInt();

//         // Find the total sum of the array
//         int sumOfNums = Arrays.stream(nums).sum();

//         // Try all possible maximum subarray sums from maxNum to sumOfNums
//         for (int num = maxNum; num <= sumOfNums; num++) {
//             // If the number of subarrays needed is exactly k, return the current sum
//             if (getSubArrayCount(nums, num) == k)
//                 return num;
//         }

//         // If no valid split found
//         return -1;
//     }

//     // Helper method to count the number of subarrays needed
//     // if no subarray has a sum greater than subArraySum
//     private int getSubArrayCount(int[] nums, int subArraySum) {
//         int subArrayCount = 1; // At least one subarray is required
//         int totalSum = 0;

//         for (int num : nums) {
//             // If adding current number keeps sum under the limit, add it
//             if (totalSum + num <= subArraySum) {
//                 totalSum += num;
//             } else {
//                 // Otherwise, start a new subarray
//                 subArrayCount++;
//                 totalSum = num;
//             }
//         }

//         return subArrayCount;
//     }
// }
/** 

Approach : Using Binary Search
\U0001f4c8 Time Complexity:
Binary Search Range: left = max(nums), right = sum(nums)
So binary search runs in O(log(sum - max))

getSubArrayCount per iteration: O(n) where n = nums.length

Total Time Complexity:
O(n × log(sum - max))

\U0001f9e0 Space Complexity:
O(1) — Constant space used (no extra data structures), excluding input.
*/

class Solution {
    public int splitArray(int[] nums, int k) {
        // Step 1: Find the maximum element in the array (lower bound for binary search)
        int maxNum = Arrays.stream(nums).max().getAsInt();

        // Step 2: Find the total sum of the array (upper bound for binary search)
        int sumOfNums = Arrays.stream(nums).sum();

        // Step 3: Perform binary search between maxNum and sumOfNums
        int left = maxNum, right = sumOfNums;
        int result = -1;

        while (left <= right) {
            // Try a mid value as the candidate maximum subarray sum
            int mid = left + (right - left) / 2;

            // Step 4: Get the number of subarrays needed if max subarray sum is 'mid'
            int countOfSubArray = getSubArrayCount(nums, mid);

            if (countOfSubArray <= k) {
                // If we can split into k or fewer subarrays, try to minimize max sum
                result = mid;
                right = mid - 1;
            } else {
                // If more than k subarrays are needed, increase allowed max sum
                left = mid + 1;
            }
        }

        // Step 5: Return the minimized largest subarray sum
        return result;
    }

    // Helper method to calculate the number of subarrays required
    // such that no subarray has a sum greater than 'subArraySum'
    private int getSubArrayCount(int[] nums, int subArraySum) {
        int subArrayCount = 1; // At least one subarray is needed
        int totalSum = 0;

        for (int num : nums) {
            // If adding the current number does not exceed the limit
            if (totalSum + num <= subArraySum) {
                totalSum += num;
            } else {
                // Otherwise, start a new subarray
                subArrayCount++;
                totalSum = num;
            }
        }

        return subArrayCount;
    }
}