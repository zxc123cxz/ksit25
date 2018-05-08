package com.kaishengit.jms.consumer;

import org.springframework.jms.listener.SessionAwareMessageListener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

public class SpringQueueListener3 implements SessionAwareMessageListener{
    public void onMessage(Message message, Session session) throws JMSException {
        TextMessage textMessage = (TextMessage) message;
        String text = textMessage.getText();

        if(1==1){
            throw new RuntimeException("无敌的寂寞：" + text);
        }

    }
}
