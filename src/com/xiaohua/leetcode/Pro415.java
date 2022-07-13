package com.xiaohua.leetcode;

/**
 * @auther XIAOHUA
 * @date 2022/7/1 23:56
 */
public class Pro415 {
    public static void main(String[] args) {
        System.out.println(new Pro415().addStrings("123","34"));

    }

    public String addStrings(String num1, String num2) {
        char[] chars1 = num1.toCharArray();
        char[] chars2 = num2.toCharArray();
        int numindex1=chars1.length-1;
        int numindex2=chars2.length-1;
        StringBuilder stringBuilder = new StringBuilder();
        int jingwei=0;
        while (numindex1>=0 || numindex2>=0){
            int charnum1=numindex1>=0?Character.getNumericValue(chars1[numindex1]):0;
            int charnum2=numindex2>=0?Character.getNumericValue(chars2[numindex2]):0;
            int i = charnum1 + charnum2+jingwei;
            stringBuilder.append(i/1%10);
            jingwei=i/10%10;
            numindex1--;
            numindex2--;
        }
        if (jingwei!=0){
            stringBuilder.append(jingwei);
        }

        return stringBuilder.reverse().toString();

    }
}
