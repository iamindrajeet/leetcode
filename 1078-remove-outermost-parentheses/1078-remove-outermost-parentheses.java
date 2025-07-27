/**
✅ Time & Space Complexity
Time: O(n) → single scan of the string

Space: O(n) → for output
*/

class Solution {
    public String removeOuterParentheses(String s) {
        StringBuilder sb = new StringBuilder();
        int depth = 0; // depth tells how "inside" we are in nested parentheses

        for (char ch : s.toCharArray()) {
            if (ch == '(') {
                // ✅ If depth > 0, it means this '(' is NOT the outermost one,
                // so we keep it in the result
                if (depth > 0) sb.append(ch);

                // Move one level deeper
                depth++;
            } else {
                // We're closing one level
                depth--;

                // ✅ If depth > 0 AFTER decreasing,
                // it means this ')' is NOT the outermost one,
                // so we keep it in the result
                if (depth > 0) sb.append(ch);
            }
        }
        return sb.toString();
    }
}
