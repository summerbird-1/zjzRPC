package com.zjz;

import com.zjz.utils.ReflectUtils;
import lombok.extern.slf4j.Slf4j;
import sun.misc.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * RPC服务器类，用于实现远程过程调用服务的服务器端。
 */
@Slf4j
public class RpcServer {
    private RpcServerConfig config; // RPC服务器配置
    private TransportServer net; // 网络传输服务
    private Encoder encoder; // 编码器
    private Decoder decoder; // 解码器
    private ServiceManager serviceManager; // 服务管理器
    private ServiceInvoker serviceInvoker; // 服务调用器

    /**
     * 构造函数，使用默认配置初始化RPC服务器。
     *
     */
    public RpcServer(){
        this(new RpcServerConfig());
    }
    /**
     * 构造函数，使用配置初始化RPC服务器。
     * @param config RPC服务器配置项。
     */
    public RpcServer(RpcServerConfig config){
        this.config = config;

        this.net = ReflectUtils.newInstance(config.getTransportClass()); // 根据配置实例化网络传输服务
        this.net.init(config.getPort(), this.handler); // 初始化网络传输服务并绑定请求处理器
        this.encoder = ReflectUtils.newInstance(config.getEncoderClass()); // 实例化编码器
        this.decoder = ReflectUtils.newInstance(config.getDecoderClass()); // 实例化解码器
        this.serviceManager = new ServiceManager(); // 创建服务管理器
        this.serviceInvoker = new ServiceInvoker(); // 创建服务调用器
    }

    /**
     * 注册服务到RPC服务器。
     * @param interfaceClass 服务接口类
     * @param bean 实现该服务接口的实例
     * @param <T> 服务接口类型
     */
    public <T> void register(Class<T> interfaceClass, T bean){
        serviceManager.register(interfaceClass, bean);
    }

    /**
     * 启动RPC服务器。
     */
    public void start(){
        this.net.start(); // 启动网络传输服务
    }

    /**
     * 停止RPC服务器。
     */
    public void stop(){
        this.net.stop(); // 停止网络传输服务
    }

    // 请求处理器
    private RequestHandler handler = new RequestHandler() {
        @Override
        public void onRequest(InputStream receive, OutputStream toRespond) {
            Response response = new Response();
            // 处理请求并生成响应
            try {
                byte[] inBytes = IOUtils.readFully(receive,receive.available(),true);
                Request request = decoder.decode(inBytes, Request.class); // 解码请求
                log.info("get request:{}",request);
                ServiceInstance sis = serviceManager.lookup(request); // 查找服务实例
                Object result = serviceInvoker.invoke(sis, request); // 调用服务
                response.setData(result); // 设置响应数据
            } catch (Exception e) {
                log.warn(e.getMessage(),e);
                response.setCode(1); // 设置错误码
                response.setMessage("RPCServer got error: "
                +e.getClass().getName()
                +":"+ e.getMessage()); // 设置错误信息
            }finally {
                try {
                    byte[] outBytes = encoder.encode(response); // 编码响应
                    toRespond.write(outBytes); // 发送响应
                    log.info("response client");
                }catch (IOException e){
                    log.warn(e.getMessage(),e);
                }
            }

        }
    };
}

