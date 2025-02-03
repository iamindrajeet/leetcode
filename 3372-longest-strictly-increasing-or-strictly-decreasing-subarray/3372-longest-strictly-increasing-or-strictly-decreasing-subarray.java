/**
Approach 1: Brute Force

Intuition
Since the problem constraints are very small (nums.length <= 50), a brute force approach is feasible here. We examine all possible subarrays of nums, check if they are increasing, and track the length of the longest increasing one. Then, we repeat the process for decreasing subarrays. The maximum length found among these two is our answer.

We start by iterating through the array, treating each element as the starting point of a subarray. For each starting point, we run an inner loop that continues as long as the current element is greater than the previous one, indicating an increasing sequence. Once this condition fails, we exit the loop. Throughout the process, we track the length of the longest increasing subarray found so far in a variable called maxLength.

After completing the search for increasing subarrays, we repeat the same logic, but this time we check for strictly decreasing subarrays. Again, we update maxLength whenever we find a longer decreasing subarray.

Once both loops are finished, maxLength will contain the length of the longest subarray that is either strictly increasing or strictly decreasing. This value can then be returned as the final result.

Algorithm
1. Initialize a variable maxLength to 0 to track the length of the longest monotonic subarray.
2. For finding the longest increasing subarray:
    - Iterate through each position in the array as a potential starting point.
    - Initialize a variable currLength to 1 for each starting position.
    - From the start position, iterate through subsequent elements:
        - If the current element is greater than the previous element, increment currLength by 1.
        - If the current element is not greater than the previous element, break the inner loop.
    - Update maxLength to be the largest of itself and the current currLength.
3. For finding the longest decreasing subarray, follow the same steps as above, but increment currLength if the current element is less than the previous element.
4. Return the final value of maxLength, which represents the length of the longest strictly increasing or strictly decreasing subarray found. 

Complexity Analysis
Let n be the length of the input array.

Time complexity: O(n^2)

The solution uses two nested loops to find both increasing and decreasing sequences. For each starting position in the outer loop (which runs n times), the inner loop can potentially examine all remaining elements (up to n elements). This gives us O(n^2) operations for finding increasing sequences. The same process is repeated for finding decreasing sequences, resulting in another O(n^2) operations. Therefore, the total time complexity is O(n^2).

Space complexity: O(1)

The solution only uses a constant amount of space to store the variables maxLength and currLength. No additional data structures are created, and the space used does not grow with the input size. Therefore, the space complexity is constant, or O(1).
*/
class Solution {
    public int longestMonotonicSubarray(int[] nums) {
        int maxLength = 0;

        // Find longest strictly increasing subarray
        for(int i = 0; i < nums.length; i++) {
            int currLength = 1;
            for(int j = i + 1; j < nums.length; j++) {
                // Extend subarray if next element is larger
                if(nums[j] > nums[j - 1]) {
                    currLength++;
                } else {
                    // Break if sequence is not increaing anymore
                    break;
                }
            }
            maxLength = Math.max(maxLength, currLength);
        } 

        // Find longest strictly decreasing subarray
        for(int i = 0; i < nums.length; i++) {
            int currLength = 1;
            for(int j = i + 1; j < nums.length; j++) {
                // Extend subarray if next element is larger
                if(nums[j] < nums[j - 1]) {
                    currLength++;
                } else {
                    // Break if sequence is not decreasing anymore
                    break;
                }
            }
            maxLength = Math.max(maxLength, currLength);
        } 
        return maxLength;
    }
}