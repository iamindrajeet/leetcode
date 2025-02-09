/**

Explanation
We need to find the number of bad pairs in the array. A pair (i, j) is good if:
nums[i] − i == nums[j] − j
Otherwise, the pair is bad.

Approach
1. Transform the Array
Modify nums[i] to nums[i] - i for all i. This simplifies checking whether (i, j) is a good pair.
2. Use a HashMap to Count Occurrences
We use a Map<Integer, Integer> to store how many times each transformed value has appeared.
3. Iterate Through the Array
For each index j, count how many times nums[j] has appeared before. This gives the number of good pairs.
The number of bad pairs for j is simply j - countOfNumsj, since j is the total elements before j, and countOfNumsj gives the good pairs.
4. Update the Map
Increment the count of nums[j] in the hashmap to keep track of occurrences.

Algorithm
1. Modify each element: nums[i] = nums[i] - i
2. Initialize a HashMap to store occurrences of transformed values.
3. Iterate through nums:
    - Get the count of nums[j] from the map.
    - Compute the number of bad pairs for j as j - countOfNumsj.
    - Update the result with bad pairs count.
    - Update the frequency of nums[j] in the map.
4.Return result.

Time and Space Complexity
Time Complexity: O(n)

The transformation of nums takes O(n).
Iterating through nums and performing hashmap operations take O(n) in total.
Overall, the complexity is O(n).

Space Complexity: O(n)

The HashMap stores at most O(n) unique values in the worst case.
Thus, the overall space complexity is O(n).
*/
class Solution {
    public long countBadPairs(int[] nums) {
        int n = nums.length;
        
        // Transform nums[i] to nums[i] - i
        // This helps in checking if two indices (i, j) form a good pair
        for (int i = 0; i < n; i++) {
            nums[i] = nums[i] - i;
        }

        // Map to store the frequency of transformed nums[i]
        Map<Integer, Integer> map = new HashMap<>();
        map.put(nums[0], 1); // Initialize map with the first element
        
        long result = 0; // Stores the count of bad pairs

        // Iterate through the array to count bad pairs
        for (int j = 1; j < n; j++) {
            int countOfNumsj = map.getOrDefault(nums[j], 0); // Count of nums[j] seen so far
            int countOfNumsBeforej = j; // Total numbers before j

            // Bad pairs for index j = total previous elements - count of good pairs
            int badPairs = countOfNumsBeforej - countOfNumsj;
            result += badPairs;

            // Update frequency map for nums[j]
            map.put(nums[j], countOfNumsj + 1);
        }
        return result;
    }
}