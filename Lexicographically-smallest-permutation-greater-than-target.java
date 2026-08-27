/*
 * ==========================================
 * PROBLEM STATEMENT: Lexicographically Smallest Permutation Greater Than Target
 * ==========================================
 * Given two strings `s` and `target` of length `n`, return the lexicographically smallest 
 * permutation of `s` that is strictly greater than `target`. If no such permutation exists, return "".
 * 
 * Constraints:
 * 1 <= s.length == target.length <= 300
 * s and target consist of lowercase English letters.
 * 
 * ==========================================
 * OPTIMAL SOLUTION & APPROACH (Backtracking with Character Counting)
 * ==========================================
 * - Approach:
 *   1. Count the frequency of each character in string `s` using a frequency array of size 26.
 *   2. Use backtracking to build the permutation character by character from left to right (index `0` to `n - 1`).
 *   3. Maintain a boolean flag `greater` which tracks whether the prefix built so far is already strictly greater than `target`.
 *   4. Iterate through characters from `'a'` to `'z'` at each position to naturally ensure the lexicographically smallest 
 *      valid sequence is generated first.
 *   5. Prune branches where `!greater && ch < target.charAt(i)` since they would result in a prefix smaller than the target.
 * 
 * - Complexity:
 *   - Time Complexity: O(n * 26) in practice due to early pruning and returning upon finding the first valid match.
 *   - Space Complexity: O(n) for the recursion stack and string builder.
 */

class Solution {
    String result = "";
    
    boolean solve(StringBuilder curr, int[] count, String target, int i, boolean greater) {
        if (i == target.length()) {
            if (greater) {
                result = curr.toString();
                return true;
            }
            return false;
        }
        
        for (char ch = 'a'; ch <= 'z'; ch++) {
            if (count[ch - 'a'] == 0)
                continue;

            // If we are not yet greater than the target prefix, we cannot pick a character smaller than target's character
            if (!greater && ch < target.charAt(i))
                continue;

            curr.append(ch);
            count[ch - 'a']--;

            boolean isGreater = greater || ch > target.charAt(i);

            if (solve(curr, count, target, i + 1, isGreater)) {
                return true;
            }
            
            // Backtrack
            curr.deleteCharAt(curr.length() - 1);
            count[ch - 'a']++;
        }
        
        return false;
    }

    public String lexGreaterPermutation(String s, String target) {
        int[] count = new int[26];
        result = "";

        for (char ch : s.toCharArray())
            count[ch - 'a']++;

        StringBuilder curr = new StringBuilder();
        solve(curr, count, target, 0, false);

        return result;
    }
}
