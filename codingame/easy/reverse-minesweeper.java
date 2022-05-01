import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {

    private static int[][] feild;
    private static int H;
    private static int W;

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        W = in.nextInt();
        H = in.nextInt();
        in.nextLine();

        feild = new int[W][H];
        for (int y = 0; y < H; y++) {
            String line = in.nextLine();
            for (int x = 0; x < W; x++) {
                char c = line.charAt(x);
                if (c == 'x') feild[x][y] = -1;
            }
        }
        in.close();

        for (int x = 0; x < W; x++) {
            for (int y = 0; y < H; y++)
            {
                pop(x, y);
            }
        }

        for (int y = 0; y < H; y++) {
            for (int x = 0; x < W; x++)
            {
                if (feild[x][y] < 1) {
                    System.out.print('.');
                } else {
                    System.out.print(feild[x][y]);
                }
            }
            System.out.println("");
        }
    }

    public static void pop (int x, int y)
    {
        if (feild[x][y] != -1) return;

        if (y > 0) popPlace(x, y - 1);
        if (y < H - 1) popPlace(x, y + 1);

        if (x > 0) {
                       popPlace(x - 1, y);
            if (y > 0) popPlace(x - 1, y - 1);
            if (y < H - 1) popPlace(x - 1, y + 1);
        }

        if (x < W - 1) {
                       popPlace(x + 1, y);
            if (y > 0) popPlace(x + 1, y - 1);
            if (y < H - 1) popPlace(x + 1, y + 1);
        }
    }

    public static void popPlace (int x, int y)
    {
        if (feild[x][y] == -1) return;
        feild[x][y]++;
    }
}