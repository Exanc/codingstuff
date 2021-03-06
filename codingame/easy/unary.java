import java.util.*;
import java.math.*;
 
class Solution {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String msg = in.nextLine();
 
        StringBuilder binMsg = new StringBuilder("");
        
        /* Converts the message into its binary code, char by char 
        (without forgetting 0s in the beginning like toBinaryString does)*/
        for (char c : msg.toCharArray()) {
            String binChar = Integer.toBinaryString(c);
            for (int j = binChar.length(); j < 7 ;j++)
                binMsg.append("0");
            binMsg.append(binChar);
        }
        
        /* prev contains the last character of binMsg read : '0' or '1'
           count cointains the number of consecutive identical characters */    
        char prev = '2';
        int count = 0;
        /* reads binMsg character by character, updating prev, count
           and printing the solution bit by bit*/
        for (char c :  binMsg.toString().toCharArray()) {
            if (c != prev) {
                for (int j = 0 ; j < count ; j++) 
                    System.out.print("0");
                if (count != 0) 
                    System.out.print(" ");
                System.out.print((c == '0') ? "00 " : "0 ");
                count = 1;
            } else {
                count++;
            }
            prev = c;
        }
        for (int j = 0 ; j < count ; j++) 
            System.out.print("0");         
    }
}