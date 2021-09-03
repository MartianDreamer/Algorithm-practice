package leetcode.remove_nth_node_from_the_end;

public class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) {
            return null;
        }
        int count = 1;
        ListNode traversal = head;
        while (traversal.next != null) {
            count++;
            traversal = traversal.next;
        }
        traversal = null;
        for (int i = 0; i < count - n; i++) {
            if (traversal == null) {
                traversal = head;
            } else {
                traversal = traversal.next;
            }
        }
        if (traversal == null) {
            return head.next;
        }
        ListNode result = traversal.next;
        traversal.next = traversal.next.next;
        result.next = null;
        return head;
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
