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
✅ Time Complexity:
Finding middle: O(n)

Reversing half list: O(n/2)

Comparing halves: O(n/2)

✅ Total Time Complexity = O(n)

✅ Space Complexity:
Using only a few pointers (fast, slow, etc.)

✅ Total Space Complexity = O(1)
*/
class Solution {
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) return true;

        // Step 1: Use fast and slow pointers to find the middle
        ListNode fast = head.next;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        // Step 2: Reverse the second half of the list
        ListNode reverseNode = reverseLL(slow.next);
        slow.next = null;  // Optional: Split the list for clarity

        // Step 3: Compare first half and reversed second half
        while (reverseNode != null) {
            if (head.val != reverseNode.val) {
                return false;  // Mismatch found
            }
            head = head.next;
            reverseNode = reverseNode.next;
        }

        return true;
    }

    private ListNode reverseLL(ListNode node) {
        ListNode prev = null;
        ListNode curr = node;
        while (curr != null) {
            ListNode next = curr.next; // Store next
            curr.next = prev;          // Reverse link
            prev = curr;               // Move prev and curr one step forward
            curr = next;
        }
        return prev;
    }
}
