
import math

n = int(input())
x, y, z = 0, 0, 0

for i in range(n):
    xi, yi, zi = [int(i) for i in input().split()]
    x += xi
    y += yi
    z += zi

print ("YES" if x == 0 and y == 0 and z == 0 else "NO")