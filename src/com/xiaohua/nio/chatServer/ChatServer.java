package com.xiaohua.nio.chatServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.nio.file.StandardOpenOption;
import java.util.Iterator;
import java.util.Set;

/**
 * @auther XIAOHUA
 * 聊天室服务器
 * @date 2022/7/12 19:23
 */
public class ChatServer {

    public  void  startServer() throws Exception{
        //新建selector
        Selector selector = Selector.open();
        //新建serversocketchannel
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //绑定地址
        serverSocketChannel.bind(new InetSocketAddress(8888));
        //设置非阻塞
        serverSocketChannel.configureBlocking(false);

        //通道绑定到selector
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        //启动日志
        System.out.println("服务端已启动");
        //循环等待连接
        for (;;){
            //获取连接就绪状态的channel数量
            int channels = selector.select();
            //如果连接就绪状态的channel数量为0，退出此次循环
            if(channels==0){
                continue;
            }
            //获取连接就绪的通道
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()){
                SelectionKey selectionKey = iterator.next();
                //根据就绪状态进行不同业务操作

                if (selectionKey.isAcceptable()){
                    AcceptOption(serverSocketChannel,selector);
                }
                if(selectionKey.isReadable()){
                    ReadOption(selector,selectionKey);
                }

                //删除该通道
                iterator.remove();


            }
        }



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
            message+=Charset.forName("UTF-8").decode(buffer);
            buffer.clear();
            read=channel.read(buffer);
        }
        System.out.println(message);

        //重新注册到selector中
        channel.register(selector,SelectionKey.OP_READ);
        //将消息数据转发给其他客户端
        if (message.length()>0){
            transferClent(message,channel,selector);
        }

    }

    private void transferClent(String message, SocketChannel targetChannel, Selector selector) throws IOException {
        Set<SelectionKey> selectionKeys = selector.keys();
        //循环所有通道,广播给其他通道
        for (SelectionKey selectionKey:selectionKeys){

            Channel channel = selectionKey.channel();
            if(channel instanceof SocketChannel&&channel!=targetChannel){
                System.out.println("xxx");
                ((SocketChannel)channel).write(Charset.forName("UTF-8").encode(message));
            }

        }
    }

    //处理接入操作
    private void AcceptOption(ServerSocketChannel serverSocketChannel,Selector selector) throws IOException {
        //接入状态，新建socketChannel
        SocketChannel socketChannel = serverSocketChannel.accept();
        //设置非阻塞
        socketChannel.configureBlocking(false);
        //注册到selector里,注册读事件
        socketChannel.register(selector,SelectionKey.OP_READ);
        //进入聊天室，服务器向客户端发消息
        socketChannel.write(Charset.forName("UTF-8").encode("欢迎进入聊天室，请注意隐私安全"));

    }

    public static void main(String[] args) throws Exception{
        new ChatServer().startServer();
    }
}
