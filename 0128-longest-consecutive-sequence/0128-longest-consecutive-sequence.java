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

/*

Intuition and Logic:
1.Sorting: By sorting the array, we can bring consecutive elements next to each other. This makes it easier to find consecutive sequences.
2.Iterate and Count: We iterate through the sorted array and count the length of consecutive sequences. If two elements are consecutive, we increase the count. If not, we reset the count to 1 (for the current element).
3.Update Longest Sequence: During each iteration, we keep track of the longest consecutive sequence found so far.
4.Handle Duplicates: If two consecutive elements are the same (duplicates), we skip resetting the count but do not increase it either.

Time Complexity (TC):

Sorting: The sorting step takes O(nlogn) where n is the number of elements in the array.
Iteration: The iteration through the array takes O(n).
Overall: The time complexity is O(nlogn).

Space Complexity (SC): O(1)

*/

// class Solution {
//     public int longestConsecutive(int[] nums) {
//         // Handle empty array
//         if (nums.length == 0) {
//             return 0;
//         }

//         // Sort the array
//         Arrays.sort(nums);

//         // Initialize the length of the longest consecutive sequence
//         int longestConsecutiveSeqLen = 1;
//         // Initialize the current sequence length
//         int count = 1;

//         // Iterate through the sorted array
//         for (int i = 0; i < nums.length - 1; i++) {
//             int currEle = nums[i];
//             int nextEle = nums[i + 1];

//             // If the next element is consecutive, increment the count
//             if (currEle + 1 == nextEle) {
//                 count++;
//             }
//             // If the next element is the same as the current element, skip it
//             else if (currEle != nextEle) {
//                 // Reset the count if the next element is not consecutive
//                 count = 1;
//             }

//             // Update the length of the longest consecutive sequence
//             longestConsecutiveSeqLen = Math.max(count, longestConsecutiveSeqLen);
//         }

//         return longestConsecutiveSeqLen;
//     }
// }


