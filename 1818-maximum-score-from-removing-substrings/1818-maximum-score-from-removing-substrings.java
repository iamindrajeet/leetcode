//Approach-1 (Using Stack)
//T.C : O(n)
//S.C : O(n)
class Solution {
    public int maximumGain(String s, int x, int y) {
        int n = s.length();
        int score = 0;

        // Determine the order of substrings to remove based on their scores
        String maxStr = (x > y) ? "ab" : "ba";
        String minStr = (x < y) ? "ab" : "ba";

        // First Pass: Remove the higher-scoring substring
        String tempFirst = removeSubstring(s, maxStr);
        int removedPairsCount = (n - tempFirst.length()) / 2;
        score += removedPairsCount * Math.max(x, y);

        // Second Pass: Remove the lower-scoring substring
        String tempSecond = removeSubstring(tempFirst, minStr);
        removedPairsCount = (tempFirst.length() - tempSecond.length()) / 2;
        score += removedPairsCount * Math.min(x, y);

        return score;
    }

    // Helper method to remove all instances of a given substring and return the remaining string
    private String removeSubstring(String s, String matchStr) {
        Stack<Character> stack = new Stack<>();

        for (char ch : s.toCharArray()) {
            // If the top of the stack and the current character form the substring, pop the stack
            if (!stack.isEmpty() && ch == matchStr.charAt(1) && stack.peek() == matchStr.charAt(0)) {
                stack.pop();
            } else {
                stack.push(ch);
            }
        }

        // Build the remaining string from the stack without using reverse
        StringBuilder remainStr = new StringBuilder();
        for (char ch : stack) {
            remainStr.append(ch);
        }

        return remainStr.toString();
    }
}

/*
Intuition and Logic:
1. The problem requires maximizing the score by removing specific substrings ("ab" and "ba") from the input string s.
2. The order of removal matters since removing higher-scoring substrings first can lead to a higher total score.
3. We use a stack to help remove the substrings efficiently.
4. In the first pass, we remove the higher-scoring substring, calculate the score, and get the remaining string.
5. In the second pass, we remove the lower-scoring substring from the remaining string and update the score again.
6. The `removeSubstring` method uses a stack to find and remove instances of the given substring without using the reverse method.

Time Complexity (TC):
- The time complexity is O(n) for traversing the string twice.

Space Complexity (SC):
- The space complexity is O(n) for the stack used to store characters of the string.
*/
