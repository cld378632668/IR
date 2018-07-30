package DesignPattern;

/**
 * Created by ChenLD on 2018/4/3.
 *
 * @author ChenLD
 * @version 1.0
 */

/**
 * 单例的应用：Spring中非懒加载的Bean
 */


/**
 * 以下三种方式均使用private static  Singleton singleton = null;为懒加载，在后面new Singleton()附近的代码处由于多线程，
 * 可能会产生多个singleton实例，故线程不安全。
 *  延迟加载作用：提高性能。当在真正需要数据的时候，才真正执行数据加载操作
 *
 * 如果是 private static  Singleton singleton = new Singleton(); 的话是即时加载，
 * 即时加载的话好像不会产生同时又多个实例的问题。
 */


//第一種单例 、 最普通的不安全单例模式: Singleton类中有一个初始化为null的Singleton对象，且被private（保证不被其他类访问） 、static修饰。
//为什么要用private的构造方法？ 如果用了public就可以在类外部初始化了。
public class Singleton {

    private static  Singleton singleton = null;

    //private的构造方法
    private Singleton(){}

    //public的获取器，保证类外部能获取到.
    //为什么要用static？ 确保其能够改变被static修饰的成员变量。
    public static Singleton getSingleton(){
        //如果当前实例为null则创建对象
        if(singleton == null){
            singleton = new Singleton();
        }
        //否则直接取用
        return singleton;
    }
/**
 * 缺點：不支持并發下絕對保證單利。兩個线程可能会同时调用public的获取器造成“ singleton = new Singleton();”被执行两次。
 * “这样一来系统中就会存在两个Singleton类的实例。” 只不过第一次的new Singleton();可能没有引用，之后会被销毁。
 */
}

/**
 *  第二种，线程安全的单例的模式，仅仅是在获取器上加了sychronized 修饰符。
 *  被sychronized修饰的代码和代码块，至多允许一个线程同时访问，其他线程必须等待。
 */
 class SychronizedSingleton{

    private static  SychronizedSingleton singleton = null;

    //private的构造方法
    private SychronizedSingleton(){}

    //public的获取器，保证类外部能获取到.
    //为什么要用static？ 确保其能够改变被static修饰的成员变量。
    public static SychronizedSingleton getSingleton(){
        //如果当前实例为null则创建对象
        if(singleton == null){
            singleton = new SychronizedSingleton();
        }
        //否则直接取用
        return singleton;
    }


}

/**
 *  双检锁法单例。
 *
 *  第三种方式是对第二种方式的优化，具体方法是对一种方式中的 “private static Singleton single == null”
 *      加volatile修饰符再配合双重检测及双检锁法、双重检测锁法。
 *
 *  第二种方式中在单例获取器函数上加sychronized的做法就可以完全不用了。
 *   volatile修饰的话就可以确保instance = new Singleton();对应的指令不会重排序。也是线程安全的。 为什么做的了线程安全？
 *   JVM 发现 当代码执行顺序变化 但 结果不变时 可能 会改变执行顺序 来提升自身性能。
 *
 *  这里需要具体研究下指令重排序。
 *
 *
 *  相比第二种的优势： 减小了锁粒度，也避免的指令重排，提高了程序效率。
 */

class VolatileSingleton {

    private static volatile  VolatileSingleton  volatileSingleton = null;

    private VolatileSingleton(){};

    public static VolatileSingleton getScchronizedSingleton(){
        //双重检测不能随意改变
        if ( volatileSingleton == null){
            synchronized (Singleton.class){                          //1
                if(volatileSingleton == null){                       //2
                    volatileSingleton = new VolatileSingleton();     //3
                }
            }
        }

        return volatileSingleton;
    }
    /**
     * 如何理解？
     线程 1 进入get 方法。
     由于single 为null，线程 1 在 //1 处进入 synchronized块。
     线程 1 被线程 2 预占。
     线程 2 进入get 方法。
     由于single 仍旧为null，线程 2 试图获取 //1 处的锁。然而，由于线程 1 持有该锁，线程 2 在 //1 处阻塞。
     线程 2 被线程 1 预占。
     线程 1 执行，由于在 //2 处实例仍旧为null，线程 1 还创建一个Singleton对象并将其引用赋值给single。
     线程 1 退出 synchronized块并从 get方法返回实例。
     线程 1 被线程 2 预占。
     线程 2 获取 //1 处的锁并检查single 是否为null。
     由于single 是非 null的，并没有创建第二个Singleton对象，由线程 1 创建的对象被返回。
     */
}

//Java单例模式--饿汉式、懒汉式需要怎么写， 参看文章 微信·《Java帝国之单例设计模式》 还没看