/**
Approach 1 - Brute Force

Time Complexity Analysis (TC):
Finding the sum of candies → O(n)
Finding the maximum candy value → O(n)
Iterating from maxCandy to 1 → At worst case, O(maxCandy)
    - Inside the loop, we iterate over candies array → O(n)
    - Worst case scenario occurs when maxCandy = max(candies), so complexity is O(n × maxCandy)
Worst Case Complexity: O(n*max(candies))

*/
// class Solution {
//     public int maximumCandies(int[] candies, long k) {
//         // Step 1: Calculate the total sum of candies
//         long sumOfCandies = Arrays.stream(candies).sum();

//         // If total candies are less than k, it's not possible to distribute at least 1
//         // candy per child
//         if (sumOfCandies < k)
//             return 0;

//         // Step 2: Find the maximum candy count in a single pile
//         int maxCandy = Arrays.stream(candies).max().orElse(0);

//         // Step 3: Try distributing candies from the maximum value down to 1
//         for (int candy = maxCandy; candy >= 1; candy--) {
//             long count = 0;

//             // Count how many children can receive `candy` pieces from the given piles
//             for (int index = 0; index < candies.length; index++) {
//                 count += candies[index] / candy;
//             }

//             // If we can distribute at least `k` piles, return the current candy count
//             if (count >= k)
//                 return candy;
//         }

//         // If no valid distribution is found, return 0
//         return 0;
//     }
// }

/*
Approach 2 - Binary Search

Time Complexity (TC) Analysis

1. Finding sum and max value of candies → O(n)
2. Binary Search on [1, maxCandy] → O(log maxCandy)
3. Checking if distribution is possible (canDistribute):
    - Iterates over candies[] → O(n) per binary search step.
    - Runs for O(log maxCandy) steps.
Total Complexity: O(n+nlogmaxCandy)=O(nlogmaxCandy)

Space Complexity : O(1)

*/

class Solution {
    public int maximumCandies(int[] candies, long k) {
        // Step 1: Calculate the total sum of candies
        long sumOfCandies = 0;
        for(int candy : candies) {
            sumOfCandies += candy;
        }
        // If total candies are less than k, it's not possible to distribute at least 1 candy per child
        if (sumOfCandies < k)
            return 0;

        // Step 2: Define the search space
        int left = 1, right = Arrays.stream(candies).max().orElse(0); // Max candy size in a single pile
        int result = 0;
        

        // Step 3: Perform binary search on the possible candy count per child
        while (left <= right) {
            int mid = left + (right - left) / 2; // Mid represents a possible candy count per child

            // Check if it's possible to distribute `mid` candies per child
            if (canDistribute(candies, mid, k)) {
                result = mid; // If possible, update result and try for a larger value
                left = mid + 1; // Increase the search space
            } else {
                right = mid - 1; // Otherwise, decrease the search space
            }
        }
        return result; // Maximum candies per child that can be evenly distributed
    }

    /**
     * Checks if we can distribute at least `k` children `candySize` candies each.
     * 
     * @param candies    Array representing candy piles
     * @param candySize  The number of candies per child we are trying to allocate
     * @param k          The number of children
     * @return           True if we can distribute, False otherwise
     */
    private boolean canDistribute(int[] candies, int candySize, long k) {
        for (int candy : candies) {
            k -= candy / candySize; // Count how many children can receive `candySize` candies
        }
        return k <= 0; // If we have distributed to at least `k` children, return true
    }
}
