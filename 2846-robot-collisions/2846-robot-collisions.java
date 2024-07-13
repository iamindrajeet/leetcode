class Solution {
    public List<Integer> survivedRobotsHealths(int[] positions, int[] healths, String directions) {
        int n = positions.length;
        Integer[] indices = new Integer[n];
        
        // Create an array to store the original indices of robots
        for (int i = 0; i < n; i++) {
            indices[i] = i;
        }
        
        // Sort the indices based on the positions of robots
        Arrays.sort(indices, (i, j) -> positions[i] - positions[j]);
        
        Stack<Integer> stack = new Stack<>();
        List<Integer> result = new ArrayList<>();
        
        // Process each robot based on the sorted positions
        for(int currentIndex : indices) {
            if(directions.charAt(currentIndex) == 'R') {
                stack.push(currentIndex); // Push the index of robots moving right into the stack
            } else {
                // Process collisions with robots moving left
                while(!stack.isEmpty() && healths[currentIndex] > 0) {
                    int topIndex = stack.pop();
                    
                    if(healths[topIndex] > healths[currentIndex]) {
                        healths[topIndex] -= 1; // The top robot survives with reduced health
                        healths[currentIndex] = 0; // The current robot dies
                        stack.push(topIndex); // Push the top robot back to the stack
                    } else if(healths[topIndex] < healths[currentIndex]) {
                        healths[currentIndex] -= 1; // The current robot survives with reduced health
                        healths[topIndex] = 0; // The top robot dies
                    } else {
                        // Both robots have the same health and destroy each other
                        healths[topIndex] = 0;
                        healths[currentIndex] = 0;
                    }
                }
            }
        }
        
        // Collect the health of surviving robots
        for(int i = 0; i < n; i++) {
            if(healths[i] > 0) {
                result.add(healths[i]);
            }
        }
        
        return result;
    }
}

/*
Intuition and Logic:
1. The problem involves robots positioned along a line with health values, moving either left or right.
2. When two robots collide, they reduce each other's health. The robot with higher health survives and continues.
3. To efficiently process the collisions, we can use a stack to keep track of robots moving to the right.
4. We iterate through the robots based on their sorted positions. If a robot is moving right, we push it to the stack.
5. If a robot is moving left, we check for collisions with robots in the stack.
6. After processing all robots, we collect the health values of surviving robots.

Time Complexity (TC):
- Sorting the robots based on their positions takes O(n log n).
- Processing each robot and handling collisions takes O(n).

Overall TC: O(n log n).

Space Complexity (SC):
- Using a stack to keep track of robots moving right and an array for sorted indices takes O(n) space.

Overall SC: O(n).
*/
