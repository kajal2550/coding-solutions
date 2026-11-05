# Zigzag Conversion

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

The string `"PAYPALISHIRING"` is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)

```
P   A   H   N
A P L S I I G
Y   I   R

```

And then read line by line: `"PAHNAPLSIIGYIR"`

Write the code that will take a string and make this conversion given a number of rows:

```
string convert(string s, int numRows);

```

 

 **Example 1:** 

```
Input: s = "PAYPALISHIRING", numRows = 3
Output: "PAHNAPLSIIGYIR"

```

 **Example 2:** 

```
Input: s = "PAYPALISHIRING", numRows = 4
Output: "PINALSIGYAHRPI"
Explanation:
P     I    N
A   L S  I G
Y A   H R
P     I

```

 **Example 3:** 

```
Input: s = "A", numRows = 1
Output: "A"

```

 

 **Constraints:** 

- 1 <= s.length <= 1000
- s consists of English letters (lower-case and upper-case), ',' and '.'.
- 1 <= numRows <= 1000

## Solution

**Language:** Java  
**Runtime:** 6 ms (beats 43.78%)  
**Memory:** 46.2 MB (beats 82.89%)  
**Submitted:** 2026-11-05T16:18:18.137Z  

```java
// class Solution {
//     public String convert(String s, int numRows) {
        
//     }
// }

 
 class Solution {
        public String convert(String s, int numRows) {
                    if (numRows == 1 || numRows >= s.length()) {
                                    return s;
                                            }
                                                    StringBuilder[] rows = new StringBuilder[numRows];
                                                            for (int i = 0; i < numRows; i++) {
                                                                            rows[i] = new StringBuilder();
                                                                                    }
                                                                                            int currentRow = 0;
                                                                                                    boolean goingDown = false;
                                                                                                            for (char c : s.toCharArray()) {
                                                                                                                            rows[currentRow].append(c);
                                                                                                                                        if (currentRow == 0 || currentRow == numRows - 1) {
                                                                                                                                                        goingDown = !goingDown;
                                                                                                                                        }
                                                                                                                                                    currentRow += goingDown ? 1 : -1;
                                                                                                                                                            }
                                                                                                                                                                    StringBuilder result = new StringBuilder();
                                                                                                                                                                            for (StringBuilder row : rows) {
                                                                                                                                                                                        result.append(row);
                                                                                                                                                                                                }
                                                                                                                                                                                                        return result.toString();
                                                                                                                                                                                                            }
                                                                                                                                                                                                            }





//                                                                                                             }
//                                                             }
//                     }
//         }
//  }
```

---

[View on LeetCode](https://leetcode.com/problems/zigzag-conversion/)