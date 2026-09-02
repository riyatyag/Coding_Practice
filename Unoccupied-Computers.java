/*
 * ==========================================
 * PROBLEM STATEMENT: Unoccupied Computers
 * ==========================================
 * A cafe has `n` computers. Customer events are represented by a string `s` where each distinct 
 * letter appears exactly twice (first occurrence = arrival, second occurrence = departure).
 * A customer is assigned a computer only if one is available; otherwise, they are rejected.
 * Return the number of customers who could not be assigned a computer upon arrival.
 * 
 * Constraints:
 * 1 <= n <= 26
 * 1 <= |s| <= 52
 * 
 * ==========================================
 * OPTIMAL SOLUTION & APPROACH (State Tracking Array)
 * ==========================================
 * - Approach:
 *   1. Use a `status` array of size 26 to track the state of each customer character:
 *      - `0`: Not yet arrived.
 *      - `1`: Arrived and successfully assigned a computer.
 *      - `2`: Arrived but rejected due to no available computers.
 *   2. Iterate through each event in the string `s`:
 *      - On arrival (`status[idx] == 0`): Check if `available > 0`. If yes, decrement available computers and mark as `1`. 
 *        If no, increment `rejected` count and mark as `2`.
 *      - On departure (`status[idx] != 0`): If the customer was previously assigned a computer (`status[idx] == 1`), 
 *        free it up by incrementing `available`. If they were rejected (`status[idx] == 2`), do nothing since they never used one.
 * 
 * - Complexity:
 *   - Time Complexity: O(|s|), processing each character of the string once.
 *   - Space Complexity: O(1) extra space for the fixed-size status array of 26 elements.
 */

class Solution {
    public int solve(int n, String s) {
        int[] status = new int[26];
        int available = n;
        int rejected = 0;

        for (char ch : s.toCharArray()) {
            int idx = ch - 'A';

            if (status[idx] == 0) {
                // First occurrence: Customer Arrival
                if (available > 0) {
                    available--;
                    status[idx] = 1; // Assigned a computer
                } 
                else {
                    status[idx] = 2; // Rejected (no computers available)
                    rejected++;
                }
            } 
            else {
                // Second occurrence: Customer Departure
                if (status[idx] == 1) {
                    available++; // Free up the computer if they were using one
                }
            }
        }
        
        return rejected;
    }
}
