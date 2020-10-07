package com.iflytek.manager.service.activeMq;

import com.iflytek.common.enumeration.user.ArticleStatus;
import com.iflytek.common.model.Article;
import com.iflytek.common.utils.ApplicationContextUtil;
import com.iflytek.manager.api.ArticleService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import javax.jms.JMSException;
import javax.jms.ObjectMessage;

@Component
public class ArticleReleaseConsumer {
    private Logger logger = Logger.getLogger(ArticleReleaseConsumer.class);
    @Autowired
    private  ArticleService articleService;

    @JmsListener(destination ="#{T(com.iflytek.common.constant.ConfigConstant).ARTICLE_RELEASE_QUEUE}", containerFactory = "defaultJmsListenerContainerFactory")
    public void getMessage(ObjectMessage objectMessage) {
        try {
            Article article = (Article) objectMessage.getObject();
            article.setStatus(ArticleStatus.HAS_RELEASE.getValue());
            articleService.writeArticle(article);
        } catch (JMSException e) {
            logger.info(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
