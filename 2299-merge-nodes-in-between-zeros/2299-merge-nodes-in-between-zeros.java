/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */




 /*
Approach 1: Two-Pointer (One-Pass)
Intuition
We can break this problem into two tasks: finding the sum of all the nodes between two consecutive 0s, and merging these values into a single list. One brute force idea is to iterate through the linked list, summing the node values, and adding this sum to a new linked list when we encounter a 0. However, we can modify the linked list in the given problem.

We can use a two-pointer approach to modify the list. The first pointer, modify, changes the linked list and the second pointer, nextSum, calculates the sum for each block between two 0s. Initially, both pointers start at the beginning of the list.

How can we manage both pointers while traversing the list? After nextSum calculates the sum for the current block, we store this value at the modify node. Since nextSum is at a 0 at the end of the block, it moves to the next node to start summing the next block.

The number of nodes in the modified linked list matches the number of blocks between consecutive 0s. After processing each block, we update modify's next pointer to nextSum, helping maintain the size of the modified list, with both pointers reaching the end simultaneously.

Algorithm
1.Initialize modify and nextSum with head->next that stores the first node with a non-zero value.
2. Iterate through the list until modify is not null:
    Initialize sum with 0 to store the sum of the current block.
    Iterate through the block until nextSum encounters a 0:
    Add the value of the current node to sum.
    Move nextSum to the next node.
    Modify the node value at modify to sum.
    Move nextSum to the next node that stores the next block's first non-zero value. Also, set modify->next to this node.
    Move modify to it's next node.
3.Return head->next.

Complexity Analysis
Let n be the size of the linked list.

Time complexity: O(n)

All the nodes of the linked list are visited exactly once. Therefore, the total time complexity is given by O(n).

Space complexity: O(1)

Apart from the original list, we don't use any additional space. Therefore, the total space complexity is given by O(1).

 */

class Solution {
    public ListNode mergeNodes(ListNode head) {
        // Initialize a dummy node with the first non-zero value
        ListNode modify = head.next;
        ListNode nextSum = modify;

        while(nextSum != null){
            int sum = 0;
            // Find the sum of all nodes until you encounter a 0.
            while(nextSum.val != 0){
                sum += nextSum.val;
                nextSum = nextSum.next;
            }
            // Assign the sum to the current node's value.
            modify.val = sum;
            // Move nextSum to the first non-zero value of the next block.
            nextSum = nextSum.next;
            // Move modify also to this node.
            modify.next = nextSum;
            modify = modify.next;
        }
        return head.next;
    }
}