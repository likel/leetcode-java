package AddTwoNumbersII;

/**
 * You are given two non-empty linked lists representing two non-negative integers. The most
 * significant digit comes first and each of their nodes contain a single digit. Add the two numbers
 * and return it as a linked list.
 * <p>
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 * <p>
 * Follow up: What if you cannot modify the input lists? In other words, reversing the lists is not
 * allowed.
 * <p>
 * Example:
 * <pre>
 * Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 8 -> 0 -> 7
 * </pre>
 */
class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}

class Solution {

    private int getLength(ListNode listNode) {
        int n = 0;
        while (listNode != null) {
            n++;
            listNode = listNode.next;
        }
        return n;
    }

    private void addNumber(ListNode l, ListNode r) {
        while (l != null && r != null) {
            l.val += r.val;
            l = l.next;
            r = r.next;
        }
    }

    private int fixup(ListNode listNode) {
        if (listNode == null) {
            return 0;
        }
        if (listNode.next == null) {
            int n = listNode.val / 10;
            listNode.val %= 10;
            return n;
        }
        int n = fixup(listNode.next);
        listNode.val += n;
        n = listNode.val / 10;
        listNode.val %= 10;
        return n;
    }

    private ListNode add(ListNode l, int nl, ListNode s, int ns) {
        ListNode p = l;
        int offset = nl - ns;
        while (offset > 0) {
            p = p.next;
            offset--;
        }
        addNumber(p, s);
        int n = fixup(l);
        if (n > 0) {
            ListNode node = new ListNode(n);
            node.next = l;
            return node;
        } else {
            // Remove leading zero
            while (l.val == 0 && l.next != null) {
                l = l.next;
            }
            return l;
        }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null)
            return l2;
        if (l2 == null)
            return l1;
        int len1 = getLength(l1);
        int len2 = getLength(l2);
        if (len1 < len2) {
            return add(l2, len2, l1, len1);
        } else {
            return add(l1, len1, l2, len2);
        }
    }
}