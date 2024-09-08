/*
- Time Complexity (TC):
The time complexity is O(n), where n is the length of the gain array. This is because we iterate through the array once to compute the current altitude at each step.

- Space Complexity (SC):
The space complexity is O(1) because we are using a constant amount of extra space (for highestAltitude and currentGain), regardless of the input size.
*/
class Solution {
    public int largestAltitude(int[] gain) {
        // Initialize highestAltitude to 0 since the starting altitude is 0
        int highestAltitude = 0;
        // Initialize currentGain to keep track of the current altitude
        int currentGain = 0;
        // Traverse through the gain array to calculate the altitudes at each point
        for (int i = 0; i < gain.length; i++) {
            // Update currentGain with the net gain at each step
            currentGain = gain[i] + currentGain;
            // Update highestAltitude if the current altitude is the highest encountered so far
            highestAltitude = Math.max(highestAltitude, currentGain);
        }
        // Return the highest altitude reached
        return highestAltitude;
    }
}
