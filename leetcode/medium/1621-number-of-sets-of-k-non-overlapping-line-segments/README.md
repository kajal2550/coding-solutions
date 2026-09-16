# Number of Sets of K Non-Overlapping Line Segments

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

Given `n` points on a 1-D plane, where the `ith` point (from `0` to `n-1`) is at `x = i`, find the number of ways we can draw  **exactly**  `k`  **non-overlapping**  line segments such that each segment covers two or more points. The endpoints of each segment must have  **integral coordinates**. The `k` line segments  **do not**  have to cover all `n` points, and they are  **allowed**  to share endpoints.

Return  *the number of ways we can draw* `k` *non-overlapping line segments **.*  Since this number can be huge, return it** modulo** `109 + 7`.

 

 **Example 1:** 

```
Input: n = 4, k = 2
Output: 5
Explanation: The two line segments are shown in red and blue.
The image above shows the 5 different ways {(0,2),(2,3)}, {(0,1),(1,3)}, {(0,1),(2,3)}, {(1,2),(2,3)}, {(0,1),(1,2)}.

```

 **Example 2:** 

```
Input: n = 3, k = 1
Output: 3
Explanation: The 3 ways are {(0,1)}, {(0,2)}, {(1,2)}.

```

 **Example 3:** 

```
Input: n = 30, k = 7
Output: 796297179
Explanation: The total number of possible ways to draw 7 line segments is 3796297200. Taking this number modulo 109 + 7 gives us 796297179.

```

 

 **Constraints:** 

- 2 <= n <= 1000
- 1 <= k <= n-1

## Solution

**Language:** Java  
**Runtime:** 0 ms (beats 100.00%)  
**Memory:** 41.9 MB (beats 98.06%)  
**Submitted:** 2026-09-16T03:55:11.857Z  

```java
class Solution {
    private static final long MOD = 1_000_000_007L;

    private long modPow(long base, long exp) {
        long result = 1;

        while (exp > 0) {
            if ((exp & 1L) != 0) {
                result = result * base % MOD;
            }

            base = base * base % MOD;

            exp >>= 1;
        }

        return result;
    }

    public int numberOfSets(int n, int k) {
        long N = n + k - 1L;
        long R = 2L * k;

        R = Math.min(R, N - R);

        long numerator = 1;
        long denominator = 1;

        for (long i = 1; i <= R; i++) {
            numerator = numerator * (N - R + i) % MOD;

            denominator = denominator * i % MOD;
        }

        long inverseDenominator = modPow(denominator, MOD - 2);

        return (int) (numerator * inverseDenominator % MOD);
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/number-of-sets-of-k-non-overlapping-line-segments/)