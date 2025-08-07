/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */

/**

✅ Time Complexity:
Cycle detection phase: O(n)

Finding the cycle start: O(n)

✅ Total Time Complexity: O(n)
Where n is the number of nodes in the list.

✅ Space Complexity:
Only constant extra space is used for pointers (fast, slow, and ptr)

✅ Total Space Complexity: O(1)
*/
public class Solution {
    public ListNode detectCycle(ListNode head) {
        ListNode fast = head, slow = head;

        // Step 1: Detect cycle using two pointers
        while (fast != null && fast.next != null) {
            fast = fast.next.next; // move fast by 2 steps
            slow = slow.next;      // move slow by 1 step

            // If slow == fast, a cycle is detected
            if (fast == slow) {
                // Step 2: Find the start of the cycle
                ListNode ptr = head;
                while (ptr != slow) {
                    ptr = ptr.next;
                    slow = slow.next;
                }
                return ptr; // ptr and slow meet at the start of the cycle
            }
        }

        // No cycle detected
        return null;
    }
}
