/*
Time Complexity:
O(n): We traverse the string once, where n is the length of the string.

Space Complexity:
O(n): In the worst case, the stack may store all opening brackets, especially if the string consists only of opening brackets.

*/
class Solution {
    public boolean isValid(String s) {
        // If the string length is odd, it cannot be valid
        if (s.length() % 2 != 0)
            return false;

        // Stack to keep track of opening brackets
        Stack<Character> stack = new Stack<>();

        // Iterate through each character in the string
        for (char ch : s.toCharArray()) {
            // If it's an opening bracket, push it onto the stack
            if (ch == '(' || ch == '{' || ch == '[') {
                stack.push(ch);
            } 
            // If it's a closing bracket, check for a matching opening bracket
            else if (ch == ')' || ch == '}' || ch == ']') {
                // If the stack is empty, no matching opening bracket exists
                if (stack.isEmpty())
                    return false;

                // Pop the top character from the stack
                Character poppedChar = stack.pop();

                // Check if the popped character matches the current closing bracket
                if ((poppedChar == '(' && ch != ')') || 
                    (poppedChar == '{' && ch != '}') || 
                    (poppedChar == '[' && ch != ']'))
                    return false;
            }
        }

        // If the stack is empty, all brackets were matched correctly
        return stack.isEmpty();
    }
}
