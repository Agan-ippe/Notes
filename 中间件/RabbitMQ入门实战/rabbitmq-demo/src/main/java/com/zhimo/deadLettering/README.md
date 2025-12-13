
# RabbitMQ 死信队列演示

本演示展示了RabbitMQ中死信队列的三种常见使用场景：
1. 消息被拒绝（requeue=false）
2. 消息过期（TTL）
3. 正常队列已满（x-max-length）

## 文件说明

- `DlxConfig.java` - 死信队列配置类，定义了正常队列和死信队列的设置
- `DlxProducer.java` - 生产者类，用于发送消息到正常队列
- `DlxRejectConsumer.java` - 消费者类，演示消息被拒绝的情况
- `DlxQueueConsumer.java` - 死信队列消费者，处理进入死信队列的消息
- `DlxDemo.java` - 演示类，自动演示三种死信情况

## 使用方法

### 方法1：使用DlxDemo自动演示

1. 运行 `DlxDemo` 类，它会自动演示三种死信情况
2. 在另一个终端运行 `DlxQueueConsumer` 类，查看死信队列中的消息

### 方法2：手动演示

1. 首先运行 `DlxConfig.setupDlxQueue()` 设置死信队列
2. 打开两个终端：
   - 终端1：运行 `DlxQueueConsumer` 监听死信队列
   - 终端2：运行 `DlxRejectConsumer` 监听正常队列
3. 在第三个终端运行 `DlxProducer` 发送消息
4. 观察不同消息如何进入死信队列

## 演示说明

### 1. 消息被拒绝
- 发送包含 "reject" 关键字的消息
- `DlxRejectConsumer` 会拒绝这些消息（不重新入队）
- 被拒绝的消息会进入死信队列

### 2. 消息过期
- 正常队列设置了10秒的TTL（Time To Live）
- 如果消息在10秒内没有被消费，会自动进入死信队列

### 3. 队列已满
- 正常队列的最大长度设置为5
- 当队列中的消息数量超过5时，新消息会进入死信队列

## 注意事项

- 确保RabbitMQ服务已启动并运行在localhost
- 演示前最好清空相关队列，以避免旧消息干扰
