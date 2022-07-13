package com.xiaohua.threads;

/**
 * @auther XIAOHUA
 * 三个线程依次打印ABC
 * @date 2022/7/7 9:50
 */
public class PrintABC {
    public static void main(String[] args) {
        new Thread(new Zim(0,"A")).start();
        new Thread(new Zim(1,"B")).start();
        new Thread(new Zim(2,"C")).start();

    }
}
class Zim implements Runnable{

    private static Object lock=new Object();
    private static volatile int currentprintnum=0;


    //当前打印条件
    private  int flag;
    private String value;

    public Zim(int flag, String value) {
        this.flag = flag;
        this.value = value;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            synchronized (lock){
                while (currentprintnum%3!=flag){
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.printf("%s,%s\n",value,Thread.currentThread().getName());
                currentprintnum++;
                lock.notifyAll();
            }

        }

    }
}