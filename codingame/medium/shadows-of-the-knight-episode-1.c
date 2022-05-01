#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <stdbool.h>

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/

int clamp(int n, int min, int max);

int main()
{
    int minX = 0, minY = 0;
    int maxX, maxY;
    scanf("%d%d", &maxX, &maxY);
    maxX -= 1;
    maxY -= 1;

    int N;
    scanf("%d", &N);

    int X0;
    int Y0;
    scanf("%d%d", &X0, &Y0);

    // game loop
    while (1) {
        // the direction of the bombs from batman's current location (U, UR, R, DR, D, DL, L or UL)
        char bomb_dir[4];
        scanf("%s", bomb_dir);

        if(bomb_dir[0] == 'U') {
            maxY = Y0 - 1;
            
        } else if (bomb_dir[0] == 'D') {
            minY = Y0 + 1;
        }

        if (bomb_dir[0] == 'L' || bomb_dir[1] == 'L') {
            maxX = X0 - 1;

        } else if (bomb_dir[0] == 'R' || bomb_dir[1] == 'R') {
            minX = X0 + 1;
        }

        Y0 = clamp( (int)((maxY - minY) / 2 + minY), minY, maxY);
        X0 = clamp( (int)((maxX - minX) / 2 + minX), minX, maxX);
        

        // the location of the next window Batman should jump to.
        printf("%d %d\n", X0, Y0);
    }

    return 0;
}

int clamp(int n, int min, int max) {
    if (n < min) return min;
    if (n > max) return max;
    return n;
}