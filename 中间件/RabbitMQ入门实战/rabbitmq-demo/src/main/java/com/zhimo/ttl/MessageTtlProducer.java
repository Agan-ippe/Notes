
package com.zhimo.ttl;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * @Author <a href="https://github.com/Agan-ippe">知莫</a>
 * @Date 2025/12/06   20:18
 * @Version 1.0
 * @Description 消息TTL生产者，演示为单独消息设置TTL
 */
public class MessageTtlProducer {

    private final static String QUEUE_NAME = "message_ttl_demo";

    public static void main(String[] argv) throws Exception {
        // 创建一个连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        // 主机地址
        factory.setHost("localhost");
        // 创建一个连接
        try (Connection connection = factory.newConnection();
             // 创建一个信道
             Channel channel = connection.createChannel()) {
            // 创建普通队列，不设置队列TTL
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);

            Scanner scanner = new Scanner(System.in);
            System.out.println("消息TTL生产者已启动，输入消息发送（输入'exit'退出）:");
            while (scanner.hasNext()) {
                String message = scanner.nextLine();
                if ("exit".equalsIgnoreCase(message)) {
                    break;
                }
                // 发送带有TTL的消息（5秒过期）
                AMQP.BasicProperties ttlProperties = new AMQP.BasicProperties.Builder()
                        // 5秒TTL
                        .expiration("10000")
                        .build();
                channel.basicPublish("", QUEUE_NAME, ttlProperties, message.getBytes(StandardCharsets.UTF_8));
                System.out.println(" [x] 发送带有TTL的消息: '" + message + "'");
            }
            System.out.println("消息TTL生产者已关闭");
        }
    }
}
