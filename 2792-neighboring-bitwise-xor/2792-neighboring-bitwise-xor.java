/**
Overview
We are given an integer array derived of length n. This array is formed by taking a binary array original (an array containing only 0s and 1s) and computing the bitwise XOR between adjacent elements in it.

For the last element in derived, the XOR is calculated as:
derived[n−1]=original[n−1]XORoriginal[0]

Our task is to determine if there exists a binary array original that could have generated the derived array.

To understand how to approach the problem, let’s recall some fundamental properties of XOR:

Commutativity: aXORb=bXORa
The order in which you XOR two numbers doesn’t matter.

Associativity: (aXORb)XORc=aXOR(bXORc)
Grouping of XOR operations doesn’t affect the result.

Identity: aXOR0=a
XOR with 0 leaves the number unchanged.

Self-inverse: aXORa=0
XORing a number with itself results in 0.

Inversion:
If aXORb=c, then:

a=bXORc
b=aXORc
These properties will help us manipulate XOR equations in coming sections to solve the problem.

Approach : Simulation
Intuition
To determine whether a valid original array can be constructed from the given derived array, we can carefully simulate how the original array would be built.

From the problem, we know:
derived[i]=original[i]XORoriginal[i+1]

Using the inversion property of XOR, we can rewrite this as:
original[i+1]=derived[i]XORoriginal[i]

This means that if we know the value of the original[i], we can calculate the next element, original[i+1], using the corresponding value from derived.

The first element of original, original[0], can be either 0 or 1 (since it’s binary).
    - If we assume original[0] = 0, we can calculate the rest of the array.
    - Similarly, we can repeat the process assuming original[0] = 1.

Once we compute all the elements of the original for both starting points, we need to check if they satisfy the circular condition:
derived[n−1]=original[n−1]XORoriginal[0]

This ensures that the last element in derived matches the XOR of the first and last elements of original.

If the circular condition is satisfied for either of the two cases (original[0] = 0 or original[0] = 1), then a valid original array exists, and we return true. Otherwise, we return false.

Algorithm
1. Create an array original initialized with {0}.
2. Construct the original array assuming the first element is 0:
    - Iterate through the derived array using a loop:
        - For each index i, calculate the next element in original as (derived[i] ^ original[i]) and append it to original.
3. Check if the first and last elements of original are equal and store the result in checkForZero.
4. Create an array original initialized with {1}.
5. Construct the original array assuming the first element is 1:
    - Iterate through the derived array using a loop:
        - For each index i, calculate the next element in original as (derived[i] ^ original[i]) and append it to original.
6. Check if the first and last elements of original are equal and store the result in checkForOne.
7. Return the logical OR of checkForZero and checkForOne.

Complexity Analysis
Let n be the size of the derived array.

Time Complexity: O(n)

The algorithm constructs the original array twice, once starting with original[0] = 0 and once with original[0] = 1. Each construction involves iterating through the derived array once, which takes O(n) time. Therefore, the overall time complexity is O(2⋅n)=O(n).

Space Complexity: O(n)

The algorithm uses an additional array original to store the intermediate results during its construction. The size of the original array is equal to the size of the derived array, requiring O(n) space. No other significant data structures are used, so the overall space complexity is O(n).
 */
class Solution {
    public boolean doesValidArrayExist(int[] derived) {
        // Create an original array initialized with 0
        int[] original = new int[derived.length + 1];
        original[0] = 0;
        for(int i = 0; i < derived.length; i++){
            original[i + 1] = derived[i] ^ original[i];
        }
        // Store the validation results in checkForZero and checkForOne respectively
        boolean checkForZero = (original[0] == original[original.length - 1]);

        original[0] = 1;
        for (int i = 0; i < derived.length; i++) {
            original[i + 1] = derived[i] ^ original[i];
        }
        boolean checkForOne = (original[0] == original[original.length - 1]);

        return checkForZero || checkForOne;
    }
}