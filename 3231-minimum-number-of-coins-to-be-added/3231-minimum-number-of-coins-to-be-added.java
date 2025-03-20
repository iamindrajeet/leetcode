class Solution {
    public int minimumAddedCoins(int[] coins, int target) {
        Arrays.sort(coins);      
        int currentMax = 0;
        int i = 0;
        int a = 0;
    while(currentMax < target) {
	    if(i < coins.length && coins[i] <= currentMax + 1)
	    {
		    currentMax += coins[i];
            i++;
        } else {
	        currentMax += currentMax + 1;
	        a++;
        }
    }
    return a;
    }
}