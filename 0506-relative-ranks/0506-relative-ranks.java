/*

Intuition
The problem requires us to rank players based on their scores, with the highest score getting the "Gold Medal", the second-highest getting the "Silver Medal", and the third-highest getting the "Bronze Medal". The rest will be ranked with their relative positions.

The approach involves:

Mapping each score to its original index.
Sorting the scores in ascending order.
Assigning ranks based on the sorted order, iterating from the highest to the lowest score.

Time Complexity
Sorting the array takes O(nlogn) time.
Iterating through the array and assigning ranks takes O(n) time.
Thus, the overall time complexity is O(nlogn).

Space Complexity
We use a HashMap to store the scores and their original indices, which takes O(n) space.
The result array takes O(n) space.
Thus, the overall space complexity is O(n).

*/

class Solution {
    public String[] findRelativeRanks(int[] score) {
        // Create a map to store the original indices of the scores
        Map<Integer, Integer> map = new HashMap<>();
        int n = score.length;
        String[] result = new String[n];

        // Populate the map with score and their original indices
        for (int i = 0; i < n; i++)
            map.put(score[i], i);
        
        // Sort the scores in ascending order
        Arrays.sort(score);

        // Assign ranks based on the sorted order
        for (int i = n - 1; i >= 0; i--) {
            int idx = map.get(score[i]); // Get the original index of the current score
            
            // Assign medals or ranks based on the position in the sorted array
            if (i == n - 1) {
                result[idx] = "Gold Medal"; // Highest score
            } else if (i == n - 2) {
                result[idx] = "Silver Medal"; // Second highest score
            } else if (i == n - 3) {
                result[idx] = "Bronze Medal"; // Third highest score
            } else {
                result[idx] = String.valueOf(n - i); // Remaining ranks
            }
        }

        // Return the result array containing the ranks
        return result;
    }
}
