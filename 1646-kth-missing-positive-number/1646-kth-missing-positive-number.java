// class Solution {
//     public int findKthPositive(int[] arr, int k) {
//         // Loop through the elements of the array
//         for (int num : arr) {
//             // If the current number is less than or equal to k,
//             // it means this number is not missing, so the k-th missing number moves forward
//             if (num <= k) {
//                 k++; // we've "seen" one more positive integer, so we need to look one step further
//             } else {
//                 // If num > k, then k is truly missing from the array
//                 break;
//             }
//         }
//         // After processing the array, k will be the k-th missing positive number
//         return k;
//     }
// }

class Solution {
    public int findKthPositive(int[] arr, int k) {
        int left = 0, right = arr.length - 1;

        // Binary search to find how many numbers are missing at each index
        while (left <= right) {
            int mid = left + (right - left) / 2;

            // At index mid, number of missing elements = arr[mid] - (mid + 1)
            int missingNumbers = arr[mid] - (mid + 1);

            if (missingNumbers < k) {
                // Not enough missing numbers yet, go right
                left = mid + 1;
            } else {
                // Too many or just enough missing numbers, go left
                right = mid - 1;
            }
        }

        // At the end, 'left' will be the position where the kth missing number fits
        return left + k;
    }
}
