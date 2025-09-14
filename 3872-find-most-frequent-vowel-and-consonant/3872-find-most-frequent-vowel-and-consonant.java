class Solution {
    public int maxFreqSum(String s) {
        // List of vowels for quick membership check
        List<Character> vowels = List.of('a', 'e', 'i', 'o', 'u');

        // Array to store the frequency of each character (a-z)
        int[] charsFreq = new int[26];

        // Variables to store the max frequency of vowels and consonants
        int maxFreqOfVowel = 0, maxFreqOfConsonant = 0;

        // Iterate through each character in the string
        for (char ch : s.toCharArray()) {
            // Calculate the index of the character (0 for 'a', 1 for 'b', ..., 25 for 'z')
            int charIndex = ch - 'a';

            // Increment the frequency of the character
            charsFreq[charIndex]++;

            // Check if the character is a vowel
            if (vowels.contains(ch)) {
                // Update max vowel frequency if needed
                maxFreqOfVowel = Math.max(maxFreqOfVowel, charsFreq[charIndex]);
            } else {
                // Otherwise, it's a consonant; update max consonant frequency if needed
                maxFreqOfConsonant = Math.max(maxFreqOfConsonant, charsFreq[charIndex]);
            }
        }
        return maxFreqOfConsonant + maxFreqOfVowel;
    }
}
