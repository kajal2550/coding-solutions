# Sum of Sortable Integers

![Difficulty](https://img.shields.io/badge/Difficulty-Hard-red)

## Problem

You are given an integer array `nums` of length `n`.

An integer `k` is called  **sortable**  if `k`  **divides**  `n` and you can sort `nums` in  **non-decreasing**  order by sequentially performing the following operations:

- Partition nums into consecutive subarrays of length k.
- Cyclically rotate each subarray independently any number of times to the left or to the right.

Return an integer denoting the sum of all possible sortable integers `k`.

 

 **Example 1:** 

 **Input:**  nums = [3,1,2]

 **Output:**  3

 **Explanation:** ​​​​​​​

- For n = 3, possible divisors are 1 and 3.
- For k = 1: each subarray has one element. No rotation can sort the array.
- For k = 3: the single subarray [3, 1, 2] can be rotated once to produce [1, 2, 3], which is sorted.
- Only k = 3 is sortable. Hence, the answer is 3.

 **Example 2:** 

 **Input:**  nums = [7,6,5]

 **Output:**  0

 **Explanation:** 

- For n = 3, possible divisors are 1 and 3.
- For k = 1: each subarray has one element. No rotation can sort the array.
- For k = 3: the single subarray [7, 6, 5] cannot be rotated into non-decreasing order.
- No k is sortable. Hence, the answer is 0.

 **Example 3:** 

 **Input:**  nums = [5,8]

 **Output:**  3

 **Explanation:** ​​​​​​​

- For n = 2, possible divisors are 1 and 2.
- Since [5, 8] is already sorted, every divisor is sortable. Hence, the answer is 1 + 2 = 3.

 

 **Constraints:** 

- 1 <= n == nums.length <= 105
- 1 <= nums[i] <= 105

## Solution

**Language:** Java  
**Runtime:** 65 ms (beats 50.00%)  
**Memory:** 80.5 MB (beats 6.82%)  
**Submitted:** 2026-10-20T14:18:59.666Z  

```java
class Solution {
    public int sortableIntegers(int[] nums) {
        int n = nums.length;

        int[] desc = new int[n];  // desc[i]: number of descents nums[j] > nums[j + 1] with j < i
        for (int i = 1; i < n; i++) desc[i] = desc[i - 1] + (nums[i - 1] > nums[i] ? 1 : 0);
        int[] premax = nums.clone(), sufmin = nums.clone();
        Arrays.parallelPrefix(premax, Math::max);  // premax[i]: max of nums[0..i]
        for (int i = n - 2; i >= 0; i--) sufmin[i] = Math.min(sufmin[i], sufmin[i + 1]);  // sufmin[i]: min of nums[i..n-1]
        boolean[] cut = new boolean[n];  // cut[m]: nums[0..m-1] holds exactly the m smallest values
        cut[0] = true;
        for (int m = 1; m < n; m++) cut[m] = premax[m - 1] <= sufmin[m];

        BiPredicate<Integer, Integer> rotatable = (l, r) ->  // nums[l..r] is a rotated sorted run: at most one cyclic descent
            desc[r] - desc[l] + (nums[r] > nums[l] ? 1 : 0) <= 1;
        IntPredicate sortable = k ->  // every block starts at a valid cut and can be rotated into order
            IntStream.iterate(0, l -> l < n, l -> l + k).allMatch(l -> cut[l] && rotatable.test(l, l + k - 1));

        IntStream divisors = IntStream.rangeClosed(1, n).filter(k -> n % k == 0);
        return divisors.filter(sortable).sum();
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/sum-of-sortable-integers/)