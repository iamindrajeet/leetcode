/**
Approach : Using 2 stacks - One for keeping track of the count of string another stack to keep track of the characters as string

T.C - O(N)
S.C - O(N)
*/
class Solution {
    public String decodeString(String s) {
        Stack<Integer> numStack = new Stack<>();   // Stack to store the repeat count (numbers)
        Stack<String> stringStack = new Stack<>(); // Stack to store characters and intermediate results
        int k = 0; // To store the current number being formed

        for (char ch : s.toCharArray()) {
            if (Character.isDigit(ch)) {
                // Form the complete number (handles multiple digits, e.g., "10")
                k = (k * 10) + (ch - '0');
                continue;
            }

            if (ch == '[') {
                // Push the current number to numStack and reset k
                numStack.push(k);
                k = 0;
                // Push "[" as a marker in stringStack
                stringStack.push(String.valueOf(ch));
                continue;
            }

            if (ch != ']') {
                // Push characters to stringStack
                stringStack.push(String.valueOf(ch));
                continue;
            }

            // If we encounter ']', pop characters until '[' is found
            StringBuilder temp = new StringBuilder();
            while (!stringStack.peek().equals("[")) {
                temp.insert(0, stringStack.pop()); // Extract the inner substring
            }
            // Remove the '['
            stringStack.pop();

            // Retrieve the repeat count from numStack
            Integer count = numStack.pop();

            // Repeat the extracted substring count times
            StringBuilder modifiedString = new StringBuilder();
            modifiedString.repeat(temp, count);

            // Push the expanded string back to stack
            stringStack.push(modifiedString.toString());
        }

        // Construct the final decoded string
        StringBuilder result = new StringBuilder();
        while (!stringStack.isEmpty()) {
            result.insert(0, stringStack.pop());
        }

        return result.toString();
    }
}
