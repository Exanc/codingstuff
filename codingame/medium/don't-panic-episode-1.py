a=input
z=int
b,_,_,_,c,_,_,d=map(z,a().split())
e=[0]*d+[c]
f=-1
exec("q,p=map(z,a().split());e[q]=p;"*d)
while 1:i=a().split();h,k=map(z,i[:2]);l=i[2][0];w=(k<e[h])*(l=="L")+(k>e[h])*(l=="R")*(h>f);f+=w;print(("WAIT","BLOCK")[w])