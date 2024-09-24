/*
Approach : Using Hash Table
- Intuition
We want to find the longest common prefix between numbers in two arrays. A prefix is formed from the digits of a number, starting from the left. To solve this, the key observation is that the prefix of a number can be reduced by removing its last digit repeatedly. By storing these reduced forms, we can efficiently check for common prefixes.

Note: In this context, a "prefix" refers to the sequence of digits that starts at the beginning of an integer and can be any length up to the full length of that integer. For example, 12 is a prefix of 123. A common prefix is one that appears at the start of both integers from arr1 and arr2.

The idea is to first create a hash table to hold all possible prefixes of the numbers from the first array (arr1). For each number in arr1, we break it down digit by digit, storing every prefix form (by dividing it by 10). This way, the hash table contains all possible digit patterns that could match any part of a number in arr2.

Next, for each number in arr2, we try to match it against the prefixes stored in the hash table. We keep reducing the number, removing digits from the end, until we find a match. Once we find a match, we compute the length of that prefix by counting its digits. The process repeats for all numbers in arr2, and we track the longest common prefix found across all comparisons.

Rather than comparing each number digit by digit across both arrays, we reduce the problem to prefix matching by storing all prefixes in a hash table and checking against it.

- Algorithm
Step 1: Build Prefixes from arr1:
    - Initialize an empty set arr1Prefixes to store all prefixes derived from arr1.
    - Iterate over each value val in arr1:
        - While val is not in arr1Prefixes and val is greater than 0:
            - Add val to arr1Prefixes (storing val as a prefix).
            - Update val to the next shorter prefix by removing the last digit (val /= 10).
Step 2: Find the Longest Matching Prefix in arr2:
    - Initialize longestPrefix to 0 to keep track of the length of the longest common prefix found.
    - Iterate over each value val in arr2:
        - While val is not in arr1Prefixes and val is greater than 0:
            - Reduce val by removing the last digit (val /= 10).
        - If val is greater than 0 (i.e., a matching prefix is found):
            - Update longestPrefix to the maximum of its current value and the length of the matched prefix (calculated 
            using log10(val) + 1).
Return the length of the longest common prefix found.

- Complexity Analysis

Let m be the length of arr1, n be the length of arr2, M be the maximum value in arr1, and N be the maximum value in arr2.
- Time Complexity: O(m⋅log10 M + n⋅log10 N) 
For each number in arr1, we repeatedly divide the number by 10 to generate its prefixes. Since dividing a number by 10 reduces the number of digits logarithmically, this process takes O(log10 M) for each number in arr1. Hence, for m numbers, the total time complexity is O(m⋅log10 M).

Similarly, for each number in arr2, we reduce it by repeatedly dividing it by 10 to check if it matches any prefix in the set. This also takes O(log10 N) for each number in arr2. Hence, for n numbers, the total time complexity is O(n⋅log10 N).

Overall, the total time complexity is O(m⋅log10 M + n⋅log10 N).

- Space Complexit: O(m⋅log10 M)

Each number in arr1 contributes O(log10 M) space to the set, as it generates prefixes proportional to the number of digits (logarithmic in the value of the number with base 10). With m numbers in arr1, the total space complexity for the set is O(m⋅log10 M).

The algorithm uses constant space for variables like longestPrefix and loop variables, so this doesn’t contribute significantly to the space complexity.

Thus, the total space complexity is O(m⋅log10 M).
*/
class Solution {
    public int longestCommonPrefix(int[] arr1, int[] arr2) {
        // Set to store all prefixes from arr1
        Set<Integer> arr1Prefixes = new HashSet<Integer>();

        // Step 1: Build all possible prefixes from arr1
        for(int num : arr1){
            while(!arr1Prefixes.contains(num) && num > 0){
                // Insert current value as prefix
                arr1Prefixes.add(num);
                // Generate the next shorter prefix by removing the last digit
                num /= 10;
            }
        }

        int longestPrefix = 0;

        // Step 2: Check each number in arr2 for the longest matching prefix
        for(int num : arr2){
            while(!arr1Prefixes.contains(num) && num > 0){
                // Reduce val by removing the last digit if not found in the prefix set
                num /= 10;
            }
            if(num > 0){
                // Length of the matched prefix using log10 to determine the number of digits
                longestPrefix = Math.max(longestPrefix, (int) Math.log10(num) + 1);
            }
        }
        return longestPrefix;
    }
}