import sys

a = str.upper(sys.stdin.readline())
b = str.upper(sys.stdin.readline())

if a == b:
    print("0")
elif a < b:
    print("-1")
else:
    print("1")