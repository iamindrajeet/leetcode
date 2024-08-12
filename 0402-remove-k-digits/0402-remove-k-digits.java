/*
1. Explanation:
    - Edge Case: If the number of digits to remove (k) is equal to the length of the number (num.length()), the result should be "0" since 
    removing all digits results in zero.

2. Stack Usage:
    - We use a stack to build the smallest possible number by iterating over each digit.
    - If the current digit is smaller than the top of the stack and we still have digits to remove (k > 0), we remove the top of the 
    stack (stack.pop()), thereby reducing k.

3. Final Adjustments:
    - If k is still greater than zero after the loop, we pop additional elements from the stack from the end of the number until k becomes zero.
    - We then convert the stack into a string, reverse it (since it was built in reverse order), and remove any leading zeros to form the final 
    number.

4. Time Complexity: O(n), where n is the length of the input string num. Each digit is pushed and popped from the stack at most once.

5. Space Complexity: O(n) for the stack and the resulting string builder.

*/
class Solution {
    public String removeKdigits(String num, int k) {
        // Edge case: if we need to remove all digits, return "0"
        if (num.length() == k) {
            return "0";
        }

        Stack<Character> stack = new Stack<>();
        
        // Iterate through each digit in the input number
        for (char digit : num.toCharArray()) {
            // Remove elements from the stack if they are greater than the current digit
            // and if we still have digits left to remove (k > 0)
            while (!stack.isEmpty() && k > 0 && stack.peek() > digit) {
                stack.pop();
                k--;
            }
            // Push the current digit onto the stack
            stack.push(digit);
        }

        // If there are still digits to remove, remove them from the end of the number
        while (k > 0) {
            stack.pop();
            k--;
        }

        // Build the final number from the stack
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        sb.reverse();  // The digits are in reverse order, so we need to reverse them

        // Remove leading zeros from the final number
        while (sb.length() > 1 && sb.charAt(0) == '0') {
            sb.deleteCharAt(0);
        }

        // Return the final number as a string
        return sb.toString();
    }
}
