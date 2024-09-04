/** 
 * Forward declaration of guess API.
 * @param  num   your guess
 * @return 	     -1 if num is higher than the picked number
 *			      1 if num is lower than the picked number
 *               otherwise return 0
 * int guess(int num);
*/

public class Solution extends GuessGame {
    public int guessNumber(int n) {
        // Initialize low to 1 and high to n
        int low = 1, high = n;

        // Continue searching while the search range is valid
        while (low <= high) {
            // Calculate mid to avoid potential overflow
            int mid = low + (high - low) / 2;

            // Use the guess API to check the guess
            int result = guess(mid);

            // If the guessed number is correct, return mid
            if (result == 0)
                return mid;
            // If the guessed number is too high, adjust the high pointer
            else if (result == -1)
                high = mid - 1;
            // If the guessed number is too low, adjust the low pointer
            else
                low = mid + 1;
        }

        // Return -1 if the number is not found (though this case won't happen)
        return -1;
    }
}

/**
 * Time Complexity (TC): O(log n)
 * - The binary search algorithm halves the search space in each iteration,
 * so the time complexity is O(log n).
 * 
 * Space Complexity (SC): O(1)
 * - The algorithm uses a constant amount of extra space, so the space
 * complexity is O(1).
*/
