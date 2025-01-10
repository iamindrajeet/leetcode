/*

Approach 1: Brute Force

Intuition
Given the small constraints of the problem where words.length ≤ 100 (the array contains at most 100 words) and words[i].length, pref.length ≤ 100 (each word and the prefix can be up to 100 characters long), a brute-force approach is viable. This approach involves checking each word in the words list to see if it starts with pref. We can do this using two pointers, one for the current word and one for pref, both starting at index 0.

To implement this logic, we iterate through the list of words and for each word, compare its characters with the corresponding characters in pref up to the length of pref. If at any point the characters don't match or if the word's length is smaller than the length of pref, we stop checking that word and move to the next one.

The counter is incremented only when the prefix matches entirely. Finally, after examining all the words in the list, the counter holds the number of words that have pref as their prefix, which is returned as the result.

Algorithm
For the main method prefixCount:

1. Initialize a variable count to 0 to track the number of strings with the given prefix.
2. Iterate through each string in the input array words. For each string:
    - Add the result of hasPrefix to count.
3. Return the final count.

For the helper method hasPrefix:

1. Initialize a variable itr to track the current character position being compared.
2. Start a loop that continues while itr is less than both the length of str and pref:
    - Compare characters at position itr in both strings.
    - If characters don't match, return 0 immediately as the prefix is not found.
3. After the loop ends, check if itr equals the length of pref.
    - If not equal, return 0 as the string was too short to contain the prefix.
4. Return 1 indicating the prefix was found.

Complexity Analysis
Let n be the length of the input array words and m be the length of the prefix string pref.

Time complexity: O(n⋅m)

The outer loop in prefixCount iterates through each string in the array words, which takes O(n) operations.

For each string, we call hasPrefix which compares characters until it reaches the end of the prefix or finds a mismatch. In the worst case, this character comparison takes O(m) operations.

Therefore, the total time complexity is O(n⋅m).

Space complexity: O(1)

The algorithm only uses a constant amount of extra space regardless of the input size. We only store the counter variables count and itr. No additional data structures are created that grow with the input size. Thus, the space complexity is constant, O(1).

*/
class Solution {

    public int prefixCount(String[] words, String pref) {
        int count = 0;
        for (String word : words) {
            count += hasPrefix(word, pref);
        }
        return count;
    }

    // Returns 1 if str has pref as prefix, 0 otherwise
    private int hasPrefix(String str, String pref) {
        int itr;
        // Compare characters until we reach end of either string
        for (itr = 0; itr < str.length() && itr < pref.length(); itr++) {
            if (str.charAt(itr) != pref.charAt(itr)) {
                return 0; // Mismatch found
            }
        }

        // Check if we've matched entire prefix
        if (itr != pref.length()) {
            return 0; // str is shorter than pref
        }
        return 1;
    }
}