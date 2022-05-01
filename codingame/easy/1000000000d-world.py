import sys
import math

a = [int(i) for i in input().split()]
b = [int(i) for i in input().split()]

# dotProduct = a¹*b¹ + a²*b² + ...

produit = 0
i = 0
while i < 10**9:

    length = min((a[0], b[0]))
    
    produit += (a[1] * b[1])*length

    a[0] -= length
    b[0] -= length

    if a[0] <= 0 and len(a) > 2:
        a = a[2:]
    
    if b[0] <= 0 and len(b) > 2:
        b = b[2:]

    i += length

print(produit)