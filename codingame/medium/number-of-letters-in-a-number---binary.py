import sys
import math

start, n = [int(i) for i in input().split()]

# This is a merging series, meaning at some point in the series it will stay at the same value

results = []
for i in range(n):

    _bin  = bin(start)[2:]
    start = _bin.count("0")*4 + _bin.count("1")*3
    results += [start]

    if len(results) > 4 and results[-1] == results[-2]:
        break

print(results[-1])
