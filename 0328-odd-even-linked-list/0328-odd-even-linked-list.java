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
Time Complexity:

1. Collecting Odd-Indexed Node Values : Time: O(n/2) = O(n)
2. Collecting E-Indexed Node Values : Time: O(n/2) = O(n)
3. Overwriting the Original List : Time: O(n)
Total Time = O(n) + O(n) + O(n) = O(3n) ⇒ O(n)

Space Complexity : O(n)
*/
// class Solution {
//     public ListNode oddEvenList(ListNode head) {
//         // Handle edge case where list is empty
//         if (head == null) return null;

//         // List to store node values in desired order
//         List<Integer> list = new ArrayList<>();

//         // Traverse and collect values from odd-positioned nodes (1-based index)
//         ListNode oddNode = head;
//         while (oddNode != null && oddNode.next != null) {
//             list.add(oddNode.val);           // Add current odd node's value
//             oddNode = oddNode.next.next;     // Move to the next odd node
//         }

//         // If the last odd node exists and wasn't added in the loop, add it
//         if (oddNode != null) {
//             list.add(oddNode.val);
//         }

//         // Traverse and collect values from even-positioned nodes (1-based index)
//         ListNode evenNode = head.next;
//         while (evenNode != null && evenNode.next != null) {
//             list.add(evenNode.val);          // Add current even node's value
//             evenNode = evenNode.next.next;   // Move to the next even node
//         }

//         // If the last even node exists and wasn't added in the loop, add it
//         if (evenNode != null) {
//             list.add(evenNode.val);
//         }

//         // Overwrite original list node values with reordered values from list
//         int i = 0;
//         ListNode temp = head;
//         while (temp != null) {
//             temp.val = list.get(i);          // Update node value
//             i++;
//             temp = temp.next;                // Move to the next node
//         }

//         return head; // Return the modified list
//     }
// }

/**
✅ Time Complexity: O(n)

✅ Space Complexity: O(1)
*/
class Solution {
    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode odd = head;              // Pointer for odd-positioned nodes
        ListNode even = head.next;        // Pointer for even-positioned nodes
        ListNode evenHead = head.next;    // Save head of even list to attach later

        // Loop until end of even list is reached
        while (even != null && even.next != null) {
            odd.next = odd.next.next;     // Link current odd node to the next odd node
            even.next = even.next.next;   // Link current even node to the next even node
            odd = odd.next;               // Move odd pointer forward
            even = even.next;             // Move even pointer forward
        }

        // Attach even list after the odd list
        odd.next = evenHead;

        return head;
    }
}
