/**
Approach : Monotonic Stack Approach(Finding nsr and nsl)

Finding Next Smaller Left
1. If heights[i] is smaller than the top of stack, pop elements until we find a smaller one.
2. The top of the stack is the left boundary (i.e., nextSmallerLeft[i]).
3. If stack is empty, there is no smaller element to the left (-1).

Finding Next Smaller Right
1. We repeat a similar process but from right to left.
2. The top of stack is the right boundary (nextSmallerRight[i]).
3. If stack is empty, there is no smaller element to the right (n).

Calculating Area

Width of the rectangle:
width=nextSmallerRight[i]−nextSmallerLeft[i]−1
Area:
area=heights[i]×width
Update maxArea if the new area is larger.

T.C - O(n)
S.C - O(n)
*/
class Solution {
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int[] nextSmallerLeft = new int[n];
        int[] nextSmallerRight = new int[n];
        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;

        // Finding next smaller element to the left for each bar
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                stack.pop();
            }
            nextSmallerLeft[i] = (stack.isEmpty()) ? -1 : stack.peek();
            stack.push(i);
        }

        // Clear stack before finding next smaller elements on the right
        stack.clear();

        // Finding next smaller element to the right for each bar
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                stack.pop();
            }
            nextSmallerRight[i] = (stack.isEmpty()) ? n : stack.peek();
            stack.push(i);
        }

        // Calculating the max area for each bar
        for (int i = 0; i < n; i++) {
            int width = nextSmallerRight[i] - nextSmallerLeft[i] - 1;
            int area = heights[i] * width;
            maxArea = Math.max(maxArea, area);
        }

        return maxArea;
    }
}
