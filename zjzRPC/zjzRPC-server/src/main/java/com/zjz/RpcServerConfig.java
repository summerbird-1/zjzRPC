package com.zjz;

import lombok.Data;

/**
 * RPC服务器配置类。
 * 用于配置RPC服务的传输协议、编码解码器及监听端口。
 */
@Data
public class RpcServerConfig {
    // 默认使用HTTP协议作为传输协议
    private Class<? extends TransportServer> transportClass = HTTPTransportServer.class;
    // 默认使用JSON编码器
    private Class<? extends Encoder> encoderClass = JSONEncoder.class;
    // 默认使用JSON解码器
    private Class<? extends Decoder> decoderClass = JSONDecoder.class;
    // 默认监听端口号为3000
    private int port = 3000;
}

