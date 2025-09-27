class Solution {
    public int maxFrequencyElements(int[] nums) {
        // Step 1: Initialize a frequency array and a variable to track the highest frequency encountered.
        int[] frequencyCounts = new int[101];
        int maxFrequency = 0; // Stores the highest frequency found among all elements.

        // Step 2: Populate the frequency array and simultaneously determine the maximum frequency.
        for (int num : nums) {
            frequencyCounts[num]++;
            maxFrequency = Math.max(maxFrequency, frequencyCounts[num]);
        }

        // Step 3: Calculate the total frequency of all elements that achieve the 'maxFrequency'.
        int sumOfMaxFrequencies = 0;

        // Iterate through the entire frequency array.
        for (int i = 0; i < 101; i++) {
            // If the frequency of the current number 'i' is equal to the 'maxFrequency',
            // it means this number is one of the most frequent elements.
            if (frequencyCounts[i] == maxFrequency) {
                sumOfMaxFrequencies += maxFrequency;
            }
        }

        // Step 4: Return the final calculated sum.
        return sumOfMaxFrequencies;
    }
}