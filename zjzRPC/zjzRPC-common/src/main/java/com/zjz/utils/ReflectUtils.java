package com.zjz.utils;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Objects;

public class ReflectUtils {
    /**
     * 创建给定类的新实例。
     *
     * @param clazz 要创建实例的类，该类必须有一个无参数的构造方法。
     * @return 新创建的类的实例。
     * @throws IllegalStateException 如果在创建实例时发生异常，比如类没有无参数构造方法。
     * @param <T> 要创建实例的类的类型。
     */
    public static <T> T newInstance(Class<T> clazz) {
        try{
            // 尝试通过调用类的无参数构造方法创建新实例
            return clazz.newInstance();
        }catch (Exception e){
            // 如果创建实例过程中发生异常，将其封装并抛出为IllegalStateException
            throw new IllegalStateException(e);
        }
    }
    /**
     * 获取指定类中所有的公有方法。
     *
     * @param clazz 待查询方法的类对象。
     * @return 包含该类中所有公有方法的Method数组。
     */
    public static Method[] getPublicMethods(Class clazz){
        // 获取类中所有声明的方法
        Method[] declaredMethods = clazz.getDeclaredMethods();
        ArrayList<Method> methods = new ArrayList<>();
        for (Method method : declaredMethods){
            // 筛选出公有方法并添加到列表中
            if(Modifier.isPublic(method.getModifiers())){
                methods.add(method);
            }
        }
        // 将列表转换为数组并返回
        return methods.toArray(new Method[0]);
    }

    /**
     * 调用指定对象上的方法。
     *
     * @param obj 要调用方法的对象。
     * @param method 要调用的方法，方法对象必须与obj所属类兼容。
     * @param args 调用方法时传递的参数数组。
     * @return 方法的返回值，如果方法返回void，则返回null。
     * @throws IllegalStateException 如果调用方法时发生异常，则封装并抛出为IllegalStateException。
     */
    public static Object invoke(Object obj, Method method, Object[] args) {
        try {
            // 通过反射调用指定对象上的方法，传入参数并返回结果
            return method.invoke(obj, args);
        } catch (Exception e) {
            // 将反射调用中可能出现的异常封装并抛出为IllegalStateException
            throw new IllegalStateException(e);
        }
    }
}
