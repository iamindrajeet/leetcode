/**
T.C - O(n)
S.C - O(n)
*/
class Solution {
    public boolean divideArray(int[] nums) {
        // Using a HashSet to keep track of elements that appear an odd number of times
        Set<Integer> set = new HashSet<>();

        // Iterate through each number in the array
        for (int num : nums) {
            // If the number is already in the set, it means we found a pair, so remove it
            if (set.contains(num)) {
                set.remove(num);
            } else { // Otherwise, add it to the set
                set.add(num);
            }
        }

        // If the set is empty, all numbers have been paired, so return true
        // If not, return false (indicating an unpaired element exists)
        return set.isEmpty();
    }
}
