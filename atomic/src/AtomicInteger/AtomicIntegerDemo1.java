package AtomicInteger;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 演示AtomicInteger的基本用法，对比非原子类的线程安全问题，
 * 使用了原子类之后，不需要加锁，也可以保证线程安全
 *
 * @author 多宝
 * @since 2021/2/8 14:32
 */
public class AtomicIntegerDemo1 implements Runnable {

    private static final AtomicInteger atomicInteger = new AtomicInteger();

    public void incrementAtomic() {
        atomicInteger.getAndIncrement();
    }

    private static volatile int basicCount = 0;

    public void incrementBasic(){
        basicCount++;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            incrementAtomic();
            incrementBasic();
        }
    }

    public static void main(String[] args) throws Exception{
        AtomicIntegerDemo1 thisClass = new AtomicIntegerDemo1();
        Thread t1 = new Thread(thisClass);
        Thread t2 = new Thread(thisClass);
        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println("原子类的结果是：" + atomicInteger.get());
        System.out.println("普通变量的结果：" + basicCount);
    }
}
