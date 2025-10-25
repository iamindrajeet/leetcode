/**
Approach 1 : Brute Force
Time Complexity = O(n * k)
Space Complexity = O(1)
*/
// class Solution {
//     public boolean hasIncreasingSubarrays(List<Integer> nums, int k) {
//         // Iterate up to nums.size() - 2*k because we need TWO subarrays of size k
//         for (int idx = 0; idx <= nums.size() - 2 * k; idx++) {
            
//             // Check first subarray: nums[idx ... idx + k - 1]
//             boolean isSubArray1Increasing = isIncreasing(nums, idx, idx + k - 1);
            
//             // Check second subarray: nums[idx + k ... idx + 2*k - 1]
//             boolean isSubArray2Increasing = isIncreasing(nums, idx + k, idx + 2 * k - 1);
            
//             // If both subarrays are strictly increasing, we found our answer
//             if (isSubArray1Increasing && isSubArray2Increasing)
//                 return true;
//         }
//         return false;  // No such pair found
//     }
//     private boolean isIncreasing(List<Integer> nums, int start, int end) {
//         // Compare each element with its next one
//         for (int i = start; i < end; i++) {
//             if (nums.get(i + 1) <= nums.get(i))  // Not strictly increasing
//                 return false;
//         }
//         return true;
//     }
// }

/**
 * Approach 2: Optimal Sliding Approach
 *
 * ✅ Intuition:
 * We want to check if there exist two *consecutive* subarrays of length `k`
 * that are both strictly increasing.
 *
 * Instead of checking each subarray separately (which would be O(n*k)),
 * we can track *runs* of consecutive increasing numbers in O(n) time.
 *
 * A "run" means a continuous segment where each element is greater than the previous one.
 *
 * ✅ Algorithm:
 * - Iterate through the array once.
 * - Maintain two counters:
 *      1️⃣ currRun → length of the current increasing sequence
 *      2️⃣ prevRun → length of the previous increasing sequence before a drop
 *
 * - If current number > previous number → extend currRun
 * - Else → sequence breaks, so update prevRun = currRun, and reset currRun = 1
 *
 * - At each step, check two conditions:
 *      1️⃣ If currRun / 2 >= k → means current run itself contains two consecutive increasing subarrays of size k
 *      2️⃣ If min(currRun, prevRun) >= k → means we have two consecutive increasing runs of at least size k each
 *
 * ✅ Time Complexity: O(n)
 * ✅ Space Complexity: O(1)
 */
class Solution {
    public boolean hasIncreasingSubarrays(List<Integer> nums, int k) {
        int currRun = 1;  // Length of current increasing sequence
        int prevRun = 0;  // Length of previous increasing sequence

        for (int i = 1; i < nums.size(); i++) {
            // If current element continues the increasing sequence
            if (nums.get(i) > nums.get(i - 1))
                currRun++;
            else {
                // Sequence broke — move current run to prevRun
                prevRun = currRun;
                currRun = 1; // reset for new sequence
            }

            // Case 1: One long run can itself contain two increasing subarrays of size k
            if (currRun / 2 >= k)
                return true;

            // Case 2: Two consecutive increasing runs of at least size k each
            if (Math.min(currRun, prevRun) >= k)
                return true;
        }

        return false;
    }
}
