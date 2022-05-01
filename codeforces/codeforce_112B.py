import sys

_input = sys.stdin.readline().split()

size = int(_input[0])
x = int(_input[1])
y = int(_input[2])

if size < 4:
    print("NO")

elif size == 4:
    if x == 1 or x == 4 or y == 1 or y == 4:
        print("YES")
    else:
        print("NO")

else:
    print("YES")
