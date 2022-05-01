import sys

n = int(sys.stdin.readline())

if n % 2 == 0:
    print((n >> 1))
else:
    print((-((n+1) >> 1)))