import java.util.*;
import java.io.*;
import java.math.*;

class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        int W = in.nextInt();
        int H = in.nextInt();

        if (in.hasNextLine()) in.nextLine();

        int[][] grid = new int[H][W];
        for (int i = 0; i < H; i++) {
            grid[i] = getIntArray(in.nextLine(), " ");
        }

        System.out.println(solve(grid));
    }

    private static int solve(int[][] mat)
    {
        int m = mat.length;
        int n = mat[0].length;
        int preSum[][] = new int[m + 1][n];
 
        for (int i = 0; i < m; i++)
        {
            for (int j = 0; j < n; j++)
            {
                preSum[i + 1][j] =
                   preSum[i][j] + mat[i][j];
            }
        }
 
        int maxSum = -1;
        int minSum = Integer.MIN_VALUE;
        int negRow = 0, negCol = 0;
        int rStart = 0, rEnd = 0, cStart = 0, cEnd = 0;
        for (int rowStart = 0;
             rowStart < m;
             rowStart++)
        {
            for (int row = rowStart; row < m; row++)
            {
                int sum = 0;
                int curColStart = 0;
                for (int col = 0; col < n; col++)
                {
                    sum += preSum[row + 1][col]
                           - preSum[rowStart][col];
                    if (sum < 0) {
                        if (minSum < sum) {
                            minSum = sum;
                            negRow = row;
                            negCol = col;
                        }
                        sum = 0;
                        curColStart = col + 1;
                    }
                    else if (maxSum < sum)
                    {
                        maxSum = sum;
                        rStart = rowStart;
                        rEnd = row;
                        cStart = curColStart;
                        cEnd = col;
                    }
                }
            }
        }
        
        return maxSum == -1 ? minSum : maxSum;
    }

    public static int[] getIntArray (String text, String separator)
    {
        String[] vals = text.split(separator);
        int[] arr = new int[vals.length];
        
        for (int i = 0; i < arr.length; i++)
        {
            arr[i] = Integer.parseInt(vals[i]);
        }

        return arr;
    }
}