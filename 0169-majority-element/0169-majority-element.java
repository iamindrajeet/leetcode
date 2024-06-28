/*

Approach : Moore Voting Algorithm
Intuition:
The intuition behind the Moore's Voting Algorithm is based on the fact that if there is a majority element in an array, it will always remain in the lead, even after encountering other elements.

Explanation:
Algorithm:

Initialize two variables: count and majorityEle. Set count to 0 and majorityEle to an arbitrary value.
Iterate through the array nums:
a. If count is 0, assign the current element as the new majorityEle and increment count by 1.
b. If the current element is the same as the majorityEle, increment count by 1.
c. If the current element is different from the majorityEle, decrement count by 1.
After the iteration, the majorityEle variable will hold the majority element.

Explanation:

The algorithm starts by assuming the first element as the majority majorityEle and sets the count to 1.
As it iterates through the array, it compares each element with the majorityEle:
a. If the current element matches the majorityEle, it suggests that it reinforces the majority element because it appears again. Therefore, the count is incremented by 1.
b. If the current element is different from the majorityEle, it suggests that there might be an equal number of occurrences of the majority element and other elements. Therefore, the count is decremented by 1.
Note that decrementing the count doesn't change the fact that the majority element occurs more than n/2 times.
If the count becomes 0, it means that the current majorityEle is no longer a potential majority element. In this case, a new majorityEle is chosen from the remaining elements.
The algorithm continues this process until it has traversed the entire array.
The final value of the majorityEle variable will hold the majority element.

*/
class Solution {
    public int majorityElement(int[] nums) {
        int count = 0, majorityEle = 0;
        for(int num : nums){
            if(count == 0)
                majorityEle = num;
            if(num == majorityEle)
                count++;
            else
                count--;
        }
        return majorityEle;
    }
}