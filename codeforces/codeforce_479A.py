

a = int(input())
b = int(input())
c = int(input())

posibilities = [
    a * b * c,
    a + b + c,
    (a + b) * c,
    a * (b + c)
]

print(max(posibilities))