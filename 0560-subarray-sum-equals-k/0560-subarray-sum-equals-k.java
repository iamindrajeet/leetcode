/**
 * Approach: Using cumulative sum and hashmap
 * T.C - O(N), S.C - O(N)
 * This solution counts the total number of subarrays with sum equal to k.
 */
class Solution {
    public int subarraySum(int[] nums, int k) {
        // Map to store cumulative sum frequencies
        Map<Integer, Integer> map = new HashMap<>();
        
        map.put(0, 1); // Base case: A cumulative sum of 0 has one occurrence

        int totalNoOfSubarrays = 0; // To store the total number of subarrays with sum = k
        int cumSum = 0;             // Running cumulative sum

        for (int i = 0; i < nums.length; i++) {
            cumSum += nums[i]; // Update cumulative sum

            // If (cumSum - k) exists in the map, it means there are
            // 'map.get(cumSum - k)' subarrays ending at index i with sum = k
            int rem = cumSum - k;
            if (map.containsKey(rem)) {
                totalNoOfSubarrays += map.get(rem);
            }

            // Update the frequency of the current cumulative sum
            map.put(cumSum, map.getOrDefault(cumSum, 0) + 1);
        }

        // Return the total number of subarrays with sum = k
        return totalNoOfSubarrays;
    }
}
