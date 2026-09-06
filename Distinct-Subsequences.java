/*
 * ==========================================
 * PROBLEM STATEMENT: Distinct Subsequences
 * ==========================================
 * Given two strings `s` and `t`, return the number of distinct subsequences of `s` which equals `t`.
 * The test cases are generated so that the answer fits on a 32-bit signed integer.
 * 
 * ==========================================
 * OPTIMAL SOLUTION & APPROACH (Dynamic Programming - Space Optimized)
 * ==========================================
 * - Approach:
 *   1. Define a DP state where `dp[j]` represents the number of distinct subsequences of `s[0..i-1]` 
 *      that equal `t[0..j-1]`.
 *   2. When characters match (`s.charAt(i-1) == t.charAt(j-1)`), we have two choices:
 *      - Include the current character of `s`: we add the ways from the previous prefix matching `t[0..j-2]` (`prev[j-1]`).
 *      - Exclude the current character of `s`: we keep the ways from the previous prefix matching `t[0..j-1]` (`prev[j]`).
 *      - Thus, `curr[j] = prev[j-1] + prev[j]`.
 *   3. When characters do not match, we can only exclude the current character of `s`, so `curr[j] = prev[j]`.
 *   4. Space optimization: Since row `i` only depends on row `i-1`, we can use two 1D arrays (`prev` and `curr`) 
 *      or even a single 1D array traversed backwards. Using `long` arrays prevents overflow during intermediate additions.
 * 
 * - Complexity:
 *   - Time Complexity: O(m * n) where `m` and `n` are the lengths of strings `s` and `t`.
 *   - Space Complexity: O(n) auxiliary space using space-optimized 1D DP arrays.
 */

class Solution {
    public int numDistinct(String s, String t) {
        int m = s.length();
        int n = t.length();

        // Use long arrays to avoid any potential overflow during addition
        long[] curr = new long[n + 1];
        long[] prev = new long[n + 1];
        
        // Base case: An empty string t can always be formed 1 way (by deleting everything)
        prev[0] = 1;
        curr[0] = 1;
        
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    curr[j] = prev[j - 1] + prev[j];
                } 
                else {
                    curr[j] = prev[j];
                }
            }
            // Copy current row to previous for the next iteration
            prev = curr.clone();
        }
        
        return (int) prev[n];
    }
}
