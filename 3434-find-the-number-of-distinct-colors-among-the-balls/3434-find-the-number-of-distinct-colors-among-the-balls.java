/** 
Approach 1: Hashmap and Array

Intuition
When approaching this problem, the main challenge is efficiently tracking and updating the colors of the balls after each query.

To solve this problem, we'll need to track both the number of times each color appears, and the number of distinct colors.

Let's consider the two different scenarios that occur when a query is applied to a ball. If the ball is:
1. Uncolored: the count of the newly assigned color is increased.
2. Colored: the count of the new color is increased and the count of the previously assigned color decreases.
Whether or not the number of distinct colors is impacted will depend on the total number of balls of that color already present.

A hashmap can be used for this purpose since it efficiently associates counts with specific colors.

We also need to track the current color of each ball because the problem involves overwriting existing colors. A straightforward solution is to use an array to store the color of each ball, where the index represents the ball and the value at that index represents the current color of the ball.

With these data structures in place, we can now proceed to process the queries. For each query, we update the color of the ball and adjust the count of distinct colors accordingly. As we process each query, we maintain the color count and track the balls' colors.

However, this solution ultimately fails due to exceeding the memory limit allowed for this problem.

Algorithm
1. Initialize:
    - an integer n, equal to the length of queries.
    - an array result of length n, where result[i] denotes the number of distinct colors after the ith query.
    - an array ballArray, which stores the distinct ball labels found when traversing queries and the current colors 
    associated with them.
    - A hash map colorMap, which stores the number of distinct colors after processing the current query.
2. Iterate from index 0 to n - 1 to traverse the queries. For each query, query[i]:
    - Initialize:
        - an integer ball equal to query[i][0], denoting the current ball that will be colored.
        - an integer color equal to query[i][1], denoting the color that the ball will be colored.
    - If ballArray[ball] is not 0, meaning the ball is already colored:
        - Check the existing color of ball, which will be labeled prevColor.
        - Decrement the count of prevColor in colorMap.
        - If the count becomes 0, remove prevColor from colorMap.
    - Update ballArray[ball] to color.
    - Increase the count of color in colorMap by one.
    - Set result[i] to the size of colorMap.
3. Return the result array.

Complexity Analysis
Let n be the number of queries and m be the limit.

Time Complexity: O(n)

The algorithm iterates through each query exactly once, performing constant-time operations for each query.

Specifically, for each query, it checks and updates the ballArray and colorMap, both of which are O(1) operations due to the use of a hash map (colorMap) and an array (ballArray).

Therefore, the overall time complexity is linear in the number of queries, O(n).

Note: The operations on the colorMap (such as get, put, and remove) are considered O(1) on average due to the nature of hash maps.

Space Complexity: O(m+n)

The space complexity is determined by the ballArray and the colorMap. The ballArray has a size of m+1 (since it stores the color of each ball up to the limit m), and the colorMap can store up to n distinct colors in the worst case (if all queries introduce a new color). Therefore, the space complexity is O(m+n).

Note: The result array also contributes O(n) space, but since it is part of the output, it is typically not counted in the auxiliary space complexity. However, if we include it, the space complexity remains O(m+n).
*/
// class Solution {
//     public int[] queryResults(int limit, int[][] queries) {
//         int n = queries.length;
//         int[] result = new int[n];
//         Map<Integer, Integer> colorMap = new HashMap<>();
//         int[] ballArray = new int[limit + 1];

//         // Iterate through queries
//         for(int i = 0; i < n; i++) {
//             // Extract ball label and color from query
//             int ball = queries[i][0];
//             int color = queries[i][1];
            
//             // Check if ball is already colored
//             if(ballArray[ball] != 0) {
//                 // Decrement count of the previous color of the ball
//                 int prevColor = ballArray[ball];
//                 colorMap.put(prevColor, colorMap.get(prevColor) - 1);

//                 // If there are no balls with previous color left, remove color from color map
//                 if (colorMap.get(prevColor) == 0) {
//                     colorMap.remove(prevColor);
//                 }
//             }

//             // Set color of the ball of the new color
//             ballArray[ball] = color;

//             // Increment the count of the new color
//             colorMap.put(color, colorMap.getOrDefault(color, 0) + 1);   

//             result[i] = colorMap.size();
//         }
//         return result;
//     }
// }

class Solution {
    public int[] queryResults(int limit, int[][] queries) {
        int n = queries.length;
        int[] result = new int[n];
        Map<Integer, Integer> colorMap = new HashMap<>();
        Map<Integer, Integer> ballMap = new HashMap<>();

        // Iterate through queries
        for(int i = 0; i < n; i++) {
            // Extract ball label and color from query
            int ball = queries[i][0];
            int color = queries[i][1];
            
            // Check if ball is already colored
            if(ballMap.containsKey(ball)) {
                // Decrement count of the previous color of the ball
                int prevColor = ballMap.get(ball);
                colorMap.put(prevColor, colorMap.get(prevColor) - 1);

                // If there are no balls with previous color left, remove color from color map
                if (colorMap.get(prevColor) == 0) {
                    colorMap.remove(prevColor);
                }
            }

            // Set color of the ball of the new color
            ballMap.put(ball, color);

            // Increment the count of the new color
            colorMap.put(color, colorMap.getOrDefault(color, 0) + 1);   

            result[i] = colorMap.size();
        }
        return result;
    }
}