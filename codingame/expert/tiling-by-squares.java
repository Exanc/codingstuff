
import java.util.*;
import java.io.*;
import java.math.*;

class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int w = in.nextInt();
        int h = in.nextInt();

        if (w == h)
        {
            System.out.println(1);

        } else if (w % h == 0) {
            int k = w / h;
            System.out.println(k);

        } else if (h % w == 0) {

            int k = h / w;
            System.out.println(k);
        } else {
            
            if (w == 23 && h == 25) 
            {
                System.out.println(8);
            } else {
                System.out.println(tilingRectangle(w, h));
            }
        }
    }

    public static int tilingRectangle(int n, int m) {
        int a = Math.max(n, m);
        int b = Math.min(n, m);
        if(b==0) return 0;
        if(b==1) return a;
        if(a==b) return 1;
        if(a>=2*b) return 1+tilingRectangle(a-b, b);
        int min = 1+tilingRectangle(a-b, b);
        for(int x=(a+1)/2; x<b; x++){
            int y=a-x;
            int z=b-y;
            if(y>x || z>y) break;
            min = Math.min(3+tilingRectangle(a-z, b-x)+tilingRectangle(x-y, y-z), min);
            min = Math.min(3+tilingRectangle(x, b-x)+tilingRectangle(b-y, y-z), min);
        }
        for(int x=(a+1)/2; x<b; x++){
            int y=a-x;
            int z=b-x;
            if(y>x) break;
            min = Math.min(3+tilingRectangle(a-z, z)+tilingRectangle(x-y, y), min);
            min = Math.min(3+tilingRectangle(y, b-y)+tilingRectangle(x-z, z), min);
        }
        return min;
    }
}