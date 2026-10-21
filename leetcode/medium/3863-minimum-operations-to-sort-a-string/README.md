# Minimum Operations to Sort a String

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

You are given a string `s` consisting of lowercase English letters.

In one operation, you can select any  **substring**  of `s` that is  **not**  the entire string and  **sort**  it in  **non-descending alphabetical**  order.

Return the  **minimum**  number of operations required to make `s` sorted in  **non-descending**  order. If it is not possible, return -1.

 

 **Example 1:** 

 **Input:**  s = "dog"

 **Output:**  1

 **Explanation:** ​​​​​​​

- Sort substring "og" to "go".
- Now, s = "dgo", which is sorted in ascending order. Thus, the answer is 1.

 **Example 2:** 

 **Input:**  s = "card"

 **Output:**  2

 **Explanation:** 

- Sort substring "car" to "acr", so s = "acrd".
- Sort substring "rd" to "dr", making s = "acdr", which is sorted in ascending order. Thus, the answer is 2.

 **Example 3:** 

 **Input:**  s = "gf"

 **Output:**  -1

 **Explanation:** 

- It is impossible to sort s under the given constraints. Thus, the answer is -1.

 

 **Constraints:** 

- 1 <= s.length <= 105
- s consists of only lowercase English letters.

## Solution

**Language:** Java  
**Runtime:** 117 ms (beats 5.88%)  
**Memory:** 47.7 MB (beats 86.76%)  
**Submitted:** 2026-10-21T14:10:55.566Z  

```java
 import java.util.*;

class Solution {
    public int minOperations(String s) {
        int n = s.length();
        if (IntStream.range(1, n).allMatch(i -> s.charAt(i - 1) <= s.charAt(i))) return 0;  // already sorted
        if (n == 2) return -1;  // only single letters can be sorted, which changes nothing
        IntSummaryStatistics mid = s.chars().skip(1).limit(n - 2).summaryStatistics();  // the middle's smallest and largest letters
        int first = s.charAt(0), last = s.charAt(n - 1), lo = mid.getMin(), hi = mid.getMax();
        if (first <= Math.min(lo, last) || Math.max(first, hi) <= last) return 1;  // the first letter is the smallest, or the last the largest
        return last < lo && hi < first ? 3 : 2;  // the middle sits strictly between the last letter and the first
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/minimum-operations-to-sort-a-string/)