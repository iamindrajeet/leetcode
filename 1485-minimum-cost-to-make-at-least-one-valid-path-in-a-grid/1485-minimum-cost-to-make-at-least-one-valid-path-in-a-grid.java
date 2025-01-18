// Approach-1 (Backtracking DFS to try all paths) - TLE
// Time Complexity: O(4^(m * n)), as each cell can branch into 4 directions recursively.
// Space Complexity: O(m * n), due to the visited array and recursion stack.

// class Solution {
//     // Direction vectors for moving right, left, down, and up (corresponding to grid values 1, 2, 3, 4)
//     private static final int[][] DIR = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
//     private int m, n; // Grid dimensions

//     public int minCost(int[][] grid) {
//         m = grid.length; // Number of rows
//         n = grid[0].length; // Number of columns

//         // A visited array to track cells already included in the current DFS path
//         boolean[][] visited = new boolean[m][n];

//         // Start the DFS from the top-left corner (0, 0) with an initial cost of 0
//         return dfs(0, 0, grid, visited, 0);
//     }

//     private int dfs(int i, int j, int[][] grid, boolean[][] visited, int cost) {
//         // Base Case: If we reach the bottom-right corner, return the accumulated cost
//         if (i == m - 1 && j == n - 1) {
//             return cost;
//         }

//         // Mark the current cell as visited to avoid revisiting it in the same path
//         visited[i][j] = true;

//         int minCost = Integer.MAX_VALUE; // Initialize the minimum cost to a large value

//         // Explore all 4 possible directions (right, left, down, up)
//         for (int dirIndex = 0; dirIndex < 4; dirIndex++) {
//             int ni = i + DIR[dirIndex][0]; // Next row index
//             int nj = j + DIR[dirIndex][1]; // Next column index

//             // Check if the next cell is within bounds and not visited
//             if (ni >= 0 && ni < m && nj >= 0 && nj < n && !visited[ni][nj]) {
//                 // If the current direction does not match the grid's direction value, add 1 to the cost
//                 int nextCost = cost + (grid[i][j] - 1 != dirIndex ? 1 : 0);

//                 // Recursively explore the next cell and update the minimum cost
//                 minCost = Math.min(minCost, dfs(ni, nj, grid, visited, nextCost));
//             }
//         }

//         // Backtrack by unmarking the current cell as visited
//         visited[i][j] = false;

//         return minCost; // Return the minimum cost for this path
//     }
// }


// Approach-2 (Dijkstra's Algorithm) - Accepted
// Time Complexity: O((m * n) * log(m * n)), due to the priority queue operations and processing each cell.
// Space Complexity: O(m * n), for the `result` matrix and the priority queue.

class Solution {
    // Direction vectors for moving right, left, down, and up (corresponding to grid values 1, 2, 3, 4)
    private static final int[][] DIR = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };

    public int minCost(int[][] grid) {
        int m = grid.length; // Number of rows
        int n = grid[0].length; // Number of columns

        // Min-Heap to keep track of cells with their cost in ascending order
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);

        // Matrix to store the minimum cost to reach each cell; initialized to infinity
        int[][] result = new int[m][n];
        for (int[] row : result) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        // Start with the top-left cell (0, 0) with an initial cost of 0
        pq.offer(new int[] { 0, 0, 0 }); // {cost, row, col}
        result[0][0] = 0;

        // Dijkstra's Algorithm: Process cells in the order of their current cost
        while (!pq.isEmpty()) {
            int[] curr = pq.poll(); // Get the cell with the minimum cost
            int currCost = curr[0]; // Current cost
            int i = curr[1];        // Current row
            int j = curr[2];        // Current column

            // If a better cost is already recorded for this cell, skip further processing
            if (result[i][j] < currCost) {
                continue;
            }

            // Explore all 4 possible directions (right, left, down, up)
            for (int dirIndex = 0; dirIndex < 4; dirIndex++) {
                int ni = i + DIR[dirIndex][0]; // Next row index
                int nj = j + DIR[dirIndex][1]; // Next column index

                // Check if the next cell is within bounds
                if (ni >= 0 && nj >= 0 && ni < m && nj < n) {
                    int gridDir = grid[i][j]; // Preferred direction of the current cell
                    int dirCost = (gridDir - 1 != dirIndex ? 1 : 0); // Add cost if not following the preferred direction

                    int newCost = currCost + dirCost; // Calculate the new cost to reach the next cell

                    // If the new cost is less than the recorded cost for the next cell, update and add to the queue
                    if (newCost < result[ni][nj]) {
                        result[ni][nj] = newCost;
                        pq.offer(new int[] { newCost, ni, nj });
                    }
                }
            }
        }

        // Return the minimum cost to reach the bottom-right cell
        return result[m - 1][n - 1];
    }
}
