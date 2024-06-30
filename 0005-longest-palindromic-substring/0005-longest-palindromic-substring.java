/*
Brute-Force Approach
*/

class Solution {
    public String longestPalindrome(String s) {
        if (s.length() <= 1)
            return s;
        
        String longestPalindrome = "";
        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j < s.length(); j++) {
                if (isPalindrome(s, i, j)) {
                    String currentPalindrome = s.substring(i, j + 1);
                    if (currentPalindrome.length() > longestPalindrome.length()) {
                        longestPalindrome = currentPalindrome;
                    }
                }
            }
        }
        return longestPalindrome;
    }

    private boolean isPalindrome(String str, int i, int j) {
        int left = i;
        int right = j;

        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    } 
}
