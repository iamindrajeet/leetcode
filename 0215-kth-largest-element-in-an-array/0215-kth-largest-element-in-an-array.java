/*
Problem:
Given an array of integers nums and an integer k, find the kth largest element in the array.

Approach:
1. Use a min-heap (priority queue) to maintain the k largest elements encountered so far.
2. Iterate through each number in nums:
   a. Add the number to the min-heap.
   b. If the size of the min-heap exceeds k, remove the smallest element (top of the heap).
3. After processing all elements, the root of the min-heap will be the kth largest element in nums.

Example:
Input: nums = [3, 2, 1, 5, 6, 4], k = 2
Output: 5
Explanation: The 2nd largest element in the array is 5.

Edge cases:
- Handle scenarios where nums has fewer elements than k gracefully.
- Handle duplicate elements in nums correctly.

Time Complexity: O(n log k) where n is the number of elements in nums. Each insertion and removal in the priority queue takes O(log k) time.
Space Complexity: O(k) for the priority queue storing at most k elements.

*/

import java.util.PriorityQueue;

class Solution {
    public int findKthLargest(int[] nums, int k) {
        // Create a min-heap to store the k largest elements encountered
        PriorityQueue<Integer> minQueue = new PriorityQueue<>((num1, num2) -> num1 - num2);
        
        // Traverse through each number in nums
        for (int num : nums) {
            minQueue.add(num); // Add the current number to the min-heap
            
            // If the size of min-heap exceeds k, remove the smallest element
            if (minQueue.size() > k) {
                minQueue.remove();
            }
        }
        
        // The root of the min-heap will be the kth largest element in nums
        return minQueue.peek();
    }
}
