/**
What is a Peak Element in a 2D Grid?
A peak element in a 2D grid is one that is strictly greater than its immediate neighbors (up, down, left, right).

This solution performs binary search on columns, and in each column finds the maximum element row-wise, which is a smart way to find a peak efficiently.

⏱ Time Complexity (TC)
Let:

m = number of rows

n = number of columns

Outer binary search on columns: O(log n)

For each column, you scan all rows to find the max: O(m)

Total Time Complexity = O(m * log n)

\U0001f9e0 Space Complexity (SC)
Only a few variables are used (no extra data structures)

Space Complexity = O(1)

*/
class Solution {
    public int[] findPeakGrid(int[][] mat) {
        int noOfRows = mat.length;
        int noOfCols = mat[0].length;
        int left = 0, right = noOfCols - 1;

        // Binary search on columns
        while (left <= right) {
            int mid = left + (right - left) / 2;

            // Find the row index with the maximum element in the current column
            int maxRowIndex = findMaxRowIndex(mat, noOfRows, mid);

            // Get left and right neighbors of the mid element (in the same row)
            int leftNum = mid - 1 >= 0 ? mat[maxRowIndex][mid - 1] : -1;
            int rightNum = mid + 1 < noOfCols ? mat[maxRowIndex][mid + 1] : -1;

            // If the current element is greater than both neighbors, it's a peak
            if (mat[maxRowIndex][mid] > leftNum && mat[maxRowIndex][mid] > rightNum) {
                return new int[] { maxRowIndex, mid };
            }

            // If the right neighbor is greater, move to the right half
            else if (mat[maxRowIndex][mid] < rightNum) {
                left = mid + 1;
            }

            // Otherwise, move to the left half
            else {
                right = mid - 1;
            }
        }

        // This return should never be reached if the input constraints are valid
        return new int[] { -1, -1 };
    }

    // Helper method to find the row index with the maximum element in a given column
    private int findMaxRowIndex(int[][] mat, int rows, int col) {
        int maxEle = -1;
        int maxRowIndex = 0;

        for (int i = 0; i < rows; i++) {
            if (mat[i][col] > maxEle) {
                maxEle = mat[i][col];
                maxRowIndex = i;
            }
        }

        return maxRowIndex;
    }
}
