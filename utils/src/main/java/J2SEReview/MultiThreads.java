package J2SEReview;

/**
 * Created by ChenLD on 2018/4/3.
 *
 * @author ChenLD
 * @version 1.0
 *          多线程复习
 */
public class MultiThreads {

//    多线程
//
//    两种启动线程方法：
//
//    private static int count = 100;
//
//    public static void main(String[] args) {
//
//// 用继承Thread类的方式启动一个线程
//
//        new Thread() {
//
//            public void run() {
//
//                synchronized (StartThreadTest.class) {
//
//                    while (count > 0) {
//
//                        count--;
//
//                        System.out.println(Thread.currentThread() + "卖了一张票，还剩" + count);
//
//                    }
//
//                }
//
//            }
//
//        }.start();
//
//// 用实现Runnable接口的方式启动一个线程
//
//        new Thread(new Runnable() {
//
//            public void run() {
//
//                synchronized (StartThreadTest.class) {
//
//                    while (count > 0) {
//
//                        count--;
//
//                        System.out.println(Thread.currentThread() + "卖了一张票，还剩" + count);
//
//                    }
//
//                }
//
//            }
//
//        }).start();
//
//    }

}
