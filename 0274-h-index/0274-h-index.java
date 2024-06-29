/*
Intuition
Simply putting in words, we need to find the maximum value(h) such that there are 'h" research papers that are cited "h" or more times

Approach
One thing is sure is that the max value of h is the length of citations array. Once you sort the array, if citations[i] is less than h, we decrement h. That is all we need to do through the whole array. At the end, we return h.

Complexity
Time complexity: O(nlogn); n is length of array
Space complexity: O(1)


*/

class Solution {
    public int hIndex(int[] citations) {
        Arrays.sort(citations);
        int hIndex = citations.length;

        for(int citation : citations){
            if(citation < hIndex)
                hIndex--;
        }
        return hIndex;
    }
}