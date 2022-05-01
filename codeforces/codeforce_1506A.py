import sys
import math

n = int(sys.stdin.readline())

for i in range(n):
    h, w, k = [int(i) for i in sys.stdin.readline().split()]

    x = math.ceil(k / h)
    y = x*h - k

    print((x + (y-1)*w))