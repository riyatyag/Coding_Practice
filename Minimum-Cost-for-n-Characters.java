/*
 * ==========================================
 * PROBLEM STATEMENT: Minimum Cost for n Characters
 * ==========================================
 * Given four integers `n`, `i`, `d`, and `c`, where:
 * - `i` is the cost of inserting a single character,
 * - `d` is the cost of deleting the last character,
 * - `c` is the cost of copying and doubling the current string.
 * Find the minimum cost required to obtain exactly `n` characters on an initially empty screen.
 * 
 * Constraints:
 * 1 <= n <= 10^6
 * 1 <= i, d, c <= 100
 * 
 * ==========================================
 * OPTIMAL SOLUTION & APPROACH (Dynamic Programming)
 * ==========================================
 * - Approach:
 *   1. Use a DP array where `dp[x]` represents the minimum cost to form `x` characters.
 *   2. Initialize the array assuming all characters are formed purely by insertions (`x * i`).
 *   3. Iterate from `2` up to `n + 1` (slightly above `n` to account for overshooting and deleting back):
 *      - Option to form `x` by adding 1 character to `x - 1`: `dp[x - 1] + i`.
 *      - If `x` is even, option to double from `x / 2`: `dp[x / 2] + c`.
 *      - If `x` is odd, option to overshoot to `x + 1` (double from `(x + 1) / 2`) and delete 1 character: `dp[(x + 1) / 2] + c + d`.
 *   4. Return `dp[n]` as the final minimum cost.
 * 
 * - Complexity:
 *   - Time Complexity: O(n)
 *   - Space Complexity: O(n)
 */

class Solution {
    public int minCost(int n, int i, int d, int c) {
        long[] dp = new long[n + 2];
        dp[0] = 0;
        
        // Base case: initialize with pure insertion cost
        for (int x = 1; x <= n + 1; x++) {
            dp[x] = (long) x * i;
        }

        for (int x = 2; x <= n + 1; x++) {
            // Cost from previous character + insert
            dp[x] = Math.min(dp[x], dp[x - 1] + i);

            if (x % 2 == 0) {
                // Cost from half size + copy-paste (doubling)
                dp[x] = Math.min(dp[x], dp[x / 2] + c);
            }
            else {
                // For odd numbers, we can either come from x - 1 + insert, 
                // or overshoot to x + 1 by doubling (x + 1) / 2 and then deleting 1 character.
                dp[x] = Math.min(dp[x], dp[x - 1] + i);
                dp[x] = Math.min(dp[x], dp[(x + 1) / 2] + c + d);
            }
        }
        
        return (int) dp[n];
    }
}
