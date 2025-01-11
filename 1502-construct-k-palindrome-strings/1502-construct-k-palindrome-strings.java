/**
Overview
We are given a string s composed of lowercase letters and an integer k. Our goal is to determine if it's possible to rearrange the characters of the string into exactly k palindromic substrings.

A palindrome is a string that reads the same forward and backward, showing symmetry with respect to its center. For example, the string "babac" can form 5 palindromic groupings, such as:

1 part: "bacab"
2 parts: "aca" + "bb"
3 parts: "aa" + "bb" + "c"
4 parts: "aa" + "b" + "b" + "c"
5 parts: "a" + "a" + "b" + "b" + "c"
In order to approach this problem, we need to understand the properties of palindromes, especially how character frequencies determine if a string can be rearranged into a palindrome. The key properties are:

1. Single Character Strings: Any string of length 1 is a palindrome. For example, the string "a" is a palindrome.
2. Even Frequency Characters: A palindrome can have characters that all appear an even number of times, which allows them to form symmetric halves around the center. For example, "aabb" can form the palindrome "abba".
3. One Odd Frequency Character: A palindrome can have exactly one character with an odd frequency, which will sit at the center of the string, with the other characters forming symmetric halves. For example, "abcba" has the center "c" and symmetric halves "ab" and "ba".
Knowing this, we can determine whether forming exactly k palindromes is possible by analyzing the frequencies of the characters within the string.

Approach 1: Count Odd Frequencies
Intuition
To approach this problem, we need to consider how the frequencies of characters in the string s affect the ability to form palindromes.

What key insight can we gain from knowing that a single character can be a palindrome? If every individual character in the string can be a palindrome, then the maximum number of palindromes we can form is the length of the string s. If k is greater than the length of s, it’s impossible to form k palindromes, so the answer will be false. Similarly, if k equals the length of s, we can form k palindromes, with each character of s forming its own palindrome.

Next, consider even-frequency characters. These characters can be used to form the mirrored halves of palindromes, meaning we can freely distribute them across multiple palindromes without any issue. Thus, even-frequency characters do not limit the number of palindromes we can form.

The real challenge lies with odd-frequency characters. A palindrome can only have one odd-frequency character at its center; the rest must appear in even numbers. Therefore, the number of odd-frequency characters in the string determines how many palindromes we can form. Specifically, the minimum number of palindromes we can make is equal to the number of odd-frequency characters, because each odd-frequency character requires its own palindrome.

Thus, if the number of odd-frequency characters is greater than k, it’s impossible to form k palindromes, so we return false. If the number of odd-frequency characters is less than or equal to k, we can form k palindromes, and the answer will be true.

Algorithm
1. Handle initial edge cases, comparing the length of s to k.
    - If the length of s is less than k, we return false, as we do not have enough characters to form k palindromes.
    - If the length of s is equal to k, we return true, as we can simply use each character of s to form a palindrome.
2. Initialize:
    - an array freq of size 26, representing the frequencies of each alphabetical character.
    - an integer oddCount, representing the number of odd frequencies found in the string.
3. Iterate through s, incrementing the value of the index in freq corresponding to the character.
4. Iterate through the freq, incrementing oddCount when a frequency is odd.
5. Return true if oddCount is less than or equal to k; return false otherwise.

Complexity Analysis
Let n be the length of string s.

Time complexity: O(n)

We traverse the string of length n only once.

All other operations performed happen in constant time. This includes traversing freq, as the size of the array is a fixed size of 26.

Space complexity: O(1)

The space required does not depend on the size of the input string, so only constant space is used.

Since we are limited to only lowercase letters in s, we can store the frequencies in constant space with an array of size 26, freq.
*/
class Solution {
    public boolean canConstruct(String s, int k) {
        // Handle edge cases
        if(s.length() < k)
            return false;
        if(s.length() == k)
            return true;

        // Initialize frequency array and oddCount
        int[] freq = new int[26];
        int oddCount = 0;

        // Increment the value of the index corresponding to the current character
        for(char ch : s.toCharArray())
            freq[ch - 'a']++;
        
        // Count the number of characters that appear an odd number of times in s
        for(int count : freq){
            if(count % 2 == 1)
                oddCount++;
        }

        // Return if the number of odd frequencies is less than or equal to k
        return oddCount <= k;
    }
}