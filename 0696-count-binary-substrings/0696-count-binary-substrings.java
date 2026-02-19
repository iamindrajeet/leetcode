// class Solution {
//     public int countBinarySubstrings(String s) {
        
//         // Array to store lengths of consecutive groups of same characters
//         // Example: "001110" → groups = [2,3,1]
//         int[] groups = new int[s.length()];
        
//         // idx points to current group index in groups array
//         int idx = 0;
        
//         // First character always starts a group of size 1
//         groups[idx] = 1;
        
//         int count = 0;

//         // Build the groups array
//         for (int i = 1; i < s.length(); i++) {
            
//             // If current char is same as previous → extend current group
//             if (s.charAt(i - 1) == s.charAt(i))
//                 groups[idx]++;
//             else {
//                 // If different → start a new group
//                 idx++;
//                 groups[idx] = 1;
//             }
//         }

//         // Count valid binary substrings
//         // A valid substring exists between every pair of adjacent groups
//         // Number of substrings = min(size of previous group, size of current group)
//         for (int i = 1; i <= idx; i++) {
//             count += Math.min(groups[i - 1], groups[i]);
//         }

//         return count;
//     }
// }

class Solution {
    public int countBinarySubstrings(String s) {
        
        // prev = size of previous group
        // curr = size of current group
        int prev = 0, curr = 1;
        int count = 0;

        for (int i = 1; i < s.length(); i++) {
            
            // If same character → extend current group
            if (s.charAt(i) == s.charAt(i - 1)) {
                curr++;
            } else {
                // Group changed → add possible substrings from previous pair
                count += Math.min(prev, curr);
                
                // Move current group to previous, reset current
                prev = curr;
                curr = 1;
            }
        }

        // Add result for the last group pair
        count += Math.min(prev, curr);

        return count;
    }
}
