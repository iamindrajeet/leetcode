/*
Approach
1. We initialize two variables, prefixProduct and suffixProduct, to 1.
2. We create an array result to store our results.
3. We traverse the array Left to Right, each result[i] gets the current product on its left, and then we update prefixProduct by multiplying nums[i].
4. We traverse the array Right to Left, and multiply each result[i] with the current product on its right and after the multiplication, we update suffixProduct to include nums[i].

Complexity
Time complexity: O(n)
Space complexity: O(n)

*/

class Solution {
    public int[] productExceptSelf(int[] nums) {
        // Result array to store the product of all elements except self
        int[] result = new int[nums.length];
        
        // Prefix product variable to store the product of all elements to the left
        int prefixProduct = 1;
        // Suffix product variable to store the product of all elements to the right
        int suffixProduct = 1;

        // Calculate the prefix product for each element
        for (int i = 0; i < nums.length; i++) {
            result[i] = prefixProduct;
            prefixProduct *= nums[i];
        }

        // Calculate the suffix product for each element and multiply it with the prefix product
        for (int i = nums.length - 1; i >= 0; i--) {
            result[i] *= suffixProduct;
            suffixProduct *= nums[i];
        }

        return result;
    }
}

/*
T.C - O(n)
S.C - O(n)
*/
// class Solution {
//     public int[] productExceptSelf(int[] nums) {
//         int[] prefixArray = new int[nums.length];
//         int[] suffixArray = new int[nums.length];
//         int[] result = new int[nums.length];

//         int prefix = 1, suffix = 1;

//         // O(n)
//         // traversing in forward direction
//         for(int i = 0; i < nums.length; i++){
//             prefixArray[i] = prefix;
//             prefix *= nums[i];
//         }

//         // O(n)
//         // traversing in backward direction
//         for(int i = nums.length - 1; i >= 0; i--){
//             suffixArray[i] = suffix;
//             suffix *= nums[i];
//         }

//          // O(n)
//         for(int i = 0; i < nums.length; i++){
//             result[i] = prefixArray[i] * suffixArray[i];
//         }

//         return result;

//     }
// }