import sys

n = int(sys.stdin.readline())

for i in range(n):

    a, b = map(int, sys.stdin.readline().split())

    # find closest product of b that is grater than a
    target = b
    while target < a:
        target += b
    
    print(str(target - a))