class Solution {
    public String[] divideString(String s, int k, char fill) {
        int len = s.length();

        // Calculate number of chunks (ceil(len / k))
        int arraySize = (len + k - 1) / k;

        // Initialize result array
        String[] result = new String[arraySize];
        int index = 0;

        for (int i = 0; i < len; i += k) {
            // Get the end index for the current chunk (may go beyond length)
            int end = Math.min(i + k, len);

            // Extract substring from i to end
            String chunk = s.substring(i, end);

            // If the chunk is shorter than k, pad it with the fill character
            if (chunk.length() < k) {
                int fillCount = k - chunk.length();
                StringBuilder sb = new StringBuilder(chunk);
                for (int f = 0; f < fillCount; f++) {
                    sb.append(fill);
                }
                chunk = sb.toString();
            }

            // Add to result
            result[index++] = chunk;
        }

        return result;
    }
}
