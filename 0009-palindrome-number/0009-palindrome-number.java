class Solution {
    public boolean isPalindrome(int x) {
        if(x < 0 || (x % 10 == 0 && x != 0))
            return false;
        
        int reversedNum = 0;
        int xCopy = x;
        while(xCopy != 0){
            int digit = xCopy % 10;
            reversedNum = (reversedNum * 10) + digit;
            xCopy /= 10;
        }
        return x == reversedNum;
    }
}