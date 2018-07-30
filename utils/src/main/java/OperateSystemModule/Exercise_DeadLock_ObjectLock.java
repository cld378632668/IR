package OperateSystemModule;

/**
 * Created by ChenLD on 2018/3/30.
 *
 * @author ChenLD
 * @version 1.0
 *
 * 死锁是在多线程编程中常常遇到的问题，现在用java编写一个简单的死锁程序。

程序在main()方法中启动2个线程，
“线程-A”和“线程-B”。 线程-A 先拿到 lockA，再寻求拿到 lockB；线程-B 先拿到locB，再需求拿到lockA
，如下图，于是变成循环等待，造成死锁。 图的话 嘿嘿
 *
 * 要获得一个对象锁，就是给这个对象锁加sychronized()
 *
 *
 * System.out.println(name + " got lockB");
System.out.println(name + ": say Hello!"); 
//上面两句如果写到一句里有什么区别
 * 
 */
public class Exercise_DeadLock_ObjectLock {

    public static void main() {

        Object lockA = new Object();
        Object lockB = new Object();

        new Thread(new Runnable() {
            @Override
            public void run() {
                String name = Thread.currentThread().getName();

                synchronized (lockA) {  //
                    System.out.println(name + " got lockA, want lockB");

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (lockB) {
                        System.out.println(name + " got lockB");
                        System.out.println(name + ": say Hello!");
                        //上面两句如果写到一句里有什么区别
                    }

                }
            }

        }, "我的名字是线程A").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                String name = Thread.currentThread().getName();

                synchronized (lockB) {  //
                    System.out.println(name + " got lockB, want lockA");

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (lockA) {
                        System.out.println(name + " got lockA");
                        System.out.println(name + ": say Hello!");
                        //上面两句如果写到一句里有什么区别
                    }

                }
            }

        }, "我的名字是线程B").start();

    }
}