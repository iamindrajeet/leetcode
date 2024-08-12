/*
Approach 1 : Brute Force (Using 2 for loops)
T.C : O(n^2)
S.C : O(1)
*/
// class Solution {
//     public int maxProduct(int[] nums) {
//         int maxProduct = Integer.MIN_VALUE;
//         for(int i = 0; i < nums.length; i++){
//             int product = 1;
//             for(int j = i; j < nums.length; j++){
//                 product = product * nums[j];
//                 maxProduct = Math.max(product, maxProduct);
//             }
//         }
//         return maxProduct;
//     }
// }\

/*
Approach 2 : Simple Observation

1. Intuition and Logic:
- The problem is to find the maximum product of a contiguous subarray within a given array nums.
- The challenge comes from handling negative numbers and zeros. A negative number can turn a large product into a small one, or it can turn a small negative product into a large positive one.
- The idea is to traverse the array from both the beginning (prefix) and the end (suffix). This approach allows us to handle the effect of negative numbers more effectively:
    - Prefix Product: Product of the subarray starting from the beginning of the array.
    - Suffix Product: Product of the subarray starting from the end of the array.
- Reset the product to 1 whenever it becomes zero, as multiplying further with zero would yield zero, and we don't want that.
- Compare the maximum of prefix and suffix products at each step to track the maximum product subarray.

2. Time Complexity (TC):
The time complexity of this approach is O(n), where n is the number of elements in the array. This is because we are traversing the array only once.
3. Space Complexity (SC):
The space complexity is O(1), as we are using only a few extra variables (maxProduct, prefixProduct, suffixProduct) and not any extra space that scales with the input size.

*/

class Solution {
    public int maxProduct(int[] nums) {
        // Initialize maxProduct with the first element of the array
        // This is because the maximum product subarray could be a single element
        double maxProduct = nums[0];
        
        // Initialize prefix and suffix products to 1
        double prefixProduct = 1, suffixProduct = 1;
        
        // Traverse the array to calculate the maximum product subarray
        for(int i = 0; i < nums.length; i++) {
            // If the prefix product becomes zero, reset it to 1
            // This is because multiplying by zero would invalidate the current product,
            // so we reset and start calculating a new subarray product.
            if(prefixProduct == 0)
                prefixProduct = 1;
            
            // If the suffix product becomes zero, reset it to 1
            // Similar to prefix, we reset if the product becomes zero.
            if(suffixProduct == 0)
                suffixProduct = 1;
            
            // Calculate the prefix product (product of subarray starting from the beginning)
            // Multiply the current prefix product with the element at index i
            prefixProduct *= nums[i];
            
            // Calculate the suffix product (product of subarray starting from the end)
            // Multiply the current suffix product with the element from the end of the array
            suffixProduct *= nums[nums.length - 1 - i];
            
            // Update maxProduct by comparing it with the current prefix and suffix products
            // This ensures that we are tracking the largest possible product encountered so far
            maxProduct = Math.max(maxProduct, Math.max(prefixProduct, suffixProduct));
        }
        
        // Return the maximum product found as an int
        // Casting to int because the final answer should be an integer
        return (int) maxProduct;
    }
}
