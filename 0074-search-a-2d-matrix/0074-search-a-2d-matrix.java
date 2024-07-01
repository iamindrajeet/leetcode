class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        // If the matrix is null or has no rows or columns, return false.
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }

        int m = matrix.length; // Number of rows in the matrix
        int n = matrix[0].length; // Number of columns in the matrix
        int left = 0; // Start of the search range (first element in the 1D view)
        int right = m * n - 1; // End of the search range (last element in the 1D view)

        // Perform binary search
        while (left <= right) {
            // Find the middle index in the 1D view
            int mid = left + (right - left) / 2;
            
            // Convert the middle index in the 1D view to a row and column in the 2D matrix
            // int midValue = matrix[mid / n][mid % n]; correctly retrieves the element from
            // the 2D matrix that corresponds to the middle index in the conceptual 1D array.
            int midValue = matrix[mid / n][mid % n];

            // Check if the middle element is the target
            if (midValue == target)
                return true;
            // If the middle element is greater than the target, adjust the search range to
            // the left half
            else if (midValue > target)
                right = mid - 1;
            // If the middle element is less than the target, adjust the search range to the
            // right half
            else
                left = mid + 1;
        }

        // If the target is not found, return false
        return false;
    }
}
