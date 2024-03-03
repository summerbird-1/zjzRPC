package com.zjz;

import java.util.Objects;

public interface Decoder {
    <T> T decode(byte[] bytes, Class<T> clazz);
}
