package com.xiaohua.nio.chatCilent;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;

/**
 * @auther XIAOHUA
 * @date 2022/7/12 20:24
 */
public class ClientThread implements Runnable{
    private Selector selector;

    public ClientThread(Selector selector) {
        this.selector = selector;
    }
    //处理可读操作
    private void ReadOption(Selector selector, SelectionKey selectionKey) throws Exception{
        //获取通道
        SocketChannel channel = (SocketChannel) selectionKey.channel();
        //创建buffer
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        //读取channel数据
        int read = channel.read(buffer);
        buffer.flip();
        //消息数据
        String message="";

        while (read>0){
            message+= Charset.forName("UTF-8").decode(buffer);
            buffer.clear();
            read=channel.read(buffer);
        }
        System.out.println(message);

        //重新注册到selector中
        channel.register(selector,SelectionKey.OP_READ);


    }

    @Override
    public void run() {
        //循环等待连接
        for (;;){
            //获取连接就绪状态的channel数量
            int channels = 0;
            try {
                channels = selector.select();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //如果连接就绪状态的channel数量为0，退出此次循环
            if(channels==0){
                continue;
            }
            //获取连接就绪的通道
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()){
                SelectionKey selectionKey = iterator.next();

                if(selectionKey.isReadable()){
                    try {
                        ReadOption(selector,selectionKey);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                //删除该通道
                iterator.remove();


            }
        }

    }
}
