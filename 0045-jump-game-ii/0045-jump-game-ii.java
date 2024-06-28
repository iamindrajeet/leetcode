/*
Initialization:

totalJumps is set to 0, which will keep track of the total number of jumps made.
destination is the last index of the array (nums.length - 1).
coverage represents the farthest index that can be reached with the current number of jumps.
lastJumpIdx is the index from where the last jump was made.

Base Case:

If the array has only one element, no jumps are needed, so the function returns 0.
Greedy Strategy:

Loop through each element in the array.
For each index i, calculate the farthest index that can be reached (coverage = Math.max(coverage, i + nums[i])).
If the current index i is the same as lastJumpIdx, it means we need to make a jump because we've reached the end of our current coverage.
Update lastJumpIdx to the new coverage.
Increment totalJumps.
If the new coverage reaches the destination, return the total number of jumps.

Return Statement:

If the loop completes, return the total number of jumps. This is the minimum number of jumps needed to reach the end of the array.

Here's how it works with an example:

For nums = [2, 3, 1, 1, 4]:

Start with coverage = 0, lastJumpIdx = 0, totalJumps = 0.
At i = 0, coverage becomes 2 (0 + nums[0]), so lastJumpIdx is updated to 2, and totalJumps becomes 1.
At i = 2, coverage becomes 4 (2 + nums[2]), so lastJumpIdx is updated to 4, and totalJumps becomes 2. Since coverage now reaches the destination, the loop breaks and returns 2.

*/

class Solution {
    public int jump(int[] nums) {
        int totalJumps = 0;

        // destination is last index
        int destination = nums.length - 1;
        int coverage = 0, lastJumpIdx = 0;
        // Base case
        if (nums.length == 1)
            return 0;

        // Greedy strategy: extend coverage as long as possible
        for(int i = 0; i < nums.length; i++){
            coverage = Math.max(coverage, i + nums[i]);

            if(i == lastJumpIdx){
                lastJumpIdx = coverage;
                totalJumps++;

                // check if we reached destination already
                if(coverage == destination){
                    return totalJumps;
                }
            }
        }
        return totalJumps;
    }
}