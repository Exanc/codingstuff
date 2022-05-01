
import sys
import math

# ---------
# CONSTANTS
# ---------

global DEBUG
DEBUG = False

# ---------
# FUNCTIONS
# ---------

def debugPrint(s):
    if DEBUG: print(s, file=sys.stderr, flush=True)

def abs(n):
    return (n if n >= 0 else -n)

def clamp(n, _min, _max):
    if n < _min: return _min
    if n > _max: return _max
    return n

# Return the shortest way to get a value
def shortestInc(n, is_offseted=False):
    
    if not is_offseted:

        n = n + 14
        if n > 26:
            n = n - 41

        else:
            n -= 14
    
    else:
        if n > 14:
            n -= 27
        
        elif n < -14:
            n += 27

    if n == 0:
        return ""
        
    if n < 0:
        return ("-"*-n)
        
    return "+"*n

# Return the shortest way to get to a memory cell
def travelFrom(i, next_i):
    n = next_i - i

    if n > 15:
        n = n - 30
    elif n < -15:
        n = abs((-n) - 30)
    
    if n == 0:
        return ""
        
    if n < 0:
        return ("<"*-n)
        
    return ">"*n

# Return the length of a sequence of values
def sequence(n, s):

    count = 0
    for i in s:
        if i == n: 
            count += 1
        else:
            break
    return count

# Return length of sequence of substring
def sequence_n(n, s):

    #print(str(n) +" : "+ str(s[0:len(n)]))

    count = 0
    i = 0
    while i < len(s):
        if n == s[i:(i+len(n))]: 
            count += 1
            i += len(n)
        else:
            break
    return count

def find_pattern(codes, min_length, max_length):

    #debugPrint(str(codes) +"\n"+ str(min_length) +" : "+ str(max_length))

    pattern = []
    length = 0
    i = min_length
    while i <= max_length:
        if i < len(codes):
            length = clamp(sequence_n(codes[0:i], codes), 1, 26)
            if length > 1:
                pattern = codes[0:i]
                break
        i += 1
    
    return (pattern, length)

# ------------
# MAIN PROGRAM
# ------------

# Get input
magic_phrase = input()
codes = []
for i in magic_phrase:
    code = ord(i) - 64

    if code == -32: code = 0
    codes.append(code)

script = ""
memory    = [0] * 30
mem_index = 0

i = 0
while i < len(codes):

    code = codes[i]

    if code == memory[mem_index]:
        script += "."
        i += 1
        continue

    if i == 0:

        # Find pattern of length 1 to 10
        pattern, length = find_pattern(codes[i:], 1, 10)
        if length > 1:
            
            tmp = ""
            for c in pattern:
                tmp += ">" + shortestInc(c)
            tmp = tmp[1:]
            tmp += ">" + shortestInc(length)
            tmp += "["+ ("<"*len(pattern)) + (".>"*len(pattern)) +"-]"

            for j, c in enumerate(pattern):
                memory[(mem_index + j) % 30] = c
            memory[(mem_index + len(pattern) % 30)] = 0
            mem_index = (mem_index + len(pattern)) % 30

            i += length*len(pattern)#-(len(pattern)-1)
            script += tmp

            debugPrint(str(length) +" : "+ str(len(pattern)))
            continue

        else:
            script += shortestInc(code) + "."
            memory[mem_index % 30] = code
            i += 1

        continue

    else:
        
        # Find pattern of length 1 to 10
        pattern, length = find_pattern(codes[i:], 1, 10)
        if length > 4:
            
            debugPrint(i)
            debugPrint(mem_index)
            debugPrint(memory)
            debugPrint(pattern)

            tmp = ""
            for j, c in enumerate(pattern):
                tmp += ">" + shortestInc(c - memory[(mem_index + j) % 30])
            debugPrint(tmp)

            
            tmp = tmp[1:]
            tmp += ">" + shortestInc(length - memory[(mem_index + len(pattern)) % 30])
            tmp += "["+ ("<"*len(pattern)) + (".>"*len(pattern)) +"-]"

            for j, c in enumerate(pattern):
                memory[(mem_index + j) % 30] = c
            memory[(mem_index + len(pattern)) % 30] = 0
            mem_index = (mem_index + len(pattern)) % 30

            i += length*len(pattern)#-(len(pattern)-2)
            script += tmp

            debugPrint(i)

            # FIFOFIFOFIFOFIFOFIFOFIFOFIFOFIFOFIFOFIFOFIFOFIFO FUM FUM FUM FUM FUM FUM FUM FUM FUM FUM FUM FUM FUM FUM FUM
            # FIFOFIFOFIFOFIFOFIFOFIFOFIFOFIFOFIFOFIFOFIFOFIFO FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF

            debugPrint(str(length) +" : "+ str(len(pattern)))
            continue

        """if sequence(code, codes[i:]) > 16 - memory[mem_index % 30]:
            length = clamp(sequence(code, codes[i:]), 1, 26)

            # get the character code
            tmp = shortestInc(code - memory[mem_index % 30])

            # get the number of prints to do
            tmp += ">" + shortestInc(length - memory[(mem_index + 1) % 30])

            # looping
            tmp += "[<.>-]"

            if len(tmp) < length:

                memory[ mem_index    % 30] = code
                memory[(mem_index+1) % 30] = 0
                mem_index += 1
                i += length
                script += tmp
                continue"""
            # if our solution is far lengthier than just a sequence of dots, then it's not worth the shot

        # Calculate what is the best options between going-back, staying or going-forward

        offsets = [code - n for n in memory]
        costs   = [0] * 30

        for j in range(0, 16):
            costs[(mem_index + j) % len(costs)] = j
            costs[(mem_index - j) % len(costs)] = j
        
        costs = [c + abs(o) for c, o in zip(costs, offsets)]
        lowest_cost_index = min(range(len(costs)), key=costs.__getitem__)

        if mem_index == lowest_cost_index:
            tmp = shortestInc(offsets[lowest_cost_index], is_offseted=True) + "."

            if code == 0 and memory[lowest_cost_index] > 3:
                script += "[-]."

            else:
                script += tmp

            memory[lowest_cost_index] = code
        
        else:
            script += travelFrom(mem_index, lowest_cost_index) + shortestInc(offsets[lowest_cost_index], is_offseted=True) + "."
            memory[lowest_cost_index] = code
            mem_index = lowest_cost_index

    i += 1

print(script)