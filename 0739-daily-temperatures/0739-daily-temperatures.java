class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        // Initialize the result array with the same length as temperatures
        int[] answer = new int[temperatures.length];

        // Stack to store indices of the temperatures array
        Stack<Integer> stack = new Stack<>();

        // Iterate through the temperatures array
        for (int i = 0; i < temperatures.length; i++) {
            // While the stack is not empty and the current temperature is higher than
            // the temperature at the index stored at the top of the stack
            while (!stack.isEmpty() && temperatures[stack.peek()] < temperatures[i]) {
                // Calculate the number of days between the current day and the day at the top
                // of the stack
                answer[stack.peek()] = i - stack.pop();
            }
            // Push the current index onto the stack
            stack.push(i);
        }

        // Return the filled answer array
        return answer;
    }
}

/**
 * Time Complexity (TC): O(n)
 * - Each element is pushed and popped from the stack at most once,
 * so the time complexity is O(n), where n is the length of the temperatures
 * array.
 * 
 * Space Complexity (SC): O(n)
 * - The space complexity is O(n) due to the space used by the stack,
 * which in the worst case can store all indices in the temperatures array.
*/