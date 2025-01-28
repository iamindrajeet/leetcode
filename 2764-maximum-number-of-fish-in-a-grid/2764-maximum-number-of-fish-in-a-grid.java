/**
 * Approach 1 : Depth first search(DFS)
 * 
 * Time Complexity:
 * - Each cell is visited at most once during the DFS traversal.
 * - If there are `m * n` cells in the grid, the time complexity is O(m * n).
 *
 * Space Complexity:
 * - The space required for the recursive DFS stack is proportional to the size
 * of the largest connected region.
 * - In the worst case (when all cells are part of a single region), the stack
 * depth is O(m * n).
 * - Therefore, the space complexity is O(m * n) in the worst case.
*/

// class Solution {
//     // Dimensions of the grid
//     int m, n;

//     // Directions array for exploring adjacent cells (up, down, left, right)
//     int[][] directions = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

//     public int findMaxFish(int[][] grid) {
//         m = grid.length; // Number of rows
//         n = grid[0].length; // Number of columns

//         int maxFish = 0; // To store the maximum fish count from any connected region

//         // Traverse through each cell in the grid
//         for (int i = 0; i < m; i++) {
//             for (int j = 0; j < n; j++) {
//                 // If the cell contains fish, perform DFS to calculate total fish in this region
//                 if (grid[i][j] > 0) {
//                     maxFish = Math.max(maxFish, dfs(grid, i, j));
//                 }
//             }
//         }
//         return maxFish;
//     }

//     private int dfs(int[][] grid, int i, int j) {
//         // Base case: Check boundaries and ensure the cell contains fish
//         if (i < 0 || i >= m || j < 0 || j >= n || grid[i][j] == 0) {
//             return 0;
//         }

//         // Collect fish from the current cell
//         int fishCount = grid[i][j];

//         // Mark the cell as visited by setting it to 0
//         grid[i][j] = 0;

//         // Explore all 4 possible directions (up, down, left, right)
//         for (int[] direction : directions) {
//             int i_ = i + direction[0];
//             int j_ = j + direction[1];
//             fishCount += dfs(grid, i_, j_);
//         }

//         return fishCount;
//     }
// }

/** 
Approach 2 : Brreadth first search (BFS)

 * Time Complexity:
 * - Each cell is visited at most once during the BFS traversal.
 * - If there are `m * n` cells in the grid, the time complexity is O(m * n).
 *
 * Space Complexity:
 * - The space required for the queue is proportional to the size of the largest connected region.
 * - In the worst case (when all cells are part of a single region), the queue size is O(m * n).
 * - Therefore, the space complexity is O(m * n) in the worst case.

*/
class Solution {
    // Dimensions of the grid
    int m, n;

    // Directions array for exploring adjacent cells (up, down, left, right)
    int[][] directions = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

    public int findMaxFish(int[][] grid) {
        m = grid.length; // Number of rows
        n = grid[0].length; // Number of columns

        int maxFish = 0; // To store the maximum fish count from any connected region

        // Traverse through each cell in the grid
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // If the cell contains fish, perform BFS to calculate total fish in this region
                if (grid[i][j] > 0) {
                    maxFish = Math.max(maxFish, bfs(grid, i, j));
                }
            }
        }
        return maxFish;
    }

    private int bfs(int[][] grid, int i, int j) {
        // Initialize a queue for BFS traversal
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] { i, j });

        // Start collecting fish from the initial cell
        int fishCount = grid[i][j];

        // Mark the cell as visited by setting it to 0
        grid[i][j] = 0;

        // BFS loop to traverse all connected cells
        while (!queue.isEmpty()) {
            // Dequeue the current cell
            int[] cell = queue.poll();
            int currentRow = cell[0];
            int currentCol = cell[1];

            // Explore all 4 possible directions (up, down, left, right)
            for (int[] direction : directions) {
                int newRow = currentRow + direction[0];
                int newCol = currentCol + direction[1];

                // Check if the new cell is valid and contains fish
                if (newRow >= 0 && newCol >= 0 && newRow < m && newCol < n && grid[newRow][newCol] > 0) {
                    // Add fish from the new cell to the total count
                    fishCount += grid[newRow][newCol];

                    // Mark the new cell as visited and add it to the queue
                    queue.add(new int[] { newRow, newCol });
                    grid[newRow][newCol] = 0;
                }
            }
        }

        return fishCount;
    }
}
