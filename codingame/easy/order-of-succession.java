import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

        People root = null;
        Hashtable <String, People> peoples = new Hashtable <String, People>();
        for (int i = 0; i < n; i++) {
            String name = in.next();
            String parentS = in.next();
            int birth = in.nextInt();
            String death = in.next();
            String religion = in.next();
            String gender = in.next();

            People cur = new People(name, gender, 2022 - birth, (!death.equals("-")) || religion.equals("Catholic"));
            peoples.put(name, cur);

            People parent = peoples.get(parentS);
            if (parent != null) parent.childs.add(cur);

            if (i == 0) root = cur;
        }

        root.print();
    }
}

class People implements Comparable<People> {

    public String name;
    public boolean isMale;
    public int age;

    public boolean isUnviable;

    public PriorityQueue<People> childs;    

    public People (String name, String gender, int age, boolean isUnviable) {
        this.name = name;
        this.isMale = gender.contains("M");
        this.age = age;
        this.isUnviable = isUnviable;
        this.childs = new PriorityQueue<People>(2);
    }

    /**
     * @return a negative integer, zero, or a positive integer as this object is less than, equal to, 
     *         or greater than the specified object.
     */
	@Override
	public int compareTo(People o) {
		
        if (this.isMale) {
            if (!o.isMale) return -1;
        } else {
            if (o.isMale)  return 1;
        }

        return o.age - this.age;
	}

    public void print() {

        if (!this.isUnviable) System.out.println(this.name);

        while (this.childs.peek() != null) {
            this.childs.poll().print();
        }
    }
}