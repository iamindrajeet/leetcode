/**
Approach : Single Pass with Frequency Array
Intuition
We use a frequency array to count how many times each number appears in the two arrays, A and B. The key idea is to avoid unnecessary nested loops by directly counting the occurrences of each number in both arrays up to the current index.

We maintain a frequency array of size n + 1. This array is used to store the count of each element's occurrence across both A and B. Since the elements in A and B are permutations of numbers from 1 to n, the frequency array has n + 1 elements to cover the range from 1 to n (ignoring index 0 for simplicity).

As we process each index currentIndex of both arrays, we increment the count of A[currentIndex] and B[currentIndex] in the frequency array. Whenever the count for an element in the frequency array reaches 2 (meaning that this number has appeared once in both A and B), we know that this element is a common element at the current prefix, and we increment the commonCount.

Finally, we store the commonCount at each index in the prefixCommonArray, which will give us the cumulative count of common elements at each position in the arrays.

Algorithm
1. Initialize an integer n to store the size of array A.
2. Create a prefixCommonArray array of size n to store the result.
3. Create a frequency array of size n + 1 to keep track of the occurrences of each number.
4. Initialize commonCount to 0, which will store the count of common elements in prefixes of A and B.
5. Iterate through each index currentIndex from 0 to n - 1:
    - Increment the frequency of A[currentIndex] and check if its count becomes 2, indicating a common element between A and 
        B. If true, increment commonCount.
    - Similarly, increment the frequency of B[currentIndex] and check if its count becomes 2. If true, increment commonCount.
    - Assign the value of commonCount to prefixCommonArray[currentIndex].
6. Return prefixCommonArray, which contains the common count for each prefix of A and B.

Complexity Analysis
Let n be the size of the input arrays A and B.

Time complexity: O(n)

The loop runs n times (from currentIndex = 0 to currentIndex = n - 1). Inside the loop, the operations involve incrementing the frequency of elements in A and B and checking if the frequency equals 2. These operations are O(1) because they involve simple array accesses and comparisons.

Therefore, the total time complexity is O(n).

Space complexity: O(n)

The space complexity is dominated by the frequency array, which requires O(n+1)=O(n) space. The output container (prefixCommonArray) is excluded from the analysis as it is part of the problem statement, and the remaining variables use O(1) space.

 */
class Solution {
    public int[] findThePrefixCommonArray(int[] A, int[] B) {
        int n = A.length;
        int[] prefixCommonArray = new int[n];
        int[] frequency = new int[n + 1];
        int commonCount = 0;

        // Iterate through the elements of both arrays
        for(int currentIdx = 0; currentIdx < n; currentIdx++) {
            // Increment frequency of current elements in A and B
            //Check if the elemenrs in A has appeared before (common in prefix)
            frequency[A[currentIdx]]++;
            if(frequency[A[currentIdx]] == 2)
                commonCount++;
            
            //Check if the elemenrs in B has appeared before (common in prefix)
            frequency[B[currentIdx]]++;
            if(frequency[B[currentIdx]] == 2)
                commonCount++;

            // Store the count of common elements for the current prefix
            prefixCommonArray[currentIdx] = commonCount;
        }

        // Return the final array with counts of common elements in each prefix
        return prefixCommonArray;

    }
}