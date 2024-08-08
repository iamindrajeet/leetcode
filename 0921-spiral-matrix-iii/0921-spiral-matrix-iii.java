//Simple SImulation
//T.C : O(max(rows,cols)^2)
//S.C : O(1) (I am not considering the result matrix we have to return as result)
class Solution {
    public int[][] spiralMatrixIII(int rows, int cols, int rStart, int cStart) {
        int[][] directions = {
            {0, 1},  // EAST
            {1, 0},  // SOUTH
            {0, -1}, // WEST
            {-1, 0}  // NORTH
        };
        
        int[][] result = new int[rows * cols][2]; // Result array to store the coordinates in spiral order
        int step = 0; // Number of steps to move in a direction
        int dir = 0;  // Direction index (0: EAST, 1: SOUTH, 2: WEST, 3: NORTH)
        
        result[0] = new int[]{rStart, cStart}; // Start from the initial position
        int count = 1; // Count of valid cells visited
        
        while (count < rows * cols) { // Continue until all cells are visited
            // Increase step size after moving EAST or WEST
            if (dir == 0 || dir == 2) step++;
            
            for (int i = 0; i < step; i++) {
                // Move in the current direction
                rStart += directions[dir][0];
                cStart += directions[dir][1];
                
                // Check if the new position is within bounds
                if (rStart >= 0 && rStart < rows && cStart >= 0 && cStart < cols) {
                    result[count++] = new int[]{rStart, cStart};
                }
            }
            // Turn to the next direction
            dir = (dir + 1) % 4;
        }
        
        return result; // Return the spiral order coordinates
    }
}
