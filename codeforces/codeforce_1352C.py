import math
t = int(input())

for i in range(t):

    n, k = [int(i) for i in input().split()]
    
    need = math.floor((k - 1) / (n - 1))
    print(k + need)