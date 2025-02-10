/** 
Approach 1: Brute Force
Intuition
In this approach, we will simply simulate the described process until we have removed all digits from s.

An important observation is that as we process the string from left to right and remove digits, the part of the string we've already processed will only contain non-digit characters (or be empty). This means that the first non-digit character to the left of the current digit will always be the one immediately before it, if such a character exists.

With this in mind, we iterate over the characters of s with charIndex from 0 to s.length - 1. When we encounter a digit, we remove both the digit and the non-digit character immediately before it. A key detail in the implementation is that after deleting a character, we should not increment the charIndex, as the next character will shift to the current position. Similarly, when deleting two characters, we should decrement the charIndex by 1, as the next character to process will shift to one position left from the current one.

Algorithm
1. Initialize charIndex to 0.
2. While charIndex is less than the current length of s:
    - If the character at charIndex is a digit:
        - Remove the digit at charIndex.
            - If there is a character to the left (i.e., charIndex > 0):
                - Remove the character at charIndex - 1.
                - Decrement charIndex by 1 to account for the removed character.
    - Otherwise, if the character at charIndex is not a digit:
        - Move to the next character by incrementing charIndex by 1.
3. Return the modified string s.

Complexity Analysis
Let n be the length of the string s and m the number of digit characters in it.

Time Complexity: O(n*m) or O(n^2).

For each digit character, we perform one or two "erase" operations, each with time complexity O(n). Therefore, processing m digits takes O(n*m). Non-digit characters are skipped and contribute O((n−m)*1) checks, which is O(n). Since m≤n the overall time complexity can be expressed as O(n^2).

Space Complexity: O(1).

Excluding the input string (which does not count toward the auxiliary space complexity), we only use a single variable (charIndex) to track the current character's position in the string. Therefore, the space complexity of the algorithm is 
O(1).

*/

// class Solution {

//     public String clearDigits(String s) {
//         int charIndex = 0;

//         StringBuilder sb = new StringBuilder(s);

//         // Until we reach the end of the string
//         while (charIndex < sb.length()) {
//             if (Character.isDigit(sb.charAt(charIndex))) {
//                 // Remove the digit from the string
//                 sb.deleteCharAt(charIndex);
//                 // If there is a character to the left of the digit, remove it
//                 if (charIndex > 0) {
//                     sb.deleteCharAt(charIndex - 1);
//                     // Adjust the index to account for the removed character
//                     charIndex--;
//                 }
//             } else {
//                 // Move to the next character if it's not a digit
//                 charIndex++;
//             }
//         }
//         return sb.toString();
//     }
// }

/**
 * Approach 2 : Using stack
 * 
 * Algorithm:
 * 1. Initialize an empty stack to store characters.
 * 2. Iterate over each character in the given string:
 * - If the character is a digit and the stack is not empty, pop the top
 * character from the stack.
 * - Otherwise, push non-digit characters onto the stack.
 * 3. After processing the string, construct the result string by popping
 * characters from the stack.
 * 4. Reverse the result as the stack stores characters in LIFO order.
 * 5. Return the final string.
 * 
 * Time Complexity: O(n), where n is the length of the input string.
 * - Each character is processed once, and stack operations (push/pop) take O(1)
 * time.
 * 
 * Space Complexity: O(n), in the worst case, when there are no digits and all
 * characters are stored in the stack.
 */
// class Solution {
// public String clearDigits(String s) {
// Stack<Character> stack = new Stack<>();

// // Process each character in the input string
// for (char ch : s.toCharArray()) {
// if (Character.isDigit(ch)) {
// // Remove the last added character if the stack is not empty
// if (!stack.isEmpty()) {
// stack.pop();
// }
// } else {
// // Push non-digit characters onto the stack
// stack.push(ch);
// }
// }

// // Construct the result string from the stack
// StringBuilder sb = new StringBuilder();
// while (!stack.isEmpty()) {
// sb.append(stack.pop());
// }

// // Reverse the string as the stack stores elements in LIFO order
// return sb.reverse().toString();
// }
// }

/**
Approach 3 : Using result string to avoid reverse call - not using stack 
 * Algorithm:
 * 1. Initialize an empty StringBuilder to store the result.
 * 2. Iterate over each character in the given string:
 * - If the character is a digit and the StringBuilder is not empty, remove the
 * last character.
 * - Otherwise, append the non-digit character to the StringBuilder.
 * 3. Return the final string after processing all characters.
 * 
 * Time Complexity: O(n), where n is the length of the input string.
 * - Each character is processed once, and StringBuilder operations
 * (append/delete) take O(1) time on average.
 * 
 * Space Complexity: O(n), in the worst case, when there are no digits and all
 * characters are stored in the StringBuilder.
 * - The auxiliary space for storing the result is also O(n).
 * 
 */
// class Solution {
//     public String clearDigits(String s) {
//         int charIndex = 0;
//         StringBuilder sb = new StringBuilder();

//         // Iterate over the input string
//         while (charIndex < s.length()) {
//             // If the current character is a digit and sb is not empty, remove the last
//             // character
//             if (Character.isDigit(s.charAt(charIndex)) && sb.length() != 0) {
//                 sb.deleteCharAt(sb.length() - 1);
//             } else {
//                 // If the character is not a digit, add it to the result
//                 sb.append(s.charAt(charIndex));
//             }
//             charIndex++;
//         }
//         return sb.toString();
//     }
// }

/**
Approach 4: In-place

Intuition
One big advantage of the previous approach is that it does not change the input string. This is helpful in situations where the input is passed by reference (like in Java) and the algorithm runs in a multithreaded environment or when the input needs to be used again after the function call. In these cases, algorithms that modify the input directly should be avoided.

However, when this is not the case, modifying the input can be more space-efficient. In such cases, in-place algorithms like the one we’ll discuss here can be good alternatives.

So, in this approach we will integrate the "stack" logic directly into the input string. Instead of pushing non-digit characters into a separate structure, we overwrite the input string in place so that non-digit characters are positioned exactly where they will appear in the final result.

To achieve this, we use a variable answerLength to track the current length of the result. When adding a new character, we place it at the answerLength position in the string and increase answerLength by 1. When removing a character, we decrease answerLength by 1, which effectively makes the last character irrelevant and ready to be overwritten.

At the end, the result is the prefix of the modified input string up to answerLength.

Algorithm
1. Initialize answerLength to 0.
2. Iterate over s with charIndex from 0 to s.length - 1:
    - If the character at charIndex is a digit:
        - If the answer is not empty (i.e. answerLength > 0) remove its last character, by decrementing answerLength by 1.
    - Otherwise, if the character at charIndex is not a digit:
        - Add it to the end of the answer, by setting s[answerLength] = s[charIndex].
        - Increment answerLength.
3. Return the first answerLength characters of the modified string s.

Complexity Analysis
Let n be the length of the string s.

Time Complexity: O(n).

Like in the previous approach, we iterate over all characters in s and perform constant-time operations, including checks and retrievals of characters in a string. Additionally, the "resize" operation on the string requires O(n) time and therefore the total time complexity of the algorithm is O(n).

Space Complexity: O(1).

As the input string does not count as auxiliary space, the algorithm requires only constant extra space for the variables answerLength and charIndex.
*/
class Solution {
    public String clearDigits(String s) {
        char[] arr = s.toCharArray();
        int charIndex = 0, answerLength = 0;
        while(charIndex < s.length()) {
            // If the current character is a digit
            if(Character.isDigit(arr[charIndex])) {
                // Decrement answerLength to remove the last character from the result
                answerLength = Math.max(answerLength - 1, 0);
            } else {
                // Place the character in the "answer" portion of the string
                arr[answerLength] = arr[charIndex];
                answerLength++;
            }
            charIndex++;
        }
        // Resize the string to match the actual length of the answer
        // new String(char[] value, int offset, int count)
        // This constructor creates a new String by copying characters from a character array (char[] value), starting from 
        // the given offset (index) and taking count number of characters.
        return new String(arr, 0, answerLength);
    }
}