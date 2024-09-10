/*
- Time Complexity (TC):
    - Adding elements to a HashSet takes O(1) on average. Since we are adding all elements from nums1 and nums2 to sets:
    - Adding nums1 to num1Set takes O(n), where n is the length of nums1.
    - Adding nums2 to num2Set takes O(m), where m is the length of nums2.
    - For checking unique elements, iterating over num1Set and num2Set takes O(n) and O(m), respectively, since we check membership in the other 
      set, which is O(1) on average.
    Thus, the total time complexity is O(n + m).

- Space Complexity (SC):
    - num1Set and num2Set store the unique elements of nums1 and nums2, so they take O(n) and O(m) space, respectively.
    - num1List and num2List store the result of unique elements, so they will also take O(n) and O(m) space in the worst case.
    - The answer list holds two lists of size up to O(n + m).
    Thus, the total space complexity is O(n + m).
*/
class Solution {
    public List<List<Integer>> findDifference(int[] nums1, int[] nums2) {
        // Initialize sets to store unique elements from both arrays
        Set<Integer> num1Set = new HashSet<>();
        Set<Integer> num2Set = new HashSet<>();

        // Lists to store the result of unique elements in nums1 and nums2 respectively
        List<Integer> num1List = new ArrayList<>();
        List<Integer> num2List = new ArrayList<>();
        
        // Final result containing two lists
        List<List<Integer>> answer = new ArrayList<>();

        // Add all elements from nums1 into num1Set
        for (int num : nums1) {
            num1Set.add(num);  // This ensures that only unique elements from nums1 are added
        }

        // Add all elements from nums2 into num2Set
        for (int num : nums2) {
            num2Set.add(num);  // This ensures that only unique elements from nums2 are added
        }

        // Find all elements that are in num1Set but not in num2Set
        for (int num : num1Set) {
            if (!num2Set.contains(num)) {
                num1List.add(num);  // Only add elements that are unique to nums1
            }
        }

        // Find all elements that are in num2Set but not in num1Set
        for (int num : num2Set) {
            if (!num1Set.contains(num)) {
                num2List.add(num);  // Only add elements that are unique to nums2
            }
        }

        // Add both unique lists to the answer
        answer.add(num1List);
        answer.add(num2List);

        // Return the final result
        return answer;
    }
}
