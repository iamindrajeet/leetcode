/*

Approach : Recursion + Backtracking

1. Intuition and Logic
-   The goal is to find all unique combinations of candidates that sum up to a given target. Each candidate can only be used once in each 
    combination.
-   Sorting the candidates array allows us to efficiently skip duplicate elements, ensuring that combinations are unique.
-   We use backtracking to explore all possible combinations. Starting from a given index, we recursively try to form valid combinations by adding 
    candidates one by one.
-   Backtracking involves exploring a path (adding a candidate to the current combination), and if it doesn't lead to a solution (target becomes 
    negative or not zero), we backtrack by removing the last added candidate and trying the next candidate.
-   If a valid combination is found (target equals zero), it's added to the result list.

2. Time Complexity
-   O(2^n): In the worst case, each element could be either included or excluded, leading to 2^n possible combinations. However, due to pruning 
    (skipping duplicates), the actual complexity is usually lower.

3. Space Complexity
-   O(k * n): The space complexity is mainly due to the space required to store the combination lists and the recursion stack. Here, k is the 
    average length of each combination, and n is the number of candidates. The recursion depth can go up to n, and each combination list can take 
    O(k) space.

*/
class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        // Resultant list to store all unique combinations
        List<List<Integer>> allUniqueCombinationsList = new ArrayList<>();
        
        // Current list to store each valid combination during the recursion
        List<Integer> currCombinationList = new ArrayList<>();
        
        // Sort the candidates array to handle duplicates easily
        Arrays.sort(candidates);
        
        // Start the recursive process to find combinations
        findCombinationSum(candidates, allUniqueCombinationsList, currCombinationList, 0, target);
        
        // Return all the unique combinations found
        return allUniqueCombinationsList;
    }

    private void findCombinationSum(int[] candidates, List<List<Integer>> allUniqueCombinationsList, 
                                    List<Integer> currCombinationList, int idx, int target) {
        // Base case: If target becomes negative, no valid combination is possible
        if (target < 0) 
            return;
        
        // Base case: If target is 0, a valid combination is found
        if (target == 0) {
            allUniqueCombinationsList.add(new ArrayList<>(currCombinationList));
            return;
        }

        // Iterate through the candidates array starting from the current index
        for (int i = idx; i < candidates.length; i++) {
            // Skip duplicates to ensure uniqueness of combinations
            if (i > idx && candidates[i] == candidates[i - 1])
                continue;
            
            // Add the current candidate to the current combination list
            currCombinationList.add(candidates[i]);
            
            // Recurse with the next index and updated target
            findCombinationSum(candidates, allUniqueCombinationsList, currCombinationList, i + 1, target - candidates[i]);
            
            // Backtrack: remove the last added candidate to explore other possibilities
            currCombinationList.remove(currCombinationList.size() - 1);
        }
    }
}
