/*
- Approach : String Concatenation to Summation

1. Intuition
We need to convert a given string into a sequence of integers and then repeatedly sum the digits of this sequence k times. The final result is the integer obtained after performing these operations.

One approach is to follow each step from the problem description literally:
    1. Convert each letter in the string s to its position in the alphabet: 'a' becomes 1, 'b' becomes 2, and so on.
    2. Concatenate these numbers to form a large string. For example, "zbax" becomes "262124".
    3. Perform the transformation k times. Each transformation involves summing the digits of this large number.
    4. Convert the string to digits, sum them, and convert the result back to a string. Repeat this process for k transformations. Finally, 
    convert the resulting string to an integer and return it. This method is straightforward but may be inefficient for very large numbers or high 
    values of k.

2. Algorithm
- Initialize an empty string numericString to store the numerical representation of each character in s.
- Iterate through each character ch in s:
    - Convert ch to its corresponding numerical value (1 for 'a', 2 for 'b', etc.).
    - Append this numerical value to numericString.

- While k is not equals to 0:
    - Initialize digitSum to 0 to accumulate the sum of digits.
    - Iterate through each character digit in numericString:
    - Convert digit to its integer value and add it to digitSum.
    - Convert digitSum back to a string and assign it to numericString.
    - Decrement k by 1.

- Convert the final numericString to an integer and return it.

3. Complexity Analysis
Let n be the length of s.

Time complexity: O(n)

For each character in the string s, we compute its numeric value and append it to numericString. We perform this transformation k times. In each transformation, we iterate over the digits of numericString. The length of numericString depends on the total number of digits obtained from converting characters. In the worst case, each character contributes up to 2 digits (e.g., 'z' becomes 26). Thus, the length of numericString could be up to 2n, making each transformation O(n) on average.

Thus, the time complexity for applying digit sum transformations k times is O(k⋅n), and since k is a constant, it becomes O(n). Combining this with the initial conversion step, the total time complexity is O(n+n)=O(n).

Space complexity: O(n)

We use space proportional to the length of numericString, which can be up to O(n) in the worst case. This gives us O(n) space complexity for storing the intermediate numeric string.

*/


class Solution {
    public int getLucky(String s, int k) {
        // Step 1: Convert the string `s` into a numeric string where each character
        // is replaced by its position in the alphabet.
        StringBuilder numericString = new StringBuilder();
        for(char ch : s.toCharArray()){
            numericString.append(ch - 'a' + 1);
        }

        // Step 2: Perform the transformation `k` times where each transformation
        // involves calculating the sum of digits in the numeric string.
        while(k != 0){
            int digitSum = 0;
            for(char digit : numericString.toString().toCharArray()){
                digitSum += digit - '0';
            }
            numericString = new StringBuilder(Integer.toString(digitSum));
            k--;
        }

        // Step 3: Return the final result as an integer.
        return Integer.parseInt(numericString.toString());
    }
}
