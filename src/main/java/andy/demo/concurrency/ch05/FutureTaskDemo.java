package andy.demo.concurrency.ch05;

import java.util.concurrent.*;

public class FutureTaskDemo {

    private static class ResourceLoader {
        public Integer fetch() throws InterruptedException {
            TimeUnit.SECONDS.sleep(2);
            return 1;
        }
    }

    public FutureTask<Integer> fetchWithFutureTask() {
        FutureTask<Integer> task = new FutureTask<>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return new ResourceLoader().fetch();
            }
        });
        ExecutorService service = Executors.newSingleThreadExecutor();
        service.execute(task);
        service.shutdown();
        return task;
    }

    public Integer fetch() throws InterruptedException {
        return new ResourceLoader().fetch();
    }


}
