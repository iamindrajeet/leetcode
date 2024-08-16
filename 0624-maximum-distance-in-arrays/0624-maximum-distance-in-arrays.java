/*
1. Intuition
The problem requires finding the maximum possible distance between elements from different arrays. Initially, the straightforward approach is to compare every possible pair of minimum and maximum values from different arrays. However, this approach can be optimized by reducing the number of comparisons.

2. Approach
- Initialization:
    - Start by taking the first array and setting its first element as minVal and its last element as maxVal.

- Iterate Through Remaining Arrays:
    - For each subsequent array, compute the potential maximum distance by comparing the array's last element with minVal, and the array's first 
    element with maxVal.
    - Update maxDistance accordingly.
    - After computing the distances, update minVal and maxVal with the current array's first and last elements if they provide new minimum or 
    maximum values.

3.Return the Result:
    - After iterating through all arrays, return maxDistance, which will hold the maximum distance between elements of different arrays.

4. Complexity

- Time complexity:
O(N) where N is the number of arrays. This is because we only iterate through the list of arrays once.

- Space complexity:
O(1) as we only use a fixed amount of extra space for variables like minVal, maxVal, and maxDistance, regardless of the input size.

*/

class Solution {
    public int maxDistance(List<List<Integer>> arrays) {
        // Initialize minValue and maxValue with the first array's first and last
        // element, respectively
        int minValue = arrays.get(0).get(0);
        int maxValue = arrays.get(0).get(arrays.get(0).size() - 1);

        // Initialize maxDistance to track the maximum distance found
        int maxDistance = Integer.MIN_VALUE;

        // Loop through each array starting from the second array
        for (int i = 1; i < arrays.size(); i++) {
            List<Integer> currentArray = arrays.get(i);

            // Calculate possible distances and update maxDistance
            maxDistance = Math.max(maxDistance,
                    Math.max(
                            Math.abs(minValue - currentArray.get(currentArray.size() - 1)), // Distance between minValue
                                                                                            // and the last element of
                                                                                            // current array
                            Math.abs(maxValue - currentArray.get(0)) // Distance between maxValue and the first element
                                                                     // of current array
                    ));

            // Update minValue and maxValue with the current array's first and last elements
            minValue = Math.min(minValue, currentArray.get(0));
            maxValue = Math.max(maxValue, currentArray.get(currentArray.size() - 1));
        }

        // Return the maximum distance found
        return maxDistance;
    }
}
