/**
    * Time Complexity: O(n + m)
    * - We traverse both strings once, so the time complexity is O(n + m), 
    *   where n is the length of string s and m is the length of string t.
    *
    * Space Complexity: O(1)
    * - We are using a constant amount of extra space (only integer indices), 
    *   so the space complexity is O(1).
*/
class Solution {
    public boolean isSubsequence(String s, String t) {
        // If s is longer than t, s cannot be a subsequence of t.
        if(s.length() > t.length()) {
            return false;
        }
        
        int sIdx = 0, tIdx = 0;
        
        // Traverse both strings to check for subsequence.
        while(sIdx < s.length() && tIdx < t.length()) {
            // If characters match, move the pointer in s.
            if(s.charAt(sIdx) == t.charAt(tIdx)) {
                sIdx++;
            }
            // Always move the pointer in t.
            tIdx++;
        }
        
        // If sIdx has reached the end of s, it means s is a subsequence of t.
        return sIdx == s.length();
    }
}
