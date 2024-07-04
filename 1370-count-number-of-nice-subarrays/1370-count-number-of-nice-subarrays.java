/*
Approach- (Using prefix sum and storing in hashmap)
T.C : O(n)
S.C : O(n)


Intuition
Since we only need to find the number of subarrays that contain a certain count of odd elements, we can ignore the numerical values of the elements and replace all odd values with 1 and even values with 0.

Now, all we need to do is identify sequences of elements within the array whose sum equals the number of odd elements needed to make a nice array. Solutions that require sequences of elements to meet criteria often utilize prefix sums, also sometimes referred to as cumulative sums.

Note: If you aren't aware of this concept we recommend you first solve this problem 560. Subarray Sum Equals K.

Utilizing prefix sums simplifies our approach and lets us avoid determining the sum of elements for every new subarray considered. Using the prefix sums approach, we can calculate the sum of elements between two indices, subtracting the prefix sum corresponding to the two indices to obtain the sum directly instead of iterating over the subarray to find the sum.

We'll use this approach to calculate how many odd numbers are between two indices in the array. Let's call the two indices start and end. If the number of odd numbers between start and end equals k, we have found a nice subarray. We will calculate this by finding the difference between the end and start indices.

Based on these thoughts, we use a hashmap to store the prefix sum of indices as keys and their frequency of occurrence as values. Instead of modifying nums, we can apply the modulo 2 operation when storing values in the hashmap.

We traverse the array nums to compute the prefix sum up to each element modulo 2. Each unique sum encountered is recorded in a hashmap. If a sum repeats, we increment its corresponding count in the hashmap. Also, for each sum encountered, we find the number of times sum - k has appeared before, as this count indicates how many subarrays with sum k exist up to the current index. We increase the count by that same amount.

Algorithm
1. Initialize integers currSum = 0,subarrays = 0 and a hashmap prefixSum.
2. Initialize prefixSum[0] with 1 to account for the initial value of currSum.
3. Iterate over all the elements of nums:
    Compute currSum as currSum = currSum + nums[i] % 2.
    If currSum - k exists in the hashmap:
    Increment the value of subarrays with prefixSum[currSum - k].
    Increment prefixSum[currSum] by 1.
4. Return subarrays.
*/

class Solution {
    public int numberOfSubarrays(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        int n = nums.length;
        int currSum = 0;
        int count = 0;
        map.put(currSum, 1);

        for(int i = 0; i < n; i++){
            currSum += (nums[i] % 2); // id odd - 1, even - 0
            if(map.containsKey(currSum - k))
                count += map.get(currSum - k);
            map.put(currSum, map.getOrDefault(currSum, 0) + 1);
        }
        return count;
    }
}