
# ---------
# FUNCTIONS
# ---------

# Return the value of a parametre
# Register or Integer
def getVal(p):
    global x0

    if p[0]=="a":
        return acc

    if p[0]=="d":
        return dat

    if p[0]=="x":
        v=x0[0]
        x0=x0[1:]
        return v
    return int(p)

# Set a registers value
def setReg(reg, val):
    global x1
    global acc
    global dat

    if reg == "acc":
        acc = val
    
    elif reg == "dat":
        dat = val
    
    else:
        x1.append(val)

# Returns the line of code associated with the label
def getLabel(name):

    for label in labels:
        if label[0] == name:
            return label[1]
    return None

def clampReg(n):

    if n > 999:
        return 999

    elif n < -999:
        return -999
    
    return n

# ------------
# MAIN PROGRAM
# ------------

global x0
global x1
global acc
global dat

# ---- Take input data
d = int(input())
x0 = []
for i in input().split():
    x0.append(int(i))

# ---- Take source code
n = int(input())
code = [""] * n
for i in range(n):
    code[i] = input().split()

# ---- Eliminate comments from the source code
# ---- Getting labels out of the way
# Arrays of tuples with the labels name and their associated line
labels = []
i = 0
while i < n:

    l = code[i]

    if l[0] == "#":
        code = code[:i] + code[i+1:]
        n -= 1
        i -= 1

    # If the word ends with a colon
    if l[0][len(l[0])-1] == ":":
        
        # Eliminates the colon from the name
        label_name = l[0][:len(l[0])-1]

        # If this line of code only contains a label
        if len(l) == 1:
            # Eliminate this line of code
            code = code[:i] + code[i+1:]
            n -= 1
            labels.append((label_name, i))

        else:
            # Eliminates the label from this line of code
            code[i] = code[i][1:]
            labels.append((label_name, i))
    
    i += 1

# ---- Iterate over source code
i = 0
x1 = []
acc, dat = (0, 0)
plus, minus = (False, False)
n = len(code)
while i < n:

    l = code[i]

    # Prefix checking
    if l[0] == "@":
        l = l[1:]
        code = code[:i] + code[i+1:]
        n -= 1
        i -= 1
    
    if l[0] == "+":
        if not plus:
            i += 1 
            continue
        
        l = l[1:]
    
    if l[0] == "-":
        if not minus:
            i += 1
            continue

        l = l[1:]

    # Execute operation
    if l[0] == "mov":
        setReg(l[2], getVal(l[1]))
    
    elif l[0] == "add":
        acc = clampReg(acc + getVal(l[1]))
    
    elif l[0] == "sub":
        acc = clampReg(acc - getVal(l[1]))
    
    elif l[0] == "mul":
        acc = clampReg(acc * getVal(l[1]))
    
    elif l[0] == "not":
        acc = (100 if acc == 0 else 0)

    elif l[0] == "jmp":
        i = getLabel(l[1]) - 1

    elif l[0] == "teq":
        validator = getVal(l[1]) == getVal(l[2])

        if validator:
            plus = True
            minus = False

        else:
            plus = False
            minus = True
    
    elif l[0] == "tgt":
        validator = getVal(l[1]) > getVal(l[2])

        if validator:
            plus = True
            minus = False

        else:
            plus = False
            minus = True

    elif l[0] == "tlt":
        validator = getVal(l[1]) < getVal(l[2])

        if validator:
            plus = True
            minus = False

        else:
            plus = False
            minus = True
    
    elif l[0] == "tcp":
        A = getVal(l[1])
        B = getVal(l[2])

        if A == B:
            plus = False
            minus = False

        elif A > B:
            plus = True
            minus = False
        
        elif A < B:
            plus = False
            minus = True
    
    elif l[0] == "dgt":
        index = getVal(l[1])

        acc = str(acc)[::-1] + "000"
        acc = int(acc[index])
    
    elif l[0] == "dst":
        place = getVal(l[1])
        digit = str(getVal(l[2]))

        acc = str(acc)[::-1] + "000"
        acc = list(acc)
        acc[place] = digit
        acc = int(''.join(acc[::-1]))

    i += 1

# ---- Print output data
print(*x1)
