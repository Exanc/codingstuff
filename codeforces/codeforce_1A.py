import math
n, m, a = [int(i) for i in input().split()]

print(math.ceil(n/a) * math.ceil(m/a))