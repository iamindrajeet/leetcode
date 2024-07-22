/*
Approach-1 (Using BFS Cycle Check - Kahn's Algorithm (Topological Sort)

Time Complexity (TC):

Building the Graph:
    1. Constructing the adjacency list and indegree array: This involves iterating through the prerequisites array once.
    2. Time Complexity for this step: O(E), where E is the number of edges (prerequisites).

Topological Sort:
    1. Initializing the queue with vertices having 0 indegree: This involves iterating through the 'indegree' array once.
    2. Time Complexity for this step: O(V), where V is the number of courses (vertices).
    3. Processing the queue: Each vertex and edge is processed exactly once in Kahn's algorithm.
    4. Time Complexity for this step: O(V+E).

Combining both steps, the overall time complexity is O(V+E).

Space Complexity (SC):

1.Adjacency List:
    a. The adjacency list stores the edges of the graph.
    b. Space Complexity for this: O(E).

2. Indegree Array:
    a. The indegree array stores an integer for each vertex.
    b. Space Complexity for this: O(V).

3.Queue:
    a. The queue can store at most all the vertices in the worst case.
    b. Space Complexity for this: O(V).

Combining all the space requirements, the overall space complexity is O(V+E).
*/
class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // Step 1: Build adjacency list and indegree array
        Map<Integer, List<Integer>> adj = new HashMap<>();
        int[] indegree = new int[numCourses];

        for (int[] prerequisite : prerequisites) {
            int course = prerequisite[0];
            int prereq = prerequisite[1];

            adj.computeIfAbsent(prereq, k -> new ArrayList<>()).add(course); // prereq -> course
            indegree[course]++;
        }

        // Step 2: Perform topological sort using Kahn's algorithm and check for cycles
        return topologicalSortCheck(adj, numCourses, indegree);
    }

    // Function to perform topological sort and check for cycles
    private boolean topologicalSortCheck(Map<Integer, List<Integer>> adj, int numCourses, int[] indegree) {
        int count = 0;
        Queue<Integer> q = new LinkedList<>();

        // Step 3: Initialize the queue with vertices having 0 indegree
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                q.offer(i); // Add vertex with 0 indegree to the queue
                count++;
            }
        }

        // Step 4: Process the queue
        while (!q.isEmpty()) {
            int u = q.poll(); // Extract vertex from the queue

            if (adj.containsKey(u)) {
                for (int v : adj.get(u)) {
                    indegree[v]--; // Decrease the indegree of adjacent vertices
                    // If indegree of a vertex becomes 0, add it to the queue
                    if (indegree[v] == 0) {
                        q.offer(v);
                        count++;
                    }
                }
            }
        }

        // Step 5: Check if all vertices were visited (count == numCourses)
        return count == numCourses;
    }
}

/*
Approach-2 (Using DFS Cycle Check)

Time Complexity (TC) and Space Complexity (SC)

Time Complexity: O(V+E) 
    1. V is the number of courses (nodes). 
    2. E is the number of prerequisites (edges).
    3. Each node and edge is processed once during the DFS.

Space Complexity: O(V+E)

    1. Space for the adjacency list is O(E).
    2. Space for the 'visited' and 'inRecursion' arrays is O(V).
    3. Recursion stack space in the worst case is O(V).
*/
// class Solution {
//     public boolean canFinish(int numCourses, int[][] prerequisites) {
//         // Adjacency list to represent the graph
//         Map<Integer, List<Integer>> adj = new HashMap<>();
//         // Array to keep track of visited nodes
//         boolean[] visited = new boolean[numCourses];
//         // Array to keep track of nodes in the current recursion stack
//         boolean[] inRecursion = new boolean[numCourses];
        
//         // Build the adjacency list from prerequisites
//         for (int[] pair : prerequisites) {
//             int a = pair[0];
//             int b = pair[1];
//             // Add edge b --> a
//             adj.computeIfAbsent(b, k -> new ArrayList<>()).add(a);
//         }
        
//         // Check each course for a cycle
//         for (int i = 0; i < numCourses; i++) {
//             if (!visited[i] && isCycleDFS(adj, i, visited, inRecursion)) {
//                 return false; // Cycle detected
//             }
//         }
        
//         return true; // No cycles detected
//     }

//     private boolean isCycleDFS(Map<Integer, List<Integer>> adj, int u, boolean[] visited, boolean[] inRecursion) {
//         visited[u] = true;
//         inRecursion[u] = true;

//         // Traverse all adjacent nodes
//         for (int v : adj.getOrDefault(u, new ArrayList<>())) {
//             // If adjacent node is not visited, perform DFS on it
//             if (!visited[v] && isCycleDFS(adj, v, visited, inRecursion)) {
//                 return true; // Cycle detected
//             }
//             // If adjacent node is in the current recursion stack, a cycle is detected
//             else if (inRecursion[v]) {
//                 return true; // Cycle detected
//             }
//         }

//         // Backtrack: mark the node as not part of the current recursion stack
//         inRecursion[u] = false;
//         return false; // No cycle detected
//     }
// }