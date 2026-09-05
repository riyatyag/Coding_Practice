/*
 * ==========================================
 * PROBLEM STATEMENT: Smallest Stable Index II
 * ==========================================
 * Given an integer array `nums` of length `n` (up to $10^5$) and an integer `k`.
 * For each index `i`, its instability score is defined as `max(nums[0..i]) - min(nums[i..n - 1])`.
 * An index `i` is stable if its instability score is less than or equal to `k`.
 * Return the smallest stable index. If no such index exists, return -1.
 * 
 * ==========================================
 * OPTIMAL SOLUTION & APPROACH (Prefix Max & Suffix Min)
 * ==========================================
 * - Approach:
 *   1. Because `n` can be up to $10^5$, an $O(n^2)$ approach will time out. We need an $O(n)$ solution.
 *   2. Precompute a `suffixMin` array where `suffixMin[i]` stores the minimum element in the range `nums[i..n-1]`.
 *   3. Maintain a running `prefixMax` as we iterate through the array from left to right (`0` to `n - 1`).
 *   4. Cast the subtraction to `long` (`(long) prefixMax - suffixMin[i]`) to prevent integer overflow since 
 *      values and `k` can be as large as $10^9$.
 *   5. The first index `i` where the instability score is `<= k` is returned immediately.
 * 
 * - Complexity:
 *   - Time Complexity: O(n) for precomputing suffix minimums and a single linear scan.
 *   - Space Complexity: O(n) to store the suffix minimum array.
 */

class Solution {
    public int firstStableIndex(int[] nums, int k) {
        int n = nums.length;

        // suffixMin[i] stores the minimum element from index i to n - 1
        int[] suffixMin = new int[n];
        suffixMin[n - 1] = nums[n - 1];

        for (int i = n - 2; i >= 0; i--) {
            suffixMin[i] = Math.min(nums[i], suffixMin[i + 1]);
        }
         int prefixMax = Integer.MIN_VALUE;

        // Iterate through each index to find the first stable index in linear time
        for (int i = 0; i < n; i++) {
            prefixMax = Math.max(prefixMax, nums[i]);
            
            // Use long to prevent overflow during subtraction given constraints up to 10^9
            long instability = (long) prefixMax - suffixMin[i];

            if (instability <= k) {
                return i;
            }
        }
         return -1;
    }
}
