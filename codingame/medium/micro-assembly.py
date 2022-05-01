import sys
import math

def getReg(s):
    if s == "a": return 0
    if s == "b": return 1
    if s == "c": return 2
    if s == "d": return 3
    return -1

def MOV(a, b, ip):

    dest = getReg(a)
    src  = getReg(b)

    if src != -1:
        regs[dest] = regs[src]
    else:
        src = int(b)
        regs[dest] = src
    
    return ip + 1

def ADD(a, b, c, ip):

    dest = getReg(a)
    n1   = (regs[getReg(b)] if getReg(b) != -1 else int(b))
    n2   = (regs[getReg(c)] if getReg(c) != -1 else int(c))
    regs[dest] = n1 + n2

    return ip + 1

def SUB(a, b, c, ip):

    dest = getReg(a)
    n1   = (regs[getReg(b)] if getReg(b) != -1 else int(b))
    n2   = (regs[getReg(c)] if getReg(c) != -1 else int(c))
    regs[dest] = n1 - n2

    return ip + 1

def JNE(a, b, c, ip):

    n1 = (regs[getReg(b)] if getReg(b) != -1 else int(b))
    n2 = (regs[getReg(c)] if getReg(c) != -1 else int(c))

    if (n1 != n2): return int(a)
    else: return ip + 1

regs = [int(i) for i in input().split()]
n = int(input())

i = 0
instructions = [""]
while i < n:
    instructions.append(input())
    i += 1
instructions.pop(0)

ip = 0
while ip < n:

    instruction = instructions[ip].split(" ")

    if (instruction[0] == "MOV"): ip = MOV(instruction[1], instruction[2], ip)
    if (instruction[0] == "ADD"): ip = ADD(instruction[1], instruction[2], instruction[3], ip)
    if (instruction[0] == "SUB"): ip = SUB(instruction[1], instruction[2], instruction[3], ip)
    if (instruction[0] == "JNE"): ip = JNE(instruction[1], instruction[2], instruction[3], ip)

print(regs[0], regs[1], regs[2], regs[3], sep=" ")
