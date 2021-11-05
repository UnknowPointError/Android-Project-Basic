package cn.example.lib.reflex;

public class Person {

    public String name;
    private int age;

    public Person() {
        System.out.println("non-parameter constructor");
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void eat() {
        System.out.println("eat...");
    }


    public void eat(String what) {
        System.out.println("eat : " + what);
    }

    public void introduce() {
        System.out.println("My name is  : " + this.name + "age is : " + age);
    }
}
