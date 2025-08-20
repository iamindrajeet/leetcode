/**
Time Complexity
Loop runs 2n times → O(2n)
Each element is pushed and popped at most once → O(n) stack ops

So total operations:
O(2n + n) = O(3n) → Still linear → O(n)

Space Complexity:
O(n)
*/
class Solution {
    public int[] nextGreaterElements(int[] nums) {
        int len = nums.length;
        int[] res = new int[len];
        Stack<Integer> stack = new Stack<>(); 

        // Traverse the array twice in reverse to simulate circular array behavior
        for (int i = 2 * len - 1; i >= 0; i--) {
            // Pop smaller or equal elements from the stack
            while (!stack.empty() && stack.peek() <= nums[i % len]) {
                stack.pop();
            }

            // Only fill results during the first pass (i < len)
            if (i < len) {
                // If stack is empty, no greater element to the right
                res[i] = stack.empty() ? -1 : stack.peek();
            }

            // Push current element onto the stack
            stack.push(nums[i % len]);
        }

        return res;
    }
}
