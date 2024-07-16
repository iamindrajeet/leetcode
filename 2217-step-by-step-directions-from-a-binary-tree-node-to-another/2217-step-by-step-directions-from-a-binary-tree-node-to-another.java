/*
Intuition
The problem is to find the directions from a start node to a destination node in a binary tree. The directions can be either 'U' (up to the parent), 'L' (left child), or 'R' (right child). To solve this, the key steps are:

1.Find the Lowest Common Ancestor (LCA) of the start node and destination node. This is the deepest node that is an ancestor to both nodes.
2.From the LCA, determine the path to the start node and the path to the destination node.
3.The final path from start to destination is formed by first moving up from the start node to the LCA, and then moving down from the LCA to the destination node.

Logic
1.Finding the Lowest Common Ancestor (LCA):

The LCA is found using a recursive approach. If the current node matches either the start node or the destination node, it is returned. Otherwise, the LCA is found recursively in the left and right subtrees.
If both left and right recursive calls return non-null values, the current node is the LCA. If only one of them returns a non-null value, that value is the LCA.

2.Finding Paths:

To find the path from the LCA to a target node, a recursive helper function is used. This function appends 'L' or 'R' to the path as it traverses left or right, respectively.
If the target node is found, the function returns true. If not, it backtracks by removing the last character from the path and tries the other subtree.

3.Building the Final Path:

The path from the start node to the LCA consists of 'U' characters equal to the length of the path found from LCA to the start node.
The path from the LCA to the destination node is directly appended.

Time Complexity (TC)
Finding the LCA takes O(N) time in the worst case, where 
N is the number of nodes in the tree.
Finding the path from the LCA to the start node and the destination node each takes O(H) time, where H is the height of the tree.
Overall, the time complexity is O(N + H + H) = O(N), since in the worst case H = N.

Space Complexity (SC)
The space complexity is primarily due to the recursion stack. In the worst case, the recursion depth is O(H), where H is the height of the tree.
Therefore, the space complexity is O(H).
*/

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
class Solution {
    public String getDirections(TreeNode root, int startValue, int destValue) {
        // Find the lowest common ancestor (LCA) of the start and destination nodes
        TreeNode lca = findLCA(root, startValue, destValue);

        // Initialize StringBuilders to store paths from LCA to startValue and destValue
        StringBuilder lcaToStart = new StringBuilder();
        StringBuilder lcaToDest = new StringBuilder();

        // Find paths from LCA to startValue and destValue
        findPath(lca, startValue, lcaToStart);
        findPath(lca, destValue, lcaToDest);

        // Create a result StringBuilder to store the final path
        StringBuilder result = new StringBuilder();

        // Add 'U' for each step in the path from LCA to startValue
        for (int i = 0; i < lcaToStart.length(); i++) {
            result.append('U');
        }

        // Append the path from LCA to destValue
        result.append(lcaToDest);

        return result.toString();
    }

    // Function to find the lowest common ancestor (LCA) of two nodes
    private TreeNode findLCA(TreeNode root, int startValue, int destValue) {
        // Base case: if the root is null, return null
        if (root == null)
            return null;

        // If either startValue or destValue matches the root's value, return the root
        if (root.val == startValue || root.val == destValue)
            return root;

        // Recursively search for LCA in the left subtree
        TreeNode left = findLCA(root.left, startValue, destValue);
        
        // Recursively search for LCA in the right subtree
        TreeNode right = findLCA(root.right, startValue, destValue);

        // If both left and right are not null, it means p and q are found in
        // different subtrees, so the current root is their lowest common ancestor
        if (left != null && right != null)
            return root;

        // If left is not null, return left (LCA found in left subtree)
        if (left != null)
            return left;

        // If right is not null, return right (LCA found in right subtree)
        return right;
    }

    // Function to find the path from the given node to the target node
    private boolean findPath(TreeNode node, int target, StringBuilder path) {
        // Base case: if the node is null, return false
        if (node == null)
            return false;

        // If the current node's value matches the target, return true
        if (node.val == target)
            return true;

        // Try to find the target in the left subtree
        path.append('L');
        if (findPath(node.left, target, path))
            return true;
        
        // If target is not found in the left subtree, backtrack and remove 'L'
        path.deleteCharAt(path.length() - 1);

        // Try to find the target in the right subtree
        path.append('R');
        if (findPath(node.right, target, path))
            return true;

        // If target is not found in the right subtree, backtrack and remove 'R'
        path.deleteCharAt(path.length() - 1);

        // If the target is not found in either subtree, return false
        return false;
    }
}
