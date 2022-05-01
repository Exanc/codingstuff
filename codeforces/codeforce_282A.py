import sys


import sys

n = sys.stdin.readline()
s = ''.join(sys.stdin.readlines())

X = 1*s.count("++") - 1*s.count("--")
print(X)