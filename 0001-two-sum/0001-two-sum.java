import java.util.*;

class Solution {
    /**
     * This method finds two indices in the array such that their sum equals the target.
     * 
     * @param nums The input array of integers.
     * @param target The target sum to find.
     * @return An array containing the indices of the two numbers that add up to the target.
     */
    public int[] twoSum(int[] nums, int target) {
        // A HashMap to store the value and its corresponding index
        Map<Integer, Integer> map = new HashMap<>();
        // Iterate through the array
        for (int i = 0; i < nums.length; i++) {
            // Calculate the complement that we need to find
            int complement = target - nums[i];
            // If the complement exists in the map, return the current index and the index of the complement
            if (map.containsKey(complement)) {
                return new int[] { map.get(complement), i };
            }
            // If the complement does not exist, add the current number and its index to the map
            map.put(nums[i], i);
        }
        // Return an empty array if no solution is found (though the problem guarantees one solution)
        return new int[0];
    }
}
