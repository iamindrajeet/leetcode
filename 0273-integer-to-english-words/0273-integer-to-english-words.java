/*
Approach - Simple Recursion Story

Time Complexity (TC):
Recursive Function Calls:
The solve method uses recursion to break down the number into smaller parts (e.g., thousands, millions). Each recursive call processes a portion of the number, and the recursion depth is determined by the magnitude of the number.

For numbers from 0 to 9, the recursion is straightforward and terminates quickly.
For numbers from 10 to 99, the recursion depth is still small.
For numbers from 100 to 999, the recursion continues until the number is reduced to less than 100.
For numbers in the thousands, millions, and billions, the recursion breaks the number down into smaller chunks (thousands, millions, billions).
The depth of recursion is logarithmic with respect to the size of the number. Specifically, the recursion depth can be considered O(log₁₀(N)), where N is the number being processed.

String Concatenations:
Each recursive call involves some string concatenation operations. The time complexity of string concatenation is proportional to the length of the strings being concatenated. However, since each recursive call handles a part of the number and concatenates fixed-size words, the overall cost is manageable and proportional to the number of words.

Combining these factors, the total time complexity can be considered as O(log₁₀(N)) where N is the number being converted. This is due to the logarithmic depth of the recursion combined with linear operations for each level of recursion.

Space Complexity (SC):

Recursive Call Stack:
The primary space usage comes from the call stack due to recursion. Each recursive call adds a frame to the call stack, and the depth of recursion is O(log₁₀(N)). Therefore, the space complexity due to the call stack is O(log₁₀(N)).

String Storage:
Strings are constructed and concatenated during the recursion. The length of the final string representation is proportional to the number of words needed to represent the number. In the worst case, the length of the resulting string could be O(log₁₀(N)), where each word can be considered constant in length.

Thus, the space used for storing the string representation is manageable and proportional to the number of words generated.

Combining these factors, the overall space complexity is O(log₁₀(N)), which accounts for both the recursive call stack and the space needed to store the final string.

Summary
Time Complexity (TC): O(log₁₀(N))
Space Complexity (SC): O(log₁₀(N))

*/
class Solution {
    // Maps for converting numbers to their word equivalents
    private static final Map<Integer, String> belowTen = Map.of(
        0, "", 1, "One", 2, "Two", 3, "Three", 4, "Four", 5, "Five", 
        6, "Six", 7, "Seven", 8, "Eight", 9, "Nine"
    );

    private static final Map<Integer, String> belowTwenty = Map.of(
        10, "Ten", 11, "Eleven", 12, "Twelve", 13, "Thirteen", 14, "Fourteen", 
        15, "Fifteen", 16, "Sixteen", 17, "Seventeen", 18, "Eighteen", 19, "Nineteen"
    );

    private static final Map<Integer, String> belowHundred = Map.of(
        2, "Twenty", 3, "Thirty", 4, "Forty", 5, "Fifty", 
        6, "Sixty", 7, "Seventy", 8, "Eighty", 9, "Ninety"
    );

    public String numberToWords(int num) {
        if (num == 0) {
            return "Zero";
        }
        return solve(num);
    }

    private String solve(int num){
        if(num < 10) {
            // Handle numbers from 0 to 9
            return belowTen.get(num);
        }
        
        if(num < 20) {
            // Handle numbers from 10 to 19
            return belowTwenty.get(num);
        }
        
        if(num < 100) {
            // Handle numbers from 20 to 99
            return belowHundred.get(num / 10) + (num % 10 != 0 ? " " + belowTen.get(num % 10) : ""); 
        }
        
        if(num < 1000) {
            // Handle numbers from 100 to 999
            return solve(num / 100) + " Hundred" + (num % 100 != 0 ? " " + solve(num % 100) : "");
        }

        if (num < 1000000) {
            // Handle numbers from 1000 to 999999
            return solve(num / 1000) + " Thousand" + (num % 1000 != 0 ? " " + solve(num % 1000) : "");
        }

        if (num < 1000000000) {
            // Handle numbers from 1,000,000 to 999,999,999
            return solve(num / 1000000) + " Million" + (num % 1000000 != 0 ? " " + solve(num % 1000000) : "");
        }

        // Handle numbers from 1,000,000,000 to 2,147,483,647 (Integer.MAX_VALUE)        
        return solve(num / 1000000000) + " Billion" + (num % 1000000000 != 0 ? " " + solve(num % 1000000000) : "");
    }
}