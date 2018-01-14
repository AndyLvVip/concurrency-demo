package andy.demo.concurrency.ch05;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class CountDownLatchDemo {

    public static class RunRace {


        public final CountDownLatch ready;
        private final AtomicInteger seq;

        public RunRace(int runner) {
            ready = new CountDownLatch(runner);
            seq = new AtomicInteger(runner + 1);
        }

        public int run() throws InterruptedException {
            ready.await();
            return seq.decrementAndGet();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int runner = 10;
        RunRace race = new RunRace(runner);
        ExecutorService service = Executors.newCachedThreadPool();
        for (int i = 0; i < runner; i++) {
            service.execute(() -> {
                race.ready.countDown();
                try {
                    System.out.println(Thread.currentThread().getName() + "->" + race.run());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        service.shutdown();
        service.awaitTermination(5, TimeUnit.SECONDS);
        System.out.println("finished!");
    }
}
