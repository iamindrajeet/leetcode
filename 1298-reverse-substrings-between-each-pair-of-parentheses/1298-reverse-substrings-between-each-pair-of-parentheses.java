/*
Intuition and Logic
The problem requires us to reverse substrings within parentheses in a given string. To solve this problem, we can leverage a stack to keep track of the positions of the opening parentheses. When we encounter a closing parenthesis, we reverse the substring that is enclosed by the most recent unmatched opening parenthesis.

Steps:
Initialize a Stack and a StringBuilder:

Use a Stack to store the indices of the opening parentheses.
Use a StringBuilder to build the resulting string as we process each character.
Iterate through the String:

For each character in the string:
If it is an opening parenthesis (, push the current length of the result (which represents the position of the opening parenthesis) onto the stack.
If it is a closing parenthesis ), pop the last opening parenthesis position from the stack, then reverse the substring in result from this position to the current end (which is result.length() - 1).
If it is neither, append the character to result.
Reverse Substrings:

Define a helper method reverse that takes a StringBuilder, a start index, and an end index, and reverses the characters in that range.

Time Complexity (TC)
Traversing characters: We traverse each character exactly once, resulting in O(n).

Reversing substrings: The maximum number of characters we might reverse in a single operation is O(n).
Given that this reversal could happen multiple times, the worst-case scenario involves many such operations.

Thus, the worst-case time complexity can indeed be approximated as O(n^2).

Space Complexity (SC)
O(n): The space complexity is also O(n) due to the stack used to store indices of opening parentheses and the StringBuilder to build the result.

*/

class Solution {
    public String reverseParentheses(String s) {
        Stack<Integer> openBracket = new Stack<>();
        StringBuilder result = new StringBuilder();

        // Iterate through each character in the string
        for (char ch : s.toCharArray()) {
            if (ch == '(') {
                // Push the current length of the result to stack when encountering an opening bracket
                openBracket.push(result.length());
            } else if (ch == ')') {
                // Pop the start index from the stack for the current parentheses
                int start = openBracket.pop();
                // Reverse the substring from the start index to the current end index
                reverse(result, start, result.length() - 1);
            } else {
                // Append the current character to the result
                result.append(ch);
            }
        }
        return result.toString();
    }

    // Method to reverse a substring within a StringBuilder
    private void reverse(StringBuilder sb, int start, int end) {
        while (start < end) {
            char temp = sb.charAt(start);
            sb.setCharAt(start++, sb.charAt(end));
            sb.setCharAt(end--, temp);
        }
    }
}
