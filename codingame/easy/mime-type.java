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
        int N = in.nextInt();
        int Q = in.nextInt();

        Hashtable<String, String> MIME = new Hashtable<String, String>(N);
        for (int i = 0; i < N; i++) {
            String EXT = in.next();
            String MT = in.next();

            MIME.put(EXT.toLowerCase(), MT);
        }
        in.nextLine();

        for (int i = 0; i < Q; i++) {
            String type = MIME.get(getExt(in.nextLine()));

            if (type != null) {
                System.out.println(type);
            } else {
                System.out.println("UNKNOWN");
            }
        }

        in.close();
    }

    public static String getExt (String file) {
        if (!file.contains(".")) return "";
        if (file.charAt(file.length() - 1) == '.') return "";

        String[] splited = file.toLowerCase().split("\\.");
        return splited[splited.length - 1];
    }
}