import sys
import math


def debugPrint(msg):
    if False: print(msg, file=sys.stderr, flush=True)

def getNeighbors(x, y, lines) -> str:

    right = "-1 -1"
    lower = "-1 -1"

    inc = x +1
    while (inc < width):
        if lines[y][inc] == "0":
            right = str(inc) + " " + str(y)
            break
        inc += 1

    inc = y +1
    while (inc < height):
        if lines[inc][x] == "0":
            lower = str(x) + " " + str(inc)
            break
        inc += 1

    return right + " " + lower

# Don't let the machines win. You are humanity's last hope...

width = int(input())  # the number of cells on the X axis
height = int(input())  # the number of cells on the Y axis
lines = []
for i in range(height):
    lines += [input()]  # width characters, each either 0 or .

for y, line in enumerate(lines):
    for x, cell in enumerate(line):
        if cell == "0":
            print(str(x) + " " + str(y) + " " + getNeighbors(x, y, lines))
