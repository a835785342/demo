package 多线程;

public class ReentrantLock {

    private boolean isLock = false;

    private Thread currentThread = null;

    private int count = 0;

    public synchronized void lock() throws InterruptedException {
        System.out.println("开始加锁" + Thread.currentThread().getName());

        Thread thread = Thread.currentThread();

        while (isLock && thread!=currentThread) {
            System.out.println("进入等待状态" + Thread.currentThread().getName());
            wait();
        }
        currentThread = thread;
        count++;
        isLock = true;
    }

    public synchronized void unlock(){
        System.out.println("开始解锁" + Thread.currentThread().getName());

        Thread thread = Thread.currentThread();
        if(thread==currentThread){
            count--;
            if(count==0){
                notify();
                isLock = false;
            }
        }

    }


}
