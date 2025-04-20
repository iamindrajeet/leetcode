/*
Approach : Binary Search
T.C - O(n log n)
S.C - O(n)
*/

class Solution {
    public int maxFrequency(int[] nums, int k) {
        int n = nums.length;

        // Step 1: Sort the array so that we can process elements from smallest to largest
        Arrays.sort(nums);

        // Step 2: Compute prefix sum array where prefixSum[i] stores the sum of nums[0] to nums[i]
        int[] prefixSum = new int[n];
        prefixSum[0] = nums[0];
        for (int i = 1; i < n; i++) {
            prefixSum[i] = nums[i] + prefixSum[i - 1];
        }

        int result = 0;

        // Step 3: Iterate through each index and treat nums[targetIdx] as the number 
        // we want to increase previous numbers up to (i.e., target number)
        for (int targetIdx = 0; targetIdx < n; targetIdx++) {
            // For each target index, calculate the maximum frequency we can reach
            int frequency = findFrequencyUsingBinarySearch(targetIdx, nums, prefixSum, k);
            result = Math.max(result, frequency); // Keep track of the best result so far
        }

        return result;
    }

    private int findFrequencyUsingBinarySearch(int targetIdx, int[] nums, int[] prefixSum, int k) {
        int targetElement = nums[targetIdx]; // The value we want to match via incrementing
        int left = 0, right = targetIdx;     // Search range from start to targetIdx
        int bestIdx = targetIdx;             // Best left index found so far

        while (left <= right) {
            int mid = left + (right - left) / 2;

            int count = targetIdx - mid + 1; // Number of elements in the window [mid...targetIdx]
            int totalRequiredSum = count * targetElement; // Total sum needed if all were targetElement
            int actualSumInWindow = prefixSum[targetIdx] - prefixSum[mid] + nums[mid];
            int operationsNeeded = totalRequiredSum - actualSumInWindow;

            if (operationsNeeded > k) {
                // Too many operations needed — need to reduce the window
                left = mid + 1;
            } else {
                // This window is valid — try to extend it further left
                bestIdx = mid;
                right = mid - 1;
            }
        }

        // The valid frequency is the size of the window [bestIdx...targetIdx]
        return targetIdx - bestIdx + 1;
    }
}
