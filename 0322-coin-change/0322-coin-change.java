/*

Intuition and Logic
The goal is to find the minimum number of coins needed to make up a given amount using a list of coin denominations. If it is not possible to make up that amount, return -1.

Dynamic Programming Approach
Initialization:

Create an array minCoinsDp where minCoinsDp[i] represents the minimum number of coins needed to make the amount i.
Initialize minCoinsDp with Integer.MAX_VALUE to signify initially that the amount is impossible to form.
Set minCoinsDp[0] to 0 because zero coins are needed to make the amount 0.

DP Array Update:

Iterate through each amount from 1 to amount.
For each amount i, iterate through each coin in the coins array.
If the current coin can be used (i >= coin) and the previous amount (i - coin) is possible to form (minCoinsDp[i - coin] != Integer.MAX_VALUE):
Update minCoinsDp[i] to the minimum of its current value and 1 + minCoinsDp[i - coin].
This means we use one more coin (the current coin) in addition to the minimum coins needed to form the amount i - coin.
Final Result:

After filling the minCoinsDp array, check minCoinsDp[amount].
If it is still Integer.MAX_VALUE, return -1 (indicating the amount cannot be formed with the given coins).
Otherwise, return minCoinsDp[amount] which contains the minimum number of coins needed.

Summary
Initialization: Set up the DP array to represent initially impossible states.
Update: Use each coin to update the minimum number of coins needed for each amount.
Result: Return the final result based on the filled DP array.


TC = O(n*amount)
SC = O(amount)
*/

class Solution {
    public int coinChange(int[] coins, int amount) {
        //Check edge case
        if (amount <= 0) return 0;

        // create DP Array
        int[] minCoinsDp = new int[amount + 1];
        Arrays.fill(minCoinsDp, Integer.MAX_VALUE);
        minCoinsDp[0] = 0;

        // finding minCoin for each amount
        for (int i = 1; i <= amount; i++) {
            // Try each coin
            for (int coin : coins) {
                if (i >= coin && minCoinsDp[i - coin] != Integer.MAX_VALUE) {
                    minCoinsDp[i] = Math.min(minCoinsDp[i], 1 + minCoinsDp[i - coin]);
                }
            }
        }
        // finally returing the value at last index in minCoinsDp array
        return minCoinsDp[amount] == Integer.MAX_VALUE ? -1 : minCoinsDp[amount];
    }
}
