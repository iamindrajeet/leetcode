/*
1.Initialize two pointers, left and right, at the beginning and end of the array.
2.Calculate the area between the two lines, which is the product of the distance between the lines and the height of the shorter line. Update the maxArea if the calculated area is greater than the current maxArea.
3.Move the pointer pointing to the shorter line towards the other pointer, in hope of finding a taller line and thus increasing the area.
4.Repeat steps 2-4 until the two pointers meet.

**/

class Solution {
    public int maxArea(int[] height) {
        int maxWater = Integer.MIN_VALUE;
        int left = 0, right = height.length - 1;
        while(left <= right){
            int area = Math.min(height[left], height[right]) * (right - left);
            maxWater = Math.max(maxWater, area);
            if(height[left] > height[right])
                right--;
            else 
                left++;
        }
        return maxWater;
    }
}