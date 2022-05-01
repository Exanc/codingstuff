M = readline().split('').map(function (char) {
        s="000000"+char.charCodeAt(0).toString(2);
        return s.substr(s.length-7);
    }).join('');;
last = '0'
count = 0
codes = ''
M.split('').forEach(c=>{
if (c == last) {
        count++
    } else {
        codes += count
        count = 1
        last = c
    }
})
codes+=count
res=''
i=0
codes.split('').forEach(n=>{
    n=parseInt(n)
    if (n>0) {
        if (i%2==0) {
            res += ' 00 '
        } else {
            res += ' 0 '
        }
        res += '0'.repeat(n)
    }
    i++;
})
print(res.slice(1))