
def decompileArray(array):

    name   = array.split("[")[0]
    indexs = array.split("[")[1].split("]")[0].split(".")
    base   = int(indexs[0])
    length = int(indexs[2]) - base + 1

    content = array.split("=")[1].split()

    array = {
        "name":name,
        "base":base,
        "length":length,
        "content":content
    }
    return array

def getValueFrom(array, index):
    return array["content"][index - array["base"]]

def decompileQuerry(querry):
    querry = querry.split("[")
    querry[len(querry)-1] = querry[len(querry)-1].replace("]", "")
    return querry

n = int(input())
arrays = [""] * n

for i in range(n):
    arrays[i] = decompileArray(input())
querry = decompileQuerry(input())

i = len(querry) - 1
while i > 0:
    index = next((index for (index, d) in enumerate(arrays) if d["name"] == querry[i-1]), None)
    querry[i-1] = getValueFrom(arrays[index], int(querry[i]))
    i -= 1

print(querry[0])