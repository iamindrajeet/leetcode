/**
 * 
 * Approach 1: Brute-Force
 * Intuition
 * We know that each cell either contains a server (represented by 1) or is
 * empty (represented by 0). So, we start by going through each cell to see if
 * there is a server at that position. If the current cell contains a server, we
 * then check if this server can communicate with any other server. If it can,
 * we count it as communicable.
 * 
 * Once we find a server, we check if there is any other server in the same row
 * that can communicate with it. We do this by iterating through all the other
 * cells in the same row. If we find another server in the same row, we can
 * immediately mark it as communicable.
 * 
 * If we do not find any other server in the row, we proceed to check the
 * column. We iterate through all the other rows in the same column to see if
 * there is another server. If a server is found in the same column, we know
 * this server can communicate and is communicable.
 * 
 * As soon as we determine that a server can communicate (either in the same row
 * or column), we increment the total communicable servers count. Once we finish
 * checking the entire grid, we return the count of communicable servers.
 * 
 * Algorithm
 * 1. Initialize numRows and numCols to represent the number of rows and columns
 * in the grid.
 * 2. Initialize communicableServersCount to 0, which will keep track of the
 * count of communicable servers.
 * 3. Traverse through the grid:
 * - For each server at position (row, col) where grid[row][col] == 1:
 * - Set canCommunicate to false.
 * - Check for communication in the same row:
 * - Iterate through each column otherCol in the same row:
 * - If otherCol is not equal to col and grid[row][otherCol] == 1, set
 * canCommunicate to true and break
 * the loop.
 * - If canCommunicate is true, increment communicableServersCount.
 * - If no communication was found in the same row, check for communication in
 * the same column:
 * - Iterate through each row otherRow in the same column:
 * - If otherRow is not equal to row and grid[otherRow][col] == 1, set
 * canCommunicate to true and break
 * the loop.
 * - If canCommunicate is true, increment communicableServersCount.
 * 4. Return communicableServersCount, the total count of servers that can
 * communicate.
 * 
 * Complexity Analysis
 * Let m be the number of rows and n be the number of columns in the grid.
 * 
 * Time complexity: O(m⋅n⋅(m+n))
 * 
 * The algorithm traverses through each cell in the grid using nested loops,
 * where the outer loop runs m times (for each row) and the inner loop runs n
 * times (for each column). For each cell containing a server (grid[row][col] ==
 * 1), it performs two additional checks:
 * 
 * It checks the entire row to see if there is another server in the same row.
 * This involves iterating over n columns.
 * If no server is found in the same row, it checks the entire column to see if
 * there is another server in the same column. This involves iterating over m
 * rows.
 * Since these checks are performed for each server, the worst-case time
 * complexity is O(m⋅n⋅(m+n)).
 * 
 * Space complexity: O(1)
 * 
 * The algorithm uses a constant amount of extra space, as it only maintains a
 * few variables (numRows, numCols, communicableServersCount, canCommunicate,
 * etc.). No additional data structures are used that scale with the input size.
 * Therefore, the space complexity is O(1).
 */
class Solution {

    public int countServers(int[][] grid) {
        int numRows = grid.length;
        int numCols = numRows > 0 ? grid[0].length : 0;
        int communicableServersCount = 0;

        // Traverse through the grid
        for (int row = 0; row < numRows; ++row) {
            for (int col = 0; col < numCols; ++col) {
                if (grid[row][col] == 1) {
                    boolean canCommunicate = false;

                    // Check for communication in the same row
                    for (int otherCol = 0; otherCol < numCols; ++otherCol) {
                        if (otherCol != col && grid[row][otherCol] == 1) {
                            canCommunicate = true;
                            break;
                        }
                    }

                    // If a server was found in the same row, increment
                    // communicableServersCount
                    if (canCommunicate) {
                        communicableServersCount++;
                    } else {
                        // Check for communication in the same column
                        for (int otherRow = 0; otherRow < numRows; ++otherRow) {
                            if (otherRow != row && grid[otherRow][col] == 1) {
                                canCommunicate = true;
                                break;
                            }
                        }

                        // If a server was found in the same column, increment
                        // communicableServersCount
                        if (canCommunicate) {
                            communicableServersCount++;
                        }
                    }
                }
            }
        }

        return communicableServersCount;
    }
}