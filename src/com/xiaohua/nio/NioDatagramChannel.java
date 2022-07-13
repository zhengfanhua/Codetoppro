package com.xiaohua.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @auther XIAOHUA
 * @date 2022/7/10 10:08
 */
public class NioDatagramChannel {
    public static void main(String[] args) {
        new Thread(()->{
            try {
                writeMsg();
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




    public static void readMsg() throws Exception{
        DatagramChannel channel = DatagramChannel.open();
        channel.bind(new InetSocketAddress(8999));
        channel.configureBlocking(false);
        ByteBuffer allocate = ByteBuffer.allocate(1024);
        while (true){
            channel.receive(allocate);
            allocate.flip();
            System.out.println(Charset.defaultCharset().decode(allocate));
            allocate.clear();
            Thread.sleep(2000);

        }
    }
    public static void writeMsg() throws Exception{
        DatagramChannel channel = DatagramChannel.open();
        ByteBuffer allocate = ByteBuffer.wrap("s水电费框架框架了".getBytes(StandardCharsets.UTF_8));
        while (true){
            channel.send(allocate,new InetSocketAddress("127.0.0.1",8999));
            System.out.println("已发送");
            Thread.sleep(2000);
        }
    }
}
