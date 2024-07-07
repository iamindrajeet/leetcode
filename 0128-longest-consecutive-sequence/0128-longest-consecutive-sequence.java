/*
Logic and Intuition
The problem is to find the length of the longest consecutive sequence in an unsorted array of integers. The optimal solution uses a HashSet to achieve an average time complexity of O(n).

Use a HashSet:
We use a HashSet to store the elements of the array. This allows us to check for the existence of elements in constant time.
Iterate through the Array:
For each element, we check if it is the start of a sequence (i.e., the element before it is not in the set). If it is, we count the length of the sequence starting from that element.
Counting the Sequence:
For the starting element of a sequence, we incrementally check for the next elements in the sequence and keep a count of the length.
Update the Longest Sequence:
During the iteration, we keep track of the maximum length of the consecutive sequence found.\

Time Complexity : O(n)
Space Complexity
O(n): We use a HashSet to store all elements of the array.

*/
class Solution {
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        
        // Add all elements to the set
        for (int num : nums) {
            set.add(num);
        }
        
        int longestConsecutiveSeqLen = 0;
        
        // Iterate through each number in the array
        for (int num : nums) {
            // Check if this is the start of a sequence
            if (!set.contains(num - 1)) {
                int currentNum = num;
                int currentConsecutiveSeqLen = 1;
                
                // Count the length of the sequence
                while (set.contains(currentNum + 1)) {
                    currentNum++;
                    currentConsecutiveSeqLen++;
                }
                
                // Update the longest sequence length
                longestConsecutiveSeqLen = Math.max(longestConsecutiveSeqLen, currentConsecutiveSeqLen);
            }
        }
        
        return longestConsecutiveSeqLen;
    }
}
