import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.jdbc.core.JdbcTemplate;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        List<String> arrayList = new ArrayList();
        for (int i = 0; i < 10; i++) {
            arrayList.add(""+i);
        }

        arrayList.add("123");





        List<String> list = new CopyOnWriteArrayList<String>();
        List<String> safeList = Collections.synchronizedList(new ArrayList<>(100));


        ExecutorService service = new ThreadPoolExecutor(1, 2, 1000, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(5)
                , Executors.defaultThreadFactory(), new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                System.out.println(r.toString()+"执行了拒绝策略");
            }
        });

        for (int i = 0; i < 10; i++) {
            service.execute(new Runnable() {
                @Override
                public void run() {
                    //让线程阻塞，使后续任务进入缓存队列
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("ThreadName:"+Thread.currentThread().getName());
                }
            });
        }


//        Iterator<String> iter = list.iterator();

        // 执行10个任务(我当前正在迭代集合（这里模拟并发中读取某一list的场景）)
//        for (int i = 0; i < 10; i++) {
//            service.execute(new Runnable() {
//                @Override
//                public void run() {
//                    list.add("121");// 添加数据
//                }
//            });
//        }
//
//
//        // 执行10个任务
//        for (int i = 0; i < 10; i++) {
//            service.execute(new Runnable() {
//                @Override
//                public void run() {
//                    while (iter.hasNext()) {
//                        System.out.println(iter.next());
//                    }
//                }
//            });
//        }

//        System.err.println(Arrays.toString(list.toArray()));

    }


}
