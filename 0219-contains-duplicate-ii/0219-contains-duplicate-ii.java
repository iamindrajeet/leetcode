/*
1. Intuition and Logic:

- Intuition:
    -   The problem asks us to find if there are two distinct indices i and j in the array such that nums[i] == nums[j] and the absolute        
    difference between i and j is at most k.
    - We can use a HashMap to keep track of the last seen index of each number. This allows us to quickly check if the same number has appeared 
    within the distance k.

- Logic:
    Step 1: Initialize a HashMap where the key is the number from the array, and the value is the index at which the number was last seen.
    Step 2: Loop through the array:
        - If the number is not in the map, add it with its current index.
        - If the number is already in the map, check the difference between the current index and the index stored in the map.
        - If the difference is less than or equal to k, return true as we found a nearby duplicate.
        - If the difference is greater than k, update the map with the current index.
    Step 3: If the loop completes without finding any nearby duplicates, return false.

2. Time Complexity (TC):
O(n): We iterate through the array once, and each operation of checking or updating the map takes O(1) time.

3. Space Complexity (SC):
O(n): In the worst case, we might store every element of the array in the HashMap, leading to O(n) space usage.

*/

class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        // Create a HashMap to store the value and its corresponding index in the array
        Map<Integer, Integer> map = new HashMap<>();
        
        // Iterate through the array
        for(int i = 0; i < nums.length; i++){
            // If the current number is not already in the map, add it with its index
            if(!map.containsKey(nums[i])) {
                map.put(nums[i], i);
            } else {
                // If the current number is already in the map, check the difference
                // between the current index and the last recorded index of this number
                if(Math.abs(map.get(nums[i]) - i) <= k) {
                    return true; // Found a duplicate within the required range
                }
                // Update the index of the current number in the map
                map.put(nums[i], i);
            }
        }
        // If no such pair is found, return false
        return false;
    }
}
