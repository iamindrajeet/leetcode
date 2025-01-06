/*
Intuition:
The problem is a classic "FizzBuzz" exercise where we iterate through numbers and replace specific multiples with corresponding strings. The key is to check conditions in order of priority:
1. Numbers divisible by both 3 and 5.
2. Numbers divisible by 3.
3. Numbers divisible by 5.
4. Otherwise, represent the number as a string.

Approach:
1. Create an empty result list.
2. Iterate from 1 to ( n ):
    - If ( i ) is divisible by both 3 and 5, append "FizzBuzz".
    - Else if ( i ) is divisible by 3, append "Fizz".
    - Else if ( i ) is divisible by 5, append "Buzz".
    - Otherwise, append the string representation of ( i ).
3. Return the list.

Complexity:
Time Complexity: O(n), as we iterate from 1 to n.
Space Complexity: O(n), as we store the result in a list.

*/
class Solution {
    public List<String> fizzBuzz(int n) {
        List<String> result = new ArrayList<>();
        for(int i = 1; i <= n; i++){
            if(i % 3 == 0 && i % 5 == 0)
                result.add("FizzBuzz");
            else if(i % 3 == 0)
                result.add("Fizz");
            else if(i % 5 == 0)
                result.add("Buzz");
            else 
                result.add(String.valueOf(i));
        }
        return result;
    }
}