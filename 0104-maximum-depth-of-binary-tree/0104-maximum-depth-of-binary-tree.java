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
Approach - 1 : Using simple DFS
Time Complexity (TC): O(n)
    - We visit each node exactly once, where n is the number of nodes in the binary tree.

Space Complexity (SC): O(h)
    - The space complexity is determined by the depth of the recursion stack, which is the height of the tree, h.
    - In the worst case, the height of the tree could be n (e.g., a skewed tree), leading to O(n) space complexity.
    - In the best case, the height of the tree is log(n) (e.g., a balanced tree), leading to O(log(n)) space complexity.
 */
class Solution {
    public int maxDepth(TreeNode root) {
        return findMaxDepth(root);
    }

    private int findMaxDepth(TreeNode root) {
        // If the tree is empty, the minimum depth is 0.
        if (root == null)
            return 0;
        
        // If the node is a leaf node, its depth is 1.
        if (root.left == null && root.right == null)
            return 1;
        
        // Recursively find the maximum depth of the left subtree.
        int leftDepth = root.left != null ? findMaxDepth(root.left) : Integer.MIN_VALUE;
        
        // Recursively find the maximum depth of the right subtree.
        int rightDepth = root.right != null ? findMaxDepth(root.right) : Integer.MIN_VALUE;

        // The minimum depth of the current node is 1 + the minimum depth of its children.
        return 1 + Math.max(leftDepth, rightDepth);
    }
}

/*

Approach 2 - Using simple BFS

 * Time Complexity (TC): O(n)
 * - We visit each node exactly once, where n is the number of nodes in the binary tree.
 *
 * Space Complexity (SC): O(n)
 * In the worst case, the queue could contain all nodes at the last level of the tree, which is O(n/2) and thus O(n).
*/
// class Solution {
//     public int maxDepth(TreeNode root) {
//         // If the tree is empty, the minimum depth is 0.
//         if(root == null)
//             return 0;
        
//         // If the root is a leaf node, its depth is 1.
//         if (root.left == null && root.right == null)
//             return 1;
        
//         // Use a queue to perform BFS.
//         Queue<TreeNode> queue = new LinkedList<>();
//         queue.offer(root);

//         int depth = 0;

//          while (!queue.isEmpty()) {
//             int n = queue.size();
//             depth++;  // Increment depth for the next level of nodes.

//             for (int i = 0; i < n; i++) {
//                 TreeNode temp = queue.poll();
                
//                 // Add the left child to the queue if it exists.
//                 if (temp.left != null)
//                     queue.offer(temp.left);
                
//                 // Add the right child to the queue if it exists.
//                 if (temp.right != null)
//                     queue.offer(temp.right);
//             }
//         }
        
//         return depth;  // Return the maximum depth after processing all levels.
//     }
// }