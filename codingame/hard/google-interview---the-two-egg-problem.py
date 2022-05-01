import sys
import math

# Auto-generated code below aims at helping you parse
# the standard input according to the problem statement.

n = int(input())

answer = 0.5*math.sqrt(8*n + 1)-0.5

print(math.ceil(answer))
