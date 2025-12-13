
package com.zhimo.ttl;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @Author <a href="https://github.com/Agan-ippe">知莫</a>
 * @Date 2025/12/06   20:15
 * @Version 1.0
 * @Description 队列TTL生产者，演示为队列设置TTL
 */
public class QueueTtlProducer {

    private final static String QUEUE_NAME = "queue_ttl_demo";

    public static void main(String[] argv) throws Exception {
        // 创建一个连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        // 主机地址
        factory.setHost("localhost");
        // 创建一个连接
        try (Connection connection = factory.newConnection();
             // 创建一个信道
             Channel channel = connection.createChannel()) {

            // 创建队列，设置消息在队列中最多存活10秒
            Map<String, Object> args = new HashMap<>();
            args.put("x-message-ttl", 10000); // 10秒TTL
            channel.queueDeclare(QUEUE_NAME, false, false, false, args);

            System.out.println("队列TTL生产者已启动，输入消息并发送（输入'exit'退出）：");
            System.out.println("队列中的所有消息将在10秒后过期");

            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNext()) {
                String input = scanner.nextLine();
                if ("exit".equalsIgnoreCase(input)) {
                    break;
                }

                String message = input + " - 将在10秒后过期";
                channel.basicPublish("", QUEUE_NAME, null, message.getBytes(StandardCharsets.UTF_8));
                System.out.println(" [x] 发送消息: '" + message + "'");
            }

            System.out.println("队列TTL生产者已关闭");
        }
    }
}
