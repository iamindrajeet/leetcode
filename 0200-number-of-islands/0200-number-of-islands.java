//Approach-1 (DFS)
//T.C : O(m*n)
//S.C : O(m*n)
// class Solution {
//     // Possible 4 directions to move in the grid (Down, Up, Right, Left)
//     private final int[][] directions = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
//     private int m, n; // Dimensions of the grid

//     public int numIslands(char[][] grid) {
//         if (grid == null || grid.length == 0) return 0; // Edge case check

//         this.m = grid.length;
//         this.n = grid[0].length;
//         int count = 0; // Counter for number of islands

//         // Traverse each cell in the grid
//         for (int i = 0; i < m; i++) {
//             for (int j = 0; j < n; j++) {
//                 // If we find land ('1'), start DFS to mark the entire island
//                 if (grid[i][j] == '1') {
//                     dfs(grid, i, j);
//                     count++; // Increase island count
//                 }
//             }
//         }
//         return count; // Return total islands found
//     }

//     private void dfs(char[][] grid, int i, int j) {
//         // Base case: Out of bounds or water or already visited cell
//         if (i < 0 || i >= m || j < 0 || j >= n || grid[i][j] != '1') {
//             return;
//         }

//         grid[i][j] = '$'; // Mark this cell as visited (any non-'1' character works)

//         // Explore all 4 possible directions (Down, Up, Right, Left)
//         for (int[] direction : directions) {
//             int newRow = i + direction[0];
//             int newCol = j + direction[1];

//             dfs(grid, newRow, newCol); // Recursive DFS call
//         }
//     }
// }


//Approach-2 (BFS)
//T.C : O(m*n)
//S.C : O(m*n)
class Solution {
    // Possible 4 directions to move in the grid (Down, Up, Right, Left)
    private final int[][] directions = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
    private int m, n; // Dimensions of the grid

    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) // Edge case check
            return 0;

        m = grid.length;
        n = grid[0].length;
        int count = 0; // Counter for the number of islands
        Queue<int[]> queue = new LinkedList<>();

        // Traverse each cell in the grid
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') { // If land is found, start BFS
                    bfs(grid, i, j, queue);
                    count++; // Increase island count
                }
            }
        }
        return count;
    }

    private void bfs(char[][] grid, int i, int j, Queue<int[]> queue) {
        queue.add(new int[]{i, j}); // Add starting cell to queue
        grid[i][j] = '$'; // Mark it as visited

        while (!queue.isEmpty()) {
            int[] curr = queue.poll(); // Dequeue the front cell

            // Explore all 4 possible directions (Down, Up, Right, Left)
            for (int[] direction : directions) {
                int newRow = curr[0] + direction[0];
                int newCol = curr[1] + direction[1];

                if (isSafe(grid, newRow, newCol)) { // Check if the new cell is valid
                    queue.add(new int[]{newRow, newCol});
                    grid[newRow][newCol] = '$'; // Mark as visited
                }
            }
        }
    }

    private boolean isSafe(char[][] grid, int i, int j) {
        return (i >= 0 && i < m && j >= 0 && j < n && grid[i][j] == '1');
    }
}

