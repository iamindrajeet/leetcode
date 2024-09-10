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

Approach: Simulation

- Intuition
To calculate the greatest common divisor (GCD) of every pair of adjacent nodes in a linked list, we maintain two pointers, node1 and node2, initially pointing to the first and second nodes, respectively.

As we iterate through the list, we need to compute the GCD of the values stored in node1 and node2. The most efficient method for finding the GCD of two numbers is the renowned Euclidean algorithm. This algorithm is based on the principle that the GCD of two numbers also divides their difference.

In simple terms, the Euclidean algorithm works by repeatedly replacing the larger number by the remainder of the division of the larger number by the smaller number, until one of the numbers becomes zero. The non-zero number at this stage is the GCD of the original pair of numbers.


After computing the GCD, we create a new node with the GCD value and insert it between node1 and node2 as follows:
    - Set node1's next pointer to the new node.
    - Set the new node's next pointer to node2.
    - Disconnect the direct link between node1 and node2.
    - Next, we move node1 and node2 to the next pair of nodes and continue the process.

Algorithm
Main method insertGreatestCommonDivisors:
    - If the list contains only one node (head.next is null), return the head as no insertion is needed.
    - Initialize ListNode variables node1 and node2 to head and head.next respectively, to traverse the linked list.
    - While node2 is not null:
        - Calculate the GCD's of the values in node1 and node2.
        - Create a new ListNode gcdNode with the calculated GCD value.
        - Update node1.next to gcdNode.
        - Update gcdNode.next to node2.
        - Set node1 to node2 and node2 to node2.next, respectively. This essentially moves node1 and node2 to the next pair of nodes in the list.
    - Return the modified head of the list as our answer.

Helper method calculateGCD(a, b):
    - While b is greater than 0:
        - Set a variable temp to b.
        - Set b to a%b and a to temp, respectively.
    - Return a.

- Complexity Analysis
Let n be the number of nodes in the linked list.

- Time complexity: O(n⋅log(min(a,b)))
The algorithm traverses the list, visiting each node exactly once. This takes linear time.
The GCD is calculated using the Euclidean algorithm, which has a time complexity of O(log(min(a,b))), where a and b are numbers whose GCD is 
being calculated.

Thus, the overall time complexity of the algorithm is O(n⋅log(min(a,b))).

- Space complexity: O(1)
The iterative implementation of the GCD method has a space complexity of O(1)

*/
class Solution {
    public ListNode insertGreatestCommonDivisors(ListNode head) {
        // If the list contains only one node, return the head as no insertion is needed
        if(head.next == null)
            return head;
        
        // Initialize pointers to traverse the list
        ListNode node1 = head;
        ListNode node2 = head.next;

        // Traverse the linked list
        while(node2 != null){
            int gcdValue = calculateGCD(node1.val, node2.val);
            ListNode gcdNode = new ListNode(gcdValue);

            // Insert the GCD node between node1 and node2
            node1.next = gcdNode;
            gcdNode.next = node2;

            // Move to the next pair of nodes
            node1 = node2;
            node2 = node2.next;
        }
        return head;
    }

    // Helper method to calculate the greatest common divisor using the Euclidean algorithm
    public int calculateGCD(int a, int b){
        if(b == 0)
            return a;
        else
            return calculateGCD(b, a % b);
    }
}