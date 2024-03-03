package com.zjz;

import com.alibaba.fastjson.JSON;

public class JSONEncoder implements Encoder{

    /**
     * 将对象编码为JSON字节数组。
     *
     * @param obj 需要被编码的对象。
     * @return 编码后的JSON字节数组。
     */
    @Override
    public byte[] encode(Object obj) {
        // 将对象转换为JSON字节数组
        return JSON.toJSONBytes(obj);
    }
}
