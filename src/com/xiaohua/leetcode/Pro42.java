package com.xiaohua.leetcode;

/**
 * @auther XIAOHUA
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水
 * @date 2022/6/30 16:26
 */
public class Pro42 {
    public static void main(String[] args) {
        System.out.println(new Pro42().trap(new int[]{0,1,0,2,1,0,1,3,2,1,2,1}));

    }

    public int trap(int[] height) {
        int maxindex=0;
        int maxvalue=height[0];
        for (int i = 0; i < height.length; i++) {
            if(height[i]>maxvalue){
                maxindex=i;
                maxvalue=height[i];
            }
        }
        int left=height[0];
        int res=0;
        for (int i = 0; i < maxindex; i++) {
            if (height[i]>=left){
                left=height[i];
            }else{
                res=res+left-height[i];
            }
        }
        int right=height[height.length-1];
        for (int i = height.length-1; i > maxindex; i--) {
            if (height[i]>=right){
                right=height[i];
            }else{
                res=res+right-height[i];
            }
        }
        return res;


    }

}
