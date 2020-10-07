package com.iflytek.front.web.shiro;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * @author AZhao
 */
public class MySessionListener implements SessionListener {
    private Logger logger = LoggerFactory.getLogger(MySessionListener.class);
    @Override
    public void onStart(Session session) {
        logger.info("onStart"+session);
    }

    @Override
    public void onStop(Session session) {
        logger.info("onStop"+session);
    }

    @Override
    public void onExpiration(Session session) {
        logger.info("onExpiration"+session);
    }
}
