# Minimum Changes to Make K Semi-palindromes

![Difficulty](https://img.shields.io/badge/Difficulty-Hard-red)

## Problem

Given a string `s` and an integer `k`, partition `s` into `k`  **substrings**  such that the letter changes needed to make each substring a  **semi-palindrome**  are minimized.

Return the  ***minimum**  number of letter changes *required*.*

A  **semi-palindrome**  is a special type of string that can be divided into  **palindromes**  based on a repeating pattern. To check if a string is a semi-palindrome:​

- Choose a positive divisor d of the string's length. d can range from 1 up to, but not including, the string's length. For a string of length 1, it does not have a valid divisor as per this definition, since the only divisor is its length, which is not allowed.
- For a given divisor d, divide the string into groups where each group contains characters from the string that follow a repeating pattern of length d. Specifically, the first group consists of characters at positions 1, 1 + d, 1 + 2d, and so on; the second group includes characters at positions 2, 2 + d, 2 + 2d, etc.
- The string is considered a semi-palindrome if each of these groups forms a palindrome.

Consider the string `"abcabc"`:

- The length of "abcabc" is 6. Valid divisors are 1, 2, and 3.
- For d = 1: The entire string "abcabc" forms one group. Not a palindrome.
- For d = 2: Group 1 (positions 1, 3, 5): "acb" Group 2 (positions 2, 4, 6): "bac" Neither group forms a palindrome.
- For d = 3: Group 1 (positions 1, 4): "aa" Group 2 (positions 2, 5): "bb" Group 3 (positions 3, 6): "cc" All groups form palindromes. Therefore, "abcabc" is a semi-palindrome.

 

Original
Java
 **Example 1:** 

 **Input:**  s = "abcac", k = 2

 **Output:**  1

 **Explanation:**  Divide `s` into `"ab"` and `"cac"`. `"cac"` is already semi-palindrome. Change `"ab"` to `"aa"`, it becomes semi-palindrome with `d = 1`.

 **Example 2:** 

 **Input:**  s = "abcdef", k = 2

 **Output:**  2

 **Explanation:**  Divide `s` into substrings `"abc"` and `"def"`. Each needs one change to become semi-palindrome.

 **Example 3:** 

 **Input:**  s = "aabbaa", k = 3

 **Output:**  0

 **Explanation:**  Divide `s` into substrings `"aa"`, `"bb"` and `"aa"`. All are already semi-palindromes.

 

 **Constraints:** 

- 2 <= s.length <= 200
- 1 <= k <= s.length / 2
- s contains only lowercase English letters.

## Solution

**Language:** Java  
**Runtime:** 215 ms (beats 10.00%)  
**Memory:** 46.1 MB (beats 80.00%)  
**Submitted:** 2026-11-17T13:26:26.741Z  

```java
class Solution {
    public int minimumChanges(String s, int k) {
        int n = s.length();
        final int INF = 1000000000;
        int[][] cost = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) cost[i][j] = INF;
        }
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int L = j - i + 1;
                int best = INF;
                for (int d = 1; d < L; d++) {
                    if (L % d != 0) continue;
                    int cur = 0;
                    int m = L / d;
                    for (int r = 0; r < d; r++) {
                        for (int t = 0; t < m / 2; t++) {
                            if (s.charAt(i + r + t * d) != s.charAt(i + r + (m - 1 - t) * d))
                                cur++;
                        }
                    }
                    best = Math.min(best, cur);
                }
                cost[i][j] = best;
            }
        }
        int[][] dp = new int[n + 1][k + 1];
        for (int i = 0; i <= n; i++) {
            for (int p = 0; p <= k; p++) dp[i][p] = INF;
        }
        dp[0][0] = 0;
        for (int i = 1; i <= n; i++) {
            for (int p = 1; p <= k; p++) {
                for (int prev = 0; prev <= i - 2; prev++) {
                    if (dp[prev][p - 1] != INF && cost[prev][i - 1] != INF)
                        dp[i][p] = Math.min(dp[i][p], dp[prev][p - 1] + cost[prev][i - 1]);
                }
            }
        }
        return dp[n][k];
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/minimum-changes-to-make-k-semi-palindromes/)