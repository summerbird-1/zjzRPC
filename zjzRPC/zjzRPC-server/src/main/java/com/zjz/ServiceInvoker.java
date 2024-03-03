package com.zjz;

import com.zjz.utils.ReflectUtils;
import sun.plugin2.message.transport.SerializingTransport;

/**
 * 服务调用器类，用于动态调用服务实例的方法。
 */
public class ServiceInvoker {

    /**
     * 调用指定服务实例的方法。
     *
     * @param service 服务实例，包含目标对象和要执行的方法。
     * @param request 请求对象，包含调用方法的参数。
     * @return 返回方法执行的结果。
     */
    public Object invoke(ServiceInstance service, Request request){
        // 通过反射工具类调用指定对象的方法，并传递参数
        return ReflectUtils.invoke(service.getTarget(),service.getMethod(),request.getParameters());
    }
}
