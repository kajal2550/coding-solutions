# N-Queens

![Difficulty](https://img.shields.io/badge/Difficulty-Hard-red)

## Problem

The  **n-queens**  puzzle is the problem of placing `n` queens on an `n x n` chessboard such that no two queens attack each other.

Given an integer `n`, return  *all distinct solutions to the  **n-queens puzzle***. You may return the answer in  **any order**.

Each solution contains a distinct board configuration of the n-queens' placement, where `'Q'` and `'.'` both indicate a queen and an empty space, respectively.

 

 **Example 1:** 

```
Input: n = 4
Output: [[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
Explanation: There exist two distinct solutions to the 4-queens puzzle as shown above

```

 **Example 2:** 

```
Input: n = 1
Output: [["Q"]]

```

 

 **Constraints:** 

- 1 <= n <= 9

## Solution

**Language:** Java  
**Runtime:** 2 ms (beats 80.97%)  
**Memory:** 45.9 MB (beats 96.94%)  
**Submitted:** 2026-11-05T16:42:02.221Z  

```java
// class Solution {
//     public List<List<String>> solveNQueens(int n) {
        
//     }
// }


class Solution {
    public List<List<String>> solveNQueens(int n) {
    List<List<String>> res = new ArrayList<>();
    char[][] board = new char[n][n];
    for (int i = 0; i < n; i++)
    Arrays.fill(board[i], '.');
    backtrack(0, board, res, n);
    return res;
    }

    private void backtrack(int row, char[][] board, List<List<String>> res, int n) {
    if (row == n) {
    res.add(construct(board));
    return;
    }
    for (int col = 0; col < n; col++) {
    if (validate(board, row, col, n)) {
    board[row][col] = 'Q';
    backtrack(row + 1, board, res, n);
    board[row][col] = '.';
    }
    }
    }

    private boolean validate(char[][] board, int row, int col, int n) {
    for (int i = 0; i < row; i++) {
    if (board[i][col] == 'Q') return false;
    }
    for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
    if (board[i][j] == 'Q') return false;
    }
    for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
        if (board[i][j] == 'Q') return false;
        }
        return true;
        }

        private List<String> construct(char[][] board) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
        list.add(new String(board[i]));
        }
        return list;
        }
        }


 


















```

---

[View on LeetCode](https://leetcode.com/problems/n-queens/)