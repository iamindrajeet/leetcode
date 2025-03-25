/**

Approach - Using Max Heap
1. Use a Max Heap (PriorityQueue) to always access the pile with the highest number of gifts.
2. Extract the maximum element (biggest pile), reduce it to floor(sqrt(original)), and push it back into the heap.
3. Repeat this process for k seconds.
4. Sum up the remaining elements in the heap and return the result.

Complexity Analysis
Let n be the size of the gifts array.

Time complexity: O(n+k×logn)

The initialization of the heap requires O(n) time. On each step, we pop the maximum element and push the square root of that element back into the heap. Both operations (pop and push) have a time complexity of O(logn) because a heap is a balanced binary tree. Since we perform this operation k times, the overall time complexity is O(n+k×logn).

Space complexity: O(n)

The space complexity is O(n) since the heap contains exactly n elements.
*/
class Solution {
    public long pickGifts(int[] gifts, int k) {
        // Max Heap to store the piles of gifts
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        // Insert all elements into the max heap
        for (int gift : gifts) {
            maxHeap.offer(gift);
        }

        // Perform k operations
        while (k != 0) {
            int maxGift = maxHeap.poll(); // Get the largest pile
            int reducedGift = (int) Math.floor(Math.sqrt(maxGift)); // Reduce it
            maxHeap.offer(reducedGift); // Push back the reduced value
            k--;
        }

        // Sum up remaining gifts
        long totalGifts = 0;
        while (!maxHeap.isEmpty()) {
            totalGifts += maxHeap.poll();
        }

        return totalGifts;
    }
}