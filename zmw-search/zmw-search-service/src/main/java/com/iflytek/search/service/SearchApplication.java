package com.iflytek.search.service;

import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author AZhao
 */
public class SearchApplication {
    private static Logger logger = Logger.getLogger(SearchApplication.class);
    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath*:spring/searchServiceContext.xml");
        applicationContext.start();
        logger.info("===========SearchService启动成功===========");
        synchronized (SearchApplication.class) {
            try {
                SearchApplication.class.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
