import sys

n = int(sys.stdin.readline())

for i in range(n):
    word = sys.stdin.readline()

    if len(word) > 11:
        print(word[0] + str(len(word) - 3) + word[-2])

    else:
        print(word, end="")