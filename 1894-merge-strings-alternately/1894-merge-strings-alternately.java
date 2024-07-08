/*

Intuition
The problem asks to merge two strings alternately. Therefore, we can traverse both strings at the same time and pick each character alternately from both strings.

Approach
Initialize an empty string to store the merged result.
Traverse both input strings together, picking each character alternately from both strings and appending it to the merged result string.
Continue the traversal until the end of the longer string is reached.
Return the merged result string.

Complexity:

Time Complexity: The time complexity is O(n + m), where n is the length of word1 and m is the length of word2, because each character of both strings is processed once.

Space Complexity: The space complexity is also O(n + m) due to the space needed to store the resulting merged string.

*/
class Solution {
    public String mergeAlternately(String word1, String word2) {
        // StringBuilder to build the resulting merged string
        StringBuilder result = new StringBuilder();

        // Get the lengths of both strings
        int word1Len = word1.length(), word2Len = word2.length();

        // Initialize pointers for both strings
        int i = 0, j = 0;

        // Loop until we have processed all characters in both strings
        while (i < word1Len || j < word2Len) {
            // If there are characters left in word1, append the next character to result
            if (i < word1Len) {
                result.append(word1.charAt(i++));
            }

            // If there are characters left in word2, append the next character to result
            if (j < word2Len) {
                result.append(word2.charAt(j++));
            }
        }

        // Convert the StringBuilder to a String and return it
        return result.toString();
    }
}