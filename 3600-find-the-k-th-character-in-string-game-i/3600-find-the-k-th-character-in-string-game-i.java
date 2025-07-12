
/**
✅ Time Complexity (BTC):
Let’s denote the number of iterations needed to reach length ≥ k as n.

In each iteration, the length of the string approximately doubles.

So after n iterations, length becomes roughly: 2^n.

To find when 2^n >= k ⇒ n = log₂(k)

So,

Best Case (BTC - B): O(1), if k = 1
Typical/Worst Case (BTC - T/W):
Each iteration processes all previous characters → O(2^0 + 2^1 + ... + 2^n) = O(2^n)

Since 2^n ≥ k, the total time complexity is: O(k)

✅ Space Complexity (SC):
You store all characters in a StringBuilder up to length ≥ k.

Hence, SC = O(k)


*/
class Solution {
    public char kthCharacter(int k) {
        StringBuilder str = new StringBuilder();
        str.append("a"); // Initialize with first character

        // Keep growing the string until its length reaches at least k
        while (str.length() < k) {
            int len = str.length(); // Store current length to prevent infinite loop
            for (int i = 0; i < len; i++) {
                // Append the next character in the alphabet
                str.append((char) (str.charAt(i) + 1));
            }
        }

        // Return the k-th character (1-based index)
        return str.charAt(k - 1);
    }
}
