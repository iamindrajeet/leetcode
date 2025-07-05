class Solution {
    public int findLucky(int[] arr) {
        // Create a HashMap to store the frequency of each number in the array
        Map<Integer, Integer> map = new HashMap<>();
        // Initialize the result with -1 (assuming no lucky number is found)
        int freq = -1;
        // Count the frequency of each number in the array
        for(int num : arr) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        // Iterate through the map entries to find "lucky" numbers
        // A lucky number is a number whose frequency is equal to its value
        for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if(entry.getKey().equals(entry.getValue())) {
                // Update the result with the maximum lucky number found so far
                freq = Math.max(freq, entry.getKey());
            }
        }
        // Return the result if any lucky number is found; otherwise return -1
        return freq != -1 ? freq : -1;
    }
}
