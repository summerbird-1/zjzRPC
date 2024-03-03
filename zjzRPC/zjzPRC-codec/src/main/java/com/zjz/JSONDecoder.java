package com.zjz;

import com.alibaba.fastjson.JSON;

public class JSONDecoder implements Decoder{
    /**
     * 将字节码数据解码为指定类型的对象。
     *
     * @param bytes 待解码的字节码数据。
     * @param clazz 需要解码成的对象类型。
     * @return 解码后的对象实例。
     * @param <T> 解码后对象的类型。
     */
    @Override
    public <T> T decode(byte[] bytes, Class<T> clazz) {
        // 使用JSON库将字节码解析为指定类型的对象
        return JSON.parseObject(bytes, clazz);
    }
}
