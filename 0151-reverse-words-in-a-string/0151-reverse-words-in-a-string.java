class Solution {
    public String reverseWords(String s) {
        // Trim leading/trailing spaces and split by space
        String[] words = s.trim().split(" ");
        StringBuilder sb = new StringBuilder();

        // Iterate from end to start
        for (int i = words.length - 1; i >= 0; i--) {
            if (!words[i].isEmpty()) { // skip empty parts from multiple spaces
                sb.append(words[i]).append(" ");
            }
        }

        // Remove the last extra space if any and return the result
        return sb.toString().trim();
    }
}
