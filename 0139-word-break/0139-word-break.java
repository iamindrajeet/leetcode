/*

Approach : DP(Memoization)
Intuition
The goal is to check if a string s can be segmented into words from a given dictionary (wordDict). We use a technique called dynamic programming with memoization to efficiently solve this problem by avoiding redundant computations.

Explanation
Initialization:

We start by creating a Boolean array memo of the same length as s. This array will store whether a substring starting from each index idx in s can be segmented into words from wordDict.
Recursive Function (solve):

The solve function checks if a substring starting from index idx in s can be segmented.
If idx is equal to or beyond the length of s, it means we've successfully segmented the entire string, so we return true.
If memo[idx] is already calculated (not null), we return its value. This avoids recalculating for the same substring.
Recursive Exploration:

We iterate through all possible end indices i of substrings starting from idx + 1 to len.
For each substring (s.substring(idx, i)), we check if it exists in wordDict.
If it does and the rest of the string can also be segmented (solve(len, i, s, wordDict, memo) returns true), we mark memo[idx] as true and return true.
Memoization:

If no valid segmentation is found for the current idx, we mark memo[idx] as false and return false. This ensures we don’t redo computations for the same substring.
Final Result:

The function wordBreak initiates the process by calling solve(len, 0, s, wordDict, memo), which checks if the entire string s starting from index 0 can be segmented into words from wordDict.


*/

class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        int len = s.length();
        Boolean[] memo = new Boolean[len];
        return solve(len, 0, s, wordDict, memo);
    }

    private boolean solve(int len, int idx, String s, List<String> wordDict, Boolean[] memo) {
        if (idx >= len)
            return true;
        
        if (memo[idx] != null)
            return memo[idx];
        
        for (int i = idx + 1; i <= len; i++) {
            if (wordDict.contains(s.substring(idx, i)) && solve(len, i, s, wordDict, memo)) {
                memo[idx] = true;
                return true;
            }
        }
        memo[idx] = false;
        return false;
    }
}
