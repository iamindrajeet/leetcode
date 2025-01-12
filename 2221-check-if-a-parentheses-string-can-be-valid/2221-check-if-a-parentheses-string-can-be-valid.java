/*
Time Complexity (TC):
The algorithm iterates over the string s once, which is O(n) where n is the length of the string.
The operations with stacks (push and pop) are O(1) each.
Overall, the time complexity is O(n).

Space Complexity (SC):
Two stacks are used: one for open brackets (openBrackets) and one for unlocked positions (openCloseBrackets).
In the worst case, the space used by the stacks is proportional to the length of the string, i.e., O(n).
Thus, the space complexity is O(n).

*/
class Solution {
    public boolean canBeValid(String s, String locked) {
        int n = s.length();
        
        // If the length of the string is odd, it cannot be valid
        if (n % 2 != 0) 
            return false;

        // Stack to keep track of indices of open brackets '('
        Stack<Integer> openBrackets = new Stack<>();
        
        // Stack to keep track of indices of unlocked brackets (where locked[i] == '0')
        Stack<Integer> openCloseBrackets = new Stack<>();

        // Iterate through the string and process each character
        for (int i = 0; i < n; i++) {
            // If the bracket is unlocked (locked[i] == '0')
            if (locked.charAt(i) == '0') {
                openCloseBrackets.push(i);  // Push the index to openCloseBrackets stack
            }
            // If the character is an open bracket '('
            else if (s.charAt(i) == '(') {
                openBrackets.push(i);  // Push the index to openBrackets stack
            }
            // If the character is a closing bracket ')'
            else if (s.charAt(i) == ')') {
                // If there is a matching open bracket '('
                if (!openBrackets.isEmpty()) {
                    openBrackets.pop();  // Remove it from the stack
                }
                // If there is no matching open bracket, but we have an unlocked spot
                else if (!openCloseBrackets.isEmpty()) {
                    openCloseBrackets.pop();  // Pop from unlocked positions stack
                }
                // If no matching open bracket and no unlocked spot, it's invalid
                else {
                    return false;
                }
            }
        }

        // After processing all characters, check if we can still match all open brackets
        // Compare the top of both stacks to see if we can still pair them
        while (!openBrackets.isEmpty() && !openCloseBrackets.isEmpty() && openBrackets.peek() < openCloseBrackets.peek()) {
            openBrackets.pop();  // Remove matched pairs
            openCloseBrackets.pop();
        }

        // If there are no unmatched open brackets, the string can be valid
        return openBrackets.isEmpty(); // If empty, it means valid; otherwise, invalid
    }
}
