package org.example;

public class Calc {
    public static int run(String exp) {

        boolean needToPlus = exp.contains("+");
        boolean needToMinus = exp.contains("-");

        String[] bits = null;

        if (needToPlus) {
            bits = exp.split(" \\+ ");
        } else if (needToMinus) {
            bits = exp.split(" - ");
        }

        int [] ints = new int [bits.length];
        for (int i = 0; i < ints.length; i++) {
            ints[i] = Integer.parseInt(bits[i]);
        }

        if (needToPlus) {
            int result = 0;
            for (int i = 0; i < ints.length; i++) {
                result += ints[i];
            }
            return result;
        } else if (needToMinus) {
            return ints[0] - ints[1];
        }

        throw new RuntimeException("해석 불가 : 올바른 계산식이 아님");
    }


}