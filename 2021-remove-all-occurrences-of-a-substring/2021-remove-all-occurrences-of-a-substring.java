/**
Approach 1: Iteration
Intuition
We are given a string s and a substring part, and we need to repeatedly remove the first occurrence of part from s until it no longer appears. Since the constraints are relatively small (s.length <= 1000 and part.length <= 1000), we can try a brute force approach.

We can use a simple iterative approach which loops through s as long as part is present in it. Each time we find part, we need to remove its first occurrence. To do this, we first locate the leftmost occurrence of part in s. Once we know where it starts, we can break s into three sections: the part of the string before the occurrence of part, the occurrence of part itself, and the part of the string after part. By combining the first and third sections (effectively leaving out the middle section), we remove that occurrence of part from s.

When the loop finishes, s will no longer contain any occurrences of part, so we return it as the result.\

Algorithm
1. Run a while loop to repeatedly check if the string s contains the substring part.
    - Find the index of the leftmost occurrence of part in s and store it in a variable partStartIndex.
    - Use the substring method to extract the portion of s before part (s.substring(0, partStartIndex)) and the portion 
      after part (s.substring(partStartIndex + part.length())).
    - Concatenate the first and last portions and assign it back to s.
3. Return the updated string s, which no longer contains any occurrences of part.

Complexity Analysis
Let n be the length of the string s and m be the length of the substring part.

Time complexity: O(n^2/m)

The algorithm uses a while loop to repeatedly remove the leftmost occurrence of part from s. Each iteration of the loop involves finding the index of part, which takes O(n) time, and then creating a new string by concatenating the segments before and after part, which also takes O(n) time. In the worst case, there are O(n/m) such iterations (e.g., when part is non-overlapping and removed sequentially). The total time across all iterations is O(n⋅(n/m))=O(n^2/m).

Space complexity: O(n)

Although the algorithm does not explicitly use additional data structures, each iteration creates a new string by concatenating the segments before and after part. This results in the creation of intermediate strings, each of size up to O(n). The space required to store these intermediate strings dominates the space complexity, leading to O(n) space usage.

*/
// class Solution {
//     public String removeOccurrences(String s, String part) {
//         // Continue removing occurences of 'part' as long as it exists in 's'
//         while(s.contains(part)) {
//             // Find the index of the leftmost occurrence of 'part'
//             int partStartIndex = s.indexOf(part);
//             // Remove the substring 'part' from 's' by concatenating the segments before and after 'part'
//             s = s.substring(0, partStartIndex) + s.substring(partStartIndex + part.length());
//         }
//         return s;
//     }
// }

/**
 * Approach 2: Stack
 * Intuition
 * In the first approach, we relied on built-in methods to find and remove
 * substrings. Let’s explore how to implement this functionality entirely on our
 * own.
 * 
 * One issue with repeatedly removing substrings from a string is that it
 * requires recreating the entire string every time. We need a way such that
 * removing the substring characters from a string at any point is as close to
 * constant time as possible.
 * 
 * We can simulate this using a stack. A stack allows us to remove its topmost
 * element in constant time. So, if we incrementally put the characters of s in
 * the stack, the moment we find out that the last part of the stack forms part,
 * we simply pop the entire substring out. This means we needed to only loop
 * over the length of part, rather than the entire string s.
 * 
 * To implement this, we can loop over each character of s and add it to the
 * stack. As we add characters, we constantly check if the most recent portion
 * of the stack matches the substring part. If it does, we remove those
 * characters from the stack. This approach avoids scanning the entire string
 * repeatedly and only focuses on the portions of s that could potentially
 * contain part.
 * 
 * However, if at any point the characters don’t match, it means that the stack
 * doesn’t contain part at the top. In that case, any intermediate pops made
 * during the check need to be undone, so the characters are pushed back onto
 * the stack in the correct order. The process continues for the rest of the
 * string.
 * 
 * When we finish processing all the characters in s, the stack will contain the
 * modified version of s with all occurrences of part removed. At this point,
 * the stack’s contents are reversed compared to the original string, so we
 * reverse them back to produce the final result, which is then returned.
 * 
 * Algorithm
 * 1. Initialize a stack of characters stk to store the characters of the string
 * as they are processed.
 * 2. Calculate the lengths of the input string s and the substring part,
 * storing them in strLength and partLength, respectively.
 * 3. Use a for loop to iterate through each character in the string s, starting
 * from index 0 and ending at strLength - 1.
 * - Push the current character of the string onto the stack.
 * - Check if the size of the stack is greater than or equal to partLength. If
 * so:
 * - Use the helper method checkMatch to check if the top of the stack matches
 * part:
 * - If a match is found, pop the top partLength characters from the stack.
 * 4. After processing the entire string, initialize a string result to
 * construct the resulting string.
 * 5. While the stack is not empty, pop each character from the stack and append
 * it to the result.
 * 6. Reverse the order of result to correct the sequence of characters and
 * return it.
 * 
 * Complexity Analysis
 * Let n be the length of the string s, and m be the length of the substring
 * part.
 * 
 * Time complexity: O(n⋅m)
 * 
 * The algorithm iterates through each character of the string s, contributing
 * O(n) to the complexity. For each character pushed onto the stack, the
 * algorithm checks if the top m characters of the stack match part. This
 * involves an O(m) comparison for potential matches. Since this check can occur
 * for each character in s, the worst-case time complexity is O(n⋅m).
 * 
 * Space complexity: O(n)
 * 
 * The stack stores up to O(n) characters in the worst case (e.g., when no part
 * substrings are removed).
 * 
 */
class Solution {
    public String removeOccurrences(String s, String part) {
        Stack<Character> stack = new Stack<>();
        int strLength = s.length(), partLength = part.length();

        // Iterate through each character in the input string `s`
        for (int i = 0; i < strLength; i++) {
            // Push the current character onto the stack
            stack.push(s.charAt(i));

            // If the stack contains at least `partLength` characters, check for a match
            if (stack.size() >= partLength && checkMatch(stack, part, partLength)) {
                // Remove last 'partLength' characters from the stack
                for (int j = 0; j < partLength; j++) {
                    stack.pop();
                }
            }
        }

        // Build the final string from the remaining characters in the stack
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        // Since we build the result from the stack (LIFO order), reverse it before
        // returning
        return sb.reverse().toString();
    }

    private boolean checkMatch(Stack<Character> stack, String part, int partLength) {
        // Compare the top `partLength` characters in the stack with `part`
        for (int i = 0; i < partLength; i++) {
            if (stack.get(stack.size() - partLength + i) != part.charAt(i)) {
                return false;
            }
        }
        return true; // Return true indicating `part` was found and removed
    }
}
