/*
 * Approach: Simulation
 *
 * Intuition:
 * The direction the pillow travels is determined by its current position and previous direction.
 * - If the pillow is with the first person, it can only move forward.
 * - If it is with the last person, it can only move backward.
 * - For all other positions, the movement direction follows the previous direction.
 *
 * Algorithm:
 * Start with the pillow at the first person (currentPillowPosition = 1).
 * Begin counting time from 0 (currentTime = 0).
 * Set the initial direction of movement towards the end of the line (direction = 1).
 * Enter a loop that runs until currentTime is less than time:
 * - Check if moving in the current direction (direction) will keep the pillow within the line boundaries (1 to n):
 *   - Move the pillow to the next position (currentPillowPosition + direction).
 *   - Increment the current time (currentTime++) since one second has passed.
 * - Reverse the direction of movement (direction *= -1) if moving out of bounds.
 * After simulating for time seconds, return currentPillowPosition, which identifies the person holding the pillow.
 *
 * Complexity Analysis:
 * - Time complexity: O(time). The algorithm runs a loop time times, with each iteration representing one second.
 * - Space complexity: O(1). The algorithm uses a constant amount of extra space for variables.
*/

// class Solution {
//     public int passThePillow(int n, int time) {
//         // Start with the pillow at the first person
//         int currentPillowPosition = 1;
//         // Begin counting time from 0
//         int currentTime = 0;
//         // Set the initial direction of movement towards the end of the line
//         int direction = 1;

//         // Continue simulating until currentTime reaches time
//         while (currentTime < time) {
//             // Check if moving in the current direction will keep the pillow within the line boundaries
//             if (currentPillowPosition + direction > 0 && currentPillowPosition + direction <= n) {
//                 // Move the pillow to the next position
//                 currentPillowPosition += direction;
//                 // Increment the current time since one second has passed
//                 currentTime++;
//             } else {
//                 // Reverse the direction of movement if moving out of bounds
//                 direction *= -1;
//             }
//         }

//         // After simulating for time seconds, return the current position of the pillow
//         return currentPillowPosition;
//     }
// }


/**
 * Approach 2: Math
 * 
 * Intuition:
 * To understand how the pillow moves among the people in line, we analyze its pattern of movement:
 * - The pillow completes a full round traveling from the first person to the last or vice versa in n - 1 seconds.
 * - Dividing time by n - 1 gives us fullRounds, the number of complete round trips.
 * - extraTime = time % (n - 1) determines the remaining time after these full rounds.
 * 
 * Movement Direction:
 * - If fullRounds is even, the pillow moves forward.
 * - If fullRounds is odd, the pillow moves backward after each complete round.
 * 
 * Position Calculation:
 * - When moving forward (fullRounds is even), the position after extraTime seconds is extraTime + 1.
 * - When moving backward (fullRounds is odd), the position is n - extraTime.
 * 
 * Algorithm:
 * - Calculate fullRounds = time / (n - 1) and extraTime = time % (n - 1).
 * - Determine the position based on whether fullRounds is even or odd.
 * 
 * Complexity Analysis:
 * - Time complexity: O(1). Constant time complexity as it performs a fixed number of operations.
 * - Space complexity: O(1). Uses a fixed number of auxiliary variables regardless of input size.
 */

class Solution {

    public int passThePillow(int n, int time) {
        // Calculate the number of complete rounds of pillow passing
        int fullRounds = time / (n - 1);

        // Calculate the remaining time after complete rounds
        int extraTime = time % (n - 1);

        // Determine the position of the person holding the pillow
        // If fullRounds is even, the pillow is moving forward.
        // If fullRounds is odd, the pillow is moving backward.
        if (fullRounds % 2 == 0) {
            return extraTime + 1; // Position when moving forward
        } else {
            return n - extraTime; // Position when moving backward
        }
    }
}
