package com.iflytek.manager.service;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;

/**
 * @author AZhao
 */
public class ManagerApplication {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath*:spring/managerServiceContext.xml");
        applicationContext.start();
        System.out.println("===========ManagerService启动成功===========");
        synchronized (ManagerApplication.class) {
            try {
                ManagerApplication.class.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            new HashMap();
        }
    }
}
