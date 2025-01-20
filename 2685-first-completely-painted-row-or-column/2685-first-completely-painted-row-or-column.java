/**
Approach 1 : Brute Force

Algorithm and Intuition
1. Mapping Numbers to Positions:
    - Use a HashMap to store the row and column indices of each number in the matrix mat. This allows for O(1) lookup 
    when processing each number from arr.
2.Simulating the Painting Process:
    - Iterate through the arr array. For each number:
        - Find its position in mat using the HashMap.
        - Mark the corresponding cell as painted (by setting it to -1).
        - Check if the current row or column is fully painted using helper methods.
3. Checking Completion:
    - Use helper methods checkRowPainted and checkColumnPainted to determine if all cells in a row or column are 
    painted (-1).
4. Return the Result:
    - Return the first index in arr where a row or column is completed. If no such index exists, return -1.

Time Complexity
1. Preprocessing:
    - Building the HashMap takes O(m * n), where m is the number of rows and n is the number of columns.
2. Processing the Array:
    - For each number in arr (length k), lookup in the HashMap takes O(1), but checking the row and column takes O(max
    (m, n)). Hence, this step takes O(k * max(m, n)).
Total Time Complexity:
    O(m * n + k * max(m, n)).

Space Complexity
1. HashMap:
    - The HashMap stores the positions of all numbers in the matrix, requiring O(m * n) space.
Total Space Complexity: O(m * n).
 */
class Solution {
    int m, n;
    public int firstCompleteIndex(int[] arr, int[][] mat) {
        m = mat.length;  // Number of rows
        n = mat[0].length;  // Number of columns

        // Map to store the position of each number in the matrix `mat`
        Map<Integer, int[]> numToPos = new HashMap<>();
        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                int val = mat[row][col];
                numToPos.put(val, new int[]{row, col});
            }
        }

        // Process the numbers in `arr` to paint cells
        for (int i = 0; i < arr.length; i++) {
            int num = arr[i];  // Current number being processed
            int[] coordinates = numToPos.get(num);
            int row = coordinates[0], col = coordinates[1];

            // Mark the current cell as painted
            mat[row][col] = -1;

            // Check if this painting completes a row or a column
            if (checkRowPainted(mat, row) || checkColumnPainted(mat, col)) {
                return i;  // Return the index where the row or column is first completed
            }
        }
        return -1;  // If no row or column is completed, return -1
    }

    private boolean checkRowPainted(int[][] mat, int row) {
        for (int col = 0; col < n; col++) {
            if (mat[row][col] != -1) {  // If any cell in the row is not painted
                return false;
            }
        }
        return true;  // All cells in the row are painted
    }
    
    private boolean checkColumnPainted(int[][] mat, int col) {
        for (int row = 0; row < m; row++) {
            if (mat[row][col] != -1) {  // If any cell in the column is not painted
                return false;
            }
        }
        return true;  // All cells in the column are painted
    }
}