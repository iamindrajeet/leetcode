/*
Approach (Recur + Memoization) : O(m*n)

Explanation with Intuition:
Main Function (minPathSum):

Purpose: Initializes necessary variables (m, n, memo) and starts computing the minimum path sum from the top-left corner (0, 0) of the grid.
Initialization: Creates a 2D array memo (memoization table) to store computed results of minimum path sums for each cell in the grid.
Memoization: Uses -1 as an initial value in memo to indicate that results for corresponding cells haven't been computed yet.
Recursive Function (maxPathSum):

Purpose: Computes the minimum path sum recursively from (i, j) to (m-1, n-1) in the grid.
Base Case: If (i, j) reaches the bottom-right corner (m-1, n-1), returns grid[i][j] as it is the end of the path.
Memoization: Checks if the result for (i, j) is already computed and stored in memo, returning it if available to avoid redundant calculations.
Recursive Calls: Handles three scenarios:
Moving only right (i == m - 1).
Moving only down (j == n - 1).
Choosing the minimum path sum by comparing moving down and moving right using Math.min.


Time Complexity (TC):
Explanation: The time complexity of this solution is O(m*n), where m is the number of rows and n is the number of columns in the grid.

Reasoning:
Each cell in the grid is computed and stored once in the memoization table memo.
Recursive calls for each cell are made once, and each cell's result is accessed in constant time from memo.


Space Complexity (SC):
Explanation: The space complexity is O(m*n) due to the memoization table memo.
Reasoning:
The memoization table memo occupies space proportional to the number of cells in the grid (m*n).

Summary:
Overall Approach: This Java solution uses dynamic programming with memoization to efficiently compute the minimum path sum in a grid.
Efficiency: Achieves O(m * n) time complexity by avoiding redundant calculations through memoization.
Optimization: Memoization ensures that each computed minimum path sum for a cell is stored and reused, enhancing overall efficiency and performance.

*/

class Solution {
    public int minPathSum(int[][] grid) {
        int m = grid.length; // Number of rows in the grid
        int n = grid[0].length; // Number of columns in the grid
        
        // Memoization table to store computed minimum path sums
        int[][] memo = new int[m][n];
        
        // Initialize memoization table with -1 (or any suitable initialization)
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        
        // Start computing minimum path sum from top-left corner (0, 0)
        return maxPathSum(grid, 0, 0, m, n, memo);
    }

    // Recursive function to compute the minimum path sum from (i, j) to (m-1, n-1)
    public int maxPathSum(int[][] grid, int i, int j, int m, int n, int[][] memo) {
        // Base case: If we reach the bottom-right corner of the grid
        if (i == m - 1 && j == n - 1) {
            return grid[i][j]; // Return the value at the bottom-right corner
        }

        // If result is already memoized, return it
        if (memo[i][j] != -1) {
            return memo[i][j];
        }

        // Calculate minimum path sum recursively
        if (i == m - 1) { // We can only move right if we are at the last row
            memo[i][j] = grid[i][j] + maxPathSum(grid, i, j + 1, m, n, memo);
        } else if (j == n - 1) { // We can only move down if we are at the last column
            memo[i][j] = grid[i][j] + maxPathSum(grid, i + 1, j, m, n, memo);
        } else {
            // Choose the minimum path sum by comparing moving down and moving right
            memo[i][j] = grid[i][j] + Math.min(maxPathSum(grid, i + 1, j, m, n, memo), 
                                                maxPathSum(grid, i, j + 1, m, n, memo));
        }

        // Return the computed minimum path sum for the current cell (i, j)
        return memo[i][j];
    }
}
