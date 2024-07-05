/*
Approach and Explantation
The function takes in two arrays: gas and cost, where gas[i] represents the amount of gas available at the i-th gas station, and cost[i] represents the amount of gas needed to travel from the i-th gas station to the next one.

The function first calculates the total amount of gas available at all the gas stations (totalGas) and the total amount of gas needed to travel around all the gas stations (totalCost). If totalGas is less than totalCost, it means that it is not possible to travel around all the gas stations, so the function returns -1.

Next, the function starts at the first gas station (start=0) and iterates through the gas stations. At each iteration, it calculates the remaining gas after visiting the current gas station (remainsGas) and adding the gas available at that station (gas[i]) and subtracting the gas needed to travel to the next gas station (cost[i]). If remainsGas becomes negative at any point, it means that it is not possible to travel from the current gas station to the next one without running out of gas. In this case, the function sets the starting gas station to the next one (start=i+1) and resets the remaining gas to 0 (remainsGas=0).

Finally, the function returns the starting gas station that allows the travel around all the gas stations.

Complexity
Time complexity:
o(n)

Space complexity:
O(1)

*/

class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        // Variables to store total gas and total cost
        int totalGas = 0, totalCost = 0;

        // Calculate the total gas and total cost
        for (int i = 0; i < gas.length; i++) {
            totalGas += gas[i];
            totalCost += cost[i];
        }

        // If the total gas available is less than the total cost, return -1
        // This means it's impossible to complete the circuit
        if (totalGas < totalCost)
            return -1;

        // Initialize starting point and remaining gas
        int start = 0, remainGas = 0;

        // Iterate through the gas stations
        for (int i = 0; i < gas.length; i++) {
            // Calculate the remaining gas after reaching the next station
            remainGas += gas[i] - cost[i];

            // If remaining gas is negative, it means we can't reach the next station from the current start
            // Update start to the next station and reset remaining gas to 0
            if (remainGas < 0) {
                start = i + 1;
                remainGas = 0;
            }
        }

        // Return the starting gas station index from where the circuit can be completed
        return start;
    }
}
