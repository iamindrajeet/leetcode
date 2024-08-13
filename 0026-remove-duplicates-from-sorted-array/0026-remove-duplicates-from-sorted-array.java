/*
1. Time Complexity (TC):
- O(n): We only traverse the array once, so the time complexity is linear.

2. Space Complexity (SC):
- O(1): The algorithm modifies the array in place, and no extra space is used, aside from a few variables.

*/
class Solution {
    public int removeDuplicates(int[] nums) {
        // Initialize a pointer `k` to track the position of the last unique element
        int k = 0;
        
        // Start iterating from the second element (index 1) to the end of the array
        for(int i = 1; i < nums.length; i++){
            // If the current element is the same as the previous one, skip it
            if(nums[i] == nums[i - 1])
                continue;
            else
                // If the current element is different, move it to the next position in the array
                nums[++k] = nums[i];
        }
        
        // Return the number of unique elements, which is k + 1
        return k + 1;
    }
}
