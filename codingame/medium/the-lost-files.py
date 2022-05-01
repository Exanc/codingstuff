import sys
import math
import numpy as np

# Auto-generated code below aims at helping you parse
# the standard input according to the problem statement.

e = int(input())
inputs = []
for i in range(e):
    inputs += [int(j) for j in input().split()]

n = max(inputs) + 1

# all edges
E = [i for i in range(n)]

for i in range(e):
    index = i*2

    a = inputs[index]
    b = inputs[index + 1]

    new = min(E[a], E[b])
    old = max(E[a], E[b])

    for j in range(len(E)):
        if E[j] == old:
            E[j] = new

faces = 0
for i in set(E):
    # calculate number of faces in sub-graph
    # f = a - s + 2

    s = E.count(i)
    S = []
    for j in range(n):
        if E[j] == i:
            S += [j]

    a = 0
    for j in range(e):
        k = j*2

        if inputs[k] in S:
            a += 1

    faces += a - s + 1


print(len(set(E)), faces)