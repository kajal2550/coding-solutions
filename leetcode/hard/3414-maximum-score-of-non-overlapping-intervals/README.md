# Maximum Score of Non-overlapping Intervals

![Difficulty](https://img.shields.io/badge/Difficulty-Hard-red)

## Problem

You are given a 2D integer array `intervals`, where `intervals[i] = [li, ri, weighti]`. Interval `i` starts at position `li` and ends at `ri`, and has a weight of `weighti`. You can choose  *up to*  4  **non-overlapping**  intervals. The  **score**  of the chosen intervals is defined as the total sum of their weights.

Return the lexicographically smallest array of at most 4 indices from `intervals` with  **maximum**  score, representing your choice of non-overlapping intervals.

Two intervals are said to be  **non-overlapping**  if they do not share any points. In particular, intervals sharing a left or right boundary are considered overlapping.

 

 **Example 1:** 

 **Input:**  intervals = [[1,3,2],[4,5,2],[1,5,5],[6,9,3],[6,7,1],[8,9,1]]

 **Output:**  [2,3]

 **Explanation:** 

You can choose the intervals with indices 2, and 3 with respective weights of 5, and 3.

 **Example 2:** 

 **Input:**  intervals = [[5,8,1],[6,7,7],[4,7,3],[9,10,6],[7,8,2],[11,14,3],[3,5,5]]

 **Output:**  [1,3,5,6]

 **Explanation:** 

You can choose the intervals with indices 1, 3, 5, and 6 with respective weights of 7, 6, 3, and 5.

 

 **Constraints:** 

- 1 <= intevals.length <= 5 * 104
- intervals[i].length == 3
- intervals[i] = [li, ri, weighti]
- 1 <= li <= ri <= 109
- 1 <= weighti <= 109

## Solution

**Language:** Java  
**Runtime:** 283 ms (beats 9.26%)  
**Memory:** 204.8 MB (beats 25.92%)  
**Submitted:** 2026-09-12T16:37:53.598Z  

```java
class Solution {

    static class Pair {
        long sum;
        List<Integer> ids;

        Pair(long sum, List<Integer> ids) {
            this.sum = sum;
            this.ids = ids;
        }

        Pair copy() {
            return new Pair(sum, new ArrayList<>(ids));
        }
    }

    Pair[][] dp;
    int[] next;

    Pair better(Pair a, Pair b) {
        if (a.sum != b.sum)
            return a.sum > b.sum ? a : b;

        Collections.sort(a.ids);
        Collections.sort(b.ids);

        for (int i = 0; i < Math.min(a.ids.size(), b.ids.size()); i++) {
            if (!a.ids.get(i).equals(b.ids.get(i)))
                return a.ids.get(i) < b.ids.get(i) ? a : b;
        }

        return a.ids.size() <= b.ids.size() ? a : b;
    }

    int lowerBound(List<List<Integer>> in, List<Integer> order, int target) {
        int l = 0, r = order.size();

        while (l < r) {
            int m = l + (r - l) / 2;

            if (in.get(order.get(m)).get(0) >= target)
                r = m;
            else
                l = m + 1;
        }

        return l;
    }

    Pair solve(List<List<Integer>> in, List<Integer> order,
               int pos, int count) {

        if (pos == order.size() || count == 4)
            return new Pair(0, new ArrayList<>());

        if (dp[pos][count] != null)
            return dp[pos][count].copy();

        Pair skip = solve(in, order, pos + 1, count);

        int id = order.get(pos);

        Pair take = solve(
            in,
            order,
            next[pos],
            count + 1
        );

        take.sum += in.get(id).get(2);
        take.ids.add(id);

        dp[pos][count] = better(skip, take);

        return dp[pos][count].copy();
    }

    public int[] maximumWeight(List<List<Integer>> intervals) {
        int n = intervals.size();

        List<Integer> order = new ArrayList<>();

        for (int i = 0; i < n; i++)
            order.add(i);

        order.sort((a, b) ->
            Integer.compare(
                intervals.get(a).get(0),
                intervals.get(b).get(0)
            )
        );

        next = new int[n];

        for (int i = 0; i < n; i++) {
            int id = order.get(i);
            next[i] = lowerBound(
                intervals,
                order,
                intervals.get(id).get(1) + 1
            );
        }

        dp = new Pair[n][4];

        List<Integer> ans = solve(intervals, order, 0, 0).ids;
        Collections.sort(ans);

        int[] res = new int[ans.size()];

        for (int i = 0; i < ans.size(); i++)
            res[i] = ans.get(i);

        return res;
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/maximum-score-of-non-overlapping-intervals/)