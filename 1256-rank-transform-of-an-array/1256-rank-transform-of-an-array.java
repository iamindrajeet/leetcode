/*
Approach 1: Sorting + Hash Map

Intuition
The rank of an element is based on its position in a sorted array. To determine the ranks, we first sort the array arr.

In the sorted array, the first element gets rank 1 because it is the smallest. The second element gets rank 2 if it is larger than the first. If it is equal to the first element, it also gets rank 1. In general, if an element's value is different from the previous element's value, its rank is one more than the previous element's rank. If the values are the same, they share the same rank.

We can store the ranks in a hash map, where each key is a number from arr and each value is its rank. We will use a variable rank, starting at 1, to track the rank as we go through the sorted array. For each element, we check if its value is greater than the previous element's value. If it is, we increment rank and store the new rank in the map. If it isn't, we store the same rank as the previous element.

After calculating the ranks for all elements, we can replace each element in the original array arr with its rank by looking it up in the hash map.

Algorithm
1. Initialize a hash map numToRank to store the mapping from each number in arr to its corresponding rank
2. Create a copy of arr called sortedArr. Sort it so that it is in ascending order.
3. Initialize current rank to 1.
4. Iterate through each element sortedArr[i] in sortedArr:
    - If i > 0 and sortedArr[i] > sortedArr[i-1], then rank can be incremented.
    - Add the mapping (sortedArr[i], rank) to our numToRank
5. Iterate through each element arr[i] in input arr:
    - Replace it with its rank: arr[i] = numToRank.get(arr[i])
6. Return arr


Complexity Analysis
Let N be the size of arr.

1. Time Complexity: O(N⋅logN)

Sorting sortedArr takes O(N⋅logN) time. Iterating through arr and sortedArr and inserting/looking up the rank for each number in our hash map takes in total O(N⋅logN) time. Thus, the total time complexity is O(N⋅logN)

2. Space complexity: O(N+S)

Creating a copy of arr to be sorted will take O(N) time.

The space taken by the sorting algorithm (S) depends on the language of implementation:

In Java, Arrays.sort() is implemented using a variant of the Quick Sort algorithm which has a space complexity of O(logN).
In C++, the sort() function is implemented as a hybrid of Quick Sort, Heap Sort, and Insertion Sort, with a worst-case space complexity of O(logN).
In Python, the sort() method sorts a list using the Timsort algorithm which is a combination of Merge Sort and Insertion Sort and has a space complexity of O(N).

*/

class Solution {
    public int[] arrayRankTransform(int[] arr) {
        // Store the rank for each number in arr
        Map<Integer, Integer> numToRank = new HashMap<>();
        int[] sortedArr = Arrays.copyOf(arr, arr.length);
        Arrays.sort(sortedArr);
        int rank = 1;
        for(int i = 0; i < sortedArr.length; i++){
            if(i > 0 && sortedArr[i] > sortedArr[i - 1])
                rank++;
            numToRank.put(sortedArr[i], rank);
        }
        for(int i = 0; i < arr.length; i++){
            arr[i] = numToRank.get(arr[i]);
        }
        return arr;
    }
}