/*
Approach : Min-Heap/Priority Queue

1. Intuition
To further streamline the process, we use a priority queue (min-heap) to efficiently manage and retrieve the smallest ugly number. We start with 1 as our base ugly number and insert it into the min-heap. The priority queue keeps the smallest element at the top, so we can easily access and remove it to get the next ugly number.

After popping the smallest ugly number, we generate new ugly numbers by multiplying them by 2, 3, and 5. These new numbers are then pushed back into the queue. To avoid duplicates, we use a set to track numbers that have already been added, ensuring each ugly number is processed only once.

2. Algorithm
- Create a min-heap (minHeap) to store ugly numbers and a set (seenNumbers) to track numbers already processed.
- Push the first ugly number (1) into the heap and insert it into the set.
- For n iterations:
    - Pop the smallest ugly number (currentUgly) from the heap.
    - Generate the next ugly numbers by multiplying currentUgly with 2, 3, and 5.
    - If a generated ugly number is not in the set, push it into the heap and add it to the set.
- After n iterations, the last popped number from the heap is the nth ugly number.
- Return the nth ugly number.

3. Complexity Analysis
Let n be the given index value of the ugly number and m be the size of set.

- Time complexity: O(nlogn)
The operations on the priority queue (push and pop) take logarithmic time, and there are n such operations.

- Space complexity: O(m)
The space is used by the heap and the set, which store up to m elements as it depends on the number of unique ugly numbers stored in the set.

*/

class Solution {
    public int nthUglyNumber(int n) {
        // PriorityQueue (min-heap) to keep track of the smallest current ugly number
        PriorityQueue<Long> minHeap = new PriorityQueue<>();
        
        // Set to avoid adding duplicate numbers to the heap
        Set<Long> seenNumbers = new HashSet<>(); 
        
        // Factors used to generate new ugly numbers (2, 3, and 5)
        int[] primeFactors = { 2, 3, 5 }; 
        
        // Start by adding the first ugly number, which is 1
        minHeap.offer(1L);
        seenNumbers.add(1L);

        long currentUgly = 1L; // Variable to store the current ugly number
        
        // Loop n times to find the nth ugly number
        for (int i = 0; i < n; i++) {
            // Get and remove the smallest ugly number from the heap
            currentUgly = minHeap.poll(); 

            // Generate the next potential ugly numbers by multiplying with prime factors
            for (int prime : primeFactors) {
                long nextUgly = currentUgly * prime;
                
                // If the generated number is not already in the set, add it to the heap and set
                if (!seenNumbers.contains(nextUgly)) { 
                    seenNumbers.add(nextUgly);
                    minHeap.offer(nextUgly);
                }
            }
        }

        // Return the nth ugly number
        return (int) currentUgly; 
    }
}
