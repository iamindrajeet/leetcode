
/**
 * Time Complexity Analysis:
 * - `sieveOfEratosthenes(int num)`: O(n log log n) → Efficient for finding primes up to 'num'.
 * - Collecting prime numbers in the range [left, right]: O(n).
 * - Finding the closest prime pair: O(m), where m is the number of primes in the range.
 * - Overall, the worst-case time complexity is **O(n log log n)** (dominated by the sieve).
 *
 * Space Complexity Analysis:
 * - The boolean array `prime[]` takes O(n) space.
 * - The `primeNumberList` can store at most O(n) elements in the worst case.
 * - The overall space complexity is **O(n)**.
 */

class Solution {
    public int[] closestPrimes(int left, int right) {
        // Step 1: Generate all prime numbers up to 'right' using the Sieve of Eratosthenes
        boolean[] primeNumbers = sieveOfEratosthenes(right);
        
        List<Integer> primeNumberList = new ArrayList<>();
        
        // Step 2: Collect all prime numbers in the range [left, right]
        for (int i = left; i <= right; i++) {
            if (primeNumbers[i]) {
                primeNumberList.add(i);
            }
        }
        
        // Step 3: Initialize variables to track the closest prime pair
        int minDiff = Integer.MAX_VALUE;
        int[] result = {-1, -1};
        
        // Step 4: Iterate through the list of prime numbers to find the pair with the smallest difference
        for (int i = 1; i < primeNumberList.size(); i++) {
            int diff = primeNumberList.get(i) - primeNumberList.get(i - 1);
            if (diff < minDiff) {
                minDiff = diff;
                result[0] = primeNumberList.get(i - 1);
                result[1] = primeNumberList.get(i);
            }
        }
        
        return result;
    }

    /**
     * This method implements the Sieve of Eratosthenes to find all prime numbers up to 'num'.
     * 
     * @param num The upper bound to find prime numbers
     * @return A boolean array where prime[i] is true if 'i' is a prime number, otherwise false
     */
    public boolean[] sieveOfEratosthenes(int num) {
        boolean[] prime = new boolean[num + 1]; 
        Arrays.fill(prime, true); // Assume all numbers are prime initially

        // Start marking non-prime numbers
        for (int i = 2; i * i <= num; i++) {
            if (prime[i]) {
                for (int j = i * i; j <= num; j += i) { // Mark multiples of i as non-prime
                    prime[j] = false;
                }
            }
        }
        
        // 0 and 1 are not prime numbers
        prime[0] = false;
        prime[1] = false;
        
        return prime;
    }
}

