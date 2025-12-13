
package com.zhimo.deadLettering;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author <a href="https://github.com/Agan-ippe">知莫</a>
 * @Date 2025/12/06   21:47
 * @Version 1.0
 * @Description 死信队列配置类
 */
public class DlxConfig {
    // 正常交换机和队列
    public static final String NORMAL_EXCHANGE = "normal_exchange";
    public static final String NORMAL_QUEUE = "normal_queue";

    // 死信交换机和队列
    public static final String DLX_EXCHANGE = "dlx_exchange";
    public static final String DLX_QUEUE = "dlx_queue";

    // 路由键
    public static final String NORMAL_ROUTING_KEY = "normal";
    public static final String DLX_ROUTING_KEY = "dlx";

    /**
     * 设置死信队列
     */
    public static void setupDlxQueue() throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {

            // 声明死信交换机
            channel.exchangeDeclare(DLX_EXCHANGE, "direct");
            // 声明死信队列
            channel.queueDeclare(DLX_QUEUE, false, false, false, null);

            // 绑定死信队列到死信交换机
            channel.queueBind(DLX_QUEUE, DLX_EXCHANGE, DLX_ROUTING_KEY);

            // 声明正常交换机
            channel.exchangeDeclare(NORMAL_EXCHANGE, "direct");

            // 设置正常队列的参数，指定死信交换机和路由键
            Map<String, Object> args = new HashMap<>();
            // 设置死信交换机
            args.put("x-dead-letter-exchange", DLX_EXCHANGE);
            // 设置死信路由键
            args.put("x-dead-letter-routing-key", DLX_ROUTING_KEY);
            // 设置队列的最大长度（用于演示队列已满的情况）
            args.put("x-max-length", 5);
            // 设置消息的TTL（Time To Live）为20秒（用于演示消息过期的情况）
            args.put("x-message-ttl", 20000);

            //TODO 以下两个配置可以试一下，都注释，默认的策略就是删除头部（移除最早的消息）

            // 将拒绝的消息放入死信队列，同一参数，选择一个配置即可
//            args.put("x-overflow", "reject-publish-dlx");
            // 拒绝新的消息，而不会放入死信队列，同一参数，选择一个配置即可
//            args.put("x-overflow", "reject-publish");


            // 声明正常队列，并传入死信参数
            channel.queueDeclare(NORMAL_QUEUE, false, false, false, args);
            // 绑定正常队列到正常交换机
            channel.queueBind(NORMAL_QUEUE, NORMAL_EXCHANGE, NORMAL_ROUTING_KEY);
            System.out.println("死信队列设置完成！");
        }
    }
}
