/*
 * ==========================================
 * PROBLEM STATEMENT: Construct Uniform Parity Array II
 * ==========================================
 * Given an array `nums1` of `n` distinct integers, we want to construct an array `nums2` 
 * of length `n` such that all elements in `nums2` are either all odd or all even.
 * For each index `i`, we can either:
 * - Keep `nums2[i] = nums1[i]`
 * - Set `nums2[i] = nums1[i] - nums1[j]` for some `j != i` such that `nums1[i] - nums1[j] >= 1`.
 * Return true if we can make all elements of `nums2` share the same parity, otherwise false.
 * 
 * ==========================================
 * OPTIMAL SOLUTION & APPROACH (Parity Analysis & Minimum Elements)
 * ==========================================
 * - Approach:
 *   1. To make all elements **even**:
 *      - Any even number in `nums1` can stay as is (even - even = even).
 *      - Any odd number in `nums1` can become even if we subtract a smaller odd number from it (odd - odd = even). 
 *        Thus, we just need to ensure there is *at least one* odd number in `nums1` smaller than every odd number we want to convert 
 *        (or simply, if there is at least one odd number in `nums1`, every odd number can be paired with the global minimum odd number, 
 *        since `odd - min_odd = even`). If there are no odd numbers, all are already even, which is valid.
 * 
 *   2. To make all elements **odd**:
 *      - An odd number can stay as is (odd).
 *      - An even number can become odd if we subtract an odd number from it (even - odd = odd). 
 *        Thus, to convert all even numbers to odd, we must have *at least one* odd number in `nums1` that is 
 *        strictly smaller than the smallest even number in `nums1` (so that we can subtract it and keep the result $\ge 1$).
 *        Specifically, `min_even > min_odd` must hold if there are any even numbers.
 * 
 * - Complexity:
 *   - Time Complexity: O(n) to find the minimums and count parities.
 *   - Space Complexity: O(1) auxiliary space.
 */

class Solution {
    public boolean uniformArray(int[] nums1) {
        int minOdd = Integer.MAX_VALUE;
        int minEven = Integer.MAX_VALUE;
        int oddCount = 0;
        int evenCount = 0;

        for (int x : nums1) {
            if (x % 2 != 0) {
                oddCount++;
                minOdd = Math.min(minOdd, x);
            }
            else {
                evenCount++;
                minEven = Math.min(minEven, x);
            }
        }

        // Case 1: Can we make all elements even?
        // If there are no odd numbers, all are already even -> True.
        // If there is at least one odd number, we can subtract it from any other odd number to make it even.
        boolean canAllEven = (oddCount == 0 || minOdd != Integer.MAX_VALUE);

        // Case 2: Can we make all elements odd?
        // To make an even number odd, we must subtract an odd number from it. 
        // This requires the smallest odd number to be smaller than the smallest even number (minOdd < minEven).
        // If there are no even numbers, all are already odd -> True.
        boolean canAllOdd = (evenCount == 0 || (minOdd != Integer.MAX_VALUE && minOdd < minEven));

        return canAllEven || canAllOdd;
    }
}
