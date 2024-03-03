package com.zjz.utils;

import org.junit.Test;

import java.lang.reflect.Method;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ReflectionUtilsTest {
    @Test
    public void newInstance() {
        TestClass testClass = ReflectUtils.newInstance(TestClass.class);
        assertNotNull(testClass);
    }
    @Test
    public void getPublicMethods() {
        Method[] methods = ReflectUtils.getPublicMethods(TestClass.class);
        assertEquals(1, methods.length);
        String name = methods[0].getName();
        assertEquals("b", name);
    }
    @Test
    public void invoke() {
        Method[] publicMethods = ReflectUtils.getPublicMethods(TestClass.class);
        Method publicMethod = publicMethods[0];
        TestClass testClass = ReflectUtils.newInstance(TestClass.class);
        Object r = ReflectUtils.invoke(testClass, publicMethod, null);
        assertEquals("b", r);
    }
}
