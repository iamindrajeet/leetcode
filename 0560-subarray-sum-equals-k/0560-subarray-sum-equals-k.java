/*
The problem is to find the number of continuous subarrays whose sum equals k. We can solve this problem using a HashMap to keep track of cumulative sums (prefix sums) and their frequencies.

Intuition
Cumulative Sum (Prefix Sum):

As we iterate through the array, we keep a running total (cumSum) of the elements. This running total represents the sum of elements from the start of the array up to the current element.
Using a HashMap:

We use a HashMap (map) to store the frequency of each cumulative sum encountered.
The key idea is to check if there exists a previous cumulative sum such that the difference between the current cumulative sum and this previous cumulative sum equals k.
Mathematical Insight:

If cumSum[i] is the cumulative sum up to index i, and cumSum[j] is the cumulative sum up to index j (where j < i), then the sum of the subarray from j+1 to i is cumSum[i] - cumSum[j].
If cumSum[i] - cumSum[j] == k, then we have found a subarray that sums to k.
Algorithm:

Initialize the cumulative sum (cumSum) to 0 and the result (number of subarrays) to 0.
Initialize the HashMap with a base case {0: 1} to handle the scenario where the cumulative sum itself is equal to k.
Iterate through the array, updating the cumulative sum and checking the HashMap for the required difference (cumSum - k).
If the required difference exists in the HashMap, it means there are one or more subarrays ending at the current index which sum to k. Add the frequency of this difference to the result.
Update the HashMap with the current cumulative sum.

Time Complexity
O(n): We only iterate through the array once.
Space Complexity
O(n): In the worst case, we store all cumulative sums in the HashMap.


*/

class Solution {
    public int subarraySum(int[] nums, int k) {
        // Map to store the frequency of cumulative sums
        Map<Integer, Integer> map = new HashMap<>();
        int result = 0, cumSum = 0;
        
        // Initialize the map with a base case to handle subarrays starting from index 0
        map.put(0, 1);

        // Iterate through the array
        for (int i = 0; i < nums.length; i++) {
            // Update the cumulative sum
            cumSum += nums[i];

            // Check if the required cumulative sum difference exists in the map
            if (map.containsKey(cumSum - k)) {
                // If yes, add the frequency of this difference to the result
                result += map.get(cumSum - k);
            }

            // Update the map with the current cumulative sum
            map.put(cumSum, map.getOrDefault(cumSum, 0) + 1);
        }

        return result;
    }
}
