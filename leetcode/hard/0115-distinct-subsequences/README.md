# Distinct Subsequences

![Difficulty](https://img.shields.io/badge/Difficulty-Hard-red)

## Problem

Given two strings s and t, return  *the number of distinct*   ***subsequences** ** of  *s*  which equals *t.

The test cases are generated so that the answer fits on a 32-bit signed integer.

 

 **Example 1:** 

```
Input: s = "rabbbit", t = "rabbit"
Output: 3
Explanation:
As shown below, there are 3 ways you can generate "rabbit" from s.
rabbbit
rabbbit
rabbbit

```

 **Example 2:** 

```
Input: s = "babgbag", t = "bag"
Output: 5
Explanation:
As shown below, there are 5 ways you can generate "bag" from s.
babgbag
babgbag
babgbag
babgbag
babgbag
```

 

 **Constraints:** 

- 1 <= s.length, t.length <= 1000
- s and t consist of English letters.

## Solution

**Language:** Java  
**Runtime:** 9 ms (beats 95.78%)  
**Memory:** 42.7 MB (beats 95.03%)  
**Submitted:** 2026-09-06T04:23:22.009Z  

```java
 class Solution {
    public int numDistinct(String s, String t) {
        int m = t.length();

        long[] dp = new long[m + 1];
        dp[0] = 1;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            for (int j = m; j >= 1; j--) {
                if (c == t.charAt(j - 1)) {
                    dp[j] += dp[j - 1];
                }
            }
        }

        return (int) dp[m];
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/distinct-subsequences/)