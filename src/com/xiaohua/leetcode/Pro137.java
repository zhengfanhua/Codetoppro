package com.xiaohua.leetcode;

/**
 * @auther XIAOHUA
 * 给你一个整数数组 nums ，除某个元素仅出现 一次 外，其余每个元素都恰出现 三次 。请你找出并返回那个只出现了一次的元素。
 * @date 2022/7/1 23:10
 */
public class Pro137 {
    public static void main(String[] args) {
        System.out.println(new Pro137().singleNumber(new int[]{0,1,0,1,0,1,99}));

    }



    public int singleNumber(int[] nums) {
        int ans=0;
        for (int i = 0; i < 32;  i++) {
            int count=0;
            for(int num:nums){
                if((num>>>i&1)==1){
                    count++;
                }
            }
            if (count%3!=0){
                ans=ans|1<<i;
            }
        }
        return ans;
    }
}
