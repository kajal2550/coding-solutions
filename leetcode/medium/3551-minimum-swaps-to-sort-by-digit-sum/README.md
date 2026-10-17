# Minimum Swaps to Sort by Digit Sum

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

You are given an array `nums` of  **distinct**  positive integers. You need to sort the array in  **increasing**  order based on the sum of the digits of each number. If two numbers have the same digit sum, the  **smaller**  number appears first in the sorted order.

Return the  **minimum**  number of swaps required to rearrange `nums` into this sorted order.

A  **swap**  is defined as exchanging the values at two distinct positions in the array.

 

 **Example 1:** 

 **Input:**  nums = [37,100]

 **Output:**  1

 **Explanation:** 

- Compute the digit sum for each integer: [3 + 7 = 10, 1 + 0 + 0 = 1] → [10, 1]
- Sort the integers based on digit sum: [100, 37]. Swap 37 with 100 to obtain the sorted order.
- Thus, the minimum number of swaps required to rearrange nums is 1.

 **Example 2:** 

 **Input:**  nums = [22,14,33,7]

 **Output:**  0

 **Explanation:** 

- Compute the digit sum for each integer: [2 + 2 = 4, 1 + 4 = 5, 3 + 3 = 6, 7 = 7] → [4, 5, 6, 7]
- Sort the integers based on digit sum: [22, 14, 33, 7]. The array is already sorted.
- Thus, the minimum number of swaps required to rearrange nums is 0.

 **Example 3:** 

 **Input:**  nums = [18,43,34,16]

 **Output:**  2

 **Explanation:** 

- Compute the digit sum for each integer: [1 + 8 = 9, 4 + 3 = 7, 3 + 4 = 7, 1 + 6 = 7] → [9, 7, 7, 7]
- Sort the integers based on digit sum: [16, 34, 43, 18]. Swap 18 with 16, and swap 43 with 34 to obtain the sorted order.
- Thus, the minimum number of swaps required to rearrange nums is 2.

 

 **Constraints:** 

- 1 <= nums.length <= 105
- 1 <= nums[i] <= 109
- nums consists of distinct positive integers.

## Solution

**Language:** Java  
**Runtime:** 187 ms (beats 100.00%)  
**Memory:** 169.5 MB (beats 34.90%)  
**Submitted:** 2026-10-17T12:57:26.849Z  

```java
class Solution {
    public int minSwaps(int[] nums) {
        int n = nums.length;
        long[] keyed = new long[n];  // digit sum in the high bits, value in the low bits
        for (int i = 0; i < n; i++) keyed[i] = (long) digitSum(nums[i]) << 32 | nums[i];
        Arrays.sort(keyed);
        Map<Integer, Integer> rank = new HashMap<>();  // final position of each value
        for (int k = 0; k < n; k++) rank.put((int) keyed[k], k);
        int[] home = new int[n];  // where the value at each position must go
        for (int i = 0; i < n; i++) home[i] = rank.get(nums[i]);

        int swaps = 0;
        for (int i = 0; i < n; i++) {
            while (home[i] != i) {  // send the value at i home, take that spot's value in exchange
                int j = home[i];
                home[i] = home[j];
                home[j] = j;
                swaps++;
            }
        }
        return swaps;
    }

    private int digitSum(int x) {
        int s = 0;
        for (; x > 0; x /= 10) s += x % 10;
        return s;
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/minimum-swaps-to-sort-by-digit-sum/)