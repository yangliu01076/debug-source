package com.my;

/**
 * @author duoyian
 * @date 2026/7/28
 */
public class Parent {

    public void test() {
        System.out.println("parent");
        this.test2();
        System.out.println(this.getClass());
    }

    public void test2() {
        System.out.println("parent2");
        System.out.println(this.getClass());
    }
}
