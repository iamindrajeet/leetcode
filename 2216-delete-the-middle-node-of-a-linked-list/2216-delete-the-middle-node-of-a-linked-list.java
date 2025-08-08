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
class Solution {
    public ListNode deleteMiddle(ListNode head) {
        // If there's only one node, deleting the middle means returning null
        if (head == null || head.next == null) return null;

        // Dummy node to simplify deletion logic
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        // Initialize slow and fast pointers, and a prev pointer for slow
        ListNode slow = head;
        ListNode fast = head;
        ListNode prev = dummy;

        // Move slow one step and fast two steps at a time
        while (fast != null && fast.next != null) {
            prev = slow;           // Track the node before slow
            slow = slow.next;      // Move slow by 1
            fast = fast.next.next; // Move fast by 2
        }

        // Delete the middle node
        prev.next = slow.next;

        // Return the head of the modified list
        return dummy.next;
    }
}
