/**
Approach : Using Hash Map
Intuition
To approach this problem, we need to consider how often each character appears in the string. The goal is to figure out how many characters need to be removed to minimize the string, based on how many times each character occurs:

If a character appears an odd number of times, we can keep exactly one instance of it, and remove the rest.
If a character appears an even number of times, we can keep two instances of it—one on the left side and one on the right side, ensuring a valid operation.
For example, let's consider the case where we have 5 'a' characters. Since 5 is odd, we'll end up with exactly one 'a'. We can remove the first and third 'a' characters because they are closest to the second 'a'. After that, we are left with three 'a' characters, and we repeat the process of removing pairs. In the end, only one 'a' remains. This is because each pair cancels out, leaving the extra character.

Now, let's look at the case with 4 'a' characters. Since 4 is even, we first remove the first and third 'a' characters, which are closest to the second 'a'. We're left with 2 'a' characters, but for comparisons, we need three characters: one as the reference pivot and two indices, one on the left and one on the right, to remove. So, we stop here in the even case.

Algorithm
1. Count the frequency of each character in the string:
    - Initialize a frequency map (charFrequencyMap).
    - For each character in the string s, increment its frequency in the map.
2.Calculate the number of characters to delete:
    - Initialize deleteCount to 0.
    - For each character's frequency in the map:
        - If the frequency is odd, add frequency - 1 to deleteCount (remove all but one).
        - If the frequency is even, add frequency - 2 to deleteCount (remove all but two).
3. Return the smallest length of the string after deletions:
    - Subtract deleteCount from the original string length.

Complexity Analysis
Let n be the size of the string s, and let k be the size of the character set.

Time Complexity: O(n)

The first loop iterates over each character in the string s, which takes O(n) time. This is because inserting or updating elements in an map has an average time complexity of O(1) per operation. The second loop iterates over the charFrequencyMap, which has at most k unique characters. This loop takes O(k) time. Since k is typically much smaller than n (e.g., k=26 for lowercase letters), the overall time complexity is dominated by the first loop, resulting in O(n).

Space complexity: O(1) or O(k)

The space used by the charFrequencyMap depends on the size of the character set k. In our case, k is fixed (e.g., 26 for lowercase letters), so the space complexity is O(1). Alternatively, it can also be expressed as O(k).
*/
class Solution {
    public int minimumLength(String s) {
        // Step 1 : Count the frequency of each character in the string
        Map<Character, Integer> charFrequencyMap = new HashMap<>();
        for(char currentChar : s.toCharArray()){
            charFrequencyMap.put(currentChar, charFrequencyMap.getOrDefault(currentChar, 0) + 1);
        }

        // Step 2 : Calculate the number of characters to delete 
        int deleteCount = 0;
        for(int frequency : charFrequencyMap.values()){
            if(frequency % 2 != 0) { 
                deleteCount += frequency - 1; // If frequency is odd, delete all except one
            } else {
                deleteCount += frequency - 2; // If frequency is even, delete all except two
            }
        }
        // Step 3: Return the minimum length after deletions
        return s.length() - deleteCount;
    }
}