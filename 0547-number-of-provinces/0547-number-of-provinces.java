/*
Problem Understanding:
You're given a matrix isConnected where isConnected[i][j] indicates if city i is directly connected to city j. This matrix represents an undirected graph where cities are nodes and connections are edges.

Goal:
The goal is to find out how many connected groups of cities (provinces) exist. If cities are interconnected directly or indirectly, they belong to the same province.

Approach:
We use Depth-First Search (DFS), which is a way to explore nodes (cities in this case) in a graph. Here’s how it works:

Initialization:

1. We start with an array visited to keep track of which cities we have already explored.
2. Another variable countOfProvinces keeps track of how many provinces (connected groups) we have found.

DFS Function:

1. The dfs function is recursively called to explore all cities connected to a starting city (u).
2. It marks the starting city as visited.
3. Then, it checks all neighboring cities (v). If a neighboring city hasn’t been visited and there is a direct connection (isConnected[u][v] == 1), it recursively explores that city.

Main Function (findCircleNum):

1. It iterates through each city.
2. If a city hasn’t been visited yet, it means it’s the start of a new province.
3. It calls dfs from this city to mark all connected cities as visited.
4. Each time it finds an unvisited city and performs DFS from it, it increments countOfProvinces because it has found a new province.

Result:

After iterating through all cities, countOfProvinces gives the total number of provinces in the graph.

Intuition:
Think of visited as marking cities you’ve already explored.
    a. You start from any unvisited city (false in visited array).
    b. Use DFS to visit all cities directly or indirectly connected to this starting city.
    c. Each time you start DFS from an unvisited city, it’s like discovering a new province.
    d. Count how many times you start DFS to find out the total number of provinces.

Complexity:
Time Complexity: O(n^2) due to iterating over the entire adjacency matrix.
Space Complexity: O(n) for the visited array and the recursion stack in DFS.

*/
class Solution {
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        boolean[] visited = new boolean[n]; // To track visited nodes
        
        int countOfProvinces = 0; // Number of connected components (provinces)

        for (int i = 0; i < n; i++) {
            if (!visited[i]) { // If node i is not visited
                dfs(isConnected, i, visited, n); // Perform DFS to mark all reachable nodes
                countOfProvinces++; // Increment the province count after completing DFS
            }
        }

        return countOfProvinces; // Return the total number of provinces
    }

    private void dfs(int[][] isConnected, int u, boolean[] visited, int n) {
        visited[u] = true; // Mark node u as visited

        // Visit all neighbors of u
        for (int v = 0; v < n; v++) {
            if (!visited[v] && isConnected[u][v] == 1) { // If v is not visited and there is an edge from u to v
                dfs(isConnected, v, visited, n); // Recursively visit v
            }
        }
    }
}
