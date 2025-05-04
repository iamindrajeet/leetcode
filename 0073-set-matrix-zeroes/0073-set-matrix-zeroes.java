/**
Approach  1 - Brute Force

✅ Intuition:
When a cell contains 0, we are required to set its entire row and column to 0. However, modifying the matrix directly while iterating can lead to incorrect results—newly changed 0s can affect other parts unintentionally.

To prevent that, we mark the cells that should be turned into 0 later using a temporary marker (-1) that does not interfere with the current logic.

✅ Approach: Using -1 as Temporary Marker
1. Traverse the matrix:
    - When a 0 is found at (i, j), mark all non-zero elements in the i-th row and j-th column with -1.
2. Traverse the matrix again:
    - Convert all -1 values to 0.

✅ Time Complexity: O(m * n) * O(m + n) + O(m * N)
First pass to find and mark → O(m * n)
markRows --> O(n), markCols --> O(m)
Second pass to convert markers → O(m * n)

m = number of rows, n = number of columns

✅ Space Complexity: O(1)
No extra space used except a few variables

Marking is done in-place
*/
// class Solution {
//     public void setZeroes(int[][] matrix) {
//         int rows = matrix.length;
//         int cols = matrix[0].length;

//         // Step 1: Mark rows and columns with -1 wherever 0 is found
//         for (int i = 0; i < rows; i++) {
//             for (int j = 0; j < cols; j++) {
//                 if (matrix[i][j] == 0) {
//                     // Mark the entire row and column (excluding actual 0s)
//                     markRows(matrix, i, cols);
//                     markCols(matrix, j, rows);
//                 }
//             }
//         }

//         // Step 2: Replace all temporary markers (-1) with 0
//         for (int i = 0; i < rows; i++) {
//             for (int j = 0; j < cols; j++) {
//                 if (matrix[i][j] == -1) {
//                     matrix[i][j] = 0;
//                 }
//             }
//         }
//     }

//     // Marks all non-zero elements in the given row as -1
//     private void markRows(int[][] matrix, int i, int cols) {
//         for (int k = 0; k < cols; k++) {
//             if (matrix[i][k] != 0) {
//                 matrix[i][k] = -1;
//             }
//         }
//     }

//     // Marks all non-zero elements in the given column as -1
//     private void markCols(int[][] matrix, int j, int rows) {
//         for (int k = 0; k < rows; k++) {
//             if (matrix[k][j] != 0) {
//                 matrix[k][j] = -1;
//             }
//         }
//     }
// }


/**
✅ Approach: Extra Space Using Marker Arrays
We use two auxiliary arrays to keep track of which rows and columns need to be zeroed. This avoids modifying the matrix immediately during the first pass.

✅ Time Complexity: O(m * n)
First pass to mark the rows and columns → O(m * n)

Second pass to update the matrix → O(m * n)

✅ Space Complexity: O(m + n)
rowMatrix of size m

colMatrix of size n
*/
// class Solution {
//     public void setZeroes(int[][] matrix) {
//         int rows = matrix.length;
//         int cols = matrix[0].length;

//         // rowMatrix[i] == 1 means row i should be set to zero
//         // colMatrix[j] == 1 means column j should be set to zero
//         int[] rowMatrix = new int[rows];
//         int[] colMatrix = new int[cols];

//         // Step 1: Identify the rows and columns that need to be zeroed
//         for (int i = 0; i < rows; i++) {
//             for (int j = 0; j < cols; j++) {
//                 if (matrix[i][j] == 0) {
//                     rowMatrix[i] = 1;
//                     colMatrix[j] = 1;
//                 }
//             }
//         }

//         // Step 2: Update the matrix
//         // If a cell's row or column is marked, set it to 0
//         for (int i = 0; i < rows; i++) {
//             for (int j = 0; j < cols; j++) {
//                 if (rowMatrix[i] == 1 || colMatrix[j] == 1) {
//                     matrix[i][j] = 0;
//                 }
//             }
//         }
//     }
// }

/**

✅ Intuition:
To avoid using extra space and still keep track of which rows/columns need to be zeroed, we reuse the first row and first column of the matrix itself as markers.

But since both matrix[0][0] belongs to both the first row and the first column, we use an additional variable col0 to track the state of the first column.

✅ Approach:
1. First pass (top to bottom):
    - Mark the first cell of each row and column with 0 if a 0 is encountered.
    - Use col0 to separately track whether the first column needs to be zeroed.

2. Second pass (bottom to top):
    - Start from matrix[1][1] and update cells to 0 if their row or column is marked.
    - Finally update the first row and column based on initial markers.

✅ Time Complexity: O(m * n)
Two passes over the matrix: one for marking, one for updating.

✅ Space Complexity: O(1)
No extra space used except a few variables.

Everything is done in-place using the matrix itself.
*/
class Solution {
    public void setZeroes(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int col0 = 1; // Flag for whether the first column should be zeroed

        // Step 1: Use first row and column as markers
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0; // Mark row
                    if (j != 0) {
                        matrix[0][j] = 0; // Mark column
                    } else {
                        col0 = 0; // Mark first column separately
                    }
                }
            }
        }

        // Step 2: Use the markers to set matrix cells to zero
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

        // Step 3: Handle the first row if needed
        if (matrix[0][0] == 0) {
            for (int j = 0; j < cols; j++) {
                matrix[0][j] = 0;
            }
        }

        // Step 4: Handle the first column if needed
        if (col0 == 0) {
            for (int i = 0; i < rows; i++) {
                matrix[i][0] = 0;
            }
        }
    }
}
