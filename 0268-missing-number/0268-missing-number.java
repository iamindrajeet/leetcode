class Solution {
    public int missingNumber(int[] nums) {
        // xor1 will store XOR of all elements in the array
        int xor1 = 0;
        // xor2 will store XOR of all numbers from 1 to n
        int xor2 = 0;

        // Traverse the array
        for (int i = 0; i < nums.length; i++) {
            // XOR all elements of the array
            xor1 = xor1 ^ nums[i];
            // XOR all numbers from 1 to n (where n is nums.length)
            xor2 = xor2 ^ (i + 1);
        }

        // Final result is xor1 ^ xor2
        // All numbers except the missing one will cancel each other
        // The remaining value will be the missing number
        return xor1 ^ xor2;
    }
}
