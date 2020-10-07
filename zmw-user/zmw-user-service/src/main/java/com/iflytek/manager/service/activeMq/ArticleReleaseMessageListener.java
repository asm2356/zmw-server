//package com.iflytek.manager.service.activeMq;
//
//import com.iflytek.common.enumeration.user.ArticleStatus;
//import com.iflytek.common.model.Article;
//import com.iflytek.common.utils.ApplicationContextUtil;
//import com.iflytek.manager.api.ArticleService;
//import org.apache.log4j.Logger;
//import org.springframework.jms.annotation.EnableJms;
//import org.springframework.jms.annotation.JmsListener;
//
//import javax.jms.JMSException;
//import javax.jms.Message;
//import javax.jms.MessageListener;
//import javax.jms.ObjectMessage;
//
///**
// * @author zgzhao
// */
//
//public class ArticleReleaseMessageListener implements MessageListener {
//    private Logger logger = Logger.getLogger(ArticleReleaseMessageListener.class);
//    @Override
//    public void onMessage(Message message) {
//        ObjectMessage objectMessage = (ObjectMessage) message;
//        ArticleService articleService = ApplicationContextUtil.getBean("articleService");
//        try {
//            Article article = (Article) objectMessage.getObject();
//            article.setStatus(ArticleStatus.HAS_RELEASE.getValue());
//            articleService.writeArticle(article);
//        } catch (JMSException e) {
//            logger.info(e.getMessage());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
