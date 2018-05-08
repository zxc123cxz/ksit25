import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;

import javax.jms.*;
import java.io.IOException;

public class PubSubTest {


    /*
     *  发布主题
     * @date 2018/5/8
     * @param
     * @return
     */
    @Test
    public void createTopic() throws JMSException {
        //创建连接工厂
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");

        //创建连接并开启
        Connection connection = connectionFactory.createConnection();
        connection.start();

        //创建Session（是否需要事物）
        final Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);

        //创建消息主题
        Topic topic = session.createTopic("Hello-topic");

        //创建消息生产者
        MessageProducer messageProducer = session.createProducer(topic);

        //发布主题
        TextMessage textMessage = session.createTextMessage("Hello-topic-2");
        messageProducer.send(textMessage);

        //释放资源
        messageProducer.close();
        session.close();
        connection.close();

    }


    @Test
    public void consumerTopic() throws JMSException, IOException {
        //创建连接工厂
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");

        //创建连接并开启
        Connection connection  =  connectionFactory.createConnection();
        connection.start();

        //创建session
        Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);

        //创建消息主题
        Topic topic = session.createTopic("Hello-topic");

        //创建消费者
        MessageConsumer messageConsumer = session.createConsumer(topic);

        //配置消息的监听器
        messageConsumer.setMessageListener(new MessageListener() {
            public void onMessage(Message message) {
                TextMessage textMessage = (TextMessage) message;
                try {
                    String text = textMessage.getText();
                    System.out.println(text);
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
    public void consumerTopics() throws JMSException, IOException {
        //创建连接工厂
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");

        //创建连接并开启
        Connection connection  =  connectionFactory.createConnection();
        connection.start();

        //创建session
        Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);

        //创建消息主题
        Topic topic = session.createTopic("Hello-topic");

        //创建消费者
        MessageConsumer messageConsumer = session.createConsumer(topic);

        //配置消息的监听器
        messageConsumer.setMessageListener(new MessageListener() {
            public void onMessage(Message message) {
                TextMessage textMessage = (TextMessage) message;
                try {
                    String text = textMessage.getText();
                    System.out.println(text);
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
