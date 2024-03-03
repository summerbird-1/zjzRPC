package com.zjz;

import com.zjz.utils.ReflectUtils;

import java.lang.reflect.Proxy;

public class RpcClient {
    private RpcClientConfig config;
    private Encoder encoder;
    private Decoder decoder;
    private TransportSelector transportSelector;
    public RpcClient() {
        this(new RpcClientConfig());
    }
    public RpcClient(RpcClientConfig config) {
        this.config = config;
        this.encoder = ReflectUtils.newInstance(this.config.getEncoder());
        this.decoder = ReflectUtils.newInstance(this.config.getDecoder());
        this.transportSelector = ReflectUtils.newInstance(this.config.getTransportSelector());

        this.transportSelector.init(
                this.config.getServers(),
                this.config.getCount(),
                this.config.getTransportClass()
        );
    }
    public<T> T getProxy(Class<T> clazz) {
        return (T) Proxy.newProxyInstance(
           getClass().getClassLoader(),
                new Class[]{clazz},
                new RemoteInvoker(clazz,encoder,decoder,transportSelector));
    }
}
