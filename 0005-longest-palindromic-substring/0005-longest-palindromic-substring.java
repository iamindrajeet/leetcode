/*
Approach: 2 Loops + Memoization

Explanation with Intuition:
Main Function (longestPalindrome):

- **Purpose**: Iterates through all possible starting (`i`) and ending (`j`) indices of substrings in `s` to find the longest palindromic substring.
- **Logic**: Uses nested loops to consider all substrings `s[i...j]`. For each substring, it checks if it is a palindrome using the `isPalindrome` function.
- **Memoization**: Stores results of palindrome checks in the `memo` array to avoid redundant computations and improve efficiency.

Palindrome Checking Function (isPalindrome):

- **Purpose**: Determines if the substring `s[left...right]` is a palindrome.
- **Base Case**: If `left >= right`, the substring is trivially a palindrome.
- **Memoization**: Uses the `memo` array to store and retrieve results of palindrome checks, ensuring that each unique substring is computed only once.

Time Complexity (TC):
- **Explanation**: The time complexity is O(n^2), where `n` is the length of the input string `s`.
- **Reasoning**:
  - The nested loops (`for(int i = 0; i < n; i++)` and `for(int j = i; j < n; j++)`) iterate over all possible substrings, resulting in O(n^2) iterations.
  - Each palindrome check (`isPalindrome` function) is optimized with memoization, ensuring that each substring is processed in constant time after the initial computation.

Space Complexity (SC):
- **Explanation**: The space complexity is O(n^2).
- **Reasoning**:
  - The `memo` array (`Boolean[][] memo`) of size `n x n` is used to store results of palindrome checks for substrings, resulting in O(n^2) space complexity.

Summary:
- **Overall Approach**: Uses dynamic programming with memoization to efficiently find the longest palindromic substring.
- **Efficiency**: Achieves O(n^2) time complexity due to nested loops and optimized palindrome checks.
- **Optimization**: Memoization ensures that each substring's palindrome status is computed only once, enhancing overall efficiency.

This approach strikes a balance between simplicity and efficiency, leveraging memoization to optimize the computation of palindrome properties for substrings in the input string `s`.
*/

class Solution {
    public String longestPalindrome(String s) {
        int len = s.length();
        Boolean[][] memo = new Boolean[len][len]; // Memoization array to store palindrome checks
        int maxLen = 0, startIndex = 0; // Variables to store the maximum length and starting index of the longest palindrome

        // Iterate through each possible substring
        for (int i = 0; i < len; i++) {
            for (int j = i; j < len; j++) {
                // Check if the substring s[i:j] is a palindrome and is longer than the current max length
                if (isPalindrome(s, i, j, memo) && j - i + 1 > maxLen) {
                    maxLen = j - i + 1; // Update maximum length
                    startIndex = i; // Update starting index of the longest palindrome
                }
            }
        }
        return s.substring(startIndex, startIndex + maxLen); // Return the longest palindrome substring
    }

    private boolean isPalindrome(String s, int left, int right, Boolean[][] memo) {
        if (left >= right) // Base case: A single character or an empty string is always a palindrome
            return true;

        if (memo[left][right] != null) // Check if the result is already computed
            return memo[left][right];

        // Recursively check if the substring s[left:right] is a palindrome
        if (s.charAt(left) != s.charAt(right)) // If the characters don't match, it's not a palindrome
            memo[left][right] = false;
        else // If they match, check the inner substring
            memo[left][right] = isPalindrome(s, left + 1, right - 1, memo);

        return memo[left][right]; // Return the result stored in memo
    }
}
