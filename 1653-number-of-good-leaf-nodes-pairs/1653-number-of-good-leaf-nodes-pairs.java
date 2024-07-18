/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

// Approach-1 (Using Graph and BFS)
// Time Complexity: O(n^2)
// Space Complexity: O(n)
class Solution {
    public int countPairs(TreeNode root, int distance) {
        // Adjacency list representation of the tree
        Map<TreeNode, List<TreeNode>> adj = new HashMap<>();
        // Set to store all leaf nodes
        Set<TreeNode> leafNodes = new HashSet<>();

        // Convert the tree into an undirected graph and identify all leaf nodes
        makeGraph(root, null, adj, leafNodes);

        int count = 0; // Count of good node pairs
        for (TreeNode leaf : leafNodes) {
            // Perform BFS to find other leaf nodes within the given distance
            Queue<TreeNode> queue = new LinkedList<>();
            Set<TreeNode> visited = new HashSet<>();
            queue.add(leaf);
            visited.add(leaf);

            for (int level = 0; level <= distance; level++) { // Only go till level <= distance
                int size = queue.size();
                while (size-- > 0) { // Process each level
                    TreeNode curr = queue.poll();
                    if (curr != leaf && leafNodes.contains(curr)) {
                        count++; // Found a good pair
                    }

                    // Add neighbors to the queue
                    for (TreeNode neighbor : adj.getOrDefault(curr, new ArrayList<>())) {
                        if (!visited.contains(neighbor)) {
                            queue.add(neighbor);
                            visited.add(neighbor);
                        }
                    }
                }
            }
        }
        // Each pair is counted twice, so divide by 2
        return count / 2;
    }

    // Helper function to convert the tree into an undirected graph
    private void makeGraph(TreeNode root, TreeNode prev, Map<TreeNode, List<TreeNode>> adj, Set<TreeNode> leafNodes) {
        if (root == null)
            return;

        // If it's a leaf node, add it to the set of leaf nodes
        if (root.left == null && root.right == null) {
            leafNodes.add(root);
        }

        // Add the edge between the current node and the previous node
        if (prev != null) {
            adj.computeIfAbsent(root, k -> new ArrayList<>()).add(prev);
            adj.computeIfAbsent(prev, k -> new ArrayList<>()).add(root);
        }

        // Recursively process the left and right subtrees
        makeGraph(root.left, root, adj, leafNodes);
        makeGraph(root.right, root, adj, leafNodes);
    }
}

/*

Intuition and Logic

Convert the Tree to a Graph:

1.The tree is converted into an undirected graph using an adjacency list. This is done so that we can easily perform a breadth-first search (BFS) to find distances between nodes.
2.We also identify and store all the leaf nodes in a set during this process.

Perform BFS for Each Leaf Node:

1.For each leaf node, we perform a BFS up to a maximum depth of distance.
2.While performing the BFS, if we encounter another leaf node within the specified distance, we count it as a good pair.
3.We maintain a set of visited nodes to ensure we do not process a node more than once in a single BFS traversal.

Count and Return the Result:

1.Since each pair of nodes is counted twice (once for each node as the starting point), we divide the final count by 2 to get the correct number of unique pairs.

Complexity Analysis
Time Complexity: O(n^2) because for each leaf node, we might traverse up to distance levels in the worst case, and there might be up to n leaf nodes.
Space Complexity: O(n) due to the space required for the adjacency list and the sets used to store leaf nodes and visited nodes.


*/