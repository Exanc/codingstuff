# -------------
# FUNCTIONS
# -------------

# ---- Takes an instruction and its index
#      returns the index of the next instruction that shall be executed
def interpret(instruction, index):

    code = instruction[0]

    # ---- Branching instructions (SE or SNE)
    if (code == "7" or code == "8" or code == "9" or code == "A"):
        
        if (code == "7"):
            return index + 1 + SE_n(instruction[1:])
        
        elif (code == "8"):
            return index + 1 + SNE_n(instruction[1:])
        
        elif (code == "9"):
            return index + 1 + SE(instruction[2:])
        
        else:
            return index + 1 + SNE(instruction[2:])
    
    # ---- Non-Braching instructions
    else:
        
        if (code == "1"): 
            LOAD(instruction[1:])

        elif (code == "2"):
            ADD(instruction[2:])

        elif (code == "3"):
            SUB(instruction[2:])
        
        elif (code == "4"):
            OR(instruction[2:])
        
        elif (code == "5"):
            AND(instruction[2:])
        
        else:
            XOR(instruction[2:])

        return index + 1

def LOAD(params):
    registers[int(params[0])] = int(params[1:], 16)

def ADD(params):
    _sum = getReg(params[0]) + getReg(params[1])

    # OVERFLOW
    if _sum >= (2**8):
        registers[2] = 1
        _sum -= 2**8
    else:
        registers[2] = 0

    setReg(params[0], _sum)

def SUB(params):
    res = getReg(params[0]) - getReg(params[1])

    # UNDERFLOW
    if getReg(params[0]) < getReg(params[1]):
        registers[2] = 1
        res = 2**8 + res
    else:
        registers[2] = 0
    
    setReg(params[0], res)

def OR(params):
    setReg(params[0], getReg(params[0]) | getReg(params[1]))

def AND(params):
    setReg(params[0], getReg(params[0]) & getReg(params[1]))

def XOR(params):
    setReg(params[0], getReg(params[0]) ^ getReg(params[1]))

def SE_n(params):
    return getReg(params[0]) == int(params[1:], 16)

def SNE_n(params):
    return getReg(params[0]) != int(params[1:], 16)

def SE(params):
    return getReg(params[0]) == getReg(params[1])

def SNE(params):
    return getReg(params[0]) != getReg(params[1])

def getReg(s):
    return registers[int(s)]

def setReg(i, n):
    registers[int(i)] = n

# -------------
# MAIN PROGRAM
# -------------

# ---- Take input and split into Strings of length 4

program = input()
length = 4
instructions = [program[i:i+length] for i in range(0, len(program), length)]

# ---- Initiate registers at 0
registers = [0, 0, 0]

# ---- While (end of program not reached) and (current intruction is not EXIT)
i = 0
while i < len(instructions) and instructions[i] != "0000":
    i = interpret(instructions[i], i)

# ---- Print registers
print(registers[0], registers[1], registers[2], sep = " ")