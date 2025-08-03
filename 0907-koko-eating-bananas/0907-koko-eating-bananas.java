/**
\U0001f9e0 Time and Space Complexity:
Time Complexity:
Binary Search: O(log M) where M is the maximum number of bananas in any pile.

For each candidate speed during binary search, we compute total hours: O(N) where N is the number of piles.

So overall: O(N * log M)

Space Complexity:
O(1) (No extra space used except variables)

*/
class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        // Step 1: Find the maximum pile to set the right boundary of binary search
        int maxPile = piles[0];
        for (int pile : piles)
            maxPile = Math.max(maxPile, pile);

        // Step 2: Initialize binary search bounds
        int left = 1, right = maxPile;
        int ans = maxPile; // Default to maxPile in worst case

        // Step 3: Binary search for the minimum feasible eating speed
        while (left <= right) {
            int mid = left + (right - left) / 2;

            // Step 4: Check if Koko can eat all bananas at this speed within h hours
            if (findHoursReq(piles, mid) <= h) {
                ans = mid;         // mid is a valid speed, try to find a smaller one
                right = mid - 1;
            } else {
                left = mid + 1;    // mid is too slow, increase speed
            }
        }

        return ans;
    }

    // Helper method to calculate total hours required at given eating speed
    private long findHoursReq(int[] piles, int speed) {
        long totalHours = 0;
        for (int pile : piles) {
            // Use ceiling to account for partial hours (e.g., 5/2 = 2.5 → 3 hours)
            totalHours += (pile + speed - 1L) / speed; // Integer ceiling trick
        }
        return totalHours;
    }
}
