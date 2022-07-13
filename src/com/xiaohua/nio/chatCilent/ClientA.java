package com.xiaohua.nio.chatCilent;

/**
 * @auther XIAOHUA
 * @date 2022/7/12 20:32
 */
public class ClientA {
    public static void main(String[] args) throws Exception {
        new ChatCilent().startClient("张三");
    }
}
