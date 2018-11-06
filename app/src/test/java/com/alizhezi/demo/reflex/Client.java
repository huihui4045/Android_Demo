package com.alizhezi.demo.reflex;

import java.lang.reflect.Constructor;
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

    }
}
