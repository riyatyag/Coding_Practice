/*
 * ==========================================
 * PROBLEM STATEMENT: Smallest Stable Index I
 * ==========================================
 * Given an integer array `nums` of length `n` and an integer `k`.
 * For each index `i`, its instability score is defined as `max(nums[0..i]) - min(nums[i..n - 1])`.
 * An index `i` is stable if its instability score is less than or equal to `k`.
 * Return the smallest stable index. If no such index exists, return -1.
 * 
 * ==========================================
 * OPTIMAL SOLUTION & APPROACH (Prefix Max & Suffix Min)
 * ==========================================
 * - Approach:
 *   1. For each index `i`, we need the maximum value in the prefix `nums[0..i]` and the minimum 
 *      value in the suffix `nums[i..n-1]`.
 *   2. We can precompute the suffix minimums using an array `suffixMin[]` where `suffixMin[i]` 
 *      stores the minimum value from index `i` to `n - 1`.
 *   3. We can maintain the running prefix maximum as we iterate from left to right (`0` to `n - 1`).
 *   4. For each index `i`, we check if `prefixMax - suffixMin[i] <= k`. The first index that satisfies 
 *      this condition is returned immediately since we iterate in increasing order.
 * 
 * - Complexity:
 *   - Time Complexity: O(n) to build the suffix minimums and perform a single linear scan.
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

        // Iterate through each index to find the first stable index
        for (int i = 0; i < n; i++) {
            prefixMax = Math.max(prefixMax, nums[i]);
            
            if (prefixMax - suffixMin[i] <= k) {
                return i;
            }
        }
        
        return -1;
    }
}
