
vow = ['A', 'E', 'I', 'O', 'U', 'Y']
s = input()
r = ""

for i in range(len(s)):
    if (s[i].upper() not in vow):
        r += '.' + s[i].lower()
print(r)