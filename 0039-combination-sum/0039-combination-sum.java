class Solution {

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        solve(candidates, target, 0, new ArrayList<>(), result, 0);
        return result;
    }

    private void solve(int[] candidates, int target, int idx, List<Integer> list, List<List<Integer>> result, int sum) {
        // If we've gone through all candidates or the current sum exceeds target, stop recursion
        if (idx == candidates.length || sum > target) return;

        // If a valid combination is found (sum matches target), add a copy to the result
        if (sum == target) {
            result.add(new ArrayList<>(list));
            return;
        }

        // ---------------------
        // Include current element
        // ---------------------
        list.add(candidates[idx]);  // Add the current number to the combination
        // Keep idx same because elements can be reused
        solve(candidates, target, idx, list, result, sum + candidates[idx]); 
        list.remove(list.size() - 1);  // Backtrack: remove the last added number

        // ---------------------
        // Exclude current element
        // ---------------------
        // Move to the next index and try without including current element
        solve(candidates, target, idx + 1, list, result, sum);
    }
}
