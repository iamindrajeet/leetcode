/**
Approach 1: Enumeration
Intuition
Enumerate all numbers from low to high:

1. If it is a two-digit number and is a multiple of 11, then it is a symmetric integer.
2. If it is a four-digit number, calculate the sum of the thousands and hundreds digits, as well as the sum of the tens and ones digits. If they are equal, it is a symmetric (even) integer.
Finally, it returns the number of symmetric integers in the range.

Complexity Analysis
Time complexity: O(high−low).
We enumerate all numbers from low to high and check whether they are symmetric integers in O(1) each time.

Space complexity: O(1).
Only a few additional variables are needed.
*/
class Solution {
    public int countSymmetricIntegers(int low, int high) {
        int res = 0;
        for(int a = low; a <= high; a++) {
            if(a < 100 && a % 11 == 0)
                res++;
            else if(a >= 1000 && a < 10000) {
                int left = a / 1000 + (a % 1000) / 100; 
                int right = (a % 100) / 10 + (a % 10);
                if(left == right)
                    res++;
            }
        }
        return res;
    }
}