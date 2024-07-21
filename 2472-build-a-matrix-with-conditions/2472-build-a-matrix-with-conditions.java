// Approach-1 (Topological Sorting using DFS)
class Solution {
    // Main method to build the matrix based on row and column conditions
    public int[][] buildMatrix(int k, int[][] rowConditions, int[][] colConditions) {
        // Perform topological sorting for rows and columns
        List<Integer> orderRows = topoSort(rowConditions, k); // T.C - O(k) & S.C - O(k)
        List<Integer> orderColumns = topoSort(colConditions, k);  // T.C - O(k) & S.C - O(k)

        // If either topological sort result is empty, return an empty matrix
        if (orderRows.isEmpty() || orderColumns.isEmpty())
            return new int[][]{};
        
        // Initialize the matrix to be returned
        int[][] matrix = new int[k][k]; // S.C - O(k * k)
        
        // Create a mapping of column order indices for quick lookup
        Map<Integer, Integer> positionMap = new HashMap<>(); // S.C - O(k)
        for(int i = 0; i < k; i++){
            positionMap.put(orderColumns.get(i), i);
        }

        // Fill the matrix using the ordered rows and columns
        // T.C - O(k)
        for(int i = 0; i < k; i++){ 
            int element = orderRows.get(i);
            if(positionMap.containsKey(element)){
                matrix[i][positionMap.get(element)] = element;
            }
        }

        return matrix;
    }

    // Method to perform topological sorting using DFS
    private List<Integer> topoSort(int[][] edges, int n){
        // Initialize adjacency list for graph representation
        Map<Integer, List<Integer>> adj = new HashMap<>();
        // Stack to store nodes in topological order
        Stack<Integer> stack = new Stack<>();
        // List to store the final topological order
        List<Integer> order = new ArrayList<>();
        // Array to track visited state of nodes during DFS
        int[] visited = new int[n + 1]; // 0: not visited, 1: visiting, 2: visited
        // Boolean array to track presence of cycle in the graph
        boolean[] hasCycle = {false};

        // Build adjacency list from the given edges
        for(int[] edge : edges){
            adj.computeIfAbsent(edge[0], k -> new ArrayList<>()).add(edge[1]);
        }

        // Perform DFS from each node to populate the topological order
        for(int i = 1; i <= n; i++){
            if(visited[i] == 0){
                dfs(i, adj, visited, stack, hasCycle);
                // If cycle is detected, return empty list indicating no valid topological order
                if (hasCycle[0])
                    return new ArrayList<>();
            }
        }

        // Retrieve elements from stack to form the final topological order
        while (!stack.isEmpty()) {
            order.add(stack.pop());
        }
        return order;
    }

    // DFS function to traverse the graph and detect cycles
    private void dfs(int node, Map<Integer, List<Integer>> adj, int[] visited, Stack<Integer> stack, boolean[] hasCycle){
        // Mark node as visiting
        visited[node] = 1;
        // Visit all neighbors
        for(int neighbour : adj.getOrDefault(node, new ArrayList<>())){
            // If neighbor is not visited, recursively perform DFS
            if(visited[neighbour] == 0){
                dfs(neighbour, adj, visited, stack, hasCycle);
            // If neighbor is currently visiting, cycle is detected
            } else if(visited[neighbour] == 1){
                hasCycle[0] = true; // Cycle detected
                return;
            }
        }
        // Mark node as visited
        visited[node] = 2;
        // Push node to stack after all neighbors are visited
        stack.push(node);
    }
}

/*
Problem Understanding
The task is to construct a k * k matrix where each row and each column must satisfy certain conditions provided in rowConditions and colConditions respectively. The matrix should be filled such that the order of elements in each row and each column follows a topological order based on the conditions.

Solution Overview
The solution uses a topological sorting approach using Depth-First Search (DFS). Here's a detailed explanation of each part of the code:

Classes and Methods
The main class 'Solution' contains:
    a. buildMatrix(int k, int[][] rowConditions, int[][] colConditions): This is the main method responsible for constructing the matrix.
    b. topoSort(int[][] edges, int n): This method performs topological sorting using DFS for the given set of conditions.
    c. dfs(int node, Map<Integer, List<Integer>> adj, int[] visited, Stack<Integer> stack, boolean[] hasCycle): This DFS function aids in       
        detecting cycles and maintaining the topological order.

Explanation of 'topoSort' Method
This method:

    a. Constructs an adjacency list adj from the edges array where edges[i][0] is prerequisite for edges[i][1].
    b. Initializes visited array to track node states during DFS (0: not visited, 1: visiting, 2: visited).
    c. Uses a Stack to store nodes in order of their finish times (topological order).
    d. Detects cycles during DFS traversal using a hasCycle boolean array.

Explanation of 'dfs' Method
This method:

    a. Marks a node as visiting (visited[node] = 1) when it starts visiting.
    b. Recursively visits all neighbors.
    c. If a neighbor is already being visited (visited[neighbour] == 1), it detects a cycle.
    d. Marks the node as visited (visited[node] = 2) and pushes it onto the stack when all neighbors are processed.

'buildMatrix' Method
    a. Calls topoSort twice: once for rows (orderRows) and once for columns (orderColumns).
    b. Checks if either orderRows or orderColumns is empty (indicating a cycle in the graph), and returns an empty matrix in that case.
    c. Constructs the matrix matrix of size k * k.
    d. Maps the indices from orderColumns to column positions.
    e. Fills the matrix using the mapped indices from orderRows and orderColumns.

Time Complexity Analysis
1. Topological Sorting (topoSort function):
    Building the adjacency list: O(n), where n is the number of edges (rowConditions or colConditions).
    Performing DFS: O(k), since there are k elements to process in the worst case (either rows or columns).

2. Building the Matrix (buildMatrix function):
    Building the matrix itself involves iterating over k rows and columns: O(k^2).

Overall Time Complexity:
    The main contributors are:
    Building adjacency lists and performing DFS for both rows and columns: O(k+n).
    Building the matrix: O(k^2)
Therefore, the overall time complexity is O(k^2+n).

Space Complexity Analysis
1. Adjacency List and Other Data Structures:
    a. Storage for adjacency list (adj): O(n), where n is the number of edges.
    b. 'stack', 'order', 'visited' arrays/lists: O(k) since they handle k elements.
    c. positionMap in buildMatrix: O(k).

2. Overall Space Complexity:
    a. The main contributors are:
        Adjacency list and other data structures: O(k^2) + O(n).
        Additional space used during DFS and matrix construction: O(k^2 + n).
    b. Therefore, the overall space complexity is O(k^2 + n).
*/
