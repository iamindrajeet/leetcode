/*
Approach : Counting

1. Intuition and Algorithm
Every uncommon word occurs exactly once in total. We can count the number of occurrences of every word, then return ones that occur exactly once.

2. Complexity Analysis
    - Time Complexity: O(M+N), where M,N are the lengths of A and B respectively.
    - Space Complexity: O(M+N), the space used by count.
*/
class Solution {
    public String[] uncommonFromSentences(String s1, String s2) {
        // Split the input sentences into arrays of words
        String[] s1Words = s1.split(" ");
        String[] s2Words = s2.split(" ");
        
        // Create a map to keep track of the frequency of each word
        Map<String, Integer> freqMap = new HashMap<>();
        
        // Process words from the first sentence and update their counts in the map
        for (String word : s1Words) {
            freqMap.put(word, freqMap.getOrDefault(word, 0) + 1);
        }
        
        // Process words from the second sentence and update their counts in the map
        for (String word : s2Words) {
            freqMap.put(word, freqMap.getOrDefault(word, 0) + 1);
        }
        
        // Create a list to store words that are uncommon (appear exactly once)
        List<String> uncommonWords = new ArrayList<>();
        
        // Iterate over the map and add words with a frequency of 1 to the list
        for (String word : freqMap.keySet()) {
            if (freqMap.get(word) == 1) {
                uncommonWords.add(word);
            }
        }
        
        // Convert the list of uncommon words to an array and return it
        return uncommonWords.toArray(new String[0]);
    }
}
