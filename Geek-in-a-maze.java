/*
 * ==========================================
 * PROBLEM STATEMENT: Geek in a Maze
 * ==========================================
 * Given a maze mat[][] of size n × m with empty cells ('.') and obstacles ('#'), 
 * find the number of distinct empty cells that Geek can visit starting from (r, c) 
 * with at most `u` upward moves and `d` downward moves (and no limit on left/right moves).
 * 
 * Constraints:
 * 1 <= n, m <= 10^6 (Note: practical constraints on total cells typically allow BFS)
 * 0 <= r, c < n, m
 * 0 <= u, d <= 10^6
 * 
 * ==========================================
 * OPTIMAL SOLUTION & APPROACH (0-1 BFS)
 * ==========================================
 * - Approach:
 *   1. This can be modeled as a shortest-path / graph traversal problem using 0-1 BFS (Deque), 
 *      where moving "up" costs 1 upward move, and moving left, right, or down costs 0 additional upward moves 
 *      (since downward moves can be derived relative to upward moves and coordinate differences).
 *   2. We track the minimum upward moves needed to reach each cell from the start. 
 *      - Moving Up (`nx < x`): cost = 1.
 *      - Moving Down, Left, Right: cost = 0.
 *   3. Using a Deque, push 0-cost moves to the front and 1-cost moves to the back.
 *   4. After computing the minimum upward moves for each reachable cell, verify if the required 
 *      upward moves (`upMoves <= u`) and corresponding downward moves (`downMoves <= d`) satisfy the constraints.
 * 
 * - Complexity:
 *   - Time Complexity: O(n * m), since each cell is processed a constant number of times in 0-1 BFS.
 *   - Space Complexity: O(n * m) to store the distance/visited matrix and the deque.
 */

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

class Solution {
    public int numberOfCells(int r, int c, int u, int d, char[][] mat) {
        int n = mat.length;
        int m = mat[0].length;
        
        // If the starting cell is an obstacle, Geek cannot move anywhere
        if (mat[r][c] == '#') {
            return 0;
        }

        // dist[i][j] will store the minimum upward moves required to reach cell (i, j)
        int[][] dist = new int[n][m];
        for (int[] row : dist) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        
        // 0-1 BFS Deque
        Deque<int[]> dq = new ArrayDeque<>();
        
        dist[r][c] = 0;
        dq.offerFirst(new int[] {r, c});
        
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};
        
        while (!dq.isEmpty()) {
            int[] cur = dq.pollFirst();
            int x = cur[0];
            int y = cur[1];
            
            for (int k = 0; k < 4; k++) {
                int nx = x + dr[k];
                int ny = y + dc[k];
                
                // Check boundaries and obstacles
                if (nx < 0 || nx >= n || ny < 0 || ny >= m || mat[nx][ny] == '#') {
                    continue;
                }
                
                // Moving up costs 1 move; down, left, and right cost 0 extra upward moves
                int cost = (nx < x) ? 1 : 0;
                
                if (dist[x][y] + cost < dist[nx][ny]) {
                    dist[nx][ny] = dist[x][y] + cost;
                    
                    if (cost == 0) {
                        dq.offerFirst(new int[] {nx, ny});
                    } else {
                        dq.offerLast(new int[] {nx, ny});
                    }
                }
            }
        }

        int ans = 0;
        
        // Count all valid cells reachable within the allowed upward and downward limits
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (mat[i][j] == '#' || dist[i][j] == Integer.MAX_VALUE) {
                    continue;
                }
                
                int upMoves = dist[i][j];
                // Derived relationship: downMoves = upMoves + (current_row - start_row)
                int downMoves = upMoves + (i - r);
                
                if (upMoves <= u && downMoves <= d) {
                    ans++;
                }
            }
        }
        
        return ans;
    }
}
