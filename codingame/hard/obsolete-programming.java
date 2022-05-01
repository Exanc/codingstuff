import java.util.*;
import java.io.*;
import java.math.*;

class Solution {

    public static void main(String args[]) {
        
        ArrayList<Integer> stack = new ArrayList<Integer>();
        HashMap<String, ArrayList<String>> methods = new HashMap<String, ArrayList<String>>();

        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        if (in.hasNextLine()) {
            in.nextLine();
        }

        ArrayList<String> code = new ArrayList<String>();
        for (int i = 0; i < N; i++) {
            code.addAll(Arrays.asList(in.nextLine().split(" ")));
        }
        in.close();
        String[] codeArray = new String[code.size()];
        code.toArray(codeArray);

        interpret(codeArray, stack, methods);
    }

    public static void interpret(String[] line, ArrayList<Integer> stack, HashMap<String, ArrayList<String>> methods) {

        int depth = 0;
        String instruction;
        for (int i = 0; i < line.length; i++) {

            instruction = line[i];
            if (isNumeric(instruction)) {
                stack.add(Integer.parseInt(instruction));
                continue;
            }
            
            if (instruction.equals("ADD")) {
                stack.add(pop(stack) + pop(stack));
    
            } else if (instruction.equals("SUB")) {
                stack.add(popp(stack, 2) - pop(stack));
    
            } else if (instruction.equals("MUL")) {
                stack.add(pop(stack) * pop(stack));
    
            } else if (instruction.equals("DIV")) {
                stack.add(popp(stack, 2) / pop(stack));
    
            } else if (instruction.equals("MOD")) {
                stack.add(popp(stack, 2) % pop(stack));
    
            } else if (instruction.equals("OUT")) {
                System.out.println(pop(stack));
    
            } else if (instruction.equals("POP")) {
                pop(stack);
    
            } else if (instruction.equals("DUP")) {
                stack.add(stack.get(stack.size()-1));
    
            } else if (instruction.equals("SWP")) {
                stack.add(popp(stack, 2));

            } else if (instruction.equals("ROT")) {
                stack.add(popp(stack, 3));
    
            } else if (instruction.equals("OVR")) {
                stack.add(stack.get(stack.size()-2));
    
            } else if (instruction.equals("POS")) {
                if (pop(stack) >= 0) {
                    stack.add(1);
                } else {
                    stack.add(0);
                }
            } else if (instruction.equals("NOT")) {
                if (pop(stack) == 0) {
                    stack.add(1);
                } else {
                    stack.add(0);
                }
            } else if (instruction.equals("IF")) {
                
                int start =  i+1;
                int end   =  0;
                int els   = -1;

                int d = depth;
                while (true) {

                    if (line[i].equals("IF")) {
                        d++;

                    } else if (line[i].equals("FI")) {
                        d--;
                        if (d == depth) {
                            end = i;
                            break;
                        }
                    } else if (line[i].equals("ELS") && d-1 == depth) {
                        els = i;
                    }
                    i++;
                }

                if (pop(stack) == 0) {
                    if (els != -1) {
                        interpret(Arrays.copyOfRange(line, els, end), stack, methods);
                    }
                } else {
                    if (els == -1) {
                        interpret(Arrays.copyOfRange(line, start, end), stack, methods);
                    } else {
                        interpret(Arrays.copyOfRange(line, start, els), stack, methods);
                    }
                }
    
            } else if (instruction.equals("DEF")) {
                
                ArrayList<String> method = new ArrayList<String>();
                String name;

                i++;
                name = line[i];
                i++;
                while (!line[i].equals("END") && i < line.length) {
                    if (!line[i].equals("")) {
                        method.add(line[i]);
                    }
                    i++;
                }

                methods.put(name, method);
            } else {
    
                ArrayList<String> method = methods.get(instruction);
                if (method == null) continue;

                String[] meth = new String[method.size()];
                method.toArray(meth);
                interpret(meth, stack, methods);
            } 
        }
    }

    public static int popp(ArrayList<Integer> stack, int place) {
        return stack.remove(stack.size() - place);
    }

    public static int pop(ArrayList<Integer> stack) {
        return stack.remove(stack.size()-1);
    }

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            int n = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}