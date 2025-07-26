/**
Approach 1 : Using hashmap to store the frequency
T.C - O(N)
S.C - O(N)
*/
// class Solution {
//     public List<Integer> majorityElement(int[] nums) {
//         // Map to store frequency of each element
//         Map<Integer, Integer> map = new HashMap<>();
//         // Result list to store elements appearing more than n/3 times
//         List<Integer> result = new ArrayList<>();
//         int len = nums.length;

//         // Any element must appear at least (n/3) + 1 times to be a valid candidate
//         int freq = (len / 3) + 1;

//         // Single-pass iteration through the array
//         for (int i = 0; i < len; i++) {
//             // Update the frequency of nums[i]
//             int count = map.getOrDefault(nums[i], 0) + 1;
//             map.put(nums[i], count);

//             // If the frequency just reached the required threshold,
//             // add it to the result (this avoids duplicate additions)
//             if (count == freq) {
//                 result.add(nums[i]);
//             }
//         }

//         return result;
//     }
// }

/**
Approach 2 : Modified Moore Voting Algorithm(It is an algorithm to find majority element whose frequency is more than N/2 times in an array)
T.C - O(N)
S.C - O(1)
*/
class Solution {
    public List<Integer> majorityElement(int[] nums) {
        // Two potential majority candidates (since at most 2 elements can appear > n/3 times)
        int element1 = Integer.MIN_VALUE, element2 = Integer.MIN_VALUE;
        int count1 = 0, count2 = 0;

        // ✅ Phase 1: Find potential candidates
        for (int num : nums) {
            if (num == element1) {
                count1++; // Increment count for first candidate
            } else if (num == element2) {
                count2++; // Increment count for second candidate
            } else if (count1 == 0) {
                element1 = num; // Assign new candidate1
                count1 = 1;
            } else if (count2 == 0) {
                element2 = num; // Assign new candidate2
                count2 = 1;
            } else {
                // If current number is different from both candidates,
                // decrease both counts (like cancelling out)
                count1--;
                count2--;
            }
        }

        // ✅ Phase 2: Verify actual counts of the candidates
        count1 = 0;
        count2 = 0;
        for (int num : nums) {
            if (num == element1) count1++;
            else if (num == element2) count2++;
        }

        // ✅ Phase 3: Check which candidates exceed threshold
        List<Integer> result = new ArrayList<>();
        int threshold = nums.length / 3;  // must appear > ⌊n/3⌋ times

        if (count1 > threshold) result.add(element1);
        if (count2 > threshold) result.add(element2);

        return result;
    }
}
