/*
Approach 1: Brute Force (Simple Simulation)
T.C : O(n^2)
S.C : O(n)

*/
// class Solution {
//     public int findTheWinner(int n, int k) {
//         List<Integer> list = new ArrayList<>();
//         for(int i = 1; i <= n; i++)
//             list.add(i);

//         int i = 0;
//         while(list.size() > 1){
//             int idx = (i + k - 1 ) % list.size();
//             list.remove(idx);
//             i = idx;
//         }
//         return list.get(0);
//     }
// }


/*
Approach 2: Using Queue for Simulation
T.C : O(n*k)
S.C : O(n)
*/
// class Solution {
//     public int findTheWinner(int n, int k) {
//         Queue<Integer> queue = new LinkedList<>();
//         for(int i = 1; i <= n; i++){
//             queue.add(i);
//         }
//         while(queue.size() > 1){
//             for(int i = 1; i < k; i++){
//                 queue.add(queue.poll());
//             }
//             queue.poll();
//         }
//         return queue.peek();
//     }
// }

/*
Approach 3: Using Recursion
T.C : O(n)
S.C : O(1), but note that it will take o(n) system stack space
*/
class Solution {
    /**
     * This method determines the winner of the game where n friends stand in a circle
     * and eliminate every k-th friend until only one friend remains.
     * 
     * @param n The number of friends.
     * @param k The step count to eliminate a friend.
     * @return The position (1-based index) of the friend who wins the game.
     */
    public int findTheWinner(int n, int k) {
        // Find the zero-based index of the winner using the helper method
        int winnerIdx = findWinnerIdx(n, k);
        // Return the 1-based index of the winner
        return winnerIdx + 1;
    }

    /**
     * This is a recursive method to find the zero-based index of the winner.
     * 
     * @param n The current number of friends remaining.
     * @param k The step count to eliminate a friend.
     * @return The zero-based index of the winner.
     */
    private int findWinnerIdx(int n, int k) {
        // Base case: If there is only one friend, they are the winner (index 0)
        if (n == 1)
            return 0;

        // Recursively find the index of the winner with one less friend
        int idx = findWinnerIdx(n - 1, k);
        // Calculate the original index of the winner in the current circle
        idx = (idx + k) % n;

        return idx;
    }
}



