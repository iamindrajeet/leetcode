class Solution {

    public int nextBeautifulNumber(int n) {
        // Loop through all numbers starting from n+1 
        // up to 1224444 (since all numerically balanced numbers 
        // are smaller than or equal to this bound — digits 1–4 repeated at most 4 times)
        for (int i = n + 1; i <= 1224444; i++) {
            // Check if the current number is numerically balanced
            if (isBalanced(i))
                return i; // Return the first such number greater than n
        }
        return -1; // Fallback (though this line is never reached in valid input range)
    }

    private boolean isBalanced(int num) {
        int[] digitsFreq = new int[10]; // To store frequency of each digit (0–9)

        // Count occurrences of each digit in the number
        while (num != 0) {
            int digit = num % 10;     // Extract the last digit
            digitsFreq[digit]++;      // Increment its count
            num /= 10;                // Remove the last digit
        }

        // Validate the "numerically balanced" condition
        for (int i = 0; i < 10; i++) {
            // If a digit appears in the number (freq > 0)
            // then its frequency must be exactly equal to the digit itself
            if (digitsFreq[i] > 0 && digitsFreq[i] != i)
                return false; // Not balanced
        }

        // If all digits satisfy the balanced condition, return true
        return true;
    }
}
