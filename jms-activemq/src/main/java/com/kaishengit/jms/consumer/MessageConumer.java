package com.kaishengit.jms.consumer;


import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;
import javax.xml.soap.Text;

@Component
public class MessageConumer{


    @JmsListener(destination = "spring-queue")
    public void queueMessageConumer(Message message){

        TextMessage textMessage = (TextMessage) message;

        try {
            String text = textMessage.getText();
            System.out.println("获取消息内容--》" + text);
        } catch (JMSException e) {
            e.printStackTrace();
        }

    }

       @JmsListener(destination = "spring-topic",containerFactory = "jmsTopicListenerContainerFactory")
        public void TopicMessageConumer(Message message){
            TextMessage textMessage = (TextMessage) message;

            try {
                String text = textMessage.getText();
                System.out.println("获取主题内容--》》" + text);
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }


}
