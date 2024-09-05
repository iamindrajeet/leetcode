/*
Approach: Math

### Intuition:
We are given the results of `m` dice throws (stored in the array `rolls`), and we need to find the values of `n` missing dice throws such that the average value of all `m + n` throws equals the given `mean`.

The mean is calculated as the sum of all observations divided by the number of observations. Thus, the total sum of all `m + n` observations is `mean * (m + n)`. We can then subtract the sum of the known `m` dice rolls from this total sum to determine the sum of the `n` missing dice throws.

For example:
- `rolls = [3, 2, 4, 3], mean = 4, n = 2`
- Total observations: `m + n = 4 + 2 = 6`
- Total sum of all observations: `4 * 6 = 24`
- Sum of given dice rolls: `3 + 2 + 4 + 3 = 12`
- Sum of remaining dice rolls: `24 - 12 = 12`

To check if it's possible to find valid missing dice rolls:
- The minimum possible sum for `n` dice is `n` (if all dice show 1), and the maximum possible sum is `6 * n` (if all dice show 6). Therefore, the sum of the missing throws must be between `n` and `6 * n`, inclusive.

Finally, we need to distribute the sum of the missing throws among the `n` dice. Ideally, each missing throw will be close to the average. If the sum is not divisible evenly by `n`, we distribute the remainder across the dice, ensuring each value stays between 1 and 6.

### Algorithm:
1. Calculate the total number of observations (`m + n`).
2. Compute the sum of the known dice rolls.
3. Calculate the total sum of all dice (both known and unknown).
4. Subtract the sum of the known dice from the total sum to find the sum of the missing dice throws.
5. Check if the sum of the missing dice is within the valid range `[n, 6 * n]`.
6. Distribute the sum of the missing dice across `n` dice:
   - Calculate the base value for each dice.
   - Distribute any remainder among the first few dice.
7. Return the result or an empty array if a valid solution isn't possible.

### Complexity Analysis:
- **Time complexity:** `O(max(m, n))`
  - We iterate through the `rolls` array once and then process the missing dice.
- **Space complexity:** `O(n)`
  - We use an array of size `n` to store the missing dice values.
*/

import java.util.Arrays;

class Solution {
    public int[] missingRolls(int[] rolls, int mean, int n) {
        // Number of given dice rolls
        int m = rolls.length;
        
        // Sum of the given dice rolls
        int sumOfGivenRolls = 0;
        for (int roll : rolls)
            sumOfGivenRolls += roll;
        
        // Total number of observations (m known + n missing)
        int totalObservations = m + n;
        
        // Total sum of all dice (m + n dice with a mean value)
        int sumOfObservations = mean * totalObservations;
        
        // Sum of the remaining n dice throws to be determined
        int sumOfRemainingDiceRolls = sumOfObservations - sumOfGivenRolls;

        // Check if the sum of the missing dice is within the possible range
        if (sumOfRemainingDiceRolls > 6 * n || sumOfRemainingDiceRolls < n) {
            return new int[0];  // If not, return an empty array
        }

        // Distribute the sum of the remaining dice rolls among the n dice
        int distributeMean = sumOfRemainingDiceRolls / n;  // Base value for each dice
        int mod = sumOfRemainingDiceRolls % n;  // Remainder to distribute
        
        // Initialize an array for the n missing dice rolls
        int[] nElements = new int[n];
        Arrays.fill(nElements, distributeMean);  // Set each dice to the base value
        
        // Distribute the remainder among the first mod elements
        for (int i = 0; i < mod; i++)
            nElements[i]++;
        
        // Return the array of missing dice rolls
        return nElements;
    }
}
