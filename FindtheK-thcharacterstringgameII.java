// Problem Statement:
// Alice and Bob are playing a game. Initially, Alice has a string word = "a".
// You are given a positive integer k. You are also given an integer array operations, where operations[i] represents the type of the ith operation.
// Now Bob will ask Alice to perform all operations in sequence:
// If operations[i] == 0, append a copy of word to itself.
// If operations[i] == 1, generate a new string by changing each character in word to its next character in the English alphabet, and append it to the original word. For example, performing the operation on "c" generates "cd" and performing the operation on "zb" generates "zbac".
// Return the value of the kth character in word after performing all the operations.
// Note that the character 'z' can be changed to 'a' in the second type of operation.

// Approach:
// The key observation is that the length of the string `word` can grow very rapidly, up to 2^100, which is too large to simulate directly.
// We need to find the k-th character without explicitly constructing the string.
// Let `len` be the current length of the string and `offset` be the character offset (how many times characters have been shifted).
// Initially, `word = "a"`, so `len = 1` and `offset = 0`.
// We iterate through the operations in reverse order. This allows us to determine what the k-th character was before the current operation.
// When processing an operation `operations[i]`:
// 1. If `operations[i] == 0`:
//    The string was `S` and became `SS`.
//    If `k` is in the first half (`k <= len / 2`), then the k-th character remains the same as it was in `S`.
//    If `k` is in the second half (`k > len / 2`), then the k-th character corresponds to `k - len / 2` in the original `S`.
//    We update `len = len / 2`.
// 2. If `operations[i] == 1`:
//    The string was `S` and became `S + S'`, where `S'` is `S` with characters shifted.
//    If `k` is in the first half (`k <= len / 2`), then the k-th character remains the same as it was in `S`.
//    If `k` is in the second half (`k > len / 2`), then the k-th character corresponds to `k - len / 2` in `S'`, which means it was `k - len / 2` in `S` but shifted by `offset`. So, we update `k = k - len / 2` and `offset = (offset + 1) % 26`.
//    We update `len = len / 2`.
// After iterating through all operations in reverse, `k` will represent the index in the original "a" string (which is 1-indexed, so `k` should be 1), and `offset` will be the total shift.
// The initial character is 'a'. We apply the final `offset` to 'a' to get the result.

// Time Complexity: O(N), where N is the number of operations.
// We iterate through the operations array once. Each step involves constant time arithmetic operations.
// Space Complexity: O(1).
// We only use a few variables to store `k`, `len`, and `offset`.

class Solution {
  public char kthCharacter(long k, int[] operations) {
     final int operationsCount = (int) Math.ceil(Math.log(k) / Math.log(2));
      int increases = 0;

    for (int i = operationsCount - 1; i >= 0; --i) {
      final long halfSize = 1L << i;
      if (k > halfSize) {
        k -= halfSize;
        increases += operations[i];
      }
    }
     return (char) ('a' + increases % 26);
  }
}