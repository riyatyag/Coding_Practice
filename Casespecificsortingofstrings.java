// Problem Statement:
// Given a string s consisting of only uppercase and lowercase characters. The task is to sort uppercase and lowercase letters separately such that if the ith place in the original string had an Uppercase character then it should not have a lowercase character after being sorted and vice versa.

// Approach:
// The approach is to first separate the uppercase and lowercase characters into two different lists.
// Then, sort both lists independently. After sorting, iterate through the original string.
// If the character at the current position in the original string is uppercase, take the next character from the sorted uppercase list.
// If the character is lowercase, take the next character from the sorted lowercase list.
// Append these characters to a StringBuilder to form the final sorted string.

// Time Complexity:
// The time complexity will be O(N log N) where N is the length of the string.
// This is because we iterate through the string once to separate characters (O(N)),
// then sort two lists (which in the worst case could be O(N log N) if all characters are of the same case),
// and finally iterate through the string again to reconstruct the result (O(N)).
// The dominant factor is the sorting step.

// Space Complexity:
// The space complexity will be O(N) because we are using two lists to store uppercase and lowercase characters separately,
// and in the worst case, these lists can store up to N characters in total.

// Optimal Solution:

import java.util.*;

class Solution {
    public static String caseSort(String s) {
        int n = s.length();
        List<Character> lower = new ArrayList<>();
        List<Character> upper = new ArrayList<>();

        for (char c : s.toCharArray()) {
            if (Character.isLowerCase(c))
                lower.add(c);
            else
                upper.add(c);
        }
        Collections.sort(lower);
        Collections.sort(upper);

        int l = 0, u = 0;
        StringBuilder result = new StringBuilder();

        for (char c : s.toCharArray()) {
            if (Character.isLowerCase(c))
                result.append(lower.get(l++));
            else
                result.append(upper.get(u++));
        }
       return result.toString();
    }
}

        