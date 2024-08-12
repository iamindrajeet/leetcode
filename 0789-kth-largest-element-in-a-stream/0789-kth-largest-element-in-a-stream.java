class KthLargest {

    // PriorityQueue to act as a min-heap.
    private PriorityQueue<Integer> minHeap;
    
    // Variable to store the k value (the k-th largest element to be found)
    private int k;

    public KthLargest(int k, int[] nums) {
        // Initialize the min-heap.
        minHeap = new PriorityQueue<>();
        this.k = k;
        
        // Add each element from the array to the min-heap.
        // Ensure the size of the heap does not exceed k.
        for (int num : nums) {
            minHeap.add(num);
            if (minHeap.size() > k) {
                minHeap.poll();  // Remove the smallest element to maintain k elements.
            }
        }
    }
    
    public int add(int val) {
        // Add the new value to the min-heap.
        minHeap.add(val);
        
        // If the size of the heap exceeds k, remove the smallest element.
        if (minHeap.size() > k) {
            minHeap.poll();
        }
        
        // The root of the min-heap is the k-th largest element.
        return minHeap.peek();
    }
}

/**
 * Your KthLargest object will be instantiated and called as such:
 * KthLargest obj = new KthLargest(k, nums);
 * int param_1 = obj.add(val);
*/

/**
 * Intuition and Logic:
 * 
 * - We need to find the k-th largest element in a stream of numbers.
 * - To efficiently maintain the k largest elements, we use a min-heap (PriorityQueue).
 * - The min-heap will always contain exactly k elements, where the smallest element in this heap is the k-th largest overall.
 * - When a new element is added, we compare it to the smallest element in the heap:
 *   - If the heap has fewer than k elements, we simply add the new element.
 *   - If adding the new element causes the heap to exceed k elements, we remove the smallest element to maintain the k largest elements.
 * - The root of the min-heap (`peek()`) will always be the k-th largest element.
 * 
 * Time Complexity:
 * 
 * - The constructor has a time complexity of O(N log k), where N is the number of elements in the initial array.
 *   - Adding each element to the heap takes O(log k) time, and we do this N times.
 * 
 * - The `add()` method has a time complexity of O(log k) for adding an element to the heap and potentially removing the smallest element.
 * 
 * Space Complexity:
 * 
 * - The space complexity is O(k) because we only store the k largest elements in the heap.
 * - The additional space used is minimal, with only a few integer variables.
 */
