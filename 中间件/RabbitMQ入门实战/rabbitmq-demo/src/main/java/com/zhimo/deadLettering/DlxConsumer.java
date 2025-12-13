
package com.zhimo.deadLettering;

import com.rabbitmq.client.*;


/**
 * @Author <a href="https://github.com/Agan-ippe">知莫</a>
 * @Date 2025/12/06   21:47
 * @Version 1.0
 * @Description 死信队列消费者 - 处理死信消息
 */
public class DlxConsumer {
    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        System.out.println("死信队列消费者已启动，等待死信消息...");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [x] Received '" +
                    delivery.getEnvelope().getRoutingKey() + "':'" + message + "'");
        };

        channel.basicConsume(DlxConfig.DLX_QUEUE, true, deliverCallback, consumerTag -> { });
    }
}
