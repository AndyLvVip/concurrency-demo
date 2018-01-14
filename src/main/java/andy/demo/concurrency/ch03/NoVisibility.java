package andy.demo.concurrency.ch03;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NoVisibility {
    public static boolean ready;
    public static int number;

    public static void main(String[] args) {
        int n = 10;
        ExecutorService sevice = Executors.newFixedThreadPool(n);
        for (int i = 0; i < n; i++) {
            sevice.submit(() -> {
                while(!ready)
                    Thread.yield();
                System.out.println(Thread.currentThread().getId() + "->" + number);
            });
        }
        sevice.shutdown();
        number = 43;
        ready = true;
    }
}
