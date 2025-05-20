/**
 * Returns the elements of the matrix in spiral order.
 *
 * Spiral order means traversing the matrix in a clockwise spiral,
 * starting from the top-left element and moving inward.
 *
 * Example:
 * Input: [[1, 2, 3],
 *         [4, 5, 6],
 *         [7, 8, 9]]
 *
 * Output: [1, 2, 3, 6, 9, 8, 7, 4, 5]
 *
 * Time Complexity: O(m * n)
 * Space Complexity: O(1) extra space (excluding the output list)
 */
class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        int left = 0, right = n - 1;
        int top = 0, bottom = m - 1;

        List<Integer> result = new ArrayList<>();

        while (left <= right && top <= bottom) {
            // Traverse from left to right on the top row
            for (int i = left; i <= right; i++) {
                result.add(matrix[left][i]);
            }
            top++;

            // Traverse from top to bottom on the right column
            for (int i = top; i <= bottom; i++) {
                result.add(matrix[i][right]);
            }
            right--;

            // Traverse from right to left on the bottom row (if still valid)
            if (top <= bottom) {
                for (int i = right; i >= left; i--) {
                    result.add(matrix[bottom][i]);
                }
                bottom--;
            }

            // Traverse from bottom to top on the left column (if still valid)
            if (left <= right) {
                for (int i = bottom; i >= top; i--) {
                    result.add(matrix[i][left]);
                }
                left++;
            }
        }

        return result;
    }
}
