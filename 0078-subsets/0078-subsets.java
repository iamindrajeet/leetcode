/*
1. Intuition and Logic:

- Intuition:
    - The problem asks us to find all possible subsets (the power set) of a given array. This can be solved using backtracking, which explores all 
    potential subsets by deciding whether to include each element or not.
    - We can systematically explore subsets by using a recursive function that either includes or excludes each element in the current subset.

- Logic:
    Step 1: Start with an empty subset and recursively add elements from the array to build all possible subsets.
    Step 2: For each element, we have two choices: include it in the current subset or exclude it.
    Step 3: After making a choice, recurse on the remaining elements, and once all choices are made for a particular path, backtrack by removing 
    the last element added to explore other possibilities.

2. Time Complexity (TC): O(n * 2^n)
    There are 2^n possible subsets for an array of length n, as each element can either be included or excluded.
    For each subset, copying the subset to add it to the list of results takes O(n) time.

3. Space Complexity (SC): O(n)
    The depth of the recursion is O(n), corresponding to the size of the array.
    Additionally, the space for storing the current subset is also O(n).
*/

class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        // List to store all possible subsets
        List<List<Integer>> allPossibleSubsetsList = new ArrayList<>();
        // Temporary list to store the current subset being explored
        List<Integer> currSubsetList = new ArrayList<>();
        // Sort the input array (not necessary in all cases, but can be useful)
        Arrays.sort(nums);
        // Start the recursive backtracking process
        findAllPossibleSubsets(nums, 0, allPossibleSubsetsList, currSubsetList);
        return allPossibleSubsetsList;
    }

    private void findAllPossibleSubsets(int[] nums, int idx, List<List<Integer>> allPossibleSubsetsList, List<Integer> currSubsetList) {
        // Add the current subset (a snapshot of currSubsetList) to the list of all subsets
        allPossibleSubsetsList.add(new ArrayList<>(currSubsetList));

        // Base case: if we have considered all elements, return
        if (idx == nums.length)
            return;

        // Iterate over the elements starting from index 'idx'
        for (int i = idx; i < nums.length; i++) {
            // Include nums[i] in the current subset
            currSubsetList.add(nums[i]);
            // Recursively explore further subsets including nums[i]
            findAllPossibleSubsets(nums, i + 1, allPossibleSubsetsList, currSubsetList);
            // Backtrack: remove the last element added to explore other possibilities
            currSubsetList.remove(currSubsetList.size() - 1);
        }
    }
}
