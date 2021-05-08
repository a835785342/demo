package 抽象类;

import java.io.IOException;

public class Son extends Father implements ManyExtend{
    @Override
    public void say() {
        System.out.println("儿子说");
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }

    @Override
    public void close() throws IOException {

    }
}
