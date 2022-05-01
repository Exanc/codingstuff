import math
n, m, a, b = [int(i) for i in input().split()]

if (b / m) < a:

    m_count = math.floor(n / m)
    n -= m_count * m

    final = n * a
    if (b < final):
        final = b

    print(m_count * b + final)

else:
    print(n * a)