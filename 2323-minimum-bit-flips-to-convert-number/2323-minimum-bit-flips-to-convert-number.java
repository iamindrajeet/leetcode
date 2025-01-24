/**

Approach 1: Brute Force
Intuition
The simplest method is to check each bit of both numbers, one by one. For each bit position, we check if the bits differ. If they do, we need to flip that bit in the start number to match the goal. We count how many bits need to be flipped as we move from the least significant bit to the most significant bit. Although simple, we need to check each bit individually, which can be slow for large numbers.

Algorithm
1. Initialize a counter count to keep track of the number of bit flips needed.
2. Loop while either start or goal has bits left to check:
    - Compare the least significant bits (rightmost bits) of start and goal:
        - Use the bitwise AND operation (& 1) to isolate the least significant bit of each number.
        - If the bits differ ((start & 1) != (goal & 1)), increment the count by 1.
    - Right shift both start and goal by one position (>>= 1) to move to the next bit.
3. Return the total count after all bits have been checked.

Complexity Analysis
Time Complexity: O(max bits)
Space Complexity: O(1)
*/
class Solution {
    public int minBitFlips(int start, int goal) {
        int count = 0;
        while (start > 0 || goal > 0) {
            // Increment count if the current bits differ
            if ((start & 1) != (goal & 1)) {
                count++;
            }
            // Shift both numbers to the right to check the next bits
            start >>= 1;
            goal >>= 1;
        }
        return count;
    }
}