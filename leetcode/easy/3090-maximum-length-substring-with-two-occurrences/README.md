# Maximum Length Substring With Two Occurrences

![Difficulty](https://img.shields.io/badge/Difficulty-Easy-green)

## Problem

Given a string `s`, return the  **maximum**  length of a substring such that it contains  *at most two occurrences*  of each character.

 

 **Example 1:** 

 **Input:**  s = "bcbbbcba"

 **Output:**  4

 **Explanation:** 

The following substring has a length of 4 and contains at most two occurrences of each character: `"bcbbbcba"`.

 **Example 2:** 

 **Input:**  s = "aaaa"

 **Output:**  2

 **Explanation:** 

The following substring has a length of 2 and contains at most two occurrences of each character: `"aaaa"`.

 

 **Constraints:** 

- 2 <= s.length <= 100
- s consists only of lowercase English letters.

## Solution

**Language:** Java  
**Runtime:** 2 ms (beats 54.50%)  
**Memory:** 45 MB (beats 15.21%)  
**Submitted:** 2026-08-14T03:24:49.578Z  

```java
class Solution {

    public int maximumLengthSubstring(String s) {
        int n = s.length();
        int res = 0;
        for (int left = 0; left < n; left++) {
            int[] count = new int[26];
            for (int right = left; right < n; right++) {
                int ch = s.charAt(right) - 'a';
                count[ch]++;
                if (count[ch] > 2) {
                    break;
                }
                res = Math.max(res, right - left + 1);
            }
        }
        return res;
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/maximum-length-substring-with-two-occurrences/)