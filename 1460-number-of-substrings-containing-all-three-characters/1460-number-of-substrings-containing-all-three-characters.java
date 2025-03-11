/**
T.C = O(2n) = O(n)
S.C = O(1)
*/
class Solution {
    public int numberOfSubstrings(String s) {
        // Array to store the count of 'a', 'b', and 'c'
        int[] chars = new int[3]; 
        int n = s.length();
        int i = 0, j = 0;
        int result = 0;

        // Sliding window approach to find substrings containing at least one of each character
        while (j < n) {
            char ch = s.charAt(j);
            chars[ch - 'a']++; // Increment the count of the current character
            
            // Check if the current window contains at least one 'a', one 'b', and one 'c'
            while (chars[0] > 0 && chars[1] > 0 && chars[2] > 0) {
                result += (n - j); // Count all valid substrings starting from index i
                chars[s.charAt(i) - 'a']--; // Shrink the window from the left
                i++; // Move the left pointer
            }
            j++; // Expand the window from the right
        }
        return result;
    }
}
