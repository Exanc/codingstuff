import sys
import math

# Auto-generated code below aims at helping you parse
# the standard input according to the problem statement.

w, h = [int(i) for i in input().split()]

bits = []
for i in range(h):
    for j in input().split():
        bits += [bin(int(j))[-1]]

text = ""
for i in range(len(bits)//8):
    byte = bits[(i*8):(i*8)+8][::-1]
    char = 0
    for i, n in enumerate(byte):
        char += int(n) * (2**i)
    
    text += chr(char)

print(text)
