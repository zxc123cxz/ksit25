package com.kaishengit.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.RedeliveryPolicy;
import org.junit.Test;

import javax.jms.*;
import java.io.IOException;

public class HelloTest {



    @Test
    public void createManage() throws JMSException {
        //创建连接工厂
        //ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
       /* RedeliveryPolicy redeliveryPolicy = new RedeliveryPolicy();
        //设置重试次数
        redeliveryPolicy.setMaximumRedeliveries(3);
        //设置初次重试延迟时间，单位毫秒
        redeliveryPolicy.setInitialRedeliveryDelay(5000);
        //设置每次重试延迟时间，单位毫秒
        redeliveryPolicy.setRedeliveryDelay(5000);
        connectionFactory.setRedeliveryPolicy(redeliveryPolicy);*/


        //创建连接并开启
        Connection connection = connectionFactory.createConnection();
        connection.start();

        //创建session  true指的是i提交事务
        Session session = connection.createSession(true,Session.CLIENT_ACKNOWLEDGE);
        //创建目的地
        Destination destination = session.createQueue("Hello-ldy");

        //创建生产者
        MessageProducer messageProducer = session.createProducer(destination);
        messageProducer.setDeliveryMode(DeliveryMode.PERSISTENT);

        //发送消息
       TextMessage textMessage = session.createTextMessage("Hello-10");
       messageProducer.send(textMessage);

       //提交事物
        session.commit();

       //释放资源
        messageProducer.close();
        session.close();
        connection.close();

    }


    @Test
    public void consumerManage() throws JMSException, IOException {
        //创建连接工厂
        //ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        //创建连接并开启
        Connection connection = connectionFactory.createConnection();
        connection.start();



        //创建session
        Session session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);

        //创建消息目的地
        Destination destination = session.createQueue("Hello-ldy");

        //创建消费者
        MessageConsumer messageConsumer = session.createConsumer(destination);

        //接受消息
        messageConsumer.setMessageListener(new MessageListener() {
            public void onMessage(Message message) {
                TextMessage textMessage = (TextMessage) message;
                try {
                    String text = textMessage.getText();
                    if("Hello,Message-13".equals(text)){
                        throw new RuntimeException("签收消息异常。。。。。");
                    }
                    System.out.println("Message:......." + text);

                    //手动签收
                    textMessage.acknowledge();

                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });
        System.in.read();

        //释放资源
        messageConsumer.close();
        session.close();
        connection.close();
    }




    @Test
    public void consumerDlQManage() throws JMSException, IOException {
        //创建连接工厂
        //ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        //创建连接并开启
        Connection connection = connectionFactory.createConnection();
        connection.start();



        //创建session
        Session session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);

        //创建消息目的地
        Destination destination = session.createQueue("ActiveMQ.DLQ");

        //创建消费者
        MessageConsumer messageConsumer = session.createConsumer(destination);

        //接受消息
        messageConsumer.setMessageListener(new MessageListener() {
            public void onMessage(Message message) {
                TextMessage textMessage = (TextMessage) message;
                try {
                    String text = textMessage.getText();

                    System.out.println("未发出成功的数据:"+text);
                    //手动签收
                    textMessage.acknowledge();

                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });
        System.in.read();

        //释放资源
        messageConsumer.close();
        session.close();
        connection.close();
    }



}
