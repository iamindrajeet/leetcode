/*
Approach : Using LinkedHashSet

Time Complexity : O(n)
Space Complexity : O(n)

*/
class Solution {
    public String kthDistinct(String[] arr, int k) {
       Map<String, Integer> freqMap = new LinkedHashMap<>();
        
        // Count the frequency of each string in the array
        for (String str : arr) {
            freqMap.put(str, freqMap.getOrDefault(str, 0) + 1);
        }
       
        // Check if there are enough distinct elements
        int distinctCount = 0;
        for (String str : freqMap.keySet()) {
            if (freqMap.get(str) == 1) {
                distinctCount++;
            }
        }

        if (distinctCount < k) {
            return ""; // Not enough distinct elements
        }
        
        // Iterate over the keys and find the k-th distinct string
        for (String s : freqMap.keySet()) {
            if (freqMap.get(s) == 1) {
                k--;
                if (k == 0) {
                    return s;
                }
            }
        }
        return ""; // If k-th distinct string is not found
    }
}