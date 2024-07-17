/*

Approach 1: Recursion

Intuition
The first way to solve problem I've came up with - recursion, let's look at this approach

1.First of all, general for every approach it's good to convert to_delete into hashset (set) because we are going to check for every node if its value is in to_delete and looking for this value in array would be O(n) which will be in total O(n * m) where n - number of nodes and m - number of nodes to delete, so we convert to_delete in the set to have ability to search for element in O(1) time.
2.Let's imagine that we've hidden every node in tree except current node, its parent and children. Let's think about logic we want to apply for every single node - this is a key to solve almost any recursion problem.
3.For every node we want to do so:
    a.In any case we have to delete this node or not we want to continue our recursion - so we will call recursive function on both our children.
    b.In general this is all we want to do in case this node will be saved. Now let's think what we need to do in case when we need to delete   this node:
        a.First of all we want to disconnect this node from its parent (so we'll pass as parameters to our function parent node and whether current node is left child of parent)
        b.Then, we know that after deleting both our children will be roots for two trees if they exist, but here's a problem - what if we already deleted parent node and now looking at child we want to delete too? We just need to delete this node as root from result and add its children - all is simple.
4.In fact, this is whole logic for recursion approach, it's not hard to come up with, let's make a quick dry run and move on to the code.

Coding
1.Convert to_delete list to a set for O(1) lookup time.
2.Define a recursive function recursion that traverses the tree.
    a.If the current node is None, return immediately.
    b.Recurse on the left and right children of the current node.
    c.If the current node's value is in to_delete:
        a.If the current node is in res, delete it from res.
        b.Disconnect the current node from its parent.
        c.If the current node has children, add them as new roots to res.
3. Start the recursion with the root node.
4. Return the values of res which represent the roots of the resulting forest.

Complexity Analysis
Time complexity: O(n), since we go through each node exactly once
Space complexity: O(n), since we use recursion (function call stack) and hashmap res


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
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        // Map to store the remaining trees after deletions
        Map<Integer, TreeNode> res = new HashMap<>();
        // Set to keep track of nodes to be deleted
        Set<Integer> to_delete_set = new HashSet<>();
        for (int val : to_delete) {
            to_delete_set.add(val);
        }
        // Add the root node to the result map initially
        res.put(root.val, root);

        // Recursive function to process the tree
        recursion(null, root, false, res, to_delete_set);

        // Convert the result map to a list and return it
        return new ArrayList<>(res.values());
    }

    private void recursion(TreeNode parent, TreeNode cur_node, boolean isleft, Map<Integer, TreeNode> res, Set<Integer> to_delete_set) {
        if (cur_node == null) return;

        // Recursively process the left and right subtrees
        recursion(cur_node, cur_node.left, true, res, to_delete_set);
        recursion(cur_node, cur_node.right, false, res, to_delete_set);

        // If the current node is in the delete set
        if (to_delete_set.contains(cur_node.val)) {
            // Remove the current node from the result map
            res.remove(cur_node.val);

            // If the current node has a parent, remove the reference from the parent
            if (parent != null) {
                if (isleft) {
                    parent.left = null;
                } else {
                    parent.right = null;
                }
            }

            // If the current node has left and right children, add them to the result map
            if (cur_node.left != null) {
                res.put(cur_node.left.val, cur_node.left);
            }
            if (cur_node.right != null) {
                res.put(cur_node.right.val, cur_node.right);
            }
        }
    }
}
