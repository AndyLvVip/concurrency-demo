package andy.demo.concurrency.ch05;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @Author: andy.lv
 * @Date: created on 2018/1/29
 * @Description:
 */
public class CyclicBarrierDemo {

    private final CyclicBarrier barrier;

    public CyclicBarrierDemo(int parties) {
        barrier = new CyclicBarrier(parties);
    }

    public void doSomething() throws BrokenBarrierException, InterruptedException {
        barrier.await();
        System.out.println(Thread.currentThread().getName() + "-do something...");
    }

}
