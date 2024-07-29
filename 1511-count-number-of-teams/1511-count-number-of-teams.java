// Simple Approach - Counting left and right values
// T.C: O(n^2) - Time Complexity
// S.C: O(1) - Space Complexity
class Solution {
    public int numTeams(int[] rating) {
        int n = rating.length;
        int teams = 0;

        // Iterate through the array, treating each element as the middle member of a potential team
        for (int j = 1; j < n - 1; j++) {
            int countSmallerLeft = 0; // Count of elements less than rating[j] to the left
            int countLargerLeft = 0;  // Count of elements greater than rating[j] to the left
            int countSmallerRight = 0; // Count of elements less than rating[j] to the right
            int countLargerRight = 0;  // Count of elements greater than rating[j] to the right

            // Count the number of elements to the left of index j
            // that are either smaller or larger than rating[j]
            for (int i = 0; i < j; i++) {
                if (rating[i] < rating[j]) {
                    countSmallerLeft++;
                } else if (rating[i] > rating[j]) {
                    countLargerLeft++;
                }
            }

            // Count the number of elements to the right of index j
            // that are either smaller or larger than rating[j]
            for (int k = j + 1; k < n; k++) {
                if (rating[j] < rating[k]) {
                    countLargerRight++;
                } else if (rating[j] > rating[k]) {
                    countSmallerRight++;
                }
            }

            // Calculate the number of valid teams using the counts
            // A valid team can be formed by either (smaller left, current, larger right)
            // or (larger left, current, smaller right)
            teams += (countLargerLeft * countSmallerRight) + (countSmallerLeft * countLargerRight);
        }

        return teams;
    }
}
