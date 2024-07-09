// Simple Simulation
// T.C : O(n)
// S.C : O(1)
class Solution {
    public double averageWaitingTime(int[][] customers) {
        // Number of customers
        int n = customers.length;

        // To keep track of the total waiting time of all customers
        double totalWaitTime = 0;

        // Current time initialized to 0
        int currTime = 0;

        // Iterate through each customer
        for (int[] customer : customers) {
            // Extract arrival time and cooking time for the current customer
            int arrivalTime = customer[0];
            int cookTime = customer[1];

            // If the current time is less than the arrival time of the customer,
            // we need to wait until the customer arrives
            if (currTime < arrivalTime) {
                currTime = arrivalTime;
            }

            // Calculate the wait time for the current customer
            // Wait time = (current time + cooking time) - arrival time
            int waitTime = currTime + cookTime - arrivalTime;

            // Add the wait time to the total wait time
            totalWaitTime += waitTime;

            // Update the current time to the time after the current customer's food is cooked
            currTime += cookTime;
        }

        // Return the average waiting time
        return totalWaitTime / n;
    }
}
