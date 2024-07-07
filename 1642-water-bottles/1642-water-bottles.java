/**
 * Approach 1: Simulation
 * 
 * Intuition:
 * We are given two integers, numBottles and numExchange. numBottles represents the number of full water bottles,
 * and numExchange is the number of empty bottles needed to exchange for one full bottle. We need to determine the 
 * total number of water bottles we can drink. For example, if numBottles is 3 and numExchange is 3, we can drink 
 * 4 bottles of water: first drinking all 3 full bottles and then exchanging the 3 empty bottles for 1 more full bottle. 
 * Note that numExchange must be greater than 1 because if numExchange = 1, we would get one full bottle for each empty one, 
 * resulting in an infinite number of bottles.
 * 
 * The key observation here is that once you have the numExchange number of empty bottles you can exchange them with one 
 * full bottle at that point. It's equivalent to keeping the empty bottles and exchanging them at some later point. This is 
 * because the number of full bottles you will get from them won't change. This observation also clarifies that the decision 
 * we make doesn't depend on the previous decision we have made and it's not a dynamic programming problem.
 * 
 * In this approach, we simulate the process to find the number of bottles we can drink. We keep consuming bottles until we 
 * have consumed numExchange bottles, then exchange them for one full bottle. We repeat this until the number of full bottles 
 * is less than numExchange and can no longer be exchanged. Finally, we consume the remaining bottles until we have none left.
 * 
 * Note that while we have more than numExchange full bottles, we can consume them in batches of numExchange instead of one 
 * by one, because we can only get one full bottle after exchanging numExchange empty bottles. In the end, we will add the 
 * remaining numBottles (which would be less than numExchange) to our answer.
 * 
 * Algorithm:
 * Initialize the answer variable consumedBottles to 0.
 * 
 * Keep doing the following until we have more numBottles than the numExchange:
 * - Consume the numExchange number of full bottles, i.e., add numExchange to consumedBottles.
 * - Decrement numExchange from the available full bottles numBottles.
 * - Exchange the empty bottles with one full bottle, i.e., increment numBottles by one.
 * 
 * Return consumedBottles + numBottles.
 * 
 * Complexity Analysis:
 * Here, N is the number of initial full bottles.
 * 
 * Time complexity: O(N).
 * The maximum number of operations in the while loop will be when the value of numExchange is minimum, i.e., 2. In this case, 
 * we will keep consuming the 2 bottles and add 1 as an exchange. Hence, the numBottles will be decreased by one after each iteration. 
 * Hence, the time complexity is equal to O(N).
 * 
 * Space complexity: O(1).
 * No extra space is required apart from a few variables, hence the space complexity is constant.
 */

// class Solution {
//     public int numWaterBottles(int numBottles, int numExchange) {
//         int consumedBottles = 0;

//         // Loop until we have fewer full bottles than needed for an exchange
//         while (numBottles >= numExchange) {
//             // Consume numExchange number of full bottles
//             consumedBottles += numExchange;

//             // Decrement the numExchange bottles from numBottles
//             numBottles -= numExchange;

//             // Exchange the empty bottles for one full bottle
//             numBottles++;
//         }

//         // Add the remaining full bottles to the total consumed bottles
//         return consumedBottles + numBottles;
//     }
// }


/*
Approach-2 : Optimized Simulation

Problem:
Given:
- numBottles: Initial number of full water bottles.
- numExchange: Number of empty bottles required to exchange for one full bottle.

Objective:
Calculate the total number of bottles consumed, including those obtained through exchanges.

Approach:
1. Initialize consumedBottles with the initial number of full bottles.
2. Initialize emptyBottles with the initial number of full bottles (all bottles will be empty once consumed).
3. Loop until the number of empty bottles is less than the number needed for an exchange:
   a. Calculate extraFullBottles obtained by exchanging empty bottles.
   b. Update consumedBottles with extraFullBottles.
   c. Update emptyBottles to include remaining empty bottles after exchanges.
4. Return the total number of consumed bottles.

Example:
Input: numBottles = 9, numExchange = 3
Output: 13 (9 full bottles + 3 exchanged bottles + 1 more from exchange)

Edge cases:
- If numBottles = 0 or numExchange = 0, return 0.
- Handle large values efficiently with integer operations.

Time Complexity: O(log(numBottles))
Space Complexity: O(1)
*/

class Solution {
    public int numWaterBottles(int numBottles, int numExchange) {
        // Initialize consumedBottles with the initial number of full bottles
        int consumedBottles = numBottles;
        // Initialize emptyBottles with the initial number of full bottles (all bottles will be empty once consumed)
        int emptyBottles = numBottles;
        
        // Loop until the number of empty bottles is less than the number needed for an exchange
        while (emptyBottles >= numExchange) {
            // Calculate the number of extra full bottles obtained by exchanging empty bottles
            int extraFullBottles = emptyBottles / numExchange;
            // Calculate the remaining empty bottles after the exchange
            int remainingEmptyBottles = emptyBottles % numExchange;
            // Add the extra full bottles to the total consumed bottles
            consumedBottles += extraFullBottles;

            // Update the emptyBottles to the sum of remaining empty bottles and extra full bottles
            emptyBottles = remainingEmptyBottles + extraFullBottles;
        }

        // Return the total number of consumed bottles
        return consumedBottles;
    }
}