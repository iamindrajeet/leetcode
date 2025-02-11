/**
Algorithm (Sliding Window Approach):

1.Initialize variables:
    - left = 0 → Left pointer of the sliding window.
    - maxLength = 0 → Stores the maximum valid subarray length.
    - zeroCount = 0 → Tracks the number of zeros in the current window.
2. Expand the window with the right pointer:
    - If nums[right] == 0, increment zeroCount.
3. Shrink the window from the left when zeroCount > k:
    - If nums[left] == 0, decrement zeroCount to keep at most k flips.
    - Move left pointer to the right to maintain the valid window.
4. Update maxLength with the longest valid subarray length.
5. Return maxLength as the result.

T.C - O(n)
S.C - O(1)

*/
class Solution {
    public int longestOnes(int[] nums, int k) {
        int left = 0, maxLength = 0, zeroCount = 0;

        // Expand the sliding window using the `right` pointer
        for (int right = 0; right < nums.length; ++right) {
            // If the current element is 0, increase zeroCount
            if (nums[right] == 0) {
                zeroCount++;
            }

            // If zeroCount exceeds k, shrink the window from the left
            while (zeroCount > k) {
                // If we remove a zero from the window, decrease zeroCount
                if (nums[left] == 0) {
                    zeroCount--;
                }
                left++; // Move left pointer to reduce window size
            }

            // Update the maximum window size
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength; // Return the longest valid subarray length
    }
}
