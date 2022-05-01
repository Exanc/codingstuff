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

        String input = in.nextLine();
        String[] stateNames = in.nextLine().split(" ");

        Hashtable<Character, State> states = new Hashtable<Character, State>(stateNames.length);
        for (int i = 0; i < stateNames.length; i++) {
            states.put(stateNames[i].charAt(0), new State(stateNames[i].charAt(0)));
        }

        int numberOfTransitions = in.nextInt();
        if (in.hasNextLine()) in.nextLine();

        for (int i = 0; i < numberOfTransitions; i++) {
            String[] transition = in.nextLine().split(" ");
            states.get(transition[0].charAt(0)).transitions.put(transition[1].charAt(0), transition[2].charAt(0));
        }

        State startState  = states.get(in.nextLine().charAt(0));
        char[] endStates  = in.nextLine().replaceAll(" ", "").toCharArray();

        int numberOfWords = in.nextInt();
        if (in.hasNextLine()) in.nextLine();

        for (int i = 0; i < numberOfWords; i++) {

            boolean valid = true;
            char[] word = in.nextLine().toCharArray();

            State cur = startState;

            for (char c : word) {
                
                if (cur.getT(c) != null) {
                    
                    State next = states.get(cur.getT(c));
                    
                    if (next == null) {
                        valid = false; break;
                    }
                    cur = next;

                } else {
                    valid = false;
                    break;
                }
            }

            if (valid) {
                valid = false;
                for (char c : endStates) {
                    if (c == cur.name) {
                        valid = true;
                        break;
                    }
                }
            }

            System.out.println(valid);
        }

        in.close();
    }
}

class State {

    public char name;
    public Hashtable<Character, Character> transitions;

    public State (char name) {
        this.name = name;
        this.transitions = new Hashtable<Character, Character>();
    }

    public Character getT (char c)  {
        return this.transitions.get(c);
    }
}