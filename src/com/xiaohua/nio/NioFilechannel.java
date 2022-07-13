package com.xiaohua.nio;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

/**
 * @auther XIAOHUA
 * @date 2022/7/8 17:21
 */
public class NioFilechannel {
    public static void main(String[] args) throws Exception {
        write();

    }
    static void read() throws Exception{
        RandomAccessFile randomAccessFile = new RandomAccessFile("F:\\data\\lin1.txt","rw");
        FileChannel channel = randomAccessFile.getChannel();
        ByteBuffer buf = ByteBuffer.allocate(1024);
        int read = channel.read(buf);
        while(read!=-1){
            buf.flip();
            while (buf.hasRemaining()){
                System.out.println((char) buf.get());
            }
            buf.clear();
            read=channel.read(buf);
        }
    }
    static void write() throws Exception{
        RandomAccessFile randomAccessFile = new RandomAccessFile("F:\\data\\te.txt","rw");
        FileChannel channel = randomAccessFile.getChannel();
        ByteBuffer buf = ByteBuffer.allocate(1024);
        String newdata="xxx ksdfkjdslkf的借款方";
        buf.put(newdata.getBytes(StandardCharsets.UTF_8));
        buf.flip();
        while (buf.hasRemaining()){
            channel.write(buf);
        }
        randomAccessFile.close();


    }
}
