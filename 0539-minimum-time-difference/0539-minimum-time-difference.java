/*

- Approach 1: Sort

1. Intuition
Since the times are given in "HH:MM" string format instead of the number of minutes, we can start by parsing the string format of each time and converting it into the total number of minutes passed since "00:00".

If this converted array is sorted in ascending order, then the minimum difference must be the difference in an adjacent pair of times. This is because adjacent elements in a sorted array have smaller differences than nonadjacent elements. Thus, we can sort our array and calculate the difference between each adjacent pair of elements, keeping track of the smallest difference.

An edge case we have to consider is if the smallest difference is between the last and first element, in which case the time loops back to "00:00". For example, if the last and first time is "22:00" and "02:00", then the time difference is 4 hours or 240 minutes.

Thus, checking the difference between each adjacent pair in the sorted array as well as the difference between the first and last element will give us the minimum time difference.

2. Algorithm
    - Initialize an array minutes to store the given time points in units of minutes.
    - For each time time in the given timePoints array:
        - Parse the first two characters in time to get the hour h
        - Parse the last two characters to get the minutes m
        - Calculate the total number of minutes h * 60 + m and store the value in minutes
    - Sort minutes in ascending order
    - Initialize our answer variable ans = Integer.MAX_VALUE
    - Iterate through each adjacent pair of elements (i, i+1) in minutes to find the minimum time difference:
        ans = min(ans, minutes[i+1] - minutes[i])
    - Return the minimum of ans and 24 * 60 - minutes[minutes.length - 1] + minutes[0], the amount of time between the 
      last and first elements.

3. Complexity Analysis
Let N be the size of the given array timePoints.
- Time Complexity: O(NlogN)
Converting the input into minutes and traversing the sorted array to calculate the minimum difference both take O(N) time. However, sorting the array takes O(NlogN) time. Thus, the total time complexity is O(NlogN)

- Space Complexity: O(N)
Our array minutes to store the converted input takes O(N) space.

*/

class Solution {
    public int findMinDifference(List<String> timePoints) {
        // Convert the input times from "HH:MM" format to minutes since midnight
        int[] minutes = new int[timePoints.size()];
        for(int i = 0; i < timePoints.size(); i++){
            String time = timePoints.get(i);
            // Extract hours and minutes from the time string
            int h = Integer.parseInt(time.substring(0, 2));
            int m = Integer.parseInt(time.substring(3));
            // Convert hours and minutes to total minutes since midnight
            minutes[i] = h * 60 + m;
        }

        // Sort the minutes array in ascending order
        Arrays.sort(minutes);

        // Initialize the minimum difference with a large value
        int ans = Integer.MAX_VALUE;
        // Find the minimum difference between consecutive times in the sorted array
        for(int i = 0; i < minutes.length - 1; i++){
            ans = Math.min(ans, minutes[i + 1] - minutes[i]);
        }

        // Consider the circular difference between the last and first time point
        // as they are on opposite sides of the 24-hour cycle
        return Math.min(ans, 24 * 60 - (minutes[minutes.length - 1] - minutes[0]));
    }
}
