
rule = '{0:08b}'.format(int(input()))

n = int(input())
pattern = input().replace(".", "0").replace("@", "1")

for i in range(n):

    print(pattern.replace("0", ".").replace("1", "@"))

    _next = ""

    for j, c in enumerate(pattern):

        if j == 0: 
            c = pattern[-1] + c + pattern[j+1]

        elif j == len(pattern) - 1:
            c = pattern[j-1] + c + pattern[0]

        else:
            c = pattern[j-1] + c + pattern[j+1]
        
        if c == "111":
            if rule[0] == "1":
                _next += "1"
            else:
                _next += "0"
        
        if c == "110":
            if rule[1] == "1":
                _next += "1"
            else:
                _next += "0"
        
        if c == "101":
            if rule[2] == "1":
                _next += "1"
            else:
                _next += "0"
        
        if c == "100":
            if rule[3] == "1":
                _next += "1"
            else:
                _next += "0"
        
        if c == "011":
            if rule[4] == "1":
                _next += "1"
            else:
                _next += "0"
        
        if c == "010":
            if rule[5] == "1":
                _next += "1"
            else:
                _next += "0"
        
        if c == "001":
            if rule[6] == "1":
                _next += "1"
            else:
                _next += "0"
        
        if c == "000":
            if rule[7] == "1":
                _next += "1"
            else:
                _next += "0"
    
    pattern = _next
