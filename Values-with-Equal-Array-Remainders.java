/*
 * ==========================================
 * PROBLEM STATEMENT: Values with Equal Array Remainders
 * ==========================================
 * Given an integer array `arr[]`, count the number of positive integers `k` such that all elements 
 * of the array leave the same remainder when divided by `k`. If there are infinitely many such 
 * values of `k`, return -1.
 * 
 * Constraints:
 * 1 <= arr.size(), arr[i] <= 10^5
 * 
 * ==========================================
 * OPTIMAL SOLUTION & APPROACH (Mathematical GCD of Differences)
 * ==========================================
 * - Approach:
 *   1. If all elements in the array are equal, any positive integer `k` will leave the same remainder 
 *      (0), meaning there are infinitely many valid `k` values. Return -1.
 *   2. For all elements to leave the same remainder when divided by `k`, the difference between 
 *      any two adjacent or relative elements must be a multiple of `k`.
 *   3. Specifically, if `arr[0], arr[1], ..., arr[n-1]` all leave remainder `r` modulo `k`, then 
 *      `arr[i] ≡ r (mod k)` and `arr[0] ≡ r (mod k)` imply that `arr[i] - arr[0] ≡ 0 (mod k)`.
 *   4. Therefore, `k` must divide the greatest common divisor (GCD) of all absolute differences 
 *      between each element and the first element: `gcd(|arr[1] - arr[0]|, |arr[2] - arr[0]|, ..., |arr[n-1] - arr[0]|)`.
 *   5. The number of valid `k` values is equal to the number of positive divisors of this GCD.
 * 
 * - Complexity:
 *   - Time Complexity: O(n + sqrt(G)) where `n` is the array length and `G` is the calculated GCD.
 *   - Space Complexity: O(1) auxiliary space.
 */

class Solution {
    public int sameMod(int[] arr) {
        int n = arr.length;

        // Check if all elements are equal (infinitely many valid k values)
        boolean allEqual = true;
        for (int i = 1; i < n; i++) {
            if (arr[i] != arr[0]) {
                allEqual = false;
                break;
            }
        }
        if (allEqual) {
            return -1;
        }

        // Find the GCD of differences between elements and arr[0]
        int gcd = 0;
        for (int i = 1; i < n; i++) {
            gcd = findGCD(gcd, Math.abs(arr[i] - arr[0]));
        }

        // Count all divisors of the GCD
        int count = 0;
        for (int i = 1; i * i <= gcd; i++) {
            if (gcd % i == 0) {
                count++;

                if (i != gcd / i) {
                    count++;
                }
            }
        }

        return count;
    }

    private int findGCD(int a, int b) {
        while (b != 0) {
            int temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }
}
