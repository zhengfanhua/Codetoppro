package com.xiaohua.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

/**
 * @auther XIAOHUA
 * @date 2022/7/9 21:47
 */
public class NioServersocketChannel {
    public static void main(String[] args) throws IOException, InterruptedException {
        ByteBuffer buffer = ByteBuffer.wrap("xxx".getBytes(StandardCharsets.UTF_8));
        ServerSocketChannel channel = ServerSocketChannel.open();
        channel.bind(new InetSocketAddress(8888));
        channel.configureBlocking(false);
        while (true){
            System.out.println("waiting connetion");
            SocketChannel socketChannel = channel.accept();
            if (socketChannel==null){
                System.out.println("no connect");
                Thread.sleep(5000);
            }else{
                System.out.printf("%s---%s","has conneting",socketChannel.getRemoteAddress());
            }


        }

    }
}
