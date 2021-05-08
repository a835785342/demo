package 抽象类;

import java.io.Closeable;

public interface ManyExtend extends Comparable, Closeable {

    default void defaultMethod(){
        System.out.println("默认方法");
    }
}
