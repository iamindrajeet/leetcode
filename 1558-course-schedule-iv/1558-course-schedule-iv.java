/*
Approach 1: Tree Traversal - On Demand

Intuition
We can simplify the problem by recognizing that the answer to the query (u, v) is true if there exists a path from node u to node v. This is because the edges are directed to represent dependencies, so if we can reach node v from node u, it indicates that node u is a prerequisite for node v.

This relationship is an example of transitive closure. For instance, consider a path with three nodes: u -> v -> w. In this case:

Node u is a prerequisite for node v
Node v is a prerequisite for node w. By transitivity, we can conclude that node u is also a prerequisite for node w.

Therefore, the problem reduces to determining whether there exists a path between two nodes. To solve this, we can use Depth-First Search (DFS) to explore the graph. Alternatively, other traversal methods like Breadth-First Search (BFS) can also be used. In this approach, we begin at node u and explore its adjacent nodes recursively until we reach node v. If we find node v during the traversal, we return true. If we exhaust all possible paths without reaching node v, we return false.

To efficiently track visited nodes and prevent revisiting them, we maintain a visited array. This array is reset for each query to ensure that each DFS traversal starts with a clean slate, avoiding interference from previous queries.

Algorithm
1. Define a function dfs that takes the adjacency list of the graph, a visited array, and two nodes src and dest, and returns whether a path exists from src to target:
    - Mark the current node src as visited.
    - If src is the same as dest, return true (we found the path).
    - For each neighboring node adj of src:
        - If adj has not been visited yet, recursively call the DFS to check if a path exists from adj to dest.
    - Return the true if the result of at least one recursive call is true and false otherwise.

2. Create the adjacency list adjList using the prerequisite pairs [u, v].
3. For each query [u, v], check if there is a path from u to v using DFS:
    - Initialize a visited array with all entries as false
    - Call the dfs function to check if there exists a path from u to v.
    - Store the result for each query in a result list answer.
4. Return answer.

Complexity Analysis
Let N be the number of courses (numCourses) and let Q be the size of the queries list. In the worst case, the size of the prerequisites list can grow up to (N⋅(N−1))/2, when every course is a prerequisite for every other course, forming a complete directed graph.

Time complexity: O(Q⋅N^2).
Creating the adjacency list adjList takes O(N^2) time as we need to iterate over the list prerequisites. Then we iterate over queries and for each we perform DFS that can take O(V+E) which is equivalent to O(N^2). Hence, the total time complexity equals O(Q⋅N^2).

Space complexity: O(N^2)

The adjacency list requires O(N^2) as it stores every edge in the list prerequisites. For the DFS traversal, we need a visited array of size O(N) and the recursive stack for DFS calls requires O(N) space in the worsts case. Therefore, the total space complexity is equal to O(N^2).
*/
// class Solution {
//     public List<Boolean> checkIfPrerequisite(int numCourses, int[][] prerequisites, int[][] queries) {
//         // Create an adjacency list to represent the graph
//         Map<Integer, List<Integer>> adjList = new HashMap<>();

//         // Build the adjacency list from the prerequisites
//         for (int[] edge : prerequisites) {
//             int u = edge[0], v = edge[1];
//             // Use computeIfAbsent to initialize the list if the key does not exist
//             adjList.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
//         }

//         // Initialize the result list to store the answers for each query
//         List<Boolean> result = new ArrayList<>();

//         // Iterate through each query
//         for (int i = 0; i < queries.length; i++) {
//             int u = queries[i][0]; // Source course
//             int v = queries[i][1]; // Destination course

//             // Create a visited array to track visited nodes during the DFS
//             boolean[] visited = new boolean[numCourses];

//             // Perform DFS to check if there is a path from u to v
//             result.add(dfs(adjList, visited, u, v));
//         }

//         // Return the result list containing true/false for each query
//         return result;
//     }

//     private boolean dfs(Map<Integer, List<Integer>> adjList, boolean[] visited, int src, int dest) {
//         // Mark the current node as visited
//         visited[src] = true;

//         // If the source is the same as the destination, return true
//         if (src == dest)
//             return true;

//         // Variable to store whether the destination is reachable
//         boolean isReachable = false;

//         // Iterate through all adjacent nodes of the current node
//         for (int adjNode : adjList.getOrDefault(src, new ArrayList<>())) {
//             // If the adjacent node has not been visited, perform DFS on it
//             if (!visited[adjNode]) {
//                 isReachable = isReachable || dfs(adjList, visited, adjNode, dest);
//             }
//         }

//         // Return whether the destination is reachable
//         return isReachable;
//     }
// }

/*
Approach 2: Tree Traversal - Preprocessed

Intuition
This approach is similar to the previous one, where we traverse the graph to determine if there is a path from node u to node v. However, the key difference here is that instead of performing DFS/BFS for each query, we precompute the reachability for all nodes. Specifically, for each node i in the range from 0 to N - 1, we perform BFS (can do DFS as well) to identify all nodes that can be reached from i and store this information in a 2D array isPrerequisite.

A value of isPrerequisite[u][v] = true indicates that node u is a prerequisite for node v. During the BFS, starting from node i, we mark all nodes adj in the path as isPrerequisite[i][adj] = true, signifying that i is a prerequisite for adj. In the BFS process, instead of using a separate visited array, we will just use an isPrerequisite array. This is because if isPrerequisite[i][adj] is true, then we can deduce that adj is already visited and skip it.

This method is particularly useful when the number of queries is much larger than the number of nodes. In contrast to the previous approach, where we performed DFS/BFS for each query, this method allows for constant-time query answers since the reachability information has already been preprocessed and stored.

Algorithm

1. Construct an adjacency list adjList from the prerequisites list where each course points to the courses that depend on it.
2. Preprocessing (BFS from each node):
    - For each node i (from 0 to N - 1):
        - Start a BFS from i to explore all reachable nodes.
        - Repeat the following while the queue is not empty:
            - Pop the front in the queue as node.
            - Iterate over the adjacent node and if the node i is not already marked as its prerequisite, mark it and 
            add node to the queue.
3. For each query [u, v] return isPrerequisite[u][v].

Complexity Analysis:

Time Complexity:
1. Preprocessing: BFS for each node takes O(V+E), where V=numCourses and E is the number of edges. For V nodes, this is O(V⋅(V+E)).
2. Query Handling: Answering each query is O(1), and for Q queries, it is O(Q).
3. Overall: O(V⋅(V+E)+Q).

Space Complexity:

Adjacency list: O(E).
Reachability matrix: O(V^2).
BFS queue: O(V) in the worst case.
Total: O(V^2+E).
*/
// class Solution {
//     public List<Boolean> checkIfPrerequisite(int numCourses, int[][] prerequisites, int[][] queries) {
//         // Create an adjacency list to represent the graph
//         Map<Integer, List<Integer>> adjList = new HashMap<>();

//         // Build the adjacency list from the prerequisites
//         for (int[] edge : prerequisites) {
//             int u = edge[0], v = edge[1];
//             // Use computeIfAbsent to initialize the list if the key does not exist
//             adjList.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
//         }

//         // Preprocess the graph to calculate reachability of nodes
//         boolean[][] isPrerequisite = new boolean[numCourses][numCourses];
//         preprocess(numCourses, adjList, isPrerequisite);

//         // Initialize the result list to store the answers for each query
//         List<Boolean> answer = new ArrayList<>();

//         // For each query, check if u is a prerequisite for v
//         for (int[] query : queries) {
//             answer.add(isPrerequisite[query[0]][query[1]]);
//         }

//         // Return the result list
//         return answer;
//     }

//     private void preprocess(int numCourses, Map<Integer, List<Integer>> adjList, boolean[][] isPrerequisite) {
//         // Iterate over each node and perform BFS
//         for (int i = 0; i < numCourses; i++) {
//             Queue<Integer> queue = new LinkedList<>();
//             queue.add(i);

//             while (!queue.isEmpty()) {
//                 int currNode = queue.poll();
//                 // Traverse all adjacent nodes
//                 for (int adj : adjList.getOrDefault(currNode, new ArrayList<>())) {
//                     // If the node has not already been marked as reachable
//                     if (!isPrerequisite[i][adj]) {
//                         isPrerequisite[i][adj] = true; // Mark as reachable
//                         queue.add(adj); // Add the node to the queue
//                     }
//                 }
//             }
//         }
//     }
// }

/*
Approach 3: Topological Sort - Kahn's Algorithm

Intuition
We need to find a way to process nodes in the correct order, ensuring that each node is processed only after its dependencies are handled. This is where topological sorting comes into play. Kahn’s algorithm is a great fit for this task because it respects the dependencies of each node, ensuring nodes are only visited once their prerequisites are completed.

Topological sorting is an algorithm used in directed graphs to arrange nodes such that for every directed edge from node u to node v, node u comes before v. This is a natural approach when dealing with dependencies, like in project scheduling, task ordering, or handling prerequisites.

Now, to adapt Kahn's algorithm to our needs, we need to keep track of a node’s prerequisites. Instead of just processing nodes in topological order, we'll modify the algorithm to maintain a list of dependencies for each node. As we move from node u to node v, we’ll add all of u's prerequisites to v's prerequisites. This is important because it computes the transitive closure, meaning we’re not just tracking immediate dependencies, but also indirect ones.

By the end of this process, each node will have a complete list of all nodes that must be visited before it. With this setup, when we need to answer a query (u, v), all we have to do is check if u is in the list of prerequisites for v.

The general structure of Kahn’s algorithm stays the same. We start by calculating the indegree of each node, which tells us how many nodes depend on it. Nodes with an indegree of zero are independent and can be processed first, so we enqueue them. Then, using a queue, we dequeue nodes, process their neighbors, update the prerequisite lists, and enqueue any neighbors whose indegree drops to zero. This continues until we’ve processed all nodes, ensuring the correct order of traversal.

Algorithm

1. Create an adjacency list (adjList) to store the directed graph representing course dependencies.
2. Initialize an array (indegree) to track the number of prerequisites (in-degree) for each course.
3. Iterate over the prerequisites array to populate the adjacency list and update the indegree for each course.
4. Initialize a queue (q) to process courses with zero in-degree (no prerequisites).
5. While the queue is not empty:
    - Dequeue a course (node).
    - For each adjacent course (adj) in the adjacency list of nodes, add the prerequisites of node to the list 
    prereqMap[adj].
    - Decrement the in-degree of the node adj, and if the in-degree becomes zero, enqueue it for further processing.
6. For each query (u, v), check if course u is in the prerequisite list of course v by checking prereqMap[v].

Complexity Analysis:

Time Complexity:
O(V^2 + V + E) -> Processing Nodes(each node pushed once in queue) = O(V), Processing edges = O(E),
Inserting prerequisites (each node can have ~V prerequisities in worst case) : O(V^2)

Space Complexity : O(V + E)
*/
class Solution {
    public List<Boolean> checkIfPrerequisite(int numCourses, int[][] prerequisites, int[][] queries) {
        // Step 1: Create the adjacency list and compute in-degrees
        Map<Integer, List<Integer>> adjList = new HashMap<>();
        int[] indegree = new int[numCourses];

        for (int[] edge : prerequisites) {
            int u = edge[0], v = edge[1];
            // Build adjacency list
            adjList.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
            // Update in-degree of the destination node
            indegree[v]++;
        }

        // Step 2: Initialize the queue with nodes that have 0 in-degree
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                queue.add(i);
            }
        }

        // Step 3: Create a map to store all prerequisites for each course
        Map<Integer, Set<Integer>> prereqMap = new HashMap<>();

        // Perform topological sort while building the prerequisite map
        while (!queue.isEmpty()) {
            int node = queue.poll();

            // Traverse all neighbors of the current node
            for (int neighbor : adjList.getOrDefault(node, new ArrayList<>())) {
                // Add the current node and its prerequisites to the neighbor's prerequisites
                prereqMap.computeIfAbsent(neighbor, k -> new HashSet<>()).add(node);
                prereqMap.get(neighbor).addAll(prereqMap.getOrDefault(node, new HashSet<>()));

                // Decrease the in-degree of the neighbor
                indegree[neighbor]--;
                // If in-degree becomes 0, add it to the queue
                if (indegree[neighbor] == 0) {
                    queue.add(neighbor);
                }
            }
        }

        // Step 4: Answer each query
        List<Boolean> result = new ArrayList<>();
        for (int[] query : queries) {
            int src = query[0];
            int dest = query[1];
            // Check if 'src' is a prerequisite for 'dest'
            result.add(prereqMap.getOrDefault(dest, new HashSet<>()).contains(src));
        }

        return result;
    }
}