//Approach-1 (Using extra space +  Sliding Window)
//T.C : O(n)
//S.C : O(2*n) ~ O(n)
class Solution {
    public int minSwaps(int[] nums) {
        int n = nums.length;
        int[] temp = new int[2 * n];
        
        // Creating a circular array by repeating the input array
        for (int i = 0; i < 2 * n; i++) {
            temp[i] = nums[i % n];
        }

        // Counting the number of ones in the original array
        int countOfOnes = 0;
        for (int num : nums) {
            countOfOnes += num;
        }

        // Initialize sliding window pointers and counters
        int i = 0, j = 0;
        int currCount = 0; // Current count of 1s in the window
        int maxCount = 0;  // Maximum count of 1s found in any window

        // Sliding window over the circular array
        while (j < 2 * n) {
            if (temp[j] == 1) {
                currCount++;
            }
            
            // When the window size exceeds the count of ones, slide the window
            if (j - i + 1 > countOfOnes) {
                currCount -= temp[i];
                i++;
            }

            // Update the maximum count of 1s found in the window
            maxCount = Math.max(maxCount, currCount);
            j++;
        }

        // The minimum number of swaps needed is the difference between the count of ones and the max count of 1s in the best window
        return countOfOnes - maxCount;
    }
}

/*
Time Complexity (TC): 
- O(n): The main operations (creating the temp array and the sliding window) each take O(n) time.

Space Complexity (SC): 
- O(n): The temp array takes O(2n) space, which simplifies to O(n).
*/


//Approach-2 (Sliding Window without using any extra space)
//T.C : O(n)
//S.C : O(1)
// class Solution {
//     public int minSwaps(int[] nums) {
//         int n = nums.length;

//         // Counting the number of ones in the original array
//         int countOfOnes = 0;
//         for (int num : nums) {
//             countOfOnes += num;
//         }

//         // Initialize sliding window pointers and counters
//         int i = 0, j = 0;
//         int currCount = 0; // Current count of 1s in the window
//         int maxCount = 0;  // Maximum count of 1s found in any window

//         // Sliding window over the circular array without using extra space
//         while (j < 2 * n) {
//             if (nums[j % n] == 1) {
//                 currCount++;
//             }
            
//             // When the window size exceeds the count of ones, slide the window
//             if (j - i + 1 > countOfOnes) {
//                 if (nums[i % n] == 1) {
//                     currCount--;
//                 }
//                 i++;
//             }

//             // Update the maximum count of 1s found in the window
//             maxCount = Math.max(maxCount, currCount);
//             j++;
//         }

//         // The minimum number of swaps needed is the difference between the count of ones and the max count of 1s in the best window
//         return countOfOnes - maxCount;
//     }
// }

/*
Time Complexity (TC): 
- O(n): We iterate through the array twice, but the dominant operation is linear.

Space Complexity (SC): 
- O(1): We are not using any extra space except for a few variables.
*/