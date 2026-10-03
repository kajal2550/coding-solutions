# Longest Valid Parentheses

![Difficulty](https://img.shields.io/badge/Difficulty-Hard-red)

## Problem

Given a string containing just the characters `'('` and `')'`, return  *the length of the longest valid (well-formed) parentheses **substring*.

 

 **Example 1:** 

```
Input: s = "(()"
Output: 2
Explanation: The longest valid parentheses substring is "()".

```

 **Example 2:** 

```
Input: s = ")()())"
Output: 4
Explanation: The longest valid parentheses substring is "()()".

```

 **Example 3:** 

```
Input: s = ""
Output: 0

```

 

 **Constraints:** 

- 0 <= s.length <= 3 * 104
- s[i] is '(', or ')'.

## Solution

**Language:** Java  
**Runtime:** 2 ms (beats 96.14%)  
**Memory:** 44.7 MB (beats 88.63%)  
**Submitted:** 2026-10-03T05:17:07.617Z  

```java
class Solution {
    public int longestValidParentheses(String s) {
        int answer = 0;
        int open = 0, close = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') open++;
            else close++;

            if (open == close) {
                answer = Math.max(answer, 2 * close);
            } else if (close > open) {
                open = close = 0;
            }
        }

        open = close = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '(') open++;
            else close++;

            if (open == close) {
                answer = Math.max(answer, 2 * open);
            } else if (open > close) {
                open = close = 0;
            }
        }

        return answer;
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/longest-valid-parentheses/)