package cn.example.lib;

public class TestDemo01 {


    public static void main(String[] args) {
//        demo01 de = new demo01();
        demo01.getInstance();
    }
}

class demo01 {

    private demo01() {
        System.out.println("私有构造话方法");
    }

    private static demo01 instance = new demo01();

    public static demo01 getInstance() {
        return instance;
    }
}

