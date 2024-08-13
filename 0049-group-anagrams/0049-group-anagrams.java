class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        // A map to group anagrams together based on their sorted character sequence
        Map<String, List<String>> map = new HashMap<>();
        
        for (String str : strs) {
            // Convert the string to a character array and sort it
            char[] letters = str.toCharArray();
            Arrays.sort(letters);
            
            // Create a string from the sorted character array
            String sortedWord = new String(letters);
            
            // If the sorted string is already a key in the map, add the original string to its list
            if (map.containsKey(sortedWord)) {
                map.get(sortedWord).add(str);
            } else {
                // If the sorted string is not in the map, create a new list and add the original string
                List<String> anagramGroup = new ArrayList<>();
                anagramGroup.add(str);
                map.put(sortedWord, anagramGroup);
            }
        }
        
        // Return all the grouped anagrams as a list of lists
        return new ArrayList<>(map.values());
    }
}

/**
 * Time Complexity: O(n * k * log k)
 * - We iterate through each string in the input array, where 'n' is the number of strings.
 * - For each string, we sort it, which takes O(k * log k) time, where 'k' is the maximum length of a string.
 * - Overall, the time complexity is O(n * k * log k).
 *
 * Space Complexity: O(n * k)
 * - The space complexity is O(n * k) because we store each string in the map and the output list.
 * - Here, 'n' is the number of strings, and 'k' is the maximum length of a string.
 */
