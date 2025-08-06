class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int noOfRows = matrix.length;
        int noOfCols = matrix[0].length;

        // Start from the top-right corner of the matrix
        // because the array is decreasing towards left and increasing towards bottom
        int row = 0;
        int col = noOfCols - 1;

        // Loop until we are within the bounds of the matrix
        while (row < noOfRows && col >= 0) {
            // Case 1: target found
            if (matrix[row][col] == target)
                return true;

            // Case 2: current element is greater than target
            // Move left to smaller elements in the row
            else if (matrix[row][col] > target) {
                col--;
            }

            // Case 3: current element is less than target
            // Move down to bigger elements in the column
            else {
                row++;
            }
        }
        // Target not found in matrix
        return false;
    }
}
