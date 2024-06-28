class Solution {
    public int maxProfit(int[] prices) {
        // Set the initial low price to the first element in the prices array.
        int buyPrice = prices[0];
        // Initialize profit variable to keep track of earnings.
        int maxProfit = 0;
        for(int price : prices){
            // If the current price is lower than the buyPrice, update the buyPrice.
            if(price < buyPrice){
                buyPrice = price;
            } else{
                int currPrice = price - buyPrice;
                // Add the profit from the trade to the total profit.
                maxProfit += currPrice;
                // Update the buyPrice to the current price, as we can buy and sell in the same time.
                buyPrice = price;
            }
        }
        return maxProfit;
    }
}