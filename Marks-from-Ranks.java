/*
 * ==========================================
 * PROBLEM STATEMENT: Marks from Ranks
 * ==========================================
 * Given intervals of marks represented by `l[]` and `r[]`, and an array `rank[]`, find the 
 * corresponding mark for each rank. The valid marks are sorted in increasing order across all intervals.
 * 
 * ==========================================
 * OPTIMAL SOLUTION & APPROACH (Prefix Sums + Binary Search)
 * ==========================================
 * - Approach:
 *   1. Compute a prefix sum array of the sizes of each interval to quickly know how many valid 
 *      marks exist up to each interval index.
 *   2. For each query `k` (the rank) in `rank[]`, use binary search (`lower_bound`) to find the 
 *      specific interval `idx` where the `k`-th mark falls.
 *   3. Calculate the exact mark using the starting mark of that interval `l[idx]` plus the offset 
 *      within that interval (`k - previousCount - 1`).
 * 
 * - Complexity:
 *   - Time Complexity: O(n + Q * log(n)) where `n` is the number of intervals and `Q` is the number of ranks.
 *   - Space Complexity: O(n) to store the prefix sum array.
 */

import java.util.ArrayList;

class Solution {
    public ArrayList<Integer> getMarks(int[] l, int[] r, int[] rank) {
        int n = l.length;
        ArrayList<Integer> ans = new ArrayList<>();

        // prefix[i] stores the total count of valid marks from interval 0 to i
        long[] prefix = new long[n];
        prefix[0] = (long) r[0] - l[0] + 1;

        for (int i = 1; i < n; i++) {
            prefix[i] = prefix[i - 1] + (long) r[i] - l[i] + 1;
        }

        // Process each rank using binary search
        for (int k : rank) {
            int low = 0, high = n - 1;

            while (low < high) {
                int mid = low + (high - low) / 2;

                if (prefix[mid] >= k) {
                    high = mid;
                } 
                else {
                    low = mid + 1;
                }
            }
            int idx = low;

            long previousCount = (idx == 0) ? 0 : prefix[idx - 1];
            int mark = (int) (l[idx] + (k - previousCount - 1));
            ans.add(mark);
        }
        
        return ans;
    }
}
