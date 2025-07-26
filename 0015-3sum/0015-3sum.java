/**

Approach 1 : Using Set Data Structure and 2 for loops

✅ Time Complexity
Outer loop → O(n)

Inner loop → O(n)

Sorting each found triplet → O(1) (fixed size 3)

HashSet lookups → O(1) average

Total: O(n²)

✅ Space Complexity
SC - O(N) + O(#triplets)

O(n) for the seen HashSet (at most n-1 elements)

O(#triplets) for the final result storage
*/
// class Solution {
//     public List<List<Integer>> threeSum(int[] nums) {
//         // ✅ A set to store unique triplets (avoids duplicates automatically)
//         Set<List<Integer>> triplets = new HashSet<>();
        
//         // ✅ Outer loop: Fix one element nums[i]
//         for (int i = 0; i < nums.length; i++) {
            
//             // ✅ Inner helper set to track elements seen for the current i
//             Set<Integer> set = new HashSet<>();
            
//             // ✅ Inner loop: Iterate over the remaining elements after nums[i]
//             for (int j = i + 1; j < nums.length; j++) {
                
//                 // ✅ Calculate the required third element to make sum = 0
//                 int thirdElement = -(nums[i] + nums[j]);
                
//                 // ✅ If we have already seen the required thirdElement earlier in this inner loop
//                 // it means we found a valid triplet: nums[i], nums[j], thirdElement
//                 if (set.contains(thirdElement)) {
//                     // ✅ Create a triplet
//                     List<Integer> list = Arrays.asList(nums[i], nums[j], thirdElement);
                    
//                     // ✅ Sort the triplet so [1, -1, 0] and [-1, 1, 0] are treated the same
//                     Collections.sort(list);
                    
//                     // ✅ Add the sorted triplet to the main set (ensures uniqueness)
//                     triplets.add(list);
//                 }
                
//                 // ✅ Add the current number nums[j] to the seen set for future lookups
//                 set.add(nums[j]);
//             }
//         }
        
//         // ✅ Convert the set of unique triplets into a list for the final answer
//         return new ArrayList<>(triplets);
//     }
// }


/**
Approach 2 : Best Solution(Sorting and 2 pointer approach)
T.C - O(N log N) + O(N * N)
S.C - O(N) - If we consider the list we are using for returing the answer otherwise its O(1)
*/
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();

        // ✅ Sort array to use two-pointer technique & easily skip duplicates
        Arrays.sort(nums);

        // ✅ Outer loop: fix one element nums[i]
        for (int i = 0; i < nums.length; i++) { // O(N)

            // ✅ Skip duplicate elements for i (to avoid duplicate triplets)
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            int left = i + 1;             // Start pointer after i
            int right = nums.length - 1;  // End pointer at last index

            // ✅ Two-pointer approach to find pairs that sum to -nums[i]
            while (left < right) { // O(N)
                int sum = nums[i] + nums[left] + nums[right];

                if (sum == 0) {
                    // ✅ Found a valid triplet
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));

                    // ✅ Move both pointers inward
                    left++;
                    right--;

                    // ✅ Skip duplicate left values
                    while (left < right && nums[left] == nums[left - 1]) left++;

                    // ✅ Skip duplicate right values
                    while (left < right && nums[right] == nums[right + 1]) right--;

                } else if (sum < 0) {
                    // ✅ If sum too small, move left pointer to increase sum
                    left++;
                } else {
                    // ✅ If sum too large, move right pointer to decrease sum
                    right--;
                }
            }
        }

        return result;
    }
}
