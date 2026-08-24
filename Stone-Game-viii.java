/*
 * ==========================================
 * PROBLEM STATEMENT: Stone Game VIII
 * ==========================================
 * Alice and Bob take turns playing a game, with Alice starting first. There are n stones.
 * On each turn, a player chooses x > 1, removes the leftmost x stones, adds their sum to their score, 
 * and places a new stone of that sum value on the left side. The game stops when 1 stone remains.
 * Alice wants to maximize the score difference (Alice's score - Bob's score), 
 * and Bob wants to minimize it. Both play optimally.
 * 
 * Constraints:
 * n == stones.length
 * 2 <= n <= 10^5
 * -10^4 <= stones[i] <= 10^4
 * 
 * ==========================================
 * OPTIMAL SOLUTION & APPROACH (Prefix Sums + Dynamic Programming / Backward Iteration)
 * ==========================================
 * - Approach:
 *   1. Let prefix sums be `prefixSum[i]` representing the sum of the first `i` stones.
 *   2. When a player chooses to remove `x` stones, they score `prefixSum[x]` and leave a new stone of value `prefixSum[x]` at index 0. 
 *      Subsequent moves operate on these prefix sums.
 *   3. If a player chooses to make a move at step `x`, their net score difference from that point onwards 
 *      can be represented as `prefixSum[x] - maxScoreFuture`.
 *   4. By working backwards from `n` down to `2`, we can maintain the maximum score difference a player can achieve 
 *      from choosing index `x` onwards, leading to an optimal O(n) solution.
 * 
 * - Complexity:
 *   - Time Complexity: O(n), since we compute prefix sums and iterate through the array in linear time.
 *   - Space Complexity: O(n) to store the prefix sum array.
 */

class Solution {
    public int stoneGameVIII(int[] stones) {
        int n = stones.length;
        
        // Step 1: Compute prefix sums of the stones array
        long[] prefixSum = new long[n];
        prefixSum[0] = stones[0];
        for (int i = 1; i < n; i++) {
            prefixSum[i] = prefixSum[i - 1] + stones[i];
        }
        
        // Step 2: Initialize maxResult with the score if the game ends at the last step (taking all n stones)
        long maxResult = prefixSum[n - 1];
        
        // Step 3: Iterate backwards from n-2 down to 1
        for (int i = n - 2; i >= 1; i--) {
            // At index i, the player can choose to take prefixSum[i] and subtract the opponent's optimal future score
            maxResult = Math.max(maxResult, prefixSum[i] - maxResult);
        }
        
        return (int) maxResult;
    }
}
