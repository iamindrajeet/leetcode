
/*

The problem of finding the maximum sum of a circular subarray can be divided into two cases:

Maximum Sum Subarray in Non-Circular Form:
This is the classic maximum subarray sum problem, which can be solved using Kadane's algorithm.
Maximum Sum Subarray in Circular Form:
This involves subarrays that wrap around the end of the array to the beginning.
To solve this problem, we need to consider both of these cases and find the maximum result.

Detailed Explanation:
Step 1: Find the Maximum Subarray Sum Using Kadane's Algorithm
This is the non-circular subarray sum. We use Kadane's algorithm to find this:

Iterate through the array, maintaining the current subarray sum (currSum) and the maximum subarray sum found so far (maxSum).
If the current subarray sum becomes negative, reset it to zero, as starting a new subarray at the next element would yield a higher sum.
Step 2: Calculate the Total Sum of the Array
This is straightforward:

Simply sum all elements of the array to get totalSum.
Step 3: Find the Minimum Subarray Sum Using a Modified Kadane's Algorithm
This is used to find the maximum sum of a circular subarray:

To handle the circular nature, we need to consider the sum of the elements outside the subarray, which is equivalent to subtracting the minimum subarray sum from the total sum.
We use a similar approach to Kadane's algorithm but instead of finding the maximum, we find the minimum subarray sum (minKadane).
Step 4: Calculate the Maximum Circular Subarray Sum
This is done by subtracting the minimum subarray sum from the total sum:

circularSubarraySum = totalSum - minKadane
Step 5: Handle Edge Cases
If all elements are negative, maxSubarraySum will be the largest (least negative) element. In this case, the circular subarray sum would be less meaningful because subtracting the minimum subarray sum from the total sum might yield zero or a negative value. Thus, we only return maxSubarraySum if it's greater than zero.
If maxSubarraySum is positive, it means there's at least one non-negative number, and we return the maximum of maxSubarraySum and circularSubarraySum.

Complexity:
Time Complexity: O(n) because we iterate through the array a constant number of times.
Space Complexity: O(1) because we use only a few extra variables and do not use any extra space that scales with the input size.


*/

class Solution {
    public int maxSubarraySumCircular(int[] nums) {
        // Find the maximum subarray sum using Kadane's algorithm
        int maxSubArraySum = maxKadane(nums);
        
        // Calculate the total sum of the array
        int totalSum = 0;
        for (int num : nums) {
            totalSum += num;
        }
        
        // Find the minimum subarray sum using Kadane's algorithm
        int minSubarraySum = minKadane(nums);
        
        // Calculate the maximum circular subarray sum
        int circularSubarraySum = totalSum - minSubarraySum;
        
        // If the maximum subarray sum is greater than 0, return the maximum of the two sums
        if (maxSubArraySum > 0) {
            return Math.max(maxSubArraySum, circularSubarraySum);
        }
        
        // If all numbers are negative, return the maximum subarray sum (which will be the least negative number)
        return maxSubArraySum;
    }

    // Helper method to find the maximum subarray sum using Kadane's algorithm
    private int maxKadane(int[] nums) {
        int currSum = 0;
        int maxSum = Integer.MIN_VALUE;
        for (int num : nums) {
            currSum += num;
            maxSum = Math.max(currSum, maxSum);
            if (currSum < 0) {
                currSum = 0;
            }
        }
        return maxSum;
    }

    // Helper method to find the minimum subarray sum using Kadane's algorithm
    private int minKadane(int[] nums) {
        int currSum = 0;
        int minSum = Integer.MAX_VALUE;
        for (int num : nums) {
            currSum += num;
            minSum = Math.min(currSum, minSum);
            if (currSum > 0) {
                currSum = 0;
            }
        }
        return minSum;
    }
}
