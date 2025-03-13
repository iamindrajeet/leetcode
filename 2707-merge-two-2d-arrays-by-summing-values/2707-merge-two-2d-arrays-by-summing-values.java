/**
Time Complexity:
1. Inserting Elements into TreeMap
    - Each insertion in a TreeMap takes O(log N) time due to the underlying Red-Black Tree.
    - We are inserting nums1.length + nums2.length elements.
    - Total Time: O((m+n)log(m+n)), where m and n are the sizes of nums1 and nums2.

2. Iterating Over TreeMap to Construct the Result
    - Extracting elements from TreeMap takes O(m + n) time.

Overall Time Complexity: O((m+n)log(m+n))
The dominant term is O(log N) per insertion, so the sorting-like behavior makes it slightly slower than a linear solution.

Space Complexity:
1. TreeMap Storage:
    - In the worst case, all elements have unique keys.
    - The TreeMap will store O(m + n) elements.
2. Result Array:
    - We store the same number of unique elements in a 2D array of size O(m + n).
Overall Space Complexity: O(m+n)
Extra space beyond input: Only the TreeMap and result array contribute.

Time Complexity	    - O((m + n) log (m + n))
Space Complexity	- O(m + n)

*/
class Solution {
    public int[][] mergeArrays(int[][] nums1, int[][] nums2) {
        // Using TreeMap to store values in sorted order by key
        Map<Integer, Integer> map = new TreeMap<>();

        // Merging first array into the map
        for (int[] num : nums1) {
            int id = num[0];
            int value = num[1];
            map.put(id, map.getOrDefault(id, 0) + value); // Fixed syntax error
        }

        // Merging second array into the map
        for (int[] num : nums2) {
            int id = num[0];
            int value = num[1];
            map.put(id, map.getOrDefault(id, 0) + value); // Fixed syntax error
        }

        // Converting the map entries into a 2D result array
        int[][] result = new int[map.size()][2];
        int rowNo = 0;

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            result[rowNo++] = new int[]{entry.getKey(), entry.getValue()};
        }

        return result;
    }
}
