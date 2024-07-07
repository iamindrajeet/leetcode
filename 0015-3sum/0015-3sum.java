/*
Logic and Intuition:
The goal is to find all unique triplets in the given array that sum up to zero. Here's a step-by-step explanation of the approach:

Sort the Array:

Sorting the array helps in efficiently finding the triplets using the two-pointer technique.
It also makes it easier to skip duplicates, ensuring the uniqueness of the triplets.
Iterate through the Array:

Use a for-loop to iterate through the array. The current element at index i will be considered the first element of the triplet.
For each element nums[i], we need to find two other elements nums[left] and nums[right] such that their sum is zero.
Skip Duplicates:

To avoid duplicate triplets, if the current element nums[i] is the same as the previous one nums[i-1], we skip this iteration. This ensures that each triplet is unique.
Two-pointer Technique:

Initialize two pointers: left (starting just after i, i.e., i + 1) and right (starting at the end of the array).
Calculate the sum of the triplet: sum = nums[i] + nums[left] + nums[right].
If the sum is zero, we have found a valid triplet. Add it to the result list.
Move the left pointer to the right and the right pointer to the left, skipping any duplicate values to ensure the uniqueness of the triplets.
If the sum is less than zero, increment the left pointer to increase the sum.
If the sum is greater than zero, decrement the right pointer to decrease the sum.
Continue until Left Meets Right:

Repeat the process until the left pointer is less than the right pointer.
By following the above steps, we ensure that we find all unique triplets that sum up to zero.

Time Complexity:
O(n^2):
Sorting the array takes O(nlogn).
The outer loop runs in O(n) and for each iteration, the two-pointer approach runs in 
O(n). Thus, the nested operations result in O(n ^ 2).
The overall time complexity is dominated by \U0001d442(n^2).

Space Complexity:
O(n):
The space complexity is \U0001d442(\U0001d45b) due to the space required for the output list, which stores the resulting triplets.
No additional space is used that grows with input size, except for the space needed to store the result.

*/

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        
        // Sort the array to use two-pointer technique
        Arrays.sort(nums);
        
        for (int i = 0; i < nums.length - 2; i++) {
            // If the current value is the same as the one before, skip it to avoid duplicate triplets
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            
            int left = i + 1;
            int right = nums.length - 1;
            
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                
                if (sum == 0) {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    
                    // Move left and right pointers to the next different numbers to avoid duplicates
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }
                    
                    left++;
                    right--;
                } else if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        
        return result;
    }
}
