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
// class Solution {
//     int m, n;
//     public int firstCompleteIndex(int[] arr, int[][] mat) {
//         m = mat.length;  // Number of rows
//         n = mat[0].length;  // Number of columns

//         // Map to store the position of each number in the matrix `mat`
//         Map<Integer, int[]> numToPos = new HashMap<>();
//         for (int row = 0; row < m; row++) {
//             for (int col = 0; col < n; col++) {
//                 int val = mat[row][col];
//                 numToPos.put(val, new int[]{row, col});
//             }
//         }

//         // Process the numbers in `arr` to paint cells
//         for (int i = 0; i < arr.length; i++) {
//             int num = arr[i];  // Current number being processed
//             int[] coordinates = numToPos.get(num);
//             int row = coordinates[0], col = coordinates[1];

//             // Mark the current cell as painted
//             mat[row][col] = -1;

//             // Check if this painting completes a row or a column
//             if (checkRowPainted(mat, row) || checkColumnPainted(mat, col)) {
//                 return i;  // Return the index where the row or column is first completed
//             }
//         }
//         return -1;  // If no row or column is completed, return -1
//     }

//     private boolean checkRowPainted(int[][] mat, int row) {
//         for (int col = 0; col < n; col++) {
//             if (mat[row][col] != -1) {  // If any cell in the row is not painted
//                 return false;
//             }
//         }
//         return true;  // All cells in the row are painted
//     }
    
//     private boolean checkColumnPainted(int[][] mat, int col) {
//         for (int row = 0; row < m; row++) {
//             if (mat[row][col] != -1) {  // If any cell in the column is not painted
//                 return false;
//             }
//         }
//         return true;  // All cells in the column are painted
//     }
// }

/**

Approach 2 : Brute Force Optimized with Counting

This approach improves efficiency by using rowCountPaint and columnCountPaint arrays to track the painted cells directly, eliminating the need to traverse entire rows or columns to check for completion.

Algorithm and Intuition
1. Mapping Numbers to Positions:
    - Use a HashMap to store the row and column indices of each number in mat for quick access during the painting 
    process.

2. Tracking Painted Cells:
    - Maintain two arrays:
        - rowCountPaint: Tracks the count of painted cells in each row.
        - columnCountPaint: Tracks the count of painted cells in each column.

3. Simulating the Painting Process:
    - Iterate through arr, and for each number:
        - Find its position in mat using the HashMap.
        - Increment the corresponding row and column counts.
        - Check if the count for the row or column matches its size (n for rows, m for columns).

4. Return the Result:
    - Return the index in arr where a row or column is first completed. If no row or column is completed, return -1.

Time Complexity
1. Preprocessing:
    - Building the HashMap takes O(m * n), where m is the number of rows and n is the number of columns.
2. Processing the Array:
    - For each element in arr (length k), lookup in the HashMap and updating the arrays take O(1) each. This step 
    takes O(k).
Total Time Complexity:O(m * n + k).

Space Complexity
1. HashMap: The HashMap stores the positions of all numbers in the matrix, requiring O(m * n) space.
2. Auxiliary Arrays: rowCountPaint and columnCountPaint require O(m + n) space.
Total Space Complexity: O(m * n + m + n).
*/

// class Solution {
//     int m, n;
//     public int firstCompleteIndex(int[] arr, int[][] mat) {
//         m = mat.length;  // Number of rows
//         n = mat[0].length;  // Number of columns

//         // Map to store the position of each number in the matrix `mat`
//         Map<Integer, int[]> numToPos = new HashMap<>();
//         for (int row = 0; row < m; row++) {
//             for (int col = 0; col < n; col++) {
//                 int val = mat[row][col];
//                 numToPos.put(val, new int[]{row, col});  // Map number to its coordinates
//             }
//         }

//         // Arrays to track the count of painted cells in each row and column
//         int[] rowCountPaint = new int[m];
//         int[] columnCountPaint = new int[n];

//         // Process the numbers in `arr` to paint cells
//         for (int i = 0; i < arr.length; i++) {
//             int num = arr[i];  // Current number being processed
//             int[] coordinates = numToPos.get(num);  // Get the position of the number in the matrix
//             int row = coordinates[0], col = coordinates[1];

//             // Increment the paint count for the corresponding row and column
//             rowCountPaint[row]++;
//             columnCountPaint[col]++;

//             // Check if the current row or column is completely painted
//             if (rowCountPaint[row] == n || columnCountPaint[col] == m) {
//                 return i;  // Return the first index where row or column is completed
//             }
//         }
//         return -1;  // If no row or column is completed, return -1
//     }
// }

/**
Approach 3: Reverse Mapping

Intuition
In Approach 2, we were checking the count of "painted" elements for each row and column after every marking operation. Now, instead of that, we track the greatest index at which an element of each row and column occurs in arr. This will reduce space usage and eliminate the need for redundant checks, as we won’t need the rowCountPaint and CoucolumnntPaint arrays anymore.

Similarly to the previous approaches, we begin by mapping each number to its position (index) in arr, using a hashmap, numToIndex.

Instead of counting marked numbers, we consider a different question: When will a row or column be fully painted? Intuitively, this happens when all the numbers in that row or column have been processed. Building on this idea, we observe that it suffices to track the latest index in arr where each number in a row or column appears. If we know the greatest index for any element in a row or column, that row or column will be fully painted once that index is reached.

For example, consider a row of mat, which contains the numbers 3, 5, and 8. If their indices in arr are 1, 3, and 2 respectively, the row will be fully painted when index 3 (the largest index for any number in that row) in arr is reached.

After determining the greatest index for each row and column, we identify the row or column with the smallest maximum index, as this represents the first to be fully painted.

Algorithm
1. Initialize a numToIndex unordered map to store the index of each element from arr.
2. Populate numToIndex by iterating over the arr and recording the index of each element.
3. Initialize lastElementIndex to INT_MAX and result to INT_MIN to track the earliest complete row or column.
4. Initialize m and n to the number of rows and columns in the matrix mat, respectively.
5. Check for the earliest row to be completely painted:
    - Iterate through each row in the matrix mat:
        - Initialize result to INT_MIN for each row.
        - Iterate through each column in the current row:
            - For each element in the row, find its index in numToIndex and update lastElementIndex with the maximum 
            of its current value and index of the current element in arr.
        - Update result with the minimum of lastElementIndex and the row's result.
6. Check for the earliest column to be completely painted:
    - Iterate through each column in the matrix mat:
        - Initialize lastElementIndex to INT_MIN for each column.
        - Iterate through each row in the current column:
            - For each element in the column, find its index in numToIndex and update lastElementIndex with the 
            maximum index value.
        - Update result with the minimum of lastElementIndex and the column's result.
7. Return result, which represents the earliest index where a row or column has been completely painted.

Complexity Analysis
Let k= m * n be the size of arr (since arr.length == m * n), m the number of rows in mat, and n the number of columns in mat.

Time complexity: O(m*n)

We first build a map to store the index of each element in arr, which takes O(k) time. Then, we check for the earliest row and column to be completely painted, which takes O(m*n) time. Since k=m*n, the total time complexity is 
O(m*n).

Space complexity: O(k)≡O(m*n)

We use a map to store the index of each element in arr, which requires O(k) space. Other variables use constant space, so the total space complexity is O(k)≡O(m*n).
*/

class Solution {
    /**
     * This method finds the first index in the array `arr` such that the corresponding cell in `mat`
     * completes painting an entire row or column.
     * 
     * @param arr The sequence of numbers representing the painting order.
     * @param mat The 2D matrix containing the initial numbers.
     * @return The first index in `arr` that completes a row or column in `mat`.
     */
    public int firstCompleteIndex(int[] arr, int[][] mat) {
        int m = mat.length;  // Number of rows
        int n = mat[0].length;  // Number of columns

        // Map to store the index of each number in `arr`
        Map<Integer, Integer> numToIndex = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            numToIndex.put(arr[i], i);
        }

        int result = Integer.MAX_VALUE;  // Initialize result to a large value

        // Check for the earliest row to be completely painted
        for (int row = 0; row < m; row++) {
            int lastElementIndex = Integer.MIN_VALUE;  // Track the latest index in this row
            for (int col = 0; col < n; col++) {
                int val = mat[row][col];
                int idx = numToIndex.get(val);
                lastElementIndex = Math.max(lastElementIndex, idx);
            }
            // Update result with the minimum index where this row is fully painted
            result = Math.min(result, lastElementIndex);
        }

        // Check for the earliest column to be completely painted
        for (int col = 0; col < n; col++) {
            int lastElementIndex = Integer.MIN_VALUE;  // Track the latest index in this column
            for (int row = 0; row < m; row++) {
                int val = mat[row][col];
                int idx = numToIndex.get(val);
                lastElementIndex = Math.max(lastElementIndex, idx);
            }
            // Update result with the minimum index where this column is fully painted
            result = Math.min(result, lastElementIndex);
        }

        return result;  // Return the earliest index
    }
}