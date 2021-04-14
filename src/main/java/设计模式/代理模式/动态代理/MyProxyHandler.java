package 设计模式.代理模式.动态代理;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyProxyHandler implements InvocationHandler {
    private Object object;

    public MyProxyHandler(Object object){
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

            System.out.println("开始动态代理");
            method.invoke(object,args);
            System.out.println("结束动态代理");


        return proxy;
    }
}
