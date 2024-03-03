package com.zjz;

import java.util.List;

/**
 * 交通选择器接口。该接口用于根据一定的策略选择一个交通客户端。
 */
public interface TransportSelector {

   /**
    * 根据选择策略选择一个交通客户端。
    *
    * @return TransportClient 返回选择的交通客户端实例。
    */
   TransportClient select();

   /**
    * 初始化函数，用于设置初始的peer列表和创建指定类型的TransportClient实例。
    *
    * @param peers 初始的peer列表，用于网络通信。
    * @param count 要创建的TransportClient实例的数量。
    * @param clazz TransportClient的子类，用于实例化客户端。
    */
   void init(List<Peer> peers,
             int count,
             Class<? extends TransportClient> clazz);

   /**
    * 释放指定的TransportClient资源。
    *
    * @param client 需要被释放的TransportClient实例。
    */
   void release(TransportClient client);

   /**
    * 关闭所有资源，终止网络通信。
    */
   void close();
}

