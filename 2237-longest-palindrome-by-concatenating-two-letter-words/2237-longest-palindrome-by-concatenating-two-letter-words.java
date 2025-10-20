/**
- Time Complexity:

Building frequency map → O(n)

Loop over words for pairing → O(n)

Loop over map for palindromes → O(n) in worst case
Total: O(n)

- Space Complexity:

HashMap for frequencies → O(n) in worst case (all unique words)
Total: O(n)
*/
class Solution {
    public int longestPalindrome(String[] words) {
        // Step 1: Store frequency of each 2-letter word
        // Example: words = ["ab","ty","yt","lc","cl","ab"]
        // freq = { "ab"=2, "ty"=1, "yt"=1, "lc"=1, "cl"=1 }
        Map<String, Integer> freq = new HashMap<>();
        for (String word : words) {
            freq.put(word, freq.getOrDefault(word, 0) + 1);
        }

        int length = 0;
        boolean centerAvailable = false;

        // Step 2: Handle non-palindromic word pairs (like "ab" and "ba")
        // If both word and its reverse exist, they can form a 4-letter palindrome segment
        // Example: "ab" + "ba" -> contributes 4 to palindrome length
        for (String w : words) {
            String rev = new StringBuilder(w).reverse().toString();
            // Only pair if:
            //  - word and reverse are different
            //  - both have positive frequency
            if (!w.equals(rev) && freq.getOrDefault(w, 0) > 0 && freq.getOrDefault(rev, 0) > 0) {
                freq.put(w, freq.get(w) - 1);
                freq.put(rev, freq.get(rev) - 1);
                length += 4;
            }
        }

        // Step 3: Handle palindromic words like "gg", "aa" etc.
        // - Pair them among themselves: each pair adds 4 to the length
        // - If there’s an odd occurrence left, we can use one in the center (+2)
        for (Map.Entry<String, Integer> entry : freq.entrySet()) {
            String word = entry.getKey();
            int count = entry.getValue();

            if (word.charAt(0) == word.charAt(1)) {  // Palindromic word check
                length += (count / 2) * 4;          // Each pair contributes 4 length
                if (count % 2 == 1) {
                    centerAvailable = true;        // One can be placed at center
                }
            }
        }

        // Step 4: Add center word if available
        // Only one palindromic word can be placed in the center
        if (centerAvailable) {
            length += 2;
        }

        return length;
    }
}