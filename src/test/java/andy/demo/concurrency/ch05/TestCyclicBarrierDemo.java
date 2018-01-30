package andy.demo.concurrency.ch05;

import org.junit.Test;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @Author: andy.lv
 * @Date: created on 2018/1/29
 * @Description:
 */
public class TestCyclicBarrierDemo {


    @Test
    public void doSomethingAtTheSameTime() throws InterruptedException {
        int n = 10;
        ExecutorService service = Executors.newCachedThreadPool();
        CyclicBarrierDemo demo = new CyclicBarrierDemo(n);
        for (int i = 0; i < n; i++) {
            final int second = i;
            service.submit(() -> {
                try {
                    for (int j = 0; j < 3; j++) {
                        TimeUnit.SECONDS.sleep(second);
                        demo.doSomething();
                    }
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        service.shutdown();
        service.awaitTermination(1, TimeUnit.MINUTES);
    }
}
