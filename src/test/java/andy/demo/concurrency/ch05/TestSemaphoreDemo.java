package andy.demo.concurrency.ch05;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @Author: andy.lv
 * @Date: created on 2018/1/16
 * @Description:
 */
public class TestSemaphoreDemo {

    @Test
    public void test() throws InterruptedException {
        int num = 10;
        final SemaphoreDemo resPool = new SemaphoreDemo(num);

        ExecutorService service = Executors.newFixedThreadPool(num * 2);
        for (int i = 0; i < num * 2; i++) {
            service.submit(() -> {
                try {
                    int res = resPool.fetch();
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println(Thread.currentThread().getName() + "->" + res);
                    resPool.release(res);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        service.shutdown();

        service.awaitTermination(5, TimeUnit.MINUTES);
    }
}
