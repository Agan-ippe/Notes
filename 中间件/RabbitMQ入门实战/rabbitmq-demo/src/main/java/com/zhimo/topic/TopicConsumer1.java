package com.zhimo.topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

public class TopicConsumer1 {

    private static final String EXCHANGE_NAME = "topic_exchange";
    private static final String QUEUE_NAME1 = "frontend";
    private static final String QUEUE_NAME2 = "backend";
    private static final String QUEUE_NAME3 = "product";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, "topic");
        channel.queueDeclare(QUEUE_NAME1, false, false, false, null);
        channel.queueBind(QUEUE_NAME1, EXCHANGE_NAME, "*.frontend.#");

        channel.exchangeDeclare(EXCHANGE_NAME, "topic");
        channel.queueDeclare(QUEUE_NAME2, false, false, false, null);
        channel.queueBind(QUEUE_NAME2, EXCHANGE_NAME, "*.backend.#");

        channel.exchangeDeclare(EXCHANGE_NAME, "topic");
        channel.queueDeclare(QUEUE_NAME3, false, false, false, null);
        channel.queueBind(QUEUE_NAME3, EXCHANGE_NAME, "#.product");

        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        DeliverCallback deliverCallback1 = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [" + QUEUE_NAME1 + "] Received '" +
                    delivery.getEnvelope().getRoutingKey() + "':'" + message + "'");
        };

        DeliverCallback deliverCallback2 = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [" + QUEUE_NAME2 + "] Received '" +
                    delivery.getEnvelope().getRoutingKey() + "':'" + message + "'");
        };

        DeliverCallback deliverCallback3 = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [" + QUEUE_NAME3 + "] Received '" +
                    delivery.getEnvelope().getRoutingKey() + "':'" + message + "'");
        };
        channel.basicConsume(QUEUE_NAME1, true, deliverCallback1, consumerTag -> { });
        channel.basicConsume(QUEUE_NAME2, true, deliverCallback2, consumerTag -> { });
        channel.basicConsume(QUEUE_NAME3, true, deliverCallback3, consumerTag -> { });
    }
}