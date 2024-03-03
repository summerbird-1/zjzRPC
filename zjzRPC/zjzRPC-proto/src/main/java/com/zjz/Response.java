package com.zjz;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 响应类，用于封装接口响应信息。
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response {
    private int code; // 响应状态码
    private String message; // 响应消息，用于描述响应的额外信息
    private Object data; // 响应数据，泛型设计，可存储任意类型的数据

    // 该类包含响应状态码、消息和数据的构造函数
    // 以及相应的getter和setter方法，此处未给出具体实现。
}

