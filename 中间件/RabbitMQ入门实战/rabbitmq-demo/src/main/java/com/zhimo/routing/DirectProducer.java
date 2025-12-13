package com.zhimo.routing;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.util.Scanner;

/**
 * @Author <a href="https://github.com/Agan-ippe">知莫</a>
 * @Date 2025/12/06   19:30
 * @Version 1.0
 * @Description 路由
 */
public class DirectProducer {

    private static final String EXCHANGE_NAME = "direct-exchange";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.exchangeDeclare(EXCHANGE_NAME, "direct");

            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNext()) {
                String userInput = scanner.nextLine();
                String[] split = userInput.split(" ");
                if (split.length < 1) {
                    continue;
                }
                String message = split[0];
                String routingKey = split[1];

                // 消息持久化
                channel.basicPublish(EXCHANGE_NAME, routingKey,
                        null,
                        message.getBytes("UTF-8"));
                System.out.println(" [x] Sent '" + message + "' with routing '" + routingKey + "'");
            }
        }
    }
}
