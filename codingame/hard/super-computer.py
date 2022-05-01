from operator import itemgetter
n = int(input())

tasks = []

for i in range(n):
    j, d = [int(j) for j in input().split()]
    task = [j, d+j-1]
    tasks.append(task)

tasks = sorted(tasks, key = itemgetter(1))

_end = tasks[0][1]
_max = 1
for i in tasks[1:]:
    if i[0] > _end:
        _max += 1
        _end = i[1]

print(_max)