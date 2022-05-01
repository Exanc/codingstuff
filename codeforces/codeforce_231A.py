import sys

n = int(sys.stdin.readline())

count = 0
for i in range(n):
    l = sys.stdin.readline().count("1")

    if l >= 2:
        count += 1

print(count)