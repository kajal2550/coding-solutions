# Remove Invalid Parentheses

![Difficulty](https://img.shields.io/badge/Difficulty-Hard-red)

## Problem

Given a string `s` that contains parentheses and letters, remove the minimum number of invalid parentheses to make the input string valid.

Return  *a list of  **unique strings**  that are valid with the minimum number of removals*. You may return the answer in  **any order**.

 

 **Example 1:** 

```
Input: s = "()())()"
Output: ["(())()","()()()"]

```

 **Example 2:** 

```
Input: s = "(a)())()"
Output: ["(a())()","(a)()()"]

```

 **Example 3:** 

```
Input: s = ")("
Output: [""]

```

 

 **Constraints:** 

- 1 <= s.length <= 25
- s consists of lowercase English letters and parentheses '(' and ')'.
- There will be at most 20 parentheses in s.

## Solution

**Language:** Java  
**Runtime:** 1 ms (beats 99.87%)  
**Memory:** 43.9 MB (beats 80.33%)  
**Submitted:** 2026-10-07T16:55:15.286Z  

```java
class Solution {
    public List<String> removeInvalidParentheses(String s) {
        List<String> answers = new ArrayList<>();
        remove(s, 0, 0, '(', ')', answers);
        return answers;
    }

    private void remove(
            String s,
            int scanStart,
            int deleteStart,
            char open,
            char close,
            List<String> answers) {
        int balance = 0;

        for (int i = scanStart; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == open) {
                balance++;
            } else if (c == close) {
                balance--;
            }

            if (balance >= 0) {
                continue;
            }

            for (int j = deleteStart; j <= i; j++) {
                if (s.charAt(j) == close
                        && (j == deleteStart
                                || s.charAt(j - 1) != close)) {
                    remove(
                            s.substring(0, j) + s.substring(j + 1),
                            i, j, open, close, answers);
                }
            }

            return;
        }

        String reversed = new StringBuilder(s).reverse().toString();

        if (open == '(') {
            remove(reversed, 0, 0, ')', '(', answers);
        } else {
            answers.add(reversed);
        }
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/remove-invalid-parentheses/)