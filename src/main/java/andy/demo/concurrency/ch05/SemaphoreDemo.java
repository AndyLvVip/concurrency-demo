package andy.demo.concurrency.ch05;

import java.util.concurrent.Semaphore;

/**
 * @Author: andy.lv
 * @Date: created on 2018/1/16
 * @Description:
 */
public class SemaphoreDemo {

    private final Semaphore pool;
    private final int[] resource;
    private int index;

    public SemaphoreDemo(int num) {
        index = num - 1;
        pool = new Semaphore(num);
        resource = new int[num];
        for(int i = 0; i < num; i++)
            resource[i] = i + 1;
    }

    public Integer fetch() throws InterruptedException {
        pool.acquire();
        return _fetch();
    }

    public void release(Integer res) {
        _release(res);
        pool.release();
    }

    private synchronized void _release(Integer res) {
        resource[++index] = res;
    }

    private synchronized Integer _fetch() {
        return resource[index--];
    }
}
