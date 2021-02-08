package lock.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 演示不释放锁会导致问题
 * Lock不会像synchronized一样，异常的时候自动释放锁，所以最佳实践是，finally里面释放锁，以保证发生异常的时候锁一定被释放
 *
 * @author 多宝
 * @since 2020/11/1 10:19
 */
public class MustUnLock {

    private static final Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        lock.lock();
        try {
            // 获取本锁保护的资源
            System.out.println(Thread.currentThread().getName() + "开始执行任务");
        } finally {
            lock.unlock();
        }
    }

}
