package zookeeper.register;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.zookeeper.CreateMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class MasterRegister implements CommandLineRunner {

    private static final String ROOT_PATH = "/test1111";

    private static final Long WAIT_SECONDS = 3L;

    public volatile boolean master = false;

    @Autowired
    private CuratorFramework zkClient;

    @Value("${server.port}")
    private String port;


    @Override
    public void run(String... strings) throws Exception {
        //Spring 容器启动后创建临时节点
        //由于在ZooKeeper中规定了所有非叶子节点必须为持久节点，调用上面这个API之后，只有path参数对应的数据节点是临时节点，其父节点均为持久节点
        regist();
        PathChildrenCache childrenCache = new PathChildrenCache(zkClient,ROOT_PATH,true);
        childrenCache.start(PathChildrenCache.StartMode.BUILD_INITIAL_CACHE);
        // 节点数据change事件的通知方法
        childrenCache.getListenable().addListener((curatorFramework,event)->{
            if(event.getType().equals(PathChildrenCacheEvent.Type.CHILD_REMOVED)){
                System.out.println("节点变更，开始重新选举");
                TimeUnit.SECONDS.sleep(WAIT_SECONDS);
                regist();
            }
        });
    }

    private void regist() {
        System.out.printf("机器【%s】开始抢占 Master", port);
        System.out.println();
        try {
            zkClient.create()
                    .creatingParentsIfNeeded()
                    .withMode(CreateMode.EPHEMERAL)
                    .forPath(ROOT_PATH + "/master", port.getBytes());
            System.out.printf("机器【%s】成为了 Master", port);
            System.out.println();
            master = true;
        } catch (Exception e) {
            System.out.printf("机器【%s】抢占 Master 失败", port);
            System.out.println();
            master = false;
        }
    }
}
