package andy.demo.concurrency.ch05;

import org.junit.Test;

import java.util.concurrent.*;

public class TestFutureTaskDemo {

    @Test
    public void fetchWithFutureTask() throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
        FutureTask<Integer> task = new FutureTaskDemo().fetchWithFutureTask();
        TimeUnit.SECONDS.sleep(2);
        Integer result = task.get();
        System.out.println("fetch with future task->" + result + ", totalTime->" + (System.currentTimeMillis() - start));
    }

    @Test
    public void fetchNormal() throws InterruptedException {
        long start = System.currentTimeMillis();
        Integer result = new FutureTaskDemo().fetch();
        TimeUnit.SECONDS.sleep(2);
        System.out.println("fetch with future task->" + result + ", totalTime->" + (System.currentTimeMillis() - start));
    }
}
