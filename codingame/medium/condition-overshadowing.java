import java.util.*;
import java.io.*;
import java.math.*;

class Solution {

    

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        int N = in.nextInt();
        if (in.hasNextLine()) in.nextLine();

        ArrayList<Integer> overShadowed = new ArrayList<Integer>();
        ArrayList<Set> conditions = new ArrayList<Set>(N);
        for (int i = 0; i < N; i++) {
            String line = in.nextLine();

            Duple<Set> cur = Set.create(line);
            System.err.println(line + " => " + cur.a + (cur.b != null ? " : " + cur.b : ""));
            
            LinkedList<Set> shadows = new LinkedList<Set>();
            LinkedList<Set> nextShadows = new LinkedList<Set>();
            if (cur.a != null) shadows.add(cur.a);
            if (cur.b != null) shadows.add(cur.b);

            for (Set T : conditions) {
                for (Set A : shadows) {

                    Duple<Set> d = T.shadow(A);
                    
                    if (d.a != null && d.a.validate()) nextShadows.add(d.a);
                    if (d.b != null && d.b.validate()) nextShadows.add(d.b);
                }

                
                shadows = (LinkedList<Set>) nextShadows.clone();
                nextShadows.clear();
            }

            if (shadows.size() == 0)
                overShadowed.add(i);

            for (Set T : shadows) {
                conditions.add(T);
                System.err.println(i + "   > " + T);
            }
        }
        in.close();

        int len = overShadowed.size();
        if (len == 0) System.out.println("ok");
        for (int i = 0; i < len; i++)
        {
            System.out.print((i == 0 ? "" : " ") + overShadowed.get(i));
        }
    }
}

class Set {

    public Bound lower;
    public Bound higher;

    public Set (Bound lower, Bound higher) {
        this.higher = higher;
        this.lower = lower;
    }

    public static Duple<Set> create (String condition) {

        String[] parts = condition.split(" ");
        int C = Integer.parseInt(parts[2]);

        if (parts[1].equals("==")) {

            return new Duple<Set>(new Set(new Bound(C, true), new Bound(C, true)));

        } else if (parts[1].equals("!=")) {

            Set lower  = new Set (new Bound(Integer.MIN_VALUE, true), new Bound(C, false));
            Set higher = new Set (new Bound(C, false), new Bound(Integer.MAX_VALUE, true));

            return new Duple<Set>(lower, higher);

        } else if (parts[1].equals(">")) {

            return new Duple<Set>(new Set (new Bound (C, false), new Bound (Integer.MAX_VALUE, true)));

        } else if (parts[1].equals("<")) {

            return new Duple<Set>(new Set (new Bound (Integer.MIN_VALUE, true), new Bound (C, false)));

        }

        return new Duple<Set>(null, null);
    }

    /**
     * @return what's left of the other set after it has be shadowed by this one
     */
    public Duple<Set> shadow (Set other) {
        
        boolean l = this.in(other.lower.val)  || this.lower.equals(other.lower);
        boolean h = this.in(other.higher.val) || this.higher.equals(other.higher);

        if (l && h) {
            return new Duple<Set>(null);

        } else if (l) {
            other.lower = this.higher.inverted();
            return new Duple<Set>(other);

        } else if (h) {
            other.higher = this.lower.inverted();
            return new Duple<Set>(other);

        } else {

            // Two possibilities
            //    1. There is no colisions
            //    2. We need to slice in two

            if (other.in(this.lower.val) && other.in(this.higher.val)) {
                // Case 2
                return new Duple<Set>(
                    new Set(other.lower, this.lower.inverted()),
                    new Set(this.higher.inverted(), other.higher)
                );
            }

            // Case 1
            return new Duple<Set>(other);
        }
    }

    public boolean in (int val) {

        if (val == this.lower.val)
            return this.lower.isInclusive;

        if (val == this.higher.val)
            return this.higher.isInclusive;
        
        return val > this.lower.val && val < this.higher.val;   
    }

    // Just in case something weird hapens
    public boolean validate () {

        if (this.lower.val > this.higher.val)
            return false;
        
        if (this.lower.val == this.higher.val) {

            if (this.lower.isInclusive && this.higher.isInclusive)
                return true;
            return false;
        }

        if (   Math.abs(this.higher.val - this.lower.val) == 1
            && !this.higher.isInclusive
            && !this.lower.isInclusive)
            return false;

        return true;
    }

    public String toString () {

        return (this.lower.isInclusive ? "[" : "]") + (this.lower.val == Integer.MIN_VALUE ? "-∞" : this.lower.val) + ("; ") + (this.higher.val == Integer.MAX_VALUE ? "+∞" : this.higher.val) + (this.higher.isInclusive ? "]" : "[");
    }

}

class Bound {

    public int val;
    public boolean isInclusive;

    public Bound (int val, boolean isInclusive) {
        this.val = val;
        this.isInclusive = isInclusive;
    }

    public Bound copy () 
    {
        return new Bound(this.val, this.isInclusive);
    }

    public Bound inverted () 
    {
        return new Bound(this.val, !this.isInclusive);
    }

    public boolean equals (Bound o)
    {
        return (this.val == o.val) && (this.isInclusive == o.isInclusive);
    }
}

class Duple<T> {
    public T a;
    public T b;
    public Duple (T a, T b) {
        this.a = a;
        this.b = b;
    }
    public Duple (T a) {
        this.a = a;
        this.b = null;
    }
}