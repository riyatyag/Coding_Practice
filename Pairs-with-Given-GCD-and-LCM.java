/*
 * ==========================================
 * PROBLEM STATEMENT: Pairs with Given GCD and LCM
 * ==========================================
 * Given two integers `x` and `y` representing the GCD and LCM of two unknown positive integers `a` and `b`, 
 * count the number of valid pairs (a, b) satisfying these conditions. Note that (a, b) and (b, a) are 
 * counted as distinct pairs when a ≠ b.
 * 
 * Constraints:
 * 1 ≤ x, y ≤ 10^4
 * 
 * ==========================================
 * OPTIMAL SOLUTION & APPROACH (Prime Factorization & Combinatorics)
 * ==========================================
 * - Mathematical Principle:
 *   1. For any two numbers `a` and `b`, the product of their GCD and LCM equals their product: 
 *      `GCD(a, b) * LCM(a, b) = a * b`, which implies `x * y = a * b`.
 *   2. Let `a' = a / x` and `b' = b / x`. Then `a' * b' = y / x = n`, and `GCD(a', b') = 1` 
 *      (i.e., `a'` and `b'` are coprime).
 *   3. Every prime factor of `n = y / x` must be allocated entirely either to `a'` or to `b'` 
 *      to maintain the coprimality condition.
 *   4. If `n` has `k` distinct prime factors, there are `2^k` ways to distribute these factors 
 *      between `a'` and `b'`, resulting in `2^k` valid pairs.
 * 
 * - Complexity:
 *   - Time Complexity: O(sqrt(y / x)) to find the distinct prime factors of `n`.
 *   - Space Complexity: O(1) auxiliary space.
 */

class Solution {
    public int pairCount(int x, int y) {
        // LCM must always be a multiple of GCD
        if (y % x != 0) {
            return 0;
        }
        
        int n = y / x;
        int distinctPrimeFactors = 0;

        // Count distinct prime factors of n = y / x
        for (int p = 2; p * p <= n; p++) {
            if (n % p == 0) {
                distinctPrimeFactors++;

                while (n % p == 0) {
                    n /= p;
                }
            }
        }
        
        // If remaining n is a prime number greater than 1
        if (n > 1) {
            distinctPrimeFactors++;
        }
        
        // Total pairs = 2^k, where k is the number of distinct prime factors
        return 1 << distinctPrimeFactors;
    }
}
