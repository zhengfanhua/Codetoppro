package com.xiaohua.leetcode;

/**
 * @auther XIAOHUA
 * 给你一个字符串 s，找到 s 中最长的回文子串
 * @date 2022/6/30 23:54
 */
public class Pro5 {

    public static void main(String[] args) {
        System.out.println(new Pro5().longestPalindrome("tattarrattat"));

    }
    public String longestPalindrome(String s) {
        int start=0;
        int end=0;
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < s.length(); j++) {
                if (i==j){
                    break;
                }else{
                    if (s.charAt(i)==s.charAt(j)){

                        if (i-j==1&&(i-j)>(end-start)){
                            start=j;
                            end=i;
                        }else{
                            boolean flag=true;
                            //如果相隔2个单位的倍数
                            if((i-j)%2==0){
                                int med=(i+j)/2;
                                for (int k = j+1; k < med+1; k++) {
                                    if (s.charAt(k)!=s.charAt(med+(med-k))){
                                        flag=false;
                                        break;
                                    }
                                }
                            }else{
                                //如果相隔1个单位的倍数
                                int med=(i+j-1)/2;
                                for (int k = j+1; k <=med ; k++) {


                                    if (s.charAt(k)!=s.charAt(2*med-k+1)){

                                        flag=false;
                                        break;
                                    }
                                }
                            }

                            if(flag){
                                if((i-j)>(end-start)){

                                    start=j;
                                    end=i;
                                    break;
                                }


                            }
                        }

                    }
                }
            }
        }
        return s.substring(start,end+1);
    }
}
