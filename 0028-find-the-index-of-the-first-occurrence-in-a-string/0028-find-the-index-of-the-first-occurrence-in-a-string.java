/*
The problem is to implement the strStr function which finds the first occurrence of the needle string in the haystack string and returns its index. If needle is not part of haystack, it returns -1. This is essentially the problem of finding a substring in a string.

Intuition

Initial Check:
If needle is longer than haystack, return -1 immediately since needle cannot be a substring of haystack.

Sliding Window Approach:
Slide over the haystack string and for each position, check if the substring starting from that position matches needle.
String Comparison:
Instead of comparing character by character, use substring to extract a part of haystack and compare it directly with needle.

Algorithm
Edge Cases:

If needle is an empty string, return 0 because an empty string is always found at the start of any string.
If haystack is shorter than needle, return -1 because needle cannot be part of haystack.
Main Loop:

Iterate through each possible starting position in haystack up to haystackLength - needleLength.
For each starting position, check if the first character of haystack at that position matches the first character of needle.
If it matches, extract the substring of haystack and compare it with needle.
String Comparison:

If the extracted substring matches needle, return the starting index.
If no match is found after checking all possible positions, return -1.

Time Complexity
O((n - m + 1) * m): Where n is the length of haystack and m is the length of needle. This is because for each possible starting position, we compare m characters in the substring.

Space Complexity
O(m): Space used to store the substring of length m.
*/
class Solution {
    public int strStr(String haystack, String needle) {
        int haystackLength = haystack.length(), needleLength = needle.length();
        // If needle is longer than haystack, return -1
        if (needleLength > haystackLength)
            return -1;
        // Iterate over possible starting positions in haystack
        for (int i = 0; i <= haystackLength - needleLength; i++) {
            // If the first character matches, check the substring
            if (haystack.charAt(i) == needle.charAt(0)) {
                // Extract the substring of haystack
                String subString = haystack.substring(i, i + needleLength);
                // Compare the substring with needle
                if (needle.equals(subString))
                    return i;
            }
        }
        // If needle is not found in haystack, return -1
        return -1; 
    }
}
