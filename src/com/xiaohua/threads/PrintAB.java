package com.xiaohua.threads;

/**
 * @auther XIAOHUA
 * 三个线程依次打印ABC
 * @date 2022/7/1 22:
 */
public class PrintAB {
    static volatile int flag=0;

    public static void main(String[] args) {
        new Thread(new Zimu(),"A").start();
        new Thread(new Zimu2(),"B").start();

    }

}
class Zimu implements Runnable{

    @Override
    public void run() {
        int i=0;
        while (i<5) {
            if(PrintAB.flag%2==0){
                System.out.println("A");
                PrintAB.flag+=1;
                i++;
            }
        }

    }
}

class Zimu2 implements Runnable{

    @Override
    public void run() {
        int i=0;
        while (i<5) {
            if(PrintAB.flag%2==1){
                System.out.println("B");
                PrintAB.flag+=1;
                i++;
        }

    }
}}
