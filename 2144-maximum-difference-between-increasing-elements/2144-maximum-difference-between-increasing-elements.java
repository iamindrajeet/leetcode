/**
I will be getting maximum difference if nums[i] is minimum and nums[j] is maximum such that i < j
*/
class Solution {
    public int maximumDifference(int[] nums) {
        // Initialize the minimum element to the first element of the array
        int minElement = nums[0];
        
        // Initialize answer to -1 (default if no valid pair is found)
        int ans = -1;
        
        // Traverse the array starting from the second element
        for (int i = 1; i < nums.length; i++) {
            // If current number is greater than minElement, we can form a valid pair
            if (nums[i] > minElement) {
                // Update ans with the maximum difference found so far
                ans = Math.max(ans, nums[i] - minElement);
            } else {
                // If current number is smaller or equal, update minElement
                minElement = nums[i];
            }
        }
        
        // Return the maximum difference found, or -1 if no valid pair exists
        return ans;
    }
}
