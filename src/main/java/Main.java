import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.jdbc.core.JdbcTemplate;

import java.math.BigDecimal;
import java.util.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        List<Integer> list = new ArrayList<>(100);
        List<Integer> safeList = Collections.synchronizedList(new ArrayList<>(100));
        for(int i = 1;i<=100;i++){
            list.add(i);
            safeList.add(i);
        }
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    list.set(i,i+1);
                }

            }
        },"线程一");


        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0;i<100;i++){
                    list.set(i,i+2);
                }
            }
        },"线程二");

        thread.start();
        thread1.start();

        Thread.sleep(2000);
        System.out.println(list);
    }


}
