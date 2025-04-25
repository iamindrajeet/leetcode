class Solution {
    public int singleNumber(int[] nums) {
        int singleElement = 0;
        for(int num : nums) {
            singleElement = singleElement ^ num;
        }
        return singleElement;
    }
}