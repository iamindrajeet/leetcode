/**
Approach: Prefix Product
Intuition

We need to implement the ProductOfNumbers class initialized with an empty integer stream that supports two operations:
1. add(int num): Add num to the stream.
2. getProduct(int k): Return the product of the last k integers in the stream. It's guaranteed that the product of the last k integers would fit into a 32-bit integer.

While the problem seems simple, the constraints - especially the potential size of the stream - make it clear that a brute force solution won’t work. A brute force approach would involve iterating over the last k integers each time a query is made, but this would be inefficient given the constraints of the problem (the stream size and k are both bound by 4 * 10^4).

To think of an optimized approach, let’s first consider the add function in which we need to find the sum of the last k integers. A natural solution here is a prefix sum approach. Prefix sum refers to an array where each element at index i represents the sum of the elements in the original array from the beginning up to the i-th element. This allows us to efficiently compute the sum of any subarray by subtracting two prefix sums. More specifically, by storing the cumulative sum of all integers up to the current index in an array, we can quickly compute the sum of the last k integers by simply taking the difference between two prefix sums: prefixSum[size] - prefixSum[size - k]. This gives us the sum in constant time.

Now, let’s apply a similar idea for the product. Instead of maintaining a prefix sum, we can maintain a prefix product array. This array will store the product of all integers encountered in the stream up to the current index. When we need the product of the last k integers, we can calculate it in constant time using the formula prefixProduct[size] / prefixProduct[size - k], just like we did for the sum.

But there's one edge case here: the presence of a 0 in the stream complicates things. If we encounter a 0, it nullifies all the products that come after it. For example, if the last k integers include a 0, the product of those integers will always be 0, regardless of the other numbers. This creates an issue when trying to calculate the product of the last k integers, especially if the 0 occurred earlier in the stream, long before the last k elements.

To address this, we can reset the prefix product array whenever we encounter a 0. This ensures that once a 0 is encountered, the product calculation is reset, and any future products that involve the 0 will correctly result in 0. When the product array is reset, we initialize it with 1 to start fresh.

Now, when answering a query to return the product of the last k integers, we can check the size of the prefix product array. If the size is less than or equal to k, we know that the last k integers must include a 0, so we return 0. Otherwise, we simply compute the product using the formula prefixProduct[size] / prefixProduct[size - k].

Algorithm
- Constructor - ProductOfNumbers()
1. Initialize the prefixProduct list with {1} to handle multiplication logic without special cases for the initial product.
2. Set size to 0 to indicate that the product list is initially empty.

- Helper Function - add(int num)
1. If num == 0:
    - Reset the prefixProduct list to {1}.
    - Reset size to 0 to indicate an empty product list.
2. Otherwise:
    - Append the cumulative product of the current number by multiplying it with the last value in the prefixProduct list.
    - Increment size.

Helper Function - getProduct(int k)
1. If k > size:
    - Return 0 because this implies that a 0 had appeared within the last k elements, making the product 0.
2. Otherwise:
    - Return the result of dividing prefixProduct[size] by prefixProduct[size - k] to get the product of the last k elements.

Complexity:

1. Time Complexity: O(n)
2. Space Complexity: O(n)
*/
class ProductOfNumbers {
    // Stores cumulative product of the stream
    List<Integer> prefixProduct = new ArrayList<>();
    private int size = 0;

    public ProductOfNumbers() {
        // Initialize the product list with 1 to handle multiplication logic
        this.prefixProduct.add(1);
        this.size = 0;
    }

    public void add(int num) {
        if (num == 0) {
            // If num is 0, reset the cumulative products since multiplication with 0
            // invalidates previous products
            this.prefixProduct = new ArrayList<>();
            this.prefixProduct.add(1);
            this.size = 0;
        } else {
            // Append the cumulative product of the current number with the last product
            this.prefixProduct.add(this.prefixProduct.get(size) * num);
            this.size++;
        }
    }

    public int getProduct(int k) {
        // Check if the requested product length exceeds the size of the valid product
        // list
        if (k > this.size)
            return 0;
        // Compute the product of the last k elements using division
        return (this.prefixProduct.get(this.size) /
                this.prefixProduct.get(this.size - k));
    }
}

/**
 * Your ProductOfNumbers object will be instantiated and called as such:
 * ProductOfNumbers obj = new ProductOfNumbers();
 * obj.add(num);
 * int param_2 = obj.getProduct(k);
 */