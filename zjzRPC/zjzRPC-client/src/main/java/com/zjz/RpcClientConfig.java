package com.zjz;

import lombok.Data;

import java.util.Arrays;
import java.util.List;

/**
 * RPC客户端配置类，用于配置RPC调用的相关参数。
 */
@Data
public class RpcClientConfig {
    // 传输客户端的类型，默认为HTTPTransportClient
    private Class<? extends TransportClient> transportClass = HTTPTransportClient.class;
    // 编码器的类型，默认为JSONEncoder
    private Class<? extends Encoder> encoder = JSONEncoder.class;
    // 解码器的类型，默认为JSONDecoder
    private Class<? extends Decoder> decoder = JSONDecoder.class;

    // 传输选择器的类型，默认为RandomTransportSelector，用于选择服务端地址
    private Class<? extends TransportSelector> transportSelector = RandomTransportSelector.class;
    // 连接尝试次数，默认为1
    private int count = 1;
    // 服务端地址列表，默认配置了一个地址：127.0.0.1:3000
    private List<Peer> servers = Arrays.asList(
            new Peer("127.0.0.1", 3000)
    );
}
