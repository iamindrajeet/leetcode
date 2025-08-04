class Solution {
    public int smallestDivisor(int[] nums, int threshold) {
        // Step 1: Initialize the search space (divisor range)
        int minDivisor = nums[0], maxDivisor = nums[0];
        int smallestDivisor = -1;

        // Find the smallest and largest number in the array
        // These are the boundaries for binary search
        for (int num : nums) {
            minDivisor = Math.min(minDivisor, num);
            maxDivisor = Math.max(maxDivisor, num);
        }

        int left = 1, right = maxDivisor; // minimum divisor must be >= 1

        // Step 2: Binary search on divisor value
        while (left <= right) {
            int mid = left + (right - left) / 2;

            // Step 3: Check if current divisor satisfies the threshold condition
            if (sumOfArray(nums, mid) <= threshold) {
                smallestDivisor = mid;     // store current result
                right = mid - 1;           // try to minimize further
            } else {
                left = mid + 1;            // need to increase divisor
            }
        }

        return smallestDivisor;
    }

    private int sumOfArray(int[] nums, int divisor) {
        int sum = 0;
        for (int num : nums) {
            sum += (num + (divisor - 1)) / divisor;
        }
        return sum;
    }
}
