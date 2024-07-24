/*
Approach 1: Conversion using strings and Sorting

Intuition
Observe that we need to replace every digit of all elements in nums with the digits of the mapping array. Since the data type for nums is an integer, we might find it difficult to make the updates directly on a particular digit of the integer. If we convert this integer to a string, we can directly convert any character of this integer to the desired character in constant time.

After making the changes, we can convert the mapped string to an integer and push it into an array. But, what if there are equal mapped values for two strings? Then, we need to sort them according to their indices. So, we create an array of pairs that stores the mapped integer value and its index.

Sort the array of pairs in non-decreasing order using any stable sorting algorithm. By default, C++, Java, and Python use stable sorting algorithms. Therefore, the first value of every pair is sorted in non-decreasing order. If these values are equal, the array is sorted in the non-decreasing order of the index values. Store the values of nums at these sorted indices and return them.

Algorithm
1. Initialize an array of pairs given by storePairs.
2. Iterate i through the nums array:
    a. Store a string number as the string conversion of the integer nums[i].
    b. Initialize an empty string formed.
    c. Iterate j through the string number:
        - Append the mapping of the current character of number to formed.
    d. Convert the string formed to an integer mappedValue.
    e. Push the pair mappedValue and the current index i in storePairs.
3. Sort the storePairs array.
4. Create an array answer.
5. Iterate through storePairs and append the nums value at the index to the answer.
6. Return the answer array.

Complexity Analysis
Let n be the size of the nums array.

Time complexity: O(nlogn)

For every integer in nums, we convert it to a string and perform constant operations over its length. The time taken for converting an integer to a string, and vice versa, is O(lengthofinteger) time, which is proportional to the logarithmic value of n. Therefore, the time complexity for these operations is given by O(nlogn).

Sorting the array of pairs takes O(nlogn) time. All other operations are linear or constant time.

Therefore, the total time complexity is given by O(nlogn).

Space complexity: O(n)

We create two new arrays of size n. Apart from this, some extra space is used when we sort arrays in place. The space complexity of the sorting algorithm depends on the programming language.

In Python, the sort method sorts a list using the Timsort algorithm which is a combination of Merge Sort and Insertion Sort and has O(n) additional space.
In Java, Arrays.sort() is implemented using a variant of the Quick Sort algorithm which has a space complexity of O(logn) for sorting two arrays.
In C++, the sort() function is implemented as a hybrid of Quick Sort, Heap Sort, and Insertion Sort, with a worse-case space complexity of O(logn).
Therefore, the total space complexity is given by O(n).

*/
class Solution {
    public int[] sortJumbled(int[] mapping, int[] nums) {
        // List to store pairs of mapped value and original index
        List<int[]> storePairs = new ArrayList<>();
        
        for (int i = 0; i < nums.length; i++) {
            // Convert current value to String
            String number = String.valueOf(nums[i]);
            StringBuilder formed = new StringBuilder();
            
            // Map each digit of the number using the mapping array
            for (int j = 0; j < number.length(); j++) {
                formed.append(mapping[number.charAt(j) - '0']);
            }
            
            // Convert the mapped string to an integer
            int mappedValue = Integer.parseInt(formed.toString());
            
            // Store the mapped value and original index as a pair
            storePairs.add(new int[]{mappedValue, i});
        }

        // Sort the list based on the mapped values, and by the original indices if mapped values are equal
        Collections.sort(storePairs, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0]) {
                    return o1[1] - o2[1];
                }
                return o1[0] - o2[0];
            }
        });

        // Prepare the result array using the original indices
        int[] answer = new int[nums.length];
        for (int i = 0; i < storePairs.size(); i++) {
            answer[i] = nums[storePairs.get(i)[1]];
        }
        
        return answer;
    }
}