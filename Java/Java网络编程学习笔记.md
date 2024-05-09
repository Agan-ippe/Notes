#  Java网络编程学习笔记

# 1、网络编程前置知识

## 1.1、什么是网络

​		具有独立功能的计算机通过通信介质连接起来就形成了网络。网络的功能是：进行数据信息的交换。



## 1.2、网络的划分

> 网络按照覆盖范围主要分为：局域网、城域网、广域网
>
> 传输速率：局域网 > 城域网 > 广域网

- 局域网：覆盖范围最小，局域网是结构复杂程度最低的计算机网络。局域网仅是同一地点上经网络连接在一起的一组计算机。覆盖范围一般在1000m以内，比如一个学校、工厂或机关等，传输速率可以达到1000Mbps，局域网以双绞线作为主要的传输媒介。
- 广域网：是影响广泛的复杂网络系统，用两个以上的局域网构成，可以覆盖全球，广为人知的万维网，因特网就是广域网
- 城域网：覆盖范围通常高达几千米，城市范围内所建立的通信网，一般以光纤作为传输媒介。



>按传输介质划分：有线网和无线网
>
>按网络的使用性质分：公用网和专用网
>
>按网络的拓扑结构分：总线型网络、环型网络、星形网络、树状网络



## 1.3、网络通信

​		两台设备之间通过网络实现数据传输，在Java语言中，`java.net` 包下提供了一系列的类或接口，供开发者使用，完成网络通信。



## 1.4、IP地址

​		IP地址是每个计算机在网络中的唯一标识，它是32位或者128位的无符号数字，使用四组数字表示的一个固定编号。如：*192.168.128.255* 这就是一个局域网络中的编号。IP地址是一种低级协议，***UDP和TCP*** 都是在它的基础上构建的。



## 1.3、域名和端口

​		域名：将IP地址映射。`baidu.com` 就是一个域名。

​		端口：用于标识计算机上某个特定的网络程序，范围在 `0-65535` （2^16^ - 1）之间。

​					0-1024端口号已被占用，比如： ssh:22， ftp: 21，smtp: 25，http: 80



## 1.4、网络协议

​		

### 1.4.1 TCP

​		TCP 是传输控制协议，主要负责数据的分组和重组。它与IP协议组合使用，称为TCP/IP。

​		TCP 适用于可靠性比较高的运行环境，TCP会确保数据按照发送的顺序正确的达到目标，可以避免丢包。传输完毕后，需要释放已建立的连接，效率较低。

​		应用场景：游戏之类的。



> TCP的三次握手和四次挥手：http://t.csdnimg.cn/Yo5X0



### 1.4.2、UDP

​		UDP 是用户数据报协议，是一种非持续连接的通信协议，不保证数据能够正确的到达目标。

​		在多个数据包传输时，抵达目标的顺序可能和发送时的不一致。

​		因为无需连接，所以不是可靠的。无需释放资源，效率较高。

​		应用场景：聊天器，流媒体等。





# 2、InetAddress

​		Java 提供了 IP地址的封装类 `InetAddress`。它封装了IP地址，并提供了相关的常用方法，例如解析 IP地址的主机名称、获取本机 IP地址的封装、测试 IP地址释放可达等。

​		常用方法如下：

```Java
InetAddress getLocalHost()			//获取本地主机名和IP（InetAddress对象）
InetAddress getByName(String host)	//通过主机名或域名获取IP地址
String getHostAddress()				//根据 InetAddress对象 获取主机IP
String getHostName()				//根据 InetAddress对象 获取主机名
boolean isReachable(int timeout)	//测试IP地址在指定时间内是否可达，单位毫秒
```







> 浅读源码

```Java
InetAddress() {
    holder = new InetAddressHolder();
}

/** 
 * hostName 主机名
 * address 地址
 * family 地址族 1代表IPv4 2代表IPv6
 */
InetAddressHolder(String hostName, int address, int family) {
            this.originalHostName = hostName;
            this.hostName = hostName;
            this.address = address;
            this.family = family;
        }
```





# 3、套接字（Socket）编程

​		套接字开发网络应用程序呗广泛采用。网络上的两个程序通过一个双向的通信连接实现数据的交换，这个双向链路的每一端称为一个 `Socket`。`Socket` 通常用来实现客户方和服务方的连接。一个 `Socket` 由*一个 IP地址和一个端口号* 唯一确定。

​		客户方：主动发起请求的一方。

​		服务方：等待通信请求的一方。

​		*套接字* 负责启动该程序内部与外部之间的通信，将远程地址和端口号传递给 *套接字* 的构造器，如果连接失败，将抛出一个 `UnknowHostException` 异常；如果存在其他问题，将会抛出一个 `IOException` 异常。

​		`UnknowHostException` 是 `IOException` 的一个子类。



## 3.1、套接字的通信过程

1. 创建 Socket
2. 打开连接到 Socket 的输入输出流
3. 按照一定的协议对 Socket 进行读写操作
4. 关闭 Socket



## 3.2、Socket的使用

​		模拟创建一个客户端，连接到服务端。

```Java
/**
 * @author Aip
 * @description 客户端测试
 */
public class SocketClientTest {
    public static void main(String[] args) throws IOException {
        //创建一个套接字，用来连接给定的主机和端口
        //Socket(InetAddress address, int port)
        Socket socket = new Socket(InetAddress.getLocalHost(), 9999);
        System.out.println("客户端 socket: " + socket.getClass());

        //写入数据
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("hello, server".getBytes());
        outputStream.close();
        socket.close();
    }
}
```



​		模拟服务端，连接客户端。

```Java
/**
 * @author Aip
 * @description 实现 基本的服务端
 */
public class SocketTest {
    public static void main(String[] args) throws IOException {
        //创建一个监听端口的服务器套接字
        ServerSocket serverSocket = new ServerSocket(9999);
        System.out.println("服务端正在监听 9999端口，等待连接....");

        //等待连接，该方法会阻塞，直到成功连接，返回一个 Socket 对象
        Socket socket = serverSocket.accept();
        System.out.println("服务端socket: " + socket.getClass());
        
        //读取数据
        InputStream inputStream = socket.getInputStream();
        byte[] buf = new byte[1024];
        int readLen = 0;
        while ((readLen = inputStream.read(buf)) != -1){
            System.out.println(new String(buf, 0, readLen));
        }

        inputStream.close();
        socket.close();
        serverSocket.close();
    }
}
```

​		***ServerSocket*** 可以通过 `accept()` 方法返回多个 ***Socket*** 对象。



---

​		下面两段代码运行时，会出现阻塞。原因是，在通信过程中，客户端和服务端两端的输入输出流都没有终止。相互之间都不知道对方是否说完，都在等待对方继续说。

​		半关闭提供了解决方法，套接字连接的一端可以终止起输出，但是仍旧能接受来自另一端的数据。其作用是终止输出流，但没有关闭套接字，因为一旦关闭套接字就无法连接服务端了。

​		使用半关闭的方法就可以解决示例代码中的问题，可以通过关闭一个套接字的输出流来表示发送给服务器的请求数据已经结束，==但必须爆出输入流处于打开状态。==

​		这种方式只适用于一站式的服务，例如HTTP服务，在这种服务中，客户端连接服务端，发送一个请求，捕获响应信息，然后断开连接。

~~~Java
void shutdownOutput()			//将输出流设为"流结束"
void shutdownInput()			//将输入流设为"流结束"
boolean isOutputShutdown()		//如果输出已经关闭,返回true
boolean isInputShutdown()		//如果输入已经关闭,返回true
~~~



```java 
public class ServerSocketTest2 {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9999);
        Socket socket = serverSocket.accept();
        if (socket.isConnected()){
            System.out.println("port: 9999, connection succeed");
        }else {
            System.out.println("connection fail");
        }
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("I am server!".getBytes(StandardCharsets.UTF_8));
        //socket.shutdownOutput();

        InputStream inputStream = socket.getInputStream();
        byte[] buf = new byte[1024];
        int readLen = 0;
        while ((readLen = inputStream.read(buf)) != -1){
            System.out.println(new String(buf, 0, readLen));
        }

        inputStream.close();
        outputStream.close();
        serverSocket.close();
        socket.close();
    }
}
```



```java
public class ClientSocketTest2 {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket(InetAddress.getLocalHost(), 9999);
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("I am client! ".getBytes(StandardCharsets.UTF_8));
        //socket.shutdownOutput();

        InputStream inputStream = socket.getInputStream();
        byte[] buf = new byte[1024];
        int readLen = 0;
        while ((readLen = inputStream.read(buf)) != -1){
            System.out.println(new String(buf, 0, readLen));
        }

        inputStream.close();
        outputStream.close();
        socket.close();

    }
}
```



---



​		字符流方式通信

​		newLine() 方法也可以当作半关闭方法使用。但是必须在对方使用 readLine() 方法 读取到最后一行才会生效。

```java
/**
 * @author Aip
 * @description 使用字符流向服务端通信
 */
public class ClientTest {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket(InetAddress.getLocalHost(), 9999);
        new NetTool().isConnected(socket);

        OutputStream outputStream = socket.getOutputStream();
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
        bufferedWriter.write("hello, server. i am client!");
        //newLine()方法也可以当作半关闭方法使用。但是必须在对方使用 readLine()方法 读取到最后一行才会生效。
        bufferedWriter.newLine();
        bufferedWriter.flush();

        InputStream inputStream = socket.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        System.out.println(bufferedReader.readLine());

        bufferedReader.close();
//        bufferedWriter.close();
        socket.close();
    }
}

class NetTool {

    /**
     * 检查连接情况
     * @param socket socket
     */
    public void isConnected(Socket socket){
        if (socket.isConnected()){
            System.out.println("port: 9999, connection succeed");
        }else {
            System.out.println("connection fail");
            //TODO 连接失败应该抛出异常或者生成日志
        }
    }
}
```



```java
/**
 * @author Aip
 * @description 使用字符流向客户端通信
 */
public class ServerTest {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9999);
        Socket socket = serverSocket.accept();
        new NetTool().isConnected(socket);

        InputStream inputStream = socket.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        System.out.println(bufferedReader.readLine());

        OutputStream outputStream = socket.getOutputStream();
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
        bufferedWriter.write("hello, client. i am server!");
        //newLine()方法也可以当作半关闭方法使用。但是必须在对方使用 readLine()方法 读取到最后一行才会生效。
        bufferedWriter.newLine();
        bufferedWriter.flush();

//        bufferedWriter.close();
        bufferedReader.close();
        serverSocket.close();
        socket.close();
    }
}
```



## 3.3、关于TCP连接

​		当客户端连接到服务器端后，实际上客户端也是通过一个端口和服务器端进行通讯的。这个端口是 TCP/IP 来随机分配的。

​		我们可以通过打印 socket 来观察。

![image-20240509114902486](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20240509114902486.png)



# 4、netstat

​		netstat -an 可以查看当前主机网络情况，包括端口监听情况和网络连接情况。

​		netstat -anb 显示端口所在的程序。

​		netstat -anb | more 分页显示，最精确。

​		ctrl + c 退出指令。

​		LISTENING 端口表示正在监听。

​		ESTABLISHED 表示双端已连接。



>netstat -anb | more

![image-20240509113325809](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20240509113325809.png)



>netstat -an | more

![image-20240509113510178](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20240509113510178.png)



# 5、UDP网络编程

​		`DatagramSocket` 和 `DatagramPacket` （数据包/报）类，实现了基于UDP协议的网络程序。

​		UDP 数据包通过数据包套接字 *DatagramSocket* 发送和接收，系统不保证UDP数据包一定能够安全的送到目的地，也不确定什么时候可以抵达。

​		*DatagramPacket* 对象封装了UDP 数据包，在数据包中包含了发送端的 IP地址 和 端口号，以及接收端的 IP地址 和 端口号。

​		UDP协议中每个数据包都给出了完整的地址信息，因此无须建立发送方和接收方的连接。
