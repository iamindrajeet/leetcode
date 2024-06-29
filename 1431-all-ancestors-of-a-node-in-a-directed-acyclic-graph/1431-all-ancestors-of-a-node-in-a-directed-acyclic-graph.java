/*
Approach : Depth First Search (Optimized)

Intuition
We can solve this problem without reversing the edges. Observe that a vertex v will be an ancestor for all nodes reachable from it. Therefore, we can initiate a depth-first traversal from each vertex and designate that vertex as an ancestor to all nodes it can reach.

Our depth-first search would be very similar to Approach 1; but with a key difference: we add the given node as an ancestor to all children of the node we're currently exploring. We then recursively call our depth-first search function on each child until all descendants of ancestor are marked with its presence.

Another optimization we can implement is eliminating the visited set. In each traversal, we add ancestor to the list of ancestors for each node. To determine if a node has been visited, we check if its last ancestor matches the current ancestor. If it does, the node has been visited and can be safely skipped from further exploration.

Algorithm
1. Main method getAncestors:
    Initialize:
        a. A list of lists adjacencyList to store the adjacency list of the graph.
        b. A list of lists ancestors to store the ancestors of each node.
    Populate adjacencyList with edges from the input.
    For each node, use depth-first search (DFS) to find all its ancestors.
    Return ancestors containing the ancestors of each node.


2. Helper method findAncestorsDFS:
    Define a method findAncestorsDFS that takes four parameters: the ancestor node, adjacencyList, 
        the current node being visited, and ancestors.
    Loop through each child node childNode of the current node in the adjacency list:
    Check if ancestor is already added to the child node's ancestor list. If not:
    Add ancestor to the child node's ancestor list.
    Recursively call findAncestorsDFS for childNode.

*/

class Solution {
    public List<List<Integer>> getAncestors(int n, int[][] edges) {
        // Initialize adjacency list for each node and ancestors list
        List<Integer>[] adjancencyList = new ArrayList[n];
        List<List<Integer>> ancestors = new ArrayList<>();

        // Initialize adjacency list and ancestors list for each node
        for(int i = 0; i < n; i++){
            adjancencyList[i] = new ArrayList<>();
            ancestors.add(new ArrayList<>());
        }

        // Populate the adjancency list
        for(int[] edge : edges){
            int from = edge[0];
            int to = edge[1];
            adjancencyList[from].add(to);
        }

        // Perform DFS for each node to find all its ancestors
        for(int i = 0; i < n; i++){
            findAncestorsDFS(i, adjancencyList, i, ancestors);
        }
        return ancestors;
    }

    // Helper method to perform DFS and find ancestors
    private void findAncestorsDFS(int ancestor, List<Integer>[] adjacencyList, 
    int currentNode, List<List<Integer>>  ancestors){
        for(int childNode : adjacencyList[currentNode]){
            // Check if the ancestor is already added to avoid duplicates
            if(ancestors.get(childNode).isEmpty() || ancestors.get(childNode).get(ancestors.get(childNode).size() - 1) != ancestor){
                ancestors.get(childNode).add(ancestor);
                findAncestorsDFS(ancestor, adjacencyList, childNode, ancestors);
            }
        }
    }
}