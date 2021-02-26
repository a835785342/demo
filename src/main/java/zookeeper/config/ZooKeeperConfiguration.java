package zookeeper.config;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ZooKeeperConfiguration {

    private static final String ZOOKEEPER_URL = "10.92.226.8:7401,10.92.226.9:7401,10.92.226.10:7401";

    @Bean
    public CuratorFramework getCuratorFramework(){
        System.out.println("zookeeper初始化成功");
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000,3);
        CuratorFramework curatorFramework = CuratorFrameworkFactory.newClient(ZOOKEEPER_URL,retryPolicy);
        curatorFramework.start();
        return curatorFramework;
    }
}
