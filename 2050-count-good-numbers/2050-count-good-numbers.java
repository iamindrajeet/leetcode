/**
Complexity Analysis
Time Complexity: O(log n) for each exponentiation using binary exponentiation.

Space Complexity: O(log n) due to recursive depth (can be reduced to O(1) with iterative approach).
*/
class Solution {
    // We take MOD to avoid integer overflow for large values
    static final long MOD = 1_000_000_007;

    /**
     * Function to count the number of good numbers of length n.
     *
     * Intuition:
     *  - A "good number" is one where:
     *    - Even indices (0-based) can have digits [0, 2, 4, 6, 8] => 5 options
     *    - Odd indices can have prime digits [2, 3, 5, 7] => 4 options
     *  - For a number of length n:
     *    - The number of even positions = ceil(n / 2)
     *    - The number of odd positions = floor(n / 2)
     *  - So total good numbers = (5 ^ evenPositions) * (4 ^ oddPositions)
     *
     * @param n Length of the number
     * @return Total number of good numbers modulo 1e9+7
     */
    public int countGoodNumbers(long n) {
        long evenPositions = (n + 1) / 2; // ceil(n/2)
        long oddPositions = n / 2;        // floor(n/2)

        // Calculate the number of good numbers using modular exponentiation
        long powerOf5 = findPower(5, evenPositions);
        long powerOf4 = findPower(4, oddPositions);

        // Final result modulo MOD
        return (int)((powerOf5 * powerOf4) % MOD);
    }

    /**
     * Function to compute (a^b) % MOD using binary exponentiation
     * a ^ b = (a ^ (b / 2) ^ 2),  if b is even
     * a ^ b = a * (a ^ (b / 2) ^ 2),  if b is odd
     *
     * Time Complexity: O(log b)
     * Space Complexity: O(log b) due to recursive call stack
     *
     * @param a Base
     * @param b Exponent
     * @return (a^b) % MOD
     */
    private long findPower(long a, long b) {
        if (b == 0)
            return 1;

        long half = findPower(a, b / 2);
        long result = (half * half) % MOD;

        if (b % 2 == 1) {
            result = (result * a) % MOD;
        }

        return result;
    }
}
