/*

Intuition and Logic:
The problem is to generate all possible letter combinations that a given digit string could represent on a traditional phone keypad.
The solution uses a recursive backtracking approach to build combinations of letters. For each digit in the input string, it appends each possible letter to the current combination, then recursively processes the next digit.
If the input string is empty, it immediately returns an empty list.
The solve function handles the recursive generation of combinations. It appends a letter to the current combination, processes the next digit, and then backtracks by removing the last letter added.

Time Complexity (TC)
The time complexity is O(4^n), where n is the length of the input string digits. This is because each digit can represent up to 4 different letters (e.g., '7' maps to 'pqrs') and the algorithm explores all possible combinations.

Space Complexity (SC)
The space complexity is O(n) due to the recursion stack, where n is the length of the input string. The additional space for the map and the result list is not considered in the recursive space complexity analysis.
*/

class Solution {
    public List<String> letterCombinations(String digits) {
        // List to store the final combinations
        List<String> combinations = new ArrayList<>();
        
        // Base case: if the input string is empty, return the empty list
        if (digits.length() == 0) {
            return combinations;
        }
        
        // Map to store the mapping of digits to letters
        Map<Character, String> digitsToLetters = new HashMap<>();
        digitsToLetters.put('2', "abc");
        digitsToLetters.put('3', "def");
        digitsToLetters.put('4', "ghi");
        digitsToLetters.put('5', "jkl");
        digitsToLetters.put('6', "mno");
        digitsToLetters.put('7', "pqrs");
        digitsToLetters.put('8', "tuv");
        digitsToLetters.put('9', "wxyz");

        // StringBuilder to keep track of the current combination of letters
        StringBuilder charsSoFar = new StringBuilder();
        
        // Helper function to generate combinations
        solve(0, digits, digitsToLetters, charsSoFar, combinations);
        
        // Return the list of combinations
        return combinations;
    }

    /**
     * Recursive helper function to generate combinations
     * @param idx Current index in the digits string
     * @param digits Input digits string
     * @param digitToLetters Map of digits to their corresponding letters
     * @param charsSoFar StringBuilder to keep track of current combination
     * @param combinations List to store the final combinations
     */
    private void solve(int idx, String digits, Map<Character, String> digitToLetters, StringBuilder charsSoFar, List<String> combinations) {
        // Base case: if we've processed all digits, add the current combination to the result list
        if (idx == digits.length()) {
            combinations.add(charsSoFar.toString());
            return;
        }

        // Get the current digit and its corresponding letters
        char digit = digits.charAt(idx);
        String letters = digitToLetters.get(digit);

        // Loop through each letter that the current digit can represent
        for (int i = 0; i < letters.length(); i++) {
            // Append the current letter to the combination
            charsSoFar.append(letters.charAt(i));
            
            // Recurse for the next digit
            solve(idx + 1, digits, digitToLetters, charsSoFar, combinations);
            
            // Backtrack by removing the last letter added
            charsSoFar.deleteCharAt(charsSoFar.length() - 1);
        }
    }
}
