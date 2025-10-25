class Solution {
    public int totalMoney(int n) {
        int total = 0, monday = 1;
        while(n > 0) {
            for(int days = 0; days < Math.min(n, 7); days++) {
                total += days + monday;
            }
            n = n - 7;
            monday++;
        }
        return total;
    }
}