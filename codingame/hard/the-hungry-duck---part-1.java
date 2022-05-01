import java.util.*;
import java.io.*;
import java.math.*;

class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        
        int W = in.nextInt();
        int H = in.nextInt();
        
        int[][] food = new int[W][H];
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                food[j][i] = in.nextInt();
            }
        }

        System.out.println(computeMax(food));
    }

    public static int computeMax (int[][] weights) {

        int computed[][] = new int[weights.length][weights[0].length];
        for (int x = 0; x < weights.length; x++) {
            for (int y = 0; y < weights[x].length; y++) {

                int W = weights[x][y];

                int tW = get(x, y - 1, computed);
                int lW = get(x - 1, y, computed);

                computed[x][y] = W + max(tW, lW);
                //System.out.print(computed[x][y] + " ");
            }
            //System.out.print("\n");
        }

        return computed[computed.length - 1][computed[0].length - 1];
    }

    public static int get(int x, int y, int[][] mat) {

        if (x >= 0 && y >= 0 && x < mat.length && y < mat[x].length) {
            return mat[x][y];
        }
        return 0;
    }

    public static int max (int a, int b)  {

        if (a >= b) return a;
        return b;
    }
}