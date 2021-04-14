package 设计模式.代理模式.静态代理;

import 设计模式.代理模式.A;
import 设计模式.代理模式.B;

public class Proxy implements A {

    B b;

    public Proxy(B b){
        this.b=b;
    }

    @Override
    public void request() {
        System.out.println("代理开始");
        b.request();
        System.out.println("代理结束");
    }

    @Override
    public void request1() {

    }
}
