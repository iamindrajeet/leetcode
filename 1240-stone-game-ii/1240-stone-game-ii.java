/*
Overview
The problem at hand is a variation of the "Stone Game" problem, where two players (Alice and Bob) take turns picking stones from piles. Alice always goes first, and the objective is to maximize the number of stones a player can get. The twist here is that the number of piles a player can take on their turn depends on a variable M, which can change based on the previous player's move.

- Intuition and Logic
1. Understanding the Game Dynamics
Alice and Bob are playing a game with piles, an array where each element represents a pile of stones.
On their turn, a player can take x piles, where 1 <= x <= 2 * M, starting from the current index i.
M is a variable that represents the maximum number of piles that can be taken in the next turn. Initially, M is 1, but it can increase based on the number of piles taken in the current turn (x). After a player takes x piles, the new M for the next turn becomes max(M, x).

2. Objective
Alice wants to maximize the number of stones she collects by the end of the game.
Bob wants to minimize the number of stones Alice can collect (since he goes second).

3. Recursive Approach with Memoization
To solve this problem, a recursive approach with memoization is used to avoid recalculating results for the same game state multiple times.

4. Recursive Function solveForAlice
This function is designed to evaluate the best possible outcome for the player whose turn it is:

Parameters:

piles: The array of stone piles.
person: Indicates whose turn it is. 1 for Alice, 0 for Bob.
i: The current index in the piles array from which the player can start taking piles.
M: The maximum number of piles that can be taken in this turn.
Base Case:

If i >= n (beyond the last pile), return 0 because no more stones can be taken.
Memoization Check:

If the result for the current state (person, i, M) is already computed (stored in memo), return it to avoid redundant calculations.
Recurrence Relation:

Alice's Turn (person == 1): She wants to maximize her score, so the function explores all possible moves (taking x piles) and keeps track of the maximum stones she can get by choosing the optimal x.
Bob's Turn (person == 0): He wants to minimize Alice's score. The function explores all possible moves and keeps track of the minimum stones Alice can get after Bob’s optimal move.

Iterating Over Possible Moves:

The loop iterates over all possible values of x that Alice or Bob can take (1 <= x <= min(2 * M, n - i)). It accumulates the stones collected and recursively calls the function to determine the outcome if the other player plays optimally after this move.

Memoization Update:

After calculating the optimal result for the current state, it is stored in the memo array to ensure that it can be reused if the same state is encountered again.

Example Walkthrough
Suppose piles = [2, 7, 9, 4, 4].

Initial Call: solveForAlice(piles, 1, 0, 1):

Alice starts at index 0 with M = 1. She can take 1 or 2 piles.
The function recursively evaluates all possibilities to maximize Alice's score.
Subsequent Calls: Each recursive call will consider the next move for Alice or Bob, adjusting the index i and M accordingly.

Memoization: For each unique state (person, i, M), the result is stored in memo to avoid redundant calculations.

- Time Complexity (TC): O(n^3)
The recurrence relation has two choices per player at each step, with M being as large as n/2.
The maximum number of states is 2 * n * n. Each state requires evaluating O(n) transitions in the worst case.
Therefore, the overall time complexity is O(n^3).

- Space Complexity (SC): O(n^2)
The space complexity is O(2 * n * n) = O(n^2) due to the memoization table used to store intermediate results.

*/
class Solution {
    int n;
    int[][][] memo = new int[2][101][101]; // Memoization table to store intermediate results

    public int stoneGameII(int[] piles) {
        n = piles.length;

        // Initialize the memoization table with -1 to indicate uncomputed states
        for (int[][] arr2D : memo) {
            for (int[] arr1D : arr2D) {
                Arrays.fill(arr1D, -1);
            }
        }

        // Start the game with Alice's turn, starting at index 0 and M = 1
        // Alice - 1, Bob - 0
        return solveForAlice(piles, 1, 0, 1);
    }

    private int solveForAlice(int[] piles, int person, int i, int M) {
        // Base case: if the index i is out of bounds, no stones can be collected
        if (i >= n)
            return 0;

        // Check if the result is already computed and stored in the memo table
        if (memo[person][i][M] != -1)
            return memo[person][i][M];

        // Initialize the result based on whose turn it is
        int result = (person == 1) ? -1 : Integer.MAX_VALUE;
        int stones = 0;

        // Try taking x piles where x ranges from 1 to the minimum of 2*M and remaining piles
        for (int x = 1; x <= Math.min(2 * M, n - i); x++) {
            stones += piles[i + x - 1];

            if (person == 1) { // Alice's turn
                result = Math.max(result, stones + solveForAlice(piles, 0, i + x, Math.max(M, x)));
            } else { // Bob's turn
                result = Math.min(result, solveForAlice(piles, 1, i + x, Math.max(M, x)));
            }
        }

        // Store the computed result in the memo table and return it
        return memo[person][i][M] = result;
    }
}
