# 1、基础常识

## 1.1、常用的DOS命令

==在输入DOS命令时，使用英文输入==

~~~dos
dir   :列出当前目录下的文件及文件夹
md    :创建目录
rd    :删除目录
cd    :进入指定目录
cd..  :退回到上一级目录
cd\   :退回到根目录
del   :删除文件
exit  :退出dos命令行
~~~



## 1.2、什么是计算机语言

- 第一代语言：打孔机——纯机器语言
- 第二代语言：汇编语言 B语言（初级语言）
- 第三代语言：（高级语言）
  - C、Pascal、Fortran 面向过程的语言
  - C++ 面向过程/面向对象
  - Java 跨平台的纯面向对象的语言
  - .NET跨语言的平台



# 2、关于Java

## 2.1、java的优点

1. 面向对象
2. 可移植性。==**Java最大的特性就是跨平台，将java源文件编译成.class文件。一次编写，到处运行**==
3. Java语言简洁易学，舍弃了C++中难以理解的指针（引用取代）、运算符重载、多继承等特性（接口取代）。增加了垃圾回收机制。
4. 体系结构中立
5. 适宜分布式计算
6. 解释型
7. 安全性
8. 健壮性
9. 高性能
10. 多线程



Java的版本

- JavaSE（标准版）：主要开发桌面软件、C/S结构（[分布式模式]Client/Server，客户端/服务器模式）软件。是Java基础。
- JavaEE（企业版）：主要开发B/S结构（Browser/Server，浏览器/服务器模式）的企业级应用。如JavaWeb
- JavaME（微型版）：嵌入式开发，PDA、手机等系统。



## 2.2、Java的两种核心机制（面试可能会问）

- Java虚拟机（JVM）
- 垃圾收集机制（GC）



## 2.3、JVM、JRE、JDK的区别

- JDK：Java开发工具（包括Java API、Java编译器、JRE、JVM）
- JRE：Java运行环境
- JVM：Java虚拟机

**JDK ⊂ JRE ⊂ JVM**



# 3、Java编程基础

此处省略又臭又长的

安装配置Java环境变量，安装IDE，关键字，保留字，命名规则，变量，常量，循环，选择，条件判断，三元运算符...



# 4、Java面向对象编程

## 4.1、面向对象(OOP)与面向过程(POP)

两者都是一种编程思想，面向过程强调的是功能行为。面向对象，将功能封装进对象，强调了具备了功能的对象。

面向对象更加强调运用人类在日常的思维逻辑中采用的思想方法与原则，如抽象、分类、继承、聚合、多态等。



## 4.2、面向对象的三大特征

- 封装
- 继承
- 多态



## 4.3、面向对象的概念

- 对象
- 类class：在面向对象程序设计中，类时程序的基本单元。
- 属性：表示成员变量，称之为类的属性，例如`学生类`有`学生姓名`、`年龄`、`学号`等。
- 方法：即对象的方法，学生通过什么方式去上学。例如： `走路`、`骑车`、`公交`等。
- 封装
- 继承
- 多态



## 4.4、类

~~~java
//类的写法

<修饰符> class <类名> [extends <父类名>] [implements <接口名称>]
{
	[程序代码]
}
public class UserDO {
    /**
     * 成员变量
     */
    private Long id;
    private String userName;
    
    /**
    * 方法
    */
    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "UserDO{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                '}';
    }
}
~~~



## 4.5、继承

```Java
public class A extends B{
	// 成员变量
    // 方法
}
class B{
    // 成员变量
    // 方法
}
```



## 4.6、多态

多态性是指：相同的操作可作用于多种类型的对象上并获得不同的结果。

不同的对象，收到同一消息可以产生不同的结果。

多态性，是面向对象中最重要的概念，在java中有两种体现：

1. 方法的重载和重写。
2. 对象的多态性——可以直接应用在抽象类和接口上。



# 5、接口与抽象类

## 5.1、抽象类

**抽象类的关键字：abstract**

==abstract用法：==

- 可用于修饰类、方法
- abstract修饰类时，该类为抽象类。抽象类不可生成对象，必须被继承使用。
- abstract不能和private、static、final同时出现。
- 修饰方法时，为抽象方法，抽象方法没有实现只有定义，由子类覆盖后实现。
- 一个类有抽象方法，他就必须声明为抽象类。
- 子类继承一个抽象类，必须实现该抽象类里所有的抽象方法，不然就要把自己声明为抽象类。
- 抽象子类可以不实现抽象父类中的抽象方法，也可以对抽象方法进行实现。



**抽象类中允许定义抽象方法，也可以定义非抽象方法。**

**抽象类自身不能进行实例化**



## 设计模式

**==适配器==**

`为抽象类提供适配器，从而让普通抽象类子类继承此适配器，避免必须实现不需要的抽象方法`

```java
package com.javase.opp.abstracts;

/**
 * 为抽象类提供适配器，从而让普通抽象类子类继承此适配器，避免必须实现不需要的抽象方法
 * @时间: 2022/12/1    17:27
 */
public class AnimalAdapter extends Animal{
    @Override
    public void run() {

    }

    @Override
    public void eat() {

    }

    @Override
    public void hunting() {

    }
}

```

~~~java
package com.javase.opp.abstracts.animal;

import com.javase.opp.abstracts.AnimalAdapter;

/**
 * @时间: 2022/12/1    17:31
 */
public class Fish extends AnimalAdapter {
    @Override
    public void run() {
        System.out.println("鱼在水中游泳");
    }
}

~~~



## 5.2、接口 interface

**什么是接口？**

Java中接口是一系列方法的声明，试一下方法特征的集合，一个接口只有方法的特征，没有方法的实现，因此这些方法可以在不同的地方被不同的类实现。



**接口的特点：**

- 接口只允许定义的常量 `public static final <常量名>`
- 接口只允许定义抽象的方法 `public abstract <返回值类型> <方法>`
- 类与接口是一种实现的关系，关键字是`implements`，一个类可以同时实现多个接口。
- 一个类实现接口，就必须覆盖接口的所有方法。不然，该类就必须声明为抽象的。
- 不能用接口来创建对象，接口没有构造方法。
- 一个接口可以继承多个接口。
- 接口也是生成.class文件。



> 接口的定义

~~~java
//接口的定义
public interface Computer {
    public static final int MAX_NUM =Integer.MAX_VALUE;

    public abstract double count(double a,double b);
}
~~~



>接口的继承

~~~java
public interface Clock extends Computer{

    void showSystemDateTime();  // 在子接口中添加新的抽象定义
}
~~~



> 通过类来实现接口功能，制作简易计算器

~~~java
/**
 * @时间: 2022/12/2    17:15
 * 此类继承了JFrame并实现了Computer接口
 */
public class Mulity extends JFrame implements Clock {

    private String title;  //窗体标题
    private JButton jButton;
    private  JTextField jtf1;
    private  JTextField jtf2;
    private  JTextField jtf3;
    private  JTextField timeText;

    private Mulity self;

    @Override
    public double count(double a, double b) {

        return a*b;
    }

    public Mulity(String title){
        init(title);
    }

    private void init(String title) {
        self=this;
        this.setTitle(title);
        this.setVisible(true);
        this.setBounds(200,100,800,600);
        this.setLayout(null);

        jButton =new JButton("请点击计算");
        jButton.setBounds(100,100,150,30);
        jButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

                double n= Double.valueOf(jtf1.getText());
                double m= Double.valueOf(jtf2.getText());
                double res = self.count(n,m);
                jtf3.setText(String.valueOf(res));
                self.validate();
            }

            @Override
            public void mousePressed(MouseEvent e) { }

            @Override
            public void mouseReleased(MouseEvent e) { }

            @Override
            public void mouseEntered(MouseEvent e) { }

            @Override
            public void mouseExited(MouseEvent e) { }
        });
        this.add(jButton);

        jtf1=new JTextField();
        jtf1.setBounds(100,200,200,30);
        this.add(jtf1);

        jtf2=new JTextField();
        jtf2.setBounds(100,300,200,30);
        this.add(jtf2);

        jtf3=new JTextField();
        jtf3.setBounds(100,400,200,30);
        this.add(jtf3);

        timeText=new JTextField();
        timeText.setBounds(100,500,200,30);
        this.add(timeText);

        self.showSystemDateTime();
    }

    @Override
    public void showSystemDateTime() {
        while (true) {
            Date time =new Date();
            String timeString =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(time);
            timeText.setText(timeString);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
~~~



> 在主程序中运行

~~~java
public class TextInterfaceCreate {
    public static void main(String[] args) {
        //Computer computer =new Computer(); 接口自身不能够实例化

        Computer computer=new Mulity("java窗体");
        double res = computer.count(2,6.1);
        System.out.println(res);
    }
}
~~~

[接口回调](https://blog.csdn.net/iteen/article/details/82183596?fromshare=blogdetail&sharetype=blogdetail&sharerId=82183596&sharerefer=PC&sharesource=m0_59059137&sharefrom=from_link)



## 5.3、接口与抽象类的区别

- 接口不能有具体的实现，但抽象类可以。
- 一个类要实现一个接口，必须实现接口中的所有方法，抽象类不必。
- 通过接口可以实现多继承，抽象类不行。
- 接口不能有构造方法，抽象类可以。
- 实体类与接口之间只有实现关系，而实体类与抽象类只有继承关系，抽象类与接口之间只有实现关系。
- 接口中的方法默认且只能都是公开抽象方法，属性默认且只能是公开静态常量，抽象类不是。





## 5.4、内部类

内部类必须定义 在某个类当中，所在类为其外部类。

内部类可以使用public、protected、private访问修饰符。



## 5.5、匿名内部类

​		匿名内部类的本质是一个类，同时又是一个对象。

​		匿名内部类的语法如下：

> 基于接口的匿名内部类

~~~Java
new 类或接口 (参数列表) {
    类体
};

/**
 * @author Aip
 * @description 匿名内部类的使用
 */
public class AnonymousInnerClass {
    public static void main(String[] args) {
        //匿名内部类
        A dog = new A() {
            @Override
            public void cry() {
                System.out.println("汪汪队立大功");
            }
        };
        dog.cry();
    }
}

interface A{
    public void cry();
}

class Dog implements A{

    @Override
    public void cry() {
        System.out.println("汪汪队立大功");
    }
}
~~~



> 基于类的匿名内部类

```Java
/**
 * @author Aip
 * @description 匿名内部类的使用
 */
public class AnonymousInnerClass {
    public static void main(String[] args) {
        Father father = new Father("你好"){
            @Override
            public void work() {
                System.out.println("工作是VC工程师");
            }
        };
        father.work(); //工作是VC工程师
    }
}

class Father{
    public Father(String name) {
    }
    public void work(){
        System.out.println("上班");
    }
}
```









# 6、代码块

**一段在{}当中的代码**

## 6.1、普通代码块

~~~java
public class CodeDemo01 {
    public static void main(String[] args) {
        /**
         * 利用普通代码块对方法中的结构进行分割，这样即使有重名变量x，也不会相互影响。
         */
        {
            int x=30;
            System.out.println("普通代码块--> x="+ x);
        }
        int x=100;
        System.out.println("代码块之外--> x="+x);
    }
}
~~~



## 6.2、构造块

不论顺序，构造块会优先与构造方法执行

构造块会被执行多次

~~~java
public class CodeDemo02 {
    public static void main(String[] args) {
        new Demo();
        new Demo();
    }
}

/**
 * 构造块
 */
class Demo{
    public Demo(){
        System.out.println("2、构造方法");   // 定义构造方法
    }
    {
        System.out.println("1、构造块");   // 定义构造块
    }
}
~~~

![1670230415524](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/20250620171929429.png)





## 6.3、静态代码块

静态代码块只会被执行一次

在静态代码块中：

- 允许访问静态属性。
- 允许调用静态方法。
- 允许定义局部变量及常量。
- 允许定义局部类。
- 不能访问实例属性和方法及定义方法。



~~~java
public class CodeDemo03 {
    public static final int EAST;
    public static final int WEST;
    public static final int SOUTH;
    public static final int NORTH;

    /**
     *静态块
     */
    static {
        EAST=0;
        WEST=1;
        SOUTH=2;
        NORTH=3;
        displayLocation();
    }

    public static void displayLocation(){
        System.out.println("您的当前默认位置是"+NORTH);
    }
}

~~~



~~~java
public class Text {
    public static void main(String[] args) {
        int C = CodeDemo03.SOUTH;
        int C1 = CodeDemo03.EAST;
    }
}
~~~

![1670232885819](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/20250620171935466.png)

### 静态代码块中的内容限制

![1670233196893](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/20250620171937903.png)

静态代码块中不允许访问非静态字段及非静态方法。



### 静态代码块的加载

静态代码块属于类，和对象无关，随类的加载而加载。

在静态属性初始化之后，调用构造器之前加载。

静态代码块在加载时通常做一些静态属性或常量的赋值、调用静态方法等工作，如果想在产生任何所在类对象之前，类加载时处理类信息，则可选择使用静态代码块完成。





# 7、enum 枚举

> 关于枚举

- 枚举是一组常量的集合
- 在Java中，枚举属于一种特殊的类，里面只包含一组有限的特定的对象。



> 枚举的实现方式

- 自定义类实现枚举
- 使用enum关键字实现枚举



## 7.1、自定义实现枚举

1. 不需要提供 setXxx 方法，因为枚举对象值通常为只读。
2. 对枚举对象/属性使用 final + static 共同修饰，实现底层优化。
3. 枚举对象名通常使用全部大写，常量的命名规范。
4. 枚举对象根据需要，也可以有多个属性



```java
public class Enumeration02 {
    public static void main(String[] args) {
        System.out.println(Season.AUTUMN);
        System.out.println(Season.SPRING);

    }
}
//演示自定义枚举
class Season{
    private String name;
    private String desc;

    //定义了四个对象
    public static final Season SPRING =new Season("春天","温暖");
    public static final Season SUMMER =new Season("夏天","炎热");
    public static final Season AUTUMN =new Season("秋天","凉爽");
    public static final Season WINTER =new Season("冬天","寒冷");

    private Season() {
    }

    /*
    1.将构造器私有化，防止直接 new
    2.去掉 setXxx 方法，防止属性被修改
    3.在该类 Season 的内部，直接创建 规定 的对象
    4.优化方案，可以再加入一个 final 修饰符，
     */
    private Season(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    @Override
    public String toString() {
        return "Season{" +
                "name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}
```





> 自定义枚举的特点

- 构造器私有化
- 本类内部创建对象
- 对外暴露对象
- 可以提供 get方法，但不需要 set方法



## 7.2、使用关键字实现枚举

如果使用了 enum 来实现枚举类

- 使用 enum 关键字来代替 class
- 不再需要 new一个对象了 直接 常量名(实参列表)
-  用关键字实现枚举 枚举类的对象 必须放在开头，多个常量，用逗号分隔      ！！！语法规则！！！



```java
public class Enumeration03 {
    public static void main(String[] args) {
        System.out.println(Season2.SPRING);
    }
}

enum Season2{
    /*
    如果使用了 enum 来实现枚举类
    1. 使用 enum 关键字来代替 class
    2. 不再需要 new一个对象了 直接 常量名(实参列表)
    3. 用关键字实现枚举 枚举类的对象 必须放在开头，多个常量，用逗号分隔      ！！！语法规则！！！
     */

    SPRING("春天","温暖"),WINTER("冬天","寒冷");

    private String name;
    private String desc;




    Season2(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    @Override
    public String toString() {
        return "Season2{" +
                "name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}
```





## 7.3、enum的成员方法

| 方法名            | 描述                                                         |
| ----------------- | ------------------------------------------------------------ |
| valueOf           | 传递枚举类型的Class对象和枚举常量名称给静态方法 valueOf， 会得到与参数匹配的枚举常量 |
| toString          | 得到当前枚举常量的名称。可以通过重写这个方法，使得到的结果更容易读。 |
| equals            | 在枚举类型中可以 直接使用  ==  来比较两个枚举常量是否相等，Enum提供的这个equals()方法，也是直接用 == 实现的。它的存在是为了在 Set、List、Map 中使用。注意， equals() 是不可变的。 |
| hashCode          | 与equals() 方法同用，用来保持一致性。也是不可变的。          |
| getDeclaringClass | 得到枚举常量所属枚举类型的 Class对象。可以用它来判断两个枚举常量是否属于同一个枚举类型。 |
| name              | 得到当前枚举常量的名称。建议优先使用toString                 |
| ordinal           | 得到当前枚举常量的次序，从零开始标号，类似数组下标           |
| compareTo         | 比较两个枚举常量的大小（按照声明的顺序排列）。               |
| clone             | 枚举类不能被克隆，为了防止子类实现克隆方法，Enum实现了一个仅抛出 CloneNotSupportedExcption 异常的不变 Clone()。 |



```java
public class EnumMethod {
    public static void main(String[] args) {
        Season2 autumn = Season2.AUTUMN;
        System.out.println(autumn.name());  //AUTUMN
        System.out.println(autumn.ordinal());  //3


        //从反编译可以看到 values方法，返回 Season2[]
        //含有定义的所有枚举对象
        Season2[] values=Season2.values();
        System.out.println("----- 遍历取出枚举对象(增强for) -----");
        for (Season2 season: values){//增强for循环
            System.out.println(season);
        }
        /*
        valueOf: 将字符串转换成枚举对象，要求字符串必须为已有的常量名，否则报异常
        执行流程
        1.根据你输入的 字符串，到 Season2的枚举对象去查找
        2.如果找到了就返回，如果没找到就报错
         */
        Season2 s=Season2.valueOf("AUTUMN");
        System.out.println("s="+s);

        /*
        compareTo: 比较两个枚举常量，比较的就是编号
        1.就是把 前者的枚举对象编号 与 后者的枚举对象编号 进行比较
        3-1= 3
         */
        System.out.println(Season2.AUTUMN.compareTo(Season2.SPRING));  //3
    }
}
```







# 8、Java常用API

## 8.1、Object类

- Object类是Java所有类的根父类，也叫基类。
- 如果在类的声明中为使用extends关键字指明其父类，则默认父类为Object类



## 8.2、toString





## 8.3、equals

**==操作符与equals方法**

==：

- 基本类型的比较：只要两个变量的值相等，即为true
- 引用类型的比较：只有指向同一个对象是，才返回true



## 8.4、hashcode





## 8.5、clone

返回当前对象的一个副本

被克隆的对象与当前对象不具有同一引用，但是具有 完全相同的对象属性，欲克隆当前对象则当前对象所属类应实现Cloneable接口。



~~~java
public class Objects implements Cloneable{
    private String id,name;
    private int age;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Objects(){}

    public Objects(String id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
@Override
    public Objects clone() throws CloneNotSupportedException {
        System.out.println("开始克隆");
        return (Objects) super.clone();
    }
}
~~~



~~~java
public class TextClone {
    public static void main(String[] args) {
        Objects obj=new Objects(UUID.randomUUID().toString(),"罗森丽娜",21);
        try {
            Objects cloneObj = obj.clone();
            System.out.println("当前对象与克隆对象是否相等"+(obj==cloneObj));
            System.err.println("当前对象属性"+obj.getId()+"\t"+obj.getName());
            System.err.println("当前对象属性"+cloneObj.getId()+"\t"+cloneObj.getName());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }
}
~~~

![1670312994405](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/20250620172236792.png)



## 8.6、String、StringBuffer、StringBuilder类



**String类：**不可变字符串类

- String类是final的，不可被继承。
- String类的本质是字符数组char[]，并且其值不可被改变。
- Java运行时会维护一个String池，String吃用来存放运行时产生的各种字符串，并且翅中的字符内容不重复。而一般对象并不存在这个缓冲池，所创建的对象也仅仅存在于方法的堆栈区中。
- String类的创建![1670316298386](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/20250620172222965.png)
- String类可以用+进行字符串拼接。
- String的比较有两种 `= =`用来比较内存地址，`equals`用来比较String对象的内容。



String类的一些常用方法

~~~java
public char charAt(int index)  //返回字符串中第index个字符
    
public int length()//返回字符串长度
    
public static String format(String format, Object... args)//将args值接入format字符串中。需要使用占位符。
System.out.println(String.format("你好,%s%s%s%s",1,2,1,"啦啦" ));
    
public int indexOf(String str)//返回字符串中出现str的第一个位置
    
public int indexOf(String str, int fromIndex)//返回字符串中从fromIndex开始，出现str的第一个位置
    
public boolean equalsIgnoreCase(String anotherString)//比较字符串与anotherString释放一样（不计大小写）
    
public String replace(char oldChar, char newChar)//在字符串中用newChar替换oldChar
    
public boolean startsWith(String prefix)//判断字符串是否以prefix字符串开头
    
public boolean endsWith(String suffix)//判断字符串是否以suffix字符串结尾
    
public String toUpperCase()//返回一个字符串的大写模式
    
public String toLowerCase()//返回一个字符串的小写模式
    
public String substring(int beginIndex)//返回该字符串从beginIndex到结尾的子字符串
    
public String substring(int beginIndex, int endIndex)//返回该字符串从beginIndex到endIndex结尾的子字符串
    
public String trim()//返回将该字符串去掉开头和结尾的空格后的字符串
    
public static String valueOf(...)//将基本数据类型转换为字符串
    
public String[] split(String regex)//可以将一个字符串按照指定的分割符分割，返回分割以后的字符串数组
    
~~~



**StringBuffer：**可变字符串，可操作增删改查的字符串，还可以改变字符串顺序，若确定字符串最终形式，可用`StringBuffer.toString()`方法转换成String类



常用方法

- 增加：append 、 insert
- 修改：reverse、setCharAt、replace
- 删除：delete、deleteCharAt
- 查询：indexOf、charAt、getChars、substring

~~~java
public class Text {
    public static void main(String[] args) {
        String str1="我喜欢学习java";
        String str2="I like study java";
        System.out.println(str1.subSequence(2,6));
        System.out.println(str2.codePointAt(1));
        System.out.println(String.format("你好,%s%s%s%s",1,2,1,"啦啦" ));

        System.out.println("---------------------------");
        StringBuffer buf=new StringBuffer("Java");

        //追加
        buf.append(" Guide Ver1/");
        buf.append(3);

        //插入
        buf.insert(5,"student ");

        //改
        buf.setCharAt(23,'.');

        //替换
        buf.replace(24,25,"4");
        
        String s=buf.toString();
        System.out.println(s);
    }
}
~~~



**StringBuilder类**

**==非线程安全的==**

- StringBuilder和StringBuffer都是可变的字符序列。
- StringBuilder是非线程安全的，StringBuffer是线程安全的。
- 设计线程安全的情况下通常选择StringBuffer。
- StringBuilder的执行效率比StringBuffer更高。



~~~java

~~~



**三者的比较**

- String：不可变字符序列，效率低，但是复用率高。
- StringBuilder 和 StringBuffer 非常相似，均代表可变字符序列，而且方法也一样。
- StringBuffer：可变字符序列、效率较高（增删）、线程安全。
- StringBuilder：可变字符序列、效率最高、线程不安全



> *String使用注意说明：*

~~~java 
string s="a";  //创建了一个字符串
s += "b";//实际上原来的“a”字符串对象已经丢弃了，现在重新产生了一个字符串s +=“b"(也就是"ab")。如果多次执行这些改变串内容的操作，会导致大量副本字符串对象存留在内存中，降低效率。如果这样的操作放到循环中，会极大影响程序的性能
~~~

`结论：`如果我们对String 做大量的修改，不要使用String。



> 三者的效率对比

~~~java
public class StringVsStringBufferVsStringBuilder {
    public static void main(String[] args) {
        String  text="";
        long startTime =0l;
        long endTime=0l;
        StringBuffer buffer=new StringBuffer("");
        StringBuilder builder=new StringBuilder("");

        startTime=System.currentTimeMillis();
        //StringBuffer拼接了两万次
        for (int i = 0; i < 20000; i++) {
            buffer.append(String.valueOf(i));
        }
        endTime=System.currentTimeMillis();
        System.out.println("StringBuffer的执行时间 : "+(endTime-startTime)+"ms");

        startTime=System.currentTimeMillis();
        //StringBuilder拼接了两万次
        for (int i = 0; i < 20000; i++) {
            builder.append(String.valueOf(i));
        }
        endTime=System.currentTimeMillis();
        System.out.println("StringBuilder的执行时间 : "+(endTime-startTime)+"ms");


        startTime=System.currentTimeMillis();
        //String拼接了两万次
        for (int i = 0; i < 20000; i++) {
            text=text+i;
        }
        endTime=System.currentTimeMillis();
        System.out.println("String的执行时间 : "+(endTime-startTime)+"ms");
    }
}
~~~

![1670661793853](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/20250620172302964.png)



**对于三者使用的总结：**

1. 操作少量的数据: 适用 `String`
2. 单线程操作字符串缓冲区下操作大量数据: 适用 `StringBuilder`
3. 多线程操作字符串缓冲区下操作大量数据: 适用 `StringBuffer`



## 8.7、包装类

| 基本数据类型 | 包装类    |
| ------------ | --------- |
| byte         | Byte      |
| short        | Short     |
| int          | Integer   |
| long         | Long      |
| float        | Float     |
| double       | Double    |
| char         | Character |
| boolean      | Boolean   |



**==包装类是不允许被继承的==**

### Integer



~~~java
public class TextInteger {
    public static void main(String[] args) {
        Employee emp=new Employee();
        Employee emp2=new Employee();

        emp.setId(10);
        System.out.println(emp.getId());

        emp2.setId(new Integer("23"));
        System.out.println(emp2.getId());
        System.out.println("----------------------------");


        //Integer 常量属性
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.MIN_VALUE);
        System.out.println(Integer.BYTES);
        System.out.println(Integer.SIZE);
        System.out.println("----------------------------");

        //比较大小的方法
        System.out.println(emp.getId().compareTo(emp2.getId()));
        System.out.println(emp2.getId().compareTo(emp.getId()));
        System.out.println(new Integer(100).compareTo(new Integer(100)));
        System.out.println("----------------------------");


        //parseInt 静态转换方法，将字符串转换为值类型，所有包装类都具备此类似的方法
        String pwd="456840";
        int pwdInt=Integer.parseInt(pwd);
        System.out.println(pwd+9);
        System.out.println(pwdInt+9);
        System.out.println("----------------------------");

        // parseInt 重载方法
        // parseInt 进制转换
        System.out.println(Integer.parseInt("10122",10));
        System.out.println("二进制10111的十进制:"+Integer.parseInt("10111",2));
    }
}
~~~



~~~java
public class TextValueOf {
    public static void main(String[] args) {
        int num=1234;
        Integer intObj0=Integer.valueOf(num);
        System.out.println(intObj0.intValue());

        System.out.println(Integer.valueOf("345").intValue());

        String str="1011010";
        //将字符串按几进制处理，返回为十进制数
        Integer intObj1=Integer.valueOf(str,2);
        System.out.println(intObj1);
    }
}
~~~



~~~java
public class TextToString {
    public static void main(String[] args) {
        Integer integer0=Integer.valueOf(156);

        System.out.println(integer0.toString());
        System.out.println(integer0.toString()+2);
        System.out.println("----------------------");

        //静态toString重载方法
        System.out.println(Integer.toString(integer0));
        System.out.println(Integer.toString(integer0,2));
        System.out.println(Integer.toString(integer0,8));
        System.out.println(Integer.toString(integer0,16));
        System.out.println(Integer.toString(integer0,27));
    }
}
~~~



### Character

> Character 实例的创建

## 8.8、System类

- System表示当前运行的JVM，System提供了标准输入、标准输出和错误输出流对象。
- 对外部定义的属性和环境变量的访问；加载文件和库的方法；
- 还有快速复制数组的一部分的实用方法，以及获取系统时间和显示告知垃圾回收器 回收垃圾释放内存空间的方法。
- System是只读的不能被实例化。



~~~java
System.out.println();//获取标准输出流
System.err.println();//获取错误输入流
System.in			 //获取标准输入流
/**
 * @时间: 2022/10/23
 * @scanner 输入类
 */
public class Demo01 {
    public static void main(String[] args) {

        //创建一个扫描器对象，用于接收键盘数据
        Scanner scanner = new Scanner(System.in);

        System.out.println("使用next方式接收:");

        //判断用户有没有输入字符串
        if (scanner.hasNext()){
            //使用next方式接收
            //next读取单个单词输入（即以空白符作为结束符）
            //nextLine读取一行输入
            //nextInt读取一个整数输入
            //nextDouble读取一个浮点数输入
            String str = scanner.next();
            System.out.println("输出的内容为:"+str);
        }
        scanner.close();
    }
}
~~~



> System类的方法

~~~java
public static void exit(int status)//退出系统
   
public static void gc()//通知垃圾回收系统期望快速回收垃圾以释放内存空间

public static native long currentTimeMillis();// 获取当前系统时间毫秒表示

public static Properties getProperties()//获取当前系统属性map集合

public static String getProperty(String key)//按照给定的名称获取当前系统属性字符串表示
    
public static native void arraycopy(Object src,  int  srcPos,
                                        Object dest, int destPos,
                                        int length);
//复制数组 src：原数组、srcPos：从原数组哪个元素开始、dest：copy到的数组、destPos：从哪个地方（下标）开始放、length：copy长度

public static java.util.Map<String,String> getenv()//获取当前JVM所在本地系统信息视图
~~~



~~~java
public class TextSystemClass2 {
    public static void main(String[] args) {
        //查看当前系统JVM信息
        Properties pros = System.getProperties();

        //System.out.println(pros.toString());


        //拷贝数组
        int[] src0 = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] dest=new int[src0.length-2];
        System.arraycopy(src0,1,dest,0,dest.length);
        for (int n:dest) {
            System.out.print(n+" ");
        }

        //利用Systems获取本地计算机信息
        Map<String,String> maps= System.getenv();

        Set<String>keys=maps.keySet();//获取所有信息名称
        for (String name:keys){
            System.out.println(name+" = "+maps.get(name));
        }

    }
~~~



## 8.9、Math类

该类为Java程序提供一个数学计算类

~~~java
public class MathMethod {
    public static void main(String[] args) {
        //Math类的常用方法
        //1.abs绝对值
        int abs=Math.abs(-9);
        System.out.println(abs);
        //2.pow 求幂
        double pow=Math.pow(2,4);
        System.out.println(pow);
        //3.ceil 向上取整，返回 >= 该参数的最小整数
        double ceil =Math.ceil(-3.01);
        double ceil1 =Math.ceil(3.01);
        System.out.println(ceil);
        System.out.println(ceil1);
        //4.floor 向下取整，返回 <= 该参数的最大整数
        double floor=Math.floor(-4.99);
        System.out.println(floor);
        //5.round 四舍五入
        long round=Math.round(-5.61);
        System.out.println(round);
        //6.sqrt 求开方
        double sqrt=Math.sqrt(9.0);
        System.out.println(sqrt);
        //7.random 求随机数
        // random 返回的是 0 <= x < 1 之间的一个随机小数
        //思考: 请写出获取 a~b之间的一个随机整数，a,b均为整数？ 2~7
        int a = 2, b = 7;
        for (int i = 0; i < 10; i++) {
            System.out.println((int) (a + Math.random() * (b - a + 1)));
        }
        System.out.println("------------------------");

        //max、min 最大值、最小值
        int max = Math.max(9, 2);
        int min = Math.min(2, 9);
        System.out.println(max);
        System.out.println(min);
    }
}
~~~



## 8.10、Array类

| 方法                                                      | 描述                                     |
| --------------------------------------------------------- | ---------------------------------------- |
| public static boolean equals(int[] a, int[] a2)           | 判断两个数组是否相等                     |
| public static void fill(int[] a, int val)                 | 将指定内容填充到数组之中                 |
| public static void sort(int[] a)                          | 数组排序                                 |
| public static int binarySearch(int[] a, int key)          | 对排序后的数组，通过二分查找法，进行检索 |
| public static String toString(int[] a)                    | 遍历，打印输出数组信息                   |
| public static int[] copyOf(int[] original, int newLength) | 数组元素的复制                           |
| public static void fill(Object[] a, Object val)           | 数组元素填充，相当于替换                 |
| public static <T> List<T> asList(T... a)                  | 会将数据转换为一个List集合               |



~~~java
public class ArrayMethod01 {
    public static void main(String[] args) {

        Integer[]integers={1,20,90};
//        //遍历数组 一般写法
//        for (int i = 0; i < integers.length; i++) {
//            System.out.println(integers[i]);
//        }

        //在Array类中，提供了遍历数组的方法
        System.out.println(Arrays.toString(integers));

        //sort方法
        Integer arr[]={1,2,1,5,7,6,0,4};
        //进行排序
        //1.可以直接使用冒泡排序，也可以直接使用Arrays提供的sort方法排序
        //2.因为数组是引用类型，所以通过sort排序后，会直接影响到实参 arr
        //3.sort重载的，也可以通过传入一个接口 Comparator 实现定制排序

        //Arrays.sort(arr);  //默认排序方法
        /*
        * 定制排序
        * 调用 定制排序 时，传入两个参数:
        * 1.需要排序的数组 arr
        * 2.实现了Comparator接口的匿名内部类，要求实现 compare方法
        * 3.这里是接口编程的方式
        *
        * 源码分析
        * */
        Arrays.sort(arr, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                   Integer i1 = (Integer)o1;
                   Integer i2 = (Integer)o2;
                return i2-i1;
            }
        });
        System.out.println("------排序后-------");
        System.out.println(Arrays.toString(arr));
    }
~~~

![1670681456769](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/20250620172311733.png)

在binarySort方法底层，会通过匿名内部类的 compare方法，来决定排序的顺序。

~~~java
![1672279743361](C:\Users\93988\Desktop\studynotes\学习笔记\JavaSE\img\1672279743361.png)public class ArraysMethod02 {
    public static void main(String[] args) {
        Integer[] arr = {1, 2, 90, 123, 567};
        //binarySearch 通过二分查找法，进行查找，要求必须排好序
        //1. 使用 binarySearch 二叉查找
        //2. 要求该数字是有序的，正序倒序都行。如果是无序的，则不能使用binarySearch方法
        //3. 如果数组中不存在该元素，就返回  -（应该在的位置）
        int index = Arrays.binarySearch(arr, 92);
        System.out.println(index);

        //copyOf 数组元素的复制
        //1. 从arr数组中，拷贝arr.length 个元素到newArr数组中
        //2. 如果拷贝长度 > arr.length就在新数组的后面 自动补齐
        //3. 如果拷贝长度 < 0 就抛出异常 NegativeArraySizeException
        Integer[]newArr=Arrays.copyOf(arr,arr.length);
        System.out.println(Arrays.toString(newArr));

        // fill 数组元素填充
        // 相当于用输入的元素替换数组中的元素
        Integer[] num=new Integer[]{9,3,2};
        Arrays.fill(num,99);
        System.out.println(Arrays.toString(num));

        //equals  比较两个数组中的 元素内容是否一致
        Integer[] arr2 = {1, 2, 90, 123, 566};
        boolean equals=Arrays.equals(arr,arr2);
        System.out.println(equals);

        //asList  将一组值，转换成List
        /*
        * 1. asList方法，会将数据转换为一个List集合
        * 2. 返回的asList 编译类型 List接口
        * 3. asList 运行类型 java.util.Arrays$ArrayList
        *    是Arrays类的静态内部类 
        *    private static class ArrayList<E> extends AbstractList<E>
        implements RandomAccess, java.io.Serializable
         */

        List<Integer> asList =Arrays.asList(2,3,4,5,6,1);
        System.out.println(asList);
        System.out.println("asList的运行类型"+asList.getClass());
    }
}
~~~

> 练习

自定义Book类，里面包含 name 和 price ，按price 排序（从小到大）。 要求使用两种方式排序，有一个Book[] books=4 本书对象。使用前面学习过的传递，实现Comparator接口匿名内部类，也称定制排序。

（1） 按照price从小到大排序；

（2） 按照price从大到小排序；

（3） 按照书名长度从小到大。

~~~java
public class ArrayPractise {
    public static void main(String[] args) {
        Book[] books = new Book[4];
        books[0] = new Book("Java核心技术卷I", 149.5);
        books[1] = new Book("阿里巴巴Java开发手册", 23.3);
        books[2] = new Book("图解设计模式", 79.0);
        books[3] = new Book("Redis 实战", 69.0);

        //1. price从小到大
        Arrays.sort(books, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                Book book1 = (Book) o1;
                Book book2 = (Book) o2;
                double price1=book1.getPrice();
                double price2=book2.getPrice();
                double priceVal=price2-price1;
                //此处判断，涉及到底层，底层代码用的是int类型，所以我们的返回值也得是int类型
                //这里需要做一个转换，如果用强转，当出现 -1 < 书本差价 < 1 及为0.x时，则会被强转为0，判断则会价格相对（程序不严谨）

                if (priceVal>0){
                    return -1;
                }else if (priceVal<0){
                    return 1;
                }else {
                    return 0;
                }
            }
        });
        //2. price从大到小
        Arrays.sort(books, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                Book book1 = (Book) o1;
                Book book2 = (Book) o2;
                double price1=book1.getPrice();
                double price2=book2.getPrice();
                double priceVal=price2-price1;
                if (priceVal>0){
                    return 1;
                }else if (priceVal<0){
                    return -1;
                }else {
                    return 0;
                }
            }
        });

        Arrays.sort(books, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                Book book1 = (Book) o1;
                Book book2 = (Book) o2;

                return book1.getName().length() - book2.getName().length();
            }
        });
        System.out.println(Arrays.toString(books));
    }
}
class Book{
    private String name;
    private double price;

    public Book(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public Book() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
~~~



## 8.11、BigInteger 和 BigDecimal类

**BigInteger**

| 方法                                       | 描述 |
| ------------------------------------------ | ---- |
| public BigInteger add(BigInteger val)      | 加法 |
| public BigInteger subtract(BigInteger val) | 减法 |
| public BigInteger multiply(BigInteger val) | 乘法 |
| public BigInteger divide(BigInteger val)   | 除法 |



~~~java
public class BigIntegers {
    public static void main(String[] args) {
        //当我们编程中，需要处理很大的整数，long 不够用
        //可以使用BigInteger的类来解决
        //long l=233333333333333333333333l;
        BigInteger bigInteger=new BigInteger("2333333333333333333333333333333333333310");
        BigInteger bigInteger2=new BigInteger("111111111111111111111111111111");
        System.out.println(bigInteger);

        /*
        1. 在对 BigInteger 进行加减乘除的时候，需要使用对应的方法，不能直接进行 + - * /
        2. 可以创建一个要操作的 BigInteger 然后进行相应操作
         */
        //加 .add
        BigInteger add = bigInteger.add(bigInteger2);
        System.out.println(add);

        //减 .subtract
        BigInteger subtract = bigInteger.subtract(bigInteger2);
        System.out.println(subtract);

        //乘 .multiply
        BigInteger multiply = bigInteger.multiply(bigInteger2);
        System.out.println(multiply);

        //除 .divide
        BigInteger divide = bigInteger.divide(bigInteger2);
        System.out.println(divide);
    }
}
~~~



**BigDecimal**

==需要注意的是： 在BigDecimal的除法方法中，可能抛出异常，原因是除不尽 Non-terminating decimal expansion ：没有终止的小数==

解决方法，指定精度即可。



~~~java
public class BigDecimals {
    public static void main(String[] args) {
        /*
        当我们需要保存一个精度很高的数时，double不能满足需求
        可以使用 BigDecimal 来解决
        1.如果我们对 BigDecimal 进行运行，比如加减乘除，也需要使用对应的方法。
        2.创建一个需要操作的 BigDecimal ，然后进行对应的操作即可。
         */
        double d=1999.111111111199999000d;
        System.out.println(d);
        BigDecimal bigDecimal=new BigDecimal("1999.111111111199999000");
        BigDecimal bigDecimal2=new BigDecimal("1.111111111000001111");
        System.out.println(bigDecimal);

        System.out.println(bigDecimal.add(bigDecimal2));
        System.out.println(bigDecimal.subtract(bigDecimal2));
        System.out.println(bigDecimal.multiply(bigDecimal2));
        /*System.out.println(bigDecimal.divide(bigDecimal2));//除法可能 抛出异常，为除不尽 Non-terminating decimal expansion ：没有终止的小数
        解决方法，在调用divide方法时，指定精度即可
        如果有无限小数，就会保留分子（被除数）的精度
        */
        System.out.println(bigDecimal.divide(bigDecimal2,BigDecimal.ROUND_CEILING));
    }
}
~~~



## 8.12、Date类  日期类

**Date**

Date类在 `java.util` 包中。以毫秒数来表示特定的时间和日期。使用Date类的无参构造方法创建的对象可以获取本地当前的时间。Date类对象表示的时间默认顺序是： `星期、月、日、小时、分、秒、年` 

例如：   `Thu Dec 15 22:48:17 CST 2022`

计算机时间的 “公元” 设置在 `1970年 01月 01日 0时 （格林尼治时间）`。



~~~java
public class Date01 {
    public static void main(String[] args) {
        Date d1=new Date();
        Date d2=new Date(1000);  // 通过指定毫秒数得到时间
        System.out.println(d1.getTime());  // 获取某个时间对应的毫秒数
        System.out.println(d1);
        System.out.println(d2);
    }
}
~~~



**SimpleDateFormat 类**

- SimpleDateFormat 是 DateFormat 的子类。

- SimpleDateFormat用来实现 日期的格式化。

- SimpleDateFormat类 常用构造方法  其中 `pattern`中含有一些特殊意义的字符

  ```java
  public SimpleDateFormat(String pattern)
  {
      this(pattern, Locale.getDefault(Locale.Category.FORMAT));
  }
  ```



`pattern`中含有一些特殊意义的字符

- y或yy：用 **2** 位数字输出年。
- yyyy：用 **4** 位数字输出年。
- M或MM：用 **2 ** 位数字输出月份。
- MMM：用 **文本** 输出月份。
- d或dd：用 **2** 位数字输出日。
- H或HH：用 **2** 位数字输出小时。
- m或mm：用 **2** 位数字输出分钟。
- s或ss：用 **2** 位数字输出秒。
- E： 用 **字符串** 输出星期。

~~~java
public class SimpleDateFormats {
    public static void main(String[] args) {
        Date date=new Date();
        SimpleDateFormat matter1 =new SimpleDateFormat("当前时间: yyyy.MM.dd  HH:mm:ss E");
        System.out.println(matter1.format(date));
    }
}    //当前时间: 2022.12.15  23:09:04 星期四
~~~



**Calendar**

1. Calendar 是一个抽象类，并且构造器是private
2. 可以通过 getInstance() 来获取示例
3. 提供大量的方法和字段提供给开发者
4. Calendar 没有自己专门的格式化方法，可以按需求，自己组合



~~~java
public class Calendars {
    public static void main(String[] args) {
        /*
        1. Calendar 是一个抽象类，并且构造器是private
        2. 可以通过 getInstance() 来获取示例
        3. 提供大量的方法和字段提供给开发者
         */
        Calendar cal=Calendar.getInstance();
        System.out.println(cal);
        String year=String.valueOf(cal.get(Calendar.YEAR)),
                month=String.valueOf(cal.get(Calendar.MARCH)+1),    // 注意如果不加1 ，结果会比当前月少 1 
                day=String.valueOf(cal.get(Calendar.DAY_OF_MONTH)),
                week=String.valueOf(cal.get(Calendar.DAY_OF_WEEK)-1);  // 注意如果不减1 ，结果会比当前 星期 多 1
        int hour=cal.get(Calendar.HOUR_OF_DAY),  //HOUR_OF_DAY: 24小时制   HOUR: 12小时制
                minute=cal.get(Calendar.MINUTE),
                second=cal.get(Calendar.SECOND);
        System.out.println("当前时间: ");
        System.out.println(year+"年"+month+"月"+day+"日"+"星期"+week);
        System.out.println(hour+":"+minute+":"+second);
    }
}
~~~



>  Calendar 存在的一些问题

- 可变性：像日期和时间这样的类 应该是不可变的。
- 偏移性：Date中的年份是从1900开始的，而月份都是从0开始的。
- 格式化：格式化只对Date有用，Calendar则不行。
- 不是线程安全的。不能处理 闰秒 等 （每隔两天，多出一秒）。



> 第三代日期类

**LocalDate**

对时间做加减

- plus+Date ： 加 后可接天数、小时数、分钟数
- minus+Date ： 减 后可接天数、小时数、分钟数
- 作用：
  - 类似于充值的会员，多久到期
  - 记事本，什么时间记得笔记

```java
public class LocalDates01 {
    public static void main(String[] args) {
        /*
        第三代日期
         */
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);
        System.out.println("年:"+now.getYear());
        System.out.println("月:"+now.getMonth());
        System.out.println("月:"+now.getMonthValue());
        System.out.println("日:"+now.getDayOfMonth());
        System.out.println("时:"+now.getHour());
        System.out.println("分:"+now.getMinute());
        System.out.println("秒:"+now.getSecond());
        System.out.println("--------------------------");
        LocalDate ld=LocalDate.now(); // 只有日期，没有时间
        System.out.println(ld);
        System.out.println(now.plusDays(890));  //输出890天后的时间
        System.out.println(now.minusDays(584));  //输出584天前的时间
    }
}
```



**DateTimeFormatter**

格式日期类，类似于`SimpleDateFormat`

```java
public class DateTimeFormatters {
    public static void main(String[] args) {
        LocalDateTime now=LocalDateTime.now();
        DateTimeFormatter dtf=DateTimeFormatter.ofPattern("yyyy-MM-dd  HH:mm:ss");
        String format = dtf.format(now);  //返回的对象才是格式化后的结果
        System.out.println(format);
    }
}
```



**Instant  时间戳**

类似于Date，提供了一系列和Date类转换的方式。

| Instant  ——>  Date            | Date ——>  Instant                   |
| ----------------------------- | ----------------------------------- |
| Date date=Date.from(instant); | Instant instant = date.toInstant(); |



# 9、集合

## 9.1、集合的分类

- 单列集合
  - Collection 接口有两个重要的子接口  List、Set，他们的实现子类都是单列集合
  - 在Set中，不能有重复元素
- 双列集合
  - Map 接口的实现子类是双列集合，存放的是  K-V(键值对)

> 单列集合

![1672279743361](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/20250620172417315.png)

> 双列集合

![1672279900668](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/20250620172439114.png)

## 9.2、Collection接口

> Collection 接口实现类的特点

- Collection 实现子类 可以存放多个元素，每个元素可以是Object
- 有些Collection的实现类，有些事有序的(List)，有些不是有序(Set)
- Collection 接口没有直接的实现子类，是通过他子接口Set 和 List来实现的

> Collection 接口和常用方法

| add：添加单个元素                   |
| ----------------------------------- |
| remove：删除指定元素                |
| contains：查找元素是否存在          |
| size：获取元素个数                  |
| isEmpty：判断是否为空               |
| clear：清空                         |
| addAll：添加多个元素                |
| containsAll：查找多个元素是否都存在 |
| removeAll：删除多个元素             |



```java
public class CollectionMethod {
    public static void main(String[] args) {
        List list = new ArrayList();

        list.add("jack");
        list.add(10);
        list.add(true);
        System.out.println("list="+list);

//        list.remove(0);//删除第一个元素
//        list.remove(true);//指定删除某个元素
//        System.out.println("list="+list);

        System.out.println(list.contains("jack"));

        System.out.println(list.size());
        System.out.println(list.isEmpty());
        list.clear();
        System.out.println(list.isEmpty());

        ArrayList list1=new ArrayList();
        list1.add("Java核心技术卷·I");
        list1.add("阿里巴巴Java开发手册");
        list1.add("图解设计模式");
        list.addAll(list1);
        System.out.println(list);

        list.add("Redis 实战");
        System.out.println(list.containsAll(list1));
        list.removeAll(list1);
        System.out.println(list);
    }
}
```



## 9.3、Iterator 迭代器

- 迭代器本身就是一个对象，它的工作就是遍历并选择集合序列中的对象。
- 所有实现了Collection 接口的集合类都有一个Iterator() 方法，用以返回一个实现了Iterator 接口的对象，即返回一个迭代器 
- Iterator 仅用于遍历集合，Iterator 本身并不存放对象



```java
public class Iterators {
    public static void main(String[] args) {
        Collection col=new ArrayList();
        col.add(new Book("Java核心技术卷I", "author1",149.5));
        col.add(new Book("图解设计模式", "author2",79.0));
        col.add(new Book("Redis 实战", "author3",69.0));

        System.out.println("col="+col);

        /*
        现在需要遍历集合
        1.先得到 col 对应的迭代器
        2.使用while循环来遍历
        3.当退出 while 循环后，迭代器的游标指向最后一个元素
          不可再往下取了，会报异常
        4.如果希望再次遍历，需要重置迭代器
         */
        Iterator iterator =col.iterator();
        while (iterator.hasNext()) {
            //返回下一个元素，类型是Object
            Object o =iterator.next();
            System.out.println("obj="+o);
        }
        //重置迭代器，第二次遍历
        iterator=col.iterator();
        while (iterator.hasNext()) {
            Object next =  iterator.next();

            System.out.println(next);
        }
    }
}

class Book{
    private String name;
    private String author;
    private double price;

    public Book(String name, String author, double price) {
        this.name = name;
        this.author = author;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", price=" + price +
                '}';
    }
}
```

```java
public class CollectionFor {
    public static void main(String[] args) {
        Collection col=new ArrayList();
        col.add(new Book("Java核心技术卷I", "author1",149.5));
        col.add(new Book("图解设计模式", "author2",79.0));
        col.add(new Book("Redis 实战", "author3",69.0));

        //使用增强for
        /*
        增强for  的底层仍然是迭代器
        增强for可以理解成就是简化版的 迭代器遍历
         */
        for (Object book: col){
            System.out.println(book);
        }

    }
}
```



## 9.4、List接口

> List接口 基本介绍

- List接口是 Collection 接口的子接口 
- List集合类中元素有序，且可重复
- List集合中的每个元素都有其对应的顺序索引，即支持索引
- List容器中的元素都对应一个整数型的序号记载在其容器中的位置，可以根据序号存取容器中的元素



```java
public class List02 {
    public static void main(String[] args) {
        List list=new ArrayList();
        list.add("jack");
        list.add("tom");
        list.add("mary");
        list.add("wjy");
        System.out.println(list);
        //索引，从第0个元素开始的
        System.out.println(list.get(2));
    }
}
```



```java
public class ListMethod {
    public static void main(String[] args) {
        List list=new ArrayList();
        list.add("南0");
        list.add("南1");
        list.add("南2");
        list.add("南2");
        list.add(1,"lxn");
        System.out.println(list);

        List list1=new ArrayList();
        list1.add("jack");
        list1.add("tom");
        list.addAll(1,list1);
        System.out.println(list);

        //list.indexOf("tom") :返回 tom 在集合中首次出现的位置
        System.out.println(list.indexOf("tom"));
        //list.lastIndexOf("廖喜南2")： 返回 廖喜南2 在集合中最后一次出现的位置
        System.out.println(list.lastIndexOf("廖喜南2"));
        //删除
        list.remove(0);
        System.out.println(list);
        //替换
        list.set(0,"html");
        System.out.println(list);
        //list.subList(x,y)取值: 从下标为 x 的值开始取， 取 y 个
        List returnList= list.subList(0,2);
        System.out.println(returnList);
    }
}
```



## 9.5、ArrayList 底层结构和源码分析

> ArrayList 的注意事项

- ArrayList 可以加入null值，并且可以多个null
- ArrayList 是由数组来实现数据存储的
- ArrayList 基本等同于 Vector ，除了ArrayList 是线程不安全（执行效率高），在多线程情况下，不建议使用ArrayList



> ArrayList 的底层操作机制分析

==结论：==

- ArrayList 中维护了一个`Object`类型的数组`elementData`。
- 当创建`ArrayList对象`时，如果使用的是无参构造器，则初始`elementData`容量为0，第一次添加，则扩容为10，如若还需添加，则扩容elementData为1.5倍。
- 当使用的是指定大小的构造器，则初始容量为指定大小，如若扩容，每次扩容为1.5倍。

![1672385360967](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/20250620172504107.png)



```java
public class ArrayListSource {
    public static void main(String[] args) {

        //使用无参构造器，创建ArrayList对象
        ArrayList list=new ArrayList();
//        ArrayList list=new ArrayList(8);

        //使用for给list集合添加 1-10数据
        for (int i = 0; i <=10 ; i++) {
            list.add(i);
        }
        //使用for给list集合添加 11-15数据
        for (int i = 11; i <=15; i++) {
            list.add(i);
        }
        list.add(100);
        list.add(200);
        list.add(null);
    }
}
```



1、创建一个空的elementData数组

![1672386142228](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/20250620172517418.png)

2、将基本数据类型进行装箱。因为集合只能储存引用数据类型。

![1672386378920](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/20250620172557443.png)

![1672386354315](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/20250620172528418.png)

3、执行list.add  

- 先确定是否需要扩容
- 然后再执行 赋值

![1672386441541](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/20250620172731249.png)

4、确定最小容量

![1672394226736](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/20250620172637474.png)

5、modCount++ 记录集合呗修改的次数，  如果elementData的大小不够，就调用grow()去扩容。

![1672394395753](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/20250620172751239.png)

6、扩容使用的是Arrays.copyof() ，本质就是复制数组，保留之前数组的数据。

![1672404247258](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/20250620172817916.png)

当前操作完成后，会回到5、 3、步骤，通过add方法添加数据。



## 9.6、Vector 底层

- Vector 底层也是一个对象数组

![1672406621494](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/20250620172813922.png)

- Vector 是线程同步的，即线程安全 。Vector类的操作方法带有 `synchronized`
- 在开发中，需要线程同步安全时，考虑使用Vector。





## 9.7、LinkedList

> LinkedList 的全面说明

- LinkedList 底层实现了双向链表和双端队列的特点
- 可以添加任意元素，元素可重复，包括null。
- 是线程不安全的，没有实现线程同步



> LinkedList 的底层操作机制

- LinkedList 底层维护了一个双向链表
- LinkedList 中维护了两个属性`frist`和`last`分别指向首节点和尾结点
- 每个节点(Node对象)里面又维护了`prev`、`next`、`item`三个属性，其中通过`prev`指向前一个，通过`next`指向后一个节点。最终实现双向链表。
- 所以LinkedList 的元素添加和删除，不是通过数组完成的，相对来说效率较高。



```java
public class LinkedList02 {
    public static void main(String[] args) {
        LinkedList linkedList=new LinkedList();
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);

        System.out.println(linkedList);
        /*
        源码阅读:
            1.LinkedList linkedList=new LinkedList();
                对应的源码:
                    public LinkedList() {}

            2.这时 LinkedList的属性 first=null   last=null

            3.执行了一个add方法
                public boolean add(E e) {
                    linkLast(e);
                    return true;}

            4.将新的节点，加入到双向链表的最后
                void linkLast(E e) {
                    final Node<E> l = last;
                    final Node<E> newNode = new Node<>(l, e, null);
                    last = newNode;
                    if (l == null)
                        first = newNode;
                    else
                        l.next = newNode;
                    size++;
                    modCount++;}
         */

        linkedList.remove();
        //linkedList.remove(2);
        System.out.println(linkedList);
    }
}
```



> LinkedList的遍历

```java
public class LinkedList03 {
    public static void main(String[] args) {
        LinkedList linkedList=new LinkedList();
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        linkedList.add(4);
        linkedList.add(5);

        Iterator i=linkedList.iterator();
        while (i.hasNext()) {
            Object next =  i.next();
            System.out.println(next);
        }

        System.out.println("--------------");
        for (Object o :linkedList) {
            System.out.println(o);
        }
        System.out.println("--------------------");
        for (int j = 0; j < linkedList.size(); j++) {
            System.out.println(linkedList.get(j));
        }
    }
}
```



## 9.8、ArrayList 、Vector和 LinkedList的比较

|           | 底层结构 | 线程安全       | 扩容倍数                                                     |
| --------- | -------- | -------------- | ------------------------------------------------------------ |
| ArrayList | 可变数组 | 不安全，效率高 | 如果有参构造1.5倍。<br />如果是无参第一次为10，第二次为1.5倍 |
| Vector    | 可变数组 | 安全，效率不高 | 如果是无参，默认为10，满后按2倍扩容<br />如果是指定大小，则每次直接按2倍扩容。 |



|            | 底层结构 | 增删的效率         | 改查的效率 |
| ---------- | -------- | ------------------ | ---------- |
| ArrayList  | 可变数组 | 较低，数组扩容     | 较高       |
| LinkedList | 双向链表 | 较高，通过链表追加 | 较低       |



> 如何选择ArrayList和LinkedList

- 如果我们改查的操作多，选择ArrayList
- 如果我们增删的操作多，选择LinkedList
- 一般来说，在程序中 80%~90%都是查询，因此大部分情况下会选择ArrayList
- 在一个项目中，根据业务灵活选择，也可能这样，一个模块使用的是ArrayList，另一个模块使用的是LinkedList，也就是说，根据业务需要来进行选择。



## 9.9、Set接口

### Set接口介绍

​		Set接口继承**Collection接口**，其特点是它不允许集合中存在重复项，并且容器中对象不按特定的方式排序。Set接口没有引入新方法，所以 Set 就是一个 Collection ，只不过其行为不同。

> 特点

1. 无序，没有索引
2. 不允许重复元素，所以最多包含一个null



### Set接口的常用方法

和List接口一样，Set接口也是Collection的子接口。因此，常用方法和Collection接口一样。

| 方法名                       | 描述                                            |
| ---------------------------- | ----------------------------------------------- |
| boolean  add(Object  o)      | 如果Set中尚未存在指定元素，则添加该元素。       |
| boolean  contains(Object  o) | 判断Set容器中是否包含指定元素，包含则返回true   |
| boolean  equals(Object  o)   | 比较指定对象是否与Set容器对象相等，相等返回true |
| boolean  isEmpty()           | 判断Set容器是否为空，空则返回true               |
| boolean  remove(Object  o)   | 将Set容器中的指定元素删除                       |
| void  clear()                | 删除Set容器中的所有元素                         |
| int  size()                  | 返回Set容器中的元素个数                         |



### Set接口的遍历方式

同Collection的遍历方式一样。

- 可以使用迭代器
- 增强for
- ==不能使用索引的方式来获取==

```java
public class SetMethod {
    public static void main(String[] args) {
        /*
        以Set接口的实现类，HashSet来学习Set接口
        1、Set接口的实现类的对象(Set接口对象)，不能存放重复的元素，可以添加一个null
        2、Set接口对象，存放数据是无序的。即添加的顺序和取出的顺序不一致
        3、注意: 取出的顺序是固定的，这是由底层的算法决定的
         */
        Set set=new HashSet();
        set.add("john");
        set.add("lucy");
        set.add("john");
        set.add("jack");
        set.add("wjy");
        set.add(null);
        set.add(null);
        System.out.println(set);

        System.out.println("--------------");
        /*
        遍历
        1、迭代器
        2、增强for
        3、不能索引，所以没有普通for循环遍历
         */
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            Object next =  iterator.next();
            System.out.println(next);
        }
        System.out.println("------------------");

        for (Object o :set) {
            System.out.println(o);
        }
    }
}
```



### HashSet

- HashSet是 Set接口的实现类
- HashSet实际上是HashMap，线程不同步
- HashSet可以存放null值，但是只能有一个null
- HashSet不保证元素是有序的，取决于散列法的机制储存后，再确定索引的结果
- HashSet也不能有重复的对象



#### 探索HashSet底层

> HashSet底层

![1672725742533](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/20250620172839850.png)

> HashSet01

```java
public class HashSet01 {
    public static void main(String[] args) {
        Set hashSet=new HashSet();
        hashSet.add(null);   //hashSet可以存放null值，但只能有一个
        hashSet.add(null);
        System.out.println(hashSet);
        System.out.println("----------------");
        //add方法的返回值为 boolean类型
        System.out.println(hashSet.add("John"));
        System.out.println(hashSet.add("Lucy"));
        System.out.println(hashSet.add("John"));  //false,因为数据是重复的，不能重复添加
        System.out.println(hashSet.add("Jack"));
        System.out.println(hashSet.add("Rose"));
        hashSet.remove("John");
        System.out.println(hashSet);
    }
}
```



> 代码模拟HashSet的底层结构

```java
public class HashSetStructure {
    public static void main(String[] args) {
        /*
        模拟一个HashSet的底层(HashMap的底层结构)
        1、创建一个数组，数组的类型是 Node[]
        2、创建结点
         */
        Node[] table=new Node[16];

        Node john = new Node("john", null);
        table[2]=john;
        Node jack = new Node("jack", null);
        john.next=jack;//将 jack结点挂载到john后面
        Node rose = new Node("rose", null);
        jack.next=rose;

        Node lucy = new Node("lucy", null);
        table[3]=lucy;

        System.out.println("table="+table);
    }
}
class Node{
    Object item;//数据
    Node next;//指向下一个结点

    public Node(Object item, Node next) {
        this.item = item;
        this.next = next;
    }
}
```

==在debug过程中，可以直观的看到链表==

![1672815053008](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/20250620172854155.png)

> HashSet的底层机制说明

1. 添加一个元素时，先得到hash值。hash值  ----转换成---->  索引值
2. 找到存储数据表table，看这个索引位置是否已经存放的有元素
3. 如果没有，直接加入元素
4. 如果有，调用`equals`比较。如果相同，就放弃添加；如果不同，则添加到最后
5. 在Java8中，如果一条链表的元素个数到达了`TREEIFY_THRESHOLD(默认是8)`，并且table的大小 >=`MIN_TREEIFY_CAPACITY(默认64)`，就会进行树化（红黑树）

> 源码解读

```java
public class HashSetSource {
    public static void main(String[] args) {
        HashSet hashSet=new HashSet();
        hashSet.add("java");
        hashSet.add("php");
        hashSet.add("java");
        System.out.println(hashSet);
    }
}
```

```java
//1、执行HashSet()
public HashSet() {
    map = new HashMap<>();
}

//2、执行add()
//把传进来的E放在key，新建的静态final的PRESENT对象放在value
//不管放入多少个元素，这些元素都存在map里面，这个map里面所有的value值都是同一个对象
public boolean add(E e) {    //e: "java"
        return map.put(e, PRESENT)==null;    //map: "{}"  e: "java"
    }

//3、执行put(),该方法会执行 hash(key)方法 得到key对应的hash值
public V put(K key, V value) {   //key: "java"   value: Object@497
        return putVal(hash(key), key, value, false, true);
    }

static final int hash(Object key) {  //key: "java"
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

//4、执行putVal
final V putVal(int hash, K key, V value, boolean onlyIfAbsent,
                   boolean evict) {
        Node<K,V>[] tab; Node<K,V> p; int n, i;  //定义了一些辅助变量
    	//table就是 HashMap的一个数组，类型就是一个Node[]
        //if语句表示如果 当前table是null，或者 大小=0
        //就是第一次扩容，到16个空间
        if ((tab = table) == null || (n = tab.length) == 0) //table: null
            n = (tab = resize()).length;
    	//(1)根据key，得到的hash值，去计算改key应该存放到table表的哪个索引位置
    	//并把这个位置的对象，赋给 p
    	//(2)判断p是否为null
    	//如果p为null，表示还没有存放过元素，就创建一个Node(key: "java",value: PRESENT)，并把元素放在该位置: tab[i] = newNode(hash, key, value, null)
        if ((p = tab[i = (n - 1) & hash]) == null)
            tab[i] = newNode(hash, key, value, null);
        else {
            Node<K,V> e; K k;
            if (p.hash == hash &&
                ((k = p.key) == key || (key != null && key.equals(k))))
                e = p;
            else if (p instanceof TreeNode)
                e = ((TreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value);
            else {
                for (int binCount = 0; ; ++binCount) {
                    if ((e = p.next) == null) {
                        p.next = newNode(hash, key, value, null);
                        if (binCount >= TREEIFY_THRESHOLD - 1) // -1 for 1st
                            treeifyBin(tab, hash);
                        break;
                    }
                    if (e.hash == hash &&
                        ((k = e.key) == key || (key != null && key.equals(k))))
                        break;
                    p = e;
                }
            }
            if (e != null) { // existing mapping for key
                V oldValue = e.value;
                if (!onlyIfAbsent || oldValue == null)
                    e.value = value;
                afterNodeAccess(e);
                return oldValue;
            }
        }
        ++modCount;   //记录修改次数
        if (++size > threshold)  //判断当前大小是否超过一个阈值，如果超过了，就要准备扩容
            resize();
        afterNodeInsertion(evict);
        return null;    //执行成功了，就返回一个null
    }
//返回到putVal(),add()添加成功

```

> 第二次执行add("java")的源码

~~~java
//1、执行HashSet()
public HashSet() {
    map = new HashMap<>();
}

//2、执行add()
//把传进来的E放在key，新建的静态final的PRESENT对象放在value
//不管放入多少个元素，这些元素都存在map里面，这个map里面所有的value值都是同一个对象
public boolean add(E e) {    //e: "java"
        return map.put(e, PRESENT)==null;    //map: "{}"  e: "java"
    }

//3、执行put(),该方法会执行 hash(key)方法 得到key对应的hash值
public V put(K key, V value) {   //key: "java"   value: Object@497
        return putVal(hash(key), key, value, false, true);
    }

static final int hash(Object key) {  //key: "java"
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

//4、执行putVal
    final V putVal(int hash, K key, V value, boolean onlyIfAbsent,
                   boolean evict) {
        Node<K,V>[] tab; Node<K,V> p; int n, i;
        if ((tab = table) == null || (n = tab.length) == 0)  //false
            n = (tab = resize()).length;
        if ((p = tab[i = (n - 1) & hash]) == null) 
            //false，因为 之前存放过"java",i=3不为null
            tab[i] = newNode(hash, key, value, null);
        else {
            Node<K,V> e; K k;
            //如果当时索引位置对应的链表的第一个元素和准备添加的key的hash值一样，并且满足下面两个条件之一：
            //(1)准备加入的key 和 p 指向的 Node结点的key 是同一个对象
            //(2)p 指向的Node结点的key的 equals()和准备加入的key比较后相同
            //就无法添加
            if (p.hash == hash &&
                ((k = p.key) == key || (key != null && key.equals(k))))
                e = p;
            //这里判断p是不是一颗红黑树。如果是一颗红黑树，就调用putTreeVal()方法进行添加
            else if (p instanceof TreeNode)
                e = ((TreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value);
            //这里则是之前的判断，还不构成红黑树，以链表的形式存在
            else {
                //用for循环去遍历链表，查看有没有和添加元素相同的元素。
                //如果有，则直接跳出循环，不添加。
                //如果遍历完，没发现和需要添加的元素相同的元素，则将元素添加至最后。
                //binCount的作用: 在把元素添加到链表后，立即判断该链表是否已经达到8个结点
                //如果达到了8个结点，就调用treeifyBin()方法，将当前的链表准备树化(红黑树)
                //在转成红黑树时，还进行一个判断，源码在下
                for (int binCount = 0; ; ++binCount) {
                    if ((e = p.next) == null) {
                        p.next = newNode(hash, key, value, null);
                        if (binCount >= TREEIFY_THRESHOLD - 1) // -1 for 1st
                            treeifyBin(tab, hash);
                        break;
                    }
                    if (e.hash == hash &&
                        ((k = e.key) == key || (key != null && key.equals(k))))
                        break;
                    p = e;
                }
            }
            if (e != null) { // existing mapping for key
                V oldValue = e.value;
                if (!onlyIfAbsent || oldValue == null)
                    e.value = value;
                afterNodeAccess(e);
                return oldValue;
            }
        }
        ++modCount;
        if (++size > threshold)
            resize();
        afterNodeInsertion(evict);
        return null;
    }

~~~

> 树化的判断条件

```java
final void treeifyBin(Node<K,V>[] tab, int hash) {
    int n, index; Node<K,V> e;
    //如果改table数组的大小< MIN_TREEIFY_CAPACITY:64
    if (tab == null || (n = tab.length) < MIN_TREEIFY_CAPACITY)
        //条件成立，则先给table进行扩容
        resize();
    //如果上面条件不成立，则进行树化
    else if ((e = tab[index = (n - 1) & hash]) != null) {
        TreeNode<K,V> hd = null, tl = null;
        do {
            TreeNode<K,V> p = replacementTreeNode(e, null);
            if (tl == null)
                hd = p;
            else {
                p.prev = tl;
                tl.next = p;
            }
            tl = p;
        } while ((e = e.next) != null);
        if ((tab[index] = hd) != null)
            hd.treeify(tab);
    }
}
```



#### HashSet扩容和树化机制

> HashSet的扩容及树化机制

1. 第一次添加时，table数组扩容到16，临界值`threshold=(16)`*加载因子`loadFactor=(0.75)`=12
2. 如果table数组使用到了临界值12，就会扩容到16 * 2 * 0.75=24。以此类推
3. 在Java8中，如果一条链表的元素个数到达了`TREEIFY_THRESHOLD(默认是8)`，并且table的大小 >=`MIN_TREEIFY_CAPACITY(默认64)`，就会进行树化（红黑树）
4. 每执行一次`add()`方法，就会触发一次`size++`。不管元素是否在同一链表上。

> 测试扩容及树化的代码。打断点，看调试结果

```java
public class HashSetIncrement {
    public static void main(String[] args) {
        /*
        1、 第一次添加时，table数组扩容到16，临界值`threshold=(16)`*加载因子`loadFactor=(0.75)`=12
        2、 如果table数组使用到了临界值12，就会扩容到16 * 2 * 0.75=24。以此类推
        3、 在Java8中，如果一条链表的元素个数到达了`TREEIFY_THRESHOLD(默认是8)`，并且table的大小 >=`MIN_TREEIFY_CAPACITY(默认64)`，就会进行树化（红黑树）
         */
        HashSet hashSet=new HashSet();
//        for (int i = 1; i <= 100; i++) {
//            hashSet.add(i);
//        }
//        System.out.println(hashSet);
        for (int i = 1; i <= 12; i++) {
            hashSet.add(new A(i));//equals()
        }
        /*
        当我们向hashSet增加一个元素，-> Node ->加入table，就算是增加了一个size++
         */
        for (int i = 1; i <= 7; i++) {//在table的某一条链表上添加了 7个A对象
            hashSet.add(new A(i));
        }
        for (int i = 1; i <= 7; i++) {//在table的某一条链表上添加了 7个B对象
            hashSet.add(new B(i));
        }
    }
}

class A{
    private int n;

    public A() {
    }

    public A(int n) {
        this.n = n;
    }
    
    @Override
    public int hashCode() {
        return 100;
    }
}
class B{
    private int n;

    public B() {
    }

    public B(int n) {
        this.n = n;
    }
    
    @Override
    public int hashCode() {
        return 200;
    }
}
```



> HashSetExercise01练习

```java
public class HashSetExercise01 {
    /*
    练习: 定义一个Employee类，该类包含 private成员属性name、age
    要求: 1、创建3个Employee对象，放入HashSet中
         2、当name和age的值相同是，认为是相同的员工，不能添加到HashSet集合中
     */
    public static void main(String[] args) {
        HashSet set=new HashSet();
        set.add(new Employee("张三",23));
        set.add(new Employee("李四",24));
        set.add(new Employee("王五",23));
        set.add(new Employee("赵六",21));
        set.add(new Employee("孙七",30));
        set.add(new Employee("张三",24));
        set.add(new Employee("张三",23));
        System.out.println(set);
    }
}
class Employee{
    private String name;
    private int age;

    public Employee() {
    }

    public Employee(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}'+'\n';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return age == employee.age &&
                Objects.equals(name, employee.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }
}
```



> HashSetExercise02练习

```java
public class HashSetExercise02 {
    /*
    练习: 定义一个Employee类，该类包含 private成员属性name、salary、birthday(MyDate类型)，其中birthday类型要包含 年、月、日
    要求: 1、创建3个Employee对象，放入HashSet中
         2、当name和birthday的值相同是，认为是相同的员工，不能添加到HashSet集合中
     */
    public static void main(String[] args) {
        HashSet set=new HashSet();
        set.add(new Employees("Elise",5324.00,new MyDate(2001,11,17)));
        set.add(new Employees("Zoe",6300.00,new MyDate(2000,5,29)));
        set.add(new Employees("Leona",4215.00,new MyDate(1999,4,20)));
        set.add(new Employees("Thresh",7648.00,new MyDate(2000,8,4)));
        set.add(new Employees("Thresh",7648.00,new MyDate(1997,12,23)));
        set.add(new Employees("Riven",3487.00,new MyDate(2000,8,4)));
        set.add(new Employees("Thresh",7814.00,new MyDate(2000,8,4)));
        System.out.println(set);
    }
}
class Employees{
    private String name;
    private double salary;
    private MyDate birthday;

    public Employees() {
    }

    public Employees(String name, double salary, MyDate birthday) {
        this.name = name;
        this.salary = salary;
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public MyDate getBirthday() {
        return birthday;
    }

    public void setBirthday(MyDate birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "Employees{" +
                "name='" + name + '\'' +
                ", salary=" + salary +
                ", birthday=" + birthday +
                '}'+'\n';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employees employees = (Employees) o;
        return Objects.equals(name, employees.name) &&
                Objects.equals(birthday, employees.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, birthday);
    }
}
```

```java
public class MyDate {
    int year;
    int month;
    int day;

    public MyDate() {
    }

    public MyDate(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    @Override
    public String toString() {
        return year + "-" + month + "-" + day;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyDate myDate = (MyDate) o;
        return year == myDate.year &&
                month == myDate.month &&
                day == myDate.day;
    }

    @Override
    public int hashCode() {
        return Objects.hash(year, month, day);
    }
}
```



### LinkedHashSet

#### LinkedHashSet的继承关系

- `LinkedHashSet`是`HashSet`的子类
- `LinkedHashSet`底层是一个`LinkedHashMap`，底层维护了一个**数组+双向链表**
- `LinkedHashSet`根据元素的`hashCode`值来决定元素的存储位置，同时使用链表维护元素的次序，这使得元素看起来是以插入顺序保存的
- `LinkedHashSet`属于`Set接口`不允许有重复元素

> 继承关系图

![1673163473888](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/20250620172921267.png)



#### LinkedHashSet特点

- 在`LinkedHashSet`中维护了一个`hashtable`和`双向链表(包含头结点:head、尾结点:tail)`
- 每个结点有`before`和`after`属性，这样可以形成双向链表
- 在添加一个元素时，先求**hash值**，再求**索引值**，以确定该元素在`hashtable`的位置，然后将元素添加到双向链表。（如果已经存在，则不添加）
- `LinkedHashSet`和`LinkedHashMap`类会记住插入元素项的顺序。这样就可以避免`hashtable`中的项看起来顺序是随机的。（即对`LinkedHashSet`遍历，输出结果和输入结果一致）



#### LinkedHashSet底层

==底层逻辑与HashSet类似，与之不同的是存放结点的类型。不再是Node[]数组了==

> 示例代码

```java
public class LinkedHashSetSource01 {
    public static void main(String[] args) {
        Set set=new LinkedHashSet();
        set.add(new String("AA人"));
        set.add(456);
        set.add(456);
        set.add(new Customer("伍",201904440112L));
        set.add(123);
        set.add("伍俊尧");
        System.out.println(set);
        /*
        1、LinkedHashSet 加入和取出元素的顺序一致
         */
    }
}
class Customer{
    private String name;
    private long no;

    public Customer() {
    }

    public Customer(String name, long no) {
        this.name = name;
        this.no = no;
    }

    @Override
    public String toString() {
        return "Customer:{" +name + ',' + no + '}';
    }
}
```



> 结点存放

- table是`HashMap$Node[]类型`，而存放元素的是`LinkedHashMap$Entry类型`

```java
//Entry继承HashMap，继承关系在内部类中完成的
static class Entry<K,V> extends HashMap.Node<K,V> {
    Entry<K,V> before, after;
    Entry(int hash, K key, V value, Node<K,V> next) {
        super(hash, key, value, next);
    }
}
```



> 练习

```java
public class LinkedHashSetExercise01 {
    public static void main(String[] args) {
        /*
        创建Car类(属性: name、price)
        要求: 如果 名字 和 价格 一样，则认为是相同的对象，就不添加
         */
        Set set=new LinkedHashSet();
        set.add(new Car("法拉利F8",3_057_000.00));
        set.add(new Car("路虎揽胜",1_427_900.00));
        set.add(new Car("迈巴赫S级",1_447_900.00));
        set.add(new Car("劳斯莱斯幻影",8_000_000.00));
        set.add(new Car("奥迪A8",720_000.00));
        set.add(new Car("路虎揽胜",1_427_900.00));
        set.add(new Car("奥迪A8",810_000.00));
        System.out.println(set);
    }
}
class Car{
    private String name;
    private double price;

    public Car() {
    }

    public Car(String name, double price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Double.compare(car.price, price) == 0 &&
                Objects.equals(name, car.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price);
    }

    @Override
    public String toString() {
        return "Car{" + name + "," + price +'}'+'\n';
    }
}
```



### TreeSet

> 介绍

TreeSet，即树集，顾名思义使用一棵树来进行存储的。每添加一个元素到树中时，都会将其放在正确的排序位置上。因此，迭代器总是能有序的将每个元素都遍历出来。

TreeSet可以排序，在正常情况下是默认的排序方法，也可以用户自己定制排序方法。



#### TreeSet的默认排序

- 如果是数字，则按照数字大小排序。
- 如果是字符，则按照对应的 ASCII 码排序 (字典排序)。
- 默认都是由小到大。

```java
public class TreeSet01 {
    public static void main(String[] args) {
        TreeSet treeSet=new TreeSet();
        treeSet.add(1);
        treeSet.add(6);
        treeSet.add(26);
        treeSet.add(8);
        treeSet.add(6);
        treeSet.add(9);
        System.out.println(treeSet);
        System.out.println("----------------------");
        TreeSet treeSet1=new TreeSet();
        treeSet1.add("Air");
        treeSet1.add("Corki");
        treeSet1.add("Garen");
        treeSet1.add("Aatrox");
        treeSet1.add("Lillia");
        System.out.println(treeSet1);
    }
}
```

> 排序结果

![1674112391343](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/20250620172956433.png)



#### TreeSet的定制排序

TreeSet中有一个构造器，该构造器可以传入一个比较器（匿名内部类），并指定排序规则。

```java
public TreeSet(Comparator<? super E> comparator) {
    this(new TreeMap<>(comparator));
}
```



```java
//按照ASCII码值，倒序排序
public class TreeSet02 {
    public static void main(String[] args) {
        TreeSet treeSet=new TreeSet(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                return ((String)o2).compareTo((String)o1);
            }
        });
        treeSet.add("Andy");
        treeSet.add("andy");
        treeSet.add("Ashe");
        treeSet.add("Ben");
        treeSet.add("Dev");
        treeSet.add("Joy");
        System.out.println(treeSet);
    }
}
```





## 9.10、Map接口

### 关于Map接口

> Map接口的结构示意图

![1673333134848](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/20250620173014053.png)



> Map接口的特点

1. Map与Collection并列存在。用于保存具有映射关系的数据：`Key-Value(键值对)`
2. Map中的 ==Key和Value== 可以是任何引用类型的数据，会封装到`HashMap$Node[]`对象中
3. Map中的==Key==不可以重复，==Value==可以重复
4. Map的 ==Key和Value== 可以为null，但是 ==Key== 只能有一个null，==Value== 可以有多个
5. 常用 String类 作为 Map的Key
6. Key和Value 之间存在单向一对一关系，即通过==指定的Key总能找到对应的Value==



```java
public class Map01 {
    public static void main(String[] args) {
        /*
        Map接口实现类的特点
         */
        Map map=new HashMap();

        map.put("106","xxy");
        map.put("112","wjy");
        map.put("112","Wjy");
        map.put("136","lxn");
        map.put("123","fmh");
        map.put("114","lk");
        map.put(null,null);
        map.put(null,123);
        map.put(123,null);

        System.out.println(map);
    }
}
```



> Map的常用方法

**Map接口**不是**Collection接口**的继承。而是从用于维护`K-V`关联的接口层次结构入手。Map接口中的元素都是`Key(键)与Value(值)`成对存储的，因而需要保证键的唯一性。Map接口定义有以下一些常用方法。

| 方法                                                         | 描述                                                         |
| :----------------------------------------------------------- | :----------------------------------------------------------- |
| Object put (Object key, Object value)                                                                                                           . | 添加或替换一对元素，返回键对应的旧值，若无旧值则返回**null**(==注意，不是返回新值==)。当存储的键不存在时即为添加。键已存在时为替换，新值会替换旧值并返回旧值。 |
| void putAll (Map m)                                          | 添加一堆元素。                                               |
| void clear()                                                 | 清空。                                                       |
| Object remove(Object key)                                    | 删除指定键，返回对应的值                                     |
| boolean isEmpty()                                            | 判空。                                                       |
| 遍历方法                                                     |                                                              |
| Object get(Object key)                                       | 根据Key（键），获取对应的值。                                |
| boolean containsKey(Object key)                              | 判断Map中是否存在某键（Key）。                               |
| boolean containsValue(Object value)                          | 判断Map中是否存在某值（Value）。                             |
| int size()                                                   | 返回Map（K-V）键值对的个数。                                 |
| public Set keySet()                                          | 返回所有的键（Key），并使用Set容器存放。                     |
| public Collection values()                                   | 返回所有的值（Value），并使用Collection存放。==注意，此方法在enumMap中，Map本身没有该方法== |
| public Set entrySet()                                        | 返回一个实现 Map.Entry接口的元素Set。（为了对Map(映射)进行遍历，将Map转变为一个Set集合）详细介绍和用法在下 |



### Map.Entry接口

**Map.Entry**是**Map**中的一个内部接口，专门用来保存`Key-Value`的内容。

- Map存放数据的`K-V`示意图，一对`K-V`是放在一个`HashMap$Node`中的，又因为Node实现类Entry接口，有些书上也说，一对`K-V`就是一个Entry（如图）

![1673343414155](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/20250620173029844.png)

> Map.Entry

- 这里的内部接口一定是static的，因为接口是不能实例化的，所以为了访问到接口中的接口，必须定义为static。如果不指定，则默认就是static。
- 此接口可以由外部通过"外部类"



```Java
interface Entry<K,V> {
    K getKey();      //获取键
    V getValue();    //获取值
    V setValue(V value);
```



### HashMap的遍历方法

```java
public class HashFor {
    public static void main(String[] args) {
        Map map=new HashMap();
        map.put("105","wzy");
        map.put("106","xxy");
        map.put("107","xwq");
        map.put("112","wjy");
        map.put("114","lk");
        map.put("119","yyf");
        map.put("121","tt");
        map.put("123","fmh");
        map.put("136","lxn");

        //第一组: 先取出所有的Key，通过Key获得Value
        Set keySet=map.keySet();

        //方式一: forEach
        for (Object key :keySet) {
            System.out.println(key+","+map.get(key));
        }
        System.out.println("-------------------------------");
        //方式二: 迭代器
        Iterator iterator=keySet.iterator();
        while (iterator.hasNext()) {
            Object next =  iterator.next();
            System.out.println(next+","+map.get(next));
        }
        System.out.println("-------------------------------");


        //第二组: 直接取出所有Value
        /*
        因为 values 是Collection类型的，所以可以使用Collection的遍历方法
        values方法是没办法反向取Key的
         */
        Collection values =map.values();
        //forEach
        for (Object o :values) {
            System.out.println(o);    //此时取出的只有值
        }
        //迭代器
        System.out.println("----------------------------------");
        Iterator iterator1=values.iterator();
        while (iterator1.hasNext()) {
            Object next =  iterator1.next();
            System.out.println(next);
        }
        System.out.println("----------------------------------");

        //第三组: 通过entrySet 获取K-V
        Set entrySet=map.entrySet();

        //forEach
        for (Object entry :entrySet) {
            Map.Entry m = (Map.Entry) entry;
            System.out.println(m.getKey()+","+m.getValue());
        }
        System.out.println("---------------------------------");
        //迭代器
        Iterator iterator2=entrySet.iterator();
        while (iterator2.hasNext()) {
            Object next = iterator2.next();
            //System.out.println(next.getClass());  //HashMap$Node
            Map.Entry m= (Map.Entry) next;
//            System.out.println(m); //直接输出m就会把键值对都输出出来
            System.out.println(m.getKey()+","+m.getValue());
        }
    }
}
```



### 练习

```java
public class MapExercise01 {
    /*
    练习: 使用HashMap添加3个员工对象
    要求: K: 员工id
         V: 员工对象
         员工类: 姓名、id、工资
         遍历工资> 18000的员工
     */
    public static void main(String[] args) {
        Map map = new HashMap();
        map.put(0001, new Employee(0001, "Jack", 14000.00));
        map.put(0002, new Employee(0002, "Tom", 21000.00));
        map.put(0003, new Employee(0003, "Rosa", 18724.71));

        //遍历:
        Set set=map.keySet();
        //forEach
        for (Object key :set) {
            //先 获取value
            Employee emp = (Employee) map.get(key);
            if (emp.getSalary()>18000){
                System.out.println(emp);
            }
        }
        System.out.println("------------------------------------------------");
        //迭代器
        Set entrySet=map.entrySet();
        Iterator iterator=entrySet.iterator();
        while (iterator.hasNext()) {
            Map.Entry next = (Map.Entry) iterator.next();
            //通过entry 获取 K和V
            Employee emp = (Employee) next.getValue();
            if (emp.getSalary()>18000){
                System.out.println(emp);
            }
        }
    }
}

class Employee {
    private int id;
    private String name;
    private double salary;

    public Employee() {
    }

    public Employee(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id == employee.id &&
                Objects.equals(name, employee.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
```



### Hashtable

> 介绍

**Hashtable**是**Map**接口的一个实现类，其父类是**Dictionary**。

**Hashtable**的使用方法与**HashMap**基本一致。

> Hashtable 和 HashMap的区别

| 比较点    | Hashtable                      | HashMap                                                      |
| --------- | ------------------------------ | ------------------------------------------------------------ |
| 推出时间  | JDK1.2之后推出，属于新的操作类 | JDK1.0时推出，属于旧的操作类                                 |
| 性能      | 采用异步处理方式，性能高       | 采用同步处理方式，性能低                                     |
| 线程安全  | 非线程安全                     | 线程安全                                                     |
| null 操作 | Key 和 Value都允许保存 null    | Key 和 Value 都不允许保存 null，否则会抛 **NullPointerException** 异常 |



> Hashtable的使用

~~~java
public class Hashtable01 {
    public static void main(String[] args) {
        Hashtable table =new Hashtable();
        table.put("john",100);  //成功
        table.put(null,100);    //键为null, 抛异常
        table.put("lxn",null);  //值为null, 抛异常
        table.put("rose",80);   //成功
        table.put("john",80);   //将键为 john 的值迭代
        System.out.println(table);
    }
}
~~~



> Hashtable的底层

1. 底层有一个数组 **Hashtable$Entry[]**，其初始化大小为11。(下标为0~10)
2. 临界值:    threshold = 11 * 0.75(加载因子) = 8。

![1674100611474](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/20250620173055526.png)



**扩容机制**

```java
int newCapacity = (oldCapacity << 1) + 1;
```

当达到临界值时，执行这段代码。翻译（2n+1）



> 具体源码分析

```java
//1、执行put方法，将程序中的key 和 value 传入 put方法
public synchronized V put(K key, V value) {
    if (value == null) {
        throw new NullPointerException();
    }
    Entry<?,?> tab[] = table;
    // 将key转化为hash码，并生成一个index
    int hash = key.hashCode();
    int index = (hash & 0x7FFFFFFF) % tab.length;
    @SuppressWarnings("unchecked")
    Entry<K,V> entry = (Entry<K,V>)tab[index];
    
    //此处为替换方法，如果是key是第一次添加，就执行下面的 addEntry() 方法
    for(; entry != null ; entry = entry.next) { //判断entry数组是否为空，循环结束，将老的数组置null
        if ((entry.hash == hash) && entry.key.equals(key)) {//判断是不是当前要操作的这个键值对
            V old = entry.value; //将当前数组的值，赋给一个临时变量
            entry.value = value; //把现在的值赋给数组的值，数组的值替换成功
            return old;          //返回之前的值
        }
    }
    
    //执行addEntry方法，将键值对添加到数组，这样方便开发者后期遍历所有的键值对
    addEntry(hash, key, value, index);
    return null;
}

private void addEntry(int hash, K key, V value, int index) {
        modCount++;  //用来计数，该方法用了几次。

        Entry<?,?> tab[] = table;
    //count：人话（数组里面有几个数了）
    //如果当前数组 >= 临界值，就进行扩容
        if (count >= threshold) {
            // Rehash the table if the threshold is exceeded
            rehash();   //扩容方法  int newCapacity = (oldCapacity << 1) + 1; 翻译：2n+1

            tab = table;
            hash = key.hashCode();
            index = (hash & 0x7FFFFFFF) % tab.length;
        }

        // Creates the new entry.  翻译：生成新的数组
        Entry<K,V> e = (Entry<K,V>) tab[index];
        tab[index] = new Entry<>(hash, key, value, e);  //将键值对、Hash码通通放到table数组里面
        count++;//丢入一项数据，计数器+1
    }
```



### Properties

> 基本介绍

1. **Properties类**继承自**Hashtable类**，并实现了Map接口，也是使用一种键值对的形式来保存数据。
2. **Properties**还可以用于从  xxx.properties  文件中，加载数据到Properties类对象，并进行读取和修改。
3. 使用方法和**Hashtable**类似。
4. **==☆（工作中常用）==** xxx.properties文件，通常作为配置文件，IO流中介绍。



## 9.11、Collections工具类

> Collections介绍

- Collections是一个操作Set、List和Map等集合的工具类。
- Collections中提供了一系列静态的方法对集合元素进行排序、查询和修改等操作

> 排序操作

- reverse(List)：反转List中元素的顺序
- shuffle(List)：对List集合元素进行随机排序
- sort(List)：根据元素的自然顺序对指定 List集合元素按升序排序
- sort(List，Comparator)：根据指定的Comparator 产生的顺序对List集合元素进行排序
- swap(List、int、int)：将指定List集合中的 i处元素 和 j处元素进行交换

> 操作演示

```java
public class Collections01 {
    public static void main(String[] args) {
        List list=new ArrayList();
        list.add("Tom");
        list.add("Jack");
        list.add("Asher");
        list.add("Anna");

//        Collections.reverse(list);
//        Collections.shuffle(list);  //随机打乱，每次运行的结果都不一样

//        Collections.sort(list, new Comparator() {
//            @Override
//            public int compare(Object o1, Object o2) {
//                return ((String)o1).length()-((String)o2).length();
//            }
//        });
        Collections.swap(list,0,2);
        System.out.println(list);
    }
}
```



> 替换、查找方法

| 方法                                                         | 效果                                                       |
| ------------------------------------------------------------ | ---------------------------------------------------------- |
| Object max（Collection）                                     | 根据元素的自然顺序，返回给定集合中的**最大元素**           |
| Object max（Collection，Comparator）                         | 根据**Comparator**指定的顺序，返回给定集合中的**最大元素** |
| Object min（Collection）                                     | 根据元素的自然顺序，返回给定集合中的**最小元素**           |
| Object min（Collection，Comparator）                         | 根据**Comparator**指定的顺序，返回给定集合中的**最小元素** |
| int frequency（Collection，Object）                          | 返回指定集合中指定元素的出现次数                           |
| void copy（List dest，List src）                             | 将**src**中的内容复制到**dest**中                          |
| boolean replaceAll（List list，Object oldVal，Object newVal） | 使用新值替换**List**对象的所有旧值                         |



> 演示

```java
public class Collections02 {
    public static void main(String[] args) {
        List list=new ArrayList();
        list.add("Tom");
        list.add("Tom");
        list.add("Jack");
        list.add("Asher");
        list.add("Anna");
        System.out.println(Collections.max(list));
        System.out.println(Collections.min(list));

        System.out.println(Collections.max(list, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                return ((String)o1).length()-((String)o2).length();
            }
        }));

        System.out.println(Collections.frequency(list,"Tom"));

        ArrayList dest = new ArrayList();
        /*
        此处代码是为了给dest增加长度，使目标数组的长度 和 资源数组的长度一致。
        如果不进行扩容，直接运行就会抛 IndexOutOfBoundsException 异常，意思是 数组下标越界
         */
        for (int i = 0; i < list.size(); i++) {
            dest.add("");
        }
        Collections.copy(dest,list);
        System.out.println(dest);

        Collections.replaceAll(list,"Tom","JoJo");
        System.out.println(list);
    }
}
```



## 9.12、集合总结

>  在开发中如何选择集合实现类

如何实现，主要取决于业务操作特点。分析如下：

判断存储的类型（对象 还是 键值对）

- 一组对象（单列）：**Collection接口**
  - 允许重复：**List**
    - 增删多：**LinkedList**（底层是一个双向链表）
    - 改查多：**ArrayList**（底层是一个object类型的可变数组）
  - 不允许重复：**Set**
    - 无序：**HashSet**（底层是HashMap，即数组+链表+红黑树）
    - 有序：**TreeSet**
    - 插入和取出顺序一致：**LinkedHashSet**（数组+双向链表）
- 一组键值对（双列）：**Map接口**
  - 键无序：**HashMap**（底层是哈希表，jdk7：数组+链表。jdk8：数组+链表+红黑树）
  - 键有序：**TreeMap**
  - 键插入和取出顺序一致：**LinkedHashMap**
  - 读取文件：**Properties**



### 练习

> 练习1

```java
public class ListHomeWork01 {
    /*
    1、封装个新闻类， 包含标题和内容属性，提供get、 set方法，重写toString 方法，打印对象时只打印标题;
    2、只提供一个带参数的构造器， 实例化对象时，只初始化标题;并且实例化两个对象:
            新闻一:新冠确诊病例超千万，数百万印度教信徒赴恒河”圣浴"引民众担忧
            新闻二:男子突然想起2个月前钓的鱼还在网兜里，捞起一看赶紧放生
    3、将新闻对象添加到ArrayList集合中，并且进行倒序遍历;
    4、在遍历集合过程中，对新闻标题进行处理，超过15字的只保留前15个，然后在后边加"..."
    5、在控制台打印遍历出 经过处理的新闻标题;
     */
    public static void main(String[] args) {
        List list=new ArrayList();
        list.add(new News("男子突然想起2个月前钓的鱼还在网兜里，捞起一看赶紧放生"));
        list.add(new News("新冠确诊病例超千万，数百万印度教信徒赴恒河\"圣浴\"引民众担忧"));
        list.add(new News("广告位招租！"));

//        System.out.println(list);

        int size = list.size();
        for (int i = size-1; i >=0; i--) {
//            System.out.println(list.get(i));
            News news = (News) list.get(i);
            System.out.println(news.processTitle(news.getTitle()));
        }
    }
}
class News{
    private String title;
    private String content;

    public News() {
    }

    public News(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "News{" +
                "title='" + title + '\'' +
                '}';
    }

    public String processTitle(String title){
        if (title==null){
            return "";
        }
        if (title.length()>15)
            return title.substring(0,15)+"...";
        else
            return title;
    }
}
```



# 10、泛型

## 10.1、什么是泛型

- 泛型实际上是对数据类型的一种约束形式。
- ==例如：ArrayList<Dog>==约束了 该集合中能存放的只能是Dog类的数据
- 默认的泛型就是Object类
- 当给泛型指定了具体类型后，只能传入该指定的类型，或者是该类型的子类。



## 10.2、泛型的使用

```java
public class Generic03 {
    public static void main(String[] args) {
        /*
        使用泛型的方式，给HashSet放入三个学生对象
         */
        HashSet<Student> students = new HashSet<Student>();
        students.add(new Student("Ross",18));
        students.add(new Student("Tom",20));
        students.add(new Student("Job",19));

        //遍历
        for (Student student :students) {
            System.out.println(student);
        }

        HashMap<String, Student> hashMap = new HashMap<>();
        hashMap.put("Tom",new Student("汤姆",20));
        hashMap.put("Ross",new Student("螺丝",18));
        hashMap.put("Job",new Student("脚脖",19));

        Set<Map.Entry<String, Student>> entries = hashMap.entrySet();
        Iterator<Map.Entry<String, Student>> iterator = entries.iterator();

        while (iterator.hasNext()) {
            Map.Entry<String, Student> next = iterator.next();
            System.out.println(next.getKey()+","+next.getValue());
        }
    }
}
```



## 10.3、自定义泛型

简单来说，就是我们自己写一个类或者是接口，然后再接口或类中给它定义了一些泛型。

### 自定义泛型类

> 基本语法

~~~java
class 类名 <T，E...>{
    成员
}
~~~



> 注意细节

1. 普通成员可以使用泛型（属性、方法）
2. 使用泛型的数组，不能初始化
3. 静态方法中不能使用类的泛型
4. 泛型类的类型，是在创建对象时确定的（因为创建对象时，需要指定确定类型）
5. 如果在创建对象时，没有指定类型，默认是Object类



> 使用案例

```java
public class CustomGeneric01 {
    public static void main(String[] args) {

    }
}
/*
    1、T、E、R: 都是泛型的标识符，一般都是单个的大写字母
    2、泛型标识符可以有多个
 */
class Tiger<T,R,E>{
    String name;
    T t;
    R r;
    E e;
    //T[] ts=new T[8];  //泛型参数不允许实例化，因为其类型不能确定，无法知道 在内存中 需要开辟多大空间
    T[] ts;   //不可以实例化，但是可以定义


    public Tiger(String name, T t, R r, E e) {  //构造器使用泛型
        this.name = name;
        this.t = t;
        this.r = r;
        this.e = e;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public T getT() {//方法使用泛型，其返回值类型也可以是泛型
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    /*
        静态是和类相关的，在类加载时，对象还没有创建。
        所以，如果静态方法和静态属性使用了泛型，JVM就无法完成初始化
        总结，静态属性和静态方法都不允许使用泛型
     */
//    static E e2;
//    public static void E1(E e){}
}
```



### 自定义泛型接口

> 基本语法

~~~java
interface 接口名 <T，E...>{
    
}
~~~



> 注意事项

1. 接口中，静态成员也不能使用泛型
2. 泛型接口的类型，在继承接口或者实现接口时确定
3. 没有指定类型的泛型，默认是Object类



> 使用案例

```java
public class CustomInterfaceGeneric01 {
    public static void main(String[] args) {

    }
}

interface IUsb<T, E> {
    //普通方法中，可以使用泛型（抽象方法）
    T get(E e);

    void hello(T t);

    void run(T t1, T t2, E e1, E e2);
    
    //默认方法，在jdk中，可以在接口中，使用默认方法
    default E method(T t){
        return null;
    }
}

/**
 * 在继承接口中，指定泛型接口的类型
 */
interface IA extends IUsb<String,Double>{
    
}

/**
 * 接口实现类中，没有指定泛型，则默认是Object类
 */
class AA implements IUsb{
    @Override
    public Object get(Object o) {
        return null;
    }

    @Override
    public void hello(Object o) {

    }

    @Override
    public void run(Object t1, Object t2, Object e1, Object e2) {

    }
}
class BB implements IUsb<String,Float>{
    @Override
    public String get(Float aFloat) {
        return null;
    }

    @Override
    public void hello(String s) {

    }

    @Override
    public void run(String t1, String t2, Float e1, Float e2) {

    }
}
```



### 自定义泛型方法

> 基本语法

~~~java
修饰符 <T,E...>返回值类型 方法名 (参数类型){}
~~~



> 注意细节

1. 泛型方法，可以定义在普通类中，也可以定义在泛型类中。
2. 当泛型方法被调用时，类型会确定。
3. `public void eat(E e){}`，修饰符后面没有用泛型`<T,E...>`。此时`eat()`方法不是泛型方法，只是使用了泛型。



> 使用案例

```java
public class CustomMethodGeneric01 {
    public static void main(String[] args) {
        C c = new C();
        c.fly("宝马", 100);//当调用方法时，传入参数，编译器会确定类型
        System.out.println("-------------------");
        c.fly(300, 100);//当调用方法时，传入参数，编译器会确定类型

        System.out.println("---------------------");
        //测试
        F<String, ArrayList> f = new F<>();
        f.hello(new ArrayList(),11.3f);
    }
}

/**
 * 普通类
 */
class C {
    public void run() {
    }  //这是一个普通方法

    public <T, E> void fly(T t, E e) {
        System.out.println(t.getClass());
        System.out.println(e.getClass());
    }  //这是泛型方法
}

/**
 * 泛型类
 *
 * @param <T>
 * @param <E>
 */
class F<T, E> {
    public void run() {
    }


    public <U, M> void eat(U u, M m) {
    }

    /*
    hi 是普通方法，使用了类声明的泛型
    hello是泛型方法，可以使用类声明的泛型，也可以使用自己声明的泛型
     */
    public void hi(T t) {
    }

    public <K> void hello(E e, K k) {
        System.out.println(e.getClass());
        System.out.println(k.getClass());
    }
}
```



# 11、反射

> 反射机制是框架的灵魂
>
> 反射可以在不修改源码，只改配置文件的情况下，改变程序的运行结果

## 11.1、为什么需要反射

1. 反射机制允许程序在执行期间，借助`Reflection（反射） API`取得任何类的内部信息。（例如：成员变量、构造器、成员方法等。）并能操作对象的属性及方法。反射在设计模式和框架底层都会用到。
2. 加载完类之后，在堆中就产生一个`Class类型`的对象（一个类只有一个Class对象）。这个对象包含了类的完整结构信息，通过这个对象得到类的结构。这个类就像一面镜子，透过这个镜子可以看到类的结构。就像镜面反射一样，可以叫反射。



## 11.2、反射原理图

![反射原理图](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/20250620173227164.png)



## 11.3、反射有什么用

- 在运行时判断任意一个对象所属的类
- 在运行时构造任意一个类的对象
- 在运行时得到任意一个类所具有的成员变量和方法
- 在运行时调用任意一个对象的成员变量和方法
- 生成动态代理



## 11.4、反射相关的类

- `java.lang.Class`：代表一个类，Class对象表示某个类加载后在堆中的对象。
- `java.lang.reflect.Method`：代表类的方法。
- `java.lang.reflect.Field`：代表类的成员变量。
- `java.lang.reflect.Constructor`：代表类的构造方法。



```java
public class Reflection01 {
    public static void main(String[] args) throws Exception {
        Properties properties = new Properties();
        properties.load(new FileInputStream("src\\re.properties"));
        String classFullPath = properties.get("classFullPath").toString(); //com.javase.reflection.Cat
        String methodName = properties.get("method").toString();

        //2、创建对象 ,使用反射机制
        //(1)、加载类，返回Class类型的对象 aClass
        Class<?> aClass = Class.forName(classFullPath);

        //(2)、通过 aClass 得到你加载的类 com.javase.reflection.Cat 的实例对象
        Object o = aClass.newInstance(); //o的运行类型: class com.javase.reflection.Cat

        //(3)、通过 aClass 得到你加载的类 com.javase.reflection.Cat 的 methodName 的方法对象
        //即在反射中，可以把方法视为对象（万物皆对象）
        Method method = aClass.getMethod(methodName);

        //(4)、通过 method 调用方法: 即通过方法对象来实现调用方法
        System.out.println("-------------------------");
        method.invoke(o);//传统方法 对象.方法() ，反射机制，方法.invoke(对象)

        //java.lang.reflect.Field : 代表类的成员变量。Field对象表示某个类的成员变量
        //得到 name 字段

        //getField() 方法不能得到私有的属性
//        Field nameField = aClass.getField("name");//java.lang.NoSuchFieldException: name
        Field age = aClass.getField("age");
        System.out.println(age.get(o));

        //java.lang.reflect.Constructor : 代表类的构造方法。
        Constructor constructor = aClass.getConstructor();//()中可以指定构造器参数类型，返回无参构造器
        System.out.println(constructor);//public com.javase.reflection.Cat()

        Constructor constructor1 = aClass.getConstructor(String.class);//这里传入的 String.class 就是String类的Class对象
        System.out.println(constructor1); //Cat(java.lang.String)
    }
}
```



## 11.5、反射调用优化

> 反射的优点

可以动态的创建和使用对象（也是框架底层核心），使用灵活。没有反射机制，框架技术就失去底层支撑。



> 反射的缺点

使用反射基本是解释执行，对执行速度有影响。



> 执行速度对比

```java
public class Reflection02 {
    public static void main(String[] args) throws Exception {
        m1();
        m2();
    }

    //传统方法调用方法
    public static void m1() {
        Cat cat = new Cat();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 999999999; i++) {
            cat.test();
        }
        long end = System.currentTimeMillis();
        System.out.println("m1方法调用test()的耗时: " + (end - start) + "ms");
    }

    //反射机制调用方法
    public static void m2() throws Exception {
        Class<?> aClass = Class.forName("com.javase.reflection.Cat");
        Object o = aClass.newInstance();
        Method hi = aClass.getMethod("test");
        long start = System.currentTimeMillis();
        for (int i = 0; i < 999999999; i++) {
            hi.invoke(o);
        }
        long end = System.currentTimeMillis();
        System.out.println("m2方法调用test()的耗时: " + (end - start) + "ms");
    }
}
```

![常规方法调用VS反射机制调用的时间](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/20250620173143622.png)



> 优化——关闭访问检查（暴力访问）

- **Method **和 **Field**、**Construction**对象都有`setAccessible()`方法。
- `setAccessible`作用是启动和禁用访问安全检查开关。
- 参数值为`true`表示反射的对象在使用时取消访问检查，提高反射的效率。
- 参数值为`false`表示反射的对象执行访问检查。



效果：聊胜于无

```java
public static void m3() throws Exception {
    Class<?> aClass = Class.forName("com.javase.reflection.Cat");
    Object o = aClass.newInstance();
    Method test = aClass.getMethod("test");
    //取消在反射调用方法时的访问检查，即暴力访问
    test.setAccessible(true);
    long start = System.currentTimeMillis();
    for (int i = 0; i < 999999999; i++) {
        test.invoke(o);
    }
    long end = System.currentTimeMillis();
    System.out.println("m2方法优化后，调用test()的耗时: " + (end - start) + "ms");
}
```



## 11.6、Class类

- Class也是一个类，也继承Object类
- Class类对象不是new出来的，而是系统创建的
- 对于某个类的Class类对象，在内存中只有一份，因为类只加载一次
- 每个类的实例都会记得自己是由哪一个Class实例所生成的
- 通过Class可以完整地得到一个类的完整结构
- Class对象是存放在堆中的
- 类的字节码二进制数据，是放在方法区的

![Class类图](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/20250620173205926.png)



![反射原理图02](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/20250620173227164.png)

```java
public class Class01 {
    public static void main(String[] args) throws Exception {
        // Class类对象不是new出来的，而是系统创建的，无论是传统方法还是利用反射机制
        /*  源码: 是由这个类完成创建的
            ClassLoader类
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                return loadClass(name, false);
            }
         */

        //Cat cat = new Cat();
        Class<?> cls1 = Class.forName("com.javase.reflection.Cat");

        //对于某个类的Class类对象，在内存中只有一份，因为类只加载一次
        //每个类的实例都会记得自己是由哪一个Class实例所生成的
        /*
            相同的类只会在内存中创建一个，同一个对象，不同的引用
         */
        Class<?> cls2 = Class.forName("com.javase.reflection.Cat");
        Class<?> cls3 = Class.forName("com.javase.reflection.Dog");

        System.out.println(cls1.hashCode()); //1554874502
        System.out.println(cls2.hashCode()); //1554874502
        System.out.println(cls3.hashCode()); //1846274136

        /*
            这里不同是因为这个两个不一样的的对象
         */
        Cat cat = new Cat();
        Cat cat1 = new Cat();
        System.out.println(cat.hashCode()); //1639705018
        System.out.println(cat1.hashCode()); //1627674070
    }
}
```



### Class类的常用方法

| 方法名                                                    | 功能说明                                                     |
| --------------------------------------------------------- | ------------------------------------------------------------ |
| static Class<?> forName(String className)                 | 返回指定类名 **name** 的 **Class** 对象                      |
| Object newInstance()                                      | 调用缺省构造函数，返回该 **Class** 对象的一个实例            |
| String getName()                                          | 返回此 **Class** 对象所表示的实体（类、接口、数组类、基本类型）名称 |
| Class getSuperclass()                                     | 返回当前 **Class** 对象的父类的 **Class** 对象               |
| Class[] getInterfaces()                                   | 获取当前 **Class** 对象的接口                                |
| ClassLoader getClassLoader()                              | 返回该类的类加载器                                           |
| Constructor<?>[] getConstructors()                        | 返回一个包含某些 **Constructor** 对象的数组                  |
| Field[] getDeclaredFields()                               | 返回 **Field** 对象的一个数组                                |
| Method getMethod(String name, Class<?>... parameterTypes) | 返回一个 **Method** 对象，此对象的形参类型为 **parameterTypes** |



```java
/**
 * 演示Class的一些常用方法
 */
public class Class02 {
    //通过Class可以完整地得到一个类的完整结构
    public static void main(String[] args) throws Exception {

        String classAllPath = "com.javase.reflection.Car";
        //获取Car类，对应的Class对象
        //<?>: 表示不确定的Java类型
        Class<?> cls = forName(classAllPath);

        System.out.println(cls);//输出会显示，cls是哪个类的Class对象     class com.javase.reflection.Car
        System.out.println(cls.getClass());//输出会显示，cls的运行类型   class java.lang.Class

        //获取包名
        System.out.println(cls.getPackage().getName());   //com.javase.reflection
        System.out.println(cls.getPackage());             //class com.javase.reflection.Car

        //得到 类的全路径
        System.out.println(cls.getName());      //com.javase.reflection.Car

        //通过cls创建对象实例
        Object car = cls.newInstance();  //等同于 Car car=(Car)cls.newInstance();
        System.out.println(car);

        //通过反射获取属性
        Field color = cls.getField("color");
        System.out.println(color.get(car));    //等同于传统写法的 car.getColor()
        Car car1 = new Car();
        System.out.println(car1.getColor());

        //通过反射给属性赋值
        color.set(car,"五彩斑斓");
        System.out.println(color.get(car));

        //遍历得到所有的属性
        Field[] fields=cls.getFields();
        for (Field f :fields) {
            System.out.println(f.getName());
        }
    }
}
```



### 获取Class对象的六种方式

```java
/**
 * 演示得到Class对象的方式
 */
public class GetClass01 {
    public static void main(String[] args) throws Exception {
        String classAllPath = "com.javase.reflection.Car";
        /*
            1、Class.forName("类的全路径");
            前提: 已知一个类的全路径。
            应用场景: 多用于配置文件，读取类全路径，加载类。
         */
        Class<?> cls1 = Class.forName(classAllPath);
        System.out.println(cls1);

        /*
            2、类名.class;
            前提: 若已知具体的类，通过类的class获取。该方式最为安全可靠，程序性能最高。
            应用场景: 多用于参数传递，比如通过反射得到对应的构造器对象。
         */
        Class<Car> cls2 = Car.class;
        System.out.println(cls2);

        /*
            3、对象.getClass();
            前提: 已知某个类的实例，调用该实例的 getClass()方法 获取 Class对象。
            应用场景: 通过创建好的对象，获取 Class对象.
         */
        Car car = new Car();
        Class<? extends Car> cls3 = car.getClass();
        System.out.println(cls3);

        /*
            4、
            前提: 通过类加载器来获取到类的 Class对象
         */
        //(1) 先得到类加载器 car
        ClassLoader classLoader = car.getClass().getClassLoader();
        //(2) 通过类加载器得到 Class对象
        Class<?> cls4 = classLoader.loadClass(classAllPath);
        System.out.println(cls4);

        //这四个都是同一个对象
        System.out.println(cls1.hashCode());
        System.out.println(cls2.hashCode());
        System.out.println(cls3.hashCode());
        System.out.println(cls4.hashCode());

        /*
            5、基本数据类型，按如下方法得到 Class对象
         */
        Class<Integer> integerClass = int.class;
        Class<Character> characterClass = char.class;
        Class<Boolean> booleanClass = boolean.class;
        System.out.println(integerClass);   //int
        System.out.println(characterClass); //char
        System.out.println(booleanClass);   //boolean

        /*
            6、基本数据类型对应的包装类 ，可以通过 包装类.TYPE 得到 Class对象。
         */
        Class<Integer> type = Integer.TYPE;
        Class<Character> type1 = Character.TYPE;
        System.out.println(type);
        System.out.println(type1);

        System.out.println(integerClass.hashCode());  //1846274136
        System.out.println(type.hashCode());          //1846274136
    }
}
```



### 哪些类型有Class对象

1. 外部类、内部类、
2. 接口
3. 枚举
4. 注解
5. 基本数据类型
6. void



```java
/**
 * 演示哪些类型有 Class对象
 */
public class AllTypeClass {
    public static void main(String[] args) {
        Class<String> cls1 = String.class;               //外部类
        Class<Service> cls2 = Service.class;             //接口
        Class<Integer[]> cls3 = Integer[].class;         //数组
        Class<float[][]> cls4 = float[][].class;         //二维数组
        Class<Override> cls5 = Override.class;           //注解
        Class<Thread.State> cls6 = Thread.State.class;   //枚举
        Class<Integer> cls7 = int.class;                 //基本数据类型
        Class<Integer> cls8 = Integer.class;             //包装类
        Class<Void> cls9 = void.class;                   //void
        Class<Class> cls10 = Class.class;                //Class

        System.out.println(cls1);
        System.out.println(cls2);
        System.out.println(cls3);
        System.out.println(cls4);
        System.out.println(cls5);
        System.out.println(cls6);
        System.out.println(cls7);
        System.out.println(cls8);
        System.out.println(cls9);
        System.out.println(cls10);
    }
}
```



## 11.7、类加载

> 类加载流程图

![类加载流程图](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/20250620173304339.png)



### 动态加载和静态加载

反射机制是 Java实现动态语言的关键，也就是通过反射实现类动态加载。

- 静态加载：编译时加载相关的类，如果没有则报错，依赖性太强。
- 动态加载：运行时加载需要的类，如果运行时不用该类，则不报错，降低了依赖性。



### 类加载的阶段

![类加载流程图02](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/20250620173312120.png)



> 加载阶段

JVM在改阶段的主要目的是将字节码从不同的数据源（可能是 class文件、也可能是jar包、甚至网络）转化为二进制字节流加载到内存中，并生成一个代表该类的 `Class对象`。



> 连接阶段 —— 验证

1. 目的是为了确保`class文件`的字节流中包含的信息符合当前虚拟机的要求，并且不会危害虚拟机自身的安全

2. 包括：文件格式验证（是否以魔数 `oxcafebabe`开头）、元数据验证、字节码验证和符号引用验证。

   ![1677325315785](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/20250620173345531.png)

3. 可以考虑使用 `-Xverify:none`参数来关闭大部分的验证措施，缩短虚拟机类加载的时间



> 连接阶段 —— 准备

- JVM会在该阶段对静态变量，分配内存并默认初始化（对应数据类型的默认初始化值，如：0、0L、null、false等）。这些变量所使用的内存都将在方法区中进行分配。



> 连接阶段 —— 解析

虚拟机将常量池内的符号引用替换为直接引用的过程

具体操作在JVM虚拟机中完成，大概就是将`逻辑地址`转化为`物理地址`。



> 初始化阶段

1. 到初始化阶段，才真正开始执行类中定义的Java程序代码，此阶段是执行`<clinit()>`方法的过程。
2. `<clinit()>`方法是由编译器按语句在源文件中出现的顺序，依次自动收集类中的所有`静态变量的赋值动作`和`静态代码块中`的语句，并进行合并。
3. 虚拟机会保证一个类`<clinit()>`方法在多线程环境中被正确的加锁、同步。如果多个线程同时去初始化一个类，那么只会有一个线程去执行这个类的`<clinit()>`方法，其他线程都需要阻塞等待，知道活动线程执行`<clinit()>`方法完毕。



~~~java
//对应2
public class ClassLoad03 {
    public static void main(String[] args) {
        //1、加载 B类，并生成 B的 class对象
        //2、连接 num = 0
        /*
            3、初始化阶段
            依次自动收集类中的所有`静态变量的赋值动作`和`静态代码块中`的语句, 并合并

            依次收集!!!!!!!!!
            clinit(){
                System.out.println("B的静态代码块被执行！");
                num=300;
                num=100;
            }
            合并: num = 100
         */
        //new B(); //类加载
        System.out.println(B.num);  //100，如果直接使用类的静态属性，也会导致类的加载
    }
}

class B{
    static {
        System.out.println("B的静态代码块被执行！");
        num=300;
    }

    static int num=100;

    public B(){
        System.out.println("B的构造器被执行！");
    }
}
~~~



## 11.8、通过反射获取类的结构

### java.lang.Class类

> 常用API

- getName: 获取全类名
- getSimpleName: 获取简单的类名
- getFields: 获取所有的 public 修饰的属性，包含本类以及父类的
- getDeclaredFields: 获取本类中所有的属性
- getMethods: 获取所有的 public 修饰符的方法，包含本类以及父类
- getDeclaredMethods: 获取本类中所有的方法
- getConstructors: 获取所有的 public 修饰的构造器，包含本类以及父类
- getDeclaredConstructors: 获取本类中的所有构造器
- getPackage: 以Package形式返回 包信息
- getSuperclass: 以 Class形式返回父类信息
- getInterfaces: 以 Class[] 形式返回接口信息
- getAnnotations: 以 Annotations[] 形式返回注解信息

```java
/**
 * 演示通过反射获取类结构信息
 */
public class Reflection {
    public static void main(String[] args) {

    }

    @Test
    public void api_01() throws Exception {
        //得到 Class对象
        Class<?> personCls = Class.forName("com.javase.reflection.Person");

        //getName: 获取全类名
        System.out.println(personCls.getName()); //com.javase.reflection.Person

        //getSimpleName: 获取简单的类名
        System.out.println(personCls.getSimpleName()); //Person

        //getFields: 获取所有的 public 修饰的属性，包含本类以及父类的
        Field[] fields = personCls.getFields();
        for (Field field : fields) {
            System.out.println("本类以及父类的属性: " + field.getName());
        }

        //getDeclaredFields: 获取本类中所有的属性
        Field[] fields1 = personCls.getDeclaredFields();
        for (Field field : fields1) {
            System.out.println("本类的属性: " + field.getName());
        }

        //getMethods: 获取所有的 public 修饰符的方法，包含本类以及父类
        Method[] methods = personCls.getMethods();
        for (Method method : methods) {
            System.out.println("本类以及父类的方法: " + method.getName());
        }

        //getDeclaredMethods: 获取本类中所有的方法
        Method[] declaredMethods = personCls.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            System.out.println("本类中所有的方法: "+declaredMethod.getName());
        }

        //getConstructors: 获取本类所有的 public 修饰的构造器
        Constructor<?>[] constructors = personCls.getConstructors();
        for (Constructor<?> constructor : constructors) {
            System.out.println("本类以及父类的构造器: "+constructor.getName());
        }

        //getDeclaredConstructors: 获取本类中的所有构造器
        Constructor<?>[] declaredConstructors = personCls.getDeclaredConstructors();
        for (Constructor<?> declaredConstructor : declaredConstructors) {
            System.out.println("本类中的所有构造器: "+declaredConstructor.getName());
        }

        //getPackage: 以Package形式返回 包信息
        System.out.println(personCls.getPackage());

        //getSuperclass: 以 Class形式返回父类信息
        System.out.println(personCls.getSuperclass());

        //getInterfaces: 以 Class[] 形式返回接口信息
        Class<?>[] interfaces = personCls.getInterfaces();
        for (Class<?> anInterface : interfaces) {
            System.out.println("接口: "+anInterface.getName());
        }

        //getAnnotations: 以 Annotations[] 形式返回注解信息
        Annotation[] annotations = personCls.getAnnotations();
        for (Annotation annotation : annotations) {
            System.out.println("注解信息: "+annotation);
        }
    }
}

interface IA{}
interface IB{}

class A {
    public String hobby;

    public A() {
    }

    public A(String hobby) {
        this.hobby = hobby;
    }

    public void hi() {
    }
}
@Deprecated
class Person extends A implements IA,IB{
    public String name;
    protected int age;
    String job;
    private double sal;

    public Person(){}

    public Person(String name) {
        this.name = name;
    }

    private Person(String name,int age){

    }

    public void m1() {
    }

    protected void m2() {
    }

    void m3() {
    }

    private void m4() {
    }
}
```



### java.lang.reflect.Field类

- getModifiers：以`int`形式返回修饰符，如果是`public static final....`，它的值是1+8+16=25。
  - 默认修饰符：0
  - public：1
  - private：2
  - protected：4
  - static：8
  - final：16
- getType：以Class形式返回类型
- getName：返回属性名

```java
/**
 *  java.lang.reflect.Field类的API
 * @throws Exception
 */
@Test
public void api_02() throws Exception {
    Class<?> personCls = Class.forName("com.javase.reflection.Person");
    //getDeclaredFields: 获取本类中所有的属性
    Field[] fields1 = personCls.getDeclaredFields();
    for (Field field : fields1) {
        System.out.println("本类的属性: " + field.getName()+"；"
        +"该属性的修饰符值: "+field.getModifiers()+"；"
        +"该属性的类型: "+field.getType());
    }
}
```



### java.lang.reflect.Method类

- getModifiers：同上一样
- getReturnType：以 Class形式获取返回类型
- getName
- getParameterTypes：以`Class[]`返回参数类型数组

```java
/**
 * java.lang.reflect.Method类的API
 * @throws Exception
 */
@Test
public void api_03() throws Exception {
    Class<?> personCls = Class.forName("com.javase.reflection.Person");
    //getDeclaredMethods: 获取本类中所有的方法
    Method[] declaredMethods = personCls.getDeclaredMethods();
    for (Method declaredMethod : declaredMethods) {
        System.out.println("本类中所有的方法: "+declaredMethod.getName()
        +" 该方法的访问修饰符值: "+declaredMethod.getModifiers()
        +" 该方法返回类型: "+declaredMethod.getReturnType());

        Class<?>[] parameterTypes = declaredMethod.getParameterTypes();
        for (Class<?> parameterType : parameterTypes) {
            System.out.println("该方法的形参数组: "+parameterType.getName());
        }
    }
```



### java.lang.reflect.Constructor类

- getModifiers：同上一样
- getName
- getParameterTypes：以`Class[]`返回参数类型数组

```java
/**
 * java.lang.reflect.Constructor类的API
 * @throws Exception
 */
@Test
public void api_04() throws Exception {
    Class<?> personCls = Class.forName("com.javase.reflection.Person");
    //getDeclaredConstructors: 获取本类中的所有构造器
    Constructor<?>[] declaredConstructors = personCls.getDeclaredConstructors();
    for (Constructor<?> declaredConstructor : declaredConstructors) {
        System.out.println("本类中的所有构造器: "+declaredConstructor.getName());

        Class<?>[] parameterTypes = declaredConstructor.getParameterTypes();
        for (Class<?> parameterType : parameterTypes) {
            System.out.println("该构造器的形参: "+paramet      }
    }
}
```
