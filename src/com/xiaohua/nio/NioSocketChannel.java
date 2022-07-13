package com.xiaohua.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

/**
 * @auther XIAOHUA
 * @date 2022/7/10 9:48
 */
public class NioSocketChannel {
    public static void main(String[] args) throws Exception {
        new Thread(()->{
            try {
                sendMsg();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(()->{
            try {
                readMsg();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();


    }

    public static void sendMsg() throws Exception {
        SocketChannel channel = SocketChannel.open();
        channel.connect(new InetSocketAddress("127.0.0.1",80));
        channel.configureBlocking(false);
        while (true) {
            ByteBuffer wrap = ByteBuffer.wrap("hhhh哈哈哈哈".getBytes(StandardCharsets.UTF_8));
            channel.write(wrap);
        }

    }

    public static void readMsg() throws Exception {
        SocketChannel channel = SocketChannel.open();
        channel.connect(new InetSocketAddress("127.0.0.1",80));
        channel.configureBlocking(false);
        while (true) {
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            channel.read(buffer);
            System.out.printf("has msg of%s", buffer);
            Thread.sleep(2000);

        }

    }

}
