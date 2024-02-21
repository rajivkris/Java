import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class SimpleCountDownLatch {
    private int count;
    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public SimpleCountDownLatch(int count) {
        this.count = count;
        if (count < 0) {
            throw new IllegalArgumentException("count cannot be negative");
        }
    }

    /**
     * Causes the current thread to wait until the latch has counted down to zero.
     * If the current count is already zero then this method returns immediately.
     */
    public void await() throws InterruptedException {
        try {
            lock.lock();
            if (count > 0) {
                condition.await();
            }
        } finally {
            lock.unlock();
        }

    }

    /**
     *  Decrements the count of the latch, releasing all waiting threads when the count reaches zero.
     *  If the current count already equals zero then nothing happens.
     */
    public void countDown() {
        try {
            lock.lock();
            if (count > 0) {
                count--;
            }
        } finally {
            if (count == 0) {
                condition.signalAll();
            }
            lock.unlock();
        }
    }

    /**
     * Returns the current count.
     */
    public int getCount() {
        return count;
    }
}
