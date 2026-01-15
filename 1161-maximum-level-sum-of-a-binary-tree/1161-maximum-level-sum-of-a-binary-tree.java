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
Complexity Analysis
Here n is the number of nodes in the given binary tree.

Time complexity: O(n).
- Each queue operation in the BFS algorithm takes O(1) time, and a single node can only be pushed once, leading to O(n) operations for n nodes.
- The computation of sum of all the values of nodes at a level also takes O(n) time as each node's value is used once.

Space complexity: O(n).
*/
class Solution {
    public int maxLevelSum(TreeNode root) {

        // Stores the maximum sum seen so far across all levels
        int maxSum = Integer.MIN_VALUE;

        // Tracks the current level number
        int level = 0;

        // Stores the level which has the maximum sum
        int ans = 0;

        // Queue for level-order (BFS) traversal
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        // Perform BFS traversal
        while (!queue.isEmpty()) {

            // Move to the next level
            level++;

            // Number of nodes at the current level
            int size = queue.size();

            // Sum of node values at the current level
            int sumAtCurrentLevel = 0;

            // Process all nodes at the current level
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();

                // Add current node's value to level sum
                sumAtCurrentLevel += node.val;

                // Add left child to queue if it exists
                if (node.left != null)
                    queue.add(node.left);

                // Add right child to queue if it exists
                if (node.right != null)
                    queue.add(node.right);
            }

            // Update maximum sum and answer level if needed
            if (sumAtCurrentLevel > maxSum) {
                maxSum = sumAtCurrentLevel;
                ans = level;
            }
        }

        // Return the level having the maximum sum
        return ans;
    }
}
