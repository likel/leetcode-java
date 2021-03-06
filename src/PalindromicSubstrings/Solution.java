package PalindromicSubstrings;

/**
 * Given a string, your task is to count how many palindromic substrings in this string.
 *
 * The substrings with different start indexes or end indexes are counted as different substrings
 * even they consist of same characters.
 */
class Solution {

    public int countSubstrings(String s) {
        if (s == null) return 0;

        char[] chars = s.toCharArray();
        int ans = 0;

        for (int i = 0, n = chars.length; i < n; i++) {
            for (int j = i, k = i; j >= 0 && k < n; j--, k++) {
                if (chars[j] != chars[k])
                    break;
                ans++;
            }
            for (int j = i - 1, k = i; j >= 0 && k < n; j--, k++) {
                if (chars[j] != chars[k])
                    break;
                ans++;
            }
        }

        return ans;
    }
}
