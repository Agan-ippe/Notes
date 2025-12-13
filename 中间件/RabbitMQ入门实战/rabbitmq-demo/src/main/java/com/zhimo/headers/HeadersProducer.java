
package com.zhimo.headers;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.util.HashMap;
import java.util.Map;

public class HeadersProducer {
    private static final String EXCHANGE_NAME = "headers_exchange";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {

            // 声明头交换机
            channel.exchangeDeclare(EXCHANGE_NAME, "headers");

            // 创建消息头
            Map<String, Object> headers1 = new HashMap<>();
            headers1.put("category", "electronics");
            headers1.put("type", "phone");

            Map<String, Object> headers2 = new HashMap<>();
            headers2.put("category", "electronics");
            headers2.put("type", "laptop");

            Map<String, Object> headers3 = new HashMap<>();
            headers3.put("category", "clothing");
            headers3.put("type", "shirt");

            // 发送消息
            String message1 = "这是一部手机";
            channel.basicPublish(EXCHANGE_NAME, "", 
                new com.rabbitmq.client.AMQP.BasicProperties.Builder()
                    .headers(headers1)
                    .build(),
                message1.getBytes("UTF-8"));

            String message2 = "这是一台笔记本电脑";
            channel.basicPublish(EXCHANGE_NAME, "", 
                new com.rabbitmq.client.AMQP.BasicProperties.Builder()
                    .headers(headers2)
                    .build(),
                message2.getBytes("UTF-8"));

            String message3 = "这是一件衬衫";
            channel.basicPublish(EXCHANGE_NAME, "", 
                new com.rabbitmq.client.AMQP.BasicProperties.Builder()
                    .headers(headers3)
                    .build(),
                message3.getBytes("UTF-8"));

            System.out.println(" [x] Sent messages with headers");
        }
    }
}
