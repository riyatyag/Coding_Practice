/*
 * ==========================================
 * PROBLEM STATEMENT: Count Subsequences Divisible by n
 * ==========================================
 * Given a numeric string `s` and an integer `n`, count the number of non-empty subsequences of `s` 
 * whose numeric value is divisible by `n`. Return the answer modulo 10^9 + 7.
 * 
 * Constraints:
 * 1 <= |s| * n <= 10^6
 * 
 * ==========================================
 * OPTIMAL SOLUTION & APPROACH (Dynamic Programming with Remainders)
 * ==========================================
 * - Approach:
 *   1. This can be solved using dynamic programming by keeping track of the count of subsequences 
 *      for each possible remainder modulo `n`.
 *   2. For each digit in the string `s`, we can either start a new subsequence with this digit 
 *      or append this digit to all existing subsequences formed so far.
 *   3. If an existing subsequence has a remainder `rem`, appending the current `digit` results in a new remainder 
 *      `newRem = (rem * 10 + digit) % n`.
 *   4. We update the DP table iteratively for each character in `s` and return `dp[0]` (the number of subsequences with remainder 0).
 * 
 * - Complexity:
 *   - Time Complexity: O(|s| * n), since for each character in `s`, we iterate through all `n` remainders.
 *   - Space Complexity: O(n) to store the remainder DP array.
 */

class Solution {
    static final int MOD = 1_000_000_007;

    public int countSubsequences(String s, int n) {
        // dp[rem] stores the count of subsequences having a numeric value with remainder `rem` modulo `n`
        long[] dp = new long[n];

        for (char ch : s.toCharArray()) {
            int digit = ch - '0';
            long[] next = dp.clone();
            
            // Case 1: Start a new subsequence consisting of just this single digit
            next[digit % n] = (next[digit % n] + 1) % MOD;

            // Case 2: Append this digit to all previously formed valid subsequences
            for (int rem = 0; rem < n; rem++) {
                int newRem = (rem * 10 + digit) % n;
                next[newRem] = (next[newRem] + dp[rem]) % MOD;
            }
            
            dp = next;
        }
        
        // Return total count of non-empty subsequences divisible by n (remainder 0)
        return (int) dp[0];
    }
}
