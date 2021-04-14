package 设计模式.代理模式.动态代理;

import 设计模式.代理模式.A;
import 设计模式.代理模式.A1;
import 设计模式.代理模式.B;

import java.lang.reflect.Proxy;

public class Main {
    public static void main(String[] args) {
        A1 a = new B();
        MyProxyHandler proxyHandler = new MyProxyHandler(a);

        A1 proxyA = (A1)Proxy.newProxyInstance(a.getClass().getClassLoader(), a.getClass().getInterfaces(), proxyHandler);
        proxyA.newRequest();
    }
}
