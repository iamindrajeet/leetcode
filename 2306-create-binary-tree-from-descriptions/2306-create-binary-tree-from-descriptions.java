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
    public TreeNode createBinaryTree(int[][] descriptions) {
        // Map to store TreeNode references by their values.
        Map<Integer, TreeNode> nodeMap = new HashMap<>();
        // Set to keep track of all child nodes.
        Set<Integer> childSet = new HashSet<>();

        // Iterate through each description to build nodes and relationships.
        for (int[] description : descriptions) {
            int parent = description[0];
            int child = description[1];
            boolean isLeft = description[2] == 1;

            // Ensure parent node exists in the map.
            nodeMap.putIfAbsent(parent, new TreeNode(parent));
            // Ensure child node exists in the map.
            nodeMap.putIfAbsent(child, new TreeNode(child));

            // Set the child to the correct side (left or right) of the parent node.
            if (isLeft) {
                nodeMap.get(parent).left = nodeMap.get(child);
            } else {
                nodeMap.get(parent).right = nodeMap.get(child);
            }

            // Add the child to the childSet.
            childSet.add(child);
        }

        // Identify the root node, which is not a child of any node.
        for (int[] description : descriptions) {
            int parent = description[0];
            if (!childSet.contains(parent)) {
                return nodeMap.get(parent);
            }
        }

        return null;
    }
}

/*
Intuition and Explanation

Node Creation and Relationship Setup:

1.We iterate through each description in the input.
2.For each description, we ensure that the parent and child nodes are present in the nodeMap. If not, we create them.
3.We then set up the relationship between the parent and child nodes. Depending on the isLeft flag, we set the child as either the left or right child of the parent.
4.We also keep track of all child nodes in a childSet.

Identifying the Root Node:

The root node is the only node that is not a child of any other node.
We iterate through the descriptions again, checking each parent node. The first parent node that is not in the childSet is our root node.

Time Complexity
O(N): We traverse the descriptions array twice, where N is the number of descriptions. The operations inside the loops are constant time operations.

Space Complexity
O(N): We use extra space to store the nodes in the nodeMap and child nodes in the childSet, which results in O(N) space complexity.

*/