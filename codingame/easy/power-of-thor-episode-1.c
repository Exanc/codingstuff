#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <stdbool.h>

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 * ---
 * Hint: You can use the debug stream to print initialTX and initialTY, if Thor seems not follow your orders.
 **/

char getX(int deltaX);

int main()
{
    // the X position of the light of power
    int light_x;
    // the Y position of the light of power
    int light_y;
    // Thor's starting X position
    int initial_tx;
    // Thor's starting Y position
    int initial_ty;
    scanf("%d%d%d%d", &light_x, &light_y, &initial_tx, &initial_ty);

    // game loop
    while (1) {
        // The remaining amount of turns Thor can move. Do not remove this line.
        int remaining_turns;
        scanf("%d", &remaining_turns);

        int deltaX = initial_tx - light_x;
        int deltaY = initial_ty - light_y;
        char out[3] = {'\0', '\0', '\0'};

        if (deltaY == 0) {
            out[0] = getX(deltaX);
            initial_tx += (-1 * (out[0] == 'W')) + (1 * (out[0] == 'E'));

        } else if (deltaY < 0) {
            out[0] = 'S';
            out[1] = getX(deltaX);

            initial_ty +=  1;
            initial_tx += (-1 * (out[1] == 'W')) + (1 * (out[1] == 'E'));

        } else {
            out[0] = 'N';
            out[1] = getX(deltaX);

            initial_ty += -1;
            initial_tx += (-1 * (out[0] == 'W')) + (1 * (out[0] == 'E'));
        }

        // A single line providing the move to be made: N NE E SE S SW W or NW
        printf("%s\n", out);
    }

    return 0;
}

char getX(int delta) {
    if (delta == 0) return '\0';
    if (delta <  0) return 'E';
    return 'W';
}