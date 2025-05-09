/**
 * Approach 1: Brute Force
 * 
 * Intuition
 * Since the problem constraints are very small (nums.length <= 50), a brute
 * force approach is feasible here. We examine all possible subarrays of nums,
 * check if they are increasing, and track the length of the longest increasing
 * one. Then, we repeat the process for decreasing subarrays. The maximum length
 * found among these two is our answer.
 * 
 * We start by iterating through the array, treating each element as the
 * starting point of a subarray. For each starting point, we run an inner loop
 * that continues as long as the current element is greater than the previous
 * one, indicating an increasing sequence. Once this condition fails, we exit
 * the loop. Throughout the process, we track the length of the longest
 * increasing subarray found so far in a variable called maxLength.
 * 
 * After completing the search for increasing subarrays, we repeat the same
 * logic, but this time we check for strictly decreasing subarrays. Again, we
 * update maxLength whenever we find a longer decreasing subarray.
 * 
 * Once both loops are finished, maxLength will contain the length of the
 * longest subarray that is either strictly increasing or strictly decreasing.
 * This value can then be returned as the final result.
 * 
 * Algorithm
 * 1. Initialize a variable maxLength to 0 to track the length of the longest
 * monotonic subarray.
 * 2. For finding the longest increasing subarray:
 * - Iterate through each position in the array as a potential starting point.
 * - Initialize a variable currLength to 1 for each starting position.
 * - From the start position, iterate through subsequent elements:
 * - If the current element is greater than the previous element, increment
 * currLength by 1.
 * - If the current element is not greater than the previous element, break the
 * inner loop.
 * - Update maxLength to be the largest of itself and the current currLength.
 * 3. For finding the longest decreasing subarray, follow the same steps as
 * above, but increment currLength if the current element is less than the
 * previous element.
 * 4. Return the final value of maxLength, which represents the length of the
 * longest strictly increasing or strictly decreasing subarray found.
 * 
 * Complexity Analysis
 * Let n be the length of the input array.
 * 
 * Time complexity: O(n^2)
 * 
 * The solution uses two nested loops to find both increasing and decreasing
 * sequences. For each starting position in the outer loop (which runs n times),
 * the inner loop can potentially examine all remaining elements (up to n
 * elements). This gives us O(n^2) operations for finding increasing sequences.
 * The same process is repeated for finding decreasing sequences, resulting in
 * another O(n^2) operations. Therefore, the total time complexity is O(n^2).
 * 
 * Space complexity: O(1)
 * 
 * The solution only uses a constant amount of space to store the variables
 * maxLength and currLength. No additional data structures are created, and the
 * space used does not grow with the input size. Therefore, the space complexity
 * is constant, or O(1).
 */
// class Solution {
// public int longestMonotonicSubarray(int[] nums) {
// int maxLength = 0;

// // Find longest strictly increasing subarray
// for(int i = 0; i < nums.length; i++) {
// int currLength = 1;
// for(int j = i + 1; j < nums.length; j++) {
// // Extend subarray if next element is larger
// if(nums[j] > nums[j - 1]) {
// currLength++;
// } else {
// // Break if sequence is not increaing anymore
// break;
// }
// }
// maxLength = Math.max(maxLength, currLength);
// }

// // Find longest strictly decreasing subarray
// for(int i = 0; i < nums.length; i++) {
// int currLength = 1;
// for(int j = i + 1; j < nums.length; j++) {
// // Extend subarray if next element is larger
// if(nums[j] < nums[j - 1]) {
// currLength++;
// } else {
// // Break if sequence is not decreasing anymore
// break;
// }
// }
// maxLength = Math.max(maxLength, currLength);
// }
// return maxLength;
// }
// }

/** 
Approach 2: Single Iteration
Intuition
In our previous approach, we did a lot of repetitive work. In both the loops, we iterate over the array and compare adjacent elements. The only difference was the type of subarray we were counting. This suggests that the two loops can likely be combined into a single loop. Also, when we break from the inner loop after exploring a subarray, we again iterate over almost the same subarray in the next iteration, when the starting element shifts by one to the left. We can make this process more efficient by completing the exploration in a single iteration.

We iterate over nums and along with maxLength, we'll now maintain two variables incLength and decLength. These will track the length of the increasing and decreasing subarrays ending at the current element we are iterating over. Here’s how we handle each element during the iteration:

Current element > Previous element: This means the current element can extend an increasing subarray. So, we increment incLength by 1. At the same time, we reset decLength to 1, since the longest decreasing subarray ending at this element is the element itself.
Current element < Previous element: Now, the current element can extend a decreasing subarray. We increment decLength by 1 and reset incLength to 1.
Current element = Previous element: Since we are looking for strictly increasing or decreasing subarrays, neither incLength nor decLength can increase in this case. We reset both to 1.
At each step, we update maxLength with the larger of incLength or decLength. Once the loop finishes, maxLength will hold the length of the longest strictly increasing or decreasing subarray. We then return maxLength as the final answer.

Algorithm
1. Initialize variables:
    - incLength to 1 to track the current length of an increasing sequence.
    - decLength to 1 to track the current length of a decreasing sequence.
    - maxLength to 1 to store the length of the longest monotonic subarray found.
2. Iterate through the array from the first element to the second-to-last element:
    - Compare each element with its next element.
    - If the next element is greater than the current element:
        - Increment incLength by 1 to extend the increasing sequence.
        - Reset decLength to 1 as the decreasing sequence breaks.
    - If the next element is less than the current element:
        - Increment decLength by 1 to extend the decreasing sequence.
        - Reset incLength to 1 as the increasing sequence breaks.
    - If the next element equals the current element:
        - Reset both incLength and decLength to 1 as both sequences break.
    - Update maxLength to be the larger among itself, incLength, and decLength.
3. Return the final value of maxLength, which represents the length of the longest strictly monotonic subarray.

Complexity Analysis
Let n be the length of the input array nums.

Time complexity: O(n)

The algorithm iterates through the array exactly once using a single loop, comparing adjacent elements to determine whether the sequence is increasing or decreasing. Each comparison and update operation is performed in constant time. Therefore, the overall time complexity is O(n).

Space complexity: O(1)

The algorithm uses a constant amount of additional space, with variables incLength, decLength, and maxLength to track the lengths of the sequences and the largest length encountered. No extra data structures are used, so the space complexity is O(1).
*/
class Solution {
    public int longestMonotonicSubarray(int[] nums) {
        int maxLength = 1;
        // Track current lengths of increasing and decreasing sequences
        int incLength = 1, decLength = 1;

        // Iterate through array comparing adjacent elements
        for (int i = 0; i < nums.length - 1; i++) {
            if(nums[i + 1] > nums[i]) {
                // If next element is larger, extend increasing sequence
                incLength++;
                decLength = 1; // Reset decreasing sequence
            } else if(nums[i + 1] < nums[i]) {
                incLength = 1; // Reset increasing sequence
                decLength++;
            } else {
                // If elements are equal, reset both sequences
                incLength = decLength = 1;
            }
            // Update max length considering both sequences
            maxLength = Math.max(maxLength, Math.max(incLength, decLength)); 
        }
        return maxLength;
    }
}