/*
SIMPLY KADANE's algo .

The code iterates through the array and keeps track of the current subarray sum using currSum.
It updates largestSum (maximum subarray sum) whenever a larger sum is encountered during the iteration.
The optional if (currSum < 0) condition can be a slight optimization to avoid accumulating negative subarray sums, but it's not strictly necessary for correctness.

*/
class Solution {
    public int maxSubArray(int[] nums) {
        int largestSum = Integer.MIN_VALUE;
        int currSum = 0;
        int startIdx = 0, endIdx = 0;
        for(int i = 0; i < nums.length; i++){
            if(currSum == 0)
                startIdx = i;
            currSum += nums[i];
            if(currSum > largestSum){
                largestSum = currSum;
                endIdx = i;
            }
            if(currSum < 0)
                currSum = 0;
        }
        System.out.println(startIdx + " " + endIdx);
        return largestSum;
    }
}