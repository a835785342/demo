package 设计模式.代理模式;

import 设计模式.代理模式.A;

public class B implements A,A1 {
    @Override
    public void request() {
        System.out.println("开始请求");
    }

    @Override
    public void request1() {
        System.out.println("开始请求1");
    }

    @Override
    public void newRequest() {
        System.out.println("新：开始请求");

    }
}
