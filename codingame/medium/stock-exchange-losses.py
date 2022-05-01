n = int(input())
values = []

for i in input().split():
    values.append(int(i))

p = values[0]
d = 0

for v in values:
    t = v
    d = max(d, p - t)
    p = max(p, t)

print(-d)