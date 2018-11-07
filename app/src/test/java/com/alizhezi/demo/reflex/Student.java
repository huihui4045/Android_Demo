package com.alizhezi.demo.reflex;

public class Student extends Person {

    public String id;
    protected int age;
    int weight;
    private static String address = "默认地址";
    private String name = "默认名字";

    public static void prientAddress() {

        System.out.println("地址：" + address);
    }

    public Student() {
    }

    public Student(String name) {
        this.name = name;
    }

    protected Student(int id) {
        this.id = name;
    }

    public Student(String name, int age, String id, int weight) {
        this.name = name;
        this.age = age;
        this.id = id;
        this.weight = weight;
    }

    private Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Student(String name, int age, String id) {
        this.name = name;
        this.age = age;
        this.id = id;
    }

    private void work() {

        System.out.println("private void work");
    }

    protected void eat() {

        System.out.println("protected void eat");
    }

    private static void study(String type) {

        System.out.println("public void study  " + type);
    }

    private void love(String name) {

        System.out.println(this.name + "  love:" + name);
    }

    private String loveTo(String name) {

        return this.name + "  love:" + name;
    }

    @Override
    public String toString() {
        return "Student{"
            + "name='"
            + name
            + '\''
            + ", age="
            + age
            + ", id='"
            + id
            + '\''
            + ", weight="
            + weight
            + '}';
    }
}
