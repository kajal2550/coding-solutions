# Kth Smallest Amount With Single Denomination Combination

![Difficulty](https://img.shields.io/badge/Difficulty-Hard-red)

## Problem

You are given an integer array `coins` representing coins of different denominations and an integer `k`.

You have an infinite number of coins of each denomination. However, you are  **not allowed**  to combine coins of different denominations.

Return the `kth`  **smallest**  amount that can be made using these coins.

 

 **Example 1:** 

 **Input:**  coins = [3,6,9], k = 3

 **Output:**  9

 **Explanation:**  The given coins can make the following amounts:
Coin 3 produces multiples of 3: 3, 6, 9, 12, 15, etc.
Coin 6 produces multiples of 6: 6, 12, 18, 24, etc.
Coin 9 produces multiples of 9: 9, 18, 27, 36, etc.
All of the coins combined produce: 3, 6,  **9**, 12, 15, etc.

 **Example 2:** 

 **Input:**  coins = [5,2], k = 7

 **Output:**  12

 **Explanation:**  The given coins can make the following amounts:
Coin 5 produces multiples of 5: 5, 10, 15, 20, etc.
Coin 2 produces multiples of 2: 2, 4, 6, 8, 10, 12, etc.
All of the coins combined produce: 2, 4, 5, 6, 8, 10,  **12**, 14, 15, etc.

 

 **Constraints:** 

- 1 <= coins.length <= 15
- 1 <= coins[i] <= 25
- 1 <= k <= 2 * 109
- coins contains pairwise distinct integers.

## Solution

**Language:** Java  
**Runtime:** 5 ms (beats 100.00%)  
**Memory:** 43.8 MB (beats 70.59%)  
**Submitted:** 2026-08-21T12:58:16.847Z  

```java
 import java.util.*;

class Solution {
    public long findKthSmallest(int[] coins, int k) {
        Arrays.sort(coins);

        List<Long> usefulList = new ArrayList<>();

        for (int coin : coins) {
            boolean redundant = false;

            for (long prev : usefulList) {
                if (coin % prev == 0) {
                    redundant = true;
                    break;
                }
            }

            if (!redundant) {
                usefulList.add((long) coin);
            }
        }

        int m = usefulList.size();

        long[] useful = new long[m];
        for (int i = 0; i < m; i++) {
            useful[i] = usefulList.get(i);
        }

        long low = 1;
        long high = useful[0] * k;

        int totalMasks = 1 << m;

        long[] lcms = new long[totalMasks];

        int[] signs = new int[totalMasks];

        for (int mask = 1; mask < totalMasks; mask++) {
            long currentLCM = 1;
            int bits = 0;

            for (int i = 0; i < m; i++) {
                if ((mask & (1 << i)) != 0) {
                    long g = gcd(currentLCM, useful[i]);

                    currentLCM /= g;

                    if (currentLCM > high / useful[i]) {
                        currentLCM = high + 1;
                        break;
                    }

                    currentLCM *= useful[i];
                    bits++;
                }
            }

            lcms[mask] = currentLCM;

            signs[mask] = (bits % 2 == 1) ? 1 : -1;
        }

        while (low < high) {
            long mid = low + (high - low) / 2;
            long count = 0;

            for (int mask = 1; mask < totalMasks; mask++) {
                if (lcms[mask] <= mid) {
                    count += signs[mask] * (mid / lcms[mask]);
                }
            }

            if (count >= k) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        return low;
    }

    private long gcd(long a, long b) {
        while (b != 0) {
            long temp = a % b;
            a = b;
            b = temp;
        }

        return a;
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/kth-smallest-amount-with-single-denomination-combination/)