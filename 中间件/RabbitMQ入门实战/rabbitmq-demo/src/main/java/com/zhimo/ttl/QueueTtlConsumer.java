
package com.zhimo.ttl;

import com.rabbitmq.client.*;

import java.nio.charset.StandardCharsets;

/**
 * @Author <a href="https://github.com/Agan-ippe">知莫</a>
 * @Date 2025/12/06   20:16
 * @Version 1.0
 * @Description 队列TTL消费者，演示队列TTL的效果
 */
public class QueueTtlConsumer {

    private final static String QUEUE_NAME = "queue_ttl_demo";

    public static void main(String[] argv) throws Exception {
        // 创建一个连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        System.out.println(" [*] 等待消息。要退出请按 CTRL+C");
        System.out.println("队列TTL说明：此队列中的所有消息将在10秒后过期");
        System.out.println("如果您在消息过期前启动消费者，可以正常接收消息");
        System.out.println("如果您在消息过期后才启动消费者，将无法接收已过期的消息");

        // 创建消费者回调
        DeliverCallback callback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
            System.out.println(" [x] 接收到消息 '" + message + "'");
        };
        // 开始消费消息
        channel.basicConsume(QUEUE_NAME, true, callback, consumerTag -> {});
    }
}
