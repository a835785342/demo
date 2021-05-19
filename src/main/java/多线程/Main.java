package 多线程;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.jdbc.core.JdbcTemplate;

import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {


        ExecutorService executorService = Executors.newFixedThreadPool(2);
        List<FutureTask> futureTasks = new ArrayList<>();

        List<String> urls = new ArrayList<>();
        urls.add("http://www.baidu.com");
        urls.add("http://www.qq.com");
        urls.add("http://www.xdclass.net");
        urls.add("http://www.blog.csdn.net");
        urls.add("https://developer.aliyun.com/article/616112");
        urls.add("http://www.liaoxuefeng.com");

        for (String url : urls) {
            FutureTask futureTask = new FutureTask(new MyTask(url));
            futureTasks.add(futureTask);
            executorService.execute(futureTask);
        }

        for (FutureTask futureTask : futureTasks) {
            try {
                System.out.println(futureTask.get(5,TimeUnit.SECONDS));
            } catch (TimeoutException e) {
                e.printStackTrace();
            }
        }

        executorService.shutdown();



    }


}
