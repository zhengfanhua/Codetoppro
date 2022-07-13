package com.xiaohua.leetcode;

/**
 * @auther XIAOHUA
 * @date 2022/6/27 22:25
 * 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 */
public class Pro53 {
    public static void main(String[] args) {
        System.out.println(new Pro53().maxSubArray(new int[]{-1,2,3}));
    }

    public int maxSubArray(int[] nums) {
        int max=nums[0],pre=0;
        for (int i:nums) {
             pre=Math.max(pre,pre+i);
             max=Math.max(max,pre);
        }


        return max;

    }
}
