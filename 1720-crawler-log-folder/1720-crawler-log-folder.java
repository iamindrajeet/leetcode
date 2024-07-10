/*
Approach 1: Depth Counting

Intuition
So, all you need - count how far from main you are now.

If you find any path element except ../ and ./ then you know for sure that you go in next directory and increased your "distance" from main on 1 (you need now one for step to return to main, because now you need to return to parent of this directory first)
If you find ../ and you are now not in main (so distance counter is bigger than 0) you know that this will reduce number of steps to return to main on 1, because initially you would go to the parent of this directory but now you was moved there with log and so you don't need to do this additional step.

Coding
Initialize counter depth to keep track of the current depth.
Iterate through each log entry in the logs list:
If the log is ../, check if res is not zero and subtract 1 from it
If the log isn't ../ or ./ then add 1 do res since we found new path element.
Return depth which represents current depth

Complexity Analysis
Time complexity: O(n), since we iterate through whole array logs
Space complexity: O(1), since no extra space is used

*/

// class Solution {
//     public int minOperations(String[] logs) {
//         int depth = 0;
//         for(String log : logs){
//             if(log.equals("../"))
//                 depth = Math.max(0, --depth);
//             else if(log.equals("./"))
//                 continue;
//             else
//                 depth++;
//         }
//         return depth;
//     }
// }


/*
Approach 2: Real Simulation With Path Saving(Using Stack)
Intuition
There's no need in this approach if you solve specifically this problem, but what if you were asked to return actual path where user stopped?

We start in main folder, so initially our path stack is empty. We want to use stack because as soon as we find ../ we want to pop the last path we added to this stack
We iterate through logs and every time we face ../ we pop last element from stack (only if it's not empty, which tell us that we are already in main)
Every time we face not ../ and not ./ we add log to the stack as new path element.
At the end we want to return len of the stack because it's number of steps we need to return to main (pop every element from path with ../)

Coding
Initialize an empty list paths_stack to keep track of the current directory path.
Iterate through each log entry in the logs list:
If the log is ../, check if paths_stack is not empty and pop last element
If the log isn't ../ or ./ then add new path element to paths_stack
Return length of the path_stack after perfoming all operations

Complexity Analysis
Time complexity: O(n), since we iterate through whole array logs
Space complexity: O(n), since we use paths_stack which can be of size n in worst case

*/
class Solution {
    public int minOperations(String[] logs) {
        Stack<String> pathStack = new Stack<>();
        for(String log : logs){
            if(log.equals("../")){
               if(!pathStack.isEmpty())
                    pathStack.pop();
            }
            else if(log.equals("./"))
                continue;
            else
                pathStack.push(log);
        }
        return pathStack.size();
    }
}