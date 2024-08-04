/*
Approach 1: Brute Force

Algorithm
1. Initialize an array given by storeSubarray to store all the subarray sums.
2. Iterate i through nums:
    a. Initialize an integer sum with 0, to store the subarray sums starting at i.
    b. Iterate j from i to the end of nums:
        - Increment sum with nums[j].
        - Append sum to the storeSubarray array.
3. Sort storeSubarray in non-decreasing order.
4. Initialize rangeSum with 0 and mod with 1000000009.
5. Iterate all elements in storeSubarray between left-1 and right-1:
    a. Add the current value of storeSubarray to rangeSum and take its modulo with mod.
6. Return rangeSum.

Complexity Analysis
Let n be the size of the nums array.

1. Time complexity: O(n^2.logn)
We iterate through nums twice to store all the subarray sums. This operation takes O(n^2) time. Then, we sort this array storing all the    subarray sums. The time complexity for this operation is O(n^2⋅logn). Iterating all indices between left and right also takes O(n^2) time in the worst case.

Therefore, the total time complexity is given by O(n^2⋅logn).

Space complexity: O(n^2)
We create a storeSubarray array with size proportional to O(n^2). Apart from this, no additional memory is used.
Therefore, the total space complexity is given by O(n^2).

*/
class Solution {
    public int rangeSum(int[] nums, int n, int left, int right) {
        List<Integer> storeSubArray = new ArrayList<>();
        for(int i = 0; i < nums.length; i++){
            int sum = 0;
            // Iterate through all indices ahead of the current index.
            for(int j = i; j < nums.length; j++){
                sum += nums[j];
                storeSubArray.add(sum);
            }
        }

        // Sort all subarray sum values in increasing order.
        Collections.sort(storeSubArray);

        // Find the sum of all values between left and right.
        int rangeSum = 0, mod = (int) 1e9 + 7;
        for(int i = left - 1; i < right; i++){
            rangeSum = (rangeSum + storeSubArray.get(i)) % mod;
        }
        return rangeSum;
    }
}
