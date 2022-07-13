package com.xiaohua.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Scanner;

/**
 * @auther XIAOHUA
 * @date 2022/7/11 11:49
 */
public class NioSelectorClientDemo {
    public static void main(String[] args) throws Exception {
        client();
    }

    static void client() throws Exception{
        SocketChannel channel = SocketChannel.open(new InetSocketAddress(9999));
        channel.configureBlocking(false);
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()){
            buffer.clear();
            String next = scanner.next();
            buffer.put((new Date().toString()+"---->"+next).getBytes(StandardCharsets.UTF_8));
            buffer.flip();
            channel.write(buffer);
        }
    }
}
