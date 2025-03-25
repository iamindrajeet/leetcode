class Solution {
    public String makeFancyString(String s) {
        // Edge case: If the string is empty, return an empty result
        if (s == null || s.isEmpty()) {
            return "";
        }

        // Initialize variables
        char previousChar = s.charAt(0);  // Store the first character
        int charFrequency = 1;  // Track consecutive character frequency
        StringBuilder fancyString = new StringBuilder();  // Resultant string
        
        // Append the first character to the result
        fancyString.append(previousChar);

        // Iterate through the string starting from the second character
        for (int i = 1; i < s.length(); i++) {
            char currentChar = s.charAt(i);

            // If the current character is the same as the previous one, increment the frequency
            if (currentChar == previousChar) {
                charFrequency++;
            } else {
                // If a new character appears, reset frequency count
                previousChar = currentChar;
                charFrequency = 1;
            }

            // Append the character only if frequency is less than 3 (to prevent triple repetition)
            if (charFrequency < 3) {
                fancyString.append(currentChar);
            }
        }

        // Return the modified string
        return fancyString.toString();
    }
}
