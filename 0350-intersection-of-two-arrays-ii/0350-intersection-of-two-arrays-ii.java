/*
Intuition
To find the intersection of two arrays where each element in the result must appear as many times as it shows in both arrays, we can take advantage of sorting and two-pointer techniques. Sorting helps in efficiently finding common elements by scanning through both arrays in a linear fashion. Using two pointers, one for each array, allows us to traverse both arrays and find common elements without having to repeatedly search through the arrays.

Approach
Sort Both Arrays: Sorting helps in comparing elements of both arrays efficiently. By sorting both arrays, we can then use a two-pointer approach to find common elements.

Initialize Pointers: Use two pointers i and j to traverse through nums1 and nums2 respectively. Also, use a pointer k to keep track of the position in nums1 where we store the result.

Traverse Both Arrays:

Compare the elements at the current positions of both pointers.
If the element in nums1 is less than the element in nums2, increment pointer i.
If the element in nums1 is greater than the element in nums2, increment pointer j.
If the elements are equal, it means we have found a common element. Store this element in nums1[k], increment i, j, and k.
Return the Result: The result is stored in the first k positions of nums1. Use Arrays.copyOfRange to return this part of the array.

Complexity
Time Complexity: Sorting both arrays takes (O(n log n + m log m)), where (n) is the length of nums1 and (m) is the length of nums2. The two-pointer traversal takes (O(n + m)). Thus, the overall time complexity is (O(n log n + m log m + n + m) = O(n log n + m log m)).
Space Complexity: The space complexity is (O(1)) if we ignore the space used for sorting, as we are not using any extra space apart from the input arrays.

*/

// class Solution {
//     public int[] intersect(int[] nums1, int[] nums2) {
//         Arrays.sort(nums1);
//         Arrays.sort(nums2);

//         int i = 0, j = 0, k = 0;
//         List<Integer> intersection = new ArrayList<>();
//         while(i < nums1.length && j < nums2.length){
//             if(nums1[i] < nums2[j])
//                 i++;
//             else if(nums1[i] > nums2[j])
//                 j++;
//             else{
//                 nums1[k++] = nums1[i++];
//                 j++;
//             }
//         }
//         return Arrays.copyOfRange(nums1, 0 , k);
//     }
// }



/*
Approach 2 : Using an extra count array and not using sorting

Intuition
The problem requires finding the intersection of two arrays such that each element in the result must appear as many times as it shows in both arrays. The given solution utilizes the frequency count of elements to achieve this. By counting the occurrences of elements in the first array and then using this count to check against elements in the second array, the solution efficiently finds the intersection.

Approach
Count Frequencies: Use an integer array arr to count the occurrences of each number in nums1.
Traverse nums1 and for each element, increment its count in arr.
Traverse nums2 and for each element, check if it exists in arr with a count greater than zero.
If it does, it means we have found a common element. Store this element in nums1[k],
Return Result: Use Arrays.copyOfRange to return only the filled portion of result array.

Complexity
Time Complexity
( O(n + m) )

Counting frequencies of elements in nums1 takes ( O(n) ) time, where n is the length of nums1.
Finding intersection by traversing nums2 takes ( O(m) ) time, where m is the length of nums2.

Space Complexity
( O(1) ) (excluding the input and output arrays)

The arr array has a fixed size of 1001, which does not depend on the input size.
The result array is of size 1001 as well, which is also constant space.

*/

class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        int[] count = new int[1001];  // Frequency count array for nums1 elements

        // Populate the frequency count array with nums1 elements
        for (int num : nums1) {
            count[num]++;
        }

        int k = 0;
        // Check nums2 elements against the frequency count array
        for (int num : nums2) {
            if (count[num] > 0) {
                nums1[k++] = num;
                count[num]--;
            }
        }

        return Arrays.copyOfRange(nums1, 0, k);
    }
}