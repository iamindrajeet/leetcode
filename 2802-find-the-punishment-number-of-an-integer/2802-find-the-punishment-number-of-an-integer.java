// /**
// Intuition & Approach:

// 1. Understanding the Partitioning Condition:
// For a number num, its square num² must be partitionable into contiguous substrings whose sum equals num.
// Example: num = 36 → 36² = 1296, possible partitions {1, 2, 9, 6} sum up to 36, so 36 is valid.
// Recursive Backtracking Approach:

// 2. Convert num² to a string.
//     - Try partitioning it into substrings from left to right.
//     - Keep a running sum of the chosen partitions.
//     - If the running sum exceeds num, prune the search.
//     - If we reach the end of the string and the sum equals num, return true.

// 3. Optimization:
// Prune unnecessary recursive calls when currSum > num.

// Algorithm:
// 1. Initialize punishmentNum = 0 to store the final result.
// 2. Iterate num from 1 to n:
//     - Compute squareNum = num * num.
//     - Convert squareNum to a string.
//     - Check if it can be partitioned using a helper method canPartition().
//     - If true, add squareNum to punishmentNum.
// 3. Return punishmentNum.

// Recursive Function (canPartition):
// 1. Base Case: If we reach the end of stringNum, return true if currSum == num, else false.
// 2. Iterate over possible partition sizes:
//     - Extract the substring and convert it to an integer.
//     - If adding it to currSum does not exceed num, recurse.
//     - If a valid partition is found, return true.
// 3. If no valid partition exists, return false.

// Time Complexity Analysis (TC)
// Let’s analyze the worst-case scenario.

// For each number num, we check its square, which has at most 2 * log(n) digits.
// The recursive function canPartition() explores all partitions of this string.
// In the worst case, the number of partitions is O(2^d), where d = 2 * log(n).
// The total complexity is approximately O(n * 2^(2 log(n))), which grows exponentially.
// Practical Complexity Reduction:
// Since n is typically small (≤ 1000), this approach works within reasonable time limits.

// Space Complexity Analysis (SC)
// The recursion depth is at most 2 * log(n), leading to a space complexity of O(log(n)).
// Other than recursion, we use constant extra space, making the overall space complexity O(log(n)).
// */

// class Solution {
//     public int punishmentNumber(int n) {
//         int punishmentNum = 0;

//         // Iterate over numbers from 1 to n
//         for (int num = 1; num <= n; num++) {
//             int squareNum = num * num; // Compute square of the number
//             String stringNum = String.valueOf(squareNum); // Convert to string

//             // If squareNum can be partitioned to sum up to num, add it to punishmentNum
//             if (canPartition(stringNum, num, 0, 0))
//                 punishmentNum += squareNum;
//         }
//         return punishmentNum;
//     }

//     /**
//      * Recursive function to check if we can partition the square number string into parts
//      * that sum up to the given number.
//      *
//      * @param stringNum The string representation of the squared number.
//      * @param num The original number whose square we are partitioning.
//      * @param i The current index in the string.
//      * @param currSum The sum of the partitions considered so far.
//      * @return True if a valid partition exists, False otherwise.
//      */
//     private boolean canPartition(String stringNum, int num, int i, int currSum) {
//         // Base case: If we have processed the whole string
//         if (i == stringNum.length()) 
//             return currSum == num; // Check if the accumulated sum equals num
        
//         // Optimization: If current sum exceeds num, terminate this branch early
//         if (currSum > num) 
//             return false;

//         // Try forming numbers of different lengths from index i
//         for (int j = i; j < stringNum.length(); j++) {
//             // Extract substring and convert it into a number
//             int leftNum = Integer.parseInt(stringNum.substring(i, j + 1));

//             // Recur for the remaining part of the string
//             if (canPartition(stringNum, num, j + 1, currSum + leftNum))
//                 return true; // If any partition is successful, return true
//         }
//         return false; // No valid partition found
//     }
// }

/**
Intuition & Approach:(Recursion + Memoization)

1. Understanding the Partitioning Condition:
For a number num, its square num² must be partitionable into contiguous substrings whose sum equals num.
Example: num = 36 → 36² = 1296, possible partitions {1, 2, 9, 6} sum up to 36, so 36 is valid.
Recursive Backtracking Approach:

2. Convert num² to a string.
    - Try partitioning it into substrings from left to right.
    - Keep a running sum of the chosen partitions.
    - If the running sum exceeds num, prune the search.
    - If we reach the end of the string and the sum equals num, return true.

3. Optimization:
Prune unnecessary recursive calls when currSum > num. We can also use memoization as problem contains computing solution for similar subproblems.

Algorithm:
1. Initialize punishmentNum = 0 to store the total sum.
2. Iterate from 1 to n:
    - Compute squareNum = num * num
    - Convert squareNum to a string for easy partitioning.
    - Initialize a memoization table memo[][] to store computed results.
    - If the square can be partitioned to sum up to num, add it to punishmentNum.
3. Recursive function canPartition(stringNum, num, i, currSum, memo):
    - Base case: If the entire string is processed (i == length), check if currSum == num.
    - If currSum > num, return false (pruning).
    - Check memo table: If already computed, return stored value.
    - Try all possible partitions from index i.
    - If any partition leads to a valid sum, store 1 in memo and return true.
    - Otherwise, store 0 and return false.
4. Return punishmentNum after iterating all numbers.

Time & Space Complexity Analysis
T.C : O(n * 2^(log10(n^2))) --> log10(n^2) is the no of digits after squaring (num * num) and 2 beacuse at every step we 
have two choice
S.C : O(n * log10(n^2))

*/

class Solution {
    public int punishmentNumber(int n) {
        int punishmentNum = 0;

        // Iterate over numbers from 1 to n
        for (int num = 1; num <= n; num++) {
            int squareNum = num * num; // Compute the square of the number
            String stringNum = String.valueOf(squareNum); // Convert to string

            // Memoization array to store results of subproblems
            int[][] memo = new int[stringNum.length()][num + 1];
            for (int[] row : memo) {
                Arrays.fill(row, -1); // Initialize memo array with -1 (uncomputed)
            }

            // If squareNum can be partitioned to sum up to num, add it to punishmentNum
            if (canPartition(stringNum, num, 0, 0, memo))
                punishmentNum += squareNum;
        }
        return punishmentNum;
    }

    /**
     * Recursive function to check if we can partition the squared number string into parts
     * that sum up to the given number.
     *
     * @param stringNum The string representation of the squared number.
     * @param num       The original number whose square we are partitioning.
     * @param i         The current index in the string.
     * @param currSum   The sum of the partitions considered so far.
     * @param memo      Memoization table to store computed results.
     * @return True if a valid partition exists, False otherwise.
     */
    private boolean canPartition(String stringNum, int num, int i, int currSum, int[][] memo) {
        // Base case: If we have processed the whole string, check if sum equals num
        if (i == stringNum.length()) 
            return currSum == num;

        // Optimization: If current sum exceeds num, terminate this branch early
        if (currSum > num) 
            return false;

        // Check memoization table to avoid recomputation
        if (memo[i][currSum] != -1)
            return memo[i][currSum] == 1;

        boolean possible = false;
        
        // Try forming numbers of different lengths starting from index i
        for (int j = i; j < stringNum.length(); j++) {
            // Extract substring and convert it into a number
            int leftNum = Integer.parseInt(stringNum.substring(i, j + 1));

            // Recur for the remaining part of the string
            if (canPartition(stringNum, num, j + 1, currSum + leftNum, memo)) {
                possible = true;
                break; // If found, no need to check further
            }
        }

        // Store the result in the memo table
        memo[i][currSum] = possible ? 1 : 0;
        return possible;
    }
}
