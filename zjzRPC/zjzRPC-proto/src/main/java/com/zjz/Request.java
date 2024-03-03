package com.zjz;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 请求类，用于封装服务请求的信息。
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Request {
    private ServiceDescriptor serviceDescriptor; // 服务描述符
    private Object[] parameters; // 服务调用参数
}
