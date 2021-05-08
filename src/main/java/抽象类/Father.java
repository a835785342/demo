package 抽象类;

import java.math.BigDecimal;

public abstract class Father {

    BigDecimal allowance;

    public Father(){}

    abstract public void say();

    public void run(){
        System.out.println("跑步");
    }
}
