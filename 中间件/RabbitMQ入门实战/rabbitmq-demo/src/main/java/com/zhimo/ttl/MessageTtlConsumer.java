
package com.zhimo.ttl;

import com.rabbitmq.client.*;

import java.nio.charset.StandardCharsets;

/**
 * @Author <a href="https://github.com/Agan-ippe">知莫</a>
 * @Date 2025/12/06   20:19
 * @Version 1.0
 * @Description 消息TTL消费者，演示消息TTL的效果
 */
public class MessageTtlConsumer {

    private final static String QUEUE_NAME = "message_ttl_demo";

    public static void main(String[] argv) throws Exception {
        // 创建一个连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        // 主机地址
        factory.setHost("localhost");
        // 创建一个连接
        Connection connection = factory.newConnection();
        // 创建一个信道
        Channel channel = connection.createChannel();
        // 声明队列，确保队列存在
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        System.out.println(" [*] 等待消息。要退出请按 CTRL+C");

        // 创建消费者回调
        DeliverCallback callback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
            System.out.println(" [x] 接收到消息 '" + message + "'");

            // 检查消息是否有TTL属性
            if (delivery.getProperties().getExpiration() != null) {
                System.out.println("    - 此消息有过期时间: " + delivery.getProperties().getExpiration() + "毫秒");
            } else {
                System.out.println("    - 此消息没有设置过期时间");
            }
        };

        // 开始消费消息
        channel.basicConsume(QUEUE_NAME, true, callback, consumerTag -> {});
    }
}
