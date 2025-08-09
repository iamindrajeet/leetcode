/**
\U0001f4ca Time Complexity
1. Sorting:
Arrays.sort(nums); // O(n log n)
Sorting n elements takes O(n log n) time.

2. Precomputing powers of 2:
for (int i = 1; i < n; i++) power[i] = ...
Runs in O(n) time.

3. Two-pointer loop:
while (left <= right) ...
In the worst case, each pointer moves at most n times.

So the loop is O(n).

✅ Total Time Complexity:
O(n log n)
(From sorting — this dominates the other O(n) steps)

\U0001f9e0 Space Complexity
1. Power array:
int[] power = new int[n];
Takes O(n) space.

2. Input array:
The array nums is given, not extra space.

✅ Total Space Complexity:
O(n)
(For storing powers of 2)
*/
class Solution {
    public int numSubseq(int[] nums, int target) {
        int mod = 1_000_000_007;  // To avoid integer overflow
        int n = nums.length;
        Arrays.sort(nums);        // Sort the array for two-pointer approach

        int left = 0, right = n - 1;
        int result = 0;

        int[] power = new int[n]; // Precompute powers of 2 up to n
        power[0] = 1;
        for (int i = 1; i < n; i++) {
            power[i] = (power[i - 1] * 2) % mod;
        }


        while (left <= right) {
            // If the smallest + largest is within target, all subsets between them are valid
            if (nums[left] + nums[right] <= target) {
                result = (result + power[right - left]) % mod;
                left++;
            } else {
                // If sum is too big, try a smaller max
                right--;
            }
        }

        return result;
    }
}
