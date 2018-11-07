package com.alizhezi.demo.reflex;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/***
 * 反射封装
 */
public class RefInvoke {

    /***
     * 反射构造函数  返回一个对象
     * @param className
     * @param parameterTypes  参数类型
     * @param parameterValue  参数值
     * @return
     */
    public static Object createObject(String className, Class[] parameterTypes,
        Object[] parameterValue) {

        try {
            Class<?> clz = Class.forName(className);

            Constructor<?> constructor = clz.getDeclaredConstructor(parameterTypes);

            constructor.setAccessible(true);

            return constructor.newInstance(parameterValue);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /*****
     * 执行实例方法
     * @param obj
     * @param methodName
     * @param parameterTypes
     * @param parameterValue
     * @return
     */
    public static Object invokeInstanceMethod(Object obj, String methodName, Class[] parameterTypes,
        Object[] parameterValue) {

        if (obj != null) {
            try {
                Method method = obj.getClass().getDeclaredMethod(methodName, parameterTypes);
                method.setAccessible(true);
                return method.invoke(obj, parameterValue);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    /***
     * 执行静态方法
     * @param className
     * @param methodName
     * @param parameterTypes
     * @param parameterValue
     * @return
     */
    public static Object invokeStaticMethod(String className, String methodName,
        Class[] parameterTypes, Object[] parameterValue) {

        try {
            Class<?> clz = Class.forName(className);

            Method method = clz.getDeclaredMethod(methodName, parameterTypes);
            method.setAccessible(true);
            return method.invoke(null, parameterValue);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /***
     * 获取filed值
     * @param className
     * @param obj
     * @param filedName
     * @return
     */
    public static Object getFieldObject(String className, Object obj, String filedName) {

        try {
            Class<?> clz = Class.forName(className);

            Field field = clz.getDeclaredField(filedName);
            field.setAccessible(true);

            return field.get(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /***
     * 设置filed的值
     * @param className
     * @param obj
     * @param filedName
     * @param filedValue
     */
    public static void setFieldObject(String className, Object obj, String filedName,
        Object filedValue) {

        try {
            Class<?> clz = Class.forName(className);

            Field field = clz.getDeclaredField(filedName);
            field.setAccessible(true);

            field.set(obj, filedValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
