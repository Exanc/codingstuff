#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <stdbool.h>

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/

int main()
{
    // the number of temperatures to analyse
    int n;
    scanf("%d", &n);

    if (n <= 0) {
        printf("0\n");
        return 0;
    }

    int temps[n]; 

    for (int i = 0; i < n; i++) {
        // a temperature expressed as an integer ranging from -273 to 5526
        int t;
        scanf("%d", &t);
        temps[i] = t;
    }

    int nearestPos =  5527;
    int nearestNeg = -5557;

    for (int i = 0; i < n; i++) {
        if (temps[i] != 0) {
            if (temps[i] < 0) {
                if (temps[i] > nearestNeg) nearestNeg = temps[i];

            } else {
                if (temps[i] < nearestPos) nearestPos = temps[i];
            }
        }
    }

    int nearest;
    if ((nearestPos * nearestPos) == (nearestNeg * nearestNeg)) {
        nearest = nearestPos;

    } else if ((nearestPos * nearestPos) < (nearestNeg * nearestNeg)) {
        nearest = nearestPos;

    } else {
        nearest = nearestNeg;
    }

    printf("%d\n", nearest);

    return 0;
}