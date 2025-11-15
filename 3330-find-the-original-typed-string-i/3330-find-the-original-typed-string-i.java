class Solution {
    public int possibleStringCount(String word) {

        // Start with 1 because the first character always forms one segment
        int count = 1;

        // Loop through the string starting from the second character
        for (int i = 1; i < word.length(); i++) {

            // If the current character is same as the previous character,
            // it means we can form an additional possible string segment
            if (word.charAt(i) == word.charAt(i - 1)) {
                count++;
            }
        }
        return count;
    }
}
