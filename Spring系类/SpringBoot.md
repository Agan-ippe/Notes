# 1、初识SpringBoot

> 什么是SpringBoot
>
> - SpringBoot 是整合 Spring 技术栈的一站式框架
> - SpringBoot 是简化 Spring 技术栈的快速开发脚手架



## 1.1、SpringBoot的优缺点

优点：

- 创建独立的Spring应用
- 内嵌Web服务器
- 自动starter依赖，简化构建配置
- 自动配置Spring以及第三方功能
- 提供生产级别的监控、健康检查及外部化配置
- 无代码生成，无需编写XML

---

缺点：

- 版本迭代快，需要时刻关注。
- 封装太深，内部原理复杂。
- 底层封装的是Spring，所以 SpringBoot 的精通程度取决于你对 Spring 的精通程度。



## 1.2、Hello World！

上文了解到，SpringBoot其实就是一个整合了Spring生态的一个脚手架。通过这个`Hello World`的实践，可以发现，在SpringBoot应用中，我们不再需要去配置`Spring、SpringMVC`的配置文件。只需要通过`@SpringBootApplication`注解，将一个主方法标识为`Spring Boot`应用。再通过主方法启动即可。



> Spring Boot配置文件

```xml
<parent>
    <artifactId>spring-boot-starter-parent</artifactId>
    <groupId>org.springframework.boot</groupId>
    <version>2.5.14</version>
</parent>

<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
</dependencies>
```



> 主方法：这个固定写法
>

```Java
/**
 * @author Aip
 * @version 1.0
 * @date 2023/8/27  13:55
 * @description Spring Boot 入门之 Hello World!
 * @SpringBootApplication 该注解作用：将类标识为一个 Spring Boot应用
 */
@SpringBootApplication
public class MainApplication {

    /**
     * 这个SpringBoot的主程序类，这是固定写法
     * @param args args
     */
    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class,args);
    }
}
```



> 控制层

```Java
@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String hello(){
        return "Hello SpringBoot!";
    }
}
```

![1693116933801](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/1693116933801.png)



## 1.3、Spring Boot简化配置

所有的配置都可以放在这个配置文件下`application.properties`。

这是一个常用且重要的配置文件，只是在微服务（分布式）开发中常常用`application.yml`代替。

> 具体可参考 [常见的 Application Properties (springdoc.cn)](https://springdoc.cn/spring-boot/application-properties.html#appendix.application-properties)



## 1.4、简化部署

```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
        </plugin>
    </plugins>
</build>
```

通过官方提供的这个插件，可以直接导出一个可执行的jar包。它完全可以运行在生产环境中。 可执行的jar文件（有时被称为 “fat jar”）是包含你的编译类以及你的代码运行所需的所有jar依赖项的压缩包。

将项目打包后，可以直接在目标服务器执行。





# 2、SpringBoot常用注解

- @SpringBootApplication
- @Configuration
- @Import
- @Conditional
- @ImportResource



### 2.1、@SpringBootApplication

用于主方法中，将主方法标识为一个`Spring Boot`应用。



### 2.2、@Configuration

作用：将类标识为一个配置文件。

其保持了`Spring`的特性，默认使用`Full`模式

~~~java
@Configuration(proxyBeanMethods = true) //Full模式 完整配置模式
@Configuration(proxyBeanMethods = false)//Lite模式 轻量级配置模式
~~~

- Full模式会将配置类或者bean对象放进容器中，如果调用，就会先去容器中检查是否存在，不存在才会创建新的对象。所以多次调用的 Bean 对象，始终指向同一个引用。
- Lite模式下，配置类中的方法就是普通方法，不会生成代理类，所以启动速度较快一些，但是调用重复Bean对象，会重新初始化多次。



> 简单示例

Myconfig：

```Java
@Configuration(proxyBeanMethods = false)
public class MyConfig {

    @Bean
    public User createUser(){
        return new User("张三", 18);
    }

    @Bean("tom")
    public Pet createPet(){
        return new Pet("tom");
    }
}
```



MainApplication：

```Java
@SpringBootApplication
public class MainApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(MainApplication.class, args);

        String[] names = run.getBeanDefinitionNames();

        MyConfig bean = run.getBean(MyConfig.class);
        System.out.println(bean);

        User user = bean.createUser();
        User user1 = bean.createUser();

        System.out.println(user == user1);
    }
}
```



false的结果

![image-20240805175329016](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20240805175329016.png)



true的结果

![image-20240805175524991](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20240805175524991.png)



> 关于这两种模式具体可参考：[Full模式与Lite模式](https://blog.csdn.net/u012702547/article/details/132534433?csdn_share_tail=%7B%22type%22%3A%22blog%22%2C%22rType%22%3A%22article%22%2C%22rId%22%3A%22132534433%22%2C%22source%22%3A%22m0_59059137%22%7D&fromshare=blogdetail)

开发建议：

- 不需要组件依赖时，即不需要创建新组件时使用`Lite模式`。
- 如果需要依赖组件就用`Full模式`。
- 大部分情况都使用 `Full模式`。



> 以下为某开源项目的一个配置类

```Java
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket baseRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
        .apiInfo(apiInfo())
       .groupName("基础版")
        .select()
        .apis(RequestHandlerSelectors.basePackage("com.yami.shop.api"))
        .paths(PathSelectors.any())
        .build();
    }

    @Bean
    public ApiInfo apiInfo() {
        return new ApiInfoBuilder()
        .title("mall4j商城接口文档")
        .description("mall4j商城接口文档Swagger版")
        .termsOfServiceUrl("http://www.mall4j.com/")
        .contact(new Contact("广州市蓝海创新科技有限公司","https://www.mall4j.com/", ""))
        .version("1.0")
        .build();
    }
}
```



### 2.3、@Import

该注解的作用就是*引入* ，使用该注解，可以在容器中中的创建某个类型的组件，这个组件的默认名称就是这个组件的全类名。

==注：==组件可以是一个或多个，若是多个组件，则组件之间用 *逗号* 相隔。

```Java
@Import({User.class, Hello.class})
@Configuration(proxyBeanMethods = true)
public class UserConfig {
    
    @Bean
    public User user(){
        return new User("Aip",18);
    }
}
```



### 2.4、@Conditional

条件装配：只有满足`@Conditional`指定的条件，才会进行组件的注入

![1693129447531](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/Conditional%E5%8F%8A%E5%85%B6%E8%A1%8D%E7%94%9F%E6%B3%A8%E8%A7%A3.png)

| 名称                           | 描述                                    |
| ------------------------------ | --------------------------------------- |
| ConditionalOnBean              | 当容器中 **存在** 指定的 Bean 时生效    |
| ConditionalOnMissingBean       | 当容器中 **不存在** 指定的 Bean 时生效  |
| ConditionalOnClass             | 当容器中 **存在** 指定的 Class 时生效   |
| ConditionalOnMissingClass      | 当容器中 **不存在** 指定的 Class 时生效 |
| ConditionalOnResource          | 当容器中 **存在** 指定的  资源 时生效   |
| ConditionalOnJava              | 是指定的 Java版本号 时生效              |
| ConditionalOnWebApplication    | 是一个 Web应用 时生效                   |
| ConditionalOnNotWebApplication | 不是一个 Web应用 时生效                 |
| ConditionalOnProperty          | 当配置文件中 配置了某个属性时生效       |



> demo

MyConfig

```Java
@Configuration
//@ConditionalOnBean 你也可以将条件装配放在配置类上，如果容器中没有 某个Bean，这个配置类不会生效
public class MyConfig {

    @Bean
    @ConditionalOnBean(name = "tom") //当容器中有tom这个bean时，才会创建user这个bean
    public User createUser(){
        return new User("张三", 18);
    }

//    @Bean("tom")
    //没有 @Bean 注解，这将是个普通方法，不会自动装配
    public Pet createPet(){
        return new Pet("tom");
    }
}
```



MainApplication

```Java
@SpringBootApplication
public class MainApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(MainApplication.class, args);

        boolean tom = run.containsBean("tom");
        System.out.println("容器中是否存在 tom 组件：" + tom);

        boolean user = run.containsBean("createUser");
        System.out.println("容器中是否存在 user 组件：" + user);
    }
}
```

![image-20240806163809436](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20240806163809436.png)





### 2.5、@ImportResource

原生配置文件引入，可以将原来的`*.xml`配置文件引入`SpringBoot`的配置类中。

```Java
@Configuration
@ImportResource("classpath:beans.xml")
public class MyConfig {

    @Bean
    @ConditionalOnBean(name = "tom")  //当容器中有tom这个bean时，才会创建user这个bean
    public User createUser(){
        return new User("张三", 18);
    }

//    @Bean("tom")
    public Pet createPet(){
        return new Pet("tom");
    }
}
```

beans：

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="MyUser" class="com.aip.pojo.User">
        <property name="name" value="张三"/>
        <property name="age" value="18"/>
    </bean>

    <bean id="MyPet" class="com.aip.pojo.Pet">
        <property name="name" value="旺财"/>
    </bean>
</beans>
```



```Java
@SpringBootApplication
public class MainApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(MainApplication.class, args);

        boolean myUser = run.containsBean("MyUser");
        boolean myPet = run.containsBean("MyPet");
        System.out.println("容器中是否存在 MyUser 组件：" + myUser);
        System.out.println("容器中是否存在 MyPet 组件：" + myPet);
    }
}
```

![image-20240806165144375](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20240806165144375.png)



### 2.6、@ConfigurationProperties

该注解的功能是：配置绑定，该注解只能作用于容器中的组件。

`@ConfigurationProperties`的两种使用方法：

- @Component + @ConfigurationProperties
- @EnableConfigurationProperties + @ConfigurationProperties



>@Component + @ConfigurationProperties
>
>- @Component：把普通pojo实例化到spring容器中

这种方法是用于基本类，也就是*Bean*对象上的。

Student

```Java
/**
 * @author Aip
 * @version 1.0
 * @date 2023/8/27  15:33
 * @description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor

@Component
@ConfigurationProperties(prefix = "student")
public class Student {
    /**
     * 学号
     */
    private Integer sid;
    /**
     * 学生姓名
     */
    private String studentName;
    /**
     * 年龄
     */
    private Integer age;
    /**
     * 班级
     */
    private String className;
    /**
     * 爱好
     */
    private String[] interests;
    /**
     * 科目
     */
    private List<String> subjects;
    /**
     * 成绩
     */
    private Map<String,Integer> score;
}

```



 application.properties

```properties
student.sid=0112
student.student-name=Aip
student.age=23
student.class-name=物联网应用技术
student.interests=[篮球,足球,音乐]
```



Controller

```Java
/**
 * @author Aip
 * @version 1.0
 * @date 2023/8/27  14:00
 */
@RestController
public class HelloController {

    @Autowired
    private User user;

    @RequestMapping("/hello")
    public String hello(){
        return "Hello SpringBoot!";
    }

   @GetMapping("/student")
    public Student testStudent(){
        return student;
    }
}
```

![image-20240806173330325](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20240806173330325.png)





> @EnableConfigurationProperties + @ConfigurationProperties
>
> - @EnableConfigurationProperties：作用是开启属性配置功能，绑定需要配置的类即可

这种方法是写在配置类中的，`@EnableConfigurationProperties`是在配置类中使用的。`@ConfigurationProperties`是在*Bean*对象中使用的。

>Bean对象

```Java
@Data
@ConfigurationProperties(prefix = "my-user")
public class User {
    private String name;
    private Integer age;

    public User() {
    }
    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}
```



> 配置类

```Java
@Configuration
@EnableConfigurationProperties(User.class)
public class UserConfig {
}
```



> properties

```properties
my-user.name=Aip
my-user.age=21
```

![image-20240806174800613](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20240806174800613.png)



# 3、配置文件.yaml

在Spring Boot的实际项目开发中，大多数选择`*.yml`作为配置文件。而不用`*.properties`用来做配置文件。



## 3.1、yaml的基本语法

==注意：==

- 大小写敏感
- 使用缩进表示层级关系
- 缩进不允许使用tab，只允许空格
- 缩进的空格数不重要，只要相同层级的元素左对齐即可
- '#'表示注释
- 字符串无需加引号，如果要加，`''`与`""`表示字符串内容 会被 转义/不转



---

> 键值对：YAML使用冒号 `:` 来表示键值对，键和值之间需要有一个空格。键通常是字符串，而值可以是各种数据类型。写法`key: value`

```yaml
url: jdbc:mysql:.....
username: root
password: root
driver-class-name: com.mysql.cj.jdbc.Driver
```



>嵌套结构：YAML支持嵌套的数据结构，通过缩进来表示层级关系。例如，可以在映射内包含其他映射或列表。

~~~yaml
server:
  port: 8111
spring:
  datasource:
    url: jdbc:mysql:....
    username: root
    password: root
    driver-class-name: com.mysql.cj.jdbc.Driver
    type: com.zaxxer.hikari.HikariDataSource
~~~



>注释：注释在YAML中使用 `#` 符号表示，可以在行内或行末添加注释来提供额外的说明。

```yaml
# 这是一个注释 
name: John Doe  # 这也是一个注释
```



>列表（数组）： 列表可以使用连字符 `-` 来表示，每个元素都以连字符和空格开头。列表中的元素可以是任何数据类型，包括字符串、数字、映射等。

```yaml
行内写法：  k: [v1,v2,v3]
#或者
k:
 - v1
 - v2
 - v3
```



> 对象：键值对的集合。map、hash、set、object 

```yaml
行内写法：  k: {k1:v1,k2:v2,k3:v3}
#或
k: 
  k1: v1
  k2: v2
  k3: v3
```



> 多行字符串： 在YAML中可以使用 `|` 符号表示多行字符串，或者使用 `>` 符号表示折叠的多行字符串。

```yaml
description: |
  This is a
  multi-line
  string.

summary: >
  This is a folded
  multi-line string.
```



> 案例
>
> ==特别注意：==`*.yml`文件中最好不要用*中文或其他特殊字符* 作为*key*，否则会有乱七八糟的错误。如果非要用的话，可以这样写`"[需要输入的内容]"`。
>
> 但是在正式开发中，最好避免使用中文充当*key* 。

```yml
student:
  sid: 04440112
  student-name: Aip
  age: 22
  class-name: 物联网应用技术
  interests:
    - 编程
    - 看书
    - 上网
  subjects: [Java,C/C++,计网,计组,数据结构]
  #   Map集合写法一
#  score:
#    Java: 85
#    C/C++: 80
#    计网: 75
#    计组: 75
#    数据结构: 80
#    操作系统: 85
#    数据库: 90

  #  写法二
  score: {"[计网]": 80,Java: 85,"[C/C++]": 85}
```



## 3.2、配置文件的绑定

> 作用：将`JavaBean`对象与配置文件绑定

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-configuration-processor</artifactId>
    <optional>true</optional>
</dependency>
```

 

> 官方文档中建议与上方配置处理器搭配使用
>
> 作用：避免在项目打包时，把配置处理器打成`jar`包，否则可能会占用`JVM`资源。

```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
            <configuration>
                <excludes>
                    <exclude>
                        <groupId>org.springframework.boot</groupId>
                        <artifactId>spring-boot-configuration-processor</artifactId>
                    </exclude>
                </excludes>
            </configuration>
        </plugin>
    </plugins>
</build>
```



# 4、SpringBoot之Web开发

## 4.1、SpringMVC的自动配置

> Spring Boot为Spring MVC提供了自动配置功能，对大多数应用程序都很适用。
>
> 自动配置在Spring的默认值基础上增加了以下功能。

- 包含了 `ContentNegotiatingViewResolver` 和 `BeanNameViewResolver` Bean。
- 支持为静态资源提供服务，包括对WebJars的支持。
- 自动注册 `Converter`、`GenericConverter` 和 `Formatter` Bean。
- 支持 `HttpMessageConverters`（配合内容协商理解原理）。
- 自动注册 `MessageCodesResolver`（国际化用）。
- 支持静态的 `index.html`。
- 自动使用 `ConfigurableWebBindingInitializer` bean（DataBinder负责将请求数据绑定到JavaBean上）



## 4.2、静态资源

> SpringBoot是如何访问静态资源的

只要静态资源放在类路径下：`/static` (or `/public` or `/resources` or `/META-INF/resources`)。

访问 ： 当前项目根路径/ + 静态资源名 

**原理： 静态映射/\**。**

请求进来，先去找Controller看能不能处理。不能处理的所有请求又都交给静态资源处理器。静态资源也找不到则响应404页面

~~~yml
spring:
  mvc:
#   private String staticPathPattern = "/**";
    static-path-pattern: /resources/**
  web:
    resources:
#	  自定义的静态资源访问路径
      static-locations: classpath:/haha,classpath:/static
    
#  Spring Boot 默认的静态资源路径
#  private static final String[] CLASSPATH_RESOURCE_LOCATIONS = { 
#  "classpath:/META-INF/resources/",
#  "classpath:/resources/", 
#  "classpath:/static/", 
#  "classpath:/public/" };
~~~



> webjar
>
> Spring Boot还支持Spring MVC提供的高级资源处理功能，允许使用的情况有：破坏缓存的静态资源或为Webjars使用版本无关的URL。
>
> 要对Webjars使用版本无关的URL，请添加 `webjars-locator-core` 依赖。 然后声明你的Webjar。 以jQuery为例，添加 `"/webjars/jquery/jquery.min.js"` 的结果是 `"/webjars/jquery/x.y.z/jquery.min.js"` ，其中 `x.y.z` 是Webjar的版本。
>
> 自动映射 /[webjars](http://localhost:8080/webjars/jquery/3.5.1/jquery.js)/**

~~~xml
<dependency>
    <groupId>org.webjars</groupId>
    <artifactId>jquery</artifactId>
    <version>3.5.1</version>
</dependency>
~~~

访问地址：[http://localhost:8080/webjars/**jquery/3.5.1/jquery.js**](http://localhost:8080/webjars/jquery/3.5.1/jquery.js)



==小提示：==

如果你的应用程序被打包成jar，请不要使用 `src/main/webapp` 目录。 尽管这个目录是一个通用的标准，但它只适用于war打包，如果你生成一个jar，它就会被大多数构建工具默认地忽略掉。



## 4.3、欢迎页

- 静态资源路径下  index.html

- - 可以配置静态资源路径
  - 但是不可以配置静态资源的访问前缀。否则导致 index.html不能被默认访问

- controller能处理/index



~~~yml
spring:
#  mvc:
#    static-path-pattern: /res/**   这个会导致welcome page功能失效

  resources:
    static-locations: [classpath:/haha/]
~~~



## 4.4、自定义Favicon

> 与其他静态资源一样，Spring Boot检查配置的静态内容位置中是否有 `favicon.ico`。 如果存在这样的文件，它就会自动作为应用程序的favicon。

~~~yml
spring:
#  mvc:
#    static-path-pattern: /res/**   这个会导致 Favicon 功能失效
~~~



## 4.5、请求处理

### 4.5.1、rest风格请求

这是一种规范，用不用看个人，个人认为没啥用。不如GET\POST来的方便。

`***.yaml`开启rest风格

```yaml
spring:
  mvc:
    hiddenmethod:
      filter:
        enabled: true
```



> 表单demo

```HTML
<form action="/query" method="get">
    <input value="REST-GET" type="submit"/>
</form>
<form action="/add/user" method="post">
    <input value="REST-POST" type="submit"/>
</form>
<form action="/put" method="post">
    <input name="_method" type="hidden" value="PUT">
    <input value="REST-PUT" type="submit"/>
</form>
<form action="/delete" method="post">
    <input name="_method" type="hidden" value="DELETE">
    <input value="REST-DELETE" type="submit"/>
</form>
```



> DemoController

```Java
package com.aip.rest.controller;

import org.springframework.web.bind.annotation.*;

/**
 * @author Aip
 * @version 1.0
 */
@RestController
public class RestDemoController {
    @GetMapping("/query")
    public String getAllUser(){
        System.out.println("查询所有的用户信息-->/user-->get");
        return "get";
    }

    @GetMapping("/user/{ID}")
    public String getUserByID(@PathVariable("ID") Integer ID){
        System.out.println("根据ID查询用户-->/user"+ID+"-->get");
        return "get by id";
    }

    @PostMapping("/add/user")
    public String insertUser(){
        System.out.println("添加用户-->/user-->post");
        return "post";
    }

    @PutMapping("/put")
    public String updateUser(){
        System.out.println("修改用户-->/user-->put");
        return "put";
    }

    @DeleteMapping("/delete")
    public String deleteUserByID(){
        System.out.println("删除用户-->/user-->delete");
        return "delete";
    }
}
```

自行体会一下。



### 4.5.1、常用注解

- `@PathVariable`：路径变量
- `@RequestHeader`：获取请求头
- `@RequestAttribute`：
- `@RequestParam`：获取请求参数
- `@MatrixVariable`：矩阵变量
- `@CookieValue`：获取cookie
- `@RequestBody`：获取请求体



### 4.5.2、PathVariable

把参数标记为路径变量，将其映射到请求中。

```Java
@GetMapping("/user/{ID}")
public String getUserByID(@PathVariable("ID") Integer ID){
    //TODO 查询操作
    return "get by id";
}
```





### 4.5.3、RequestHeader

```Java
@GetMapping("/testRequestHeader")
public Map testRequestHeader(@RequestHeader("User-Agent") String userAgent,
                              @RequestHeader Map<String,String> header){
    HashMap<String, Object> map = new HashMap<>();
    map.put("userAgent", userAgent);
    map.put("headers", header);
    return map;
}
```

```html
<form action="/testRequestHeader" method="get">
    <input value="获取请求头" type="submit"/>
</form>
```

![image-20240816181628560](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20240816181628560.png)



### 4.5.4、CookieValue

```Java
@GetMapping("/testCookie")
public String testRequestHeader(@CookieValue("Idea-a7d6643f") String cookie){
    return cookie;
}
```

![image-20240818205954905](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20240818205954905.png)



### 4.5.5、RequestBody

```Java
@PostMapping("/testRequestBody")
public String testRequestBody(@RequestBody String content){
    return content;
}
```

![image-20240818212128315](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20240818212128315.png)

![image-20240818212211771](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20240818212211771.png)

## 4.6、拦截器

怎么做一个拦截器

1. 确定并配置好拦截器需要拦截的请求
2. 把拦截器的配置放在容器中
3. 指定拦截规则，合理拦截，避免拦截静态资源



> 创建一个拦截器

```java
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {


    //目标执行方法之前
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {


        String requestURI = request.getRequestURI();
        log.info("拦截的请求路径{}",requestURI);

        HttpSession session = request.getSession();
        Object loginUser = session.getAttribute("loginUser");

        if (loginUser != null){
            //放行
            return true;
        }
        //非法请求，未登录。 ---->  跳转到登录页面
        request.setAttribute("msg","请登录！");
        request.getRequestDispatcher("/").forward(request,response);
        // response.sendRedirect("/");
        return false;
    }

    //目标方法执行完之后
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    //页面渲染以后
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
```



> 提供配置类，将拦截器配置到容器中

```java
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // addPathPatterns()：需要拦截请求
        // excludePathPatterns()：需要放行的请求
        // /** ：表示所有请求，包括静态资源
       registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/", "/login", "/login.html");
    }
}
```





## 4.7、文件上传

> 文件上传表单的固定写法

```html
<form role="form" th:action="@{/upload}" method="post" enctype="multipart/form-data">
```



~~~java
    /**
     * MultipartFile 自动封装上传过来的文件
     * @param email
     * @param username
     * @param headerImg
     * @param photos
     * @return
     */
    @PostMapping("/upload")
    public String upload(@RequestParam("email") String email,
                         @RequestParam("username") String username,
                         @RequestPart("headerImg") MultipartFile headerImg,
                         @RequestPart("photos") MultipartFile[] photos) throws IOException {

        log.info("上传的信息：email={}，username={}，headerImg={}，photos={}",
                email,username,headerImg.getSize(),photos.length);

        if(!headerImg.isEmpty()){
            //保存到文件服务器，OSS服务器
            String originalFilename = headerImg.getOriginalFilename();
            headerImg.transferTo(new File("H:\\cache\\"+originalFilename));
        }

        if(photos.length > 0){
            for (MultipartFile photo : photos) {
                if(!photo.isEmpty()){
                    String originalFilename = photo.getOriginalFilename();
                    photo.transferTo(new File("H:\\cache\\"+originalFilename));
                }
            }
        }


        return "main";
    }
~~~

---

==注意：==文件上传解析器是默认限制文件上传大小的。可以在配置文件中自定义文件大小，及其他属性。

![1694485136824](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/1694485136824.png)





## 4.8、异常处理

默认情况下，Spring Boot提供了一个 `/error` 映射，以合理的方式处理所有错误，它被注册为servlet容器中的 “global” 错误页面。 对于机器客户端，它产生一个JSON响应，包含错误的细节、HTTP状态和异常消息。 对于浏览器客户端，有一个 “whitelabel” error view，以HTML格式显示相同的数据（要自定义它，添加一个 `View`，解析为 `error`）。

如果你想定制默认的错误处理行为，有一些 `server.error` 属性可以被设置。 参见附录中的[“Server Properties”](https://springdoc.cn/spring-boot/application-properties.html#appendix.application-properties.server)部分。

要完全替换默认行为，你可以实现 `ErrorController` 并注册该类型的Bean定义，或者添加 `ErrorAttributes` 类型的Bean来使用现有机制但替换内容。



## 4.9、Web原生API注入

> 官方文档参考： [Web (springdoc.cn)](https://springdoc.cn/spring-boot/web.html#web.servlet.embedded-container)
>
> 使用方法：
>
> - 方式一：当使用嵌入式容器时，可以通过使用 `@ServletComponentScan` 来启用对 `@WebServlet`、`@WebFilter` 和 `@WebListener` 注解的类的自动注册。
> - 方式二：使用RegistrationBean
>   - `ServletRegistrationBean`
>   - `FilterRegistrationBean`
>   - ``ServletListenerRegistrationBean`



---

*方式一*

```java
@WebServlet(urlPatterns = "/my")
public class MyServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().write("66666");
    }
}
```

```java
@ServletComponentScan(basePackages = "com.aip.servlet")
@SpringBootApplication
public class MainApplication {
    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class,args);
    }
}
```



---

*方式二*

~~~java
@Configuration
public class MyRegistConfig {

    @Bean
    public ServletRegistrationBean myServlet(){
        MyServlet myServlet = new MyServlet();

        return new ServletRegistrationBean(myServlet,"/my","/my02");
    }


    @Bean
    public FilterRegistrationBean myFilter(){

        MyFilter myFilter = new MyFilter();
//        return new FilterRegistrationBean(myFilter,myServlet());
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(myFilter);
        filterRegistrationBean.setUrlPatterns(Arrays.asList("/my","/css/*"));
        return filterRegistrationBean;
    }

    @Bean
    public ServletListenerRegistrationBean myListener(){
        MySwervletContextListener mySwervletContextListener = new MySwervletContextListener();
        return new ServletListenerRegistrationBean(mySwervletContextListener);
    }
}
~~~





# 5、数据访问

## 5.1、连接数据库

> 连接MySQL数据库

Spring Boot底层配置好的连接池是：**HikariDataSource**

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jdbc</artifactId>
</dependency>

<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>8.0.17</version>
</dependency>
```

---

~~~yml
datasource:
  url: jdbc:mysql://localhost:3306/ssm?serverTimezone=UTC
  username: root
  password: 123456
  driver-class-name: com.mysql.cj.jdbc.Driver
~~~



>使用Druid连接池

配置文件模板

~~~yml
spring:
  datasource:
    driver-class-name: com.mysql.jdbc.Driver
    druid:
      initialSize: 1 #初始化时建立物理连接的个数。初始化发生在显示调用init方法，或者第一次getConnection时
      minIdle: 1  #最小连接池数量
      maxActive: 20 #最大连接池数量
      maxWait: 2000 #获取连接时最大等待时间，单位毫秒。配置了maxWait之后，缺省启用公平锁，并发效率会有所下降，如果需要可以通过配置useUnfairLock属性为true使用非公平锁。建议配置2000ms
      timeBetweenEvictionRunsMillis: 60000 #1) Destroy线程定时监测的间隔， Destroy线程会检测连接的间隔时间，如果连接空闲时间大于等于minEvictableIdleTimeMillis则关闭物理连接。2) testWhileIdle的判断依据，详细看testWhileIdle属性的说明
      minEvictableIdleTimeMillis: 300000 #连接保持空闲而不被驱逐的最长时间。建议值：5* timeBetweenEvictionRunsMillis
      validationQuery: SELECT 1 #用来检测连接是否有效的sql，要求是一个查询语句，常用select 'x'。如果validationQuery为null，testOnBorrow、testOnReturn、testWhileIdle都不会起作用。
      testWhileIdle: true #建议配置为true，不影响性能，并且保证安全性。申请连接的时候检测，如果空闲时间大于timeBetweenEvictionRunsMillis，执行validationQuery检测连接是否有效。
      testOnBorrow: false  #申请连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能。建议值：false
      testOnReturn: false #归还连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能。建议值：false
      poolPreparedStatements: true  #是否缓存preparedStatement，也就是PSCache。PSCache对支持游标的数据库性能提升巨大，比如说oracle。在mysql5.5以下的版本中没有PSCache功能，建议关闭掉。5.5及以上版本有PSCache，建议开启。
      maxPoolPreparedStatementPerConnectionSize: 20 #要启用PSCache，必须配置大于0，当大于0时，poolPreparedStatements自动触发修改为true。在Druid中，不会存在Oracle下PSCache占用内存过多的问题，可以把这个数值配置大一些，比如说100
      filters: stat,wall  #属性类型是字符串，通过别名的方式配置扩展插件，常用的插件有：监控统计用的filter:stat，日志用的filter:log4j， 防御sql注入的filter:wall
      connectionProperties: druid.stat.mergeSql=true;druid.stat.slowSqlMillis=5000 #建立新连接时将发送到JDBC驱动程序的连接属性。字符串的格式必须为[propertyName = property;] *注 - “用户”和“密码”属性将被明确传递，因此不需要在此处包含。
      # 要打开Druid监控页面需要做以下配置
      stat-view-servlet:
        enabled: true # 这个一定要加，不然【http://localhost:8080/druid/index.html】页面打不开
        url-pattern: '/druid/*'
        allow: 127.0.0.1
        #deny: 192.168.1.73
        login-username: admin
        login-password: 123456
        reset-enable: false
      web-stat-filter:
        enabled: true # 这个一定要加，不然【http://localhost:8080/druid/index.html】页面打不开
        url-pattern: '/*'
        exclusions: '*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*'
        session-stat-enable: true
        
        filter:
        stat:    # 对上面filters里面的stat的详细配置
          slow-sql-millis: 1000
          logSlowSql: true
          enabled: true
        wall:
          enabled: true
          config:
            drop-table-allow: false        
~~~



### 5.1.1、整合Mybatis

引入依赖，配好配置文件，就行了。在每个`mapper`接口上标注上`@mapper`注解，其他方式照常使用。

如果不想在每一个`mapper`接口上都加上注解，可以在Spring Boot的启动类上加上`@MapperScan("你的mapper接口所在的包")`



> 基本的配置文件

```yml
mybatis:
  mapper-locations: classpath:mapper/*Mapper.xml
  configuration:
    map-underscore-to-camel-case: true
```



### 5.1.2、整合Mybatis-puls

*关于 MP 的源码，可以自行阅读，MP本质上就是一个MyBatis的集成工具，目的就是在原来的用法上简化开发，将一些简单基础的SQL语句进行提取。源码注释有中文，可自行阅读理解。*

用法就是，将`mapper`类实现`BaseMapper`接口。

![1695112173592](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/MP.png)



> 关于配置：MP在底层都自动配置好了，包括项目中`mapper`映射文件的包。（为什么还需要映射文件，我们可以用映射文件来写一些比较复杂的SQL语句，因为`BaseMapper`里面只能满足一些基本的增删改查。）
>
> @Mapper 标注的接口也会被自动扫描；建议直接 `@MapperScan("映射文件的位置")` 批量扫描就行。



依赖

```xml
<dependency>
    <groupId>com.baomidou</groupId>
    <artifactId>mybatis-plus-boot-starter</artifactId>
    <version>3.5.3</version>
</dependency>
```



当一个`JavaBean`对象中的某些属性，在数据库中不存在。可以使用MP中的`@TableField`注解来标注。

> 其注释的用法

```Java
@TableName("表名")   //用来关联数据库表，默认值为空。
public class User {
    @TableField(exist = false)   //属性是否属于数据库字段
    public String userName;
    public String password;
}
```





# 6、单元测试

## 6.1、使用SpringBoot测试

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-test</artifactId>
</dependency>
```

```Java
@SpringBootTest
public class UserTest {

    @Test
    public void test(){
        System.out.println(1 + 1);
    }
}
```



## 6.2、Junit5常用注解

- @Test：测试方法
- @ParameterizedTest：参数化测试
- @RepeatedTest(num)：重复测试 num 次
- @DisplayName("测试")：设置测试了或测试方法展示名称
- @BeforeAll：在所有单元测试之前执行
- @AfterAll：在所有单元测试之后执行
- @BeforeEach：在每个单元测试之前执行
- @AfterEach：在每个单元测试之后执行
- @Tag("tag")：表示单元测试类别
- @Disabled：表示测试类或测试方法不执行
- @Timeout(1)：测试方法如果超时，返回错误
- @ExtendWith({MyTestExtension.class})：为测试类或测试方法提供拓展类引用



### 6.2.1、DisplayName

```java
@Test
@DisplayName("测试显示名称")
public void testDisplayName(){
    System.out.println(1);
}
```

![image-20240823125352734](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20240823125352734.png)



### 6.2.2、RepeatedTest

```Java
@RepeatedTest(2)
public void testRepeatedTest(){
    System.out.println("方法重复执行");
}
```

![image-20240823125601673](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20240823125601673.png)



### 6.2.3、BeforeEach

```Java
@SpringBootTest
public class AnnotationTest {
        @Test
        @DisplayName("测试显示名称")
        public void testDisplayName(){
            System.out.println(1);
        }

        @BeforeEach
        public void testBeforeEach(){
            System.out.println("测试方法执行前");
        }

        @AfterEach
        public void testAfterEach() {
            System.out.println("测试方法执行后");
        }
}
```

![image-20240823125856339](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20240823125856339.png)



### 6.2.4、BeforeAll

使用 `BefroeAll \ AfterAll` 注解时，其方法必须是一个静态方法。

```Java
@SpringBootTest
public class AnnotationTest {

        @Test
        @DisplayName("测试方法1")
        public void testDisplayName(){
            System.out.println(1);
        }

        @Test
        @DisplayName("测试方法2")
        public void test(){
            System.out.println(2);
        }

        @BeforeEach
        public void testBeforeEach(){
            System.out.println("测试方法执行前");
        }

        @AfterEach
        public void testAfterEach() {
            System.out.println("测试方法执行后");
        }

        @BeforeAll
        public static void testBeforeAll(){
            System.out.println("所有测试方法执行前");
        }

        @AfterAll
        public static void testAfterAll(){
            System.out.println("所有测试方法执行后");
        }
```

![image-20240824175625304](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20240824175625304.png)



### 6.2.5、Disabled

```Java
@SpringBootTest
public class AnnotationTest {

        @Test
        @DisplayName("测试方法1")
        public void testDisplayName(){
            System.out.println(1);
        }

        @Test
        @DisplayName("测试方法2")
        @Disabled
        public void test(){
            System.out.println(2);
        }

        @RepeatedTest(2)
        @Disabled
        public void testRepeatedTest(){
            System.out.println("方法重复执行");
        }
```

![image-20240824175940944](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20240824175940944.png)



### 6.2.6、Timeout

```java
@SpringBootTest
public class AnnotationTest {
        @Timeout(value = 1000,unit = TimeUnit.MILLISECONDS)
        @Test
        public void testTimeout() throws InterruptedException {
                Thread.sleep(5000);
        }
}
```

![image-20240824180615697](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20240824180615697.png)



## 6.3、断言

实际开发中，可以在测试类中写一些断言。其作用是，如果运行的结果不符合预期，就强制退出程序。并返回错误信息。

```Java
@SpringBootTest
public class AssertionTest {

    Integer count(Integer num1,Integer num2 ){
        return num1 + num2;
    }

    @Test
    public void testAssertEquals(){
        Integer count = count(1, 2);
        Assertions.assertEquals(5, count, "自定义错误信息");

    }
}
```

![image-20240824182842239](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20240824182842239.png)



### 6.3.1、常用断言

| 名称            | 描述                                |
| --------------- | ----------------------------------- |
| assertEquals    | 判断期望值与目标值是否 相同         |
| assertNotEquals | 判断期望值与目标值是否 不同         |
| assertSame      | 判断期望值与目标值是否 引用同一对象 |
| assertNotSame   | 判断期望值与目标值是否 引用不同对象 |
| assertTrue      | 判断期望值是否为 true               |
| assertFalse     | 判断期望值是否为 false              |
| assertNull      | 判断期望值是否为 Null               |
| assertNotNull   | 判断期望值是否 非Null               |
| assertTimeout   |                                     |
|                 |                                     |
|                 |                                     |

### 6.3.2、组合断言

需要其中的所有断言全部通过，才能继续运行。

```Java
/**
 * 组合断言
 */
void testAssertAll(){
    Assertions.assertAll("断言描述",
            () -> Assertions.assertEquals(5, result,"自定义错误信息"),
            () -> Assertions.assertTrue(result,"自定义错误信息")
    );
}
```



### 6.3.3、异常断言

在程序一定会出现异常时使用，如果出现异常则继续运行。如果正常运行，则断言强制结束程序。

```Java
/**
 * 断言异常
 */
@Test
void testAssertThrows() {

    Assertions.assertThrows(ArithmeticException.class, () -> {
        int i = 1 / 1;
    }, "自定义错误信息");
}
```

![image-20240824185411192](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20240824185411192.png)



### 6.3.4、快速失败

使用场景，测试完这个功能之后，剩下的功能不测了。快速失败

```JavaScript
@Test
    void testFail() {
        Assertions.fail("今天的测试就到这里吧");
    }
```

![image-20240824185758633](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20240824185758633.png)

