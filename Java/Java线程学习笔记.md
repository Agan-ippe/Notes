# Java线程学习笔记

## 一、线程的相关概念

> 程序

​		程序是一个静态实体，本质上就是一段代码。学习编程输出的第一个 hello word 就是一个程序。

> 进程

​		执行程序的过程就叫进程，进程是具有一定独立功能的程序关于某个数据集合上的一次运行活动，是系统进行资源分配和调度的独立单位。

> 并发

​		同一时刻，多个任务交替执行，造成一种 ”同时运行“ 的错觉，单核CPU实现多任务就是并发。

> 并行

​		同一时刻，多个任务同时执行。多核CPU可以实现并行。

​		并发和并行是可以同时存在的。

---



### 什么是线程

- 线程是由进程创建的，是进程的一个实体。
- 一个进程可以拥有多个线程。

> 举个例子：
>
> ​		游戏中有一把枪，你打出去的每一发子弹都是一个独立的线程，线程是存在生命周期的。
>
> ​		当这颗子弹命中了一名敌人，那么这个子弹（也就是这一个线程）的生命周期就结束了，被销毁。这种属于强制消亡，因为命中了敌人，这颗子弹就不需要了。
>
> ​		如果不销毁，那么这颗子弹将会一直运行，造成一穿N的离谱局面。
>
> ​		同样的，这颗子弹出膛之后，直至失去所有动能。（程序结束了）也会被销毁。这时候就是线程的自动消亡。



### 单线程和多线程

> 单线程

​		同一时刻，只允许执行一个线程。



> 多线程

​		同一时刻，可以执行多个线程。



## 多线程编程

​		多个线程在运行过程中是随机的，部分先运行，部分后运行。通过一下代码示例，每次执行输出都不一致。

```Java
public class TestThread {
    public static void main(String[] args) {
        Test t = new Test();
        t.start();
        for (int i = 0; i < 4; i++) {
            System.out.println(Thread.currentThread().getName() + " 线程正在运行");
        }
    }
}

class Test extends Thread{
    @Override
    public void run() {
        for (int i = 0; i < 4; i++) {
            System.out.println(Thread.currentThread().getName() + " 线程正在运行");
        }
    }
}
```



### 线程的使用

线程的实现有两种方式

- 一个是继承 `Thread` 类
- 一个是实现 `Runnable` 接口

### 继承Thread类

```Java
public class TestThread {
    public static void main(String[] args) {
        Test t = new Test();
        t.start();
        for (int i = 0; i < 4; i++) {
            System.out.println(Thread.currentThread().getName() + " 线程正在运行");
        }
    }
}

class Test extends Thread{
    @Override
    public void run() {
        for (int i = 0; i < 4; i++) {
            System.out.println(Thread.currentThread().getName() + " 线程正在运行");
        }
    }
}
```



### 实现Runnable接口

​		在Java中虽然可以通过继承 `Thread` 类来实现线程，但由于在Java语言中，只能继承一个类，如果我们定义的类已经继承了其他类，此时想要实现线程，就可以实现 `Runnable` 接口即可。

​		`Runnable` 接口中定义了一个 `run()` 方法在实例化一个 `Thread` 对象时，可以传入一个实现 `Runnable` 接口的对象作为参数，`Thread` 类会调用 `Runnable` 对象的 `run()` 方法，继而执行 `run()` 方法中的内容。

```Java
public class TestRunnable{
    public static void main(String[] args) {
        UseRunnable ur = new UseRunnable();
        Thread thread = new Thread(ur);
        thread.start();
    }
}

class UseRunnable implements Runnable{
    private static final int COUNT = 10;
    DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM);

    @Override
    public void run() {
        int cot = 0;
        while (true){
            System.out.println(LocalTime.now().format(formatter) + " 线程运行中...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            cot++;
            if (cot == COUNT){
                break;
            }
        }
    }
}
```



> 通过接口的方式实现，底层是使用的设计模式是 “代理模式” 。
>
> - 用静态代理模式去创建新线程就是弥补java单继承缺陷，同时提高 `Thread` 类的复用（创建新线程都由`Thread` 完成，自定义其他类只需编好新线程的业务逻辑，再通过代理类去复用`Thread` 的创建线程功能。
> - java单继承，若自定义Tiger已继承了其他类，就无法再继承 `Thread` 类，tiger对象也就不能直接使用`Thread` 类的 `start() 和 start0()` 方法创建新线程
> - 既然无法直接调用 `start()` 方法，就让tiger借用代理类 `Thread` 去间接调用这两个方法。也就是在`Thread` 中构造一个可传入 `Runnable` 接口实现类对象的构造方法接收tiger对象(参数多态)
> - `Thread` 对象创建新线程时调用本身`start()`，`start() `调用底层 `start0()` ，`start0()` 又回调它本身的 `run()`，但此刻Thread的run方法已经调用了传入的Tiger类对象重写的run业务逻辑
> - 所以负责创建线程的还是Thread的start方法，但是线程业务逻辑绑定到自定义类内run方法，这样任何类想创建线程实现自己业务逻辑都只需实现R接口重写run方法，再向T代理类传入对象就可实现
>
> 
>
> PS：这个Proxy代理类只是模拟Thread代理类内的run方法动态调用过程，方便理解静态代理逻辑和创建线程的方法调用过程，并没有真正实现新线程创建



```java 
public class TestThreadProxy {
    public static void main(String[] args) {
        Tiger tiger = new Tiger();
        ThreadProxy threadProxy = new ThreadProxy(tiger);
        threadProxy.start();

    }
}
class Animal{}
class Tiger extends Animal implements Runnable{

    @Override
    public void run() {
        System.out.println("老虎嗷嗷叫...");
    }
}

class ThreadProxy implements Runnable{
    private Runnable target = null;

    @Override
    public void run() {
        if (target != null){
            target.run();
        }
    }

    public ThreadProxy(Runnable target) {
        this.target = target;
    }

    public void start(){
        //这里是真正实现多线程的方法，非常重要
        start0();
    }

    private void start0() {
        run();
    }
}
```



## 线程的状态

线程可以有如下6中状态：

- **New**：新建状态
- **Runnable**：可运行状态
- **Blocked**：阻塞状态
- **Waiting**：等待状态
- **Timed waiting**：计时等待状态
- **Terminated**：终止状态

> ​		要确定一个线程当前的状态，调用`getState()`方法。





## 线程的生命周期

线程的生命周期有以下几个状态：

- 创建状态：实例化了一个 `Thread` 对象后，线程是创建状态。
- 可执行状态：实例化了一个 `Thread` 对象，并执行了 `start()` 方法后。线程进入 可执行状态 。
- 非可执行状态：线程在执行的过程中，进入了等待执行权的队列中，为非可执行状态。`Thread` 类中的 `wait() 和 sleep()` 方法可以使线程进入非可执行状态。
- 消亡状态：当 `run()` 方法执行完毕，线程自动消亡。
  - 也可以调用线程中的 `stop()` 方法来终止线程。该方法会通过抛出一个错误对象，从而杀死线程。不过 `stop()` 方法已经废弃。尽量不要再自己的代码中调用这个方法。
  - 当遇到没有捕获的异常，线程也会终止。



## 线程的属性

​		下面会简单介绍一下线程的一些属性，包括中断线程、守护线程、未捕获异常的处理器以及不应该使用的一些遗留特性。

> 线程常用方法：
>
> - `setName()  getName()` ：设置和获取线程名称。
> - `start()` ：使该线程进入可执行状态。
> - `run()` ：执行线程。
> - `setPriority()   getPriority()` ：设置和获取线程的优先级。
> - `sleep()` ：让线程休眠指定的毫秒数。
> - `wait()`：让线程等待。
> - `notify()`： 通知线程停止等待。
> - `yield()` ：使当前正在执行的线程向另一个线程交出运行权。这是一个静态方法。
> - `join()` ：插入线程(让线程插个队)，线程一旦插入成功，则一定先执行插入线程的所有任务。
> - `interrupt()` ：给线程设置一个中断标志，线程仍会继续运行。
> - `interrupted()` ：测试提前线程是否被中断，这是一个静态方法，调用有一个副作用——它将当前线程的中断状态重置为  ***false*** 。
> - `isInterrupted()` ：判断一个线程是否被中断，不会清除中断状态。
> - `setDaemon()` ：标识该线程为 *守护线程* 或 *用户线程* 。这一方法必须在线程启动之前调用。



### 中断线程

​		当对一个线程调用 `interrupt()` 方法时，就会设置线程的 **中断状态** 。这是每个线程都有的 `boolean 标志`。每个线程都应该不时地检查这个标志，以判断线程是否被中断。

​		通过 `Thread.currentThread().isInterrupted()` 方法得出线程是否设置了 **中断状态**。

​		但是，如果线程被阻塞，就无法检查中断状态。这里需要引入`InterruptedException`异常。当在一个被 `sleep 或 wait` 调用阻塞的线程上调用 `interrupt()`方法时，那个阻塞调用将被一个 `InterruptedException` 异常中断。

​		没有任何语言要求被中断的线程应当终止。中断一个线程只是要引起它的注意。被中断的线程可以决定如何响应中断。某些线程非常重要，所以应该处理这个异常，然后再继续执行。



> `interrupt()`：向线程发送中断请求，线程的中断状态将被设置为 ***true***。如果当前该线程被一个`sleep`调用阻塞，则抛出一个 `InterruptedException` 中断异常。



### 守护线程

​		可以通过调用 `setDaemon(ture)` 将一个线程转换为守护线程。

​		守护线程的唯一用途是为其他线程提供服务。计时器线程就是一个例子，另外清空过时缓存项的线程也是守护线程。当只剩下守护线程时，虚拟机就会退出。因为只剩下守护线程，就没必要继续运行程序了。

> - 用户线程：也叫工作线程，当线程的任务执行完或通知方式结束。
> - 守护线程：一般是为了工作线程服务的，当所有的用户线程结束，守护线程自动结束。
> - 常见的守护线程：垃圾回收机制。



### 线程名

​		默认情况下，线程的命名为：Thread-num。 当然你也可以用 `setName()` 方法为线程设置任何名字。这在线程转储时可能很有用。



### 线程的优先级

​		在Java中，每一个线程有一个优先级。默认情况下，一个线程会继承构造它的那个线程的优先级。可以用 `setPriority()` 方法提高或降低任何一个线程的优先级。可以将优先级设置为 `MIN_PRIOROTY  值为1` 与 `MAX_PRIOROTY  值为10` 之间的任何值。

​		在没有使用操作系统线程的 Java 早期版本中，线程优先级可能很有用。不过现在不要使用线程优先级了。





---

## 同步

​		如果程序是单线程的，执行起来不必担心此线程会被其他线程打扰。但如果程序是多线程的，就好比多个人同时进入一扇门。此时需要控制，否则容易阻塞。

> ​		以下方代码为例，在售票程序中可能碰到一种意外，同一张票被打印多次，也有可能超额售票。

```Java
public class SellTicketUseRunnable {
    public static void main(String[] args) {
        SellTicketR sellTicketR = new SellTicketR();

        new Thread(sellTicketR).start();
        new Thread(sellTicketR).start();
        new Thread(sellTicketR).start();
    }
}
class SellTicketR implements Runnable{
    /**
     * 让多个线程共享 ticketNum
     */
    private int ticketNum = 100;

    @Override
    public void run() {
        while (true) {
            if (ticketNum <= 0) {
                System.out.println("售票结束...");
                break;
            }

            //休眠50毫秒
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("窗口 " + Thread.currentThread().getName() +
                    " 售出一张票" + " 剩余票数 = " + (--ticketNum));
        }
    }
}
```

![image-20240322181052398](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20240322181052398.png)



​		为了避免多线程共享资源发生冲突的情况发生，只要在线程使用资源时给该资源上一把锁就可以了，访问资源的第一个线程为资源上锁，其他线程若想使用这个资源，必须等到锁解除为止，锁解开的同时另一个线程使用该资源并为这个资源上锁。

​		为了处理这种共享资源竞争，可以使用同步机制。

> 线程的同步机制
>
> ​		在多线程编程，一些敏感数据不允许被多个线程同时访问。此时就使用同步访问技术，保证数据在任何同一时刻，最多只有一个线程访问，以保证数据的完整性。

​		Java语言提供 `synchronized` 关键字，为防止资源冲突提供了内置支持。共享资源一般是文件、I/O端口、或者是打印机。

​		Java语言中有两种同步形式：

- 同步方法
- 同步代码块



> 同步代码块

```Java
public void method (){
    synchronized (this){
        //todo 需要被同步的代码
    }
}
```



> 同步方法

```Java
public synchronized void method (){
    //TODO 需要被同步的代码
}
```

---

​		下面案例将使用同步方法解决之前超额售票的问题。

```Java
/**
 * @author Aip
 * @version 1.0
 * @date 2024/3/25  11:36
 * @description 使用同步方法解决之前超额售票问题
 */
public class SynMethod {
    public static void main(String[] args) {
        SellTicket01 sell = new SellTicket01();

        new Thread(sell).start();
        new Thread(sell).start();
        new Thread(sell).start();
    }
}

class SellTicket01 implements Runnable{
    /**
     * 让多个线程共享 ticketNum
     */
    private int ticketNum = 100;
    private boolean mark = true;

    /**
     * 同步方法，在同一时刻只能有一个线程来执行m方法
     */
    public synchronized void sell(){
        if (ticketNum <= 0) {
            mark = false;
            System.out.println(Thread.currentThread().getName() + "售票结束...");
            return;

        }

        //休眠50毫秒
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("窗口 " + Thread.currentThread().getName() +
                " 售出一张票" + " 剩余票数 = " + (--ticketNum));
    }

    @Override
    public void run() {
        while (mark) {
            sell();
        }
    }
}
```



![同步方法解决超卖问题](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20240325115625640.png)



> ​		同步的局限性：导致程序的执行效率要降低。



## 互斥锁

​		互斥锁的基本概念：

- Java语言种，引入了兑现互斥锁的概念，来保证共享数据操作的完整性。
- 每个对象都对应于一个可称为 “互斥锁” 的标记，这个标记用来保证在任一时刻，只能有一个线程访问该对象。
- 关键字 `synchronized` 来与对象的互斥锁联系。当某个对象用 `synchronized` 修饰时，表明改对象在任一时刻只能由一个线程访问。
- **非静态的** 同步方法的锁，可以是 `this` ，也可以是其他对象（要求是同一种对象）。
- **静态的** 同步方法的锁，为当前类本身。



​		注意事项和细节：

- 同步方法如果是**非静态的** ，默认锁对象为 `this`。
- 同步方法如果是**静态的** ，默认锁对象为 `当前类.class`。



​		实现的落地步骤：

1. 需要先分析上锁的代码
2. 选择同步代码块或者同步方法。尽可能的选择代码块，因为代码块的范围小，效率高。
3. 要求多个线程的锁对象为同一个的对象即可。



## 线程的死锁

​		什么是线程的死锁？

​		多个线程都占用了对方的锁资源，但不肯相让，导致了死锁，在编程里是一定要避免死锁的发生。



​		造成死锁必须 **同时满足** 以下4个条件：

- 互斥条件：线程使用的资源必须至少有一个是不能共享的。
- 请求与保持条件：至少有一个线程必须持有一个资源并且正在等待获取一个当前被其他线程持有的资源。
- 非剥夺条件：分配的资源不能从相应的线程中被强制剥夺。
- 循环等待条件：第一个线程等待其他线程，后者又在等待第一个线程。



​		所以要防止死锁，只需要破坏其中一个条件即可。



### 释放锁

> 如何释放锁？

- 当前线程的同步方法、同步代码块执行结束，锁自动释放。
- 当前线程在同步方法、同步代码块中遇到了 `break 或 return`。
- 当前线程在同步方法、同步代码块中出现了未处理的 *错误或异常* ，导致程序异常结束。
- 当前线程在同步方法、同步代码块中执行了线程对象的 `wait()` 方法，当前线程暂停，并释放锁。



> 什么情况下不会释放锁？

- 当前线程执行同步方法、同步代码块时，程序调用了线程对象的 `sleep() 或 yield()` 方法，暂停当前线程的执行，不会释放锁。
- 当前线程执行同步方法、同步代码块时，其他线程调用了该线程的 `suspend()` 方法，将当前线程挂起，当前线程不会释放锁。



提示：应尽量避免使用 `suspend()暂停 和 resume()恢复` 方法来控制线程，这些方法已经废弃。



> 小练习：
>
> ​		两个用户分别从同一张卡上取钱，总额为 1w。每次都取 1k ，直到取完。 要求不能超取。

```Java
public class Work02 {
    public static void main(String[] args) {
        Withdraw withdraw = new Withdraw();
        Thread userA = new Thread(withdraw);
        Thread userB = new Thread(withdraw);
        userA.setName("A");
        userB.setName("B");
        userA.start();
        userB.start();
    }
}

class Withdraw implements Runnable {
    private int cash = 10000;
    private static final int TAKE_OUT = 1000;

    public void withdraw() {
        while (cash >= TAKE_OUT) {
            synchronized (this) {
                if (cash < TAKE_OUT){
                    System.out.println("正在取钱..." + '\n' + "当前余额不足！");
                    break;
                }
                System.out.println("用户 " + Thread.currentThread().getName() + " 取出了" + TAKE_OUT + "块,当前剩余: " + (cash -= TAKE_OUT));
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    @Override
    public void run() {
        withdraw();
    }
}
```













