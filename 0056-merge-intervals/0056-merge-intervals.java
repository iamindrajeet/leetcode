/* 
 * Approach:
 * 1. Sort the intervals based on their start times.
 * 2. Use a greedy approach to iterate through the intervals:
 *    - If the current interval overlaps with the previous one, merge them by updating the end
        time.
 *    - Otherwise, add the current interval to the result list.
 * 3. Convert the list of merged intervals back to an array and return.
 *
 * Time Complexity:
 * - Sorting the intervals takes O(n log n), where n is the number of intervals.
 * - The iteration through the intervals takes O(n).
 * - Overall TC: O(n log n).
 *
 * Space Complexity:
 * - The merged list takes up O(n) space in the worst case (when no intervals overlap).
 * - The sorting operation uses O(log n) space due to the sorting algorithm.
 * - Overall SC: O(n).
*/
class Solution {
    public int[][] merge(int[][] intervals) {
        // List to store merged intervals
        List<int[]> merged = new ArrayList<>();
        
        // Sort intervals by the start time
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        
        // Initialize start and end with the first interval
        int start = intervals[0][0], end = intervals[0][1];
        
        // Iterate through each interval
        for (int[] interval : intervals) {
            // If intervals overlap, merge them by updating the end time
            if (interval[0] <= end) {
                end = Math.max(end, interval[1]);
            } else {
                // If they don't overlap, add the current merged interval to the list
                merged.add(new int[] {start, end});
                // Update start and end to the current interval
                start = interval[0];
                end = interval[1];
            }
        }
        
        // Add the last merged interval to the list
        merged.add(new int[] {start, end});
        
        // Convert the list of intervals to an array and return
        return merged.toArray(new int[merged.size()][]);
    }
}