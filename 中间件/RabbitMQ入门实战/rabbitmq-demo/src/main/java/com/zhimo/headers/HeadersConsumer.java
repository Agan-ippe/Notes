
package com.zhimo.headers;

import com.rabbitmq.client.*;

import java.util.HashMap;
import java.util.Map;

public class HeadersConsumer {
    private static final String EXCHANGE_NAME = "headers_exchange";
    private static final String QUEUE_NAME_ELECTRONICS = "electronics_queue";
    private static final String QUEUE_NAME_CLOTHING = "clothing_queue";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        // 声明头交换机
        channel.exchangeDeclare(EXCHANGE_NAME, "headers");

        // 声明电子产品队列
        channel.queueDeclare(QUEUE_NAME_ELECTRONICS, false, false, false, null);

        // 绑定电子产品队列，匹配category为electronics的消息
        Map<String, Object> electronicsHeaders = new HashMap<>();
        electronicsHeaders.put("category", "electronics");
        electronicsHeaders.put("x-match", "any"); // any表示任意一个header匹配即可
        channel.queueBind(QUEUE_NAME_ELECTRONICS, EXCHANGE_NAME, "", electronicsHeaders);

        // 声明服装队列
        channel.queueDeclare(QUEUE_NAME_CLOTHING, false, false, false, null);

        // 绑定服装队列，匹配category为clothing的消息
        Map<String, Object> clothingHeaders = new HashMap<>();
        clothingHeaders.put("category", "clothing");
        clothingHeaders.put("x-match", "all"); // all表示所有header都匹配
        channel.queueBind(QUEUE_NAME_CLOTHING, EXCHANGE_NAME, "", clothingHeaders);

        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        // 创建电子产品队列的回调
        DeliverCallback electronicsCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [" + QUEUE_NAME_ELECTRONICS + "] Received '" + message + "'");
            System.out.println("  Headers: " + delivery.getProperties().getHeaders());
        };

        // 创建服装队列的回调
        DeliverCallback clothingCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [" + QUEUE_NAME_CLOTHING + "] Received '" + message + "'");
            System.out.println("  Headers: " + delivery.getProperties().getHeaders());
        };

        // 开始消费消息
        channel.basicConsume(QUEUE_NAME_ELECTRONICS, true, electronicsCallback, consumerTag -> { });
        channel.basicConsume(QUEUE_NAME_CLOTHING, true, clothingCallback, consumerTag -> { });
    }
}
