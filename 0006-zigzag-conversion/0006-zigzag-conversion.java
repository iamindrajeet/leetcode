class Solution {
    public String convert(String s, int numRows) {
        if (numRows == 1) {
            return s; // If numRows is 1, the zigzag pattern is just the original string
        }

        // Initialize an array of StringBuilder objects for each row
        StringBuilder[] rowsString = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++) {
            rowsString[i] = new StringBuilder();
        }

        int rowNo = 0; // To track the current row
        int direction = 1; // To track the direction (1 for down, -1 for up)

        // Iterate over each character in the input string
        for (char ch : s.toCharArray()) {
            rowsString[rowNo].append(ch); // Append the character to the current row

            // Change direction when reaching the top or bottom row
            if (rowNo == 0) {
                direction = 1;
            } else if (rowNo == numRows - 1) {
                direction = -1;
            }

            rowNo += direction; // Move to the next row
        }

        // Combine all rows into a single string
        StringBuilder result = new StringBuilder();
        for (StringBuilder sb : rowsString) {
            result.append(sb);
        }

        return result.toString(); // Return the final converted string
    }
}
/*
- Time Complexity:
O(n): The time complexity is linear with respect to the length of the input string s, as we iterate over each character once.

- Space Complexity:
O(n): The space complexity is linear due to the additional storage required for the StringBuilder array, which holds all characters of the input string.

*/