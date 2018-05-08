package com.kaishengit.jms.consumer;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class SpringQueueListener implements MessageListener {


    public void onMessage(Message message) {
        TextMessage textMessage = (TextMessage) message;
        try {
            String text = textMessage.getText();
            System.out.println("------>" + text);
            message.acknowledge();
        } catch (JMSException e) {
            e.printStackTrace();
        }

    }

}
