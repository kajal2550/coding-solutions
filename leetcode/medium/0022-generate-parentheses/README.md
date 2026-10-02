# Generate Parentheses

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

Given `n` pairs of parentheses, write a function to  *generate all combinations of well-formed parentheses*.

 

 **Example 1:** 

```
Input: n = 3
Output: ["((()))","(()())","(())()","()(())","()()()"]

```

 **Example 2:** 

```
Input: n = 1
Output: ["()"]

```

 

 **Constraints:** 

- 1 <= n <= 8

## Solution

**Language:** Java  
**Runtime:** 1 ms (beats 85.51%)  
**Memory:** 44.3 MB (beats 79.36%)  
**Submitted:** 2026-10-02T06:37:02.079Z  

```java
class Solution {
    private List<String> ans = new ArrayList<>();

    private void backtrack(StringBuilder s, int open, int close, int n) {

        // A complete valid combination is formed
        if (s.length() == 2 * n) {
            ans.add(s.toString());
            return;
        }

        // Add '(' if opening brackets are still available
        if (open < n) {
            s.append('(');

            backtrack(s, open + 1, close, n);

            // Undo the choice
            s.deleteCharAt(s.length() - 1);
        }

        // Add ')' only when it is safe
        if (close < open) {
            s.append(')');

            backtrack(s, open, close + 1, n);

            // Undo the choice
            s.deleteCharAt(s.length() - 1);
        }
    }

    public List<String> generateParenthesis(int n) {
        ans.clear();

        StringBuilder s = new StringBuilder(2 * n);

        backtrack(s, 0, 0, n);

        return ans;
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/generate-parentheses/)