/*
Intuition and Logic:
1. Stack for Nested Structures: Use a stack to handle nested parentheses and their corresponding element counts.
2. TreeMap for Sorting: Use TreeMap to maintain elements in sorted order.
3. Iterate Through Formula: Traverse through the formula string to parse elements, counts, and parentheses.
4. Manage Counts: For each element, update its count in the current scope (top of the stack). When encountering a closing parenthesis, apply the multiplier to the counts and merge the counts with the previous scope.

Time Complexity (TC):
Parsing and Stacking: Each character in the formula is processed once, leading to O(n) operations where n is the length of the formula.

Sorting in TreeMap: Operations on TreeMap are O(logk) for insertions and lookups, where k is the number of distinct elements. In the worst case, O(n).

Space Complexity (SC):
Stack and Maps: The stack can contain multiple TreeMap instances, and each map can store up to O(n) elements in the worst case.
Auxiliary Space: Additional space for storing the elements, counts, and intermediate strings.
Overall SC: O(n).

*/

class Solution {
    public String countOfAtoms(String formula) {
        // Initialize stack to keep track of counts in nested parentheses
        Stack<Map<String, Integer>> stack = new Stack<>();
        stack.push(new TreeMap<>());
        
        int n = formula.length();
        int i = 0;

        while (i < n) {
            char currentChar = formula.charAt(i);

            // If current character is '(', push a new map to stack
            if (currentChar == '(') {
                stack.push(new TreeMap<>());
                i++;
            } 
            // If current character is ')', process the multiplier and merge maps
            else if (currentChar == ')') {
                Map<String, Integer> topMap = stack.pop();
                i++;
                StringBuilder multiplierBuilder = new StringBuilder();
                
                // Get the multiplier for the group inside parentheses
                while (i < n && Character.isDigit(formula.charAt(i))) {
                    multiplierBuilder.append(formula.charAt(i));
                    i++;
                }
                int multiplier = multiplierBuilder.length() > 0 ? Integer.parseInt(multiplierBuilder.toString()) : 1;

                // Apply the multiplier to the counts in the top map
                for (String element : topMap.keySet()) {
                    int count = topMap.get(element);
                    topMap.put(element, count * multiplier);
                }

                // Merge the top map with the map below it on the stack
                for (String element : topMap.keySet()) {
                    stack.peek().put(element, stack.peek().getOrDefault(element, 0) + topMap.get(element));
                }
            } 
            // If current character is an element, process the element and its count
            else {
                StringBuilder elementBuilder = new StringBuilder();
                elementBuilder.append(formula.charAt(i++));
                
                // Get the full element name (considering possible lowercase letters)
                while (i < n && Character.isLowerCase(formula.charAt(i))) {
                    elementBuilder.append(formula.charAt(i++));
                }
                
                StringBuilder countBuilder = new StringBuilder();
                
                // Get the count of the element
                while (i < n && Character.isDigit(formula.charAt(i))) {
                    countBuilder.append(formula.charAt(i++));
                }
                int count = countBuilder.length() > 0 ? Integer.parseInt(countBuilder.toString()) : 1;

                // Add the element and its count to the top map on the stack
                String element = elementBuilder.toString();
                stack.peek().put(element, stack.peek().getOrDefault(element, 0) + count);
            }
        }

        // Construct the result string from the final map on the stack
        StringBuilder result = new StringBuilder();
        for (String element : stack.peek().keySet()) {
            result.append(element);
            int count = stack.peek().get(element);
            if (count > 1) {
                result.append(count);
            }
        }
        
        return result.toString();
    }
}
