//Approach (Using simple Greedy)
//T.C : O(m+n)
//S.C : O(m*n) //You can exclude this because it is what is expected to return from the function
class Solution {
    public int[][] restoreMatrix(int[] rowSum, int[] colSum) {
        int m = rowSum.length; // Number of rows
        int n = colSum.length; // Number of columns

        int[][] matrix = new int[m][n]; // Initialize the matrix with given dimensions

        int i = 0, j = 0; // Initialize row and column pointers
        while (i < m && j < n) {
            // Assign the minimum of the current row sum and column sum to the matrix cell
            matrix[i][j] = Math.min(rowSum[i], colSum[j]);

            // Update the row and column sums
            rowSum[i] -= matrix[i][j];
            colSum[j] -= matrix[i][j];

            // Move to the next row if the current row sum is zero
            if (rowSum[i] == 0) {
                i++;
            }
            // Move to the next column if the current column sum is zero
            if (colSum[j] == 0) {
                j++;
            }
        }
        return matrix; // Return the restored matrix
    }
}
