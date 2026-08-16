# Reverse Linked List II

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

Given the `head` of a singly linked list and two integers `left` and `right` where `left <= right`, reverse the nodes of the list from position `left` to position `right`, and return  *the reversed list*.

 

 **Example 1:** 

```
Input: head = [1,2,3,4,5], left = 2, right = 4
Output: [1,4,3,2,5]

```

 **Example 2:** 

```
Input: head = [5], left = 1, right = 1
Output: [5]

```

 

 **Constraints:** 

- The number of nodes in the list is n.
- 1 <= n <= 500
- -500 <= Node.val <= 500
- 1 <= left <= right <= n

 

 **Follow up:**  Could you do it in one pass?

## Solution

**Language:** Java  
**Runtime:** 0 ms (beats 100.00%)  
**Memory:** 43.1 MB (beats 29.50%)  
**Submitted:** 2026-08-16T17:26:02.253Z  

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode reverseBetween(ListNode head, int left, int right) {

                if (head == null || left == right) return head;
                        ListNode dummy = new ListNode(0);
                                dummy.next = head;
                                        ListNode prev = dummy;
                                                for (int i = 0; i < left - 1; i++) {
                                                            prev = prev.next;
                                                                    }
                                                                            ListNode curr = prev.next;
                                                                                    for (int i = 0; i < right - left; i++) {
                                                                                                ListNode temp = curr.next;
                                                                                                            curr.next = temp.next;
                                                                                                                        temp.next = prev.next;
                                                                                                                                    prev.next = temp;
                                                                                                                                            }
                                                                                                                                                    return dummy.next;


        
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/reverse-linked-list-ii/)