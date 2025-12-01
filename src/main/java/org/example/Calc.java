package org.example;

public class Calc {
    public static int run(String exp) {

        String[] bits = null;
        bits = exp.split(" ");
        int [] ints = new int [bits.length];
        for (int i = 0; i < ints.length; i+=2) {
            ints[i] = Integer.parseInt(bits[i]);
        }
        int result = ints[0];

        for (int i = 1; i < bits.length; i+=2) {
            if(bits[i].equals("-")) {
                result -= ints[i+1];
            }
            else if(bits[i].equals("+")){
                result += ints[i+1];
            }
            else if(bits[i].equals("*")){
                result *= ints[i+1];
            }
            else{
                throw new RuntimeException("해석 불가 : 올바른 계산식이 아님");
            }
        }
        return result;
    }

}