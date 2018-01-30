package andy.demo.concurrency.ch13;

import org.junit.Test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: andy.lv
 * @Date: created on 2018/1/24
 * @Description:
 */
public class TestReentrantLock {

    public static class Res {
        private Lock lock = new ReentrantLock();
        private int value;

        public Res(int value) {
            this.value = value;
        }

        public int getValue() {
            lock.lock();
            try {
                return value;
            }finally {
                lock.unlock();
            }
        }

        public void increment() throws InterruptedException {
            lock.lock();
            try {
                lock.lockInterruptibly();
                this.value++;
            }finally {
                lock.unlock();
            }
        }
    }

    @Test
    public void lockAndRelease() {
        Res res = new Res(0);
        res.getValue();
    }
}
