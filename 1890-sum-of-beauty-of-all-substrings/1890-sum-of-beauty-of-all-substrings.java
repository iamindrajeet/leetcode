class Solution {
    public int beautySum(String s) {
        int sumOfBeauty = 0;
        // Iterate over all possible starting points of substrings
        for (int i = 0; i < s.length(); i++) {
            // Frequency array to keep track of character counts in current substring
            int[] freq = new int[26]; // for 'a' to 'z'

            // Expand the substring one character at a time
            for (int j = i; j < s.length(); j++) {
                freq[s.charAt(j) - 'a']++; // increment frequency of current character

                // Compute beauty of current substring and add it to the total sum
                int beauty = getBeauty(freq);
                sumOfBeauty += beauty;
            }
        }
        return sumOfBeauty;
    }
    private int getBeauty(int[] freq) {
        int maxFreq = 0;
        int minFreq = Integer.MAX_VALUE;

        for (int f : freq) {
            if (f > 0) { // consider only characters that appear in the substring
                maxFreq = Math.max(maxFreq, f);
                minFreq = Math.min(minFreq, f);
            }
        }

        return maxFreq - minFreq;
    }
}
