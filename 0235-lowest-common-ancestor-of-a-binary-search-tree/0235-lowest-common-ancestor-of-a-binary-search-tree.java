/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

class Solution {
    /**
     * This method finds the lowest common ancestor (LCA) of two nodes p and q in a BST.
     *
     * @param root The root node of the BST.
     * @param p The first node.
     * @param q The second node.
     * @return The LCA of nodes p and q.
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // If the root is null, there is no LCA to be found.
        if (root == null)
            return null;
        
        int currVal = root.val;

        // If both p and q are smaller than the current node's value,
        // then LCA must be in the left subtree.
        if (currVal > p.val && currVal > q.val)
            return lowestCommonAncestor(root.left, p, q);
        
        // If both p and q are greater than the current node's value,
        // then LCA must be in the right subtree.
        else if (currVal < p.val && currVal < q.val)
            return lowestCommonAncestor(root.right, p, q);
        
        // If one of p or q is on one side and the other is on the other side,
        // or one of them is equal to the current node's value,
        // then the current node is the LCA.
        return root;   
    }
}

/**
 * Intuition and Logic:
 * The idea is based on the properties of the BST. In a BST, for any given node, 
 * the values of all nodes in its left subtree are smaller, and the values of all 
 * nodes in its right subtree are larger.
 * 
 * To find the LCA of two nodes p and q, we start at the root and traverse down 
 * the tree. At each node, we check:
 * 1. If both p and q are smaller than the current node, then LCA must be in the left subtree.
 * 2. If both p and q are larger than the current node, then LCA must be in the right subtree.
 * 3. If one of p or q is smaller and the other is larger, or one of them is equal to the 
 *    current node, then the current node is the LCA.
 *
 * Time Complexity (TC):
 * - In the worst case, we might have to traverse from the root to the deepest leaf node. 
 * - The time complexity is O(h), where h is the height of the tree. In the worst case of an unbalanced tree, h could be O(n), 
 *   where n is the number of nodes. In the best case of a balanced tree, h is O(log n).
 *
 * Space Complexity (SC):
 * - The space complexity is O(h) due to the recursion stack. In the worst case of an unbalanced tree, 
 *   the space complexity could be O(n). In the best case of a balanced tree, the space complexity is O(log n).
 */
