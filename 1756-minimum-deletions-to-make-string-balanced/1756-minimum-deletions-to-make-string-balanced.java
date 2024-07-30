/*
Approach 1 - Using Stack

1. Stack Initialization: A Stack is used to track the characters as we iterate through the string. The goal is to identify and remove instances where 'b' appears before 'a', as these need to be deleted to maintain order.

2. Count Initialization: count is used to keep track of the number of deletions needed.

3. Iteration and Checking:

For each character in the string, if the stack is not empty and the top of the stack is 'b' while the current character is 'a', this indicates a disorder that requires removing the 'b'. Hence, the 'b' is popped from the stack and count is incremented.
If the above condition is not met, the current character is pushed onto the stack.

4. Returning the Result: The total count of deletions is returned as the result

T.C : O(n)
S.C : O(n)
*/
class Solution {
    public int minimumDeletions(String s) {
        Stack<Character> stack = new Stack<>();
        int count = 0;

        // Iterate through each character in the string
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            // If stack is not empty and the top of the stack is 'b' and current char is 'a'
            // This means we have an out-of-order 'b' before 'a', which needs to be removed
            if (!stack.isEmpty() && stack.peek() == 'b' && ch == 'a') {
                stack.pop();  // Remove the 'b' to make the order correct
                count++;      // Increment the count of deletions
            } else {
                stack.push(ch); // Otherwise, push the current character onto the stack
            }
        }

        return count; // Return the total number of deletions needed
    }
}


// Approach-2 (3 Pass + O(2*n) space)
// T.C : O(3*n)
// S.C : O(2*n)
// class Solution {
//     public int minimumDeletions(String s) {
//         int n = s.length();
        
//         // Arrays to keep track of the number of 'b's to the left of each index
//         // and the number of 'a's to the right of each index
//         int[] left_b = new int[n];
//         int[] right_a = new int[n];

//         // Count the number of 'b's encountered from the left
//         int countb = 0;
//         for (int i = 0; i < n; i++) {
//             left_b[i] = countb;  // Store the current count of 'b's before index i
//             if (s.charAt(i) == 'b')
//                 countb++;  // Increment count if current character is 'b'
//         }

//         // Count the number of 'a's encountered from the right
//         int counta = 0;
//         for (int i = n - 1; i >= 0; i--) {
//             right_a[i] = counta;  // Store the current count of 'a's after index i
//             if (s.charAt(i) == 'a')
//                 counta++;  // Increment count if current character is 'a'
//         }

//         int count = Integer.MAX_VALUE;

//         // Calculate the minimum deletions needed by checking each index
//         // The minimum deletions at each point would be the sum of 'b's to the left
//         // and 'a's to the right
//         for (int i = 0; i < n; i++) {
//             count = Math.min(count, left_b[i] + right_a[i]);
//         }

//         return count;  // Return the minimum deletions found
//     }
// }



// Approach-3 (2 Pass + O(n) space)
// T.C : O(2*n)
// S.C : O(n)
// class Solution {
//     public int minimumDeletions(String s) {
//         int n = s.length();
        
//         // Array to keep track of the number of 'a's to the right of each index
//         int[] right_a = new int[n];

//         // Count the number of 'a's encountered from the right
//         int counta = 0;
//         for (int i = n - 1; i >= 0; i--) {
//             right_a[i] = counta;  // Store the current count of 'a's after index i
//             if (s.charAt(i) == 'a')
//                 counta++;  // Increment count if current character is 'a'
//         }

//         int count = Integer.MAX_VALUE;
//         int countb = 0;

//         // Calculate the minimum deletions needed
//         // The minimum deletions at each point would be the sum of 'b's counted so far
//         // and 'a's to the right of the current index
//         for (int i = 0; i < n; i++) {
//             count = Math.min(count, countb + right_a[i]);
//             if (s.charAt(i) == 'b')
//                 countb++;  // Increment count of 'b's encountered so far
//         }

//         return count;  // Return the minimum deletions found
//     }
// }



// // Approach-4 (Constant space)
// // T.C : O(2*n)
// // S.C : O(1)
// class Solution {
//     public int minimumDeletions(String s) {
//         int n = s.length();

//         int counta = 0;
//         for (int i = n - 1; i >= 0; i--) {
//             if (s.charAt(i) == 'a')
//                 counta++;
//         }

//         int count = Integer.MAX_VALUE;
//         int countb = 0;

//         for (int i = 0; i < n; i++) {
//             if (s.charAt(i) == 'a')
//                 counta--;
//             count = Math.min(count, countb + counta);

//             if (s.charAt(i) == 'b')
//                 countb++;
//         }

//         return count;
//     }
// }