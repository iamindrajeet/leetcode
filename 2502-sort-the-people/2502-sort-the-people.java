/*
Approach : Sorted Map

Intuition
We established that the two steps to solving this problem are:

    1. Establishing a mapping between the heights and the names.
    2. Sorting the heights.

Is there a way to achieve this simultaneously? Enter sorted maps—a data structure similar to hash maps but with the added benefit of maintaining its entries in sorted order (ascending by default).

We use the heights as keys and the names as the values in the map. The map inherently arranges the keys in order based on heights. Finally, we can traverse the entries in the map and fill our resultant array from the back, obtaining the required names in descending order of heights.

Algorithm
a. Initialize a variable numberOfPeople to the length of the names array.
b. Create a sorted map heightToNameMap to store height-name pairs.
c. Fill heightToNameMap with the height as the key and the name as the value for each entry.
d. Initialize an array sortedNames.
e. Initialize currentIndex to numberOfPeople - 1, since we intend to fill sortedNames from the back to ensure the names are in descending order of height.
f. Iterate over the keys of heightToNameMap. For each key height:
g. Add the name corresponding to height to sortedNames[currentIndex].
h. Decrement currentIndex to move to the next position from the end towards the start.
i. Return sortedNames as our result.

Complexity Analysis
Let n be the length of the names array.

Time complexity: O(nlogn)

The algorithm iterates over the length of n to insert each height-name pair in the sorted map. Each insertion in the sorted map requires O(logn) time. Thus, the total complexity of this step is O(nlogn).

To fill the sortedNames array, we iterate over all n entries in the map. Each get() operation takes another O(logn) time, making the time complexity of this step O(nlogn).

Thus, the total time complexity of the algorithm is 2O(nlogn) or O(nlogn).

Space complexity: O(n)

The only additional space used by the algorithm is a sorted map to store the height-name pairs, which takes O(n) space.
*/

class Solution {
    public String[] sortPeople(String[] names, int[] heights) {
        int noOfPeople = names.length;

        // create a TreeMap to store height-name pairs (automatically sorted by height) 
        Map<Integer, String> heightToNameMap = new TreeMap<>();

        // Populate the map with height as key and name as value
        for(int i = 0; i < noOfPeople; i++)
            heightToNameMap.put(heights[i], names[i]);

        String[] sortedNames = new String[noOfPeople];

        // Index for filling sortedNames array from end to start
        int currentIndex = noOfPeople - 1;

        // Iterate through the map (sorted by height in ascending order) and fill the sortedNames array from end to start
        for(int height : heightToNameMap.keySet()){
            sortedNames[currentIndex] = heightToNameMap.get(height);
            currentIndex--;
        }

        return sortedNames;
    }
}