/*
Approach: Sorting
Intuition
We have N cities (nodes) numbered 0 to N-1, connected by bidirectional roads (edges). Our task is to assign unique values from 1 to N to each node, maximizing the total importance of all edges.

Edge importance is defined as the sum of the values of the nodes it connects.

Key observation: A node's value contributes to importance once for each connected edge. This means that nodes with more connections (higher degree) should be assigned higher values.

We can solve the problem in three steps:

Calculate the degree of each node (number of connected edges).
Sort nodes by degree in ascending order.
Assign values 1 to N to nodes, starting with the lowest degree.



Complexity Analysis
Here, N is the number of nodes in the graph.

Time complexity: O(N^2).

We iterate over the edges list roads to find the degree of each node. In the worst case, the number of edges in the graph could reach N^2, assuming an edge exists between every pair of nodes. Assigning degrees thus requires O(N^2) operations.
Next, sorting the degrees in ascending order takes O(NlogN). Iterating through the degree array to calculate the total importance is an O(N) operation. Therefore, the overall time complexity remains O(N^2).

Space complexity: O(N)

We need an array of size N, degree, to keep the edge count of each node.

Some additional space is required for sorting. The space complexity of the sorting algorithm depends on the programming language.

In Python, the sort method sorts a list using the Tim Sort algorithm which is a combination of Merge Sort and Insertion Sort and has O(n) additional space. Additionally, Tim Sort is designed to be a stable algorithm.
In Java, Arrays.sort() is implemented using a variant of the Quick Sort algorithm which has a space complexity of O(logn) for sorting an array.
In C++, the sort() function is implemented as a hybrid of Quick Sort, Heap Sort, and Insertion Sort, with a worse-case space complexity of O(logn).
Thus, the inbuilt sort() function might add up to O(log⁡⁡N) or O(N) to the space complexity.

**/

class Solution {
    public long maximumImportance(int n, int[][] roads) {
        long[] degree = new long[n];

        // Calculating the degree of each node (number of connected edges).
        for(int[] road : roads){
            degree[road[0]]++;
            degree[road[1]]++;
        }

        // sorting the array
        Arrays.sort(degree);

        // Assigning values 1 to N to nodes, starting with the lowest degree.
        long value = 1;
        long totalImportance = 0;
        for(long d : degree){
            totalImportance += value * d;
            value++;
        }

        return totalImportance;

    }
}