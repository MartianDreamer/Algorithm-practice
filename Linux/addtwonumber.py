# Definition for singly-linked list.

class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next


class Solution:
    def processList(self, node):
        result = 0
        p = 0
        while node != None:
            result += node.val * 10**p
            p += 1
            node = node.next
        return result

    def addTwoNumbers(self, l1: ListNode, l2: ListNode) -> ListNode:
        total = self.processList(l1) + self.processList(l2)
        result = None
        traverse = None
        if l1.val == l2.val == 0 and l1.next is None and l2.next is None:
            return l1
        while total > 0:
            if traverse == None:
                traverse = ListNode(total % 10)
                total //= 10
                result = traverse
            else:
                traverse.next = ListNode(total % 10)
                total //= 10
                traverse = traverse.next
        return result


first = ListNode(0)
traverse = first
for i in range(1, 10):
    temp = ListNode(i)
    traverse.next = temp
    traverse = traverse.next
result = Solution().addTwoNumbers(ListNode(), ListNode())
while result != None:
    print(result.val)
    result = result.next
