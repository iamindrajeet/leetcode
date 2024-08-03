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
// class Solution {
//     public int minDepth(TreeNode root) {
//         return findMinDepth(root);
//     }

//     private int findMinDepth(TreeNode root) {
//         // If the tree is empty, the minimum depth is 0.
//         if (root == null)
//             return 0;
        
//         // If the node is a leaf node, its depth is 1.
//         if (root.left == null && root.right == null)
//             return 1;
        
//         // Recursively find the minimum depth of the left subtree.
//         int leftDepth = root.left != null ? findMinDepth(root.left) : Integer.MAX_VALUE;
        
//         // Recursively find the minimum depth of the right subtree.
//         int rightDepth = root.right != null ? findMinDepth(root.right) : Integer.MAX_VALUE;

//         // The minimum depth of the current node is 1 + the minimum depth of its children.
//         return 1 + Math.min(leftDepth, rightDepth);
//     }
// }

/*

Approach 2 - Using simple BFS

 * Time Complexity (TC): O(n)
 * - We visit each node exactly once, where n is the number of nodes in the binary tree.
 *
 * Space Complexity (SC): O(n)
 * - In the worst case, the space needed for the queue is the number of nodes at the deepest level.
 * - For a balanced tree, this is O(n/2) which simplifies to O(n).
 * - For a skewed tree, the space complexity remains O(n).
*/
class Solution {
    public int minDepth(TreeNode root) {
        // If the tree is empty, the minimum depth is 0.
        if(root == null)
            return 0;
        
        // If the root is a leaf node, its depth is 1.
        if (root.left == null && root.right == null)
            return 1;
        
        // Use a queue to perform BFS.
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int depth = 1;

        while(!queue.isEmpty()){
            int n = queue.size();

            for(int i = 0 ; i < n; i++){
                TreeNode temp = queue.poll();

                // If a leaf node is found, return the current depth.
                if (temp.left == null && temp.right == null)
                    return depth;
                
                // Add the left child to the queue if it exists.
                if (temp.left != null)
                    queue.offer(temp.left);
                
                // Add the right child to the queue if it exists.
                if (temp.right != null)
                    queue.offer(temp.right);
            }
            depth++;  // Increment depth for the next level of nodes.
        }
        return -1;  // This line should never be reached.   
    }
}