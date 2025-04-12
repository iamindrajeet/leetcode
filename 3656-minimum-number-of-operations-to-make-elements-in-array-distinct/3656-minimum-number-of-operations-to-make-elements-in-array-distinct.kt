/**
Approach : Reverse traversal
Intuition
If the repeated element x appears at indices i and j with i<j, then all elements before index i must be removed. This reduces the problem to finding the longest suffix of the array in which all elements are distinct. Since each time it is necessary to remove 3 elements, to remove all elements before index i, i.e., nums[0⋯i], at least ceil((i + 1) / 3) removal operations are required.

If the array length is n, we traverse it in reverse order, using seen to record the elements that have already appeared. When we reach the first duplicate element nums[i], it indicates that the element already exists in the current suffix. We then return the minimum number of operations: Math.ceil((i + 1) / 3). If there are no duplicate elements in the array, we return 0.

Complexity Analysis
Let n be the length of the array nums.

Time complexity: O(n).
We only need to traverse the array once.

Space complexity: O(n).
A hash map is used to store the traversed elements. Since up to n elements may be stored, the required space is O(n).
*/
class Solution {
    fun minimumOperations(nums: IntArray): Int {
        val seen = mutableSetOf<Int>()
        
        for (i in nums.size - 1 downTo 0) {
            if (seen.contains(nums[i])) {
                return Math.ceil((i + 1) / 3.0).toInt();
            } else {
                seen.add(nums[i])
            }
        }
        
        return 0;
    }
}
