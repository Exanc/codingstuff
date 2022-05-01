a,b,c,d=map(int,input().split())
e,f=c,d
while 1:
 _=input();h=""
 if f!=b:h,f=((h+"S",f+1)if f<b else(h+"N",f-1))
 if e!=a:h,e=((h+"E",e+1)if e<a else(h+"W",e-1))
 print(h)