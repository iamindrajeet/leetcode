// T.C - O(n)
// S.C - O(1)
class Solution {
    public int countSeniors(String[] details) {
        int count = 0;
        
        // Iterate over each detail string in the array
        for(String detail : details){
            // The age is located at the 12th and 13th characters in the string
            int startIndex = 11;
            int endIndex = startIndex + 2;
            
            // Extract the substring representing the age and convert it to an integer
            int age = Integer.parseInt(detail.substring(startIndex, endIndex));
            
            // If the age is above 60, increment the count
            if(age > 60) {
                count++;
            }
        }
        
        // Return the total count of seniors
        return count;
    }
}
