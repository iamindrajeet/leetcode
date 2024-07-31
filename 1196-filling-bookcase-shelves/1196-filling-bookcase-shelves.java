class Solution {
    int n, WIDTH;
    int[][] memo;

    public int minHeightShelves(int[][] books, int shelfWidth) {
        n = books.length; // Number of books
        WIDTH = shelfWidth; // Width of each shelf
        memo = new int[1001][1001]; // Memoization array to store results of subproblems

        // Initialize the memo array with -1 to indicate uncomputed states
        for(int[] row : memo)
            Arrays.fill(row, -1);

        // Start the recursive function from the first book, with the full width of the shelf and initial height 0
        return solve(books, 0, shelfWidth, 0);
    }

    // Recursive function to calculate the minimum height for placing books up to index `i`
    private int solve(int[][] books, int i, int remainingWidth, int maxHeight) {
        // Base case: If all books are considered, return the accumulated maxHeight
        if(i >= n)
            return maxHeight;

        // Check if the result for the current state is already computed
        if(memo[i][remainingWidth] != -1)
            return memo[i][remainingWidth];
        
        int bookWidth = books[i][0]; // Width of the current book
        int bookHeight = books[i][1]; // Height of the current book

        int keep = Integer.MAX_VALUE;
        int skip = Integer.MAX_VALUE;

        // Option 1: Keep the current book on the current shelf if it fits
        if(bookWidth <= remainingWidth)
            keep = solve(books, i + 1, remainingWidth - bookWidth, Math.max(maxHeight, bookHeight));
        
        // Option 2: Place the current book on a new shelf
        skip = maxHeight + solve(books, i + 1, WIDTH - bookWidth, bookHeight);

        // Store the result in the memo array and return the minimum of both options
        memo[i][remainingWidth] = Math.min(keep, skip);

        return memo[i][remainingWidth];
    }
}

/*
Time Complexity (TC):
The time complexity is O(n * shelfWidth) where n is the number of books and shelfWidth is the shelf width. This is because we iterate over all books and for each book, we may explore up to shelfWidth possible remaining widths.

Space Complexity (SC):
The space complexity is also O(n * shelfWidth) due to the memoization array. Additionally, the recursive call stack can go up to O(n) depth.
*/
