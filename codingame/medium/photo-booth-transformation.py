import sys
import math

# Auto-generated code below aims at helping you parse
# the standard input according to the problem statement.

t = int(input())
for i in range(t):
    w, h = [int(j) for j in input().split()]
    
    n, t = 1, 2
    while (t-1) % (w-1) != 0 or (t-1) % (h-1) != 0:
        n += 1
        t *= 2
    
    print(str(n))

# On détermine le plus petit entier p tel que 2n-1 divise 2^p-1
# On détermine le plus petit entier k tel que 2m-1 divise 2^k-1
# On calcule le ppcm de p et k. Le résultat obtenu est la période cherchée.
