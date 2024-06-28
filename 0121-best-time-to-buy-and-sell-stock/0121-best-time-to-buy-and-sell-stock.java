/*

Approach:
1. Initialize variables buyPrice with the first element of the prices array and maxProfit as 0.
2. Iterate through the prices.
3. Update the buy variable if the current price is lower than the current buying price.
4. Update the maxProfit if the difference between the current price and the buying price is greater than the current profit.
5. Return the final maxProfit.
**/

class Solution {
    public int maxProfit(int[] prices) {
        int maxProfit = 0;
        int buyPrice = prices[0];
        for(int price : prices){
            buyPrice = Math.min(buyPrice, price);
            int currProfit = price - buyPrice;
            maxProfit = Math.max(maxProfit, currProfit);
        }
        return maxProfit;
    }
}