# Cinema Seat Allocation

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

A cinema has `n` rows of seats, numbered from 1 to `n`. Each row has 10 seats, numbered from 1 to 10.

You are given a 2D integer array `reservedSeats`, where `reservedSeats[i] = [rowi, seati]` means that seat `seati` in row `rowi` is already reserved.

A four-person group must be assigned to four seats in the  **same**  row. The group can be seated in one of the following seat blocks:

- seats 2, 3, 4, 5
- seats 4, 5, 6, 7
- seats 6, 7, 8, 9

A block can be used only if  **none**  of its seats are reserved. Each seat can be assigned to  **at most** one group.

Return an integer denoting the  **maximum**  number of four-person groups that can be assigned.

 

 **Example 1:** 

```
Input: n = 3, reservedSeats = [[1,2],[1,3],[1,8],[2,6],[3,1],[3,10]]
Output: 4
Explanation: The figure above shows an optimal allocation of four groups. Seats marked in blue are already reserved, and each set of four contiguous seats marked in orange is assigned to one group.

```

 **Example 2:** 

```
Input: n = 2, reservedSeats = [[2,1],[1,8],[2,6]]
Output: 2

```

 **Example 3:** 

```
Input: n = 4, reservedSeats = [[4,3],[1,4],[4,6],[1,7]]
Output: 4

```

 

 **Constraints:** 

- 1 <= n <= 109
- 1 <= reservedSeats.length <= min(10 * n, 104)
- reservedSeats[i] == [rowi, seati]
- 1 <= rowi <= n
- 1 <= seati <= 10
- All reservedSeats[i] are distinct.

## Solution

**Language:** Java  
**Runtime:** 17 ms (beats 91.32%)  
**Memory:** 53.1 MB (beats 43.16%)  
**Submitted:** 2026-08-19T05:14:03.338Z  

```java
class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        Map<Integer, Integer> rows = new HashMap<>();

        for (int[] r : reservedSeats) {
            int row = r[0];
            int seat = r[1];

            if (seat == 1 || seat == 10) {
                continue;
            }

            int mask = rows.getOrDefault(row, 0);
            mask |= 1 << seat;
            rows.put(row, mask);
        }

        int total = (n - rows.size()) * 2;

        int leftMask = 0;
        int middleMask = 0;
        int rightMask = 0;

        for (int seat = 2; seat <= 5; seat++) {
            leftMask |= 1 << seat;
        }

        for (int seat = 4; seat <= 7; seat++) {
            middleMask |= 1 << seat;
        }

        for (int seat = 6; seat <= 9; seat++) {
            rightMask |= 1 << seat;
        }

        for (int mask : rows.values()) {
            boolean leftFree = (mask & leftMask) == 0;
            boolean middleFree = (mask & middleMask) == 0;
            boolean rightFree = (mask & rightMask) == 0;

            if (leftFree && rightFree) {
                total += 2;
            } else if (leftFree || middleFree || rightFree) {
                total += 1;
            }
        }

        return total;
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/cinema-seat-allocation/)