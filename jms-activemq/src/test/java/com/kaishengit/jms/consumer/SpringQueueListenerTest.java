package com.kaishengit.jms.consumer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import java.io.IOException;
import java.util.Date;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-jms-queue-4.xml")
public class SpringQueueListenerTest {

        @Autowired
        private JmsTemplate jmsTemplate;

        @Test
        public void sendMessageToqueue() throws IOException, InterruptedException {
            while (true){

                jmsTemplate.send(new MessageCreator() {
                    public Message createMessage(Session session) throws JMSException {
                        return session.createTextMessage("Hello,Spring + JMS -" + new Date().getTime());
                    }
                });
                Thread.sleep(2000);
                System.in.read();
            }




            }








        @Test
        public void consumerMessage() throws IOException {
            System.out.println("Spring start...");
            System.in.read();
        }


}