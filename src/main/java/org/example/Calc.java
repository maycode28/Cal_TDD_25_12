package org.example;

public class Calc {
    public static int run(String exp) {

        boolean needToPlus = exp.contains("+");
        boolean needToMinus = exp.contains("-");
        int result = 0;
        String[] bits = null;
        bits = exp.split(" ");


        int [] ints = new int [bits.length];
        for (int i = 0; i < ints.length; i+=2) {
            ints[i] = Integer.parseInt(bits[i]);
        }
        if (needToMinus){
            result = ints[0] - ints[2];
        }

        if (needToPlus) {
            if(bits[1].equals("-")){
                result += ints[4];
            }
            else {
                for (int i = 0; i < ints.length; i+=2) {
                    result += ints[i];
                }
            }
            return result;
        } else if (needToMinus) {
            return ints[0] - ints[2];
        }

        throw new RuntimeException("해석 불가 : 올바른 계산식이 아님");
    }


}