package com.zjz;

import java.io.InputStream;

/**
 * 1、创建连接
 * 2、发送数据，等待相应
 * 3、关闭连接
 */
/**
 * 传输客户端接口，定义了与网络中其他节点进行数据传输的基本方法。
 */
public interface TransportClient {
    /**
     * 连接到指定的对等节点。
     *
     * @param peer 对等节点的信息，包括IP地址和端口号等。
     */
    void connect(Peer peer);

    /**
     * 将输入流数据发送到服务器，并返回一个输入流，用于读取服务器的响应数据。
     *
     * @param data 要发送到服务器的数据流。
     * @return 从服务器接收的响应数据流。
     */
    InputStream write(InputStream data);

    /**
     * 关闭客户端连接，释放资源。
     */
    void close();

}
