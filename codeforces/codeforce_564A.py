import sys

_input = sys.stdin.readline().split()

k = int(_input[0])
n = int(_input[1])
w = int(_input[2])

price = 0
for i in range(1, w+1): price += (i)
price *= k

if price <= n:
    print(0)
else:
    print(price - n)