
# Definition for a binary tree node.
from typing import List

class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right

def BinaryTreeInorderTraversal(self, root: TreeNode) -> List[int]:
        
        if root is None:
            return
        
        s = []
        if not(root.left is None):
            s += self.BinaryTreeInorderTraversal(root.left)
        
        s += [root.val]
        if not(root.right is None):
            s += self.BinaryTreeInorderTraversal(root.right)
        return s

def BinaryTreeSum(self, root: TreeNode) -> int:
        
        elements = self.BinaryTreeInorderTraversal(root)
        
        _sum = 0
        for i in elements:
                _sum += i
        return _sum

def BinaryTreePrettyPrint(self, node, prefix="", isLeft=True):
    if not node:
        print("Empty Tree")
        return

    if node.right:
        self.BinaryTreePrettyPrint(node.right, prefix + ("│   " if isLeft else "    "), False)

    print(prefix + ("└── " if isLeft else "┌── ") + str(node.val))

    if node.left:
        self.BinaryTreePrettyPrint(node.left, prefix + ("    " if isLeft else "│   "), True)

