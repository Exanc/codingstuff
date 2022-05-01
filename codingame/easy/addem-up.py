n,x,c=int(input()),sorted(map(int, input().split())),0
while len(x)>1:
 minSum=x[0]+x[1]
 c+=minSum
 x=sorted([minSum]+x[2:])
print(c)