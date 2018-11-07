package com.alizhezi.demo.reflex;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import org.junit.Test;

public class Client {
    @Test
    public void constructions() {
        Student student = new Student();

        Class<? extends Student> clz = student.getClass();

        Constructor<?>[] declaredConstructors = clz.getDeclaredConstructors();

        for (Constructor<?> declaredConstructor : declaredConstructors) {

            int modifiers = declaredConstructor.getModifiers();

            String modifier = Modifier.toString(modifiers);

            System.out.println("modifier:" + modifier + ",className:" + clz.getName());

            Class<?>[] parameterTypes = declaredConstructor.getParameterTypes();

            for (Class<?> parameterType : parameterTypes) {

                System.out.println("parameterType:" + parameterType.getName());
            }

            System.out.println("-----------------------------------");
        }
    }

    @Test
    public void methods() {

        Class clz = getDemoClass();
        /***
         * public
         */
        Method[] methods = clz.getMethods();

        for (Method method : methods) {

            LogMethodInfo(method);
        }

        System.out.println("=======================================================");

        Method[] declaredMethods = clz.getDeclaredMethods();

        for (Method declaredMethod : declaredMethods) {

            LogMethodInfo(declaredMethod);
        }
    }

    @Test
    public void testMethod() {

        Class clz = getDemoClass();

        try {
            Constructor constructor = clz.getDeclaredConstructor(String.class);

            Object instance = constructor.newInstance("gavin");

            Method method = clz.getDeclaredMethod("love", String.class);
            method.setAccessible(true);

            method.invoke(instance, "huihui");

            Method study = clz.getDeclaredMethod("study", String.class);
            study.setAccessible(true);
            study.invoke(instance, "英语");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testFiled() throws Exception {

        Class clz = getDemoClass();

        Constructor constructor = clz.getDeclaredConstructor(String.class, int.class);

        constructor.setAccessible(true);

        Object o = constructor.newInstance("gavin", 27);

        Field[] declaredFields = clz.getDeclaredFields();

        for (Field declaredField : declaredFields) {

            String name = declaredField.getName();

            int modifiers = declaredField.getModifiers();

            System.out.println(Modifier.toString(modifiers) + " " + name);
        }

        Field nameField = clz.getDeclaredField("age");

        nameField.setAccessible(true);

        Object o1 = nameField.get(o);

        System.out.println((int)o1);

        nameField.set(o, 30);

        System.out.println("修改后" + nameField.getInt(o));

        Field addressFiled = clz.getDeclaredField("address");
        addressFiled.setAccessible(true);

        Object add = addressFiled.get(o);

        System.out.println("修改前add:" + add);

        addressFiled.set(o, "南泥湾");

        System.out.println("修改后add:" + addressFiled.get(null));

        Student.prientAddress();
    }

    private Class getDemoClass() {
        try {
            return Class.forName("com.alizhezi.demo.reflex.Student");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    private void LogMethodInfo(Method method) {

        System.out.println("方法公私："
                               + Modifier.toString(method.getModifiers())
                               + "  方法的名字："
                               + method.getName()
                               + ",返回类型"
                               + method.getReturnType().getName());
    }
}
