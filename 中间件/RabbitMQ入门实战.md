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



### 头交换机

头交换机使用消息属性来进行消息的分发，通过判断消息头的值能否与指定的绑定相匹配来确立路由规则。在头交换机里有一个特别的参数”x-match”，当”x-match”的值为“any”时，只需要消息头的任意一个值匹配成功即可，
当”x-match”值为“all”时，要求消息头的所有值都需相等才可匹配成功。

![image-20251209175726898](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20251209175726898.png)

*头交换机并不常用*



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



# 消息过期机制

官方文档：[生存时间 (TTL) 和过期 | RabbitMQ 消息队列](https://rabbitmq.org.cn/docs/ttl#queue-ttl)



## 什么是消息过期机制

消息过期机制也称 TTL，TTL 指定了消息和队列的**有效期**，消息超时未被消费，则会被丢弃或放入[死信队列](#死信队列)。

TTL 的设置可以非常灵活，你既可以**为整个队列设置一个统一的过期时间**，也可以**为单条消息设置独立的过期时间**。



## TTL的作用

消息过期机制在实际应用中非常有用，主要解决以下几类问题：

1. 防止消息堆积：对于包含时效性数据的系统（如订单超时未支付、限时优惠活动），可以确保过期的订单或活动信息不会无限期地留在队列中占用资源。
2. 实现延迟任务：这是 TTL 一个非常经典和巧妙的用法。通过结合“死信队列”，可以实现一个功能完善的延迟队列。例如，创建一个没有消费者的队列，并为其设置 TTL 和死信交换机。消息进入该队列后，等待 TTL 时间后过期，然后被路由到死信交换机，最后由绑定在死信交换机上的消费者来处理，从而实现了延迟执行的效果。
3. 保证数据新鲜度：对于一些实时性要求很高的场景（如实时路况、股票行情），旧的消息很快就会失去价值。设置 TTL 可以自动清理这些过时的数据，确保消费者处理到的都是最新的信息。
4. 系统容错与重试：在复杂的任务处理流程中，如果一条消息因为某种原因长时间未被处理，可能意味着下游系统出现了问题。通过 TTL 可以将这些“僵尸”消息自动移除，并触发告警或进入人工干预流程。



## 如何使用

使用方法分为两个

1. 为队列设置
2. 为消息单独设置



### 为队列设置TTL

~~~Java
// 创建一个Map来存放队列参数
Map<String, Object> argsMap = new HashMap<>();
// 设置队列的TTL为10000毫秒（10秒）
argsMap.put("x-message-ttl", 10000);
// 声明队列，并传入参数
channel.queueDeclare(QUEUE_NAME, true, false, false, argsMap);
~~~

说明：

- 所有发送到队列的消息，如果在 10 秒内没有被消费，都会自动过期。
- 如果将 `x-message-ttl` 设置为 `0`，表示只要消息一进入队列，如果不能立即被消费者获取，就会立即过期（除非有消费者正在等待）。

> demo

```Java
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
```

```Java
public class QueueTtlConsumer {

    private final static String QUEUE_NAME = "queue_ttl_demo";

    public static void main(String[] argv) throws Exception {
        // 创建一个连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        System.out.println(" [*] 等待消息。要退出请按 CTRL+C");
        System.out.println("队列TTL说明：此队列中的所有消息将在10秒后过期");
        System.out.println("如果您在消息过期前启动消费者，可以正常接收消息");
        System.out.println("如果您在消息过期后才启动消费者，将无法接收已过期的消息");

        // 创建消费者回调
        DeliverCallback callback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
            System.out.println(" [x] 接收到消息 '" + message + "'");
        };
        // 开始消费消息
        channel.basicConsume(QUEUE_NAME, true, callback, consumerTag -> {});
    }
}
```

![image-20251209205851528](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20251209205851528.png)

---



### 为消息单独设置TTL

~~~Java
// 创建BasicProperties对象来设置消息属性
AMQP.BasicProperties properties = new AMQP.BasicProperties.Builder()
        .expiration("5000") // 设置消息的TTL为5000毫秒（5秒）
        .build();
// 发送消息，并传入properties
channel.basicPublish("", QUEUE_NAME, properties, message.getBytes());
~~~

说明：

- 即使没有设置队列级别的 TTL，这条消息也会在 5 秒后过期。
- 如果队列也设置了 TTL（比如 10 秒），那么这条消息的实际过期时间是 5 秒（取两者中的较小值）。

---

> 生产者demo

```Java
public class MessageTtlProducer {

    private final static String QUEUE_NAME = "message_ttl_demo";

    public static void main(String[] argv) throws Exception {
        // 创建一个连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        // 主机地址
        factory.setHost("localhost");
        // 创建一个连接
        try (Connection connection = factory.newConnection();
             // 创建一个信道
             Channel channel = connection.createChannel()) {
            // 创建普通队列，不设置队列TTL
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);

            Scanner scanner = new Scanner(System.in);
            System.out.println("消息TTL生产者已启动，输入消息发送（输入'exit'退出）:");
            while (scanner.hasNext()) {
                String message = scanner.nextLine();
                if ("exit".equalsIgnoreCase(message)) {
                    break;
                }
                // 发送带有TTL的消息（10秒过期）
                AMQP.BasicProperties ttlProperties = new AMQP.BasicProperties.Builder()
                        // 10秒TTL
                        .expiration("10000")
                        .build();
                channel.basicPublish("", QUEUE_NAME, ttlProperties, message.getBytes(StandardCharsets.UTF_8));
                System.out.println(" [x] 发送带有TTL的消息: '" + message + "'");
            }
            System.out.println("消息TTL生产者已关闭");
        }
    }
}
```

<font color="red">在到期后未被消费的消息被丢弃</font>

![image-20251209204736986](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20251209204736986.png)

---

### 重要区别：队列TTL vs. 消息TTL

这是一个非常关键且容易混淆的点：

1. **队列 TTL (`x-message-ttl`)**：
   - **过期时机**：只有当消息处于**队列头部**（即即将被消费者消费）时，RabbitMQ 才会检查它是否过期。
   - **影响**：如果队列因为消息积压，导致一条设置了 5 秒 TTL 的消息在 10 秒后才到达队首，那么它在第 10 秒才会被判定为过期并移除。**它的实际存活时间可能远大于设定的 TTL**。
2. **消息 TTL (`expiration`)**：
   - **过期时机**：无论消息在队列的哪个位置，RabbitMQ 都会确保它在设定的 TTL 时间后被移除。
   - **影响**：一条设置了 5 秒 TTL 的消息，无论队列积压多严重，它都一定会在进入队列后的 5 秒左右被移除。

**结论**：如果你需要**精确控制每条消息的生命周期**，请务必使用**消息级别的 TTL**。如果只是对队列中的消息有一个大致的存活时间要求，使用队列级别的 TTL 即可。



# 死信队列

死信是指那些无法被正常处理的消息，包括过期的消息、被拒绝的消息、处理失败的消息、队列已满无法存储的消息。这些消息没有得到正确的响应，遗落在邮箱底的邮件，没有得到妥善的处理。

为了处理这些失败的消息，引入了死信队列的概念

**死信队列** 是专门用来处理死信的队列（本质上就是一个普通队列），其保证消息的可靠性，确保每条消息都能被成功消费而提供的一种容错机制。

**死信交换机**：用于将死信消息转发到死信队列的交换机，也可以设置路由绑定来确认消息的路由规则（和普通的交换机用法无二）



![image-20251211210404513](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20251211210404513.png)

## 相关配置

```Java
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
// 将拒绝的消息放入死信队列
args.put("x-overflow", "reject-publish-dlx");

// 声明正常队列，并传入死信参数
channel.queueDeclare(NORMAL_QUEUE, false, false, false, args);
```



> 特别注意

在队列满的情况下，如果需要将新的消息放入死信队列，需要将 `x-overflow` 配置为 `reject-publish-dlx`。

该参数的默认配置是 **删除头部**，会将最早的消息放入死信队列。

而 `reject-publish` 参数会拒绝新消息

~~~java
// 将拒绝的新消息放入死信队列
args.put("x-overflow", "reject-publish-dlx");
// 拒绝新消息
args.put("x-overflow", "reject-publish");
~~~



## 死信队列demo

案例结构

![image-20251212214223418](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20251212214223418.png)

<font color="red">使用须知</font>

两个生产者选择你需要的运行一个即可，然后同时运行消费者 [idea启动两个main方法](https://blog.csdn.net/wgq3773/article/details/115449197)



### DlxConfig

```Java
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.util.HashMap;
import java.util.Map;

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
```



### DlxConsumer

死信队列消费者示例代码

```Java
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
```



### DlxRejectProducer

直接拒绝消息,生产者示例

```Java
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
public class DlxRejectProducer {
    public static void main(String[] argv) throws Exception {
        // 首先设置死信队列
        DlxConfig.setupDlxQueue();

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {

            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String message = new String(delivery.getBody(), "UTF-8");
                // 拒绝消息
                channel.basicNack(delivery.getEnvelope().getDeliveryTag(), false, false);
                System.out.println(" [x] Received '" +
                        delivery.getEnvelope().getRoutingKey() + "':'" + message + "'");
            };

            channel.basicConsume(DlxConfig.NORMAL_QUEUE, false, deliverCallback, consumerTag -> {
            });

            Scanner scanner = new Scanner(System.in);
            System.out.println("死信队列生产者已启动，请输入消息：");
            while (scanner.hasNext()) {
                String message = scanner.nextLine();
                // 消息持久化
                channel.basicPublish(DlxConfig.NORMAL_EXCHANGE,
                        DlxConfig.NORMAL_ROUTING_KEY,
                        null,
                        message.getBytes("UTF-8"));
                System.out.println(" [x] Sent '" + message + "' with routing '" + DlxConfig.NORMAL_ROUTING_KEY + "'");
            }
        }
    }
}
```



### DlxTtlProducer

测试消息过期、队列已满的情况下，消息入死信的 生产者示例代码

```Java
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
```
