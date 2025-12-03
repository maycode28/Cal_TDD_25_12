package org.example;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CalcMay {
    public static int run(String exp) {

        if (!exp.contains(" ")) {
            if (exp.contains("(")) {
                exp = exp.replace("(", "");
                exp = exp.replace(")", "");
            }
            return Integer.parseInt(exp);
        }
        boolean needToParen = exp.contains(")");
        exp = exp.replaceAll("- ", "+ -");
        if (needToParen) {
            return run(removeParen(exp));
        }

        String[] bits = null;
        bits = exp.split(" ");
        List bitsList = new ArrayList();
        bitsList.addAll(Arrays.asList(bits));
        for (int i = 0; i < bitsList.size(); i+=2) {
            bitsList.set(i,Integer.parseInt(bitsList.get(i).toString()));
        }


        int result = 0;
        while (bitsList.contains("*")) {
            for (int i = 1; i < bitsList.size(); i+=2) {
                if(bitsList.get(i).equals("*")) {
                    result = (int)bitsList.get(i-1)*(int)bitsList.get(i+1);
                    bitsList.set(i-1, result);
                    bitsList.remove(i);
                    bitsList.remove(i);
                    break;
                }
            }
        }
        while (bitsList.contains("+")) {
            for (int i = 1; i < bitsList.size(); i+=2) {
                if(bitsList.get(i).equals("+")) {
                    result = (int)bitsList.get(i-1)+(int)bitsList.get(i+1);
                    bitsList.set(i-1, result);
                    bitsList.remove(i);
                    bitsList.remove(i);
                    break;
                }
            }
        }

        return result;
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