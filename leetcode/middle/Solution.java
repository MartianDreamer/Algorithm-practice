package leetcode.middle;

class Solution {
    public ListNode middleNode(ListNode head) {
        if (head == null) {
            return null;
        }
        int count = 1;
        ListNode traversal = head;
        while (traversal.next != null) {
            count++;
            traversal = traversal.next;
        }
        int middle = count / 2;
        traversal = head;
        for (int i = 0; i < middle; i++) {
            traversal = traversal.next;
        }
        return traversal;
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
