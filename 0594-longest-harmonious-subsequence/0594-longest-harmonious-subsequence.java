class Solution {
    public int findLHS(int[] nums) {
        // Create a map to store frequency of each number
        Map<Integer, Integer> map = new HashMap<>();
        int result = 0;

        // Count the frequency of each number in the array
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        // Iterate through each number in the array
        for (int num : nums) {
            // Check if there is a number exactly 1 greater (i.e., num + 1)
            if (map.containsKey(num + 1)) {
                // Calculate the possible length of a harmonious subsequence
                int length = map.get(num) + map.get(num + 1);

                // Update the result if this is the longest found so far
                result = Math.max(result, length);
            }
        }

        // Return the length of the longest harmonious subsequence
        return result;
    }
}
