/*
 * ==========================================
 * PROBLEM STATEMENT: Count Palindromic Strings with Constraints
 * ==========================================
 * Given two integers `n` and `k`, consider an alphabet consisting of the first `k` lowercase English letters. 
 * Find the number of palindromic strings of length <= `n` such that:
 * - Every character belongs to the first `k` letters.
 * - No character appears more than twice in the string.
 * Return the answer modulo 10^9 + 7.
 * 
 * Constraints:
 * 1 <= k <= 26
 * 1 <= n <= 52
 * n <= 2 * k
 * 
 * ==========================================
 * OPTIMAL SOLUTION & APPROACH (Combinatorics & Permutations)
 * ==========================================
 * - Approach:
 *   1. A palindrome is uniquely determined by its first half (and an optional middle character if length is odd).
 *   2. Since no character can appear more than twice in the entire string, each character can appear 
 *      at most once in the first half of the palindrome.
 *   3. For a palindrome of length `len`, the number of pairs (or first-half length) is `pairs = len / 2`.
 *   4. We choose `pairs` distinct characters from the available `k` characters for the first half:
 *      - When transitioning to an even length, we pick a new character for the next position, multiplying 
 *      the number of ways by the remaining available characters `(k - pairs + 1)`.
 *      - When length is odd, the middle character can be any of the remaining unused characters `(k - pairs)`.
 *   5. We accumulate the valid palindromes for all lengths from 1 to `n` modulo 10^9 + 7.
 * 
 * - Complexity:
 *   - Time Complexity: O(n)
 *   - Space Complexity: O(1)
 */

class Solution {
    static final long MOD = 1_000_000_007L;

    public int palindromicStrings(int n, int k) {
        long ans = 0;
        long perm = 1; // Tracks the number of ways to form the first half prefix

        for (int len = 1; len <= n; len++) {
            int pairs = len / 2;

            if (len % 2 == 1) {
                // Odd length: middle character can be any of the remaining unused characters
                ans = (ans + perm * (k - pairs)) % MOD;
            } 
            else {
                // Even length: add another character to the first half prefix
                perm = (perm * (k - pairs + 1)) % MOD;
                ans = (ans + perm) % MOD;
            }
        }
        
        return (int) ans;
    }
}
