class Solution {

    /**
     * Generates Pascal's Triangle up to numRows.
     * 
     * Time Complexity: O(numRows^2)
     *   - We generate each row from 1 to numRows.
     *   - Row i has i elements → total elements = 1 + 2 + ... + numRows = O(numRows^2).
     * 
     * Space Complexity: O(1)
     */
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<>();
        
        // Loop through each row 
        for (int i = 1; i <= numRows; i++) {
            result.add(generateRow(i)); // Generate and add the ith row
        }
        
        return result;
    }

    /**
     * Generates a single row of Pascal's Triangle.
     * 
     * For row `r`, the elements are:
     *   C(r-1, 0), C(r-1, 1), ..., C(r-1, r-1)
     * 
     * This method calculates each element using the relation:
     *   next = prev * (row - col) / col
     * 
     * Time Complexity: O(row)
     * Space Complexity: O(row) for the temporary list.
     */
    public List<Integer> generateRow(int row) {
        int ans = 1;  // First element is always 1
        List<Integer> list = new ArrayList<>();
        list.add(ans);

        // Generate subsequent elements using the binomial coefficient relation
        for (int col = 1; col < row; col++) {
            ans = ans * (row - col); // Multiply by (n - k)
            ans = ans / col;         // Divide by k
            list.add(ans);
        }
        return list;
    }
}
