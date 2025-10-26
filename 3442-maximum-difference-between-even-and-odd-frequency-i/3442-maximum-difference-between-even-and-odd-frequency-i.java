class Solution {
    public int maxDifference(String s) {
        // Create a HashMap to store the frequency of each character in the string
        Map<Character, Integer> freqCount = new HashMap<>();
        
        // Iterate through each character in the string and update its frequency
        for(char ch : s.toCharArray()) {
            freqCount.put(ch, freqCount.getOrDefault(ch, 0) + 1);
        }
        
        // Initialize variables to track the maximum odd frequency
        // and minimum even frequency
        int maxOddFreq = 0;
        int minEvenFreq = Integer.MAX_VALUE;
        
        // Iterate through the frequency map to find:
        // - the maximum frequency among characters with odd counts
        // - the minimum frequency among characters with even counts
        for(Map.Entry<Character, Integer> m : freqCount.entrySet()) {
            int freq = m.getValue();
            if(freq % 2 == 0) {
                // Update minimum even frequency
                minEvenFreq = Math.min(minEvenFreq, freq);
            } else {
                // Update maximum odd frequency
                maxOddFreq = Math.max(maxOddFreq, freq);
            }
        }
        
        // If no even frequency exists, set minEvenFreq to 0
        minEvenFreq = minEvenFreq == Integer.MAX_VALUE ? 0 : minEvenFreq;
        
        // Return the difference between the maximum odd frequency
        // and the minimum even frequency
        return maxOddFreq - minEvenFreq;
    }
}
