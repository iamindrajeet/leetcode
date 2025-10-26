class Solution {
    public boolean hasSameDigits(String s) {
        int n = s.length();
        char[] digits = s.toCharArray(); // Convert input string to a mutable character array

        // Keep reducing until only 2 digits remain
        while (n > 2) {
            // Compute each new digit as (digit[i] + digit[i+1]) % 10
            for (int i = 0; i < n - 1; i++) {
                int digit1 = digits[i] - '0';      // Convert char to int
                int digit2 = digits[i + 1] - '0';  // Convert next char to int
                int newDigitValue = (digit1 + digit2) % 10;
                digits[i] = (char) (newDigitValue + '0'); // Convert back to char
            }
            n--; // After each pass, one digit is effectively removed
        }

        // Compare the final two digits
        return digits[0] == digits[1];
    }
}
