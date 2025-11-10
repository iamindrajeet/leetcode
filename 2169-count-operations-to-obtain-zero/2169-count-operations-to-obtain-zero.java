class Solution {
    public int countOperations(int num1, int num2) {
        int noOfOps = 0; // Counter to track number of operations performed
        
        // Continue until one of the numbers becomes 0
        while (num1 != 0 && num2 != 0) {
            
            // If num1 is greater, subtract num2 from num1
            if (num1 > num2)
                num1 = num1 - num2;
            else // Otherwise, subtract num1 from num2
                num2 = num2 - num1;
            
            noOfOps++; // Increment operation count after each subtraction
        }
        return noOfOps;
    }
}
