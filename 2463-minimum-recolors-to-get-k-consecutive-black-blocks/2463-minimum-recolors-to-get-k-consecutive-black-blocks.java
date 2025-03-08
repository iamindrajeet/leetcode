/**
Approach 1 : Brute Force 
T.C - 
*/
class Solution {
    public int minimumRecolors(String blocks, int k) {
        int len = blocks.length();
        int minOperations = Integer.MAX_VALUE;

        // Iterate over all possible substrings of length 'k'
        for (int i = 0; i <= len - k; i++) {
            int whiteCount = 0;

            // Count the number of 'W' in the current window of size 'k'
            for (int j = i; j - i + 1 <= k; j++) {
                if (blocks.charAt(j) == 'W')
                    whiteCount++;
            }

            // Update the minimum operations required
            minOperations = Math.min(minOperations, whiteCount);
        }

        return minOperations;
    }
}


/** 
Approach 2 - Sliding window
T.C - O(N)
S.C - O(1)
*/

// class Solution {
//     public int minimumRecolors(String blocks, int k) {
//         int len = blocks.length();
//         int i = 0, j = 0, whiteCount = 0;
//         int minOperations = Integer.MAX_VALUE;

//         while (j < len) {
//             // Count the number of white blocks in the current window
//             if (blocks.charAt(j) == 'W')
//                 whiteCount++;

//             // When window size reaches `k`, process the result
//             if (j - i + 1 == k) {
//                 // Update the minimum operations needed
//                 minOperations = Math.min(minOperations, whiteCount);
                
//                 // Slide the window by removing the leftmost element
//                 if (blocks.charAt(i) == 'W')
//                     whiteCount--;

//                 i++; // Move left pointer forward
//             }
//             j++; // Expand right pointer
//         }
//         return minOperations;
//     }
// }
