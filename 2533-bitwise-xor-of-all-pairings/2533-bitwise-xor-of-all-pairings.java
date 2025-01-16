/**
Approach : Space Optimized Bit Manipulation

Intuition
A key observation is that the contribution of any element from nums1 or nums2 to the final result depends on the length of the other array:
    - For an element a1 in nums1, it is XOR'd with every element in nums2. So, its total contribution depends on the 
    length of nums2 (n2).
    - Similarly, for an element b1 in nums2, its total contribution depends on the length of nums1 (n1).

Let’s simplify this further:
1. If n2 (length of nums2) is even, each element in nums1 is XOR'd an even number of times. Using the property of XOR (a ^ a = 0), all such elements cancel out and contribute 0 to the result.
2. If n2 is odd, each element in nums1 is XOR'd an odd number of times. Using the property that an odd number of XORs leaves the element unchanged, all elements in nums1 retain their value in the result.

The same logic applies to nums2 when considering the length of nums1.

Depending on whether n1 and n2 are even or odd, there are four possible scenarios:
1. Both n1 and n2 are even:
    - All elements in nums1 and nums2 contribute 0 to the result since their total occurrences are even.
2. n2 is odd, n1 is even:
    - Elements in nums1 occur an odd number of times and contribute to the result.
    - Elements in nums2 occur an even number of times and contribute 0.
    Thus the answer will be XOR of all elements in nums1.
3. n1 is odd, n2 is even:
    - Elements in nums2 occur an odd number of times and contribute to the result.
    - Elements in nums1 occur an even number of times and contribute 0.
    Thus the answer will be XOR of all elements in nums2.
4. Both n1 and n2 are odd:
    - Elements in both nums1 and nums2 occur an odd number of times and retain their value in the result.
    Thus the answer will be XOR of all elements in nums1 XOR'd with XOR of all elements in nums2.

Algorithm
1. Initialize two variables xor1 and xor2 to store the XOR results for the first and second arrays respectively, both starting at 0.
2. Initialize two variables len1 and len2 to store the lengths of the input arrays nums1 and nums2 respectively.
3. If the length of the second array nums2 is odd:
    - Iterate through each number in the first array nums1. For each number:
        - Compute its XOR with the current value of xor1.
4. If the length of the first array nums1 is odd:
    - Iterate through each number in the second array nums2. For each number:
        - Compute its XOR with the current value of xor2.
5. Compute and return the XOR of xor1 and xor2 as the final result.

Complexity Analysis
Let n and m be the lengths of the arrays nums1 and nums2 respectively.

Time complexity: O(n+m)

The algorithm performs two conditional iterations. If len2 is odd, it iterates through nums1 taking O(n) time. If len1 is odd, it iterates through nums2 taking O(m) time. In the worst case, both conditions are true, leading to a total time complexity of O(n+m).

Space complexity: O(1)

The algorithm only uses four variables (xor1, xor2, len1, len2) regardless of the input size. These variables consume constant space and do not grow with the input size. Therefore, the space complexity is O(1).
*/

class Solution {
    public int xorAllNums(int[] nums1, int[] nums2) {
        // Initialize XOR results for both arrays
        int xor1 = 0;
        int xor2 = 0;

        // Get the length of both arrays
        int len1 = nums1.length;
        int len2 = nums2.length;

        // If nums2 length is odd, each element in nums1 appears odd times in final result
        if(len2 % 2 != 0){
            for(int num : nums1){
                xor1 ^= num;
            }
        }

        // If nums1 length is odd, each element in nums2 appears odd times in final result
        if (len1 % 2 != 0) {
            for (int num : nums2) {
                xor2 ^= num;
            }
        }
        // Return XOR of both results
        return xor1 ^ xor2;
    }
}