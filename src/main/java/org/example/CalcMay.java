package org.example;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CalcMay {
    public static int run(String exp) {

        exp = exp.replaceAll("- ", "+ -");
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


}