class Solution {
    public int canBeTypedWords(String text, String brokenLetters) {
        // Create a set to store all broken letters for fast lookup (O(1) time).
        Set<Character> broken = new HashSet<>();
        for (char ch : brokenLetters.toCharArray()) {
            broken.add(ch);
        }

        int res = 0;         // To store the number of words that can be typed
        boolean flag = true; // Indicates whether the current word can be typed

        // Iterate through each character in the text
        for (char ch : text.toCharArray()) {
            if (ch == ' ') {
                // End of a word. If the word was typeable (flag == true), increment result.
                if (flag) res++;
                // Reset the flag for the next word
                flag = true;
            } else if (broken.contains(ch)) {
                // Current character is broken, so the word can't be typed
                flag = false;
            }
        }

        // After the loop, we need to check the last word (since it may not end with a space)
        if (flag) res++;

        return res;
    }
}
