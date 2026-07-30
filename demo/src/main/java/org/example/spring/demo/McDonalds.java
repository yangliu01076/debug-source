package org.example.spring.demo;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class McDonalds {
    private final Lock lock = new ReentrantLock();

    // 【关键点 1】：两个独立的等待室
    // 服务员专用的等待室（等待放餐台不满）
    private final Condition notFull = lock.newCondition();
    // 顾客专用的等待室（等待放餐台不空）
    private final Condition notEmpty = lock.newCondition();

    private final String[] meals = new String[5]; // 只能放 5 个
    private int count = 0; // 当前数量

    // 服务员做饭
    public void put(String meal) throws InterruptedException {
        lock.lock();
        try {
            // 如果满了，我就去“notFull”房间睡觉
            while (count == meals.length) {
                System.out.println("放餐台满了，服务员去睡觉了...");
                notFull.await();
            }

            // 做饭
            meals[count] = meal;
            count++;
            System.out.println("服务员做好了：" + meal + "，当前数量：" + count);

            // 【关键点 2】：做好了，要去“notEmpty”房间的叫醒一个顾客
            // 注意：这里只叫顾客，不会吵醒其他睡觉的服务员！
            notEmpty.signal();

        } finally {
            lock.unlock();
        }
    }

    // 顾客拿饭
    public void take() throws InterruptedException {
        lock.lock();
        try {
            // 如果空了，我就去“notEmpty”房间睡觉
            while (count == 0) {
                System.out.println("没有饭了，顾客去睡觉了...");
                notEmpty.await();
            }

            // 拿饭
            count--;
            String meal = meals[count];
            meals[count] = null;
            System.out.println("顾客拿走了：" + meal + "，当前数量：" + count);

            // 【关键点 3】：拿走了，腾出位置了，去“notFull”房间叫醒一个服务员
            // 注意：这里只叫服务员，不会吵醒其他睡觉的顾客！
            notFull.signal();

        } finally {
            lock.unlock();
        }
    }

    // 测试
    public static void main(String[] args) {
        McDonalds shop = new McDonalds();

        // 启动一个顾客一直拿
        new Thread(() -> {
            try { while(true) { Thread.sleep(1000); shop.take(); } } catch(Exception e){}
        }).start();

        // 启动两个服务员一直做
        new Thread(() -> {
            try { while(true) { Thread.sleep(500); shop.put("汉堡"); } } catch(Exception e){}
        }).start();

        new Thread(() -> {
            try { while(true) { Thread.sleep(600); shop.put("可乐"); } } catch(Exception e){}
        }).start();
    }
}
