class Solution {
    public int findClosest(int x, int y, int z) {
        // Calculate absolute distances to person 3
        int distance1 = Math.abs(z - x);
        int distance2 = Math.abs(z - y);

        // Compare distances and return result
        if (distance1 < distance2) {
            return 1; // Person 1 is closer
        } else if (distance2 < distance1) {
            return 2; // Person 2 is closer
        } else {
            return 0; // Both are equally close
        }
    }
}
