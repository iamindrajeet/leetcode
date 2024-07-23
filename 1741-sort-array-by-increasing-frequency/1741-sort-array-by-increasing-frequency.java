/*
Approach: Customized Sorting
Intuition
To sort the numbers, we first arrange them based on their frequency in ascending order. Numbers that appear less frequently will come before those with higher frequencies. We use a hashmap, freq, to count the occurrences of each number in the array.

If two numbers have the same frequency, we then sort them by their values in descending order. This introduces a dual sorting criterion: first by frequency and then by value.

To accomplish this, we will apply a custom sorting function using lambda expressions. These anonymous functions let us define sorting logic inline. Specifically, our lambda function ensures that numbers are compared primarily by their frequency, and secondarily by their value if frequencies match. This approach guarantees that the final sorted list adheres to both sorting criteria.

Algorithm
1. Initialize an unordered map freq to store the frequency of each integer in the input array nums.
2. Traverse through each integer num in the array nums.
3. Increase the count of num in the freq map using freq[num]++.
4. Sort the array nums using the sort function with a custom comparator:
5. Compare two integers a and b based on their frequencies stored in the freq map:
    a. If freq[a] (frequency of a) equals freq[b] (frequency of b), then:
        Return  b - a to ensure that in case of tie-in frequency, larger values come first (decreasing order).
    b. Otherwise, return freq[a] - freq[b] to sort by frequency in increasing order.
6. Return the sorted nums array, which now reflects the integers sorted primarily by frequency in ascending order, and by value in descending order when frequencies are tied.

Complexity Analysis

Let N be the length of nums.

Time complexity: O(NlogN).

Sorting nums incurs a time complexity of O(NlogN). Iterating over nums when counting frequencies incurs a time complexity of O(N), which can be ignored since O(NlogN) is the dominating term.

Space Complexity (SC):

1. Frequency Calculation: Storing frequencies in a HashMap: In the worst case, all elements are unique, so space complexity is O(n).

2.Conversion to Integer Array: An additional Integer array is used: O(n).

3. Auxiliary Space for Sorting: Timsort used by Arrays.sort has an auxiliary space complexity of O(n) in the worst case.

Overall, the space complexity is dominated by the space used to store the frequencies and the auxiliary space used for sorting:
SC=O(n)+O(n)+O(n)=O(n)
*/

class Solution {
    public int[] frequencySort(int[] nums) {
        // Create a hashmap to store the frequency of each number in the array
        Map<Integer, Integer> freq = new HashMap<>();
        for(int num : nums)
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        
        // Convert the primitive int array to an Integer array.
        // This is necessary because Java's Arrays.sort method doesn't directly support
        // sorting primitive arrays (int[]) with a lambda comparator.
        Integer[] numsObj = new Integer[nums.length];
        for(int i = 0; i < nums.length; i++){
            numsObj[i] = nums[i];
        }

        // Sort the Integer array based on the frequency of each number.
        // If two numbers have the same frequency, sort them in descending order.
        Arrays.sort(numsObj, (a, b) -> {
            if(freq.get(a).equals(freq.get(b)))
                return b - a; // If frequencies are equal, sort in descending order
            return freq.get(a) - freq.get(b); // Otherwise, sort based on frequency in ascending order
        });

        // Convert the Integer array back to a primitive int array to match the return type.
        for (int i = 0; i < nums.length; i++) {
            nums[i] = numsObj[i];
        }

        // Return the sorted array
        return nums;
    }
}
