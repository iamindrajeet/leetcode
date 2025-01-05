
/*
Overview
We are given a string s consisting of lowercase English letters and a 2D array shifts, where each entry is a triplet [start, end, direction]. Each shift operation in shifts updates a range of characters [start, end] in the string s in the following way:

If direction == 1: Shift each character in the range forward in the alphabet. For example, 'a' becomes 'b', and 'z' wraps around to 'a'.
If direction == 0: Shift each character in the range backward in the alphabet. For example, 'b' becomes 'a', and 'a' wraps around to 'z'.
A direct implementation would involve iterating over each range [start, end] for every shift operation and updating the characters in that range individually.
Since applying each shift involves iterating over a substring of s, and this approach has a quadratic time complexity which is inefficient for the problem's constraints.

Instead of applying each shift directly, we can optimize by focusing on the net effect of all shifts on each character. This means that rather than updating the string multiple times for each operation, we calculate how many total shifts each character undergoes. Once the total shifts numberOfShifts for each character have been calculated, we can use the following formula to create the final string in one pass:

newChar=’a’+(oldChar−’a’+numberOfShifts) mod 26.
​
 
Here,

oldChar−’a’: Converts the character to a 0-based index in the range [0, 25] (e.g., 'a' = 0, 'b' = 1, ..., 'z' = 25).
numberOfShifts: Applies the total shifts to the character.
 mod 26: Ensures the result wraps around the alphabet if necessary (e.g., shifting 'z' forward yields 'a').
’a’+... : Converts the 0-based index back to a character.
Calculating the total effect of all shifts on each character is a key step toward optimizing the solution. However, this calculation does not reduce the time complexity compared to the naive approach. This is because it still involves iterating over all the substrings specified by the shifts array and updating a counter for every character in those ranges.


Approach: Difference Array
Intuition
Building on the idea of cumulative sums, we can use a difference array to handle range updates more efficiently. A difference array helps us record changes in values between consecutive elements rather than updating every element in a range directly.

Instead of keeping track of how many shifts should be applied to each character in the alphabet, we’ll use the difference array to store how many more shifts should be applied to the current character compared to the previous one. This allows us to record changes only at the starting and ending points of shifts, rather than updating each character in the range.

For convenience, a positive shift means that the character must move forward in the alphabet, and a negative shift means that it must move backward.

Algorithm
1. Initialize n to the size of the string s.
2. Initialize an array of length n, called diffArray, and set all its elements to 0.
3. For every shift = [start, end, direction] in shifts:
    - If direction == 1 (shift forward):
        - Increment diffArray[start] by 1, indicating that s[start] is shifted forward one more time than the previous  
        character.
        - If end + 1 < n:
            -Decrement diffArray[end + 1] by 1, as the character exactly after the shift range is shifted forward one 
            time less than the previous character.
    - If direction == 0 (shift backward):
        - Decrement diffArray[start] by 1, as s[start] is shifted backward one more time than the previous character.
        - If end + 1 < n:
            - Increment diffArray[end + 1] by 1, as the character exactly after the shift range is shifted backward one 
            time less than the previous character.
4. Initialize numberOfShifts to 0.
5. Initialize a string result of length n.
6. Iterate over s with i from 0 to n - 1:
    - Add diffArray[i] to numberOfShifts and take it mod 26.
    - If numberOfShifts < 0, increment numberOfShifts by 26.
    - Set result[i] to the shifted character: 'a' + (s[i] - 'a' + numberOfShifts) % 26.
7. Return result.

Complexity Analysis
Let n be the size of the string s and m the size of the shifts array.

Time complexity: O(n+m)

We are iterating over the shifts array to find the difference between the shifts of any two consecutive characters of s. On each iteration, we only perform constant-time operations (accessing and updating two elements of the diffArray) and therefore the initialization of the diffArray requires O(m) time. Then, we create the resulting string with a single pass over the original, which contributes O(n) to the total time complexity.

Space complexity: O(n)

We are using an array of size n to store the differences in the shifts between consecutive characters. We are also creating a new string result of length n to avoid modifying the input directly. These data structures have a size that is linear to the length of the input string and therefore the algorithm requires O(n) extra space.

*/

class Solution {

    public String shiftingLetters(String s, int[][] shifts) {
        int n = s.length();
        int[] diffArray = new int[n]; // Initialize a difference array with all elements set to 0.

        // Process each shift operation
        for (int[] shift : shifts) {
            int start = shift[0];
            int end = shift[1];
            int direction = shift[2];

             if (direction == 1) { // If direction is forward (1)
                diffArray[start] += 1; // Increment at the start index
                if (end + 1 < n) {
                    diffArray[end + 1] -= 1; // Decrement at the end+1 index
                }
            } else { // If direction is backward (0)
                diffArray[start] -= 1; // Decrement at the start index
                if (end + 1 < n) {
                    diffArray[end + 1] += 1; // Increment at the end+1 index
                }
            }
        }

        StringBuilder result = new StringBuilder(s);
        int numberOfShifts = 0;

        // Apply the shifts to the string
        for (int i = 0; i < n; i++) {
            numberOfShifts = (numberOfShifts + diffArray[i]) % 26; // Update cumulative shifts, keeping within the
                                                                   // alphabet range
            if (numberOfShifts < 0)
                numberOfShifts += 26; // Ensure non-negative shifts

            // Calculate the new character by shifting `s[i]`
            char shiftedChar = (char) ('a' +
                    ((s.charAt(i) - 'a' + numberOfShifts) % 26));
            result.setCharAt(i, shiftedChar);
        }

        return result.toString();
    }
}