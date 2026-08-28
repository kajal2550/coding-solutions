# Lexicographically Smallest Palindromic Permutation Greater Than Target

![Difficulty](https://img.shields.io/badge/Difficulty-Hard-red)

## Problem

You are given two strings `s` and `target`, each of length `n`, consisting of lowercase English letters.

Return the  **lexicographically smallest string**  that is  **both**  a  **palindromic permutation**  of `s` and  **strictly**  greater than `target`. If no such permutation exists, return an empty string.

 

 **Example 1:** 

 **Input:**  s = "baba", target = "abba"

 **Output:**  "baab"

 **Explanation:** 

- The palindromic permutations of s (in lexicographical order) are "abba" and "baab".
- The lexicographically smallest permutation that is strictly greater than target is "baab".

 **Example 2:** 

 **Input:**  s = "baba", target = "bbaa"

 **Output:**  ""

 **Explanation:** 

- The palindromic permutations of s (in lexicographical order) are "abba" and "baab".
- None of them is lexicographically strictly greater than target. Therefore, the answer is "".

 **Example 3:** 

 **Input:**  s = "abc", target = "abb"

 **Output:**  ""

 **Explanation:** 

`s` has no palindromic permutations. Therefore, the answer is `""`.

 **Example 4:** 

 **Input:**  s = "aac", target = "abb"

 **Output:**  "aca"

 **Explanation:** 

- The only palindromic permutation of s is "aca".
- "aca" is strictly greater than target. Therefore, the answer is "aca".

 

 **Constraints:** 

- 1 <= n == s.length == target.length <= 300
- s and target consist of only lowercase English letters.

## Solution

**Language:** Java  
**Runtime:** 10 ms (beats 52.38%)  
**Memory:** 52.4 MB (beats 9.52%)  
**Submitted:** 2026-08-28T07:52:19.349Z  

```java
 class Solution {
    public String lexPalindromicPermutation(
        String s,
        String target
    ) {
        int n = s.length();

        int[] freq = new int[26];

        for (char ch : s.toCharArray()) {
            freq[ch - 'a']++;
        }

        String middle = "";

        for (int i = 0; i < 26; i++) {
            if (freq[i] % 2 == 1) {
                if (!middle.isEmpty()) {
                    return "";
                }

                middle = String.valueOf(
                    (char)('a' + i)
                );
            }

            freq[i] /= 2;
        }

        int halfLen = n / 2;

        StringBuilder half =
            new StringBuilder();

        int matched = 0;

        while (matched < halfLen) {
            int c =
                target.charAt(matched) - 'a';

            if (freq[c] == 0) {
                break;
            }

            freq[c]--;
            half.append((char)('a' + c));
            matched++;
        }

        int i = matched;

        while (i >= 0) {
            if (i < halfLen) {
                int start =
                    target.charAt(i) - 'a' + 1;

                for (int c = start; c < 26; c++) {
                    if (freq[c] == 0) {
                        continue;
                    }

                    freq[c]--;

                    StringBuilder suffix = new StringBuilder();

                    for (int j = 0; j < 26; j++) {
                        for (int x = 0; x < freq[j]; x++) {
                            suffix.append((char)('a' + j));
                        }
                    }

                    String left = half.substring(0, i) + (char)('a' + c) + suffix;

                    String candidate = left + middle + new StringBuilder(left).reverse().toString();

                    if (candidate.compareTo(target) > 0) {
                        return candidate;
                    }

                    freq[c]++;
                }
            }

            if (i == halfLen) {
                String left = half.toString();

                String candidate = left + middle + new StringBuilder(left).reverse().toString();

                if (candidate.compareTo(target) > 0) {
                    return candidate;
                }
            }

            i--;

            if (i >= 0) {
                int c = half.charAt(i) - 'a';
                freq[c]++;
                half.deleteCharAt(half.length() - 1);
            }
        }

        return "";
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/lexicographically-smallest-palindromic-permutation-greater-than-target/)