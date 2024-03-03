package com.zjz;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * 服务实例类，封装了服务对象和对应的方法。
 *
 */
@Data
@AllArgsConstructor
public class ServiceInstance {
    private Object target; // 服务目标对象
    private Method method; // 服务方法
}
