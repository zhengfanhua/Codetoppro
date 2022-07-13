package com.xiaohua.leetcode;

/**
 * @auther XIAOHUA
 * @date 2022/6/28 10:23
 * 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/gaM7Ch
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Pro322 {
    public static void main(String[] args) {
        System.out.println(new Pro322().coinChange(new int[]{2,5,7},27));


    }

    public int coinChange(int[] coins, int amount) {
        //dp数组
        int[] ints = new int[amount + 1];

        //初始化
        ints[0]=0;


        for (int i = 1; i < amount + 1; i++) {
            int res=Integer.MAX_VALUE;
            for (int j = 0; j < coins.length; j++) {
                if (i-coins[j]>=0&&ints[i-coins[j]]!=Integer.MAX_VALUE) {
                    res = Math.min(ints[i - coins[j]] + 1, res);
                }
            }
            ints[i]=res;

        }
        return ints[amount]==Integer.MAX_VALUE?-1:ints[amount];


    }
}
