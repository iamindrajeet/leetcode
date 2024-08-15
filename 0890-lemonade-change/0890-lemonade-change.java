/*
Approach: Simulation

1. Intuition
Customers can pay in three ways:

    1. 5-dollar bill: Since each lemonade costs 5 dollars, no change is necessary. We simply add the 5-dollar bill to our collection.

    2. 10-dollar bill: We need to provide 5 dollars in change. If we have a 5-dollar bill available, we give it to the customer and add the 
       10-dollar bill to our collection. If we lack a 5-dollar bill, the transaction fails and we can return false.

    3. 20-dollar bill: We must provide 15 dollars in change. We can do this in two ways:
    - Give one 10-dollar bill and one 5-dollar bill.
    - Give three 5-dollar bills.

To solve this problem, we'll iterate through the bills array and keep track of the available change we have at any given turn. This means tracking the number of 5-dollar and 10-dollar bills. Interestingly, we won't need to track the 20-dollar bills since they aren't needed to make change.

Since the 5-dollar bill is required for both the 10-dollar and 20-dollar transactions and the 10-dollar bill can only be used in the 20-dollar transactions, we want to prioritize using the 10-dollar bill when possible.

The solution to this problem involves making a series of individual decisions to optimize the final outcome. We don't need to revisit past choices, and by conserving critical resources (like 5-dollar bills), we increase the chances of completing all transactions. This straightforward, resource-conserving approach aligns perfectly with the principles of a greedy algorithm.

Algorithm
1. Initialize two variables, fiveDollarBills and tenDollarBills, to keep track of the count of 5-dollar and 10-dollar bills, respectively.
2. Iterate through each bill customerBill in the bills array:
    - If customerBill is 5, increment fiveDollarBills.
    - If customerBill is 10:
        - Check if there is at least one fiveDollarBills:
            - If there is, decrement fiveDollarBills by 1 and increment tenDollarBills by 1.
            - Otherwise, return false.

3. If customerBill is 20:
    - Check if there are at least one fiveDollarBills and one tenDollarBills:
        - If there are, decrement fiveDollarBills and tenDollarBills by 1.
    - Else, check if there are at least three fiveDollarBills available:
        - If so, decrement fiveDollarBills by 3.
    - If neither conditions are met, return false.
4. Return true as our answer.

Complexity Analysis
Let n be the length of the bills array.

1. Time complexity: O(n)
The algorithm loops over the length of bills once, taking O(n) time. All operations within the loop are constant time operations.
Thus, the time complexity of the algorithm is O(n).

2. Space complexity: O(1)
The algorithm does not use any additional data structures that scale with the input size. Thus, the space complexity remains constant.

*/
class Solution {
    public boolean lemonadeChange(int[] bills) {
        // Count of $5 and $10 bills in hand initially
        int fiveDollarBills = 0, tenDollarBills = 0;
        // Iterate through each customer's bill
        for (int bill : bills) {
            if (bill == 5)
                // Increment the $5 bill count by 1
                fiveDollarBills++;
            else if (bill == 10) {
                // We need to give $5 change
                if (fiveDollarBills > 0) {
                    fiveDollarBills--;
                    tenDollarBills++;
                } else {
                    // Can't provide change so return false
                    return false;
                }
            } else { // Customer with $20 bill
                     // We need to give $15 change
                if (tenDollarBills > 0 && fiveDollarBills > 0) {
                    // Give change as one $10 and one $5
                    fiveDollarBills--;
                    tenDollarBills--;
                }else if(fiveDollarBills >= 3){
                    // Give change as three $5
                    fiveDollarBills -= 3;
                }else{
                    // Can't provide change, return false
                    return false;
                }
            }
        }
        // If we've made it through all customers, return true
        return true; 
    }
}