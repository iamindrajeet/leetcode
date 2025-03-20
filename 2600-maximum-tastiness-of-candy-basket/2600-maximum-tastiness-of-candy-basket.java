class Solution {
    public int maximumTastiness(int[] price, int k) {
        Arrays.sort(price);
        int low = 0, high = 1000_000_000;
        while(low <= high) {
            int mid = low + (high - low) / 2;
            if(check(mid, price, k))
                low = mid + 1;
            else 
                high = mid - 1;
        }
        return low - 1;
        
    }

    private boolean check(int x, int[] price, int k) {
        int lastPrice = price[0], i = 1, count = 1;
        while(count < k && i < price.length) {
            if(price[i] - lastPrice >= x) {
                lastPrice = price[i];
                count++;
            }
            i++;
        }
        return count == k;        
    }
}