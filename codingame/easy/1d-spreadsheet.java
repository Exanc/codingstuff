import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {

    public static Cell[] values;
    public static Operation[] operations;

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();

        values = new Cell[N];
        operations = new Operation[N];

        for (int i = 0; i < N; i++) {
            String operation = in.next();
            String arg1 = in.next();
            String arg2 = in.next();

            O inst = O.get(operation);

            if (inst == O.VAL) {

                if (isRef(arg1)) {
                    operations[i] = new Operation(O.ADD, arg1, "0");
                } else {
                    values[i] = new Cell(Integer.parseInt(arg1));
                }

            } else {

                if (isRef(arg1) || isRef(arg2)) {
                    operations[i] = new Operation(inst, arg1, arg2);
                } else {
                    values[i] = new Cell(Operation.execute(inst, Integer.parseInt(arg1), Integer.parseInt(arg2)));
                }
            }
        }
        in.close();
        
        for (int i = 0; i < N; i++) {

            if (values[i] != null) {
                System.out.println(values[i].val);
            } else {

                operations[i].execute(i);
                System.out.println(values[i].val);   
            }
        }
    }

    public static boolean isRef(String arg) {
        return arg.charAt(0) == '$';
    }
}

class Cell {

    public int val;

    public Cell (int val) {
        this.val = val;
    }
}

class Operation {

    public O instruction;
    public String arg1;
    public String arg2;

    public Operation (O instruction, String arg1, String arg2) {
        this.instruction = instruction;
        this.arg1 = arg1;
        this.arg2 = arg2;
    }

    public void execute(int index) {
        
        int a, b;
        if (Solution.isRef(this.arg1)) {

            int i = Integer.parseInt(this.arg1.replace("$", ""));
            
            if (Solution.values[i] == null) {
                Solution.operations[i].execute(i);
            }
            
            a = Solution.values[i].val;

        } else {
            a = Integer.parseInt(this.arg1);
        }

        if (Solution.isRef(this.arg2)) {

            int i = Integer.parseInt(this.arg2.replace("$", ""));

            if (Solution.values[i] == null) {
                Solution.operations[i].execute(i);
            }

            b = Solution.values[i].val;

        } else {
            b = Integer.parseInt(this.arg2);
        }

        Solution.values[index] = new Cell(execute(this.instruction, a, b));
    }

    public static int execute(O instruction, int arg1, int arg2) {
        if (instruction == O.ADD) return arg1 + arg2;
        if (instruction == O.SUB) return arg1 - arg2;
        return arg1 * arg2;
    }
}

enum O {
    VAL,
    ADD,
    SUB,
    MUL;

    public static O get(String o) {
        if (o.equals("VALUE")) return O.VAL;
        if (o.equals("ADD")) return O.ADD;
        if (o.equals("SUB")) return O.SUB;
        return O.MUL;
    }
}