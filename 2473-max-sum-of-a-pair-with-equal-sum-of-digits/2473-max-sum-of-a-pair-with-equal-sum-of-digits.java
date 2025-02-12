/**
Approach-1 (Optimal using Map)

Algorithm & Intuition:
1. We iterate through the nums array and calculate the sum of digits for each number.
2. We use a HashMap to store the largest number seen so far for each digit sum.
3. If a number with the same digit sum already exists in the map, we check if their sum is the largest encountered so far.
4. Update the map to store the maximum number encountered for each digit sum.
5. Finally, return the maximum sum found. If no such pair exists, return -1.

T.C : O(n*m), m = number of digits
S.C : O(n)
*/
class Solution {
    public int maximumSum(int[] nums) {
        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>(); // Map to store max number for each digit sum
        int maxSum = 0; // Stores the maximum sum of two numbers with the same digit sum

        for (int i = 0; i < n; i++) {
            int sumOfDigits = getSumOfDigits(nums[i]); // Compute the sum of digits of the current number
            
            // If a number with the same digit sum exists, update maxSum
            if (map.containsKey(sumOfDigits)) {
                maxSum = Math.max(maxSum, map.get(sumOfDigits) + nums[i]);
            }

            // Update the map with the maximum value corresponding to this digit sum
            map.put(sumOfDigits, Math.max(map.getOrDefault(sumOfDigits, 0), nums[i]));
        }

        return maxSum == 0 ? -1 : maxSum; // Return -1 if no valid pair is found
    }

    private int getSumOfDigits(int num) {
        int sum = 0;
        while (num != 0) {
            int rem = num % 10; // Extract the last digit
            sum += rem; // Add it to sum
            num /= 10; // Remove the last digit
        }
        return sum;
    }
}
