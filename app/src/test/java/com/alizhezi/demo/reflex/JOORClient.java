package com.alizhezi.demo.reflex;

import org.joor.Reflect;
import org.junit.Test;

public class JOORClient {

    private String className = "com.alizhezi.demo.reflex.Student";

    @Test
    public void testCon() {

        Student student = Reflect.on(Student.class).create("张三", 29).get();

        System.out.println(student.toString());

        Reflect reflect = Reflect.on(Student.class).create("张三", 29);

        reflect.call("love", "huihui").get();
        String str = reflect.call("loveTo", "huihui").get();

        System.out.println("str:" + str);
    }

    @Test
    public void testRefInvoke() {

        RefInvoke.invokeInstanceMethod(RefInvoke.createObject(className, null, null), "study",
                                       new Class[] { String.class }, new Object[] { "美女" });

        RefInvoke.invokeStaticMethod(className, "study", new Class[] { String.class },
                                     new Object[] { "英文" });
    }

    @Test
    public void testFiled() {

        Student student = new Student();

        RefInvoke.setFieldObject(className, student, "name", "张三");

        System.out.println("value: " + RefInvoke.getFieldObject(className, student, "name"));

        System.out.println("value: " + RefInvoke.getFieldObject(className, null, "address"));
    }
}
