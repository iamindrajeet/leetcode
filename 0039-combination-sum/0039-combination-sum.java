

class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        // Resultant list to store all unique combinations
        List<List<Integer>> allUnqiueCombinationsList = new ArrayList<>();

        // To store each valid combination while recursion call
        List<Integer> currCombinationList = new ArrayList<>();

        findCombinationSum(candidates, allUnqiueCombinationsList, currCombinationList, 0, target);

        return allUnqiueCombinationsList;
    }

    private void findCombinationSum(int[] candidates, List<List<Integer>> allUnqiueCombinationsList, List<Integer> currCombinationList, int i, int target){
        if(target < 0 || i >= candidates.length)
            return;
        
        if(target == 0){
            allUnqiueCombinationsList.add(new ArrayList<>(currCombinationList));
            return;
        }

        currCombinationList.add(candidates[i]);
        findCombinationSum(candidates, allUnqiueCombinationsList, currCombinationList, i, target - candidates[i]);

        currCombinationList.remove(currCombinationList.size() -1);
        findCombinationSum(candidates, allUnqiueCombinationsList, currCombinationList, i + 1, target);
    }


}