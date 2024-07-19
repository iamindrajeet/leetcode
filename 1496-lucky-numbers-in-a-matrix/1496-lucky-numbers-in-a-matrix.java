/*
Intuition and Logic:

1.Initialization:
We first determine the dimensions of the matrix, m (number of rows) and n (number of columns).

2.Finding Row Minimums:
For each row, find the minimum element and store it in the rowMin list. This involves iterating through each element in the row and updating the minimum value accordingly.

3.Finding Column Maximums:
For each column, find the maximum element and store it in the colMax list. This involves iterating through each element in the column and updating the maximum value accordingly.

4.Finding Lucky Numbers:
Iterate through each element in the matrix. If an element is both the minimum in its row (as recorded in rowMin) and the maximum in its column (as recorded in colMax), it is a lucky number and is added to the result list.

Time Complexity:
The time complexity is O(m*n), where m is the number of rows and 
n is the number of columns. This is because we iterate through the entire matrix three times: once to find row minimums, once to find column maximums, and once to check for lucky numbers.

Space Complexity:
The space complexity is O(m+n). This is due to the space used by the rowMin and colMax lists, which store the minimum element of each row and the maximum element of each column, respectively.

*/

// class Solution {
//     public List<Integer> luckyNumbers(int[][] matrix) {
//         int m = matrix.length; // Number of rows
//         int n = matrix[0].length; // Number of columns

//         // Step 1: Find the minimum element in each row and store it in rowMin
//         List<Integer> rowMin = new ArrayList<>();
//         for(int row = 0; row < m; row++) {
//             int minEle = Integer.MAX_VALUE;
//             for(int col = 0; col < n; col++) {
//                 minEle = Math.min(minEle, matrix[row][col]);
//             }
//             rowMin.add(minEle);
//         }

//         // Step 2: Find the maximum element in each column and store it in colMax
//         List<Integer> colMax = new ArrayList<>();
//         for(int col = 0; col < n; col++) {
//             int maxEle = Integer.MIN_VALUE;
//             for(int row = 0; row < m; row++) {
//                 maxEle = Math.max(maxEle, matrix[row][col]);
//             }
//             colMax.add(maxEle);
//         }

//         // Step 3: Find all elements that are both row minimums and column maximums
//         List<Integer> result = new ArrayList<>();
//         for(int row = 0; row < m; row++) {
//             for(int col = 0; col < n; col++) {
//                 if(matrix[row][col] == rowMin.get(row) && matrix[row][col] == colMax.get(col)) {
//                     result.add(matrix[row][col]);
//                 }
//             }
//         }

//         return result;
//     }
// }

/*

Intuition and Logic
1.Row Minimums: For each row, find the minimum value and then determine the maximum value among these minimum values (rMinMax).
2.Column Maximums: For each column, find the maximum value and then determine the minimum value among these maximum values (colMaxMin).
3.Lucky Number: The element that is both the maximum of the row minimums and the minimum of the column maximums is identified as a lucky number.

Time Complexity
Finding the minimum value in each row takes O(n) time, and there are 
m rows, so this step takes O(m×n) time.

Similarly, finding the maximum value in each column also takes O(m×n) time.
Overall, the time complexity is O(m×n).

Space Complexity
The space complexity is O(1) because we are using a fixed amount of additional space (for storing integers and the result list), regardless of the size of the input matrix.

*/

class Solution {
    public List<Integer> luckyNumbers(int[][] matrix) {
        int m = matrix.length; // Number of rows
        int n = matrix[0].length; // Number of columns

        // rMinMax will hold the maximum value among the minimum values of each row
        int rMinMax = Integer.MIN_VALUE;
        
        // Find the minimum value in each row and then get the maximum of these minimum values
        for(int row = 0; row < m; row++) {
            int rowMin = Integer.MAX_VALUE;
            for(int col = 0; col < n; col++) {
                rowMin = Math.min(rowMin, matrix[row][col]);
            }
            rMinMax = Math.max(rMinMax, rowMin);
        }
        
        // colMaxMin will hold the minimum value among the maximum values of each column
        int colMaxMin = Integer.MAX_VALUE;
        
        // Find the maximum value in each column and then get the minimum of these maximum values
        for(int col = 0; col < n; col++) {
            int colMax = Integer.MIN_VALUE;
            for(int row = 0; row < m; row++) {
                colMax = Math.max(colMax, matrix[row][col]);
            }
            colMaxMin = Math.min(colMaxMin, colMax);
        }

        List<Integer> result = new ArrayList<>();
        
        // If the max of row minimums equals the min of column maximums, it's a lucky number
        if (rMinMax == colMaxMin) {
            result.add(rMinMax); 
        }

        return result;
    }
}




