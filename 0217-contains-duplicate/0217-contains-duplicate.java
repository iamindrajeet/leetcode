/*
1. Intuition and Logic:

- Intuition:    
    - The problem requires us to determine if there are any duplicate values in the array. A straightforward way to solve this is by using a 
    HashSet, which automatically handles duplicate checks.
    - A HashSet allows for O(1) average-time complexity for both insertions and lookups, making it ideal for checking duplicates as we iterate 
    through the array.

- Logic:
    Step 1: Initialize a HashSet to store the numbers we've seen so far.
    Step 2: Loop through each number in the array:
        - If the number is already in the HashSet, return true since we've found a duplicate.
        - If the number is not in the HashSet, add it to the set.
    Step 3: If the loop completes and no duplicates were found, return false.

2. Time Complexity (TC):
O(n): We iterate through the array once. Each operation of adding to the HashSet or checking if a number is already in the HashSet takes O(1) on average.

3. Space Complexity (SC):
O(n): In the worst case, if there are no duplicates, we may end up storing every element of the array in the HashSet.

*/
class Solution {
    public boolean containsDuplicate(int[] nums) {
        // Initialize a HashSet to keep track of the numbers we've seen
        Set<Integer> seen = new HashSet<>();
        
        // Iterate through each number in the array
        for(int num : nums) {
            // If the number is already in the set, a duplicate has been found
            if(seen.contains(num)) {
                return true;
            }
            // Otherwise, add the number to the set
            seen.add(num);
        }
        
        // If no duplicates were found, return false
        return false;
    }
}
