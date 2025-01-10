//Approach - (Simple storing character frequency in map and checking)
//T.C : O(n*l1 + m*l2) , n = # of words in words1, l1 = average length of word in words1, similarly m and l2 for words2
//S.C : O(26) ~= O(1)
class Solution {
    public List<String> wordSubsets(String[] words1, String[] words2) {
        List<String> result = new ArrayList<>();

        // Step 1: Calculate the maximum frequency requirement for words2
        int[] freq2 = new int[26];
        for(String word : words2){
            int[] temp = new int[26];
            for(char ch : word.toCharArray()){
                temp[ch - 'a']++;
                freq2[ch - 'a'] = Math.max(freq2[ch - 'a'], temp[ch - 'a']);
            }
        }

        // Step 2: Check each word in words1
        for(String word : words1){
            int[] temp = new int[26];
            for(char ch : word.toCharArray()){
                temp[ch - 'a']++;  // Frequency array for the current word
            }
            // Check if the word satisfies the universal condition
            if (isSubset(freq2, temp)) {
                result.add(word);
            }
        }
        return result;
    }

    // Helper method to check if freq2 is a subset of temp
    private boolean isSubset(int[] freq2, int[] temp) {
        for(int i = 0; i < 26; i++){
            if(freq2[i] > temp[i])
                return false; // Not a subset
        }
        return true;
    }
}