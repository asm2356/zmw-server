package com.iflytek.manager.service.activeMq;

import com.iflytek.common.model.Letter;
import com.iflytek.manager.api.LetterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;

@Component
public class ChatListenter {
    @Autowired
    private LetterService letterService;

    @JmsListener(destination = "#{T(com.iflytek.common.constant.ConfigConstant).CHAT_QUEUE}", containerFactory = "defaultJmsListenerContainerFactory")
    public void getObject(ObjectMessage objectMessage) {
        try {
            Letter letter = (Letter) objectMessage.getObject();
            letterService.writeLetter(letter);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
