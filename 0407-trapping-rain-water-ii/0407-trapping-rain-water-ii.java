/**
Time Complexity:
Heap Operations:
Each cell is added and removed from the heap exactly once.
Heap operations (insertion/removal) take O(log(m * n)).
Total Cells:
There are m * n cells in the grid.
Overall: O(m * n * log(m * n)).

Space Complexity:
Priority Queue:
Stores at most m * n cells: O(m * n).
Visited Matrix: O(m * n).
Overall: O(m * n).

*/
class Solution {
    public int trapRainWater(int[][] heightMap) {
        int m = heightMap.length; // Number of rows
        int n = heightMap[0].length; // Number of columns

        // Min-heap to store cells in the format {height, row, col}.
        PriorityQueue<int[]> boundaryCells = new PriorityQueue<>((a, b) -> a[0] - b[0]);

        // Visited matrix to ensure each cell is processed only once.
        boolean[][] visited = new boolean[m][n];

        // Add all boundary cells (left-most, right-most, top-most, bottom-most) to the heap.
        for (int row = 0; row < m; row++) {
            boundaryCells.add(new int[] { heightMap[row][0], row, 0 }); // Left-most column
            visited[row][0] = true;

            boundaryCells.add(new int[] { heightMap[row][n - 1], row, n - 1 }); // Right-most column
            visited[row][n - 1] = true;
        }

        for (int col = 0; col < n; col++) {
            boundaryCells.add(new int[] { heightMap[0][col], 0, col }); // Top-most row
            visited[0][col] = true;

            boundaryCells.add(new int[] { heightMap[m - 1][col], m - 1, col }); // Bottom-most row
            visited[m - 1][col] = true;
        }

        int water = 0; // Variable to store the total water trapped.

        // Directions for moving to neighbors (left, right, up, down).
        int[][] directions = { { 0, -1 }, { 0, 1 }, { -1, 0 }, { 1, 0 } };

        // Process the boundary cells using the min-heap.
        while (!boundaryCells.isEmpty()) {
            int[] cell = boundaryCells.poll(); // Get the cell with the smallest height.
            int height = cell[0];
            int i = cell[1];
            int j = cell[2];

            // Check all 4 neighboring cells.
            for (int[] direction : directions) {
                int ni = i + direction[0]; // Neighbor's row index.
                int nj = j + direction[1]; // Neighbor's column index.

                // Ensure the neighbor is within bounds and not visited.
                if (ni >= 0 && ni < m && nj >= 0 && nj < n && !visited[ni][nj]) {
                    // Calculate trapped water for this neighbor.
                    water += Math.max(0, height - heightMap[ni][nj]);

                    // Add the neighbor to the heap with its effective height.
                    boundaryCells.add(new int[] { Math.max(height, heightMap[ni][nj]), ni, nj });

                    // Mark the neighbor as visited.
                    visited[ni][nj] = true;
                }
            }
        }

        return water; // Return the total water trapped.
    }
}
