/**
Time Complexity:
O(n²) — because for each index (n), you may traverse up to n elements in check().

Space Complexity:
O(n) — because of the cloned array used in each call.
*/
//  class Solution {
//     public int countValidSelections(int[] nums) {
//         // Step 1: Count how many elements are initially non-zero
//         int countOfNonZeroNos = 0;
//         int result = 0;
//         for (int num : nums) {
//             if (num > 0)
//                 countOfNonZeroNos++;
//         }

//         // Step 2: Try starting from every index and check in both directions
//         for (int i = 0; i < nums.length; i++) {
//             // Check moving to the right (direction = +1)
//             if (check(i, nums.clone(), countOfNonZeroNos, 1))
//                 result++;

//             // Check moving to the left (direction = -1)
//             if (check(i, nums.clone(), countOfNonZeroNos, -1))
//                 result++;
//         }

//         // Step 3: Return total number of valid selections
//         return result;
//     }

//     private boolean check(int idx, int[] temp, int countOfNonZeroNos, int direction) {
//         // Continue until we go out of bounds or all numbers become zero
//         while (idx >= 0 && idx < temp.length && countOfNonZeroNos > 0) {
//             if (temp[idx] > 0) {
//                 // When we encounter a non-zero number:
//                 // 1. Reverse direction
//                 // 2. Decrease the number by 1
//                 // 3. If that number becomes zero, decrement countOfNonZeroNos
//                 direction *= -1;
//                 temp[idx]--;
//                 if (temp[idx] == 0)
//                     countOfNonZeroNos--;
//             }

//             // Move in the current direction
//             idx += direction;
//         }

//         // Valid only if all numbers turned to 0
//         return countOfNonZeroNos == 0;
//     }
// }

/**
Time & Space Complexity

Time Complexity: O(n) : one pass to compute total sum + one pass to check zeros.

Space Complexity: O(1)
*/
class Solution {
    public int countValidSelections(int[] nums) {
        int totalSum = 0, result = 0;

        // Step 1: Compute total sum of all elements
        for (int num : nums)
            totalSum += num;

        int currSum = 0; // Keeps track of prefix sum as we iterate

        // Step 2: Traverse the array and check each index
        for (int num : nums) {
            currSum += num; // add current element to prefix sum

            // Only consider indices where element == 0
            if (num == 0) {
                // Left sum = sum including this index
                int leftSum = currSum;
                // Right sum = remaining sum after this index
                int rightSum = totalSum - leftSum;

                // Step 3: Determine if this zero index can be a valid selection point
                if (leftSum == rightSum)
                    result += 2; // can go either left or right
                else if (Math.abs(leftSum - rightSum) == 1)
                    result += 1; // can go in only one direction
            }
        }

        // Step 4: Return total valid selection count
        return result;
    }
}
