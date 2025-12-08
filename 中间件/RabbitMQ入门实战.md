 [RabbitMQ 教程 | RabbitMQ 消息队列](https://rabbitmq.org.cn/tutorials)

# 基本概念

生产者`Producer`：向RabbitMQ发送消息，将消息发到某个交换机

交换机`Exchange`：负责把消息 **转发** 到对应的队列

路由`Routes`：实现 *交换机* 和 *队列* 之间的绑定关系，决定交换机把消息转发到哪个队列

队列`Queue`：存储消息

消费者`Consumer`：从RabbitMQ接收消息，从队列中取消息



![RabbitMQ图解](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/RabbitMQ图解.png)

- `Broker`：接收和分发消息的应用，其实Broker就是RabbitMQ Server。
- `Connection`：是生产者和消费者与Broker之间的TCP连接。
- `Channel`：信道/通道
- `Virtual Host`：虚拟分组（类似于namespace），多个用户使用同一个Broker的时候，会在各自的Virtual Host中创建自己的Exchange/Queue。



## 交换机

交换机是消息的路由中心，它接收从生产者发送的消息，并根据预定义的规则将它们路由到一个或多个队列中。RabbitMQ支持多种类型的交换机，包括直连、主题、扇出和头交换机。

![image-20251205181551331](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20251205181551331.png)



### 交换机的类别

- Fanout（扇形交换机）
- Topic（主题交换机）
- Direct（直连交换机）
- Headers（头交换机 ）





### 扇形交换机

![image-20251205203554511](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20251205203554511.png)

将消息转发给所有绑定到自身的队列，路由键在扇形交换机里没有作用，故消息队列绑定扇形交换机时，路由键可为空。

**适用场景：发布/订阅**

[使用案例](#发布/订阅)



### 直连交换机

![image-20251206205355493](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20251206205355493.png)

队列与交换机通过一个 **路由键** 绑定在一起，一个队列和交换机之间可以多个绑定关系，同一个路由键也可以绑定多个队列。

特点：消息会根据路由键被转发到指定的交换机

> 示例

可以看到交换机绑定了两个队列。第一个队列绑定了绑定 key `orange`，第二个队列有两个绑定，一个绑定 key 是 `black`，另一个是 `green`。

在这种设置下，发布到具有路由 key `orange` 的交换机的消息将被路由到队列 `Q1`。具有路由 key `black` 或 `green` 的消息将发送到 `Q2`。所有其他消息将被丢弃。

![image-20251206210121726](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20251206210121726.png)

---

**多个绑定**

示例中，一个交换机和两个队列之间绑定，绑定 key 为 `black`。在这种情况下，`direct` 交换机将表现得像 `fanout`，并将消息广播给所有匹配的队列。路由 key 为 `black` 的消息将被传递给 `Q1` 和 `Q2`。

![image-20251206210301064](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20251206210301064.png)

---

使用场景：消息只交给特定的系统来处理

[使用案例](#路由)



### 主题交换机

类似于订阅模式，当一个Msg和路由键规则发送到一个主题交换机时，交换机会根据路由键规则来筛选出符合规则的绑定到自身消息队列的路由键（可能是1个，也可能是N个，也可能是0个），根据符合的路由键，将消息发送到其对应的消息队列里。

绑定规则：

- `*` 可以替代正好一个单词。
- `#` 可以替代零个或多个单词。

![image-20251208161251891](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20251208161251891.png)



使用场景：将某一类主题的消息交给特定的系统处理

[使用案例](#主题)



## 绑定

绑定也可以叫路由。

是交换机和队列之间的关联关系。它定义了如何将消息从交换机路由到队列。绑定通常使用路由键（Routing Key）来指定消息的路由规则。

~~~Java
channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "绑定规则");

// 参数
// 队列名称
// 交换机名称
// 路由键
Queue.BindOk queueBind(String queue, String exchange, String routingKey) throws IOException;
~~~







# 安装使用

[Releases · rabbitmq/rabbitmq-server](https://github.com/rabbitmq/rabbitmq-server/releases)，请在官网中下载合适的版本

前置条件：需要先安装 erlang 才能使用RabbitMQ [Downloads - Erlang/OTP](https://www.erlang.org/downloads)



Windows 安装 RabbitMQ 监控面板

找到 RabbitMQ 的安装位置，进入sbin文件夹，输入cmd

![image-20251126184134186](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20251126184134186.png)

![image-20251126184208659](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20251126184208659.png)



~~~sh
rabbitmq-plugins.bat enable rabbitmq_management
~~~

![image-20251126184402297](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20251126184402297.png)

完成后访问 [localhost:15672](http://localhost:15672/)

初次安装出现无法访问的情况，win + r 输入 services.msc 回车。找到rabbitmq，右键重启服务。

![image-20251126185144299](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20251126185144299.png)

默认用户名/密码（guest / guest）

![image-20251126185307069](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20251126185307069.png)

> 如果想要远程服务器访问 RabbitMQ 管理面板，需要自己创建一个管理员账号，不能使用默认的，否则会被拦截。
>
> 需要自己添加用户请参考 [身份验证、授权、访问控制 | RabbitMQ 消息队列](https://rabbitmq.org.cn/docs/access-control#adding-a-user)



# 开始使用

## 引入依赖

```xml
    <dependencies>
<!--        RabbitMQ 依赖客户端-->
        <dependency>
            <groupId>com.rabbitmq</groupId>
            <artifactId>amqp-client</artifactId>
            <version>5.17.0</version>
        </dependency>
<!--        操作文件流-->
        <dependency>
            <groupId>commons-io</groupId>
            <artifactId>commons-io</artifactId>
            <version>2.15.1</version>
        </dependency>
    </dependencies>
```

一个简单的消息队列模型

![image-20251201180418547](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20251201180418547.png)



## 生产者Demo

~~~Java
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.nio.charset.StandardCharsets;

public class Send {

    private final static String QUEUE_NAME = "hello";

    public static void main(String[] argv) throws Exception {
        // 创建一个连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        // 主机地址
        factory.setHost("localhost");
        // 如果自己修改了账号密码和端口，则需要重新设置
//        factory.setUsername();
//        factory.setPassword();
//        factory.setPort();
        // 创建一个连接
        try (Connection connection = factory.newConnection();
             // 创建一个信道
             Channel channel = connection.createChannel()) {
            // 声明一个队列
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            String message = "Hello World!";
            // 发送消息
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes(StandardCharsets.UTF_8));
            System.out.println(" [x] Sent '" + message + "'");
        }
    }
}
~~~



**声明队列的参数列表**

```Java
Queue.DeclareOk queueDeclare(String queue, boolean durable, boolean exclusive, boolean autoDelete,
                             Map<String, Object> arguments) throws IOException;
```

- `queue`：队列名称（注意，同名称的消息队列，只能用同样的参数创建一次，再次执行不会创建新的队列，只会存入消息）
- `durable`：消息队列是否持久化（存入硬盘），默认是在内存中，重启会丢失
- `exclusive`：是否只允许当前这个创建消息队列的连接来操作（是否共享给多个消费者）
- `autoDelete`：没有消费者连接，是否自动删除队列。（不推荐开启）
- `arguments`：其他参数



**发送消息参数列表**

```Java
void basicPublish(String exchange, String routingKey, BasicProperties props, byte[] body) throws IOException;
```

- `exchange`：发送到指定交换机
- `routingKey`：路由的Key值
- `props`：其它参数
- `body`：需要发送的消息



## 消费者Demo

```Java
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import java.nio.charset.StandardCharsets;

public class Recv {

    private final static String QUEUE_NAME = "hello";

    public static void main(String[] argv) throws Exception {
        // 创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        // 创建连接
        Connection connection = factory.newConnection();
        // 创建信道
        Channel channel = connection.createChannel();
        // 声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
        // 定义如何处理消息
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
            System.out.println(" [x] Received '" + message + "'");
        };
        // 消费消息，会持续阻塞
        channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });
    }
}
```



**消费消息参数设置**

```Java
String basicConsume(String queue, boolean autoAck, DeliverCallback deliverCallback, CancelCallback cancelCallback) throws IOException;
```

- `queue`：消费的队列名
- `autoAck`：自动确认
  - true：自动确认，在消费者取到消息的那一刻就确认
  - false：手动确认，最好设置手动确认[详情见下](#消息确认)
- `deliverCallback`：消息发送时的回调
- `cancelCallback`：消费者取消的回调



# 工作队列

工作队列模式也叫多消费者，是一个能者多劳的消息队列模型，官方教程的图解如下

![image-20251201180518131](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20251201180518131.png)

## 轮询分发

默认情况下，RabbitMQ 会按顺序将每条消息发送给下一个消费者。平均而言，每个消费者将获得相同数量的消息。这种分发消息的方式称为轮询。



## 公平分派

上述我们介绍了轮询分发。但实际上，每个消费者的效率不一样（每个任务难度不一致），导致消费者处理的时间也是不同的。可能会造成一个消费者一直处理高难度的消息，而另一个消费者在摸鱼的情况。（老板看到有员工摸鱼当然不乐意了）。

为了禁止消费者摸鱼的情况，使用`basicQos`方法，并设置为`1`。这告诉RabbitMQ一次只给一个工作者发送一条消息。

换句话说，在消费者处理并确认前一条消息之前，不要将新消息分派给它。相反，它会将消息分派给下一个尚未忙碌的消费者。

所以当哪个消费者空闲了，就会被塞入下一个任务（能者多劳）。

~~~Java
channel.basicQos(1);
~~~



## 消息确认

使用之前的的代码，一旦 RabbitMQ 将消息传递给消费者，它会立即将其标记为删除。当消费者消费一个超长任务的途中突然挂掉了，就会导致消息丢失。

但是我们不想丢失任何任务。如果一个工作进程死亡，我们希望将任务分发给另一个工作进程。

为了避免这种情况RabbitMQ引入消息确认。确认是由消费者发回给RabbitMQ的，用来告知RabbitMQ特定的消息已被接收、处理，并且RabbitMQ可以安全地删除它。

```Java
// 每个消费者每次只能消费一条未确认的消息
channel.basicQos(1);

DeliverCallback deliverCallback = (consumerTag, delivery) -> {
    String message = new String(delivery.getBody(), "UTF-8");

    System.out.println(" [x] Received '" + message + "'");
    try {
        // 处理工作
        // 停20s，模拟超长时长任务，每个机器处理能力有限
        Thread.sleep(20000);
    } catch (InterruptedException e) {
        throw new RuntimeException(e);
    } finally {
        System.out.println(" [x] Done");
        // 手动确认
        channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
    }
};
// 消费任务
channel.basicConsume(TASK_QUEUE_NAME, false, deliverCallback, consumerTag -> { });
```



现在我们发送了一下5条消息

![image-20251203165859552](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20251203165859552.png)



两个消费者同时打开，正常接收消息（一人一个）

![image-20251203170001898](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20251203170001898.png)



掐掉右边线程，模拟任务执行中消费者突然挂掉。此时 zhimo 任务并未完成，任务没有手动确认。RabbitMQ 将未完成的消息重新发送给左边的消费者执行。

![image-20251203170024388](C:\Users\93988\AppData\Roaming\Typora\typora-user-images\image-20251203170024388.png)

### 消息确认的方式

**ack**

```java
// multiple:批量处理，
// true：处理所有未完成的消息，直到最新的这一条
// false：只处理当前这一条消息
void basicAck(long deliveryTag, boolean multiple) throws IOException;
```

---

如果消息本身或者消息的处理过程出现问题怎么办？

需要一种 **否定确认** 机制通知RabbitMQ，*这个消息我无法处理，请让别的消费者处理。*

这里就有两种机制，Reject和Nack。

reject和Nack的差别只有一种 ：reject一次只能拒绝一条消息，Nack支持批量拒绝。

还记得死信队列中的消息来源吗？有一条就是“被拒绝的消息”。

在拒绝消息时，可以使用requeue标识。

**reject**

~~~Java
void basicReject(long deliveryTag, boolean requeue) throws IOException;
~~~

参数：`requeue`

- true：重新放回队列

- false：丢弃或进入死信队列

---

**nack**

~~~Java
// 第一个参数：消息
// 第二个参数：是否批量.true:将一次性拒绝所有小于deliveryTag的消息。
// 第三个参数：是否重新放回队列，否则丢弃或者进入死信队列
void basicNack(long deliveryTag, boolean multiple, boolean requeue)throws IOException;
~~~



## 生产者Demo

```Java
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

import java.util.Scanner;

public class MultiProducer {

  private static final String TASK_QUEUE_NAME = "task_queue";

  public static void main(String[] argv) throws Exception {
    ConnectionFactory factory = new ConnectionFactory();
    factory.setHost("localhost");
    try (Connection connection = factory.newConnection();
         Channel channel = connection.createChannel()) {
        // 队列持久化
        channel.queueDeclare(TASK_QUEUE_NAME, true, false, false, null);
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String message = scanner.nextLine();
            // 消息持久化
            channel.basicPublish("", TASK_QUEUE_NAME,
                    MessageProperties.PERSISTENT_TEXT_PLAIN,
                    message.getBytes("UTF-8"));
            System.out.println(" [x] Sent '" + message + "'");
        }
    }
  }
}
```



## 消费者Demo

```Java
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

/**
 * 工作队列
 */
public class MultiConsumer {

  private static final String TASK_QUEUE_NAME = "multi_queue";

  public static void main(String[] argv) throws Exception {
    ConnectionFactory factory = new ConnectionFactory();
    factory.setHost("localhost");
    final Connection connection = factory.newConnection();
    final Channel channel = connection.createChannel();

    channel.queueDeclare(TASK_QUEUE_NAME, true, false, false, null);
    System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

    channel.basicQos(1);

    DeliverCallback deliverCallback = (consumerTag, delivery) -> {
        String message = new String(delivery.getBody(), "UTF-8");

        System.out.println(" [x] Received '" + message + "'");
        try {
            // 处理工作
            // 停20s，模拟超长时长任务，每个机器处理能力有限
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            System.out.println(" [x] Done");
            // 手动确认
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
        }
    };
    // 消费任务
    channel.basicConsume(TASK_QUEUE_NAME, false, deliverCallback, consumerTag -> { });
  }
}
```



# 发布/订阅

![image-20251205204539739](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20251205204539739.png)



## 创建交换机

```Java
channel.exchangeDeclare(EXCHANGE_NAME, "fanout");

// 源码
Exchange.DeclareOk exchangeDeclare(String exchange, String type) throws IOException;
```

参数

1. 交换机名称
2. 交换机类型



## 队列绑定交换机

~~~Java
channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "绑定规则");

// 源码
Queue.BindOk queueBind(String queue, String exchange, String routingKey) throws IOException;
~~~

参数

1. 队列名称
2. 交换机名称
3. 路由键



## 生产者Demo

```Java
public class FanoutProducer {
    private static final String EXCHANGE_NAME = "fanout-exchange";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            // 创建交换机
            channel.exchangeDeclare(EXCHANGE_NAME, "fanout");

            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNext()) {
                String message = scanner.nextLine();
                // 消息持久化
                channel.basicPublish(EXCHANGE_NAME, "",
                        null,
                        message.getBytes("UTF-8"));
                System.out.println(" [x] Sent '" + message + "'");
            }
        }
    }
}
```



## 消费者Demo

```Java
public class FanoutConsumer1 {
    private static final String EXCHANGE_NAME = "fanout-exchange";

    private static final String QUEUE_NAME = "aip";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        // 创建交换机
        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
        // 声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        // 绑定队列到交换机（创建路由）
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "");

        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [aip] Received '" + message + "'");
        };
        channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });
    }
}
```

---

~~~Java
public class FanoutConsumer2 {
    private static final String EXCHANGE_NAME = "fanout-exchange";

    private static final String QUEUE_NAME = "zhimo";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        // 创建交换机
        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
        // 声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        // 绑定队列到交换机（创建路由）
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "");

        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [zhimo] Received '" + message + "'");
        };
        channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });
    }
}
~~~



## 效果图

![image-20251205212531403](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20251205212531403.png)



# 路由

## 生产者Demo

```Java
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
```





## 消费者Demo

```Java
public class DirectConsumer1 {
    private static final String EXCHANGE_NAME = "direct-exchange";
    private static final String QUEUE_NAME = "aip";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, "direct");
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "aip");
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "everyone");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [" +QUEUE_NAME+ "] Received '" +
                    delivery.getEnvelope().getRoutingKey() + "':'" + message + "'");
        };
        channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });
    }
}
```

---

```Java
public class DirectConsumer2 {
    private static final String EXCHANGE_NAME = "direct-exchange";
    private static final String QUEUE_NAME = "zhimo";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, "direct");
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "zhimo");
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "everyone");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [" +QUEUE_NAME+ "] Received '" +
                    delivery.getEnvelope().getRoutingKey() + "':'" + message + "'");
        };
        channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });
    }
}
```

---

> 效果图

![image-20251206220812355](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20251206220812355.png)



# 主题

## 生产者Demo

```Java
public class TopicProducer {

    private static final String EXCHANGE_NAME = "topic_exchange";

    public static void main(String[] argv) throws Exception {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {

            channel.exchangeDeclare(EXCHANGE_NAME, "topic");
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNext()) {
                String userInput = scanner.nextLine();
                String[] split = userInput.split(">>>");
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
```



## 消费者Demo

```Java
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
```



## 效果图

> 生产者

![image-20251208223637644](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20251208223637644.png)

---

> 消费者

![image-20251208223728596](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20251208223728596.png)
