/**
Brute force : Linearly traversing between minimum and maximum day and finding out if it's possible to make 'm' 
bouquet with 'k' adjacent flowers.

✅ Time and Space Complexity:

Time Complexity:
    - Finding minDay and maxDay takes O(n)
    - Outer loop runs from minDay to maxDay 
    - Each iteration calls possibleToMakeBouquet → O(n)

So worst-case: O((maxDay - minDay) * n)

Space Complexity: O(1)
*/
// class Solution {
//     public int minDays(int[] bloomDay, int m, int k) {
//         int noOfFlowers = bloomDay.length;

//         // Check if it's even possible to make m bouquets with k flowers each
//         if (m * k > noOfFlowers) {
//             return -1;
//         }

//         // Find the minimum and maximum day from bloomDay[]
//         // These define the search range for the answer
//         int minDay = bloomDay[0], maxDay = bloomDay[0];
//         for (int i = 0; i < noOfFlowers; i++) {
//             minDay = Math.min(minDay, bloomDay[i]);
//             maxDay = Math.max(maxDay, bloomDay[i]);
//         }

//         // Brute-force from minDay to maxDay
//         for (int day = minDay; day <= maxDay; day++) {
//             if (possibleToMakeBouquet(bloomDay, day, m, k))
//                 return day;
//         }

//         // If no day found where we can make m bouquets, return -1
//         return -1;
//     }

//     private boolean possibleToMakeBouquet(int[] bloomDay, int day, int m, int k) {
//         int countOfAdjacentFlowers = 0;
//         int bouquet = 0;

//         // Traverse each flower
//         for (int currentDay : bloomDay) {
//             if (currentDay <= day) {
//                 // If flower has bloomed by this day, count it as part of current group
//                 countOfAdjacentFlowers++;
//             } else {
//                 // If the sequence breaks, calculate how many bouquets could be made
//                 bouquet += countOfAdjacentFlowers / k;
//                 countOfAdjacentFlowers = 0;

//                 // Early exit if enough bouquets already formed
//                 if (bouquet >= m)
//                     return true;
//             }
//         }

//         // After loop, check remaining group if any
//         bouquet += countOfAdjacentFlowers / k;

//         return bouquet >= m;
//     }
// }

class Solution {
    public int minDays(int[] bloomDay, int m, int k) {
        int noOfFlowers = bloomDay.length;

        // Check if it's even possible to make m bouquets with k flowers each
        // Total flowers needed = m * k. If not enough, return -1
        if (m * k > noOfFlowers) {
            return -1;
        }

        // Find the minimum and maximum day from bloomDay[]
        // These define the binary search range for the answer
        int minDay = bloomDay[0], maxDay = bloomDay[0];
        for (int i = 0; i < noOfFlowers; i++) {
            minDay = Math.min(minDay, bloomDay[i]);
            maxDay = Math.max(maxDay, bloomDay[i]);
        }

        // Binary search over days to find the minimum day on which m bouquets can be made
        int left = minDay, right = maxDay;
        int minNoOfDays = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;

            // If it's possible to make m bouquets by 'mid' day
            if (possibleToMakeBouquet(bloomDay, mid, m, k)) {
                minNoOfDays = mid;       // store current day
                right = mid - 1;         // try to find smaller day
            } else {
                left = mid + 1;          // need to wait for more flowers to bloom
            }
        }

        // If no valid day found, return -1
        return minNoOfDays;
    }

    private boolean possibleToMakeBouquet(int[] bloomDay, int day, int m, int k) {
        int countOfAdjacentFlowers = 0;
        int bouquet = 0;

        // Traverse each flower in bloomDay
        for (int currentDay : bloomDay) {
            if (currentDay <= day) {
                // Flower is bloomed by 'day', add to current group
                countOfAdjacentFlowers++;
            } else {
                // Sequence breaks, form bouquets with collected adjacent flowers
                bouquet += countOfAdjacentFlowers / k;
                countOfAdjacentFlowers = 0;

                // Early exit if required number of bouquets is formed
                if (bouquet >= m)
                    return true;
            }
        }

        // Check leftover flowers after the loop
        bouquet += countOfAdjacentFlowers / k;

        return bouquet >= m;
    }
}
