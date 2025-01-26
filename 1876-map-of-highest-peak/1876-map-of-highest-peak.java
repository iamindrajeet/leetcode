/*
Approach : Multi source BFS

Algorithm Intuition:
1. Initialization:
Start by identifying all water cells (isWater[i][j] == 1) and set their heights to 0. Add these cells to a BFS queue.
Initialize all land cells (isWater[i][j] == 0) with a height of -1 to mark them as unvisited.
Breadth-First Search (BFS):

2. Perform BFS starting from all water cells simultaneously.
For each cell in the queue, explore its four neighbors (up, down, left, right).
If a neighboring cell is unvisited (height == -1), update its height as the current cell's height + 1 and add it to the queue.

3. Propagation:
BFS ensures that cells closer to water cells are visited first, and their heights are updated accordingly. This guarantees that the height of each cell represents its minimum distance to the nearest water cell.

Time Complexity:
O(m × n): Each cell is visited exactly once during the BFS traversal, and updating neighbors involves constant work per cell.

Space Complexity:
O(m × n):
The height array requires O(m × n) space.
The BFS queue can hold at most O(m × n) elements in the worst case (when all cells are land).
*/
class Solution {
    public int[][] highestPeak(int[][] isWater) {
        int m = isWater.length; // Number of rows in the grid
        int n = isWater[0].length; // Number of columns in the grid

        // Output grid to store heights
        int[][] height = new int[m][n];
        // Queue for BFS
        Queue<int[]> queue = new LinkedList<>();

        // Step 1: Initialize the queue and height grid
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (isWater[i][j] == 1) {
                    // Water cells start with height 0
                    height[i][j] = 0;
                    queue.add(new int[] { i, j }); // Add water cells to the queue
                } else {
                    // Land cells are initialized with -1 (unvisited)
                    height[i][j] = -1;
                }
            }
        }

        // Directions array to move up, down, left, right
        int[][] directions = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

        // Step 2: Perform BFS
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size > 0) {
                // Remove the current cell from the queue
                int[] curr = queue.poll();
                int i = curr[0], j = curr[1];

                // Explore all 4 neighboring cells
                for (int[] direction : directions) {
                    int i_ = i + direction[0]; // New row
                    int j_ = j + direction[1]; // New column

                    // Check if the neighbor is within bounds and unvisited
                    if (i_ >= 0 && i_ < m && j_ >= 0 && j_ < n && height[i_][j_] == -1) {
                        // Assign height to the neighbor
                        height[i_][j_] = height[i][j] + 1;
                        // Add the neighbor to the queue for further processing
                        queue.add(new int[] { i_, j_ });
                    }
                }
                size--;
            }
        }
        return height; // Return the resulting grid
    }
}
