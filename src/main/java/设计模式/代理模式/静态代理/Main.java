package 设计模式.代理模式.静态代理;

import 设计模式.代理模式.B;

public class Main {
    public static void main(String[] args) {
        Proxy proxy = new Proxy(new B());
        proxy.request1();
    }
}
