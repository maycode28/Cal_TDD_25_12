package org.example;

public class Calc {
    public static int run (String exp){
        String[] bits = exp.split(" \\+ ");
        //String[] bits = exp.split("\\+").trim();현재로선 안 됨 trim 은 string의 공백을 제거해주는데 얜 String[]이니까
        int a = Integer.parseInt(bits[0]);
        int b = Integer.parseInt(bits[1]);

        return a+b;
    }
}
