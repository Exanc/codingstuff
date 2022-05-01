L = int(input())
H = int(input())
T = input().upper()
unknownCharIndex = ord('Z') - ord('A') + 1

for i in range(H):
    asciiLine = input()
    
    for j in range(len(T)):
        charIndex = ord(T[j]) - ord('A')
        letterIndex = charIndex if T[j].isalpha() else unknownCharIndex
        asciiPart = asciiLine[letterIndex * L : (letterIndex + 1) * L]
        print(asciiPart, end='')
    print()
