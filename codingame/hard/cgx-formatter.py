import sys

DEBUG = False

def debugPrint(s):
    if DEBUG: print(s, file=sys.stderr, flush=True)

# ---- Input
n = int(input())
text = ""
for i in range(n):
    line = input()

    # ignore blank lines
    if not line.isspace():
        text += line

# ---- Formating
i = 0
is_string = False
while i < len(text):

    c = text[i]

    # --- Ignore strings
    if c == "'":
        is_string = (not is_string)

    if is_string:
        i += 1
        continue

    # --- Delete white spaces
    if c.isspace():
        text = text[:i] + text[i+1:]
        continue

    i += 1


i = 0
depth = 0
is_string = False
while i < len(text):

    debugPrint(str(i)+" : "+str(len(text)))

    c = text[i]

    # --- Ignore strings
    if c == "'":
        is_string = (not is_string)

    if is_string:
        i += 1
        continue
    
    # --- Bloc depth
    if c == "(" and text[i+1] != ")":
        depth += 1
        text = text[:i+1] + "\n" + ("    "*depth) + text[i+1:]
        i += 2 + (4*depth)
        continue
    
    elif c == "(" and text[i+1] == ")":
        depth += 1
        i += 1
        continue

    if c == ")":
        depth -= 1
        text = text[:i] + "\n" + ("    "*depth) + text[i:]
        i += 2 + (4*depth)
        continue
    
    # --- Separate lines
    if c == ";":
        text = text[:i+1] + "\n" + ("    "*depth) + text[i+1:]
        i += 2 + (4*depth)
        continue

    # --- Separate keys and blocs
    if c == "=" and text[i+1] == "(":
        text = text[:i+1] + "\n" + ("    "*depth) + text[i+1:]
        i += 2 + (4*depth)
        continue

    i += 1

print(text)