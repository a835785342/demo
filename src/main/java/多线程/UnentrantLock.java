package 多线程;

public class UnentrantLock {

    private boolean isLock = false;

    public synchronized void lock() throws InterruptedException {
        System.out.println("开始加锁" + Thread.currentThread().getName());

        while (isLock) {
            System.out.println("进入等待状态" + Thread.currentThread().getName());
            wait();
        }
        isLock = true;
    }

    public synchronized void unlock(){
        System.out.println("开始解锁" + Thread.currentThread().getName());
        notify();
        isLock = false;
    }

}
