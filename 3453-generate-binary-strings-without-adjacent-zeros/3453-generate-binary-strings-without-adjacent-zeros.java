/**

✅ Time Complexity: O(2^n)
The code builds all strings of length n made of '0' and '1', but it avoids two '0's in a row.

Still, it tries both '1' and '0' at most at each step, so in the worst case, it makes around 2^n recursive calls.

So the time it takes grows exponentially with n.

✅ Space Complexity: O(n * 2^n)
The function stores all valid strings in a list. There are about 2^n valid strings (a bit fewer because of the no-double-zero rule).

Each string has length up to n.

So total space needed to store the results is around n * 2^n.

*/
class Solution {
    public List<String> validStrings(int n) {
        List<String> result = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        // Start recursion with empty string and placeholder last character
        solve(n, sb, result, ' ');
        return result;
    }

    private void solve(int n, StringBuilder sb, List<String> result, char lastCharAdded) {
        // Base case: if current string length equals n, add to result
        if (sb.length() == n) {
            result.add(sb.toString());
            return;
        }

        // Always allowed: add '1'
        sb.append('1');
        solve(n, sb, result, '1');
        sb.deleteCharAt(sb.length() - 1); // backtrack

        // Add '0' only if last character was not '0'
        if (lastCharAdded != '0') {
            sb.append('0');
            solve(n, sb, result, '0');
            sb.deleteCharAt(sb.length() - 1); // backtrack
        }
    }
}
