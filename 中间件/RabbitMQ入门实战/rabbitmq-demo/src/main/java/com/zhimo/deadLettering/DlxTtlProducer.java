
package com.zhimo.deadLettering;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.util.Scanner;

/**
 * @Author <a href="https://github.com/Agan-ippe">知莫</a>
 * @Date 2025/12/06   21:47
 * @Version 1.0
 * @Description 死信队列生产者
 */
public class DlxTtlProducer {
    public static void main(String[] argv) throws Exception {
        // 首先设置死信队列
        DlxConfig.setupDlxQueue();

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {

            Scanner scanner = new Scanner(System.in);
            System.out.println("死信队列生产者已启动，请输入消息：");
            while (scanner.hasNext()) {
                String message = scanner.nextLine();
                // 消息持久化
                channel.basicPublish(DlxConfig.NORMAL_EXCHANGE,
                        DlxConfig.NORMAL_ROUTING_KEY,
                        null,
                        message.getBytes("UTF-8"));
                System.out.println(" [x] Sent '" + message + "' with routing '" + DlxConfig.NORMAL_ROUTING_KEY+ "'");
            }
        }
    }
}
