/*
 * ==========================================
 * PROBLEM STATEMENT: Negative Weight Cycle
 * ==========================================
 * Given a weighted directed graph containing V vertices and E directed edges, determine 
 * whether the graph contains a negative weight cycle or not.
 * 
 * Constraints:
 * 1 <= V <= 10^3
 * 0 <= E <= 10^5
 * 0 <= u, v < V
 * -10^6 <= w <= 10^6
 * 
 * ==========================================
 * OPTIMAL SOLUTION & APPROACH (Bellman-Ford Algorithm)
 * ==========================================
 * - Approach:
 *   1. Bellman-Ford Algorithm is designed to find shortest paths and detect negative weight cycles in graphs 
 *      with negative edge weights.
 *   2. We initialize a distance array of size `V` with 0s (to handle disconnected graph components properly, 
 *      starting all distances at 0 ensures any vertex can act as a valid source).
 *   3. We relax all edges `V` times:
 *      - In each iteration, we check if `dist[v] > dist[u] + w`. If so, we update `dist[v] = dist[u] + w`.
 *      - If an update occurs on the `V`-th iteration (`i == V - 1`), it guarantees the presence of a 
 *        negative weight cycle, so we return `true`.
 *      - An early stopping optimization is added: if an entire iteration passes with no distance updates (`!updated`), 
 *        the shortest paths have converged, and we can safely return `false`.
 * 
 * - Complexity:
 *   - Time Complexity: O(V * E) in the worst case, but often much faster due to early stopping.
 *   - Space Complexity: O(V) to store the distance array.
 */

class Solution {
    public boolean isNegativeWeightCycle(int V, int[][] edges) {
        // Distance array initialized to 0 to handle disconnected components
        long[] dist = new long[V];

        // Relax all edges V times
        for (int i = 0; i < V; i++) {
            boolean updated = false;

            for (int[] edge : edges) {
                int u = edge[0];
                int v = edge[1];
                int w = edge[2];

                // Relaxation step
                if (dist[v] > dist[u] + w) {
                    dist[v] = dist[u] + w;
                    updated = true;
                    
                    // If a relaxation happens on the V-th iteration, a negative weight cycle exists
                    if (i == V - 1) {
                        return true;
                    }
                }
            }

            // Early stopping optimization: if no updates occurred, distances have converged
            if (!updated) {
                return false;
            }
        }
        
        return false;
    }
}
