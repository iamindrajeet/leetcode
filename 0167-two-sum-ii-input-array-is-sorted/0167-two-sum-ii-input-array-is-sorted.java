/*
Intuition:
The code uses a two-pointer approach where str and end initially point to the start and end of the array, respectively. It then iteratively adjusts the pointers based on whether the sum of the elements at these pointers is less than, greater than, or equal to the target value.

Approach:
Initialize two pointers, str at the start of the array and end at the end of the array.
While str is less than or equal to end, check if the sum of elements at these pointers equals the target.
If yes, return an array containing the indices str+1 and end+1.
If the sum is less than the target, increment str.
If the sum is greater than the target, decrement end.
If no such pair is found, return null.

Complexity
Time complexity:
The time complexity of this approach is O(n), where n is the number of elements in the array. The while loop iterates through the array once or twice in the worst case.

Space complexity:
The space complexity is O(1) because the code uses a constant amount of extra space regardless of the input size. The only additional space used is for the res array, which has a fixed size of 2 elements (the indices).


*/

class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int[] result = new int[2];
        int low = 0, high = numbers.length - 1;
        while (low < high) {
            int sum = numbers[low] + numbers[high];
            if (sum == target) {
                result[0] = low + 1; // Convert to 1-based index
                result[1] = high + 1; // Convert to 1-based index
                return result;
            } else if (sum > target) {
                high--;
            } else {
                low++;
            }
        }
        return null;
    }
}
