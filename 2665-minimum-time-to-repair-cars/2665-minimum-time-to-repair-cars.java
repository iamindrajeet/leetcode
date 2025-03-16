/**
Approach: Using Binary Search on Time
Since the number of cars a mechanic can repair is determined by a quadratic equation, we cannot directly distribute the cars optimally. Instead, we treat the time required as a search space and apply binary search to determine the minimum time.

Binary Search on Minimum Required Time
The minimum possible time required is 1 minute (edge case).
The maximum possible time required is when the slowest mechanic (max rank) repairs all cars alone, i.e.,
max time = =max rank * (cars)^2
We perform binary search on the range [1, maxRank * cars²] to find the minimum time T that allows all mechanics to repair at least cars.

Binary Search Logic
1. Define the search range:
    - left = 1
    - right = maxRank * cars²
2. Check the mid-point (mid time) as a candidate:
    - Compute mid = (left + right) / 2.
    - Check if it is possible to repair all cars within mid time.
3. Determine the next search range:
    - If it is possible to repair all cars in mid time, try to minimize time further (right = mid - 1).
    - If it is not possible, increase the time (left = mid + 1).
4. Base Case:
    - When left > right, the smallest valid mid is our answer.

Checking If Repairing is Possible (isPossible method)
For a given mid-time T, we check if all cars can be repaired:
    - For each mechanic with rank r, compute the maximum cars they can repair in T:
    n=⌊squareroot(T/r)⌋
    - Sum up the cars each mechanic can repair.
    - If the total number of repaired cars is at least the required cars, return true.

Time and Space Complexity Analysis

Time Complexity
    - Binary Search runs in O(log(maxRank × cars²)).
    - Checking feasibility (isPossible method) runs in O(N), where N is the number of mechanics.
    - Overall complexity: O(N*log(maxRank*cars^2))
        - Since cars² is a large value, the logarithmic factor remains manageable.
Space Complexity
    - We use O(log(maxRank × cars²)) recursive calls (depth of recursion in binary search).
    - No extra data structures are used.
    - Overall space complexity: O(log(maxRank × cars²)).
*/
class Solution {
    public long repairCars(int[] ranks, int cars) {
        int maxRank = Arrays.stream(ranks).max().orElse(0);
        // The minimum possible time required is 1 minute
        // The maximum possible time required is maxRank * cars^2
        long left = 1, right = (long) maxRank * cars * cars;
        return findMinTimeRequired(ranks, left, right, cars);
    }

    private long findMinTimeRequired(int[] ranks, long left, long right, int cars) {
        if (left > right) {
            return left; // Base case: left is the minimum required time
        }

        long mid = left + (right - left) / 2;

        if (isPossible(ranks, mid, cars)) {
            return findMinTimeRequired(ranks, left, mid - 1, cars); // Search in the lower half
        } else {
            return findMinTimeRequired(ranks, mid + 1, right, cars); // Search in the upper half
        }
    }

    private boolean isPossible(int[] ranks, long time, int cars) {
        long totalCarsRepaired = 0;

        // Iterate over each mechanic's rank and calculate how many cars they can repair
        for (int rank : ranks) {
            long n = (long) Math.sqrt(time / rank); // n = sqrt(time / rank)
            totalCarsRepaired += n;

            // If total repaired cars exceed required cars, return true early
            if (totalCarsRepaired >= cars) {
                return true;
            }
        }
        return totalCarsRepaired >= cars;
    }
}