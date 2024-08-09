/*
Approach : Manual Scan

Intuition
One brute-force approach is to consider each 3 x 3 subarray of the grid and manually check if each subarray satisfies the definition of a 3 x 3 magic square.

We iterate through the entire grid, examining each possible 3 x 3 subarray. For each subarray, we'll check each element to make sure that it is within the allowed range and that it isn't a duplicate. Then, we verify that the sums of all three rows, three columns, and the two diagonals are equal. If all these conditions are met, then the subarray is a magic square.

Algorithm
    1. Initialize ans to 0, representing the total count of magic squares.
    2. Define a helper function isMagicSquare(grid, row, col) that determines if the subarray of grid starting at index (row, col) is a magic square:
        - For each element num of the subarray:
            - If it falls outside the allowed range (num > 9 or num < 1), return false
            - If we have seen num in the previous iteration, that means the values aren't distinct, so return false
        - Initialize diagonal1 and diagonal2 as the sums for the 2 diagonals.
        - If diagonal1 != diagonal2, return false
        - Initialize row1, row2, and row3 as the sums for the 3 rows.
        - If any of the row sums don't equal diagonal1, then there are different sums for the rows and columns, so return false
        - Initialize col1, col2, and col3 as the sums for the 3 columns.
        - Similarly, if any of the column sums don't equal diagonal1, return false
    3. For each index (row, col) of grid:
        - If isSquareMagic(grid, row col) is true, then increment ans.
    4. Return ans.


Time Complexity
Let M and N be the number of rows and columns of grid, respectively.

Time Complexity: O(M⋅N)

The number of 3 x 3 subarrays to check for grid is linearly proportional to the size of grid, which is M⋅N. For each 3 x 3 subarray of grid, we iterate through all its values to check that they are distinct and within range, which takes constant time. We also perform the sum calculations that involve additional array indexing into a grid, which also takes constant time. Thus, the total time complexity is O(M⋅N).

Space Complexity: O(1)

isMagicSquare uses an array to keep track of which values the current subarray of grid contains. However, this array has a constant size of 10, so the space complexity is O(1)


*/
class Solution {
    public int numMagicSquaresInside(int[][] grid) {
        int ans = 0;
        int m = grid.length;
        int n = grid[0].length;
        
        // Iterate through each 3x3 sub-grid in the given grid
        for(int row = 0; row + 2 < m; row++){
            for(int col = 0; col + 2 < n; col++){
                // Check if the current 3x3 grid is a magic square
                if(isMagicSquare(grid, row, col))
                    ans++;
            }
        }
        return ans;  // Return the count of magic squares found
    }

    private boolean isMagicSquare(int[][] grid, int row, int col){
        boolean[] seen = new boolean[10];  // Track the numbers 1-9 to ensure no duplicates
        
        // Check each number in the 3x3 grid
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                int num = grid[row + i][col + j];
                // Numbers must be between 1 and 9
                if(num < 1 || num > 9)
                    return false;
                // Numbers must be unique
                if(seen[num])
                    return false;
                seen[num] = true;
            }
        }

        // Check if both diagonals have the same sum
        int diagonal1 = grid[row][col] + grid[row + 1][col + 1] + grid[row + 2][col + 2];
        int diagonal2 = grid[row + 2][col] + grid[row + 1][col + 1] + grid[row][col + 2];
        if(diagonal1 != diagonal2)
            return false;
        
        // Check if all rows have the same sum as the diagonals
        int row1 = grid[row][col] + grid[row][col + 1] + grid[row][col + 2];
        int row2 = grid[row + 1][col] + grid[row + 1][col + 1] + grid[row + 1][col + 2];
        int row3 = grid[row + 2][col] + grid[row + 2][col + 1] + grid[row + 2][col + 2];
        if(row1 != diagonal1 || row2 != diagonal1 || row3 != diagonal1)
            return false;
        
        // Check if all columns have the same sum as the diagonals
        int col1 = grid[row][col] + grid[row + 1][col] + grid[row + 2][col];
        int col2 = grid[row][col + 1] + grid[row + 1][col + 1] + grid[row + 2][col + 1];
        int col3 = grid[row][col + 2] + grid[row + 1][col + 2] + grid[row + 2][col + 2];
        if(col1 != diagonal1 || col2 != diagonal1 || col3 != diagonal1)
            return false;

        // If all checks pass, it is a magic square
        return true;
    }
}
