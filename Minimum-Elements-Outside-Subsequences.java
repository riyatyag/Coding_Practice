/*
 * ==========================================
 * PROBLEM STATEMENT: Minimum Elements Outside Subsequences
 * ==========================================
 * Given an array `arr[]` of size `n`, partition its elements into a strictly increasing subsequence 
 * and a strictly decreasing subsequence. Each element can belong to at most one of these subsequences, 
 * and some elements may remain unused. Determine the minimum number of elements that cannot be included 
 * in either subsequence.
 * 
 * Constraints:
 * 1 <= n <= 100
 * 1 <= arr[i] <= 100
 * 
 * ==========================================
 * OPTIMAL SOLUTION & APPROACH (Dynamic Programming)
 * ==========================================
 * - Approach:
 *   1. We can reframe the problem as maximizing the total number of elements included in either the 
 *      strictly increasing subsequence or the strictly decreasing subsequence.
 *   2. Use dynamic programming where `dp[i][j]` represents the maximum number of elements selected so far, 
 *      where `i` is the index of the last element included in the increasing subsequence, and `j` is the 
 *      index of the last element included in the decreasing subsequence.
 *   3. `n` represents a dummy index for an empty subsequence.
 *   4. For each incoming element `arr[k]` from the array:
 *      - We can choose to skip `arr[k]` entirely.
 *      - We can append `arr[k]` to the increasing subsequence if it's strictly greater than `arr[i]` (or if the increasing subsequence is empty).
 *      - We can append `arr[k]` to the decreasing subsequence if it's strictly smaller than `arr[j]` (or if the decreasing subsequence is empty).
 *   5. Finally, the minimum number of unselected elements is `n - maxSelected`.
 * 
 * - Complexity:
 *   - Time Complexity: O(n^3), where `n` is the size of the array. Given `n <= 100`, this easily runs within limits.
 *   - Space Complexity: O(n^2) to maintain the DP table across iterations.
 */

class Solution {
    public int minCount(int[] arr) {
        int n = arr.length;
        int[][] dp = new int[n + 1][n + 1];

        // Initialize DP table with -1 (unreachable states)
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                dp[i][j] = -1;
            }
        }
        
        // Base case: Both subsequences are empty, 0 elements selected
        dp[n][n] = 0;

        for (int k = 0; k < n; k++) {
            int[][] next = new int[n + 1][n + 1];

            for (int i = 0; i <= n; i++) {
                for (int j = 0; j <= n; j++) {
                    next[i][j] = -1;
                }
            }

            for (int i = 0; i <= n; i++) {
                for (int j = 0; j <= n; j++) {
                    if (dp[i][j] == -1) continue;
                    
                    // Option 1: Skip the current element arr[k]
                    next[i][j] = Math.max(next[i][j], dp[i][j]);

                    // Option 2: Add arr[k] to the increasing subsequence
                    if (i == n || arr[k] > arr[i]) {
                        next[k][j] = Math.max(next[k][j], dp[i][j] + 1);
                    }
                    
                    // Option 3: Add arr[k] to the decreasing subsequence
                    if (j == n || arr[k] < arr[j]) {
                        next[i][k] = Math.max(next[i][k], dp[i][j] + 1);
                    }
                }
            }
            dp = next;
        }

        int maxSelected = 0;
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                maxSelected = Math.max(maxSelected, dp[i][j]);
            }
        }
        
        // Minimum unselected elements = total elements - maximum selected elements
        return n - maxSelected;
    }
}
