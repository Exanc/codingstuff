
s = input()

val = 0
for c in s:
    if val == 0:
        if c == 'h': val += 1
        
    elif val == 1:
        if c == 'e': val += 1
        
    elif val == 2:
        if c == 'l': val += 1
    
    elif val == 3:
        if c == 'l': val += 1
        
    elif val == 4:
        if c == 'o': val += 1
        
    else:
        break

print ("YES" if val == 5 else "NO")