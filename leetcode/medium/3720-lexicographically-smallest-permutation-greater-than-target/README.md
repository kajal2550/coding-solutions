# Lexicographically Smallest Permutation Greater Than Target

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

You are given two strings `s` and `target`, both having length `n`, consisting of lowercase English letters.

Return the  **lexicographically smallest permutation**  of `s` that is  **strictly**  greater than `target`. If no permutation of `s` is lexicographically strictly greater than `target`, return an empty string.

A string `a` is  **lexicographically strictly greater** than a string `b` (of the same length) if in the first position where `a` and `b` differ, string `a` has a letter that appears later in the alphabet than the corresponding letter in `b`.

 

 **Example 1:** 

 **Input:**  s = "abc", target = "bba"

 **Output:**  "bca"

 **Explanation:** 

- The permutations of s (in lexicographical order) are "abc", "acb", "bac", "bca", "cab", and "cba".
- The lexicographically smallest permutation that is strictly greater than target is "bca".

 **Example 2:** 

 **Input:**  s = "leet", target = "code"

 **Output:**  "eelt"

 **Explanation:** 

- The permutations of s (in lexicographical order) are "eelt", "eetl", "elet", "elte", "etel", "etle", "leet", "lete", "ltee", "teel", "tele", and "tlee".
- The lexicographically smallest permutation that is strictly greater than target is "eelt".

 **Example 3:** 

 **Input:**  s = "baba", target = "bbaa"

 **Output:**  ""

 **Explanation:** 

- The permutations of s (in lexicographical order) are "aabb", "abab", "abba", "baab", "baba", and "bbaa".
- None of them is lexicographically strictly greater than target. Therefore, the answer is "".

 

 **Constraints:** 

- 1 <= s.length == target.length <= 300
- s and target consist of only lowercase English letters.

## Solution

**Language:** Java  
**Runtime:** 37 ms (beats 6.45%)  
**Memory:** 47.7 MB (beats 6.45%)  
**Submitted:** 2026-08-27T01:33:38.824Z  

```java
 import java.util.HashMap;
import java.util.Map;

class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int n = s.length();
        Map<Character, Integer> sCounts = new HashMap<>();
        for (char c : s.toCharArray()) {
            sCounts.put(c, sCounts.getOrDefault(c, 0) + 1);
        }

        String bestSolution = "";
        Map<Character, Integer> prefixCounts = new HashMap<>();

        for (int i = 0; i < n; i++) {
            // Create a copy of counts available for suffix and pivot
            Map<Character, Integer> availableCounts = new HashMap<>(sCounts);
            for (Map.Entry<Character, Integer> entry : prefixCounts.entrySet()) {
                availableCounts.put(entry.getKey(), availableCounts.getOrDefault(entry.getKey(), 0) - entry.getValue());
            }

            // Find the smallest char > target[i] to use as the pivot
            for (char pivotChar = (char) (target.charAt(i) + 1); pivotChar <= 'z'; pivotChar++) {
                if (availableCounts.getOrDefault(pivotChar, 0) > 0) {
                    // We found a valid pivot character
                    availableCounts.put(pivotChar, availableCounts.get(pivotChar) - 1);

                    String currentPrefix = target.substring(0, i);
                    
                    // Build the smallest possible suffix from remaining characters
                    StringBuilder suffix = new StringBuilder();
                    for (char k = 'a'; k <= 'z'; k++) {
                        if (availableCounts.getOrDefault(k, 0) > 0) {
                            int count = availableCounts.get(k);
                            for(int j = 0; j < count; j++) {
                                suffix.append(k);
                            }
                        }
                    }

                    String candidate = currentPrefix + pivotChar + suffix.toString();
                    if (bestSolution.isEmpty() || candidate.compareTo(bestSolution) < 0) {
                        bestSolution = candidate;
                    }

                    // Since we want the smallest pivotChar, we break after finding one
                    break;
                }
            }

            // Update prefix_counts for the next iteration
            char targetChar = target.charAt(i);
            prefixCounts.put(targetChar, prefixCounts.getOrDefault(targetChar, 0) + 1);
            if (prefixCounts.get(targetChar) > sCounts.getOrDefault(targetChar, 0)) {
                // We can't match the target's prefix any further, so stop.
                break;
            }
        }

        return bestSolution;
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/lexicographically-smallest-permutation-greater-than-target/)