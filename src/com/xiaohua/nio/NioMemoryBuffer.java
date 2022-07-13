package com.xiaohua.nio;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

/**
 * @auther XIAOHUA
 * @date 2022/7/10 18:26
 */
public class NioMemoryBuffer {
    public static void main(String[] args) throws Exception {
        RandomAccessFile file = new RandomAccessFile("F:\\data\\data.txt","rw");
        FileChannel channel = file.getChannel();
        MappedByteBuffer map = channel.map(FileChannel.MapMode.READ_WRITE, 0, 1024);
        while (map.hasRemaining()){
            System.out.println((char) map.get());
        }
    }
}
