/*
1. Intuition and Logic:

- Intuition:
The problem requires finding all unique combinations of numbers from the candidates array that sum up to a given target. Each candidate can be used multiple times. This is typically solved using a backtracking approach.

- Logic:
    Step 1: Start at the first candidate and try to form combinations that sum up to the target.
    Step 2: If the target is reduced to zero, we have found a valid combination. If the target goes below zero, that path is not valid.
    Step 3: Include the current candidate and recurse with the same candidate to allow multiple uses. Backtrack and remove the last candidate to 
        try other possibilities.
    Step 4: Move to the next candidate and repeat the process.

2. Time Complexity (TC): O(2^n)
    The number of possible combinations can be exponential in the worst case. However, pruning the search space (e.g., skipping invalid paths) 
    reduces unnecessary work.

3. Space Complexity (SC): O(n)
    The space complexity is O(n) due to the recursion depth, where n is the length of the candidates array. Additionally, space is used to store 
    the current combination list.
*/
class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        // Resultant list to store all unique combinations
        List<List<Integer>> allUniqueCombinationsList = new ArrayList<>();

        // Current list to store each valid combination during the recursion
        List<Integer> currCombinationList = new ArrayList<>();

        // Start the recursive process to find combinations
        findCombinationSum(candidates, allUniqueCombinationsList, currCombinationList, 0, target);

        // Return all the unique combinations found
        return allUniqueCombinationsList;
    }

    private void findCombinationSum(int[] candidates, List<List<Integer>> allUniqueCombinationsList,
            List<Integer> currCombinationList, int i, int target) {
        // Base case: If the target becomes negative, no valid combination is possible
        if (target < 0 || i >= candidates.length)
            return;

        // Base case: If the target is 0, a valid combination is found
        if (target == 0) {
            // Add a copy of the current combination list to the result list
            allUniqueCombinationsList.add(new ArrayList<>(currCombinationList));
            return;
        }

        // Add the current candidate to the current combination list
        currCombinationList.add(candidates[i]);

        // Recurse with the same index `i` to allow reuse of the current candidate and updated target
        findCombinationSum(candidates, allUniqueCombinationsList, currCombinationList, i, target - candidates[i]);

        // Backtrack: remove the last added candidate to explore other possibilities
        currCombinationList.remove(currCombinationList.size() - 1);

        // Recurse with the next index `i + 1` to try the next candidate
        findCombinationSum(candidates, allUniqueCombinationsList, currCombinationList, i + 1, target);
    }
}
