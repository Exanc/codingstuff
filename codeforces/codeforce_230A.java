package codeforces;

import java.util.*;

public class codeforce_230A {

    public static void main(String[] args) {
        
        Scanner in = new Scanner(System.in);

        int s = in.nextInt();
        int n = in.nextInt(); in.nextLine();

        PriorityQueue<Dragon> dragons = new PriorityQueue<>();
        for (int i = 0; i < n; i++)
        {
            int si = in.nextInt();
            int bi = in.nextInt(); in.nextLine();

            dragons.add(new Dragon(si, bi));
        }
        in.close();
        
        boolean valid = true;
        while (! dragons.isEmpty())
        {
            Dragon cur = dragons.poll();

            if (s > cur.strength)
            {
                s += cur.bonus;
            }
            else
            {
                valid = false; break;
            }
        }

        System.out.println(valid ? "YES" : "NO");
    }

}

class Dragon implements Comparable<Dragon>
{

    public int strength;
    public int bonus;

    public Dragon (int s, int b)
    {
        this.strength = s;
        this.bonus = b;
    }

    @Override
    public int compareTo (Dragon o) {
        
        return this.strength - o.strength;
    }
}
