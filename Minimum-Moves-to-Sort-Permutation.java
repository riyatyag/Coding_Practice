/*
 * ==========================================
 * PROBLEM STATEMENT: Minimum Moves to Sort Permutation
 * ==========================================
 * Given an array arr[] containing integers from 1 to n exactly once, sort the array in ascending order.
 * In one operation, you can pick any element and move it either to the beginning or to the end of the array.
 * Return the minimum number of operations required to sort the array.
 * 
 * Constraints:
 * arr.size() <= 10^5
 * 1 <= arr[i] <= arr.size()
 * 
 * ==========================================
 * OPTIMAL SOLUTION & APPROACH (Longest Consecutive Subsequence in Permutation)
 * ==========================================
 * - Approach:
 *   1. This problem can be mapped to finding the length of the longest increasing consecutive subsequence 
 *      (i.e., elements $1, 2, 3, \dots$ appearing in that order as a subsequence in the original array).
 *   2. Elements that form the longest consecutive subsequence of numbers from $1$ to $n$ in their correct relative order 
 *      do not need to be moved. We only need to move the remaining elements to the beginning or end.
 *   3. Therefore, minimum operations = $n - \text{length of longest consecutive increasing subsequence}$.
 * 
 * - Complexity:
 *   - Time Complexity: O(n), since we store positions and iterate from 1 to n-1 once.
 *   - Space Complexity: O(n) to store the position array.
 */

class Solution {
    public int minMoves(int[] arr) {
        int n = arr.length;
        // pos[val] stores the index of value `val` in the array
        int[] pos = new int[n + 1];

        for (int i = 0; i < n; i++) {
            pos[arr[i]] = i;
        }

        int longest = 1;
        int current = 1;

        // Find the length of the longest consecutive increasing subsequence (e.g., value, value+1, value+2...)
        for (int value = 1; value < n; value++) {
            // Check if `value + 1` appears to the right of `value` in the original array
            if (pos[value] < pos[value + 1]) {
                current++;
                longest = Math.max(longest, current);
            } 
            else {
                current = 1;
            }
        }

        // Minimum operations required is total elements minus the longest untouched subsequence
        return n - longest;
    }
}
