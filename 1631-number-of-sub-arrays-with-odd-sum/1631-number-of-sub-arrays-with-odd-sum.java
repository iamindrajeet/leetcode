// Brute force approach
// T.C - O(n^2)
// S.C - O(1)
// class Solution {
//     public int numOfSubarrays(int[] arr) {
//         int n = arr.length;
//         int count = 0;
//         int M = (int) 1e9 + 7;
//         for (int i = 0; i < n; i++) {
//             int sum = 0;
//             for (int j = i; j < n; j++) {
//                 sum += arr[j];
//                 if (sum % 2 != 0)
//                     count = (count + 1) % M;
//             }
//         }
//         return count;
//     }
// }

// Approach : Optimal using prefix sum array
// T.C - O(n)
// S.C - O(n)
// class Solution {
//     public int numOfSubarrays(int[] arr) {
//         int n = arr.length;
//         int count = 0;  // Stores the count of subarrays with an odd sum
//         int even = 1;   // Tracks the count of even prefix sums (initialized to 1 for handling the whole array case)
//         int odd = 0;    // Tracks the count of odd prefix sums
//         int M = (int) 1e9 + 7; // Modulo to prevent integer overflow
        
//         int[] prefixSum = new int[n]; // Array to store prefix sums
//         prefixSum[0] = arr[0];

//         // Compute prefix sums
//         for (int i = 1; i < n; i++) {
//             prefixSum[i] = prefixSum[i - 1] + arr[i];
//         }

//         // Iterate through prefix sums and count subarrays with an odd sum
//         for (int i = 0; i < n; i++) {
//             if (prefixSum[i] % 2 == 0) { 
//                 // If the prefix sum is even, it can form an odd sum subarray 
//                 // only when subtracted by a previous odd prefix sum.
//                 count = (count + odd) % M; 
//                 even++; // Increment count of even prefix sums
//             } else { 
//                 // If the prefix sum is odd, it can form an odd sum subarray 
//                 // by itself and also with all previous even prefix sums.
//                 count = (count + even) % M; 
//                 odd++; // Increment count of odd prefix sums
//             }
//         }
//         return count;
//     }
// }

// Approach : Optimal using prefix sum but constant space
// T.C - O(n)
// S.C - O(1)
class Solution {
    public int numOfSubarrays(int[] arr) {
        int n = arr.length;
        int count = 0;  // Stores the count of subarrays with an odd sum
        int even = 1;   // Tracks the count of even prefix sums (initialized to 1 for handling the whole array case)
        int odd = 0;    // Tracks the count of odd prefix sums
        int M = (int) 1e9 + 7; // Modulo to prevent integer overflow
        
        int sum = 0;
        // Iterate through prefix sums and count subarrays with an odd sum
        for (int i = 0; i < n; i++) {
            sum += arr[i];
            if (sum % 2 == 0) { 
                // If the prefix sum is even, it can form an odd sum subarray 
                // only when subtracted by a previous odd prefix sum.
                count = (count + odd) % M; 
                even++; // Increment count of even prefix sums
            } else { 
                // If the prefix sum is odd, it can form an odd sum subarray 
                // by itself and also with all previous even prefix sums.
                count = (count + even) % M; 
                odd++; // Increment count of odd prefix sums
            }
        }
        return count;
    }
}
