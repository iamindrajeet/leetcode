/*
Intuition:
The problem is to determine if s3 can be formed by interleaving characters from s1 and s2 while maintaining their respective order.

Logic:
Length Check: First, check if the length of s3 is equal to the combined length of s1 and s2. If not, it's impossible to form s3 by interleaving s1 and s2, so return false.

Empty Strings: If all strings are empty, they trivially interleave to form an empty string, so return true.

Memoization Array: Use a 3-dimensional array to store results of subproblems. The array helps avoid redundant calculations by storing whether certain parts of s1 and s2 can interleave to form a part of s3.

Recursive Helper Function: Use a recursive function to check if the remaining parts of s1 and s2 can form the remaining part of s3. This function:

Checks if the current character of s3 matches the current character of s1. If it does, move to the next character in s1 and s3 and call the function recursively.
If the previous step didn't work, check if the current character of s3 matches the current character of s2. If it does, move to the next character in s2 and s3 and call the function recursively.
Use the memoization array to store the results of these checks to avoid redundant work.
Base Case: If the indices for s1, s2, and s3 all reach the end, it means s3 can be formed by interleaving s1 and s2, so return true.

Example:
Let's say s1 = "abc", s2 = "def", and s3 = "adbcef".

The length check passes because 3 + 3 = 6.
Initialize a memoization array.
Start from the beginning of all three strings:
s3[0] = 'a' matches s1[0] = 'a', move to s1[1] and s3[1].
s3[1] = 'd' matches s2[0] = 'd', move to s2[1] and s3[2].
s3[2] = 'b' matches s1[1] = 'b', move to s1[2] and s3[3].
s3[3] = 'c' matches s1[2] = 'c', move to s1[3] and s3[4].
s3[4] = 'e' matches s2[1] = 'e', move to s2[2] and s3[5].
s3[5] = 'f' matches s2[2] = 'f', move to s2[3] and s3[6].
All indices have reached the end, so return true.

Time Complexity (TC):
The recursive function solve(i, j, k) checks each character of s1, s2, and s3.
Each unique combination of indices (i, j, k) will be computed only once due to memoization.
Since i can go from 0 to lenS1, j can go from 0 to lenS2, and k can go from 0 to lenS3, the total number of unique states is (lenS1 + 1) * (lenS2 + 1) * (lenS3 + 1).
However, since lenS1 + lenS2 = lenS3, the number of unique states is actually (lenS1 + 1) * (lenS2 + 1).
Therefore, the time complexity is: O((lenS1+1)×(lenS2+1))

Space Complexity (SC):
The space complexity is determined by the memoization array.
The memoization array memo has dimensions (lenS1 + 1) * (lenS2 + 1) * (lenS3 + 1).
Again, since lenS1 + lenS2 = lenS3, the actual space needed is (lenS1 + 1) * (lenS2 + 1) for the Boolean values stored in the array.

Additionally, the recursive call stack can go as deep as lenS3 in the worst case, which means the space complexity for the recursion stack is O(lenS3). But, considering the memoization array dominates, the overall space complexity is :
O((lenS1+1)×(lenS2+1))

*/

class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        int lenS1 = s1.length(), lenS2 = s2.length(), lenS3 = s3.length();
        
        // If the sum of lengths of s1 and s2 is not equal to the length of s3, return false.
        if (lenS1 + lenS2 != lenS3)
            return false;
        
        // If all strings are empty, return true.
        if (lenS1 == 0 && lenS2 == 0 && lenS3 == 0)
            return true;

        // Initialize a memoization array to store the results of subproblems.
        Boolean[][][] memo = new Boolean[lenS1 + 1][lenS2 + 1][lenS3 + 1];

        // Use a helper function to solve the problem using recursion and memoization.
        return solve(0, 0, 0, lenS1, lenS2, lenS3, s1, s2, s3, memo);
    }

    private boolean solve(int i, int j, int k, int lenS1, int lenS2, int lenS3, String s1, String s2, String s3,
            Boolean[][][] memo) {
        
        // If all indices have reached the end of their respective strings, return true.
        if (i == lenS1 && j == lenS2 && k == lenS3)
            return true;

        // If the result of this subproblem has already been computed, return it.
        if (memo[i][j][k] != null)
            return memo[i][j][k];

        boolean result = false;

        // Check if the current character of s1 matches the current character of s3.
        if (i < lenS1 && s1.charAt(i) == s3.charAt(k))
            result = solve(i + 1, j, k + 1, lenS1, lenS2, lenS3, s1, s2, s3, memo);

        // If the previous result is false, check if the current character of s2 matches the current character of s3.
        if (!result && j < lenS2 && s2.charAt(j) == s3.charAt(k))
            result = solve(i, j + 1, k + 1, lenS1, lenS2, lenS3, s1, s2, s3, memo);

        // Store the result of the subproblem in the memoization array.
        memo[i][j][k] = result;

        return memo[i][j][k];
    }
}
