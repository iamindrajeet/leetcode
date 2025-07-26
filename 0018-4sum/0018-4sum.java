/**
Approach 1 : Using 3 for loops and a set to keep track of fourth number

✅ Time Complexity
First loop → O(n)

Second loop → O(n)

Third loop → O(n)

HashSet lookup → O(1)

So total O(n³).


✅ Space Complexity
set stores up to O(n) elements per (i, j) iteration

quartet stores O(#quadruplets)

Sorting each list is O(1) since size is fixed (4)

So O(n + #quadruplets)
*/
// class Solution {
//     public List<List<Integer>> fourSum(int[] nums, int target) {
//         // ✅ Set to store unique quadruplets (avoids duplicates automatically)
//         Set<List<Integer>> quartet = new HashSet<>();
        
//         // ✅ First loop: fix the first number nums[i]
//         for (int i = 0; i < nums.length; i++) {
            
//             // ✅ Second loop: fix the second number nums[j]
//             for (int j = i + 1; j < nums.length; j++) {
                
//                 // ✅ A HashSet to track seen numbers for the current i, j
//                 Set<Integer> set = new HashSet<>();
                
//                 // ✅ Third loop: iterate over remaining elements
//                 for (int k = j + 1; k < nums.length; k++) {
                    
//                     // ✅ Calculate the required 4th number
//                     int fourthNum = target - (nums[i] + nums[j] + nums[k]);
                    
//                     // ✅ If we already saw the required 4th number, we found a valid quadruplet
//                     if (set.contains(fourthNum)) {
//                         // ✅ Create a quadruplet
//                         List<Integer> list = Arrays.asList(nums[i], nums[j], nums[k], fourthNum);
                        
//                         // ✅ Sort the quadruplet so [1,0,-1,2] and [2,1,-1,0] are treated the same
//                         Collections.sort(list);
                        
//                         // ✅ Add the sorted quadruplet to the set
//                         quartet.add(list);
//                     }
                    
//                     // ✅ Add the current number nums[k] to the seen set for future lookups
//                     set.add(nums[k]);
//                 }
//             }
//         }
        
//         // ✅ Convert the unique quadruplets set into a list
//         return new ArrayList<>(quartet);
//     }
// }



/**
Approach 2 : Best Appraoch (Sorting array and 2 pointer approach)

✅ Time Complexity
Sorting → O(n log n)

Two nested loops (i, j) → O(n²)

Two-pointer inside → O(n)

➡ Total: O(n³)

✅ Space Complexity

result holds O(#quadruplets)

➡ Total: O(#quadruplets)

*/
class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();

        // ✅ Sorting array
        Arrays.sort(nums);

        int n = nums.length;

        // ✅ Outer loop for first number
        for (int i = 0; i < n; i++) {
            // ✅ Skip duplicate i values
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            // ✅ Second loop for second number
            for (int j = i + 1; j < n; j++) {
                // ✅ Skip duplicate j values
                if (j > i + 1 && nums[j] == nums[j - 1]) continue;

                int left = j + 1;
                int right = n - 1;

                // ✅ Two-pointer search for remaining two numbers
                while (left < right) {
                    long sum = (long) nums[i] + nums[j] + nums[left] + nums[right]; 

                    if (sum == target) {
                        result.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        left++;
                        right--;

                        // ✅ Skip duplicate left values
                        while (left < right && nums[left] == nums[left - 1]) left++;

                        // ✅ Skip duplicate right values
                        while (left < right && nums[right] == nums[right + 1]) right--;

                    } else if (sum < target) {
                        left++;  // need a bigger sum
                    } else {
                        right--; // need a smaller sum
                    }
                }
            }
        }

        return result;
    }
}