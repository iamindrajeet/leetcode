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
/**
T.C - O(n)
S.C - O(1)
*/
// class Solution {
//     public ListNode removeNthFromEnd(ListNode head, int n) {
//         int count = 0;
//         ListNode temp = head;
        
//         // Count total number of nodes
//         while (temp != null) {
//             count++;
//             temp = temp.next;
//         }

//         // If we need to remove the head node
//         if (count == n) {
//             return head.next;
//         }

//         // Find the node just before the one to be removed
//         int res = count - n;
//         temp = head;
//         while (res > 1) {
//             temp = temp.next;
//             res--;
//         }

//         // Remove the nth node from end
//         temp.next = temp.next.next;
//         return head;
//     }
// }

class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // Initialize two pointers: fast and slow
        ListNode fast = head;
        ListNode slow = head;

        // Move the 'fast' pointer n steps ahead
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }

        // If fast is null after moving n steps, it means we need to remove the head node
        if (fast == null) {
            return head.next;
        }

        // Move both fast and slow pointers one step at a time
        // until fast reaches the last node
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }

        // Skip the node to be removed
        slow.next = slow.next.next;

        // Return the (possibly updated) head of the list
        return head;
    }
}
