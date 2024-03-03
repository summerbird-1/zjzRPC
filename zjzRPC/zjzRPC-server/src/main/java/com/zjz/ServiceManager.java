package com.zjz;

import com.zjz.utils.ReflectUtils;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 服务管理器类，用于注册和查找服务实例。
 */
@Slf4j
public class ServiceManager {
    // 存储服务描述符和服务实例的映射
    private Map<ServiceDescriptor,ServiceInstance> services;

    /**
     * 构造函数，初始化服务映射为并发哈希映射。
     */
    public ServiceManager(){
        this.services = new ConcurrentHashMap<>();
    }

    /**
     * 注册服务实例。
     * @param interfaceClass 服务接口类
     * @param bean 实现了服务接口的实例
     * @param <T> 服务接口类型
     */
    public <T> void register(Class<T> interfaceClass, T bean){
        // 获取并遍历接口的所有公有方法
        Method[] publicMethods = ReflectUtils.getPublicMethods(interfaceClass);
        for(Method method : publicMethods){
            // 为每个方法创建一个服务实例
            ServiceInstance sis = new ServiceInstance(bean, method);
            // 根据接口类和方法创建服务描述符
            ServiceDescriptor sdp = ServiceDescriptor.from(interfaceClass, method);

            // 将服务描述符和服务实例注册到映射中
            services.put(sdp, sis);
            // 记录注册的日志信息
            log.info("register service: {} {}",sdp.getClazz(), sdp.getMethod());
        }
    }
    /**
     * 根据请求查找对应的服务实例。
     * @param request 包含服务描述符的请求对象
     * @return 对应的服务实例，如果不存在则返回null
     */
    public ServiceInstance lookup(Request request){
        // 从请求中获取服务描述符
        ServiceDescriptor serviceDescriptor = request.getServiceDescriptor();
        // 根据服务描述符查找服务实例
        return services.get(serviceDescriptor);
    }
}
