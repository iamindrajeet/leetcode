class Solution {
    public int shipWithinDays(int[] weights, int days) {
        // Find the heaviest weight — minimum capacity must be at least this
        int maxWt = Arrays.stream(weights).max().getAsInt();

        // The maximum capacity can be the sum of all weights (ship everything in one day)
        int left = maxWt, right = Arrays.stream(weights).sum();

        // Binary search to find the minimum valid capacity
        while (left <= right) {
            int mid = left + (right - left) / 2;

            // Check how many days are needed with 'mid' capacity
            if (noOfDaysRequired(weights, mid) <= days) {
                // Try to reduce capacity if it fits within the allowed days
                right = mid - 1;
            } else {
                // Need more capacity to reduce the number of days
                left = mid + 1;
            }
        }

        // 'left' is the smallest capacity that allows shipping within 'days'
        return left;
    }

    private int noOfDaysRequired(int[] weights, int weight) {
        int totalWeight = 0;
        int noOfDays = 0;

        // Iterate through all weights
        for (int currWt : weights) {
            totalWeight += currWt;

            if (totalWeight <= weight) {
                // Still under current day's capacity, continue
                continue;
            } else {
                // Exceeded capacity, count a day and start new
                noOfDays++;
                totalWeight = currWt;
            }
        }

        // Count the last day
        return noOfDays + 1;
    }
}
