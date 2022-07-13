package com.xiaohua.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

/**
 * @auther XIAOHUA
 * @date 2022/7/11 0:10
 */
public class NioSelectorServerDemo {
    public static void main(String[] args) throws Exception {
        server();

    }

    static void server() throws Exception{
        //服务端通道
        ServerSocketChannel channel = ServerSocketChannel.open();
        //非阻塞模式
        channel.configureBlocking(false);
        //创建buffer
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        //绑定地址
        channel.bind(new InetSocketAddress(9999));
        //创建选择器
        Selector selector = Selector.open();
        //通道注册到选择器
        channel.register(selector, SelectionKey.OP_ACCEPT);
        //轮训事件
        while (selector.select()==1){
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()){
                SelectionKey selectionKey = iterator.next();
                //如果是可连接状态
                if (selectionKey.isAcceptable()){
                    SocketChannel socketChannel = channel.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector,SelectionKey.OP_READ);
                }else if(selectionKey.isReadable()){
                    SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    int length=0;
                    while ((length= socketChannel.read(byteBuffer))>0){
                        byteBuffer.flip();
                        System.out.println(new String(byteBuffer.array(),0,length));
                        byteBuffer.clear();
                    }
                }
                iterator.remove();
            }
        }

    }
}
