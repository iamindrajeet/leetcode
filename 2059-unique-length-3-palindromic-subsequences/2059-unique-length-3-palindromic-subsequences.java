/*
Explanation:

First and Last Index Arrays:

We maintain two arrays firstIdx and lastIdx of size 26 (for each letter 'a' to 'z'). These arrays store the first and last occurrences of each character in the string. We fill these arrays as we traverse through the string.
For example, if the string is "abca", firstIdx will store the index of 'a' as 0, and lastIdx will store the index of 'a' as 3.

Palindrome Subsequence Logic:

We loop through each character in the alphabet (a to z). If the character appears in the string (i.e., firstIdx[i] != -1), we check the substring that lies between the first and last occurrence of the character.
We want to count how many unique characters can form the middle of a palindromic subsequence with the character at the start and end. For this, we use a HashSet to store characters that appear between the first and last occurrence.
The size of the set gives the number of unique palindromic subsequences for that character.

Answer Calculation:

For each character, we count how many unique characters can exist in the middle of a palindromic subsequence, and accumulate this count in ans.

Time Complexity:

Traversing the string: We loop through the string once to determine the firstIdx and lastIdx arrays, which takes O(n), where n is the length of the string.
Processing each character: We process each character in the alphabet (26 characters). For each character that appears in the string, we check the characters in the substring between its first and last occurrence. In the worst case, we traverse the entire string again for each of the 26 characters. This leads to a time complexity of O(26×n)=O(n).
HashSet operations: In the worst case, each iteration over a character's middle section involves inserting elements into a HashSet, which is an O(1) operation for each character.

Thus, the overall time complexity is O(n), where n is the length of the string.

Space Complexity:
We maintain two arrays firstIdx and lastIdx of size 26, which takes O(26)=O(1) space.
The HashSet used to track the unique characters in the middle of a subsequence can have at most n characters in the worst case, so the space used by the HashSet is O(n).
Thus, the overall space complexity is O(n).

Summary:
Time Complexity: O(n), where n is the length of the string.
Space Complexity: O(n), due to the space used by the HashSet and the input string itself.

*/
class Solution {
    public int countPalindromicSubsequence(String s) {
        // firstIdx[i] will store the first occurrence index of character 'i' (a=0, b=1, ..., z=25)
        // lastIdx[i] will store the last occurrence index of character 'i' (a=0, b=1, ..., z=25)
        int[] firstIdx = new int[26];
        int[] lastIdx = new int[26];

        // Initialize all values in firstIdx and lastIdx to -1 (meaning no occurrence found yet)
        Arrays.fill(firstIdx, -1);
        Arrays.fill(lastIdx, -1);

        // Loop through the string and update the first and last occurrences of each character
        for (int i = 0; i < s.length(); i++) {
            int idx = s.charAt(i) - 'a'; // Calculate index for the character (0 for 'a', 1 for 'b', etc.)
            if(firstIdx[idx] == -1)
                firstIdx[idx] = i; // If this is the first occurrence of the character, record it
            lastIdx[idx] = i; // Always update the last occurrence of the character
        }

        int ans = 0; // Variable to store the number of palindromic subsequences

        // Iterate over all characters (a to z)
        for (int i = 0; i < 26; i++) {
            // If the character does not appear in the string, skip it
            if (firstIdx[i] == -1)
                continue;
            
            // Set to store unique characters that can be in the middle of the palindromic subsequence
            Set<Character> set = new HashSet<>();
            
            // Loop through all characters between the first and last occurrence of character 'i'
            // These characters can potentially form the middle of a palindromic subsequence
            for (int middle = firstIdx[i] + 1; middle < lastIdx[i]; middle++) {
                set.add(s.charAt(middle)); // Add the character to the set (only unique characters are counted)
            }
            
            // The number of unique characters in the set will give us the number of palindromic subsequences
            // where the character 'i' is the first and last character of the subsequence.
            ans += set.size();
        }
        
        return ans; // Return the final count of palindromic subsequences
    }
}
