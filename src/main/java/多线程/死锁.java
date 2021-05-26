package 多线程;

public class 死锁 {

    private String locka = new String("locka");
    private String lockb = new String("lockb");

    public void methodA(){
        synchronized (locka){
            System.out.println("我是方法A拿到了锁A");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lockb){
                System.out.println("我是方法A拿到了锁B");
            }
        }
    }

    public void methodB(){
        synchronized (lockb){
            System.out.println("我是方法B拿到了锁B");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (locka){
                System.out.println("我是方法A拿到了锁A");
            }
        }
    }

    public static void main(String[] args) {
        死锁 sisuoa = new 死锁();
        死锁 sisuob = new 死锁();

        System.out.println("主线程开始运行");
        new Thread(()->{
            sisuoa.methodA();
        }).start();

        new Thread(()->{
            sisuob.methodB();
        }).start();

        System.out.println("主线程结束运行");


    }
}
