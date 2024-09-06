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
Approach: Hash Set

- Intuition
The first challenge is efficiently determining whether a linked list value exists in the nums array. A naive approach would involve searching through nums for each node, but this is inefficient for large arrays. Instead, we can use a Hash Set, which allows constant-time lookups. By adding all elements of nums to the set, we can check if a node should be removed by verifying if its value exists in constant time.

If you're unfamiliar with hash sets, you can refer to this LeetCode Explore Card for an in-depth tutorial.

With the lookup mechanism in place, we handle the linked list. The head requires special attention, as removing it alters the starting point of the list. We loop through the list to remove nodes from the beginning if their values are found in the hash set, then store the updated head. After this loop, the modified head is stored as the new starting point of the linked list.

Next, we traverse the rest of the list using a current node. As we iterate, we check if current.next's value is in the hash set. If it is, we adjust current.next to skip over that node, removing it from the list.

Once the traversal is complete, we return the modified head of the list.

- Algorithm
    - Initialize a set valuesToRemove and populate it with the values of the nums array.
    - While the head of the linked list is not null and the head's value is present in valuesToRemove:
        - Move head to head.next.
    - If the head is null, return null since all nodes have been removed.
    - Start iterating from the head of the modified list:
        - For each node current, check if the value of the next node (current.next) is in the valuesToRemove set.
            - If it is, skip the next node by updating current.next to current.next.next
        - If it is not, move the current pointer to the next node in the list.
    - Return the updated head of the list.

- Complexity Analysis
Let m and n be the lengths of the nums array and the linked list, respectively.

- Time complexity: O(m+n)

Iterating through the nums array and inserting each element into the hash set takes O(m) time, as each insertion into the set is O(1) on average.

The algorithm traverses the entire linked list exactly once, checking if each node's value is in the hash set. This operation takes O(n) time.

Thus, the overall time complexity of the algorithm is O(m)+O(n)=O(m+n).

- Space complexity: O(m)

The hash set can store up to m elements, one for each unique value in the nums array, leading to a space complexity of O(m). All additional variables used take constant space.
*/

class Solution {
    public ListNode modifiedList(int[] nums, ListNode head) {
        // Create a HashSet for efficient lookup of values in nums
        Set<Integer> valuesToRemove = new HashSet<>();
        for (int num : nums) {
            valuesToRemove.add(num);
        }

        // Handle the case where the head node needs to be removed
        while (head != null && valuesToRemove.contains(head.val)) {
            head = head.next;
        }

        // If the list is empty after removing head nodes, return null
        if (head == null) {
            return null;
        }

        // Iterate through the list, removing nodes with values in the set
        ListNode current = head;
        while(current.next != null){
            if(valuesToRemove.contains(current.next.val)){
                // Skip the next node by updating the pointer
                current.next = current.next.next;
            } else {
                // Move to the next node
                current = current.next;
            }
        }
        return head;
    }
}