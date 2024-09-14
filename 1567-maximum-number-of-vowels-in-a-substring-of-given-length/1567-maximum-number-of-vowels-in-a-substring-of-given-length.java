/*

- Approach

1. Identify Vowels:
First, we recognize which characters are vowels by storing them in a list. This list includes 'a', 'e', 'i', 'o', 'u'. Using a list allows us to quickly check if a character is a vowel.

2. Initial Count of Vowels:
We count how many vowels are there in the first k characters of s. This count gives us the number of vowels in the initial window (or segment) of the string.

3. Slide the Window:
We then slide this window one character at a time from the start to the end of the string. For each new position of the window:
    - Check the new character curr coming into the window (if it's a vowel, increase the count).
    - Check the character that's leaving the window toRemove (if it's a vowel, decrease the count).

4. Time Complexity (TC):
O(n), where n is the length of the string s. We loop through the string once to calculate the initial number of vowels and then use a sliding window to check each subsequent substring of length k.

5. Space Complexity (SC):
O(1) for extra space because the listOfVowels is fixed and the sliding window doesn't require additional space proportional to the input size. We only use a few variables to keep track of the count of vowels and intermediate results.

*/
class Solution {
    public int maxVowels(String s, int k) {
        // List containing all vowels
        List<Character> listOfVowels = List.of('a', 'e', 'i', 'o', 'u');
        
        // Calculate the initial number of vowels in the first 'k' characters of the string
        int maxVowels = countInitialVowels(s, listOfVowels, k);
        int currVowels = maxVowels;

        // Sliding window approach: shift the window by 1 character each time
        for (int i = k; i < s.length(); i++) {
            char curr = s.charAt(i);            // Current character to add to the window
            char toRemove = s.charAt(i - k);    // Character to remove from the window

            // If the current character is a vowel, increase the vowel count
            if (listOfVowels.contains(curr))
                currVowels++;
            
            // If the character to remove is a vowel, decrease the vowel count
            if (listOfVowels.contains(toRemove))
                currVowels--;

            // Update the maximum number of vowels found so far
            maxVowels = Math.max(maxVowels, currVowels);
        }
        return maxVowels;
    }

    /**
     * Helper method to count the number of vowels in the first 'limit' characters of the string.
     * 
     * @param s The input string
     * @param listOfVowels The list of vowels
     * @param limit The number of characters to check
     * @return The number of vowels found in the first 'limit' characters
     */
    private int countInitialVowels(String s, List<Character> listOfVowels, int limit) {
        int vowelCount = 0;
        // Check for vowels in the first 'limit' characters of the string
        for (int i = 0; i < limit; i++) {
            char c = s.charAt(i);
            if (listOfVowels.contains(c))
                vowelCount++;
        }
        return vowelCount;
    }
}
