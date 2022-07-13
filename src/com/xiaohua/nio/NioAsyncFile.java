package com.xiaohua.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.Future;

/**
 * @auther XIAOHUA
 * 异步文件读写操作
 * @date 2022/7/12 11:57
 */
public class NioAsyncFile {
    public static void main(String[] args) throws IOException, InterruptedException {
//        method1();

        method2();


    }
    static void method1() throws IOException{
        //文件路径
        Path path = Paths.get("F:\\成绩.txt");
        //异步文件通道
        AsynchronousFileChannel open = AsynchronousFileChannel.open(path, StandardOpenOption.READ);
        //要接收的数据
        ByteBuffer data = ByteBuffer.allocate(1024);
        //异步线程异步读取
        Future<Integer> read = open.read(data, 0);

        //模拟主线程做别的事情
        while (!read.isDone()){
            System.out.println("主线程在做别的事情");
        }
        //异步线程读取完毕
        //改变buffer模式
        data.flip();
        byte[] bytes = new byte[data.limit()];
        data.get(bytes);
        System.out.println(new String(bytes));
        data.clear();
        open.close();

    }

    static void method2() throws IOException, InterruptedException {
        //文件路径
        Path path = Paths.get("F:\\成绩.txt");
        //异步文件通道
        AsynchronousFileChannel open = AsynchronousFileChannel.open(path, StandardOpenOption.READ);
        //要接收的数据
        ByteBuffer data = ByteBuffer.allocate(1024);
        //异步线程异步读取
         open.read(data, 0, data, new CompletionHandler<Integer, ByteBuffer>() {
             @Override
             public void completed(Integer result, ByteBuffer attachment) {
                 System.out.println("result:"+result);
                 attachment.flip();
                 System.out.println(attachment.limit());
                 byte[] bytes = new byte[attachment.limit()];
                 attachment.get(bytes);
                 System.out.println(new String(bytes));
                 attachment.clear();
             }

             @Override
             public void failed(Throwable exc, ByteBuffer attachment) {
                 System.out.println("文件读取失败");
             }
         });
         //为了让回调函数执行让主线程阻塞2秒，不然文件还没读取完成，程序就关闭了
        Thread.sleep(2000);
    }
}
