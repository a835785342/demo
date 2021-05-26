package 多线程;

public class UnentrantLockMain {

//    private UnentrantLock unentrantLock = new UnentrantLock();
    private ReentrantLock reentrantLock = new ReentrantLock();

    public static void main(String[] args) {
        UnentrantLockMain unentrantLockMain1 = new UnentrantLockMain();
        for (int i = 0; i < 5; i++) {
            new Thread(()->{
                unentrantLockMain1.methodA();
            }).start();
        }

    }


    public void methodA(){
        System.out.println("方法A被调用"+Thread.currentThread().getName());
        try{
            reentrantLock.lock();
            methodB();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            reentrantLock.unlock();
        }
    }

    public void methodB(){
        System.out.println("方法B被调用"+Thread.currentThread().getName());
        try{
            reentrantLock.lock();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            reentrantLock.unlock();
        }
    }
}
