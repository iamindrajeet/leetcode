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

/*
- Time Complexity (TC):
O(h), where h is the height of the BST. In the worst case, h can be O(n) for a skewed tree (like a linked list), and in the best case, h is 
O(log n) for a balanced tree, where n is the number of nodes.

- Space Complexity (SC):
O(h) due to the recursion stack. In the worst case, the recursion depth can be equal to the height of the tree.
*/
class Solution {
    public TreeNode searchBST(TreeNode root, int val) {
        // Base case: If the root is null, return null (the value doesn't exist in the tree)
        if (root == null)
            return null;

        // If the value of the current node matches the target value, return the node
        if (root.val == val)
            return root;

        // If the value is smaller than the current node's value, search in the left subtree
        if (root.val > val)
            return searchBST(root.left, val);

        // Otherwise, search in the right subtree
        else
            return searchBST(root.right, val);
    }
}