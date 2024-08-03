/*
Approach 1: Sorting

1. Sort both the input arrays arr and target in ascending order.
2. Iterate through the elements of both sorted arrays simultaneously:
    a. Compare corresponding elements from arr and target.
    b. If any pair of elements differs, return false as the arrays cannot be made equal.
3. If all elements match after the iteration, return true indicating that the arrays can be made equal by rearranging the elements.

Time Complexity: O(NlogN)

Sorting each array takes O(NlogN). Iterating through the two arrays to check for differences takes O(N).
Thus, the total time complexity is O(NlogN).

Space Complexity: O(logN)

Assuming the quicksort implementation for sorting, there is a recursive overhead where the maximum depth of the call stack is O(logN). Thus, the total space complexity is O(logN).

*/
// class Solution {
//     public boolean canBeEqual(int[] target, int[] arr) {
//         Arrays.sort(target);
//         Arrays.sort(arr);

//         for(int i = 0; i < arr.length; i++)
//             if(arr[i] != target[i])
//                 return false;
//         return true;
//     }
// }

/*
Approach 2: Frequency Counting With 2 Maps

Algorithm
1. Create a frequency map arrFreq to count the occurrences of each number in the array arr.
    a. Iterate through arr and for each number, update the frequency in arrFreq using getOrDefault to handle missing keys.
2. Create a frequency map targetFreq to count the occurrences of each number in the array target.
    a. Iterate through target and for each number, update the frequency in targetFreq similarly.
3. Compare the size of the key sets of arrFreq and targetFreq.
    a. If they differ in size, return false, indicating the arrays cannot be equal.
4. Iterate through the keys in arrFreq:
    a. For each key, check if the frequency in targetFreq matches the frequency in arrFreq.
    b. If any frequency does not match, return false.
5. If all checks are passed, return true, indicating the arrays can be made equal by reversing subarrays.

Complexity Analysis
Let N be the size of arrays target and arr.

Time Complexity: O(N)

Iterating through each array and updating their dictionaries takes O(N) time. Iterating through one of the dictionary's keys and performing lookups will also take O(N) time. Thus, the total time complexity is O(N).

Space Complexity: O(N)

In the worst case, each array's dictionary will have arr.length keys, taking up O(N) space. Thus, the total space complexity is O(N).
*/

// class Solution {
//     public boolean canBeEqual(int[] target, int[] arr) {
//         // Map to maintain frequency count for arr
//         Map<Integer, Integer> arrFreq = new HashMap<>();
//         for(int num : arr)
//             arrFreq.put(num, arrFreq.getOrDefault(num, 0) + 1);

//         // Map to maintain frequency count for target
//         Map<Integer, Integer> targetFreq = new HashMap<>();
//         for(int num : target)
//             targetFreq.put(num, targetFreq.getOrDefault(num, 0) + 1);
        
//         // Number of distinct elements of the 2 arrays are not equal
//         if(arrFreq.size() != targetFreq.size())
//             return false;
        
//         for(int num : arrFreq.keySet()){
//             // Frequency for num differs
//             if(targetFreq.getOrDefault(num, 0) != arrFreq.getOrDefault(num, 0))
//                 return false;
//         }
//         return true;
//     }
// }

/*
Approach 3: Frequency Counting With 1 Map

Algorithm
1. Create a frequency map arrFreq to count the occurrences of each number in the array arr.
    a. Iterate through arr and update the frequency of each number using getOrDefault to handle missing keys.
2. Iterate through each number in the target array:
    a. Check if the number is present in arrFreq. If not, return false as the arrays cannot be made equal.
    b. Decrease the frequency of the number in arrFreq by 1.
    c. If the frequency of the number becomes 0, remove the number from arrFreq as there are no more occurrences needed.
3. After processing all numbers in target, check if arrFreq is empty.
    a. If it is empty, return true, indicating that the arrays can be made equal.
    b. If not, return false.

Complexity Analysis
Let N be the size of arrays target and arr.

Time Complexity: O(N)

Iterating through one array and updating their dictionary takes O(N) time. Iterating through an array and performing lookups in the dictionary will also take O(N) time. Thus, the total time complexity is O(N).

Space Complexity: O(N)

In the worst case, the dictionary will have arr.length keys, taking up O(N) space. Thus, the total space complexity is O(N).
*/
class Solution {
    public boolean canBeEqual(int[] target, int[] arr) {
        // Map to maintain frequency count for arr
        Map<Integer, Integer> arrFreq = new HashMap<>();
        for(int num : arr)
            arrFreq.put(num, arrFreq.getOrDefault(num, 0) + 1);

        for(int num : target){
            // If num does not appear in target, then arrays are not equal
            if(!arrFreq.containsKey(num))
                return false;
            
            // Decrement the frequency count for num and
            // remove key if the count goes to 0
            arrFreq.put(num, arrFreq.getOrDefault(num, 0) - 1);
            if(arrFreq.get(num) == 0)
                arrFreq.remove(num);
        }
        return arrFreq.size() == 0;
    }
}
