/*
 * ==========================================
 * PROBLEM STATEMENT: Count Commas in Range II
 * ==========================================
 * Given an integer `n`, return the total number of commas used when writing all integers 
 * from 1 to `n` (inclusive) in standard number formatting (a comma after every three digits from the right).
 * 
 * Constraints:
 * 1 <= n <= 10^15
 * 
 * ==========================================
 * OPTIMAL SOLUTION & APPROACH (Digit Counting / Mathematical Breakdown)
 * ==========================================
 * - Approach:
 *   1. Numbers with 1 to 3 digits contain 0 commas.
 *   2. Numbers with 4 to 6 digits contain 1 comma.
 *   3. Numbers with 7 to 9 digits contain 2 commas, and so on.
 *   4. In general, a number with `len` digits contains `(len - 1) / 3` commas.
 *   5. We can iterate over each comma count block (1 comma, 2 commas, etc.), determine the range 
 *   of numbers that fall into that block (e.g., 1,000 to 999,999 for 1 comma), intersect that range 
 *   with `[1, n]`, and calculate the total contribution using `long` to prevent overflow.
 * 
 * - Complexity:
 *   - Time Complexity: O(log10(n)), which takes a constant number of steps since `n <= 10^15`.
 *   - Space Complexity: O(1) auxiliary space.
 */

class Solution {
    public long countCommas(long n) {
        long totalCommas = 0;
        
        // Start checking from 4-digit numbers (where commas first appear)
        long start = 1000;
        long commaCount = 1;

        while (start <= n) {
            // End of the current block with `commaCount` commas
            long end = Math.min(n, (start * 1000) - 1);
            
            // Number of integers in this range
            long count = end - start + 1;
            
            totalCommas += count * commaCount;

            // Move to the next block (e.g., from 10^3 to 10^6, then 10^9, etc.)
            if (start > n / 1000) {
                break;
            }
            start *= 1000;
            commaCount++;
        }

        return totalCommas;
    }
}
