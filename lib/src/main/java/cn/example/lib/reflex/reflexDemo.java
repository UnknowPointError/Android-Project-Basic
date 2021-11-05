package cn.example.lib.reflex;

import java.lang.reflect.Field;

/*
 * 全类名：包名.类名
 * */
public class reflexDemo {

    public static void main(String[] args) throws Exception {
        System.out.println("----------------------");
        Person Tom = new Person("What", 60);
        Tom.introduce();
        Class cls1 = Class.forName("cn.example.lib.reflex.Person");
        System.out.println(cls1);
        Class cls2 = Person.class;
        System.out.println(cls2);
        Class cls3 = Tom.getClass();
        System.out.println(cls3);
        // cls1 cls2 cls3 是一个引用类型
        System.out.println(cls1 == cls2);

        // 获取成员getField方法得到公共的对象
        Field field1 = cls2.getField("name");
        System.out.println(field1);

        // 获取成员getDeclaredField方法得到私有的对象
        Field field2 = cls2.getDeclaredField("age");
        System.out.println(field2);

        // 遍历类的每一个成员变量
        Field[] field3 = cls2.getDeclaredFields();
        for (Field field : field3) {
            System.out.println(field3);
        }
        field1.set(Tom, "Barry");
        System.out.println(field2);
        Tom.introduce();
        System.out.println("----------------------");
    }
}
