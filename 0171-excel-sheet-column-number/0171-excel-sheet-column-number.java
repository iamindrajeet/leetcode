class Solution {
    public int titleToNumber(String columnTitle) {
        int columnNumber = 0; // Initialize the result variable to store the column number
        int length = columnTitle.length(); // Length of the input columnTitle

        // Iterate through each character in the columnTitle
        for (int i = 0; i < length; i++) {
            // Convert the character to its 1-based numeric value ('A' -> 1, 'B' -> 2, ..., 'Z' -> 26)
            int num = columnTitle.charAt(i) - 'A' + 1;

            // Calculate the power of 26 for the current character's position
            int power = length - 1 - i; // The leftmost character has the highest power

            // Update the column number using the value and position power
            columnNumber += num * Math.pow(26, power);
        }

        return columnNumber; // Return the final column number
    }
}
