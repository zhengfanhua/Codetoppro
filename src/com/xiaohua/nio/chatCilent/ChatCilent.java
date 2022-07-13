package com.xiaohua.nio.chatCilent;

import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Scanner;

/**
 * @auther XIAOHUA
 * @date 2022/7/12 20:17
 */
public class ChatCilent {


    public void startClient(String name) throws Exception{
        //创建socketchannel
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1",8888));
        //设置非阻塞
        socketChannel.configureBlocking(false);

        //创建selector
        Selector selector = Selector.open();
        socketChannel.register(selector, SelectionKey.OP_READ);

        //另起一个线程接受数据
        new Thread(new ClientThread(selector)).start();

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()){
            //得到用户输入
            String message = scanner.nextLine();
            //写入通道
            socketChannel.write(Charset.forName("UTF-8").encode(name+":"+message));
        }


    }


}
