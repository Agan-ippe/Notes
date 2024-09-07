# 1、了解Spring

## 1.1、Spring Framework的五大功能

| 功能模块                | 功能介绍                                                    |
| ----------------------- | ----------------------------------------------------------- |
| Core Container          | 核心容器，在 Spring 环境下使用任何功能都必须基于 IOC 容器。 |
| AOP&Aspects             | 面向切面编程                                                |
| Testing                 | 提供了对 junit 或 TestNG 测试框架的整合。                   |
| Data Access/Integration | 提供了对数据访问/集成的功能。                               |
| Spring MVC              | 提供了面向Web应用程序的集成功能。                           |



## 1.2、Spring Framework的特性

- 非侵入式：使用 Spring Framework 开发应用程序时，Spring 对应用程序本身的结构影响非常
  小。对领域模型可以做到零污染；对功能性组件也只需要使用几个简单的注解进行标记，完全不会
  破坏原有结构，反而能将组件结构进一步简化。这就使得基于 Spring Framework 开发应用程序
  时结构清晰、简洁优雅。
- 控制反转：IOC——Inversion of Control，翻转资源获取方向。把自己创建资源、向环境索取资源
  变成环境将资源准备好，我们享受资源注入。
- 面向切面编程：AOP——Aspect Oriented Programming，在不修改源代码的基础上增强代码功
  能。
- 容器：Spring IOC 是一个容器，因为它包含并且管理组件对象的生命周期。组件享受到了容器化
  的管理，替程序员屏蔽了组件创建过程中的大量细节，极大的降低了使用门槛，大幅度提高了开发
  效率。
- 组件化：Spring 实现了使用简单的组件配置组合成一个复杂的应用。在 Spring 中可以使用 XML
  和 Java 注解组合这些对象。这使得我们可以基于一个个功能明确、边界清晰的组件有条不紊的搭
  建超大型复杂应用系统。
- 声明式：很多以前需要编写代码才能实现的功能，现在只需要声明需求即可由框架代为实现。
- 一站式：在 IOC 和 AOP 的基础上可以整合各种企业应用的开源框架和优秀的第三方类库。而且
  Spring 旗下的项目已经覆盖了广泛领域，很多方面的功能性需求可以在 Spring Framework 的基
  础上全部使用 Spring 来实现。



# 2、IOC容器

## 2.1、IOC思想

> IOC：反转控制（Inversion of Control）

*什么是IOC思想？*

它是一个过程，对象仅通过构造参数、工厂方法的参数或在对象实例被构造或从工厂方法返回后在其上设置的属性来定义其依赖关系（即它们与之合作的其他对象）。然后容器在创建 bean 时注入这些依赖关系。这个过程从根本上说是Bean本身通过使用直接构建类或诸如服务定位模式的机制来控制其依赖关系的实例化或位置的逆过程（因此被称为控制反转）。

> 举个例子：

你饿了，要吃饭，但是老婆不愿意做饭。

正常来说，你完成煮饭，洗菜，炒菜，装饭等步骤，才能吃饭。

但是呢，你不想做了，于是你一个电话打到丈母娘那去，说你媳妇怎么怎么样。

然后丈母娘一个电话打给你媳妇，狠狠地一顿训。

然后你就得到了一顿饭。



总的来说就是：平常需要你自己去调用的资源，方法。现在有东西帮你完成了。你只需要完成吃，这个最终目的就行了。



## 2.2、DI

> DI：依赖注入（Dependency Injection）

DI 是 IOC 的另一种表述方式：即组件以一些预先定义好的方式（例如：setter 方法）接受来自于容器的资源注入。相对于IOC而言，这种表述更直接。

所以结论是：IOC 就是一种反转控制的思想， 而 DI 是对 IOC 的一种具体实现。



## 2.3、IOC容器在Spring中的实现

> `org.springframework.beans` 和 `org.springframework.context` 包是Spring Framework的IoC容器的基础。
>
> `BeanFactory` 接口提供了一种高级配置机制，能够管理任何类型的对象。
>
>  `ApplicationContext`是 `BeanFactory` 的一个子接口。

Spring 的 IOC 容器就是 IOC 思想的一个落地的产品实现。IOC 容器中管理的组件也叫做 `bean`。在创建`bean`之前，首先需要创建 IOC 容器。Spring 提供了 IOC 容器的两种实现方式：

- *BeanFactory*：这是 IOC 容器的基本实现，是 Spring 内部使用的接口。面向 Spring 本身，不提供给开发人员使用。
- *ApplicationContext*：BeanFactory 的子接口，提供了更多高级特性。面向 Spring 的使用者，几乎所有场合都使用ApplicationContext 而不是底层的 BeanFactory。

在Spring中，构成你的应用程序的骨干并由Spring IoC容器管理的对象被称为Bean。Bean是一个由Spring IoC容器实例化、组装和管理的对象。否则，Bean只是你的应用程序中众多对象中的一个。Bean以及它们之间的依赖关系都反映在容器使用的配置元数据中。



### 2.3.1、ApplicationContext

`ApplicationContext` 接口代表Spring IoC容器，负责实例化、配置和组装bean。容器通过读取配置元数据来获得关于要实例化、配置和组装哪些对象的指示。配置元数据以XML、Java注解或Java代码表示。它可以让你表达构成你的应用程序的对象以及这些对象之间丰富的相互依赖关系。



> ApplicationContext的实现类

| 类名                              | 功能                                                         |
| --------------------------------- | ------------------------------------------------------------ |
| *ClassPathXmlApplicationContext*  | 通过读取类路径下的 XML 格式的配置文件创建 IOC 容器对象       |
| *FileSystemXmlApplicationContext* | 通过文件系统路径读取 XML 格式的配置文件创建 IOC 容器对象     |
| *ConfigurableApplicationContext*  | ApplicationContext 的子接口，包含一些扩展方法refresh() 和 close() ，让 ApplicationContext 具有启动、关闭和刷新上下文的能力。 |
| *WebApplicationContext*           | 专门为 Web 应用准备，基于 Web 环境创建 IOC 容器对象，并将对象引入存入 ServletContext 域中。 |



### 2.3.2、基于xml管理bean

> 以下是官方文档中的说明
>
> 在官方文档中称之为元数据
>
> 配置元数据传统上是以简单直观的XML格式提供的，这也是本章大部分内容用来传达Spring IoC容器的关键概念和特性。
>
> 基于XML的元数据并不是配置元数据的唯一允许形式。Spring IoC容器本身与这种配置元数据的实际编写格式是完全解耦的。如今，许多开发者为他们的Spring应用程序选择 基于Java的配置（即使用注解，面向注解编程多方便啊~）。
>
> Spring的配置包括至少一个，通常是一个以上的Bean定义，容器必须管理这些定义。基于XML的配置元数据将这些Bean配置为顶层 `<beans/>` 元素内的 `<bean/>` 元素。Java配置通常使用 `@Configuration` 类中的 `@Bean` 注解的方法。

即在`applicationContext.xml`配置文件中，加入需要管理的实现类（一般为`***Controller`）。



下面的例子显示了基于XML的配置*bean*的基本结构。

- `id` 属性是一个字符串，用于识别单个Bean定义。
- `class` 属性定义了 Bean 的类型，并使用类的全路径名。
- `id` 属性的值可以用来指代协作对象。

~~~xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="..." class="...">  
        <!-- 这个bean的合作者和配置在这里 -->
    </bean>

    <bean id="..." class="...">
        <!-- c这个bean的合作者和配置在这里 -->
    </bean>

    <!-- 更多bean 定义在这里 -->

</beans>
~~~

![xml管理bean](C:\Users\93988\Desktop\studynotes\学习笔记\SSM\Spring\Imgs\xml管理bean.png)

Spring 底层默认通过反射技术调用组件类的无参构造器来创建组件对象。



### 2.3.3、Bean的命名规则

惯例是在命名Bean时使用标准的Java惯例来命名实例字段名。也就是说，Bean的名字以小写字母开始，然后以驼峰字母开头。

统一命名Bean使你的配置更容易阅读和理解。另外，如果你使用Spring AOP，在对一组按名称相关的Bean应用 advice 时，也有很大的帮助。



### 2.3.4、获取bean

> 方式一：通过配置文件，直接获取bean的id

```Java
@Test
public void testIOCByXml(){
    ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
    Student student = (Student) context.getBean("studentOne");
    System.out.println(student);
}
```



> 方式二：根据类型获取。根据类型获取时，要求IOC容器中指定类型的bean有且只能有一个。

```Java
@Test
    public void testIOCByXml(){
        ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
        Student student = context.getBean(Student.class);
        System.out.println(student);
    }
```



> 方式三：类型 + id 获取

```Java
@Test
    public void testIOCByXml(){
        ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
        Student student = context.getBean("studentOne",Student.class);
        System.out.println(student);
    }
```



==拓展==

> 如果对象实现了接口，根据接口类型可以获取 bean 吗？

可以，前提是bean唯一。即只有一个对象实现这个接口。

```Java
@Test
    public void testIOCByXml(){
        ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
        Person bean = context.getBean(Person.class);
        System.out.println(bean);
    }
```



```Java
public class Student implements Person{}
```



> 如果多个对象实现了这个接口，这些对象都配置了 bean，根据接口类型可以获取 bean 吗？

不行，此时bean不唯一了，Spring不知道需要找哪一个对象。



## 2.4、依赖注入

### 2.4.1、setter注入

所谓 *setter* 注入就是通过对象的setter方法，给对象赋值。

前提是你的对象要有对应的setter方法。

- *name*：属性名
- *value*：值

```xml
<bean id="studentTwo" class="com.aip.pojo.Student">
    <property name="sid" value="0444012"/>
    <property name="name" value="Aip"/>
    <property name="age" value="21"/>
    <property name="gender" value="男"/>
</bean>
```



### 2.4.2、构造方法注入

通过有参构造来给对象赋值。这种方式不需要setter方法。只需要你有对应的构造器即可。

- *index*：指定参数所在位置的索引（从0开始）
- *name*：指定参数名
- *value*：值

```xml
<bean id="studentTwo" class="com.aip.pojo.Student">
        <constructor-arg value="0112"/>
        <constructor-arg value="Aip"/>
        <constructor-arg value="22" name="age"/>
        <constructor-arg value="男"/>
    </bean>
```



### 2.4.5、特殊值注入

以上常见的，我们统称为字面量赋值。以下，介绍以下特殊值的注入。

> null

```xml
<bean id="studentThree" class="com.aip.pojo.Student">
    <property name="gender"> <null/> </property>
</bean>
```

注意，不可以用以下方法。否则将赋值为字符串的*null*。

```xml
<property name="gender" value="null"/>
```



> xml实体

什么是 XML 实体？简单来说，实体是一种表示特殊字符的方式。 实体也称为实体引用。

为什么需要 XML 实体？ 某些字符（例如"、& < "等）在 XML 中是保留的。 它们被称为特殊字符，不能直接用于其他目的。

例如，用于标记的 < 和 > 符号。 您不能直接从键盘键入小于和大于符号。 相反，您需要使用实体。

| Character | Usage    |
| --------- | -------- |
| "         | `&quot;` |
| &         | `&amp;`  |
| '         | `&apos;` |
| <         | `&lt;`   |
| >         | `&gt;`   |



> CDATA节

XML解析器看到CDATA节就知道这里是纯文本，就不会当作XML标签或属性来解析。所以CDATA节中写什么符号都随意。

- 启动节：<![CDATA[
- 结束节：]]>

```xml
<property name="name">
    <value><![CDATA[a < b]]></value>
</property>
```



### 2.4.6、为数组/集合类型的属性赋值

![1686982599910](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/1686982599910.png)

- *value*：值
- *array*：数组
- *bean*：bean对象
- *ref*：引用
- *list/map*：集合
- *null*：空

> 数组

```xml
<bean id="test" class="com.aip.pojo.Student">
    <property name="name">
        <array>
            <value>Aip</value>
            <value>Ray</value>
            <value>Zack</value>
            <value>Zero</value>
        </array>
    </property>
</bean>
```



> List

```xml
<bean id="listDemo" class="com.aip.pojo.BanJi">
    <property name="bid" value="1901"/>
    <property name="name" value="物网"/>
    <property name="students">
        <list>
            <ref bean="studentOne"/>
            <ref bean="studentTwo"/>
            <ref bean="studentThree"/>
        </list>
    </property>
</bean>
```



使用 *util* 约束

```xml
<util:list id="stuList">
    <ref bean="studentOne"/>
    <ref bean="studentTwo"/>
    <ref bean="studentThree"/>
</util:list>
<bean id="listDemo02" class="com.aip.pojo.BanJi">
    <property name="bid" value="1901"/>
    <property name="name" value="物网"/>
    <property name="students" ref="stuList"/>
</bean>
```

> Map

```xml
<bean id="teacherOne" class="com.aip.pojo.Teacher">
    <property name="tid" value="1"/>
    <property name="name" value="***"/>
</bean>
<bean id="teacherTwo" class="com.aip.pojo.Teacher">
    <property name="tid" value="2"/>
    <property name="name" value="***"/>
</bean>
<bean id="testMap" class="com.aip.pojo.BanJi">
    <property name="bid" value="1901"/>
    <property name="name" value="物网"/>
    <property name="teacherMap">
        <map>
            <entry key="安卓开发" value-ref="teacherOne"/>
            <entry key="C#开发" value-ref="teacherTwo"/>
        </map>
    </property>
</bean>
```



Map也可以使用 *util* 约束

```xml
<util:map id="teacherMap">
        <entry key="Android" value-ref="teacherOne"/>
        <entry key="C#" value-ref="teacherTwo"/>
    </util:map>

    <bean id="testMap" class="com.aip.pojo.BanJi">
        <property name="bid" value="1901"/>
        <property name="name" value="物网"/>
        <property name="teacherMap" ref="teacherMap"/>
    </bean>
```



### 2.4.7、为类/接口类型的属性赋值

>方式一：引用外部bean

```xml
<bean id="studentAndClass" class="com.aip.pojo.Student">
    <property name="sid" value="0112"/>
    <property name="name" value="Aip"/>
    <property name="age" value="22"/>
    <property name="gender" value="男"/>
    <property name="banJi" ref="banJi"/>
</bean>

<bean id="banJi" class="com.aip.pojo.BanJi">
    <property name="bid" value="1901"/>
    <property name="name" value="物联网应用技术"/>
</bean>
```



> 方式二：嵌套bean

嵌套bean只能用于给属性赋值，不能在外部通过IOC容器获取，因此可以省略id属性

```xml
<bean id="qianTaoBean" class="com.aip.pojo.Student">
    <property name="sid" value="0112"/>
    <property name="name" value="Aip"/>
    <property name="age" value="22"/>
    <property name="gender" value="男"/>
    <property name="banJi">
        <bean id="banJiBean" class="com.aip.pojo.BanJi">
            <property name="bid" value="1901"/>
            <property name="name" value="物联网应用技术"/>
        </bean>
    </property>
</bean>
```



> 方式三：级联属性赋值

```xml
<bean id="banJiOne" class="com.aip.pojo.BanJi">
</bean>
<bean id="jiLian" class="com.aip.pojo.Student">
    <property name="sid" value="0112"/>
    <property name="name" value="Aip"/>
    <property name="age" value="21"/>
    <property name="gender" value="男"/>
    <!-- 一定先引用某个bean为属性赋值，才可以使用级联方式更新属性 -->
    <property name="banJi" ref="banJiOne"/>
    <property name="banJi.bid" value="1901"/>
    <property name="banJi.name" value="物网"/>
</bean>
```



### 2.4.8、p命名空间

引入p命名空间后，可以通过以下方式为bean的各个属性赋值

```xml
<bean id="testP" class="com.aip.pojo.Student"
      p:sid="4440112" p:name="Aip" p:age="22" p:teacherMap-ref="teacherMap"/>
```



### 2.4.9、引入外部（资源）文件

以`jdbc.properties`示例

```xml
<context:property-placeholder location="jdbc.properties"/>
<bean id="propData" class="com.alibaba.druid.pool.DruidDataSource">
    <property name="driverClassName" value="${jdbc.driver}"/>
    <property name="url" value="${jdbc.url}"/>
    <property name="username" value="${jdbc.user}"/>
    <property name="password" value="${jdbc.password}"/>
</bean>
```



## 2.5、bean的作用域

> 当你创建一个Bean定义时，你创建了一个“配方”，用于创建该Bean定义（definition）是所定义的类的实际实例。Bean定义（definition）是一个“配方”的想法很重要，因为它意味着，就像一个类一样，你可以从一个“配方”中创建许多对象实例。
>
> 你不仅可以控制各种依赖和配置值，将其插入到从特定Bean定义创建的对象中，还可以控制从特定Bean定义创建的对象的scope。这种方法是强大而灵活的，因为你可以通过配置来选择你所创建的对象的scope，而不是在Java类级别上烘托出一个对象的scope。Bean可以被定义为部署在若干scope中的一个。Spring框架支持六个scope，其中四个只有在你使用Web感知（aware）的 `ApplicationContext` 时才可用。你也可以创建 一个自定义 scope



在Spring中可以通过配置bean标签的scope属性来指定bean的作用域范围，各取值含义参加下表

| 作用域              | 含义                   | 创建对象的时机  |
| ------------------- | ---------------------- | --------------- |
| *singleton*（默认） | 单例模式，单个实例对象 | IOC容器初始化时 |
| *prototype*         | 多例模式，多个实例对象 | 获取bean时      |



> 配合测试解释

为多例模式时，创建出来的每一个对象都不是同一个。即使其内容是相同的。

```xml
<bean id="student" class="com.aip.pojo.Student" scope="prototype">
    <property name="sid" value="4440112"/>
    <property name="name" value="aip"/>
</bean>
```

```java
@Test
public void testDemo01(){
    ApplicationContext context = new ClassPathXmlApplicationContext("scope.xml");
    Student bean1 = context.getBean(Student.class);
    Student bean2 = context.getBean(Student.class);
    System.out.println(bean1 == bean2);
}
```

![1687085263537](C:\Users\93988\Desktop\studynotes\学习笔记\SSM\Spring\Imgs\1687085263537.png)



为单例模式时，内容相同的话，会指向同一个对象。（姑且可以这么理解，具体需要看源码）

```xml
<bean id="student" class="com.aip.pojo.Student" scope="singleton">
    <property name="sid" value="4440112"/>
    <property name="name" value="aip"/>
</bean>
```

![1687085498128](C:\Users\93988\Desktop\studynotes\学习笔记\SSM\Spring\Imgs\1687085498128.png)



是在WebApplicationContext环境下还会有另外两个作用域（但不常用）

| 作用域  | 含义             |
| ------- | ---------------- |
| request | 一个请求范围有效 |
| session | 一个会话范围有效 |



## 2.6、bean的生命周期

- bean对象创建（调用无参构造器）
- 给bean对象设置属性
- bean对象初始化之前操作（由bean的后置处理器负责）
- bean对象初始化（需在配置bean时指定初始化方法）
- bean对象初始化之后操作（由bean的后置处理器负责）
- bean对象就绪可以使用
- bean对象销毁（需在配置bean时指定销毁方法）
- IOC容器关闭

单例模式时，生命周期的前三个步骤会在获取IOC容器时执行。

多例模式时，生命周期的前三个步骤会在获取bean对象时执行。



> 与其他scope相比，Spring并不管理 prototype Bean的完整生命周期。

这句官方文档中的原话，我是这么理解的。

在多例模式下，每次通过bean来获取的对象，都是一个新对象。所以没必要提前创建。

在以下测试中也可以看出*prototype bean* 的销毁操作是没法完成的。

```java
//测试代码
@Test
    public void testDemo01(){
        ConfigurableApplicationContext context =new ClassPathXmlApplicationContext("lifecycle.xml");
//        User bean = context.getBean(User.class);
//        System.out.println(bean);
        context.close();
    }
```

```xml
单例模式
<bean id="user" class="com.aip.pojo.User" scope="singleton" init-method="initMethod"
      destroy-method="destroyMethod">
    <property name="id" value="4440112"/>
    <property name="username" value="Aip"/>
    <property name="password" value="20190"/>
    <property name="age" value="22"/>
</bean>
```

![1687160168292](C:\Users\93988\Desktop\studynotes\学习笔记\SSM\Spring\Imgs\1687159984119.png)

```java
@Test
    public void testDemo01(){
        ConfigurableApplicationContext context =new ClassPathXmlApplicationContext("lifecycle.xml");
        User bean = context.getBean(User.class);
//        System.out.println(bean);
        context.close();
    }
```

```xml
多例模式
<bean id="user" class="com.aip.pojo.User" scope="prototype" init-method="initMethod"
      destroy-method="destroyMethod">
    <property name="id" value="4440112"/>
    <property name="username" value="Aip"/>
    <property name="password" value="20190"/>
    <property name="age" value="22"/>
</bean>
```

![1687160120720](C:\Users\93988\Desktop\studynotes\学习笔记\SSM\Spring\Imgs\1687160041384.png)



> 为了让Spring容器释放由 prototype scopeBean 持有的资源，可以尝试使用自定义 [Bean后处理器](https://springdoc.cn/spring/core.html#beans-factory-extension-bpp)，它持有对需要清理的Bean的引用。



## 2.7、bean的后置处理器

> 在内部，Spring框架使用 `BeanPostProcessor` 实现来处理它能找到的任何回调接口并调用相应的方法。如果你需要自定义功能或其他Spring默认不提供的生命周期行为，你可以自己实现一个 `BeanPostProcessor`。欲了解更多信息，请参见 [容器扩展点](https://springdoc.cn/spring/core.html#beans-factory-extension)。

bean的后置处理器会在生命周期的初始化前后添加额外的操作，需要实现BeanPostProcessor接口，且配置到IOC容器中，需要注意的是，bean后置处理器不是单独针对某一个bean生效，而是针对IOC容器中所有bean都会执行。

```Java
public class MyBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("☆☆☆" + beanName + " = " + bean);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("★★★" + beanName + " = " + bean);
        return bean;
    }
}
```



## 2.8、FactoryBean

> 参考：https://blog.csdn.net/yy_diego/article/details/115710104
>
> 官方文档：[核心技术 (springdoc.cn)](https://springdoc.cn/spring/core.html#beans-factory-extension-factorybean)

### 2.8.1、*BeanFactory* 和 *FactoryBean* 有什么区别？

结论：

- *BeanFactory*： 是 IOC 容器，并且提供方法支持外部程序对这些 bean 的访问，0数产生各种类型的 bean，并添加到 IOC容器（实现 BeanFactory 接口的类) 的 singletonObject 属性中。
- *FactoryBean*： 首先是个 bean，也存放在 BeanFactory 中。它具有工厂方法的功能，在程序运行中 产生指定(一种)类型的 bean，并添加到了 IOC容器中的 factoryBeanObjectCache 属性中。

所以，这两种方式创建的 bean 都是被 spring 容器管理的。

> BeanFactory

*beanFactory* 是 *IOC容器* 的核心接口，它定义了 Spring 管理 bean 的通用方法，比如：getbean() 、containsBean() 。Spring 容器都是他的一种实现，比如：

- DefaultListableBeanFactory。
- XmlBeanFactory
- ApplicationContext

> 使用场景

- 通过 名字或类型从容器中获取 bean。
- 判断容器中是否包含指定的 bean。
- 判断 bean 是不是单例。

> 源码

```java
package org.springframework.beans.factory;
import org.springframework.beans.BeansException;
import org.springframework.core.ResolvableType;
import org.springframework.lang.Nullable;

public interface BeanFactory {
	
    // factoryBean 的转义标识符。（具体用法后面有介绍）
	String FACTORY_BEAN_PREFIX = "&";
	
    // 根据 name 从容器中拿对应的 bean。
	Object getBean(String name) throws BeansException;
	
    // 根据 name 和 type 从容器中拿对应的 bean，要对 bean 的类型做校验。
	<T> T getBean(String name, Class<T> requiredType) throws BeansException;
	
    // 在容器中能否找到与 name 匹配的 bean 或者 beanDefinition。
	boolean containsBean(String name);

	// 判断 name 对对应的 bean 是不是 单例。
	boolean isSingleton(String name) throws NoSuchBeanDefinitionException;
	
	boolean isPrototype(String name) throws NoSuchBeanDefinitionException;
	// 判断 name 对应的 bean 与指定的类型是否匹配。
	boolean isTypeMatch(String name, ResolvableType typeToMatch) throws NoSuchBeanDefinitionException;

	boolean isTypeMatch(String name, @Nullable Class<?> typeToMatch) throws NoSuchBeanDefinitionException;

	//根据 name 获取对应的 bean 的类型。
	@Nullable
	Class<?> getType(String name) throws NoSuchBeanDefinitionException;

    // 根据 name 获取对应 bean 的 别名。
	String[] getAliases(String name);
}
```


> FactoryBean

首先 `FactoryBean` 是一个 bean，但它又不仅仅是个 bean。它是一个可以 **创建** 或 **修饰** 其他对象的”工厂 bean“，这跟设计模式中的工厂模式或者装饰设模式很相似，它可以创建除自身以外的其他对象。

将来整合Mybatis时，Spring就是通过FactoryBean机制来帮我们创建SqlSessionFactory对象的。





> 源码

```Java
public interface FactoryBean<T> {
	
    String OBJECT_TYPE_ATTRIBUTE = "factoryBeanObjectType";
    
	//从 factory 中获取 bean。
	@Nullable
	T getObject() throws Exception;

	// 从 beanFactory 中获取类型。
	@Nullable
	Class<?> getObjectType();

	//是单例？
	default boolean isSingleton() {
		return true;
	} 
}
```

从上面的接口可以看出，FactoryBean 有工厂的味道。也就是说，如果一个 A类实现了 FactoryBean 接口，那么 类A 就变成了 A工厂。根据 A 的名称获得的实际上是工厂调用 getObject() 返回的对象, 而不是 A工厂自己。如果你想获得 A工厂自己的实例，你需要添加 & 前缀。



> 使用场景

Spring 中 FactoryBean 最大的应用场景是用在 AOP 中。我们都知道，AOP 实际上是 Spring 在运行是创建出来的代理对象，这个对象是在运行时才被创建的，而不是在启动时定义的，这与工厂方法模式是一致的。更生动地说，AOP 代理对象通过 java 反射机制在运行时创建代理对象，并根据业务需求将相应的方法编织到代理对象的目标方法中。Spring 中的 ProxyFactoryBean 就是干这事的。

因此，FactoryBean 提供了更灵活的实例化 bean 的方法。通过 FactoryBean 我们可以创建更复杂的 bean。



## 2.9、自动装配

> 什么是自动装配

根据指定的策略，在IOC容器中匹配某一个bean，自动为指定的bean中所依赖的类类型或接口类型属性赋值。



### 2.9.1基于xml的自动装配

> 举例

这种情况就是手动装配，通过属性值，来确定控制层、服务层、持久层之间的关系。

```xml
<bean id="userController" class="com.aip.controller.UserController">
    <property name="userService" ref="userService"/>
</bean>

<bean id="userService" class="com.aip.service.impl.UserServiceImpl">
    <property name="userDao" ref="userDao"/>
</bean>

<bean id="userDao" class="com.aip.dao.impl.UserDaoImpl"></bean>
```

以下是自动装配

使用bean标签的autowire属性设置自动装配效果

- byType：根据类型匹配IOC容器中的某个兼容类型的bean，为属性自动赋值。
  - 若在IOC中，没有任何一个兼容类型的bean能够为属性赋值，则该属性不装配，即值为默认值null。
  - 若在IOC中，有多个兼容类型的bean能够为属性赋值，则抛出异常NoUniqueBeanDefinitionException
- byName：将自动装配的属性的属性名，作为bean的id在IOC容器中匹配相对应的bean进行赋值。

```xml
<bean id="userController" class="com.aip.controller.UserController" autowire="byType"></bean>

<bean id="userService" class="com.aip.service.impl.UserServiceImpl" autowire="byType"></bean>

<bean id="userDao" class="com.aip.dao.impl.UserDaoImpl"></bean>
```

正常情况通常使用`ByType`，如果`ByType`不好使了，则用`ByName`。

```xml
<bean id="userController" class="com.aip.controller.UserController" autowire="byName"></bean>

<bean id="userService" class="com.aip.service.impl.UserServiceImpl" autowire="byName"></bean>

<bean id="userServiceImpl" class="com.aip.service.impl.UserServiceImpl" autowire="byName"></bean>

<bean id="userDao" class="com.aip.dao.impl.UserDaoImpl"></bean>

<bean id="userDaoImpl" class="com.aip.dao.impl.UserDaoImpl"></bean>
```



### 2.9.2、基于注解的自动装配

> 官方文档：[核心技术 (springdoc.cn)](https://springdoc.cn/spring/core.html#beans-annotation-config)

如果IOC容器管理的是第三方jar包提供的类，这时候则用不了注解。



#### 手动配置

手动配置有两个步骤

- 注解
- 扫描

> 这四个注解有什么区别？
>
> @Component：将类标识为普通组件
> @Controller：将类标识为控制层组件
> @Service：将类标识为业务层组件
> @Repository：将类标识为持久层组件

```java
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface Service {

   /**
    * The value may indicate a suggestion for a logical component name,
    * to be turned into a Spring bean in case of an autodetected component.
    * @return the suggested component name, if any (or empty String otherwise)
    */
   @AliasFor(annotation = Component.class)
   String value() default "";

}
```

通过查看源码我们得知，@Controller、@Service、@Repository这三个注解只是在@Component注解的基础上起了三个新的名字。

对于Spring使用IOC容器管理这些组件来说没有区别。所以@Controller、@Service、@Repository这三个注解只是给开发人员看的，让我们能够便于分辨组件的作用。

注意：虽然它们本质上一样，但是为了代码的可读性，为了程序结构严谨我们肯定不能随便胡乱标记。

> 注解

以控制层为例

```java
@Controller
public class UserController {
}
```



> 扫描

- `context:component-scan`：扫描指定包下的文件
  - `context:exclude-filter`：指定排除扫描的文件
    - `type="annotation"`：根据注解排除
    - `type="assignable"`：根据类型排除
  - `context:include-filter`：仅扫描指定文件
    - `use-default-filters`：取值false表示关闭默认扫描规则，默认规则即扫描指定包下所有类。如果要仅扫描指定文件，则必须设置为false。
    - `type="annotation"`：根据注解排除
    - `type="assignable"`：根据类型排除

扫描指定包下的文件

最基本的方式：扫描该路径一下的所有文件

```xml
<context:component-scan base-package="com.aip"/>
```

指定排除扫描的文件

```xml
<context:component-scan base-package="com.aip">
    <context:exclude-filter type="annotation" expression="org.springframework.stereotype.Controller"/>
    <context:exclude-filter type="assignable" expression="com.aip.controller.UserController"/>
</context:component-scan>
```

仅扫描指定文件

```xml
<context:component-scan base-package="com.aip" use-default-filters="false">
    <context:exclude-filter type="annotation" expression="org.springframework.stereotype.Controller"/>
    <context:include-filter type="assignable" expression="com.aip.controller.UserController"/>
</context:component-scan>
```



#### 通过注解生成的bean的id

在我们使用XML方式管理bean的时候，每个bean都有一个唯一标识，便于在其他地方引用。现在使用
注解后，每个组件仍然应该有一个唯一标识。

> 默认情况

类名首字母小写就是bean的id。例如：UserController类对应的bean的id就是userController。

自定义bean的id可通过标识组件的注解的value属性设置自定义的bean的id。@Service("userService")



#### 自动装配

原理和基于xml的自动装配一样的，基于注解也就是省事一点。

在成员变量上直接标记@Autowired注解即可完成自动装配，不需要提供setXxx()方法。以后我们在项目中的正式用法就是这样。



```Java
@Controller
public class UserController {
    @Autowired
    private UserService userService;

    public void saveUser(){
        userService.saveUser();
    }
}
```



```java
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public void saveUser() {
        userDao.saveUser();
    }
}
```



```java
@Repository
public class UserDaoImpl implements UserDao {
    @Override
    public void saveUser() {
        System.out.println("保存成功");
    }
}
```

> @Autowired注解可以标记在构造器和set方法上。

```Java
@Controller
public class UserController {
    
    private UserService userService;

    public UserController() {
    }
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
```



```Java
@Controller
public class UserController {

    private UserService userService;

    public UserController() {
    }
    
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
```



### 2.9.3、@Qualifier 注解

如果**通过类型**和**通过名称**两种方式都无法实现自动装配。即 IOC容器 中有多个类型匹配的bean，且这些bean的id和要赋值的属性的属性名都不一致。

在要赋值的属性（成员变量）上添加`@Qualifier()`注解。

通过该注解的value属性值，指定某个bean的id，将这个bean为属性赋值

也可以理解为起别名。

> 在官方文档中的介绍是这样的

~~~java
public class MovieRecommender {

    @Autowired
    @Qualifier("main")
    private MovieCatalog movieCatalog;

    // ...
}
~~~

```XML
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:context="http://www.springframework.org/schema/context"
    xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        https://www.springframework.org/schema/context/spring-context.xsd">

    <context:annotation-config/>

    <bean class="example.SimpleMovieCatalog">
        <qualifier value="main"/> 

        <!-- inject any dependencies required by this bean -->
    </bean>

    <bean class="example.SimpleMovieCatalog">
        <qualifier value="action"/> 

        <!-- inject any dependencies required by this bean -->
    </bean>

    <bean id="movieRecommender" class="example.MovieRecommender"/>

</beans>
```

> 我的Demo

```Java
@Controller
public class UserController {
    @Autowired
    @Qualifier("userServiceImpl")
    private UserService userService;

    public UserController() {
    }
}
```



```Java
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    @Qualifier("userDaoImpl")
    private UserDao userDao;

    @Override
    public void saveUser() {
        userDao.saveUser();
    }
}
```



### 2.9.4、自动装配的原理

![自动装配原理图](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/%E8%87%AA%E5%8A%A8%E8%A3%85%E9%85%8D%E5%8E%9F%E7%90%86.png)

基于注解的自动装配，有一个属性是必须完成自动装配的，就是说用注解的话，是需要把整个流程跑完的。



- 首先根据所需要的组件类型到IOC容器中查找
  - 能够找到唯一的bean：直接执行装配
  - 如果完全找不到匹配这个类型的bean：装配失败
  - 和所需类型匹配的bean不止一个
    - 没有@Qualifier注解：根据@Autowired标记位置成员变量的变量名作为bean的id进行匹配
      - 能够找到：执行装配
      - 找不到：装配失败
    - 使用@Qualifier注解：根据@Qualifier注解中指定的名称作为bean的id进行匹配
      - 能够找到：执行装配
      - 找不到：装配失败



# 3、AOP思想

## 3.1、问题引入

以下是一个计算器接口的实现类，提供了关于算数的核心代码。

```Java
public class CalculatorImpl implements Calculator{

    @Override
    public int add(int i, int j) {
        System.out.println("[日志] add 方法开始了，参数是：" + i + "," + j);
        int result = i + j;
        System.out.println("方法内部 result = " + result);
        System.out.println("[日志] add 方法结束了，结果是：" + result);
        return result;
    }

    @Override
    public int sub(int i, int j) {
        System.out.println("[日志] sub 方法开始了，参数是：" + i + "," + j);
        int result = i - j;
        System.out.println("方法内部 result = " + result);
        System.out.println("[日志] sub 方法结束了，结果是：" + result);
        return result;
    }

    @Override
    public int mul(int i, int j) {
        System.out.println("[日志] mul 方法开始了，参数是：" + i + "," + j);
        int result = i * j;
        System.out.println("方法内部 result = " + result);
        System.out.println("[日志] mul 方法结束了，结果是：" + result);
        return result;
    }

    @Override
    public int div(int i, int j) {
        System.out.println("[日志] div 方法开始了，参数是：" + i + "," + j);
        int result = i / j;
        System.out.println("方法内部 result = " + result);
        System.out.println("[日志] div 方法结束了，结果是：" + result);
        return result;
    }
}
```

以改代码为例子，在真实的项目中。我们会有核心业务代码，例如计算器的加减乘除。同时也有非核心代码，例如日志输出。

> 现存的问题

针对带日志功能的实现类，我们发现有如下缺陷：

- 对核心业务功能有干扰，导致程序员在开发核心业务功能时分散了精力
- 附加功能分散在各个业务功能方法中，不利于统一维护

>解决思路

核心就是：解耦。我们需要把附加功能从业务功能代码中抽取出来。

>解决问题的困难点

要抽取的代码在方法内部，靠以前把子类中的重复代码抽取到父类的方式没法解决。

因为核心代码与日志代码是连续执行的，因为OOP是纵向继承机制，只能将一段连续的代码进行封装。

所以需要引入新的技术。



## 3.2、新的模式Proxy（代理）模式

### 3.2.1、什么是Proxy模式

> 它指的是代替别人进行工作的人。当不一定需要本人亲自进行工作时，就可以寻找代理人去完成工作。但代理人毕竟只是代理人，能代替本人做的事情终究是有限的。因此，当代理人遇到无法自已解决的事情时就会去找本人解决该问题。
> 在面向对象编程中，“本人”和“代理人”都是对象。如果“本人”对象太忙了，有些工作无法自己亲自完成，就将其交给“代理人”对象负责。
>
> 取自《图解设计模式》

给一个对象提供一种代理对象以控制对该对象的访问。

简单点理解：

- 目标对象：原对象，我们需要通过代理对象控制它的访问，扩展其功能。
- 代理对象：代理模式产生的对象，是原对象的替身，在原有基础上进行修改。可以在不改变原对象代码的基础上对原对象的功能进行扩展

再简单点理解：比如点外卖事件

你想吃麻辣烫，自己又不想去店里吃，所以你点外卖，此时的外卖小哥，可以看作为代理对象。而你又想在吃完麻辣烫后喝一杯珍珠奶茶，所以你又联系外卖小哥帮你去店里买一杯。这个过程可以理解为加的扩展功能。



### 3.2.2、为什么要使用代理模式

- 降低了系统的耦合度，扩展性好
- 可以起到保护目标对象的作用

Proxy模式可以提供更好的控制和管理对象的方式，同时还能为系统添加额外的功能和服务。它能够提高系统的灵活性、安全性、性能和可维护性，适用于各种复杂的软件系统和场景。



### 3.2.3、通过引入问题了解代理模式

> 静态代理

静态代理是一对一的，确实实现了解耦，但是由于代码都写死了，完全不具备任何的灵活性。

就拿日志功能来说，将来其他地方也需要附加日志，那还得再声明更多个静态代理类，那就产生了大量重复的代码，日志功能还是分散的，没有统一管理。

```Java
public class CalculatorStaticProxy implements Calculator{

    /**
     * 将被代理的目标对象声明为成员变量
     * @author Aganippe
     * @version v1.0
     * @date 2023/6/26
     * @param null
     */
    private Calculator target;

    public CalculatorStaticProxy() {
    }

    public CalculatorStaticProxy(Calculator target) {
        this.target = target;
    }

    @Override
    public int add(int i, int j) {
        int result = 0;

        try {
            // 附加功能由代理类中的代理方法来实现
            System.out.println("[日志] add 方法开始了，参数是：" + i + "," + j);
            // 通过目标对象来实现核心业务逻辑
            result = target.add(i, j);
            System.out.println("[日志] add 方法结束了，结果是：" + result);

        } catch (Exception e) {
            e.printStackTrace();

        } finally {

        }
        return result;
    }

    @Override
    public int sub(int i, int j) {
        // 附加功能由代理类中的代理方法来实现
        System.out.println("[日志] add 方法开始了，参数是：" + i + "," + j);

        // 通过目标对象来实现核心业务逻辑
        int addResult = target.sub(i, j);

        System.out.println("[日志] add 方法结束了，结果是：" + addResult);
        return addResult;
    }

    @Override
    public int mul(int i, int j) {
        // 附加功能由代理类中的代理方法来实现
        System.out.println("[日志] add 方法开始了，参数是：" + i + "," + j);

        // 通过目标对象来实现核心业务逻辑
        int addResult = target.mul(i, j);

        System.out.println("[日志] add 方法结束了，结果是：" + addResult);
        return addResult;
    }

    @Override
    public int div(int i, int j) {
        // 附加功能由代理类中的代理方法来实现
        System.out.println("[日志] add 方法开始了，参数是：" + i + "," + j);

        // 通过目标对象来实现核心业务逻辑
        int addResult = target.div(i, j);

        System.out.println("[日志] add 方法结束了，结果是：" + addResult);
        return addResult;
    }
}
```

==解决办法==：将日志功能集中到一个代理类中，将来有任何日志需求，都通过这一个代理类来实现。这就需要使用动态代理技术了。



> 动态代理

打一次断点，自己调式一遍就能理解。通过反射去进行动态代理。

在事先我们是不知道这是什么类的，所以构造器需要用 *Object类型* 来处理。

然后创建一个代理实例，其中三个参数：

-  代理类的类加载器
- 目标类所实现的所有接口
- 设置代理对象实现目标对象方法的过程，即代理类中如何重写接口中的抽象方法

`invoke()`是一个调用类中方法的方法。动态代理需要了解反射的前置知识点。

```Java
public class ProxyFactory {
    private Object target;

    public ProxyFactory() {
    }

    public ProxyFactory(Object target) {
        this.target = target;
    }

    public Object getProxy(){
        /* newProxyInstance()：创建一个代理实例，其中有三个参数：
           1、classLoader：加载动态生成的代理类的类加载器
           2、class[] interfaces：目标对象实现的所有接口的class对象所组成的数组
           3、invocationHandler：设置代理对象实现目标对象方法的过程，即代理类中如何重写接口中的抽象方法
         */
        ClassLoader classLoader = target.getClass().getClassLoader();
        Class<?>[] interfaces = target.getClass().getInterfaces();
        InvocationHandler invocationHandler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                /* proxy：代理对象
                   method：代理对象需要实现的方法，即其中需要重写的方法
                   args：method所对应方法的参数
                */
                Object result = null;
                try {
                    System.out.println("[动态代理][日志] 方法名："+method.getName()+"，参数："+ Arrays.toString(args));
                    result = method.invoke(target, args);
                    System.out.println("[动态代理][日志] 方法名："+method.getName()+"，结果："+ result);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("[动态代理][日志] 方法名："+method.getName()+"，异常："+e.getMessage());
                }finally {
                    System.out.println("[动态代理][日志] 方法名："+method.getName()+"，方法执行完毕");
                }
                return result;
            }
        };
        return Proxy.newProxyInstance(classLoader,interfaces,invocationHandler);
    }
}
```



```Java
@Test
public void test(){
    ProxyFactory factory = new ProxyFactory(new CalculatorImpl());
    Calculator proxy = (Calculator) factory.getProxy();
    proxy.div(27,3);
}
```



> 动态代理的拓展

动态代理有两种：

- JDK动态代理：要求必须有接口，最终生成的**代理类**和**目标类**实现相同的接口。在`com.sun.proxy`包下，类名为`$proxy*`*为任意数字，例如`$proxy3`。
- cglib动态代理：最终生成的**代理类**会继承**目标类**,并且和目标类在相同的包下。



JDK和CGLIB动态代理的区别：

- JDK
  - JDK代理使用的是反射机制生成一个实现代理接口的匿名类，在调用具体方法前调用InvokeHandler来处理。
  - JDK创建代理对象效率较高，执行效率较低；
  - JDK动态代理机制是委托机制，只能对实现接口的类生成代理，通过反射动态实现接口类；

- CGLIB
  - CGLIB代理使用字节码处理框架asm，对代理对象类的class文件加载进来，通过修改字节码生成子类。
  - 
    CGLIB创建代理对象效率较低，执行效率高。
  - CGLIB则使用的继承机制，针对类实现代理，被代理类和代理类是继承关系，所以代理类是可以赋值给被代理类的，因为是继承机制，不能代理final修饰的类。



两种代理需要满足的要求：

- JDK代理是不需要依赖第三方的库，只要JDK环境就可以进行代理，需要满足以下要求：
  - 实现InvocationHandler接口，重写invoke()
  - 使用Proxy.newProxyInstance()产生代理对象
  - 被代理的对象必须要实现接口
- CGLib 必须依赖于CGLib的类库,需要满足以下要求：
  - 实现MethodInterceptor接口，重写intercept()
  - 使用Enhancer对象.create()产生代理对象

> AOP是**JDK代理**和**CGLIB代理**并存的，如果实现了接口，就用JDK代理，没有就转换CGLIB代理
>
> 以下取自官方文档 [核心技术 (springdoc.cn)](https://springdoc.cn/spring/core.html#aop-introduction-proxies)
>
> Spring AOP默认使用标准的JDK动态代理进行AOP代理。这使得任何接口（或一组接口）都可以被代理。
>
> Spring AOP也可以使用CGLIB代理。这对于代理类而不是接口来说是必要的。默认情况下，如果一个业务对象没有实现一个接口，就会使用CGLIB。由于对接口而不是类进行编程是很好的做法，业务类通常实现一个或多个业务接口。在那些（希望是罕见的）需要向没有在接口上声明的方法提供advice的情况下，或者在需要将代理对象作为具体类型传递给方法的情况下，可以 [强制使用 CGLIB](https://springdoc.cn/spring/core.html#aop-proxying)。
>
> 掌握Spring AOP是基于代理的事实是很重要的。请参阅 “[了解AOP代理](https://springdoc.cn/spring/core.html#aop-understanding-aop-proxies)”，以深入研究这一实现细节的实际含义。



## 3.3、AOP概念

> 面向切面的编程（AOP）通过提供另一种思考程序结构的方式来补充面向对象的编程（OOP）。OOP中模块化的关键单位是类，而AOP中模块化的单位是切面。切面使跨越多种类型和对象的关注点（如事务管理）模块化。（这样的关注点在AOP文献中通常被称为 "交叉（crosscutting）" 关注点）。
>
> Spring的关键组件之一是AOP框架。虽然Spring IoC容器并不依赖于AOP（意味着如果你不想使用AOP，就不需要使用），但AOP补充了Spring IoC，提供了一个非常有能力的中间件解决方案。
>
> AOP在Spring框架中被用于：
>
> - 提供声明式的企业服务。最重要的此类服务是 [声明式事务管理](https://springdoc.cn/spring/data-access.html#transaction-declarative)。
> - 让用户实现自定义切面，用AOP补充他们对OOP的使用。



AOP（Aspect-Oriented Programming）是一种软件开发思想，旨在通过将横切关注点（cross-cutting concerns）从核心业务逻辑中分离出来，提高代码的模块化和可维护性。

AOP 的思想是将**横切关注点**（在相关术语中介绍）抽象出来，形成可重用的模块，称为切面（Aspect）。切面可以独立于业务逻辑进行开发和维护，从而提供了一种集中处理横切关注点的方式。

在 AOP 中，程序的功能被分为核心关注点（Core Concerns）和横切关注点。核心关注点代表业务逻辑的实现，而横切关注点代表与核心关注点无关的功能，如日志记录、异常处理等。通过 AOP，我们可以将横切关注点从核心关注点中剥离出来，并通过切面将其注入到程序的适当位置，而无需修改核心关注点的代码。



## 3.4、AOP相关术语

要学习AOP，需要先了解AOP的术语，这是整个AOP思想的术语，并非Spring的。



### 3.4.1、核心关注点（Core Concerns）

指的是核心业务逻辑的实现代码



### 3.4.2、横切关注点（Cross-cutting concerns）

指的是非核心业务逻辑的实现，如日志记录、异常处理等。

横切关注点是从每个方法中抽取出来的同一类非核心业务。在同一个项目中，我们可以使用多个横切关注点对相关方法进行多个不同方面的增强。

这个概念不是语法层面天然存在的，而是根据附加功能的逻辑上的需要：有十个附加功能，就有十个横切关注点。



### 3.4.3、切面（Aspect）

切面是对横切关注点（通知方法）的抽象和封装，是实现横切关注点功能的具体实体



### 3.4.4、连接点（Join point）

==连接点代表的是一个位置，是纯逻辑概念，不是语法定义的某个点。==你在哪个地方把非核心代码抽出来解耦，就要在那个地方连回去，这样才是一个整体。

抽取横切关注点（非核心业务）的位置。例如一个方法的执行或一个异常的处理。在Spring AOP中，一个连接点总是代表一个方法的执行。

把方法排成一排，每一个横切位置看成x轴方向，把方法从上到下执行的顺序看成y轴，x轴和y轴的交叉点就是连接点。

![连接点](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/%E8%BF%9E%E6%8E%A5%E7%82%B9.png)



### 3.4.5、通知（Advice）

横切关注点，也就是非核心业务，需要从核心代码中抽离出来。那么每一个处理出来的横切关注点，可以封装为一个方法，也就是我们的通知。

每一个横切关注点上要做的事情都需要写一个方法来实现，这样的方法就叫通知方法。

通知是一种方法，方法则需要封装到类里面进行实现，切面就是用来封装通知的一个类。

- 前置通知：**使用@Before注解标识**，在被代理的目标方法前执行（try内返回值的前面）
- 返回通知：**使用@AfterReturning注解标识**，在被代理的目标方法成功结束后执行（try内返回值的后面）
- 异常通知：**使用@AfterThrowing注解标识**，在被代理的目标方法异常结束后执行（catch）
- 后置通知：**使用@After注解标识**，在被代理的目标方法最终结束后执行（finally）
- 环绕通知：**使用@Around注解标识**，使用try...catch...finally结构围绕整个被代理的目标方法，包括上面四种通知对应的所有位置

> 小拓展：Spring各版本通知的执行顺序

- Spring版本5.3.x以前：
  - 前置通知
  - 目标操作
  - 后置通知
  - 返回通知或异常通知
- Spring版本5.3.x以后：
  - 前置通知
  - 目标操作
  - 返回通知或异常通知
  - 后置通知



### 3.4.6、切入点（Pointcut）

切入点是定位连接点的方式。每个类的方法中都包含多个连接点，所以连接点是类中客观存在的事物。

> *advice与一个切点表达式相关联，并在切点匹配的任何连接点上运行（例如，执行一个具有特定名称的方法）。由切点表达式匹配的连接点概念是AOP的核心，Spring默认使用AspectJ的切点表达式语言。*
>
> 如官方文档所描述切入点是一种表达式，通过这种表达式来定位连接点在哪里。



### 3.4.7、目标对象（Target object）

目标类，需要被代理的类，这个对象总是一个被代理的对象。

是指所有被通知的对象，也称为被增强对象。



### 3.4.8、代理（proxy）

一个由AOP框架创建的对象。将通知应用到目标对象之后，被动态创建的对象。



### 3.4.9、织入（Weaving）

将切面代码插入到目标对象上，从而生成代理对象的过程。 

> *将aspect与其他应用程序类型或对象连接起来，以创建一个 advice 对象。这可以在编译时（例如，使用AspectJ编译器）、加载时或运行时完成。Spring AOP和其他纯Java AOP框架一样，在运行时进行织入。*



## 3.5、AOP的作用

 总结一下，两个关键点：

- 简化代码：把方法中固定位置的重复的代码抽取出来，让被抽取的方法更专注于自己的核心功能，
  提高内聚性。
- 代码增强：把特定的功能封装到切面类中，看哪里有需要，就往上套，被套用了切面逻辑的方法就
  被切面给增强了。



## 3.6、AOP的实现

### 3.6.1、基于注解

AOP是一种思想，*AspectJ* 是AOP具体的一种实现方式。

![AOP的实现方式](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/Spring%20AOP%E7%9A%84%E5%AE%9E%E7%8E%B0%E6%96%B9%E5%BC%8F.png)

AspectJ：本质上是静态代理，将代理逻辑“织入”被代理的目标类编译得到的字节码文件，所以最终效果是动态的。weaver就是织入器。Spring只是借用了AspectJ中的注解。



### 3.6.2、启用 @AspectJ

两个方法任选其一，启用之后，则代表该项目支持面向切面编程。

> 通过Java 注解配置

~~~Java
@Configuration
@EnableAspectJAutoProxy
public class AppConfig {
}
~~~

> 通过XML配置启用——通常使用这个

```xml
<aop:aspectj-autoproxy />
```



### 3.6.3、声明一个 Aspect 切面

~~~Java
package com.aip;
@Component   //@Component注解保证这个切面类能够放入IOC容器，当然你也可以选择手动注入 IOC容器。
@Aspect  	 //@Aspect表示这个类是一个切面类
public class LogAspect {
}
~~~

>手动注入

~~~xml
<bean id="myAspect" class="com.aip.LogAspect" />
~~~

### 3.6.4、切入点语法表达式

```Java
@Component
@Aspect
public class LogAspect {
    /**
     * 在目标方法执行之前执行
     * @author Aganippe
     * @version v1.0
     * @date 2023/6/28
     * @name beforeMethod 前置通知
     * @return void
     */
    @Before("execution(public int com.aip.CalculatorImpl.add(int,int))") //此类注解中填写的是切入点表达式，格式：修饰符 返回值 具体哪个类的方法（需要的参数类型）
    public void beforeMethod(){
        System.out.println("前置通知");
    }
}
```

以当前案例来说，这个切入点表达式是写死的。非常的不灵活。当然在实际开发中是不可取的，开发者不可能为每一个方法都一一写一个通知。



首先我们需要了解表达式的格式

![切入点表达式格式](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/%E5%88%87%E5%85%A5%E7%82%B9%E8%A1%A8%E8%BE%BE%E5%BC%8F%E7%9A%84%E6%A0%BC%E5%BC%8F.png)

- 用 \*号代替“权限修饰符”和“返回值”部分表示“权限修饰符”和“返回值”不限
- 在包名的部分，一个\*号只能代表包的层次结构中的一层，表示这一层是任意的。
  - 例如：\*.Hello匹配com.Hello，不匹配com.aip.Hello
- 在包名的部分，使用 \*.. 表示包名任意、包的层次深度任意
- 在类名的部分，类名部分整体用 \* 号代替，表示类名任意
- 在类名的部分，可以使用 \*号代替类名的一部分
  - 例如：\*Service匹配所有名称以Service结尾的类或接口
- 在方法名部分，可以使用 \*号表示方法名任意
- 在方法名部分，可以使用 \*号代替方法名的一部分
  - 例如：\*Operation匹配所有方法名以Operation结尾的方法
- 在方法参数列表部分，使用(..)表示参数列表任意
- 在方法参数列表部分，使用(int,..)表示参数列表以一个int类型的参数开头
- 在方法参数列表部分，基本数据类型和对应的包装类型是不一样的
  - 切入点表达式中使用 int 和实际方法中 Integer 是不匹配的
- 在方法返回值部分，如果想要明确指定一个返回值类型，那么必须同时写明权限修饰符
  - 例如：execution(public int ..Service.\*(.., int)) 正确
  - 例如：execution(\* int ..Service.\*(.., int)) 错误



### 3.6.5、切入点表达式的重用

类似于声明了一个公共常量，随用随拿，当类用到大量的相同的切入点时，可以使用。

当然你也可以设置多个，只需要方法名不同即可。

```Java
@Component
@Aspect
public class LogAspect {

    /**
     * 用来声明一个公共的切入点表达式，你可以理解为设置了一个常量字符串，随用随拿
     * @author Aganippe
     * @version v1.0
     * @date 2023/6/28
     * @name pointCut
     * @return void
     */
    @Pointcut("execution(* com.aip.CalculatorImpl.*(..))")
    public void pointCut(){}

    @Before("pointCut()")
    public void beforeMethod() {
        System.out.println("前置通知");
    }
}
```



### 3.6.6、连接点

> 如何获取连接点信息

在通知方法的参数位置，设置一个为`JoinPoint`类型的参数。

这个类有什么用呢？

这个类的主要作用就是**可以让我们在Advice（通知）中获取被增强方法相关的所有信息**。通过JoinPoint可以**获取被代理方法的各种信息，如方法参数，方法所在类的class对象**，然后执行反射操作。

常用方法如下

| 方法名                    | 功能                                                         |
| ------------------------- | ------------------------------------------------------------ |
| Signature getSignature(); | 获取封装了署名信息的对象,在该对象中可以获取到目标方法名,所属类的Class等信息。 |
| Object[] getArgs();       | 获取传入目标方法的参数对象。                                 |
| Object getTarget();       | 获取目标对象（被代理对象）。                                 |
| Object getThis();         | 获取代理对象                                                 |

其中关键方法就是这个getSignature(),该方法返回Signature对象，而通过该对象我们可以获得被增强方法的信息。

```Java
@Component
@Aspect
public class LogAspect {

    /**
     * 用来声明一个公共的切入点表达式，你可以理解为设置了一个常量字符串，随用随拿
     * @author Aganippe
     * @version v1.0
     * @date 2023/6/28
     * @name pointCut
     * @return void
     */
    @Pointcut("execution(* com.aip.CalculatorImpl.*(..))")
    public void pointCut(){}

    /**
     * 在目标方法执行之前执行
     * @return void
     * @author Aganippe
     * @version v1.0
     * @date 2023/6/28
     * @name beforeMethod 前置通知
     */
    @Before("pointCut()")
    public void beforeMethod(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        Object[] args = joinPoint.getArgs();
        System.out.println("前置通知,方法名：" + signature.getName() + "\t参数为：" + Arrays.toString(args));
    }
}
```



### 3.6.7、在返回通知获取返回值

@AfterReturning中的属性returning，用来将通知方法的某个形参，接收目标方法的返回值。

```Java
@AfterReturning(value = "pointCut()", returning = "result")
public void afterReturningMethod(JoinPoint joinPoint, Object result) {
    Signature signature = joinPoint.getSignature();
    System.out.println("返回通知,方法：" + signature.getName() + "\t结果：" + result);
}
```



### 3.6.8、在异常通知捕获异常

使用方法和上面例子大差不差。

@AfterThrowing中的属性throwing，用来将通知方法的某个形参，接收目标方法的异常

```Java
@AfterThrowing(value = "pointCut()", throwing = "e")
public void afterThrowing(JoinPoint joinPoint, Throwable e) {
    Signature signature = joinPoint.getSignature();
    System.out.println("异常通知,方法" + signature.getName() + ",异常：" + e);
}
```



### 3.6.9、环绕通知

环绕通知传入的参数是`ProceedingJoinPoint`，环绕通知的方法返回值一定要和目标对象的方法的返回值一致。

```Java
@Around("pointCut()")
public Object aroundMethod(ProceedingJoinPoint pjp){
    Object result = null;
    try {
        System.out.println("环绕通知-->前置通知");
        //表示目标对象的执行方法
        result = pjp.proceed();
        System.out.println("环绕通知-->返回通知");
    } catch (Throwable throwable) {
        throwable.printStackTrace();
        System.out.println("环绕通知-->异常通知");
    } finally {
        System.out.println("环绕通知-->后置通知");
    }
    return result;
}
```



### 3.6.10、切面的优先级

> 当两个定义在不同切面的advice都需要在同一个连接点运行时，除非你另外指定，否则执行的顺序是不确定的。你可以通过指定优先级来控制执行的顺序。这可以通过在切面类中实现 `org.springframework.core.Ordered` 接口或用 `@Order` 注解来完成，这是正常的Spring方式。给定两个切面，从 `Ordered.getOrder()` 返回较低值的切面（或注解值）具有较高的优先权。

每个切面都有一个默认的优先级。当然也可以手动设置优先级的值，值越小，优先级越高。

```Java
/**
 * @author Aganippe
 * @version 1.0
 * @date 2023/6/29  16:57
 * @description 验证切面优先级
 */
@Component
@Aspect
@Order(1)
public class ValidateAspect {
    @Before("execution(* com.aip.CalculatorImpl.*(..))")
    public void beforeMethod(){
        System.out.println("ValidateAspect--->前置通知");
    }
}
```



### 3.6.11、基于xml实现AOP

其实就是xml注入，非常不方便，一般都使用注解，这个了解即可。用了的话，估计接手你项目的人会用眼神刀了你。

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:aop="http://www.springframework.org/schema/aop"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/aop https://www.springframework.org/schema/aop/spring-aop.xsd http://www.springframework.org/schema/context https://www.springframework.org/schema/context/spring-context.xsd">

    <context:component-scan base-package="com.xml" />
    <aop:config>
        <!--配置切面类-->
        <aop:aspect ref="logAspect">
            <aop:pointcut id="pointCut" expression="execution(* com.xml.CalculatorImpl.*(..))"></aop:pointcut>
            <aop:before method="beforeMethod" pointcut-ref="pointCut" />
            <aop:after method="afterMethod" pointcut-ref="pointCut" />
            <aop:after-returning method="afterReturningMethod" returning="result" pointcut-ref="pointCut" />
            <aop:after-throwing method="afterThrowing" throwing="e" pointcut-ref="pointCut" />
        </aop:aspect>
        <aop:aspect ref="validateAspect" order="1">
        <aop:before method="beforeMethod" pointcut-ref="pointCut" />
        </aop:aspect>
    </aop:config>
</beans>
```



# 4、事务管理

> 具体的介绍可以去看看官方文档的内容。 [数据访问 (springdoc.cn)](https://springdoc.cn/spring/data-access.html#spring-data-tier)
>
> 大多数Spring框架用户选择声明式事务管理。这个选项对应用程序代码的影响最小，因此与非侵入性的轻量级容器的理想最为一致。

## 4.1基于Spring框架对数据库进行管理

Spring 框架对 JDBC 进行封装，使用 JdbcTemplate 方便实现对数据库操作

> 依赖

```xml
<dependencies>
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>8.0.23</version>
    </dependency>

    <dependency>
        <groupId>com.alibaba</groupId>
        <artifactId>druid</artifactId>
        <version>1.0.31</version>
    </dependency>

    <dependency>
        <groupId>junit</groupId>
        <artifactId>junit</artifactId>
        <version>4.13.2</version>
        <scope>test</scope>
    </dependency>

    <!-- 基于Maven依赖传递性，导入spring-context依赖即可导入当前所需所有jar包 -->
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-context</artifactId>
        <version>5.3.20</version>
    </dependency>

    <!-- Spring 持久化层支持jar包 
 	     Spring 在执行持久化层操作、与持久化层技术进行整合过程中，需要使用orm、jdbc、tx三个jar包 
         导入 orm 包就可以通过 Maven 的依赖传递性把其他两个也导入 -->
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-orm</artifactId>
        <version>5.3.20</version>
    </dependency>

    <!-- Spring 测试相关 -->
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-test</artifactId>
        <version>5.3.20</version>
    </dependency>
</dependencies>
```

```xml
<!-- 导入外部属性文件 -->
<context:property-placeholder location="jdbc.properties" />
<!-- 配置数据源 -->
<bean id="druidDataSource" class="com.alibaba.druid.pool.DruidDataSource">
    <property name="driverClassName" value="${jdbc.driver}" />
    <property name="url" value="${jdbc.url}" />
    <property name="username" value="${jdbc.username}" />
    <property name="password" value="${jdbc.password}" />
</bean>

<!-- 配置 JdbcTemplate -->
<bean class="org.springframework.jdbc.core.JdbcTemplate">
    <!-- 装配数据源 -->
    <property name="dataSource" ref="druidDataSource"/>
</bean>
```

### 4.1.2、测试

`JdbcTemplate`里面的update方法可以完成增删改的操作

> 增加数据

```Java
//指定当前测试类在Spring的测试环境中执行，此时就可以通过注入的方式直接获取IOC容器中的bean
@RunWith(SpringJUnit4ClassRunner.class)
//设置Spring测试环境的配置文件
@ContextConfiguration("classpath:jdbc.xml")
public class JdbcTemplateTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /** 
     * 这是一个添加功能的测试
     * @author Aganippe
     * @version v1.0
     * @date 2023/6/30
     * @name testInsert
     * @return void
     */
    @Test
    public void testInsert(){
        String sql = "insert into t_user values(null,?,?,?,?,?)";
        int result = jdbcTemplate.update(sql,"Xinan","4440136",22,"女","Xinan@163.com");
        System.out.println(result);
    }
}
```



> 查询数据

了解即可，以后还是用Mybatis

这是单条数据

```Java
@Test
public void testSelect(){
    String sql = "select * from t_user where id = ?";
    User user = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), 2);
    System.out.println(user);
}
```

这是查询多个数据，集合

```Java
/** 
 * 查询多条数据
 * @author Aganippe
 * @version v1.0
 * @date 2023/6/30
 * @name testGetMoreUser
 * @return void
 */
@Test
public void testGetMoreUser(){
    String sql = "select * from t_user where id between ? and ?";
    List<User> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class), 1, 7);
    list.forEach(System.out::println);
}
```

查询匹配数据的记录行数

```Java
@Test
public void testGetCount(){
    String sql = "select count(id) from t_user";
    Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
    System.out.println(count);
}
```



> 总结：

- 查询数据返回结果是单条数据的话用`queryForObject()`的方法。查询出来是一个集合结果的话用`query()`方法。
- 增删改操作，统一用`update()`方法。



## 4.2、什么是编程式事务

之前学的JDBC就是编程式事务。

编程式事务：自己写代码实现功能，事务功能的相关操作全部通过自己编写代码来实现。下面是相关实例

~~~java
Connection conn = ...;
try {
	// 开启事务：关闭事务的自动提交
	conn.setAutoCommit(false);
    
	// 核心操作
    
	// 提交事务
	conn.commit();
	}catch(Exception e){
	// 回滚事务
	conn.rollBack();
	}finally{
    // 释放数据库连接
	conn.close();
}
~~~

编程式的实现方式存在缺陷：

- 细节没有被屏蔽：具体操作过程中，所有细节都需要程序员自己来完成，比较繁琐。
- 代码复用性不高：如果没有有效抽取出来，每次实现功能都需要自己编写代码，代码就没有得到复用。

这时候声明式事务的优势就体现出来了。



## 4.3、声明式事务的概念

> 简单来说，声明式事务就是通过配置让框架实现事务功能。

既然事务控制的代码有规律可循，代码的结构基本是确定的，所以框架就可以将固定模式的代码抽取出来，进行相关的封装。

封装起来后，我们只需要在配置文件中进行简单的配置即可完成操作。

- 好处1：提高开发效率
- 好处2：消除了冗余的代码
- 好处3：框架会综合考虑相关领域中在实际开发环境下有可能遇到的各种问题，进行了健壮性、性能等各个方面的优化。



## 4.4、如何选择编程式还是声明式事务管理

通常只有当你有少量的事务性操作时，编程式事务管理才是一个好主意。例如，如果你有一个web应用程序，只需要对某些更新操作进行事务，你可能不想通过使用Spring或其他技术来设置事务代理。在这种情况下，使用 `TransactionTemplate` 可能是一个好办法。能够明确地设置事务名称也是只有通过使用事务管理的编程式方法才能做到的。



## 4.5、基于注解实现声明式事务

### 4.5.1问题引入

以下是测试案例

> Dao层

```Java
@Repository
public class BookDaoImpl implements BookDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Double getPriceByBookId(Integer bookId) {
        String sql = "select price from t_book where book_id =?";
        return jdbcTemplate.queryForObject(sql,Double.class,bookId);
    }

    @Override
    public void updateStock(Integer bookId) {
        String sql = "update t_book set stock = stock-1 where book_id = ?";
        jdbcTemplate.update(sql,bookId);
    }

    @Override
    public void updateBalance(Integer Cid, Double price) {
        String sql = "update t_customer set balance = balance -? where c_id =?";
        jdbcTemplate.update(sql,price,Cid);
    }
}
```



> Service层

```Java
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDao bookDao;
    
    @Override
    public void buyBook(Integer bookId, Integer Cid) {
        Double price = bookDao.getPriceByBookId(bookId);
        bookDao.updateStock(bookId);
        bookDao.updateBalance(Cid,price);
    }
}
```



> Controller层

```Java
@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    public void buyBook(Integer bookId,Integer Cid){
        bookService.buyBook(bookId,Cid);
    }
}
```



> 相关数据表

![数据表](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/Spring%E4%BA%8B%E5%8A%A1%E6%B5%8B%E8%AF%95%E6%95%B0%E6%8D%AE%E8%A1%A8.png)



> 测试demo

```Java
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:transactionAnnotation.xml")
public class TxByAnnotationTest {

    @Autowired
    private BookController bookController;

    @Test
    public void testBuyBook(){
        bookController.buyBook(4,5);
    }
}
```



> 结果

异常意思：该字段存入的值超出了这个字段范围。

原因是，余额设置了无符号，也就是不能为负数。

![1688264222985](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/%E6%B2%A1%E6%9C%89%E5%AE%9E%E7%8E%B0%E4%BA%8B%E5%8A%A1%E5%8A%9F%E8%83%BD%E5%BC%82%E5%B8%B8%E5%9B%BE.png)

按理来说余额不够应该不能进行购买，这时候应该有回滚操作，但是此案例中没有实现，所以商品库存还是减了1。

![1688264696448](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/1688264696448.png)

MySQL中，默认的情况下是，一个SQL语句独占一个事务，且自动提交。

所以商品库存减少了，但是顾客余额没变。也就是库存提交了，但是余额回滚了。



### 4.5.2、如何通过注解开始事务

声明式事务不需要手动创建事务的切面，也不需要写通知。因为Spring提供有事务管理的切面和通知，叫做*事务管理器*。

> 在Spring中配置事务管理器

```xml
<bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
    <property name="dataSource" ref="dataSource"/>
</bean>
<!--开启事务的注解驱动通过注解@Transactional所标识的方法或标识的类中所有的方法，都会被事务管理器管理事务-->
<!-- transaction-manager属性的默认值是transactionManager，如果事务管理器bean的id正好就是这个默认值，则可以省略这个属性 -->
<tx:annotation-driven transaction-manager="transactionManager" />
```



> 添加事务注解

一般是在`Service业务逻辑层`添加事务注解，因为`Service`中的一个方法代表一个完整的功能。因此处理事务一般在service层的方法上添加注解@Transactional。

- 在方法上添加事务注解，则只有该方法会被事务管理。
- 如果将注解标识在实现类上，那么类中所有的方法都会被事务管理。

```Java
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDao bookDao;
    
    @Transactional
    @Override
    public void buyBook(Integer bookId, Integer Cid) {
        Double price = bookDao.getPriceByBookId(bookId);
        bookDao.updateStock(bookId);
        bookDao.updateBalance(Cid,price);
    }
}
```



## 4.6、事务的属性

### 4.6.1、只读

对一个查询操作来说，如果把它设置成只读，就能够明确告诉数据库，这个操作不涉及写操作。这样数据库就能够针对查询操作来进行优化。

*用法：*

```Java
@Transactional(readOnly = true)
```

> 注意
>
> 只读属性只适用于查询操作，对增删改操作设置只读会抛出下面异常：
>
> Caused by: java.sql.SQLException: Connection is read-only. Queries leading to data modification are not allowed



### 4.6.2、超时

设置一个超时属性`timeout 单位：秒`，当程序出现某些问题，导致卡顿。为了避免该程序长时间响应，占用资源。

当达到了设置的超时时间，则会强制回滚（不管事务是否执行完成），撤销它已做的操作，事务结束，把资源让出来，让其他正常程序可以执行。

*用法：*

```Java
@Transactional(timeout = 5)
```



> 结果
>
> 在设置的时间内事务并没有执行完，就会强制回滚，并抛出异常
>
> org.springframework.transaction.TransactionTimedOutException: Transaction timed out: deadline was Sun Jul 02 16:05:14 CST 2023



### 4.6.3、回滚策略

声明式事务默认只针对运行时异常回滚，编译时异常不回滚。那么当程序抛异常时，我们可以通过`@Transactional()`相关属性让事务回滚。

- rollbackFor属性：因为什么而回滚，需要设置一个Class类型的对象
- rollbackForClassName属性：因为什么而回滚，需要设置一个字符串类型的全类名
- noRollbackFor属性：不因为什么而回滚，需要设置一个Class类型的对象
- noRollbackFor属性：不因为什么而回滚，需要设置一个字符串类型的全类名

如果不设置相关属性的话，阿里约束会提示你，这很不舒服。请尽量规范代码！！！

```Java
@Transactional(rollbackFor = Exception.class)
```



### 4.6.4、事务隔离级别

数据库系统必须具有隔离并发运行各个事务的能力，使它们不会相互影响，避免各种并发问题。一个事务与其他事务隔离的程度称为隔离级别。SQL标准中规定了多种事务隔离级别，不同隔离级别对应不同的干扰程度，隔离级别越高，数据一致性就越好，但并发性越弱。

隔离级别一共有四种：

- 读未提交`READ UNCOMMITTED`：允许Transaction01读取Transaction02未提交的修改。
- 读已提交`READ COMMITTED`：要求Transaction01只能读取Transaction02已提交的修改。
- 可重复读`REPEATABLE READ`：确保Transaction01可以多次从一个字段中读取到相同的值，即Transaction01执行期间禁止其它事务对这个字段进行更新。
- 串行化`SERIALIZABLE`：确保Transaction01可以多次从一个表中读取到相同的行，在Transaction01执行期间，禁止其它事务对这个表进行添加、更新、删除操作。可以避免任何并发问题，但性能十分低下。



各个隔离级别解决并发问题的能力见下表：

| 隔离级别                     | 脏读 | 不可重复读 | 幻读 |
| ---------------------------- | ---- | ---------- | ---- |
| 读未提交（READ UNCOMMITTED） | 有   | 有         | 有   |
| 读已提交（READ COMMITTED）   | 无   | 有         | 有   |
| 可重复读（REPEATABLE READ）  | 无   | 无         | 有   |
| 串行化（SERIALIZABLE）       | 无   | 无         | 无   |



各种数据库产品对事务隔离级别的支持程度：

| 隔离级别                     | Oracle  | MySQL   |
| ---------------------------- | ------- | ------- |
| 读未提交（READ UNCOMMITTED） | ×       | √       |
| 读已提交（READ COMMITTED）   | √(默认) | √       |
| 可重复读（REPEATABLE READ）  | ×       | √(默认) |
| 串行化（SERIALIZABLE）       | √       | √       |



*用法：*

~~~Java
@Transactional(isolation = Isolation.DEFAULT)//使用数据库默认的隔离级别
@Transactional(isolation = Isolation.READ_UNCOMMITTED)//读未提交
@Transactional(isolation = Isolation.READ_COMMITTED)//读已提交
@Transactional(isolation = Isolation.REPEATABLE_READ)//可重复读
@Transactional(isolation = Isolation.SERIALIZABLE)//串行化
~~~



### 4.6.5、事务的传播行为

可以通过@Transactional中的propagation属性设置事务传播行为

- @Transactional(propagation = Propagation.REQUIRES_NEW)：业务中有多个方法（SQL），如果某个方法（SQL）出错了，那之前执行完的就提交，错误的方法（SQL）则回滚。
  - *人话就是：能完成多少是多少。*
- @Transactional(propagation = Propagation.REQUIRED)：默认值，会把整个业务看成一个事务，一旦里面任意一个方法出错，则所有执行过的方法都会回滚。
  - *人话是：不能出一点错，错了就重来。*

*用法：*

```Java
@Transactional(propagation = Propagation.REQUIRES_NEW)
```



## 4.7、基于XML实现

> 了解即可，看看这多繁琐啊

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context" xmlns:tx="http://www.springframework.org/schema/tx"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context https://www.springframework.org/schema/context/spring-context.xsd http://www.springframework.org/schema/tx http://www.springframework.org/schema/tx/spring-tx.xsd http://www.springframework.org/schema/aop https://www.springframework.org/schema/aop/spring-aop.xsd">

    <context:property-placeholder location="jdbc.properties"/>
    <context:component-scan base-package="com.aip" />
    <bean id="dataSource" class="com.alibaba.druid.pool.DruidDataSource">
        <property name="driverClassName" value="${jdbc.driver}" />
        <property name="url" value="${jdbc.url}" />
        <property name="username" value="${jdbc.username}" />
        <property name="password" value="${jdbc.password}" />
    </bean>

    <bean class="org.springframework.jdbc.core.JdbcTemplate">
        <property name="dataSource" ref="dataSource"/>
    </bean>

    <bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
        <property name="dataSource" ref="dataSource"/>
    </bean>

    <!--配置通知事务-->
    <aop:config>
        <aop:advisor advice-ref="txAdvice" pointcut="execution(* com.aip.service.impl.*.*(..))" />
    </aop:config>

    <!-- tx:advice标签：配置事务通知 -->
    <!-- id属性：给事务通知标签设置唯一标识，便于引用 -->
    <!-- transaction-manager属性：关联事务管理器 -->
    <tx:advice id="txAdvice" transaction-manager="transactionManager">
        <tx:attributes>
            <tx:method name="buyBook"/>
            <!-- tx:method标签：配置具体的事务方法 -->
            <!-- name属性：指定方法名，可以使用星号代表多个字符 -->
            <tx:method name="get*" read-only="true"/>
            <tx:method name="query*" read-only="true"/>
            <tx:method name="find*" read-only="true"/>
            <!-- read-only属性：设置只读属性 -->
            <!-- rollback-for属性：设置回滚的异常 -->
            <!-- no-rollback-for属性：设置不回滚的异常 -->
            <!-- isolation属性：设置事务的隔离级别 -->
            <!-- timeout属性：设置事务的超时属性 -->
            <!-- propagation属性：设置事务的传播行为 -->
            <tx:method name="save*" read-only="false" rollback-for="java.lang.Exception" propagation="REQUIRES_NEW"/>
            <tx:method name="update*" read-only="false" rollback-for="java.lang.Exception" propagation="REQUIRES_NEW"/>
            <tx:method name="delete*" read-only="false" rollback-for="java.lang.Exception" propagation="REQUIRES_NEW"/>
        </tx:attributes>
    </tx:advice>
</beans>
```
