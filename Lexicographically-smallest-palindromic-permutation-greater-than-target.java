/*
 * ==========================================
 * PROBLEM STATEMENT: Lexicographically Smallest Palindromic Permutation Greater Than Target
 * ==========================================
 * Given two strings `s` and `target` of each length `n`, return the lexicographically smallest 
 * string that is both a palindromic permutation of `s` and strictly greater than `target`. 
 * If no such permutation exists, return "".
 * 
 * ==========================================
 * OPTIMAL SOLUTION & APPROACH (Half-Prefix Backtracking)
 * ==========================================
 * - Approach:
 *   1. Check if `s` can form a palindrome: At most one character can have an odd frequency.
 *   2. Since any valid palindrome is fully defined by its first half and an optional middle character, 
 *      we only need to build the first half of length `n / 2` using half the frequencies of each character.
 *   3. Use backtracking to generate the first half lexicographically (from 'a' to 'z').
 *   4. Once the first half is completed, construct the full palindrome by appending the middle character 
 *      (if `n` is odd) and the reversed first half.
 *   5. Compare the fully constructed palindrome against `target`. If it is strictly greater, save it and 
 *      return immediately because generating characters in increasing order guarantees the first valid one is the smallest.
 * 
 * - Complexity:
 *   - Time Complexity: O((n/2)! / product(count[i]!)) in the worst case, heavily optimized by pruning.
 *   - Space Complexity: O(n) for recursion stack and string assembly.
 */

class Solution {
    String result = "";
    char midChar = '$';
    int half = 0;

    boolean solve(StringBuilder curr, int[] count, String target, int i, boolean greater) {
        if (i == half) {
            String leftHalf = curr.toString();                     
            String rightHalf = new StringBuilder(leftHalf).reverse().toString();          

            String candidate = leftHalf;
            if (midChar != '$') {
                candidate += midChar;                               
            }
            candidate += rightHalf;

            if (candidate.compareTo(target) > 0) {                 
                result = candidate;
                return true;
            }
            return false;
        }
        
        for (char ch = 'a'; ch <= 'z'; ch++) {
            if (count[ch - 'a'] == 0)
                continue;

            // If we are not yet greater than the target prefix, we can check a prefix optimization 
            // by comparing against the corresponding prefix of target.
            if (!greater && ch < target.charAt(i))
                continue;

            curr.append(ch);
            count[ch - 'a']--;

            boolean isGreater = greater || ch > target.charAt(i);

            if (solve(curr, count, target, i + 1, isGreater))
                return true;

            // Backtrack
            curr.deleteCharAt(curr.length() - 1);
            count[ch - 'a']++;
        }
        return false;
    }

    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();
        int[] count = new int[26];
        result = "";
        midChar = '$';

        for (char ch : s.toCharArray())
            count[ch - 'a']++;

        int oddCount = 0;
        for (int c = 0; c < 26; c++) {
            if (count[c] % 2 == 1) {
                oddCount++;
                midChar = (char) (c + 'a');
            }
        }
        
        // A palindrome can have at most one character with an odd frequency
        if (oddCount > 1)
            return "";

        int[] halfCount = new int[26];
        for (int c = 0; c < 26; c++) {
            halfCount[c] = count[c] / 2;
        }
        
        half = n / 2;

        StringBuilder curr = new StringBuilder();
        solve(curr, halfCount, target, 0, false);
        return result;
    }
}
