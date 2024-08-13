class Solution {
    public int lengthOfLastWord(String s) {
        // Remove any trailing spaces from the string
        s = s.trim();
        
        // Initialize the length of the last word to 0
        int lengthOfLastWord = 0;
        
        // Traverse the string from the end towards the beginning
        for(int i = s.length() - 1; i >= 0; i--) {
            // If a space is encountered, return the current length of the last word
            if(s.charAt(i) == ' ')
                return lengthOfLastWord;
            
            // Increment the length of the last word
            lengthOfLastWord++;
        }
        
        // Return the length of the last word (if no space is encountered)
        return lengthOfLastWord;
    }
}

/**
 * Time Complexity: O(n)
 * - We traverse the string once from the end to the beginning. 
 * - Here, 'n' is the length of the string after trimming any trailing spaces.
 *
 * Space Complexity: O(1)
 * - We use a constant amount of extra space for the integer variable to store the length of the last word.
 */
