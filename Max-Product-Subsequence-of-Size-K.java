/*
 * ==========================================
 * PROBLEM STATEMENT: Max Product Subsequence of Size K
 * ==========================================
 * Given an array `arr[]` of integers and an integer `k`, find a subsequence of size `k` 
 * whose product is maximum among all possible subsequences of size `k`. Return the maximum product.
 * 
 * Constraints:
 * arr.size() <= 30
 * -10 <= arr[i] <= 10
 * 1 <= k <= arr.size()
 * 
 * ==========================================
 * OPTIMAL SOLUTION & APPROACH (Dynamic Programming)
 * ==========================================
 * - Approach:
 *   1. Because negative numbers can become large positive numbers when multiplied by another negative number, 
 *      we need to maintain both the maximum product (`maxDP[j]`) and minimum product (`minDP[j]`) 
 *      for each subsequence size `j` from 0 to `k`.
 *   2. `maxDP[j]` stores the maximum product of a subsequence of size `j`.
 *   3. `minDP[j]` stores the minimum product of a subsequence of size `j` (which can hold large negative numbers).
 *   4. For each element `num` in the array, we iterate backwards from `k` down to `1` to update our DP tables, 
 *      ensuring each element is used at most once per subsequence.
 * 
 * - Complexity:
 *   - Time Complexity: O(n * k), where `n` is the array size and `k` is the subsequence size.
 *   - Space Complexity: O(k) auxiliary space to store the DP arrays.
 */

class Solution {
    public int maxProduct(int[] arr, int k) {
        int n = arr.length;

        long[] maxDP = new long[k + 1];
        long[] minDP = new long[k + 1];

        // Initialize DP arrays with extreme values
        for (int j = 0; j <= k; j++) {
            maxDP[j] = Long.MIN_VALUE;
            minDP[j] = Long.MAX_VALUE;
        }
        
        // Base case: Subsequence of size 0 has a product of 1
        maxDP[0] = 1;
        minDP[0] = 1;

        for (int num : arr) {
            for (int j = k; j >= 1; j--) {
                if (maxDP[j - 1] == Long.MIN_VALUE) {
                    continue;
                }
                
                long a = maxDP[j - 1] * num;
                long b = minDP[j - 1] * num;

                long newMax = Math.max(a, b);
                long newMin = Math.min(a, b);

                maxDP[j] = Math.max(maxDP[j], newMax);
                minDP[j] = Math.min(minDP[j], newMin);
            }
        }
        
        return (int) maxDP[k];
    }
}
