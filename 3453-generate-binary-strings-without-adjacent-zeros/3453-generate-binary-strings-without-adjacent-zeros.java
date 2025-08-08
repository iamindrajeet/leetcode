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
