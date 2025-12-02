package org.example;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Calc {

    public static int run(String exp) {

        if (!exp.contains(" ")) {
            return Integer.parseInt(exp);
        }

        exp = exp.replace("- ", "+ -");
        boolean needToMulti = exp.contains("*");
        boolean needToPlus = exp.contains("+");
        boolean needToCompound = needToPlus && needToMulti;
        boolean needToParenPlus = exp.contains(") +");
        boolean needToParenMulti = exp.contains(") *");
        boolean needToParen = exp.contains(")");


        if (needToParenMulti){

            String[] bits = exp.split("\\) \\* ");
            String newExp = "";
            for (int i = 0; i < bits.length; i++) {
                bits[i] = bits[i].replace("(", "");
                bits[i] = bits[i].replace(")", "");
                int result = Calc.run(bits[i]);
                newExp += result;
                if (i < bits.length - 1) {
                    newExp += " * ";
                }

            }
            return run(newExp);

        }

        if (needToParenPlus){

            String[] bits = exp.split("\\) \\+ ");
            String newExp = "";
            for (int i = 0; i < bits.length; i++) {
                bits[i] = bits[i].replace("(", "");
                bits[i] = bits[i].replace(")", "");
                int result = Calc.run(bits[i]);
                newExp += result;
                if (i < bits.length - 1) {
                    newExp += " + ";
                }

            }
            return run(newExp);

        }
        if(needToParen){
            exp = exp.replace("(", "");
            exp = exp.replace(")", "");
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


}
