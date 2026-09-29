# Check if There Is a Valid Parentheses String Path

![Difficulty](https://img.shields.io/badge/Difficulty-Hard-red)

## Problem

A parentheses string is a  **non-empty**  string consisting only of `'('` and `')'`. It is  **valid**  if  **any**  of the following conditions is  **true** :

- It is ().
- It can be written as AB (A concatenated with B), where A and B are valid parentheses strings.
- It can be written as (A), where A is a valid parentheses string.

You are given an `m x n` matrix of parentheses `grid`. A  **valid parentheses string path**  in the grid is a path satisfying  **all**  of the following conditions:

- The path starts from the upper left cell (0, 0).
- The path ends at the bottom-right cell (m - 1, n - 1).
- The path only ever moves down or right.
- The resulting parentheses string formed by the path is valid.

Return `true`  *if there exists a  **valid parentheses string path**  in the grid.*  Otherwise, return `false`.

 

 **Example 1:** 

```
Input: grid = [["(","(","("],[")","(",")"],["(","(",")"],["(","(",")"]]
Output: true
Explanation: The above diagram shows two possible paths that form valid parentheses strings.
The first path shown results in the valid parentheses string "()(())".
The second path shown results in the valid parentheses string "((()))".
Note that there may be other valid parentheses string paths.

```

 **Example 2:** 

```
Input: grid = [[")",")"],["(","("]]
Output: false
Explanation: The two possible paths form the parentheses strings "))(" and ")((". Since neither of them are valid parentheses strings, we return false.

```

 

 **Constraints:** 

- m == grid.length
- n == grid[i].length
- 1 <= m, n <= 100
- grid[i][j] is either '(' or ')'.

## Solution

**Language:** Java  
**Runtime:** 30 ms (beats 73.33%)  
**Memory:** 281.9 MB (beats 5.71%)  
**Submitted:** 2026-09-29T14:27:39.063Z  

```java
class Solution {
    static Boolean[][][] memo;

    public boolean hasValidPath(char[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        if (grid[0][0] == ')' || grid[rows - 1][cols - 1] == '(') {
            return false;
        }

        if ((rows + cols - 1) % 2 != 0) {
            return false;
        }

        memo = new Boolean[101][101][201];

        return search(grid, 0, 0, 0);
    }

    private boolean search(char[][] grid, int row, int col, int balance) {
        if (grid[row][col] == '(') {
            balance++;
        } else {
            balance--;
        }

        if (balance < 0) {
            return false;
        }

        if (row == grid.length - 1 && col == grid[0].length - 1) {
            return balance == 0;
        }

        if (memo[row][col][balance] != null) {
            return memo[row][col][balance];
        }

        boolean canFormValidPath = false;

        if (row + 1 < grid.length) {
            canFormValidPath = search(grid, row + 1, col, balance);
        }

        if (!canFormValidPath && col + 1 < grid[0].length) {
            canFormValidPath = search(grid, row, col + 1, balance);
        }

        return memo[row][col][balance] = canFormValidPath;
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/check-if-there-is-a-valid-parentheses-string-path/)