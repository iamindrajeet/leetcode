/*
Approach 1: Brute Force
Intuition
Given that the number of boxes is bounded by 2000, we can use brute force techniques to solve this problem. This involves calculating the total number of operations for each box individually and storing the results in an array.

First, we go through all the boxes to check if a box contains a ball. If a box has a ball, we then calculate how many operations are needed to move that ball to the current box by iterating through all other boxes. The number of operations needed to move a ball from one box to another is based on the distance between their positions. This is simply the absolute difference between the indices of the two boxes.

Next, we add up the differences for all the balls and keep a running total of the operations required for each box. These totals are stored in an answer array, which holds the result for each box. Finally, after processing all boxes, we return the answer array.

Algorithm
Initialize the Result Array:

Create an array answer of size equal to the length of the input string boxes and initialize all elements to 0.
Iterate Through Each Box:

Loop through the boxes using an index variable currentBox.
Check for Balls in the Current Box:

If the current box contains a ball (i.e., boxes.charAt(currentBox) == '1'):
Iterate through all other boxes using an index variable newPosition.
For each box, calculate the distance to the currentBox using the absolute difference Math.abs(newPosition - currentBox).
Add this distance to answer[newPosition].
Return the Result:

After processing all boxes, return the answer array.

Complexity Analysis
Let n be the size of the string boxes.

Time Complexity: O(n^2)

The algorithm iterates through each box, and for each box containing a ball, it iterates through all other boxes to calculate the distances. This results in a nested loop structure with n iterations for both the outer and inner loops, leading to a total time complexity of O(n^2).

Space Complexity: O(1)

We use an answer array to store the result. However, since this array is part of the output defined by the problem, it is not considered in the space complexity analysis. Therefore, the overall space complexity remains O(1).

*/
class Solution {
    public int[] minOperations(String boxes) {
        int[] answer = new int[boxes.length()];
        for(int currBox = 0; currBox < boxes.length(); currBox++){
            // If the current box contains a ball, calculate the number of moves for every box.
            if(boxes.charAt(currBox) == '1'){
                for(int newPosition = 0; newPosition < boxes.length(); newPosition++){
                    answer[newPosition] += Math.abs(newPosition - currBox);
                }
            }
        }
        return answer;
    }
}