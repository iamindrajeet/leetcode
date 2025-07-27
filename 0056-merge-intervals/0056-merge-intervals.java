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
        List<int[]> mergedIntervalList = new ArrayList<>();
        
        // Sort intervals by start time
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        
        for (int[] interval : intervals) {
            // If list is empty OR current interval does not overlap
            if (mergedIntervalList.isEmpty() || interval[0] > mergedIntervalList.get(mergedIntervalList.size() - 1)[1]) {
                mergedIntervalList.add(interval);
            } else {
                // Overlap → merge with the last interval by extending the end
                int[] lastInterval = mergedIntervalList.get(mergedIntervalList.size() - 1);
                lastInterval[1] = Math.max(lastInterval[1], interval[1]);
            }
        }
        
        // Convert merged list into 2D array
        int[][] result = new int[mergedIntervalList.size()][2];
        int i = 0;
        for (int[] interval : mergedIntervalList) {
            result[i++] = interval;
        }
        
        return result;
    }
}