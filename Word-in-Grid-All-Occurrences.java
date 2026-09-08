/*
 * ==========================================
 * PROBLEM STATEMENT: Word in Grid - All Occurrences
 * ==========================================
 * Given a 2D grid `mat[][]` of size `n x m` and a string `word`, find all starting positions 
 * where the word occurs in the grid. The word can be formed by moving in any of the 8 directions 
 * in a straight line. Return all unique starting coordinates in lexicographically smallest order.
 * 
 * Constraints:
 * 1 <= n <= m <= 50
 * 1 <= |word| <= 20
 * 
 * ==========================================
 * OPTIMAL SOLUTION & APPROACH (Directional Search / Matrix Traversal)
 * ==========================================
 * - Approach:
 *   1. Iterate through each cell `(i, j)` in the grid.
 *   2. If the character `mat[i][j]` matches the first character of the `word`, explore all 8 possible 
 *      directions using direction arrays (`dx` and `dy`).
 *   3. For each direction, check if the full `word` can be formed continuously without going out of bounds.
 *   4. If a valid match is found in any direction, add the starting coordinate `[i, j]` to the result list 
 *      and break out of the direction loop for this cell to avoid duplicate additions for the same starting point.
 *   5. Since we iterate row by row and column by column, the coordinates are naturally collected in 
 *      lexicographically smallest order.
 * 
 * - Complexity:
 *   - Time Complexity: O(n * m * 8 * |word|), which is extremely efficient for the given constraints.
 *   - Space Complexity: O(1) auxiliary space (excluding the output list).
 */

import java.util.ArrayList;

class Solution {
    public ArrayList<ArrayList<Integer>> searchWord(char[][] mat, String word) {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();

        int n = mat.length;
        int m = mat[0].length;

        // Direction arrays for all 8 surrounding directions
        int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // Pruning: skip if the starting character doesn't match
                if (mat[i][j] != word.charAt(0)) continue;

                boolean found = false;

                for (int dir = 0; dir < 8 && !found; dir++) {
                    int x = i;
                    int y = j;
                    int k;

                    for (k = 0; k < word.length(); k++) {
                        if (x < 0 || x >= n || y < 0 || y >= m || mat[x][y] != word.charAt(k)) {
                            break;
                        }
                        x += dx[dir];
                        y += dy[dir];
                    }
                    
                    if (k == word.length()) {
                        found = true;
                    }
                }
                
                if (found) {
                    ArrayList<Integer> pos = new ArrayList<>();
                    pos.add(i);
                    pos.add(j);
                    ans.add(pos);
                }
            }
        }
        
        return ans;
    }
}
