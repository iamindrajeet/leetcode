/**
 * Rotates a given n x n 2D matrix by 90 degrees clockwise **in-place**.
 * 
 * Steps:
 * 1. Transpose the matrix: Convert rows to columns.
 * 2. Reverse each row: This completes the 90-degree rotation.
 * 
 * Example:
 * Input:  [[1,2,3],
 *          [4,5,6],
 *          [7,8,9]]
 * 
 * Transpose: [[1,4,7],
 *             [2,5,8],
 *             [3,6,9]]
 * 
 * Reverse Rows: [[7,4,1],
 *                [8,5,2],
 *                [9,6,3]]
 *
 * Time Complexity: O(n^2)
 * - Transposing takes O(n^2)
 * - Reversing each row also takes O(n^2) in total
 *
 * Space Complexity: O(1)
 * - The rotation is performed in-place without using extra space.
 */
class Solution {
    public void rotate(int[][] matrix) {
        int n = matrix.length;

        // Step 1: Transpose the matrix (swap elements across the diagonal)
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                swap(matrix, i, j);
            }
        }

        // Step 2: Reverse each row
        for (int i = 0; i < n; i++) {
            reverseRow(matrix[i]);
        }
    }

    // Helper method to swap elements at (i, j) and (j, i)
    private void swap(int[][] matrix, int i, int j) {
        int temp = matrix[i][j];
        matrix[i][j] = matrix[j][i];
        matrix[j][i] = temp;
    }

    // Helper method to reverse a single row
    private void reverseRow(int[] row) {
        int left = 0, right = row.length - 1;
        while (left < right) {
            int temp = row[left];
            row[left] = row[right];
            row[right] = temp;
            left++;
            right--;
        }
    }
}
