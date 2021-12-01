package demos;

import com.rabbitmq.client.*;
import demo.start.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author Zml
 * @createDate 2021-09-02
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class RabbitMqTest {

    @Test
    public void test() throws IOException, TimeoutException{

        //创建RabbitMq连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        //设置RabbitMQ连接的ip和端口
        connectionFactory.setHost("192.168.136.129");
        connectionFactory.setPort(5672);
        //设置连接的虚拟主机
        connectionFactory.setVirtualHost("127.0.0.1");
        //设置房屋虚拟主机的用户名密码(必须有权限)
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");

        //获取连接对象
        Connection connection = connectionFactory.newConnection();

        //获取连接通道
        Channel channel = connection.createChannel();
        //通道绑定对应的消息队列
        //参数1：queue:队列名称，如果队列不存在自动创建
        //参数2：durable：定义队列是否要持久化 TRUE 持久化 FALSE 不持久化
        //参数3：exclusive：当前连接是否独占队列，其他连接不可用，TRUE独占，FALSE不独占
        //参数4：autoDelete：是否在消费完成后自动删除队列，TRUE自动删除 FALSE 不自动删除
        //参数5 map:附件参数
        channel.queueDeclare("queuename",false,false,false,null);

        //发布消息
        //参数1：交换机名称
        //参数2：队列名称
        //参数3：传递消息额外设置 MessageProperties.PERSISTENT_TEXT_PLAIN 消息持久化
        //参数4: 消息
        channel.basicPublish("","queuename",null,"这是一个消息".getBytes());

        //关闭通道
        channel.close();
        //关闭链接
        connection.close();
    }

    public static void main(String[] args)throws Exception{
        //创建RabbitMQ链接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        //设置RabbitMQ链接的IP和端口
        connectionFactory.setHost("192.168.136.129");
        connectionFactory.setPort(5672);
        //设置连接的虚拟主机
        connectionFactory.setVirtualHost("/ems");
        //设置访问虚拟主机的用户名密码(必须有权限)
        connectionFactory.setUsername("ems");
        connectionFactory.setPassword("123");
        //获取连接对象
        Connection connection = connectionFactory.newConnection();

        //创建通道
        Channel channel = connection.createChannel();
        //通道绑定队列
        channel.queueDeclare("queuename",false,false,false,null);

        //消费消息
        //参数1：消费的队列名称
        //参数2：开启消息自动确认，消费者自动确认消息消费
        //参数3：消费时的回调接口
        channel.basicConsume("queuename",true,new DefaultConsumer(channel){
            //body 消息队列中取出的消息
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费消息=========" + new String(body));
                //super.handleDelivery(consumerTag, envelope, properties, body);
            }
        });
    }
}
