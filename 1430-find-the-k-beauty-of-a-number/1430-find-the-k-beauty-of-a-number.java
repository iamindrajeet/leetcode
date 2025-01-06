/*
Complexity
Time complexity: O(N)
Space complexity: O(1)
*/

class Solution {
    // Function to count the number of substrings of length 'k' that divide 'num'
    public int divisorSubstrings(int num, int k) {
        // Convert the integer 'num' to a string for easy substring extraction
        String str = String.valueOf(num);
        
        // Initialize count to track valid substrings
        int count = 0;
        
        // Loop through the string and extract all possible substrings of length 'k'
        // Note: We loop until str.length() - k + 1 to make sure we extract 'k' length substrings
        for (int i = 0; i < str.length() - k + 1; i++) {
            // Extract a substring of length 'k' starting at index i
            String temp = str.substring(i, i + k);
            
            // Convert the substring to an integer
            int n = Integer.valueOf(temp);
            
            // Skip if the number is zero (avoid division by zero)
            if (n == 0) 
                continue;
            
            // Check if 'num' is divisible by the substring number 'n'
            if (num % n == 0) 
                count++; // If divisible, increment the count
        }
        
        // Return the total count of valid substrings
        return count;
    }
}
