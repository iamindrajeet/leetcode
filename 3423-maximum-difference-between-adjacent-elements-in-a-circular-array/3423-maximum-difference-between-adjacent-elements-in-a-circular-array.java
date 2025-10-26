class Solution {
    public int maxAdjacentDistance(int[] nums) {
        int n = nums.length;
        int result = 0;

        // Iterate through each element
        for (int i = 0; i < n; i++) {
            // Use modulo to handle circular adjacency
            int nextIndex = (i + 1) % n;
            
            // Calculate absolute difference between current and next element
            int absoluteDiff = Math.abs(nums[i] - nums[nextIndex]);
            
            // Keep track of the maximum difference
            result = Math.max(result, absoluteDiff);
        }

        return result;
    }
}
