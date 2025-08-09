/**
✅ Time Complexity: O(2^n)
For each number, you have two choices: include or exclude it.

So the total number of subsets is 2^n.

The function visits each subset once.

Building each subset takes up to n time (because of the new ArrayList<>(list) call), so technically it's O(n * 2^n) — but we usually just say:

Time Complexity: O(2^n) — exponential growth with input size.

✅ Space Complexity: O(n * 2^n)
You store all 2^n subsets in the result list.

Each subset can be up to size n.

So:

Space Complexity: O(n * 2^n)
*/

class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        // This list will store one possible subset at a time
        List<Integer> list = new ArrayList<>();

        // This is the final result that will store all subsets
        List<List<Integer>> result = new ArrayList<>();

        // Start the recursive function from index 0
        solve(nums, 0, list, result);

        return result;
    }

    private void solve(int[] nums, int idx, List<Integer> list, List<List<Integer>> result) {
        // Base case: if we've considered all elements
        if (idx == nums.length) {
            result.add(new ArrayList<>(list));
            return;
        }

        // \U0001f449 TAKE the current element at index 'idx'
        list.add(nums[idx]); // Add it to the current subset
        solve(nums, idx + 1, list, result); // Recurse for the next index

        // \U0001f519 BACKTRACK: remove the last element added
        list.remove(list.size() - 1);

        // \U0001f449 DO NOT TAKE the current element at index 'idx'
        solve(nums, idx + 1, list, result); // Recurse for the next index without including nums[idx]
    }
}
