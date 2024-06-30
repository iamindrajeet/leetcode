/*
Brute-Force Approach
*/

// class Solution {
//     public String longestPalindrome(String s) {
//         if (s.length() <= 1)
//             return s;
        
//         String longestPalindrome = "";
//         for (int i = 0; i < s.length(); i++) {
//             for (int j = i; j < s.length(); j++) {
//                 if (isPalindrome(s, i, j)) {
//                     String currentPalindrome = s.substring(i, j + 1);
//                     if (currentPalindrome.length() > longestPalindrome.length()) {
//                         longestPalindrome = currentPalindrome;
//                     }
//                 }
//             }
//         }
//         return longestPalindrome;
//     }

//     private boolean isPalindrome(String str, int i, int j) {
//         int left = i;
//         int right = j;

//         while (left < right) {
//             if (str.charAt(left) != str.charAt(right)) {
//                 return false;
//             }
//             left++;
//             right--;
//         }
//         return true;
//     } 
// }


/*
Approach 2 - Recursion + Memoization
T.C : O(n^2) - Because the amortized Time Complexity of checkPalindrome() will become 1 due to memoization.
S.C : O(n^2)


Explanation with Intuition:
Main Function (longestPalindrome):

Purpose: Iterates through all possible starting (i) and ending (j) indices of substrings in s to find the longest palindromic substring.
Logic: Uses nested loops to consider all substrings s[i...j]. For each substring, it checks if it is a palindrome using the checkPalindrome function.
Memoization: Stores results of palindrome checks in the memo array to avoid redundant computations and improve efficiency.

Palindrome Checking Function (checkPalindrome):

Purpose: Determines if the substring s[left...right] is a palindrome.
Base Case: If left >= right, the substring is trivially a palindrome.
Memoization: Uses the memo array to store and retrieve results of palindrome checks, ensuring that each unique substring is computed only once.
Time Complexity (TC):
Explanation: The time complexity is \U0001d442(n^2), where n is the length of the input string s.
Reasoning:
The nested loops (for(int i = 0; i < n; i++) and for(int j = i; j < n; j++)) iterate over all possible substrings, resulting in O(n^2) iterations.
Each palindrome check (checkPalindrome function) is optimized with memoization, ensuring that each substring is processed in constant time after the initial computation.

Space Complexity (SC):
Explanation: The space complexity is O(n^2)
Reasoning:
The memo array (Boolean[][] memo) of size n x n is used to store results of palindrome checks for substrings, resulting in O(n^2) space complexity.

Summary:
Overall Approach: Uses dynamic programming with memoization to efficiently find the longest palindromic substring.
Efficiency: Achieves O(n^2) time complexity due to nested loops and optimized palindrome checks.
Optimization: Memoization ensures that each substring's palindrome status is computed only once, enhancing overall efficiency.
This approach strikes a balance between simplicity and efficiency, leveraging memoization to optimize the computation of palindrome properties for substrings in the input string s.

*/
class Solution {
    public String longestPalindrome(String s) {
        int n = s.length();
        int maxLen = 0;        // Variable to store the maximum length of palindromic substring found
        int startIdx = 0;      // Variable to store the starting index of the longest palindromic substring
        
        Boolean[][] memo = new Boolean[n][n];  // Memoization array to store results of palindrome checks
        
        // Iterate through all possible starting indices i and ending indices j of substrings
        for(int i = 0; i < n; i++){
            for(int j = i; j < n; j++){
                // Check if substring s[i...j] is a palindrome using memoization
                if(checkPalindrome(s, i, j, memo) && j - i + 1 > maxLen){
                    maxLen = j - i + 1;     // Update maxLen if current substring is longer
                    startIdx = i;           // Update startIdx to current starting index i
                }
            }
        }
        
        // Return the longest palindromic substring found using startIdx and maxLen
        return s.substring(startIdx, startIdx + maxLen);
    }

    // Function to check if substring s[left...right] is a palindrome
    private boolean checkPalindrome(String s, int left, int right, Boolean[][] memo){
        // Base case: If left index is greater than or equal to right index, substring is a palindrome
        if(left >= right)
            return true;
        
        // If result is already memoized, return it
        if(memo[left][right] != null)
            return memo[left][right];
        
        // Check if characters at left and right indices are equal
        if(s.charAt(left) != s.charAt(right)){
            memo[left][right] = false;  // Memoize result as false if characters are not equal
        } else {
            // Recursively check if substring s[left+1...right-1] is a palindrome
            memo[left][right] = checkPalindrome(s, left + 1, right - 1, memo);
        }
        
        // Return the memoized result
        return memo[left][right];
    }
}
