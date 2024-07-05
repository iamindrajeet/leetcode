/*
Logic and Intuition
The problem requires us to find the minimum and maximum distance between critical points in a singly linked list. Critical points are defined as nodes that are either local minima or local maxima, i.e., their value is either less than or greater than both their neighbors.

To solve this problem, we need to:

Traverse the linked list to identify critical points.
Calculate the minimum and maximum distances between these critical points.
Approach
Initialization:

Use pointers to track the previous and current nodes as we traverse the list.
Initialize variables to store the minimum distance (minDistance), the position of the first critical point (firstCriticalIdx), and the position of the last critical point (previousCriticalIdx).
Traverse the List:

Start from the second node (as the first node cannot be a critical point).
For each node, check if it is a critical point by comparing its value with the values of its neighbors.
If a critical point is found, update the minimum distance (minDistance) if it is not the first critical point. If it is the first critical point, set firstCriticalIdx and previousCriticalIdx.
Move to the next node and update the position counter.
Calculate Distances:

If no critical points or only one critical point is found, return [-1, -1].
Otherwise, calculate the maximum distance (maxDistance) between the first and last critical points and return [minDistance, maxDistance].

Time Complexity
O(n): The algorithm involves a single traversal of the linked list to identify critical points and calculate distances. Here, n is the number of nodes in the linked list.
Space Complexity
O(1): The algorithm uses a constant amount of extra space, independent of the input size. All operations are performed in place without using additional data structures.


*/


/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        // Initialize pointers and variables
        ListNode prevNode = head; // Previous node
        ListNode currNode = head.next; // Current node
        int minDistance = Integer.MAX_VALUE; // Initialize minDistance to a large value
        int currPosition = 2; // Position counter starts at 2 because we are at the second node
        int firstCriticalIdx = 0, previousCriticalIdx = 0; // Initialize indices for critical points

        // Traverse the list to find critical points
        while (currNode != null && currNode.next != null) {
            // Check if the current node is a critical point
            if ((currNode.val < prevNode.val && currNode.val < currNode.next.val) ||
                (currNode.val > prevNode.val && currNode.val > currNode.next.val)) {
                // If it's the first critical point
                if (previousCriticalIdx == 0) {
                    previousCriticalIdx = currPosition; // Set previous critical index to current position
                    firstCriticalIdx = currPosition; // Set first critical index
                } else {
                    // Update the minimum distance
                    minDistance = Math.min(minDistance, currPosition - previousCriticalIdx);
                    previousCriticalIdx = currPosition; // Update previous critical index
                }
            }
            // Move to the next node
            prevNode = currNode;
            currNode = currNode.next;
            currPosition++; // Increment position counter
        }

        // If no critical points or only one critical point was found
        if (minDistance == Integer.MAX_VALUE) {
            return new int[]{-1, -1};
        }

        // Calculate the maximum distance
        int maxDistance = previousCriticalIdx - firstCriticalIdx;
        return new int[]{minDistance, maxDistance};
    }
}
