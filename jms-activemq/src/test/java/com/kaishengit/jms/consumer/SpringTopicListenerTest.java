package com.kaishengit.jms.consumer;

import org.apache.activemq.command.ActiveMQTopic;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.jms.*;

import java.util.Date;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-jms-queue-3.xml")
public class SpringTopicListenerTest {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Test
    public void sendMessage() throws InterruptedException {
        while (true){
            Destination destination = new ActiveMQTopic("spring-topic");
            jmsTemplate.send(destination,new MessageCreator() {
                public Message createMessage(Session session) throws JMSException {
                    return session.createTextMessage("Hello-Topic" + new Date().getTime());
                }
            });
            Thread.sleep(2000);
        }


    }



}