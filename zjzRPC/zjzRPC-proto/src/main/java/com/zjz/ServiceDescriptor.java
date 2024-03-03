package com.zjz;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * 表示服务
 * @author zjz
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
/**
 * 服务描述符类，用于描述服务的细节。
 * 包含服务对应的类名、方法名、返回类型以及方法参数类型。
 */
public class ServiceDescriptor {
    private String clazz; // 服务对应的类名
    private String method; // 服务方法名
    private String returnType; // 方法的返回类型
    private String[] parameterTypes; // 方法参数类型数组

    /**
     * 根据指定的类和方法信息创建服务描述符。
     * @param clazz 包含目标方法的类对象。
     * @param method 目标方法对象。
     * @return 创建好的服务描述符实例。
     */
    public static ServiceDescriptor from(Class clazz, Method method){
        ServiceDescriptor sdp = new ServiceDescriptor();
        // 设置服务描述符的类名
        sdp.setClazz(clazz.getName());
        // 设置服务描述符的方法名
        sdp.setMethod(method.getName());
        // 设置服务描述符的返回类型名
        sdp.setReturnType(method.getReturnType().getName());
        // 获取并设置服务描述符的方法参数类型名数组
        Class[] methodParameterTypes = method.getParameterTypes();
        String[] parameterTypes = new String[methodParameterTypes.length];
        for(int i = 0;i < methodParameterTypes.length;i++){
            parameterTypes[i] = methodParameterTypes[i].getName();
        }
        sdp.setParameterTypes(parameterTypes);

        return sdp;
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if(obj == null || getClass() != obj.getClass()) return false;
        ServiceDescriptor that = (ServiceDescriptor) obj;
        return this.toString().equals(that.toString());
    }
    @Override
    public String toString() {
        return "clazz=" + clazz
                +"method="+ method
                +"returnType="+ returnType
                +"parameterTypes"+ Arrays.toString(parameterTypes);
    }
}
