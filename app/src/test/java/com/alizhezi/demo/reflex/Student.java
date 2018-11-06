package com.alizhezi.demo.reflex;

public class Student {

    public String id;
    protected int age;
    int weight;
    private String name;

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

    public void study() {

        System.out.println("public void study");
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
