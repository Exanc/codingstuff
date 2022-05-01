import sys

s = sys.stdin.readline()
length = len(set(s)) -1

if length % 2 == 0:
    print("CHAT WITH HER!")
else:
    print("IGNORE HIM!")