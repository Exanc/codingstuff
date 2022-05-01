# Definition for singly-linked list.
class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next

# Returns the node in the specified index
def LinkedListGetNode(self, head: ListNode, index: int) -> ListNode:
        
        node = head
        for i in range(index):
            node = node.next
        return node

# Returns the length of a list
def LinkedListLength(self, head: ListNode) -> int:

        n = 0
        if head is None:
            return 0
        
        node = head
        while True:
            n += 1
            if node.next is None:
                break
            node = node.next
        return n

# Sawps two nodes in an offset from both ends of the LinkedList (1 based)
def SwapNodesFromEnds(self, head: ListNode, k: int) -> ListNode:
        
        _length = self.LinkedListLength(head)
        target1 = head
        target2 = head
        
        for i in range(k-1):
            target1 = target1.next
        for i in range(_length-k):
            target2 = target2.next
             
        tmp = target1.val
        target1.val = target2.val
        target2.val = tmp
        
        return head

# Removes duplicates
def deleteDuplicates(self, head: ListNode) -> ListNode:
        
        # Check for empty List
        if head is None:
            return None
        if head.next is None:
            return head
        
        previous  = None
        current   = None
        last      = None
        elements = []
        
        # Iterate over List
        while True:
            if previous is None:
                previous = head
                current  = head
            else:
                if current.next is None:
                    break
                previous = current
                current  = current.next
            
            if current.val in elements:
                if current.next is None:
                    last.next = None
                    break
                last.next = current.next
            else:
                last = current
                elements += [current.val]
                
        return head

# Sorts a LinkedList of positive integers (memory O(max))
def LinkedListSort(self, head: ListNode, max: int) -> ListNode:
        
        occurrence = [0] * max
        node = head

        while True:
            occurrence[node.val] += 1
            
            if not (node.next is None):
                node = node.next
            else:
                break
        
        new     = None
        current = None
            
        for i, n in enumerate(occurrence):
            
            if n == 0:
                continue
            if new is None:
                new = ListNode(i)
                current = new
                n -= 1

            for j in range(n):
                current.next = ListNode(i)
                current = current.next
        
        return new

# Find the maximum value in the LinkedList
def LinkedListMax(self, head: ListNode) -> int:
        
        _max = head.val
        if head.next is None:
            return _max
        
        node = head
        while True:
            if node.val > _max:
                _max = node.val
            if node.next is None:
                break
            node = node.next
        return _max

# Find the minimum value in the LinkedList
def LinkedListMin(self, head: ListNode) -> int:
        
        _min = head.val
        if head.next is None:
            return _min
        
        node = head
        while True:
            if node.val < _min:
                _min = node.val
            if node.next is None:
                break
            node = node.next
        return _min

# Reverses a LinkedList
def LinkedListReverse(self, head: ListNode) -> ListNode:
        
        if head is None:
            return head
        if head.next is None:
            return head
        
        inverse = ListNode(head.val)
        head = head.next

        while True:
            node = ListNode(head.val, inverse)
            inverse = node
            
            if head.next is None:
                break
            else:
                head = head.next
        return node