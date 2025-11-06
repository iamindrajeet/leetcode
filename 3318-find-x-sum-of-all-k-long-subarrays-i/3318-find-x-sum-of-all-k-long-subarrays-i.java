/**
- Time Complexity
Building frequency map per window - O(1) amortized (sliding update)
Finding top X sum using heap - O(m log x), where m = number of unique elements in window
Overall (for all windows) - O(n * m log x) in worst case

- Space Complexity
Frequency map - O(m) where m ≤ k
Min-heap - O(x)
Result array - O(n - k + 1)

Overall: O(k + x)
*/
class Solution {
    public int[] findXSum(int[] nums, int k, int x) {
        int n = nums.length;

        // Result will have (n - k + 1) elements, one for each window of size k
        int[] result = new int[n - k + 1];

        // Sliding window pointers
        int i = 0, j = 0, idx = 0;

        // Frequency map to store count of each number in the current window
        Map<Integer, Integer> freqMap = new HashMap<>();

        while (j < n) {
            // Include nums[j] in the window and update its frequency
            freqMap.put(nums[j], freqMap.getOrDefault(nums[j], 0) + 1);

            // When window size reaches k
            if (j - i + 1 == k) {
                // Calculate top X frequent number sum in the current window
                result[idx++] = findTopXSum(freqMap, x);

                // Remove nums[i] from window (shrink the window)
                freqMap.put(nums[i], freqMap.get(nums[i]) - 1);
                if (freqMap.get(nums[i]) == 0)
                    freqMap.remove(nums[i]);

                i++; // Move window start
            }
            j++; // Expand window
        }

        return result;
    }

    private int findTopXSum(Map<Integer, Integer> freqMap, int x) {

        // Min-heap to keep track of top x frequent elements
        // Each element in heap = [frequency, number]
        // Sorted by:
        //  1. frequency ascending
        //  2. number ascending (as tiebreaker)
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(
            (a, b) -> (a[0] == b[0]) ? a[1] - b[1] : a[0] - b[0]
        );

        // Go through all (num, freq) pairs in map
        for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
            int num = entry.getKey();
            int freq = entry.getValue();

            minHeap.offer(new int[]{freq, num});

            // Keep only top x elements in heap
            if (minHeap.size() > x) {
                minHeap.poll();
            }
        }

        // Compute sum = (frequency * number) for top x elements
        int sum = 0;
        while (!minHeap.isEmpty()) {
            int[] pair = minHeap.poll();
            sum += pair[0] * pair[1];
        }

        return sum;
    }
}
