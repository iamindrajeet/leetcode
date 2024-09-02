/*


- Time Complexity (TC):
Step 1: Calculating totalChalkSum takes O(n) time, where n is the number of students.
Step 2: The modulus operation takes O(1) time.
Step 3: Iterating through the students again takes O(n) time.
Overall, the time complexity is O(n).

- Space Complexity (SC):
The space complexity is O(1) since we're only using a few extra variables (totalChalkSum and remainingChalk), and no additional data structures that scale with input size are used.

*/

class Solution {
    public int chalkReplacer(int[] chalk, int k) {
        // Step 1: Calculate the total sum of chalk required by all students
        long totalChalkSum = 0;
        for (int chalkRequired : chalk) {
            totalChalkSum += chalkRequired;
        }

        // Step 2: Find the remaining chalk after complete rounds (modulus operation)
        int remainingChalk = (int)(k % totalChalkSum);

        // Step 3: Iterate through the students to find the first one who can't complete their required chalk usage
        for (int i = 0; i < chalk.length; i++) {
            // If the remaining chalk is less than the chalk required by the current student, return the index
            if (remainingChalk < chalk[i]) {
                return i;
            }
            // Subtract the chalk required by the current student from the remaining chalk
            remainingChalk -= chalk[i];
        }

        // If no such student is found (though the problem guarantees there will be one), return -1 as a fallback
        return -1;
    }
}
