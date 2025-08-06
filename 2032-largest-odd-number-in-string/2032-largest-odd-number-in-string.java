class Solution {
    public String largestOddNumber(String num) {
        int length = num.length();  // Store the length as an integer

        // Start from the last digit and move left
        while (length != 0) {
            char ch = num.charAt(length - 1);

            // Check if the digit is odd
            if ((ch - '0') % 2 != 0) {
                // Return the substring from start to this index (inclusive)
                return num.substring(0, length);
            } else {
                // Move to the next digit to the left
                length--;
            }
        }

        // If no odd digit is found, return an empty string
        return "";
    }
}
