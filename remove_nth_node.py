# Definition for singly-linked list.


class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next


class Solution:
    def removeNthFromEnd(self, head: ListNode, n: int) -> ListNode:
        if head.next == None:
            return None
        traverse = head
        node_list = []
        while traverse != None:
            node_list.append(traverse)
            traverse = traverse.next
        if n == len(node_list):
            return node_list[1]
        node_list[len(node_list) - n -
                  1].next = node_list[len(node_list) - n].next
        return head


node = ListNode(-1)
traverse = node
for i in range(0, 1):
    traverse.next = ListNode(i)
    traverse = traverse.next
traverse = node
while(traverse != None):
    print(traverse.val, end=" ")
    traverse = traverse.next
print("")
traverse = Solution().removeNthFromEnd(node, 1)
while(traverse != None):
    print(traverse.val, end=" ")
    traverse = traverse.next
