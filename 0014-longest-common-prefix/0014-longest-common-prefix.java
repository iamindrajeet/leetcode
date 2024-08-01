/*
Time Complexity:  O ( m * n).  where m is the length of the prefix and n is the number of strings in the input array. 
Space complexity: O(1)
*/
class Solution {
    public String longestCommonPrefix(String[] strs) {
        // If the array is empty, return an empty string
        if (strs == null || strs.length == 0) {
            return "";
        }

        int n = strs.length;
        // Initialize the result with the first string in the array
        String result = strs[0];

        // Iterate through the remaining strings in the array
        for (int i = 1; i < n; i++) {
            // Check if the current string contains the result as a prefix
            // If not, reduce the length of the result by one character at a time
            while (strs[i].indexOf(result) != 0) {
                result = result.substring(0, result.length() - 1);
                // If result becomes empty, return an empty string as there's no common prefix
                if (result.isEmpty()) {
                    return "";
                }
            }
        }

        // Return the longest common prefix found
        return result;
    }
}
