
var inputs = readline().split(' ');
lightX = parseInt(inputs[0]);
lightY = parseInt(inputs[1]);
initialTx = parseInt(inputs[2]);
initialTy = parseInt(inputs[3]);

while (true) {
    const remainingTurns = parseInt(readline());
    
    res = ""
    if (initialTy > lightY) {
        res += "N"
        initialTy -= 1}
    else if (initialTy < lightY) {
        res += "S"
        initialTy += 1}
    if (initialTx > lightX) {
        res += "W"
        initialTx -= 1}
    else if (initialTx < lightX) {
        res += "E"
        initialTx += 1
    }
    
    print(res)
}
    