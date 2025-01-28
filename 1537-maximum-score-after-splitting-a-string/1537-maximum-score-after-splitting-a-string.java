/**
Approach 1: Brute Force

Intuition
In this problem, we need to make a "split" which involves separating the input into a left part and a right part.

To start, we can check every possible split. We will use an integer i to iterate over the string, where i represents the index of the final character in the left part.

For a given i, we iterate on the indices of s from 0 to i and count how many times 0 occurs. We then iterate on the indices from i + 1 until the last index and count how many times 1 occurs. The sum of these counts represents the score for the current split, and we take the maximum of all scores.

Note that we cannot iterate i until the final index, but rather the second last index. If we were to iterate to the final index, the right part would be empty, which is not allowed by the problem.

Algorithm
1. Initialize the answer maxScore = 0.
2. Iterate i from 0 until s.length - 1:
    - Initialize the current score = 0.
    - Iterate j from 0 to i:
        - If s[j] == '0', increment score.
    - Iterate j from i + 1 until s.length:
        - If s[j] == '1', increment score.
    - Update maxScore with score if it is larger.
3. Return maxScore.

Complexity Analysis

Given n as the length of nums,

Time complexity: O(n^2)
We iterate i over n−1 indices. For each iteration, we have two iterations over j, traversing over a total of n indices. Thus, we iterate O(n⋅(n−1)) = O(n^2) times.

Space complexity: O(1)
We aren't using any extra space other than a few integers.
*/
// class Solution {
//     public int maxScore(String s) {
//         int maxScore = 0;
//         for(int i = 0; i < s.length() - 1; i++) {
//             int score = 0;
//             for(int j = 0; j <= i; j++) {
//                 if(s.charAt(j) == '0')
//                     score++;
//             } 
//             for(int j = i + 1; j < s.length(); j++) {
//                 if(s.charAt(j) == '1')
//                     score++;
//             }
//             maxScore = Math.max(maxScore, score);
//         }
//         return maxScore;
//     }
// }


/**

Approach 2: Count Left Zeros and Right Ones
Intuition

We can improve on the previous solution by noticing that between a split at index i and index i + 1, we are only changing one character (more specifically, moving it from the right substring to the left substring), leaving the other characters unchanged. Instead of iterating over the entire string for each split, we only need to check the moved character and calculate the score for the new split based on the previous split.

We start by counting how many times 1 occurs in s. Let's store this value in a variable ones. We will also have a variable zeros that represents how many 0 are in the left part. Initially, our variables ones and zeros are set as if the left part is empty and the right part is the entire string.

Now, we iterate i in the same manner as the previous approach: each index i represents the final index of the left part. At each iteration i, we remove s[i] from the right part and add it to the left part.

There are two possibilities for each index i:

1. If s[i] == '1': this 1 was in the right part, but it is now joining the left part. Thus, we lose 1 score since the right part is losing a 1. Decrement ones.
2. If s[i] == '0', this 0 was in the right part, but it is now joining the left part. Thus, we gain 1 score since the left part is gaining a 0. Increment zeros.

We update the answer with zeros + ones at each iteration if it is larger.

Algorithm

1. Initialize ones as the number of times 1 occurs in s.
2. Initialize noOfZeros = 0 and the answer maxScore = 0.
3. Iterate i from 0 until s.length - 1:
    - If s[i] == '1', decrement ones.
    - Otherwise, increment zeros.
    - Update maxScore with zeros + ones if it is larger.
4. Return maxScore.

Complexity Analysis

Given n as the length of nums,

Time complexity: O(n)

We start by finding the frequency of 1, which costs O(n). Next, we iterate over the string once, performing O(1) work at each iteration. Thus, our time complexity is O(2n)=O(n).

Space complexity: O(1)

We aren't using any extra space other than a few integers.
*/
class Solution {
    public int maxScore(String s) {
        // Step 1: Calculate the total number of '1's in the string
        int noOfOnes = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1') {
                noOfOnes++;
            }
        }

        // Initialize variables to track the maximum score and the count of '0's
        int maxScore = 0;
        int noOfZeros = 0;

        // Step 2: Iterate through the string up to the second last character
        for (int i = 0; i < s.length() - 1; i++) {
            // Update the counts of '0's and '1's based on the current character
            if (s.charAt(i) == '1') {
                noOfOnes--; // A '1' shifts to the right part after the split
            } else {
                noOfZeros++; // A '0' remains in the left part after the split
            }

            // Calculate the score and update the maximum score
            maxScore = Math.max(maxScore, noOfZeros + noOfOnes);
        }

        return maxScore;
    }
}