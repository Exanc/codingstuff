import sys

n = sys.stdin.readline()
s = sys.stdin.readline()

count = 0
for i, c in enumerate(s):
    if i > len(s)-1:
        continue

    if c == s[i+1]:
        count += 1
print(count)
