package cn.example.lib.reflex;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/*
 * 全类名: 包名.类名
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
        Field field1 = cls1.getField("name");
        System.out.println(field1);

        // 获取成员getDeclaredField方法得到私有的对象
        Field field2 = cls1.getDeclaredField("age");
        System.out.println(field2);

        // 遍历类的每一个成员变量
        Field[] field3 = cls1.getDeclaredFields();
        for (Field field : field3) {
            System.out.println(field);
        }
        field2.set(Tom, 20);
        System.out.println(field2);
        Tom.introduce();
        System.out.println("----------------------");
        System.out.println("age is " + field2.get(Tom));
        Method method = cls1.getMethod("eat");
        System.out.println(method);
        method.invoke(Tom);
        System.out.println("----------------------");
        Method method1 = cls1.getMethod("eat", String.class);
        method1.invoke(Tom, "Apple");
        Method[] method2 = cls1.getDeclaredMethods();
        for (Method method3 : method2) {
            System.out.println(method3);
            System.out.println("function name is : " + method3.getName());
            System.out.println("function params count is : " + method3.getParameterCount());
            Class[] params = method3.getParameterTypes();
            for (Class param : params) {
                System.out.println("param is : " + param);
            }
        }
        System.out.println("----------------------");
        System.out.println(cls1.getConstructors());
    }
}
