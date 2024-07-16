/*
Intuition and Logic

1.Base Case:
If the current node (root) is null, return null.
If the current node is either p or q, then this node might be the lowest common ancestor (LCA).

2.Recursive Search:
Recursively search for p and q in the left subtree (findLCA(root.left, p, q)).
Recursively search for p and q in the right subtree (findLCA(root.right, p, q)).

3.Determine the LCA:
If both left and right recursive calls return non-null values, it means p and q are found in different subtrees of the current node, so the current node is their LCA.
If only one of the recursive calls returns a non-null value, that means both p and q are located in the same subtree. Hence, return the non-null value.

Time Complexity (TC)
The time complexity of this solution is O(N), where N is the number of nodes in the binary tree. This is because the algorithm needs to visit each node in the tree to determine the LCA.

Space Complexity (SC)
The space complexity is O(H), where H is the height of the binary tree. This is due to the recursive stack space used in the depth-first search traversal. In the worst case, the space complexity is O(N) for a completely unbalanced tree, and in the best case, it's O(logN) for a balanced tree.
*/




/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
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
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // Initiate the LCA search starting from the root
        return findLCA(root, p, q);
    }

    private TreeNode findLCA(TreeNode root, TreeNode p, TreeNode q) {
        // Base case: if the root is null or if the root is either p or q
        // If root is null, we've reached the end of a branch without finding p or q
        // If root is equal to p or q, we've found at least one of the nodes
        if (root == null || root == p || root == q)
            return root;

        // Recursively search for p and q in the left and right subtrees
        TreeNode left = findLCA(root.left, p, q);
        TreeNode right = findLCA(root.right, p, q);

        // If both left and right are not null, it means p and q are found in
        // different subtrees, so the current root is their lowest common ancestor
        if (left != null && right != null)
            return root;

        // If left is not null, that means p and q are both in the left subtree
        if (left != null)
            return left;

        // If right is not null, that means p and q are both in the right subtree
        return right;
    }
}
