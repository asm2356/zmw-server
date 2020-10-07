package com.iflytek.config.service.shiro;

import com.iflytek.common.constant.RedisPrefix;
import com.iflytek.config.service.app.SystemConfigUtils;
import com.iflytek.config.service.redis.RedisService;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.Collection;

/**
 * @author AZhao
 */
public class ShiroSessionService extends AbstractSessionDAO {
    private long expireTime = Long.parseLong(SystemConfigUtils.getValue("shiro.expireTime"));//s
    private Logger logger = LoggerFactory.getLogger(ShiroSessionService.class);
    @Autowired
    private RedisService redisService;

    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = this.generateSessionId(session);
        this.assignSessionId(session, sessionId);
        redisService.set(RedisPrefix.SHIRO_SESSION + session.getId(), session, expireTime);
        logger.info("create----" + session);
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        if (sessionId == null) {
            return null;
        }
        logger.info("read----" + sessionId);
        return (Session) redisService.get(RedisPrefix.SHIRO_SESSION + sessionId);
    }

    @Override
    public void update(Session session) throws UnknownSessionException {
        if (session == null || session.getId() == null) {
            return;
        }
        logger.info("update----" + session);
        session.setTimeout(expireTime * 1000);
        redisService.set(RedisPrefix.SHIRO_SESSION + session.getId(), session, expireTime);

    }

    @Override
    public void delete(Session session) {
        if (null == session) {
            return;
        }
        logger.info("delete----" + session);
        redisService.del(RedisPrefix.SHIRO_SESSION + session.getId());

    }

    @Override
    public Collection<Session> getActiveSessions() {
        return redisService.getRedisTemplate().keys(RedisPrefix.SHIRO_SESSION + "*");
    }
}
