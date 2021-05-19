package 多线程;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.concurrent.Callable;

public class MyTask implements Callable<String> {

    private String url;

    public MyTask(String url){
        this.url=url;
    }

    @Override
    public String  call(){
        try {
            URL urls = new URL(url);
            // 通过远程url连接对象打开一个连接，强转成httpURLConnection类
            HttpURLConnection connection = (HttpURLConnection) urls.openConnection();
            // 设置连接方式：get
            connection.setRequestMethod("GET");
            // 设置连接主机服务器的超时时间：15000毫秒
            connection.setConnectTimeout(15000);
            // 设置读取远程返回的数据时间：60000毫秒
            connection.setReadTimeout(60000);
            // 发送请求
            connection.connect();

            return connection.getResponseCode()+connection.getURL().getHost();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "线程异常";
    }
}
