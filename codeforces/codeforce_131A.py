
word = input()

if len(word) == 1:
    if word.islower(): print(word.upper())
    else: print(word.lower())

else:
    if word[:1].islower() and word[1:].isupper():
        print(word[:1].upper() + word[1:].lower())
    elif word.isupper():
        print(word.lower())
    else:
        print(word)