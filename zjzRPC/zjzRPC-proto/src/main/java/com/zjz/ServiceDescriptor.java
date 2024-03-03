package com.zjz;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

}
