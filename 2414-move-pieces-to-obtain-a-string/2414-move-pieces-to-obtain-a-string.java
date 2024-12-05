/*
Approach : Two pointer

1. Intuition
Instead of using additional data structures like queues or generating possible states, we can directly compare both strings by scanning them simultaneously using two pointers. These pointers will help us compare the corresponding 'L' and 'R' pieces. When we encounter underscores, we can simply skip over them because they don't affect the validity of the transformation.

What really matters is the relative positions of the 'L' and 'R' pieces and whether they can move to their target positions according to the movement rules. Each time we find an 'L' or 'R' in both strings (after skipping underscores), we can immediately check if the movement is possible based on their positions:

'L' pieces can only move left, so their position in the start string must be greater than or equal to their position in the target string.
'R' pieces can only move right, so their position in the start string must be less than or equal to their position in the target string.
To implement this, we use two pointers, startIndex and targetIndex, to traverse the start and target strings respectively. By making a single pass through the strings, we validate two key aspects:

Character Matching: Ensure that the sequence of 'L' and 'R' pieces is identical in both strings.
Position Constraints: Check that 'L' pieces don't need to move right and 'R' pieces don't need to move left.

2. Algorithm
- Initialize startLength as the length of the start string.
- Initialize two pointers, startIndex and targetIndex, both set to 0, to traverse the start and target strings.
- While either startIndex or targetIndex is less than startLength:
    - Skip underscores in the start string by incrementing startIndex until a non-underscore character is found or 
    the end of the string is reached.
    - Skip underscores in the target string by incrementing targetIndex until a non-underscore character is found or    
    the end of the string is reached.
    - If one string is fully traversed and the other is not, return false as both strings should be exhausted 
    simultaneously.
    - If the characters at start[startIndex] and target[targetIndex] do not match, return false as the transformations 
    are invalid.
    -  If the character is 'L' in start, ensure startIndex >= targetIndex (left pieces can only move left); otherwise, 
    return false.
    - If the character is 'R' in start, ensure startIndex <= targetIndex (right pieces can only move right); otherwise, 
    return false.
- Increment both startIndex and targetIndex to move to the next characters.
- If the loop ends without returning false, all conditions for a valid transformation are satisfied; return true.

3. Complexity Analysis
Let n be the size of the start and target strings.

Time complexity: O(n)

The algorithm iterates through both strings once, skipping underscores and comparing characters. Each character is processed at most once, resulting in a linear time complexity.

The inner while loops that skip underscores run in constant time for each character, so they do not increase the overall time complexity.
The main while loop runs until both indices reach the end of the strings, which takes O(n) time in the worst case.

Space complexity: O(1)

The space complexity is constant because the algorithm uses a fixed amount of extra space regardless of the input size.

The only additional space used is for the indices startIndex and targetIndex, which are single integer variables.
No additional data structures are used that grow with the input size.

*/


class Solution {
    public boolean canChange(String start, String target) {
        int startLength = start.length();

        // Pointers for start string and target string
        int startIndex = 0, targetIndex = 0;

        while(startIndex < startLength || targetIndex < startLength) {
            // Skip underscores in start
            while(startIndex < startLength && start.charAt(startIndex) == '_'){
                startIndex++;
            }
            // Skip underscores in target
            while(targetIndex < startLength && target.charAt(targetIndex) == '_'){
                targetIndex++;
            }

            // If one string is exhausted, both string should be exhausted
            if(startIndex == startLength || targetIndex == startLength){
                return startIndex == startLength && targetIndex == startLength;
            }

            // Check if the pieces match and follow movement rules
            if(start.charAt(startIndex) != target.charAt(targetIndex) || 
            (start.charAt(startIndex) == 'L' && startIndex < targetIndex) || 
            (start.charAt(startIndex) == 'R' && startIndex > targetIndex)){
                return false;
            }
            startIndex++;
            targetIndex++;
        }
        // If all conditions are satisfied, return true
        return true;
    }
}