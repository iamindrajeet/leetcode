class Solution {
    public int[] plusOne(int[] digits) {
        int lastIdx = digits.length - 1; // Get the index of the last digit

        // Traverse the array from the end to the start
        while (lastIdx >= 0 && digits[lastIdx] == 9) {
            digits[lastIdx] = 0; // Set current digit to 0
            lastIdx--; // Move to the next digit
        }

        // If we have gone past the first digit and need to add a new leading 1
        if (lastIdx < 0) {
            // Create a new array with an additional space for the new leading 1
            int[] resultArray = new int[digits.length + 1];
            resultArray[0] = 1; // Set the first element to 1
            // No need to copy the old array as the remaining elements are already 0 by default
            return resultArray;
        }

        // Increment the digit that is not 9
        digits[lastIdx] += 1;

        return digits; // Return the updated digits array
    }
}
