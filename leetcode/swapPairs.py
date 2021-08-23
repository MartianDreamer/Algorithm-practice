from typing import Optional


class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next


class Solution:
    def swapPairs(self, head: Optional[ListNode]) -> Optional[ListNode]:
        if head == None:
            return None
        if head.next == None:
            return head
        count = 0
        traversal_node = head
        last_node = None
        while(traversal_node != None):
            if (count % 2 == 0 and traversal_node.next != None):
                next_node = traversal_node.next
                next_next_node = next_node.next
                next_node.next = traversal_node
                traversal_node.next = next_next_node
                if last_node != None:
                    last_node.next = next_node
                if(count == 0):
                    head = next_node
                count += 1
                continue
            count += 1
            last_node = traversal_node
            traversal_node = traversal_node.next
        return head


head = ListNode(1)
head.next = ListNode(2)
head.next.next = ListNode(3)
head.next.next.next = ListNode(4)

Solution().swapPairs(head)
