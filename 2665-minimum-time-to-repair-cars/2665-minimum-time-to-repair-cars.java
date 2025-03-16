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