package com.zjz;

import com.zjz.utils.ReflectUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 使用随机算法选择TransportClient的 selector实现类。
 */
@Slf4j
public class RandomTransportSelector implements TransportSelector{
    private List<TransportClient> clients; // 存储可用的TransportClient实例列表

    /**
     * RandomTransportSelector的构造函数，初始化client列表。
     */
    public RandomTransportSelector() {
         clients = new ArrayList<>();
    }

    /**
     * 从clients列表中随机选择一个TransportClient实例。
     *
     * @return 随机选择的TransportClient实例。
     */
    @Override
    public TransportClient select() {
        int i = new Random().nextInt(clients.size()); // 随机生成一个索引
        return clients.remove(i); // 根据索引移除并返回该TransportClient实例
    }

    /**
     * 根据提供的peers列表和每台peer所需的client数量，初始化clients列表。
     *
     * @param peers peer列表，代表需要连接的服务端列表。
     * @param count 每个peer需要建立的client数量，若小于1则默认为1。
     * @param clazz 用于创建TransportClient实例的类。
     */
    @Override
    public synchronized void init(List<Peer> peers, int count, Class<? extends TransportClient> clazz) {
        count = Math.max(count, 1); // 确保count至少为1
        for(Peer peer : peers){
            for(int i = 0;i < count;i++){
                TransportClient transportClient = ReflectUtils.newInstance(clazz); // 使用反射创建TransportClient实例
                transportClient.connect(peer); // 建立连接
                clients.add(transportClient); // 将创建的client添加到列表中
            }
            log.info("connect server:{}", peer); // 记录连接建立的日志
        }
    }

    /**
     * 将一个TransportClient实例添加回clients列表。
     *
     * @param client 需要被添加回列表的TransportClient实例。
     */
    @Override
    public synchronized void release(TransportClient client) {
          clients.add(client); // 将client实例添加回列表
    }

    /**
     * 关闭所有TransportClient实例，并清空列表。
     */
    @Override
    public synchronized void close() {
       for(TransportClient client : clients){
           client.close(); // 关闭每个client实例
       }
       clients.clear(); // 清空client列表
    }
}
