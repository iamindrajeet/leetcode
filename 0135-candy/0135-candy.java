/*
 * Greedy Algorithm: Two-Pass Method for Candy Distribution
 *
 * Problem Explanation:
 * We want to distribute the minimum number of candies to children based on their ratings.
 * Two conditions must be satisfied:
 * 1. Each child must receive at least one candy.
 * 2. If a child has a higher rating than their neighbor, they must receive more candies than their neighbor.
 *
 * Approach:
 * 1. Initialize a candies array of the same length as ratings, all set to 1.
 *    This ensures every child starts with at least one candy.
 *
 * 2. First Pass (Left to Right):
 *    Iterate through the ratings array from left to right.
 *    Update candies[i] to candies[i-1] + 1 if ratings[i] > ratings[i-1].
 *    This satisfies the second condition for left neighbors.
 *
 * 3. Second Pass (Right to Left):
 *    Iterate through the ratings array from right to left.
 *    Update candies[i] to max(candies[i], candies[i+1] + 1) if ratings[i] > ratings[i+1].
 *    This ensures both conditions are met for neighboring children.
 *
 * 4. Sum up all values in the candies array to get the total minimum candies needed.
 *
 * Time Complexity: O(n), where n is the length of the ratings array.
 *   - Two passes through the array, each O(n).
 *
 * Space Complexity: O(n), for the candies array.
 *
 */

class Solution {
    public int candy(int[] ratings) {
        int n = ratings.length;
        int[] candies = new int[n];
        Arrays.fill(candies, 1);

        // First pass: left to right
        for (int i = 1; i < n; i++) {
            if (ratings[i] > ratings[i - 1]) {
                candies[i] = candies[i - 1] + 1;
            }
        }

        // Second pass: right to left
        for (int i = n - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                candies[i] = Math.max(candies[i], candies[i + 1] + 1);
            }
        }

        // Calculate total candies
        int totalCandies = 0;
        for (int candy : candies) {
            totalCandies += candy;
        }

        return totalCandies;
    }
}