package org.example;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Calc {

    public static int run(String exp) {

        if (!exp.contains(" ")) {
            if (exp.contains("(")) {
                exp = exp.replace("(", "");
                exp = exp.replace(")", "");
            }
            return Integer.parseInt(exp);
        }

        exp = exp.replace("- ", "+ -");
        boolean needToMulti = exp.contains("*");
        boolean needToPlus = exp.contains("+");
        boolean needToCompound = needToPlus && needToMulti;

        boolean needToParen = exp.contains(")");


        if(needToParen){
            return run(removeParen(exp));
        }

        if (needToCompound) {
            String[] bits = exp.split(" \\+ ");

            String newExp = Arrays.stream(bits)
                    .mapToInt(Calc::run)
                    .mapToObj(e -> e + "")
                    .collect(Collectors.joining(" + "));


            return run(newExp);
        }

        if (needToPlus) {
            String[] bits = exp.split(" \\+ ");
            int sum = 0;

            for (int i = 0; i < bits.length; i++) {
                sum += Integer.parseInt(bits[i]);
            }

            return sum;
        } else if (needToMulti) {
            String[] bits = exp.split(" \\* ");

            int sum = 1;

            for (int i = 0; i < bits.length; i++) {
                sum *= Integer.parseInt(bits[i]);
            }

            return sum;
        }


        throw new RuntimeException("해석 불가 : 올바른 계산식이 아님");
    }
    static String removeParen(String original) {
        String[] result = new String[3];
        int indexOpen = 0;
        int indexClose = 0;
        for(int i= 0; i<original.length();i++){
            if (original.charAt(i)=='('){
                indexOpen = i;
            }
        }
        indexClose = original.indexOf(')', indexOpen);
        result[0] = original.substring(0, indexOpen);
        result[1] = original.substring(indexOpen + 1, indexClose);
        result[2] = original.substring(indexClose + 1, original.length());
        result[1] = ""+Calc.run(result[1]);
        String newExp = result[0]+result[1]+result[2];
        return newExp;
    }


}
