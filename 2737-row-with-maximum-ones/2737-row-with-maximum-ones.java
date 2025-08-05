/*

Time complexity : 
1. Sorting each row
If there are m rows and each row has n columns:
Sorting each row takes O(n log n).

There are m rows, so total sorting time = O(m * n log n).

2. Main loop: Binary search on each row

For each row, you run binary search: O(log n)

For m rows: total time = O(m * log n)

✅ Total Time Complexity
Putting it all together:
Time = O(m * n log n) [for sorting] + O(m * log n) [for binary search]
     = O(m * n log n)
*/
class Solution {
    public int[] rowAndMaximumOnes(int[][] mat) {
        for(int[] row : mat)
            Arrays.sort(row);
        // result[0] = index of row with max number of 1s
        // result[1] = number of 1s in that row
        int[] result = new int[2];
        int maxNoOf1s = -1;

        for (int i = 0; i < mat.length; i++) {
            int idx = findFirstIndexOf1(mat[i]);

            // If row contains at least one '1'
            if (idx != -1) {
                int noOf1s = mat[i].length - idx;

                // Update result if current row has more 1s than previously found
                if (noOf1s > maxNoOf1s) {
                    maxNoOf1s = noOf1s;
                    result[0] = i;
                    result[1] = noOf1s;
                }
            }
        }

        return result;
    }

    // Binary search to find the first occurrence of 1 in a sorted binary row
    private int findFirstIndexOf1(int[] row) {
        int left = 0, right = row.length - 1;
        int idx = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (row[mid] == 1) {
                idx = mid;        // Potential first 1 found
                right = mid - 1;  // Search left half for earlier 1s
            } else {
                left = mid + 1;   // Search right half
            }
        }

        return idx; // Returns -1 if no 1 is found
    }
}
