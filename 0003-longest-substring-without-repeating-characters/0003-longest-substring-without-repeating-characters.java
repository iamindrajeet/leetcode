/*
- Time Complexity (TC)
The time complexity of this solution is O(N), where N is the length of the input string s. Each character is processed at most twice, once by the right pointer and once by the left pointer.

- Space Complexity (SC)
The space complexity is O(min(N, M)), where M is the size of the character set (for example, 26 for lowercase English letters). In the worst case, if all characters are unique, the seen set will store all characters of the string, so the space complexity is linear with respect to the size of the string or the character set, whichever is smaller.

*/
class Solution {
    public int lengthOfLongestSubstring(String s) {
        // A set to store the characters that are currently in the sliding window
        Set<Character> seen = new HashSet<>();

        // Pointers to define the current sliding window
        int left = 0, right = 0;

        // Variable to keep track of the maximum length found
        int ans = 0;

        // Traverse the string using the right pointer
        while (right < s.length()) {
            // If the current character is already in the set, it means we have a duplicate
            // We need to move the left pointer to shrink the window until the duplicate is
            // removed
            while (seen.contains(s.charAt(right))) {
                seen.remove(s.charAt(left)); // Remove the character at the left pointer from the set
                left++; // Move the left pointer to the right
            }

            // Update the maximum length found
            ans = Math.max(ans, right - left + 1);

            // Add the current character to the set
            seen.add(s.charAt(right));

            // Move the right pointer to the next character
            right++;
        }

        // Return the maximum length of the substring without repeating characters
        return ans;
    }
}